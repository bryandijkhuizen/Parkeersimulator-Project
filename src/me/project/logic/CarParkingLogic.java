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
    private static int numberOfFloors, numberOfRows, numberOfPlaces;
    private static CarQueue entranceCarQueue, paymentCarQueue, membersCarQueue, exitCarQueue ;
    private Car[][][] cars;
    
    private int amountOfPassHolders;
    
    private int day = 0;
    private int hour = 0;
    private int minute = 0;

    private int weekDayArrivals= 90; 
    private int weekendArrivals = 50; 

    private int enterSpeed;
    private int paymentSpeed;
    private int exitSpeed; 
    
    private int numberOfEnteringCars; 
    private int numberOfPayingCars; 
    private int numberOfExitingCars; 
    private int numberOfMembersExiting;
    
    private int totalRegularCars, totalPassHolders, totalCars; 
    
    private int totalSpace = 0;
    
    /**
     * Constructor of the CarParkingLogic. 
     * @param numberOfFloors The number of floors of the car park
     * @param numberOfRows   The number of rows per floor of the car park
     * @param numberOfPlaces The number of parking spots per row of the car park
     */
    @SuppressWarnings("static-access")
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
        amountOfPassHolders = 150;

        numberOfEnteringCars = 0;
        numberOfPayingCars = 0;
        numberOfExitingCars = 0;
        numberOfMembersExiting = 0;
        totalRegularCars = 0;
        totalPassHolders = 0;
       

        cars = new Car[numberOfFloors][numberOfRows][numberOfPlaces];
        
    }

    /**
     * This method creates a random number.
     * @param min
     * @param max
     * @return returns a random number 
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
     * @return numberOfPlaces 
     */
    
    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    /**
     * Get the cars array
     *
     * @return cars 
     */
    
    public Car[][][] getCars() {
        return cars;
    }

    /**
     * Get queue of all cars
     *
     * @return cars 
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
     * @return enterSpeed
     */
    
	public int getEnterSpeed() {
		return enterSpeed;
	}
	
    /**
     * @return paymentSpeed
     */
	
	public int getPaySpeed() {
		return paymentSpeed;
	}
	
    /**
     * @return exitSpeed 
     */
	
	public int getExitSpeed(){
		return exitSpeed;
	}

    /**
     * Executes one simulation step, it advances the time by one minute.
     */
	
    public void tick() {
    	
    	/**
         * The time will be advanced.
         */
    	
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
        
        /**
         * A random object gets defined here.
         */

        Random random = new Random();
        
        /**
         * The average numbers are calculated here.
         */

        int averageNumberOfCarsPerHour = day < 5 ? weekDayArrivals : weekendArrivals;
        
        double standardDeviation = averageNumberOfCarsPerHour * 0.1;
        double numberOfCarsPerHour = averageNumberOfCarsPerHour + random.nextGaussian() * standardDeviation;
        int numberOfCarsPerMinute = (int)Math.round(numberOfCarsPerHour / 60);
 
        double numberOfParkingPassHoldersPerHour = (amountOfPassHolders / 6) + random.nextGaussian() * standardDeviation;
        int numberOfParkingPassHoldersPerMinute = (int)Math.round(numberOfParkingPassHoldersPerHour / 60);
        int numberTotalCarsPerMinute = numberOfCarsPerMinute + numberOfParkingPassHoldersPerMinute;
        
        /**
         * Here will the Car Park be simulated.
         */

        
        /**
		 * The cars will enter until 
	 	 * the maximum amount of cars per minute was reached
	 	 */
        
        for (int j = 0; j < numberTotalCarsPerMinute; j++) { 	
        	
        		/** 
				 * As long as the maximum of regular cars entering the 
				 * parking hasn't been reached regular cars will enter
				 */
        	
            for (int i = 0; i < numberOfCarsPerMinute; i++) {	
                Car car = new AdHocCar();
                numberOfEnteringCars++;
                totalRegularCars++;
                entranceCarQueue.addCar(car);
            
          }
            	/** 
            	 * As long as the maximum of ParkingPassHolders cars entering the 
            	 * parking hasn't been reached regular cars will enter
            	 */
            
            for (int i = 0; i < numberOfParkingPassHoldersPerMinute ; i++) {
                Car car = new ParkingPassCar();
                numberOfEnteringCars++;
                totalPassHolders++;
                entranceCarQueue.addCar(car);
            
          }
            super.notifyViews(); //updates the CarParkView
        }
        
        /*
         * For as long as the maximum enterSpeed hasn't been reached
         * The Car will be removed from the EntranceQueue and the NumberOfEnteringCars will be decreased 
         * by 1.
         */

        for (int i = 0; i < enterSpeed; i++) {
        	while(totalCars < totalSpace) {
        		Car car = entranceCarQueue.removeCar();
                numberOfEnteringCars--;

            Location freeLocation = this.getFirstFreeLocation();
            if (freeLocation != null) {
                this.setCarAt(freeLocation, car);
                if(car instanceof AdHocCar || car instanceof ParkingPassCar) {
                		car.setMinutesLeft(car.getStayTime());
              }
            }
            super.notifyViews();
        }
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
            totalRegularCars--;
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
        
        totalCars = totalRegularCars + totalPassHolders;
        super.notifyViews();

        
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
     * @param location 
     * @return car 
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
     * Check if a specific location is empty in the car park.
     *
     * @param location 
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
     * @return car | null
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
     * @return car | null 
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
     * @param steps 
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
     * @return totalCars 
     */
    
    public int getTotalCars() {
        return totalRegularCars;
    }

    /**
     * @return totalCars 
     */
    
    public int getTotalPassHolders() {
        return totalPassHolders;
    }
    
    /**
     * @return amountOfPassHolders
     */
    
    public int getNumberOfPassHolders(){
    	return amountOfPassHolders;
    }
    
    public int getCarsInEntranceQueue() {
    	return entranceCarQueue.carsInQueue();
    }
    
    public void quit() {
    	System.exit(0);
    }
    
    public void printCarParkingDetails() {
        System.out.println("Regular Cars: " + totalRegularCars);
        System.out.println("ParkingPass Cars: " + totalPassHolders);
        System.out.println("Total Cars: " + totalCars + "/" + totalSpace);
    }
}