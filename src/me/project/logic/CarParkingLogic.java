package me.project.logic;

import java.util.Random;

import me.project.model.AbstractModel;
import me.project.model.AdHocCar;
import me.project.model.Car;
import me.project.model.CarQueue;
import me.project.model.Location;
import me.project.model.ParkingPassCar;


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
    private static CarQueue paymentCarQueue;
    private static CarQueue membersCarQueue;
    private static CarQueue exitCarQueue;
    private Car[][][] cars;
    
    // amount of pass holders allowed in the car park
    int amountOfPassHolders;
    
    private int day = 0;
    private int hour = 0;
    private int minute = 0;

    int weekDayArrivals= 50; // average number of arriving cars per hour
    int weekendArrivals = 90; // average number of arriving cars per hour

    int enterSpeed;
    int paymentSpeed;
    int exitSpeed; 
    
    // Keeping track of statistics
    int numberOfEnteringCars; // how many cars are in entranceCarQueue
    int numberOfPayingCars; // how many cars are in paymentCarQueue
    int numberOfExitingCars; // how many cars are in exitCarQueue
    int numberOfMembersExiting; // how many cars are in the membersCarQueue
    int totalCars; // amount of no member cars in car park 
    int totalPassHolders; // amount of members in car park
    int total; // amount of all cars in car park
    int totalSpace;
    

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
        
        enterSpeed = 3; // number of cars that can enter per minute
        paymentSpeed = 10; // number of cars that can pay per minute
        exitSpeed = 9; // number of cars that can leave per minute
        totalSpace = numberOfPlaces * numberOfRows * numberOfFloors;
        // amount of cars that have membership at the car park
        amountOfPassHolders = 150;
        
        // counting different cars to be able to visualize this data
        numberOfEnteringCars = 0;
        numberOfPayingCars = 0;
        numberOfExitingCars = 0;
        numberOfMembersExiting = 0;
        totalCars = 0;
        totalPassHolders = 0;
       

        cars = new Car[numberOfFloors][numberOfRows][numberOfPlaces];
        
    }
    
    //week 2 a random number
    /**
     * This method creates a random number, where min and max are the boundries between which the
     * random number should lie.
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
     * Get queue of all the cars that need to pay at the car park.
     *
     * @return cars Return the queue with all cars that need to pay
     */
    public CarQueue getPaymentCarQueue() {
        return paymentCarQueue;
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
     * @return paymentSpeed The speed with which car owners currently are paying per minute.
     */
	public int getPaySpeed() {
		return paymentSpeed;
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
        // Advance the time by one minute.
        minute++;
        while (minute > 59) {
            minute -= 60;
            hour++;
        }
        while (hour > 23) {
            hour -= 24;
            day++;
        }
        while (day > 6) {
            day -= 7;
        }

        Random random = new Random();

        int averageNumberOfCarsPerHour = day < 5
                ? weekDayArrivals
                : weekendArrivals;

        double standardDeviation = averageNumberOfCarsPerHour * 0.1;
        double numberOfCarsPerHour = averageNumberOfCarsPerHour + random.nextGaussian() * standardDeviation;
        int numberOfCarsPerMinute = (int)Math.round(numberOfCarsPerHour / 60);
 
        double numberOfMembersPerHour = (amountOfPassHolders / 6) + random.nextGaussian() * standardDeviation;
        int numberOfMembersPerMinute = (int)Math.round(numberOfMembersPerHour / 60);
        int numberTotalCarsPerMinute = numberOfCarsPerMinute + numberOfMembersPerMinute;

        for (int j = 0; j < numberTotalCarsPerMinute; j++) { 
            for (int i = 0; i < numberOfCarsPerMinute; i++) {
                Car car = new AdHocCar();
                numberOfEnteringCars++;
                totalCars++;
                entranceCarQueue.addCar(car);
            }
            
            for (int i = 0; i < numberOfMembersPerMinute ; i++) {
                Car car = new ParkingPassCar();
                numberOfEnteringCars++;
                totalPassHolders++;
                entranceCarQueue.addCar(car);
            }

            super.notifyViews();
        }

        for (int i = 0; i < enterSpeed; i++) {
            Car car = entranceCarQueue.removeCar();

            if (car == null) {
                break;
            } else {
            	numberOfEnteringCars--;
            }

            Location freeLocation = this.getFirstFreeLocation();
            if (freeLocation != null) {
                this.setCarAt(freeLocation, car);
                if(car instanceof AdHocCar || car instanceof ParkingPassCar) {
                		car.setMinutesLeft(car.getStayTime());
              }
            }
            super.notifyViews();
        }

        this.tickCars();

        while (true) {
            Car car = this.getFirstLeavingCar();
            if (car == null) {
                break;
            }
            
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

        for (int i = 0; i < paymentSpeed; i++) {
            Car car = paymentCarQueue.removeCar();
            if (car == null) {
                break;
            } else {
                numberOfPayingCars--;
            }

            this.removeCarAt(car.getLocation());
            exitCarQueue.addCar(car);
            numberOfExitingCars++;
            totalCars--;
            super.notifyViews(); 
        }
        
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

        for (int i = 0; i < exitSpeed; i++) {
            Car car = membersCarQueue.removeCar();
            if (car == null) {
                break;
            } else {
            	numberOfMembersExiting--;
            }
            super.notifyViews(); 

        }
        
        total = totalCars + totalPassHolders;
        // Update the car park view.
        super.notifyViews();
        System.out.println("Regular Cars: " + totalCars);
        System.out.println("ParkingPass Cars: " + totalPassHolders);
        System.out.println("Total Cars: " + total + "/" + totalSpace);
        
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
     * Set steps in the simulator
     *
     * @param steps Amount of steps we should do
     */
    public void setSteps(int steps) {
        for(int i = 0; i < steps; i++) {
            tick();
        }
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
     * @return numberOfExitingCars
     */
    public int getNumberOfExitingMembers() {
        return numberOfMembersExiting;
    }
    
    /**
     * @return totalCars Return total number of no member cars in car park
     */
    public int getTotalCars() {
        return totalCars;
    }

    /**
     * @return totalCars Return total number of members in car park
     */
    public int getTotalPassHolders() {
        return totalPassHolders;
    }
    
    /**
     * @return amountOfPassHolders Amount of pass holders allowed in the car park
     */
    public int getNumberOfPassHolders(){
    	return amountOfPassHolders;
    }
    
    /**
     * Change the amount of pass holders allowed in the car park.
     * @param newNumber The new amount to set
     */
    public void setNumberOfPassHolders(int newNumber){
    	amountOfPassHolders = newNumber;
    }
}

