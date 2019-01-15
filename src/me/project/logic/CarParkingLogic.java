package me.project.logic;

import java.util.Random;
import me.project.model.AbstractModel;
import me.project.model.AdHocCar;
import me.project.model.Car;
import me.project.model.CarQueue;
import me.project.model.Location;
import me.project.model.ParkingPassCar;
import me.project.view.AbstractView;


/**
 * Class for the "Main" model of Simulator
 * @author Bryan Dijkhuizen, Daphne Gritter, Kevin Wu, Thalisa Jagt
 *
 */

public class CarParkingLogic extends AbstractModel {
	private static int numberOfFloors;
    private static int numberOfRows;
    private static int numberOfPlaces;
    private static CarQueue entranceCarQueue;
    private static CarQueue paymentCarQueue;
    private static CarQueue membersCarQueue;
    private static CarQueue exitCarQueue;
    private Car[][][] cars;
    
    int amountOfPassHolders;
    
    private double standardDeviation;
    private double numberOfCarsPerHour;
    private int numberOfCarsPerMinute;
    private int averageNumberOfCarsPerHour;
    private Random random;
    
    private double numberOfMembersPerHour;
    private int numberOfMembersPerMinute;
    private int numberTotalCarsPerMinute;

    
    private int day = 0;
    private int hour = 0;
    private int minute = 0;

    int weekDayArrivals= 200; // average number of arriving cars per hour
    int weekendArrivals = 100; // average number of arriving cars per hour

    int enterSpeed;
    int paymentSpeed;
    int exitSpeed; 
    
    int numberOfEnteringCars; // how many cars are in entranceCarQueue
    int numberOfPayingCars; // how many cars are in paymentCarQueue
    int numberOfExitingCars; // how many cars are in exitCarQueue
    int numberOfMembersExiting; // how many cars are in the membersCarQueue
    int totalCars; // amount of no member cars in car park 
    int totalPassHolders; // amount of members in car park
    int total; // amount of all cars in car park

    /**
     * Constructor of the CarPark. It initializes the fields and creates the
     * three dimensional array to represent the parking spots in the car park.
     *
     * @param numberOfFloors The number of floors of the car park
     * @param numberOfRows   The number of rows per floor of the car park
     * @param numberOfPlaces The number of parking spots per row of the car park
     */
    public CarParkingLogic(int numberOfFloors, int numberOfRows, int numberOfPlaces) {
        this.numberOfFloors = numberOfFloors;
        this.numberOfRows = numberOfRows;
        this.numberOfPlaces = numberOfPlaces;

        entranceCarQueue = new CarQueue();
        paymentCarQueue = new CarQueue();
        membersCarQueue = new CarQueue();
        exitCarQueue = new CarQueue();
        
        enterSpeed = 3; 
        paymentSpeed = 10; 
        exitSpeed = 9; 
        
        amountOfPassHolders = 100;
        numberOfEnteringCars = 0;
        numberOfExitingCars = 0;
        
        totalCars = 0;
        totalPassHolders = 0;
        
        cars = new Car[numberOfFloors][numberOfRows][numberOfPlaces];
        
    }
    
    /**
     * This method creates a random number.
     * @param min
     * @param max
     * @return returns a random number between the min and max value 
     */
    public int getRandInt(int max, int min){
    	
    	Random rand;
    	rand = new Random();
    	
    	int randNum = rand.nextInt(max - min) + min; 
    	
    	return randNum;
    }
 
    /**
     * Get number of floors of the car park.
     *
     * @return numberOfFloors Number of floors of the car park
     */
    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    /**
     * Get number of rows of the car park.
     *
     * @return numberOfRows Number of rows of the car park
     */
    public int getNumberOfRows() {
        return numberOfRows;
    }

    /**
     * Get number of parking spots in the car park.
     *
     * @return numberOfPlaces Number of parking spots in the car park
     */
    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    /**
     * Get the three dimensional array that represents the car park.
     *
     * @return cars Return the array cars
     */
    public Car[][][] getCars() {
        return cars;
    }

    /**
     * Get queue of all the cars are entering the car park.
     *
     * @return cars Return the queue with all cars entering
     */
    public CarQueue getEntranceCarQueue() {
        return entranceCarQueue;
    }

