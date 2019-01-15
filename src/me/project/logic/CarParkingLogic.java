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
 * This class contains all the logic for the simulator
 * @author Bryan Dijkhuizen, Daphne Gritter, Kevin Wu, Thalisa Jagt
 *
 */

public class CarParkingLogic extends AbstractModel {
	private static int numberOfFloors;
    private static int numberOfRows;
    private static int numberOfPlaces;
    private static CarQueue entranceCarQueue;
    private static CarQueue parkingPassCars;
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
     * Constructor of CarParkingLogic creates instances of the carQueues
     * @param numberOfFloors The number of floors of the car park
     * @param numberOfRows   The number of rows per floor of the car park
     * @param numberOfPlaces The number of parking spots per row of the car park
     */
    public CarParkingLogic(int numberOfFloors, int numberOfRows, int numberOfPlaces) {
        this.numberOfFloors = numberOfFloors;
        this.numberOfRows = numberOfRows;
        this.numberOfPlaces = numberOfPlaces;

        entranceCarQueue = new CarQueue();
        parkingPassCars = new CarQueue();
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
     * @return numberOfFloors
     */
    
    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    /**
     * Get number of rows of the car park.
     *
     * @return numberOfRows
     */
    
    public int getNumberOfRows() {
        return numberOfRows;
    }

    /**
     * Get number of parking spots in the car park.
     *
     * @return numberOfPlaces
     */
    
    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    /**
     * Get the three dimensional array that represents the car park.
     *
     * @return cars 
     */
    public Car[][][] getCars() {
        return cars;
    }

    /**
     * Get queue of all the cars are entering the car park.
     *
     * @return entering cars in queue
     */
    public CarQueue getEntranceCarQueue() {
        return entranceCarQueue;
    }

    /**
     * Get queue of all the cars are exiting the car park.
     *
     * @return exiting cars in queue
     */
    public CarQueue getExitCarQueue() {
        return exitCarQueue;
    }
    
    /**
     * @return enterSpeed 
     */
    
	public int getEnterSpeed() {
		return enterSpeed;
	}
	
    /**
     * @return exitSpeed 
     */
	
	public int getExitSpeed(){
		return exitSpeed;
	}

    /**
     * Executes one simulation step
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
        
        super.notifyViews();
        }
	/**
	 * advanceTime() advances time by one minute
	 */
	
	public void advanceTime() {
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
        averageNumberOfCarsPerHour = day < 5 ? weekDayArrivals : weekendArrivals;
                
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
            // Find a free spot.
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
            
            if(car instanceof AdHocCar){
            	numberOfPayingCars++;
            	break;
            	
            	
            } else if(car instanceof ParkingPassCar) { 
                numberOfMembersExiting++;
                parkingPassCars.addCar(car);
                
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
        }
	}
	
	public void tickMembersLeave() {
		// Let members leave.
        for (int i = 0; i < exitSpeed; i++) {
            Car car = parkingPassCars.removeCar();
            if (car == null) {
                break;
            } else {
            	numberOfMembersExiting--;
            }
            super.notifyViews(); 
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
     * Put a car in a certain parking spot.
     * @param location 
     * @param car 
     * @return boolean 
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
     * Check if a specific location is empty.
     *
     * @param location Location to check
     * @return boolean
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
     * Get the first leaving car in the car park.
     *
     * @return car 
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
     * @param location
     * @return car
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
     * @return numberOfExitingCars
     */
    
    public int getNumberOfExiting() {
        return numberOfExitingCars;
    }
    
    /**
     * @return totalCars
     */
    
    public int getTotalCars() {
        return totalCars;
    }

}