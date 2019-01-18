package me.project.logic;

import java.util.Random;

import me.project.abstracts.AbstractModel;
import me.project.abstracts.Car;
import me.project.model.AdHocCar;
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
    private static CarQueue entranceCarQueue /*VOOR ALLE AUTOS */,  paymentCarQueue /*VOOR NORMALE AUTO'S */, membersCarQueue /*UITGANG VOOR MEMBERS */, exitCarQueue /*UITGANG VOOR NORMALE AUTO'S */, secondEntranceCarQueue /*INGANG VOOR MEMBERS EN RES */ ;
    private Car[][][] cars;
    
    private int amountOfPassHolders;
    
    private int day = 0;
    private int hour = 0;
    private int minute = 0;

    private int weekDayArrivals= 50; 
    private int weekendArrivals = 90; 

    private int enterSpeed;
    private int paymentSpeed;
    private int exitSpeed; 
    
    private int numberOfEnteringCars; 
    private int numberOfPayingCars; 
    private int numberOfExitingCars; 
    private int numberOfMembersExiting;

    private int totalRegularCars, totalPassHolders, totalCars; 
    private int totalSpace;
    
    private String currentDay;
    
    /**
     * CarParkingLogic Constructor 
     * @param numberOfFloors 
     * @param numberOfRows   
     * @param numberOfPlaces 
     */
    
    @SuppressWarnings("static-access")
	public CarParkingLogic(int numberOfFloors, int numberOfRows, int numberOfPlaces) {
        this.numberOfFloors = numberOfFloors;
        this.numberOfRows = numberOfRows;
        this.numberOfPlaces = numberOfPlaces;
        
        cars = new Car[numberOfFloors][numberOfRows][numberOfPlaces];
        
        entranceCarQueue = new CarQueue();
        secondEntranceCarQueue = new CarQueue();
        paymentCarQueue = new CarQueue();
        membersCarQueue = new CarQueue();
        exitCarQueue = new CarQueue();
        
        enterSpeed = 3; 
        paymentSpeed = 10; 
        exitSpeed = 9; 
        totalSpace = numberOfPlaces * numberOfRows * numberOfFloors; 
        amountOfPassHolders = 108;

        numberOfEnteringCars = 0;
        numberOfPayingCars = 0;
        numberOfExitingCars = 0;
        numberOfMembersExiting = 0;
        
        totalRegularCars = 0;
        totalPassHolders = 0;
        totalCars = 0;
        
        currentDay = "Monday";
    }
    

    /**
     * This method creates a random number.
     * @param min
     * @param max
     * @return returns a random integer 
     */
    
    
    public int getRandInt(int max, int min){
    	
    	Random rand;
    	rand = new Random();
    	
    	int randNum = rand.nextInt(max - min) + min; 
    	
    	return randNum;
    }
    
    /**
     * Get number of floors of the car park.
     * @return numberOfFloors 
     */
    
    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    
    /**
     * Get number of rows at the car park.
     * @return numberOfRows
     */
    
    
    public int getNumberOfRows() {
        return numberOfRows;
    }

    
    /**
     * Get number of parking spots in the car park.
     * @return numberOfPlaces 
     */
    
    
    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    
    /**
     * Get the cars array
     * @return cars 
     */
    
    
    public Car[][][] getCars() {
        return cars;
    }

    
    /**
     * Get queue of all cars that are entering
     * @return cars 
     */
    
    
    public CarQueue getEntranceCarQueue() {
        return entranceCarQueue;
    }
    
    /**
     * Get the second queue of all cars that are entering
     * @return cars of second queue
     */
    
    public CarQueue getSecondEntranceCarQueue() {
    	return secondEntranceCarQueue;
    }
    
    /**
     * Get queue of the cars that are paying
     * @return cars in the payment car queue
     */
    
    
    public CarQueue getPaymentCarQueue() {
        return paymentCarQueue;
    }

    /**
     * Get queue of all the cars are exiting the car park.
     * @return cars in the exiting car queue
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
    
    /**
     * @return carsInQueue
     */
    
    
    public int getCarsInEntranceQueue() {
    	return entranceCarQueue.carsInQueue();
    }
    /**
     * @return carsInSecondQueue
     */
    
    public int getCarsInSecondEntranceQueue() {
    	return secondEntranceCarQueue.carsInQueue();
    }
	
    
	/**
     * Simulates one step, it advances the time by one minute.
     */
	
	
    public void tick() {
    	
    	/*
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
         * BEGIN VAN DE SWITCH VOOR HET TELLEN VAN DAGEN
         * DEFINIEËR EEN STRING: 'CURRENTDAY'
         * INITIALISEER DEZE MET CURRENTDAY = 'MONDAY'
         * VERVOLGENS MAAK JE EEN SWITCH OVER DAY
         * 
         * 
         * 
         * 
         * switch (day){
         * 	case 0: currentDay = "Monday";
         * 	break;
         * 	case 1: currentDay etc.....
         * }
         * 
         */
        
        switch (day) {
        case 0: 
        	currentDay = "Monday";
        	break;
        case 1:
        	currentDay = "Tuesday";
        	break;
        case 2:
        	currentDay = "Wednesday";
        	break;
        case 3:
        	currentDay = "Thursday";
        	break;
        case 4: 
        	currentDay = "Friday";
        	break;
        case 5:
        	currentDay = "Saturday";
        	break;
        case 6:
        	currentDay = "Sunday";
        	break;
        
       
        }
        
        /*
         * A random object gets defined here.
         */

        Random random = new Random();
        
        /*
         * The average numbers are calculated here.
         */

        int averageNumberOfCarsPerHour = day < 5 ? weekDayArrivals : weekendArrivals;
        
        double standardDeviation = averageNumberOfCarsPerHour * 0.3;
        double numberOfRegularCarsPerHour = averageNumberOfCarsPerHour + random.nextGaussian() * standardDeviation;
        int numberOfRegularCarsPerMinute = (int)Math.round(numberOfRegularCarsPerHour / 60);
 
        double numberOfParkingPassHoldersPerHour = (amountOfPassHolders / 20) + random.nextGaussian() * standardDeviation;
        int numberOfParkingPassHoldersPerMinute = (int)Math.round(numberOfParkingPassHoldersPerHour / 60);
        int numberTotalCarsPerMinute = numberOfRegularCarsPerMinute + numberOfParkingPassHoldersPerMinute;
        
        
        
        /**
         * Here will the Car Park be simulated.
         */

        
        /*
		 * The cars will enter until 
	 	 * the maximum amount of cars per minute was reached
	 	 */
        
        for (int j = 0; j < numberTotalCarsPerMinute; j++) { 	
        	
        		/*
				 * As long as the maximum of regular cars entering the 
				 * parking hasn't been reached regular cars will enter
				 */
        	
            for (int i = 0; i < numberOfRegularCarsPerMinute; i++) {	
                Car car = new AdHocCar();
                numberOfEnteringCars++;
                entranceCarQueue.addCar(car);
            
          }
            	/*
            	 * As long as the maximum of ParkingPassHolders cars entering the 
            	 * parking hasn't been reached regular cars will enter
            	 */
            
            for (int i = 0; i < numberOfParkingPassHoldersPerMinute ; i++) {
                Car car = new ParkingPassCar();
                numberOfEnteringCars++;
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
        	if(totalCars < totalSpace) { //car can't enter if the car park is full
        		Car car = entranceCarQueue.removeCar(); //car removed from entranceQueue
                numberOfEnteringCars--; 
                
                if(car == null) { //if car doesn't exist; stop
                	break;
                }else {
                	
                if(getFirstFreeLocation() != null) { //firstFreeLocation has to exist
                	this.setCarAt(getFirstFreeLocation(), car); //car gets put in firstFreeLocation
                
                }else {
                	break;
                }
                if(car instanceof AdHocCar) {
                	totalRegularCars++; //if the car is a regular car that amount will be increased by 1
                }else if (car instanceof ParkingPassCar) {
                	totalPassHolders++; //if the car is a parking pass car that amount will be increased by 1
                }
                
                if(car instanceof AdHocCar || car instanceof ParkingPassCar) {
                		car.setMinutesLeft(car.getStayTime()); //sets the minutes left for the car
                }
            }
       }
            super.notifyViews();
        }
        	
     
   
        this.tickCars();
        
        /*
         * Gets the firstLeavingCar. If it doesn't exist then break
         * 
         * If the car is a Regular Car, the car will be added to the paymentQueue
         * 
         * if the car is a parkingpasscar the car will be added to the membersExitingQueue and will be 
         * removed from it's current location
         * 
         */

        
        while (true) {
           Car car = this.getFirstLeavingCar();
           
            
            if (car == null) {
                break;
            }

            if(car instanceof AdHocCar && car.getMinutesLeft() <= 0){
            	numberOfPayingCars++;
            	paymentCarQueue.addCar(car); // Car gets added to the payment Queue
            	break;
	
            } else if(car instanceof ParkingPassCar && car.getMinutesLeft() <= 0) { 
                numberOfMembersExiting++;
            	membersCarQueue.addCar(car); // Car gets added to the membersCarQueue to leave
                this.removeCarAt(car.getLocation()); //Car gets removed from it's location
                break;
            }
            
            super.notifyViews();
        }
     
        
        /*
         * 
         * Here will the payments be done.
         * This will happen until the maximum amount of 'payers' has been reached
         * 
         */

        for (int i = 0; i < paymentSpeed; i++) {
            Car car = paymentCarQueue.removeCar();  //car will be removed from the payment Queue
            if (car == null) { //car has to exist else there will be a break
                break;
            } else {
                numberOfPayingCars--;
            }

            this.removeCarAt(car.getLocation()); //car gets removed from it's location
            exitCarQueue.addCar(car); //car will be added to the exiting car queue
            numberOfExitingCars++;
            super.notifyViews(); //view gets updated
        }
        
        /**
         * Here the members/parkingpass holders will be entering in their entrance
         * until the maximum amount of cars has been reached
         */
        
        for (int i = 0; i < enterSpeed; i++) {
        	
        }
        /*
         * Here the regular cars will be leaving
         * until the maximum amount of cars has been reached
         */
        
        for (int i = 0; i < exitSpeed; i++) {
            Car car = exitCarQueue.removeCar(); //car gets removed from exiting car queue
            if (car == null) { //car has to exist else there will be a break
                break;
            } else {
                numberOfExitingCars--;	 //exiting car queue will me decreased by 1
                totalRegularCars--;	     //total regular car count will be decreased by 1
            }
            super.notifyViews();
 
        }
        
        /*
         * Here the members/Parkingpassholder will be leaving
         * Until the maximum amount of cars has been reached
         */
        

        for (int i = 0; i < exitSpeed; i++) {
            Car car = membersCarQueue.removeCar(); //car gets removed from the members exiting queue
            if (car == null) { //car has to exist else there will be a break
                break;
            } else {
            	numberOfMembersExiting--; //exiting car queue will be decreased by 1
            	totalPassHolders--; //total passholder count will be decreased by 1
            }
            super.notifyViews();  //view gets updated

        }
        
        totalCars = totalRegularCars + totalPassHolders; //total cars calculation
        super.notifyViews(); //view gets updated

        
    }
    
    /**
	 * This method simulates 'x' times
	 */
    
	
	public void steps(int x) {
		for(int i = 0; i < x; i++) {
			tick();
		}
	}
    
    /**
     * This method finds the first free location in the car park and returns it.
     * @return location
     */
    
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
     * Ticks for every car at the car park
     */
    
    public void tickCars() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = getCarAt(location);
                    if (car != null) {
                        car.tick();
                    }
                }
            }
        }
    }

    /**
     * Get a car from a entered location at the car park.
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
     * Put a car in at the entered location
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
     * Check if the entered location is empty at the car park.
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
     * Gets the first leaving car at the car park.
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
     * Removes a car from the entered location.
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
     * @return Quits the program
     */
    
    public void quit() {
    	System.exit(0);
    }
    
    /**
     * Prints out the statistics of the car park
     */
    
    public void printCarParkingDetails() {
        System.out.println("Regular Cars: " + totalRegularCars);
        System.out.println("ParkingPass Cars: " + totalPassHolders);
        System.out.println("Total Cars: " + totalCars + "/" + totalSpace);
    }
}