    /**
     * Get queue of all the cars are exiting the car park.
     *
     * @return cars Return the queue with all cars exiting
     */
    public CarQueue getExitCarQueue() {
        return exitCarQueue;
    }
    
    /**
     * Change entering speed.
     * 
     * @param newSpeed The speed the user wants to change the entering speed to.
     */
    public void setEnterSpeed(int newSpeed) {
    	enterSpeed = newSpeed;
    }
    
    /**
     * Change payment speed.
     * 
     * @param newSpeed The speed the user wants to change the payment speed to.
     */
    
    public void setPaySpeed(int newSpeed) {
    	paymentSpeed = newSpeed;
    }
    
    /**
     * Change exiting speed.
     * 
     * @param newSpeed The speed the user wants to change the exiting speed to.
     */
    public void setExitSpeed(int newSpeed) {
    	exitSpeed = newSpeed;
    }
    
    /**
     * @return enterSpeed The speed with which cars currently are entering per minute.
     */
	public int getEnterSpeed() {
		return enterSpeed;
	}
	
    /**
     * @return exitSpeed The speed with which cars currently are exiting per minute.
     */
	public int getExitSpeed(){
		return exitSpeed;
	}

    /**
     * Executes one simulation step, it advances the time by one minute.
     */
	public void tick() {
        advanceTime();
        tickCalculations();
        tickNonMemberCarsToBack();
        tickMemberCarsToBack();
        tickCarFromQueueToFreeSpace();
        tickLeavingCarsToExitQueue();
        tickNonMembersLeave();
        tickMembersLeave();
        
        total = totalCars + totalPassHolders;
        // Update the car park view.
        super.notifyViews();
        }
	
	public void advanceTime() {
		// Advance the time by one minute.
        minute++;
        if (minute > 59) {
            minute -= 60;
            hour++;
        }
        if (hour > 23) {
            hour -= 24;
            day++;
        }
        if (day > 6) {
            day -= 7;
        }
	}
	
	
	public void tickCalculations() {
		random = new Random();

        // Get the average number of cars that arrive per hour.
        averageNumberOfCarsPerHour = day < 5
                ? weekDayArrivals
                : weekendArrivals;

        // Calculate the number of cars of people who are not members that arrive this minute.
        standardDeviation = averageNumberOfCarsPerHour * 0.1;
        numberOfCarsPerHour = averageNumberOfCarsPerHour + random.nextGaussian() * standardDeviation;
        numberOfCarsPerMinute = (int)Math.round(numberOfCarsPerHour / 60);
   
        // Calculates the number of members that arrive each minute.
        numberOfMembersPerHour = (amountOfPassHolders / 6) + random.nextGaussian() * standardDeviation;
        numberOfMembersPerMinute = (int)Math.round(numberOfMembersPerHour / 60);
        numberTotalCarsPerMinute = numberOfCarsPerMinute + numberOfMembersPerMinute;
	}


	public void tickNonMemberCarsToBack() {
		// Add the cars of people who are not members to the back of the entrance queue.
        for (int j = 0; j < numberTotalCarsPerMinute; j++) { 
            for (int i = 0; i < numberOfCarsPerMinute; i++) {
                Car car = new AdHocCar();
                numberOfEnteringCars++;
                totalCars++;
                entranceCarQueue.addCar(car);
            }
        }
	}
	
	public void tickMemberCarsToBack() {
		// Add the cars of members to the back of the entrance queue.
        for (int i = 0; i < numberOfMembersPerMinute ; i++) {
            Car car = new ParkingPassCar();
            numberOfEnteringCars++;
            totalPassHolders++;
            entranceCarQueue.addCar(car);
        }

        super.notifyViews();
    }
	
	
	public void tickCarFromQueueToFreeSpace() {
		// Remove car from the front of the queue and assign to a parking space.
        for (int i = 0; i < enterSpeed; i++) {
        
            Car car = entranceCarQueue.removeCar();

            if (car == null) {
                break;
            } else {
            	numberOfEnteringCars--;
            }
            // Find a space for this car.
            Location freeLocation = this.getFirstFreeLocation();
            if (freeLocation != null) {
                this.setCarAt(freeLocation, car);
                
              }
            }
            super.notifyViews();
        

        this.tickCars();
	}
	
	public void tickLeavingCarsToExitQueue() {
		// Add leaving cars to the exit queue.
        while (true) {
            Car car = this.getFirstLeavingCar();
            if (car == null) {
                break;
            }

            //if a car isn't a member it goes to the payment queue else it goes to the members queue,
            // members pay monthly so they don't need to pay.
            if(car instanceof AdHocCar){
            	numberOfPayingCars++;
            	paymentCarQueue.addCar(car);
            	break;
            	
            	
            } else if(car instanceof ParkingPassCar) { 
                numberOfMembersExiting++;
            	membersCarQueue.addCar(car);
                this.removeCarAt(car.getLocation());
                if (car instanceof ParkingPassCar) {
                	totalPassHolders--;
                }
                break;
            }
            

            super.notifyViews();
        }
	}
	
	public void tickNonMembersLeave() {
		// Let non members leave.
        for (int i = 0; i < exitSpeed; i++) {
            Car car = exitCarQueue.removeCar();
            if (car == null) {
                break;
            } else {
                numberOfExitingCars--;	
            }
            super.notifyViews();
            // Bye!
        }
	}
	
	public void tickMembersLeave() {
		// Let members leave.
        for (int i = 0; i < exitSpeed; i++) {
            Car car = membersCarQueue.removeCar();
            if (car == null) {
                break;
            } else {
            	numberOfMembersExiting--;
            }
            super.notifyViews(); 
            // Bye!
        }
	}
	
    
    
    public Location getFirstFreeLocation() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    if (getCarAt(location) == null) {
                        return location;
                    }
                }
            }
        }
        return null;
    }
    

    /**
     * For every car call the tick method by looping through the car park.
     */
    private void tickCars() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = this.getCarAt(location);
                    if (car != null) {
                        car.tick();
                    }
                }
            }
        }
    }

    /**
     * Get a car from a specific location in the car park.
     *
     * @param location A Location object which stores where the car is parked.
     * @return car The car that is parked at the given location.
     */
    public Car getCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        return cars[location.getFloor()][location.getRow()][location.getPlace()];
    }

    /**
     * Put a car in a certain parking spot, a certain Location in the car park.
     *
     * @param location Location object where the car should be parked
     * @param car The car that needs to be parked
     * @return boolean Return true if the car is successfully parked, if not return false
     */
    public boolean setCarAt(Location location, Car car) {
        if (!locationIsValid(location)) {
            return false;
        }
        Car oldCar = getCarAt(location);
        if (oldCar == null) {
            cars[location.getFloor()][location.getRow()][location.getPlace()] = car;
            car.setLocation(location);
            return true;
        }
        return false;
    }
    
    /**
     * Check if a specific location is empty in the car park.
     *
     * @param location Location to check
     * @return boolean Return true if location is empty, if not return
     */
    private boolean locationIsValid(Location location) {
        int floor = location.getFloor();
        int row = location.getRow();
        int place = location.getPlace();
        if (floor < 0 || floor >= numberOfFloors || row < 0 || row > numberOfRows || place < 0 || place > numberOfPlaces) {
            return false;
        }
        return true;
    }

    /**
     * Gets the first free parking spot that is empty in the car park.
     *
     * @return location | null If empty location is found return location, if not return null 
     */

    /**
     * Get the first leaving car in the car park.
     *
     * @return car | null | If a leaving car is found return this car, if not return null
     */
    public Car getFirstLeavingCar() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = getCarAt(location);
                    if (car != null && car.getMinutesLeft() <= 0) {
                        return car;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Remove a car at a specific location in the car park.
     *
     * @param location Location of the car that needs to be removed
     * @return car | null | If the location is not valid or there is no car at the 
     *                      specified location return null, else return the car
     */
    public Car removeCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        Car car = getCarAt(location);
        if (car == null) {
            return null;
        }
        cars[location.getFloor()][location.getRow()][location.getPlace()] = null;
        car.setLocation(null);
        return car;
    }
    
    /**
     * @return numberOfEnteringCars
     */
    public int getNumberOfEntering() {
        return numberOfEnteringCars;
    }

    /**
     * @return numberOfPayingCars
     */
    public int getNumberOfPaying() {
        return numberOfPayingCars;
    }

    /**
     * @return numberOfExitingCars
     */
    public int getNumberOfExiting() {
        return numberOfExitingCars;
    }
    
    /**
     * @return totalCars Return total number of no member cars in car park
     */
    public int getTotalCars() {
        return totalCars;
    }

}