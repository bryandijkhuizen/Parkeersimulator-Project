package me.project.logic;

import java.util.Random;

import me.project.abstracts.AbstractModel;
import me.project.abstracts.Car;
import me.project.model.AdHocCar;
import me.project.model.CarQueue;
import me.project.model.ElectricalCar;
import me.project.model.Location;
import me.project.model.ParkingPassCar;
import me.project.model.ReservationCar;

	/**
	 * This class contains all the logic for the simulator
	 * @author Bryan Dijkhuizen, Daphne Gritter, Kevin Wu, Thalisa Jagt
	 *
	 */

public class CarParkingLogic extends AbstractModel {
    private static int numberOfFloors, numberOfRows, numberOfPlaces;
    private static CarQueue entranceCarQueue,  paymentCarQueue, membersCarQueue, exitCarQueue, secondEntranceCarQueue, passHolderQueue, reservationQueue ;

    private Car[][][] cars;
    
    private int amountOfPassHolders;
    private int amountOfReservations;
    private int amountOfElectricals;
    private int amountOfRegularCars;
    
    private int month = 0;
    private int week = 0;
    private int day = 0;
    private int hour = 0;
    private int minute = 0;

    private int weekDayArrivals;
    private int weekendArrivals;


    
    private int enterSpeed;
    private int paymentSpeed;
    private int exitSpeed; 
    
    private int numberOfEnteringCars; 
    private int numberOfPayingCars; 
    private int numberOfExitingCars; 
    private int numberOfMembersExiting;
    private int numberOfReservationsExiting;

    private int totalRegularCarsInPark, totalPassHoldersInPark, totalCars, totalReservationsInPark, totalElectricalsInPark; 
    private int totalSpace;
    
    private String currentDay;
    private String currentTime;
    
    private double pricePerMinute = 0.16;
    private double memberRevenue = 0;
    private double totalRevenue = 0 + memberRevenue;

    
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
        passHolderQueue = new CarQueue();
        reservationQueue = new CarQueue();
        
        enterSpeed = 3; 
        paymentSpeed = 10; 
        exitSpeed = 9; 
        totalSpace = numberOfPlaces * numberOfRows * numberOfFloors; 
        

        numberOfEnteringCars = 0;
        numberOfPayingCars = 0;
        numberOfExitingCars = 0;
        numberOfMembersExiting = 0;
        
        
        totalRegularCarsInPark = 0;
        totalPassHoldersInPark = 0;
        totalElectricalsInPark = 0;
        totalCars = 0;
        
        amountOfRegularCars = 360;
        amountOfPassHolders = 90;
        amountOfReservations = 60;
        amountOfElectricals = 30;

        
        currentDay = "Monday";
        
        currentTime = hour + ":" + minute;
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
    
    public int getTotalRegularCars() {
        return totalRegularCarsInPark;
    }

   
    /**
     * @return totalCars 
     */
    
    public int getTotalPassHolders() {
        return totalPassHoldersInPark;
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
     * Gets the current day
     * @return currentDay
     */
    
    public String getCurrentDay() {
    	return currentDay;
    }
    
    /**
     * @return passHolderQueue
     */
    
    public CarQueue getPassHoldersQueue() {
    	return passHolderQueue;
    }
    
    /**
     * @return currenTime
     */
    
    public String getCurrentTime() {
    	return currentTime;
    }
	
	/**
     * Simulates one step, it advances the time by one minute.
     */
	
    public int getAmountOfReservations() {
		return amountOfReservations;
	}
    
    /**
     * @param amountOfReservations
     */


	public void setAmountOfReservations(int amountOfReservations) {
		this.amountOfReservations = amountOfReservations;
	}
	
	/**
	 * @return numberOfReservationsExiting
	 */
	
	public int getNumberOfReservationsExiting() {
		return numberOfReservationsExiting;
	}

	/**
	 * 
	 * @param numberOfReservationsExiting
	 */

	public void setNumberOfReservationsExiting(int numberOfReservationsExiting) {
		this.numberOfReservationsExiting = numberOfReservationsExiting;
	}
	
	/**
	 * @return totalReservationsInPark
	 */
	
	public int getTotalReservations() {
		return totalReservationsInPark;
	}
	
	/** 
	 * @return totalCars
	 */
	
	public int getTotalCars() {
		return totalCars;
	}
	
	/**
	 * @return totalRevenu
	 */
	
	public int getTotalRevenue() {
		return (int) Math.round(totalRevenue);
	}
	
	/**
	 * @return totalElectricalsInPark
	 */
	
	public int getTotalElectricals() {
		return totalElectricalsInPark;
	}
	
	/**
	 * 
	 * Tick method actually simulates the
	 * carpark
	 * 
	 */
	
	/**
	 * Sets the enterSpeed
	 * @param speed
	 */
	
	public void setEnterSpeed(int enterSpeed) {
		this.enterSpeed = enterSpeed;
	}
	
	/**
	 * Sets the exitSpeed
	 * @param speed
	 */
	
	public void setExitSpeed(int exitSpeed) {
		this.exitSpeed = exitSpeed;
	}
	
	/**
	 * Sets the average amount of people
	 * 
	 * @param amount
	 */
	
	public void setWeekDayArrivals(int weekDayArrivals) {
		this.weekDayArrivals = weekDayArrivals;
	}
	
	/**
	 * 
	 * 
	 * @return weekDayArrivals
	 */
	
	public int getWeekDayArrivals() {
		return weekDayArrivals;
	}
	
	/**
	 * 
	 * @param weekendArrivals
	 */
	
	public int getWeekendArrivals() {
		return weekendArrivals;
	}
	
	/**
	 * 
	 * @param weekendarrivals
	 */
	
	public void setWeekendArrivals(int weekendArrivals) {
		this.weekendArrivals = weekendArrivals;
	}
    public void tick() {
    	
    	/*
    	 * 
    	 * All the regular amount of cars in the 
    	 * carpark
    	 */
    	
    	if(day >=0 && day <=2) {
    		
    	if(hour > 0 && hour < 7) {
        	weekDayArrivals = 20;
        	weekendArrivals = 21;
        } else if(hour > 5 && hour < 7) {
    		weekDayArrivals = 20;
        	weekendArrivals = 30;
    	} else if (hour >= 7 && hour < 9) {
    		weekDayArrivals = 75;
        	weekendArrivals = 100;
    	} else if (hour >= 9 && hour < 17) {
    		weekDayArrivals = 40;
        	weekendArrivals = 80;
    	} else if (hour >= 17 && hour < 21) {
    		weekDayArrivals = 30;
        	weekendArrivals = 40;
    	} else if (hour >= 21 && hour < 23) {
    		weekDayArrivals = 20;
        	weekendArrivals = 21;
    	}
    }
    	
    	/*
    	 * 
    	 * Thursday night will be busy
    	 * 
    	 */
    	
    	if(day == 3) {
    		if(hour > 0 && hour < 7) {
            	weekDayArrivals = 20;
            	weekendArrivals = 21;
            } else if(hour > 5 && hour < 7) {
        		weekDayArrivals = 20;
            	weekendArrivals = 30;
        	} else if (hour >= 7 && hour < 9) {
        		weekDayArrivals = 75;
            	weekendArrivals = 100;
        	} else if (hour >= 9 && hour < 17) {
        		weekDayArrivals = 40;
            	weekendArrivals = 80;
        	} else if (hour >= 17 && hour < 21) {
        		weekDayArrivals = 100;
            	weekendArrivals = 200;
        	} else if (hour >= 21 && hour < 23) {
        		weekDayArrivals = 20;
            	weekendArrivals = 21;
        	}
    	}
    	
    	/*
    	 * Friday & Saturday will be busy
    	 * 
    	 */
    	
    	if(day >= 4 && day <= 5) {
    		if(hour > 0 && hour < 7) {
            	weekDayArrivals = 20;
            	weekendArrivals = 21;
            } else if(hour > 5 && hour < 7) {
        		weekDayArrivals = 20;
            	weekendArrivals = 30;
        	} else if (hour >= 7 && hour < 9) {
        		weekDayArrivals = 75;
            	weekendArrivals = 100;
        	} else if (hour >= 9 && hour < 17) {
        		weekDayArrivals = 60;
            	weekendArrivals = 140;
        	} else if (hour >= 17 && hour < 21) {
        		weekDayArrivals = 150;
            	weekendArrivals = 250;
        	} else if (hour >= 21 && hour < 23) {
        		weekDayArrivals = 40;
            	weekendArrivals = 60;
        	}
    	}
    	
    	/*
    	 * 
    	 * 
    	 * Sunday in the afternoon will be busy
    	 */
    	
    	if(day == 6) {
    		if(hour > 0 && hour < 7) {
            	weekDayArrivals = 20;
            	weekendArrivals = 21;
            } else if(hour > 5 && hour < 7) {
        		weekDayArrivals = 20;
            	weekendArrivals = 30;
        	} else if (hour >= 7 && hour < 9) {
        		weekDayArrivals = 20;
            	weekendArrivals = 30;
        	} else if (hour >= 9 && hour < 17) {
        		weekDayArrivals = 60;
            	weekendArrivals = 140;
        	} else if (hour >= 17 && hour < 21) {
        		weekDayArrivals = 20;
            	weekendArrivals = 30;
        	} else if (hour >= 21 && hour < 23) {
        		weekDayArrivals = 20;
            	weekendArrivals = 21;
        	}
    	}

    	/*
    	 * The time will be advanced here
    	 * 
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
            week++;
        }
        
        while (week > 3) {
        	week -= 4;
        	month++;
        	memberRevenue += 100 * amountOfPassHolders;
        }
        
        while (month > 11) {
        	month -= 12;
        	
        }
        
        /*
         * 
         * these if statments will make sure the time will
         * be displayed
         * 
         */
        
        if(hour < 10) {
        	currentTime = "0"+ hour + ":" + minute;
        }
        
        if (minute < 10) {
        	currentTime = hour + ":" + "0" + minute;
        }else {
        	currentTime = hour + ":" + minute;
        }
        	
        
        /*
         * The Switch case that creates the days
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
        

        int averageNumberOfCarsPerHour = day < 5
                ? weekDayArrivals
                : weekendArrivals;
        
       
        
        double standardDeviation = averageNumberOfCarsPerHour * 0.3;
        double numberOfRegularCarsPerHour = averageNumberOfCarsPerHour + random.nextGaussian() * standardDeviation;
        int numberOfRegularCarsPerMinute = (int)Math.round(numberOfRegularCarsPerHour / 60);
        
        double numberOfReservationsPerHour = (amountOfReservations / 5)  + random.nextGaussian() * standardDeviation;
        int numberOfReservationsPerMinute = (int)Math.round(numberOfReservationsPerHour / 60);
        
        double numberOfElectricalsPerHour = amountOfElectricals + random.nextGaussian() * standardDeviation;
        int numberOfElectricalsPerMinute = (int)Math.round(numberOfElectricalsPerHour / 60);
 
        double numberOfParkingPassHoldersPerHour = (amountOfPassHolders / 20) + random.nextGaussian() * standardDeviation;
        int numberOfParkingPassHoldersPerMinute = (int)Math.round(numberOfParkingPassHoldersPerHour / 60);
        int numberTotalCarsPerMinute = numberOfRegularCarsPerMinute + numberOfParkingPassHoldersPerMinute + numberOfReservationsPerMinute + numberOfElectricalsPerMinute;
        
        
        
        
        
        /**
         * Here will the Car Park be simulated.
         */

        
        /*
		 * The cars will enter until 
	 	 * the maximum amount of cars per minute was reached
	 	 */
        
        for (int j = 0; j < numberTotalCarsPerMinute; j++) { 	
        	
        	/*
        	 * This will remove the cars from the entrance queue
        	 * if the queue gets too long
        	 * 
        	 * 
        	 */
        	
        	for(int i = 0; i < entranceCarQueue.carsInQueue(); i++) {
        		if(entranceCarQueue.carsInQueue() > 25) {
        			@SuppressWarnings("unused")
					Car car = entranceCarQueue.removeCar();
        		}
        	}
        	
        	for(int i = 0; i < secondEntranceCarQueue.carsInQueue(); i++) {
        		if(secondEntranceCarQueue.carsInQueue() > 25) {
        			@SuppressWarnings("unused")
        			Car car = secondEntranceCarQueue.removeCar();
        		}
        	}
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
            	 * parking hasn't been reached ParkingPassHolders cars will enter
            	 */
            

            for (int i = 0; i < numberOfParkingPassHoldersPerMinute; i++) {
            	Car car = new ParkingPassCar();
            	numberOfEnteringCars++;
                secondEntranceCarQueue.addCar(car);
          
            }
            
            /*
             * As long as the maximum of Reservations cars entering the parking hasn't
             * been reached, they will enter
             * 
             */
            
            for (int i = 0; i < numberOfReservationsPerMinute; i++) {
            	Car car = new ReservationCar();
            	numberOfEnteringCars++;
            	secondEntranceCarQueue.addCar(car);
            
         }
            	
            	/*
            	 * As long as the maximum of Electrical cars entering the parking hasn't
            	 * been reached, they will enter
            	 */
            	
            	for(int i = 0; i < numberOfElectricalsPerMinute; i++) {
            		if(totalElectricalsInPark < amountOfElectricals) {
        				Car car = new ElectricalCar();
        				numberOfEnteringCars++;
        				entranceCarQueue.addCar(car);
        			
            		}
        }

            
            super.notifyViews(); //updates the CarParkView
        }
        
        /*
         * For as long as the maximum enterSpeed hasn't been reached
         * The Car will be removed from the EntranceQueue and the NumberOfEnteringCars will be decreased 
         * by 1.
         */

        for(int i = 0; i < enterSpeed; i++){
        	if(totalCars < totalSpace){
        		if(totalRegularCarsInPark < amountOfRegularCars) {
        		Car car = entranceCarQueue.removeCar();
        		numberOfEnteringCars--;
        		
        		if(car == null){
        			break;
        		} else {
        			if(car instanceof AdHocCar && getFirstFreeLocationForRegular() != null){
        				this.setCarAt(getFirstFreeLocationForRegular(), car);
        			} else {
        				break;
        			}
        			
        			if(car instanceof AdHocCar){
        				totalRegularCarsInPark++;
        			}
        			
        			if(car instanceof AdHocCar){
        				car.setMinutesLeft(car.getStayTime());	
        			}
        		}
        		super.notifyViews();
        	}
        	this.tickCars();
        }
     }
        
        for(int i = 0; i < enterSpeed; i++){
        	if(totalCars < totalSpace){
        		if(totalElectricalsInPark < amountOfElectricals) {
        		Car car = entranceCarQueue.removeCar();
        		numberOfEnteringCars--;
        		
        		if(car == null){
        			break;
        		} else {
        			if(car instanceof ElectricalCar && getFirstFreeLocationForElec() != null){
        				this.setCarAt(getFirstFreeLocationForElec(), car);
        			} else {
        				break;
        			}
        			
        			if(car instanceof ElectricalCar){
        				totalElectricalsInPark++;
        			}
        			
        			if(car instanceof ElectricalCar){
        				car.setMinutesLeft(car.getStayTime());	
        			}
        		}
        		super.notifyViews();
        	}
        	this.tickCars();
        }
    }
        for(int i = 0; i < enterSpeed; i++){
        	if(totalCars < totalSpace){
        		if(totalPassHoldersInPark < amountOfPassHolders) {
        		Car car = secondEntranceCarQueue.removeCar();
        		numberOfEnteringCars--;
        		
        		if(car == null){
        			break;
        		} else {
        			if(car instanceof ParkingPassCar && getFirstFreeLocationForPass() != null){
        				this.setCarAt(getFirstFreeLocationForPass(), car);
        			} else {
        				break;
        			}
        			
        			if(car instanceof ParkingPassCar){
        				totalPassHoldersInPark++;
        			}
        			
        			if(car instanceof ParkingPassCar){
        				car.setMinutesLeft(car.getStayTime());	
        			}
        		}
        		super.notifyViews();
        	}
        	this.tickCars();
        }
     }
        for(int i = 0; i < enterSpeed; i++){
        	if(totalCars < totalSpace){
        		if(totalReservationsInPark < amountOfReservations) {
        		Car car = secondEntranceCarQueue.removeCar();
        		numberOfEnteringCars--;
        		
        		if(car == null){
        			break;
        		} else {
        			if(car instanceof ReservationCar && getFirstFreeLocationForRes() != null){
        				this.setCarAt(getFirstFreeLocationForRes(), car);
        			} else {
        				break;
        			}
        			
        			if(car instanceof ReservationCar){
        				totalReservationsInPark++;
        			}
        			
        			if(car instanceof ReservationCar){
        				car.setMinutesLeft(car.getStayTime());	
        			}
        		}
        		super.notifyViews();
        	}
        	this.tickCars();
        }
      }
      
                
        	while(true) {
        		Car car = this.getFirstLeavingCar();
        		
        		if(car == null) {
        			break;
        		}
        
            if(car instanceof AdHocCar && car.getMinutesLeft() <= 0){
            	numberOfPayingCars++;
            	paymentCarQueue.addCar(car); // Car gets added to the payment Queue
            	totalRevenue += car.getStayTime() * pricePerMinute;
            	break;
            	
            } else if (car instanceof ElectricalCar && car.getMinutesLeft() <= 0) {
            	numberOfPayingCars++;
            	paymentCarQueue.addCar(car);
            	totalRevenue += car.getStayTime() * pricePerMinute;
            	break;
	
            } else if(car instanceof ParkingPassCar && car.getMinutesLeft() <= 0) { 
                numberOfMembersExiting++;
            	membersCarQueue.addCar(car); // Car gets added to the membersCarQueue to leave
                this.removeCarAt(car.getLocation()); //Car gets removed from it's location
                break;
            } else if(car instanceof ReservationCar && car.getMinutesLeft() <= 0) {
            	numberOfPayingCars++;
            	setNumberOfReservationsExiting(getNumberOfReservationsExiting() + 1);
            	paymentCarQueue.addCar(car);
            	totalRevenue += car.getStayTime() * pricePerMinute;
            	break;
            
            }
            
            super.notifyViews();
        }
    

    
       
        /*
         * Here will the payments be done.
         * This will happen until the maximum amount of 'payers' has been reached
         * 
         */

        for (int i = 0; i < paymentSpeed; i++) {
            Car car = paymentCarQueue.removeCar();  //car will be removed from the payment Queue
            if (car == null) { //car has to exist else there will be a break
                break;
            } else if(car instanceof AdHocCar){
                numberOfPayingCars--;
                exitCarQueue.addCar(car); //car will be added to the exiting car queue
                numberOfExitingCars++;
            } else if (car instanceof ReservationCar) {
            	numberOfPayingCars--;
            	reservationQueue.addCar(car);
            	setNumberOfReservationsExiting(getNumberOfReservationsExiting() + 1);
            } else if (car instanceof ElectricalCar) {
            	numberOfPayingCars--;
            	exitCarQueue.addCar(car); //car will be added to the exiting car queue
            	numberOfExitingCars++;
            }

            this.removeCarAt(car.getLocation()); //car gets removed from it's location
            
            
            
            super.notifyViews(); //view gets updated
        }
        
        /**
         * Here the members/parkingpass holders will be entering in their entrance
         * until the maximum amount of cars has been reached
         */
        
        for (int i = 0; i < enterSpeed; i++) {
        	
        }
        /*
         * Here the regular & electrical cars will be leaving
         * until the maximum amount of cars has been reached
         */
        
        for (int i = 0; i < exitSpeed; i++) {
            Car car = exitCarQueue.removeCar(); //car gets removed from exiting car queue
            if (car == null) { //car has to exist else there will be a break
                break;
            } else if(car instanceof AdHocCar){
                numberOfExitingCars--;	 //exiting car queue will me decreased by 1
                totalRegularCarsInPark--;	     //total regular car count will be decreased by 1
            } else if(car instanceof ElectricalCar) {
            	numberOfExitingCars--;
            	totalElectricalsInPark--;
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
            	totalPassHoldersInPark--; //total passholder count will be decreased by 1
            }
            super.notifyViews();  //view gets updated

        }
        
        for (int i = 0; i < exitSpeed; i++) {
        	Car car = reservationQueue.removeCar();
        	if(car == null) {
        		break;
        	} else {
        		setNumberOfReservationsExiting(getNumberOfReservationsExiting() - 1);
        		totalReservationsInPark--;
        	}
        }
        
        totalCars = getTotalRegularCars() + getTotalPassHolders() + getTotalReservations() + getTotalElectricals(); //total cars calculation
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
    
    public Location getFirstFreeLocationForRes() {
    	for (int floor = 2; floor < getNumberOfFloors(); floor++) {
    		for (int row = 3; row <= 4 ; row++) {
    			for(int place = 0; place < getNumberOfPlaces(); place++) {
    				Location location = new Location(floor, row, place);
    					if(getCarAt(location) == null) {
    						return location;
    					}
    				}
    			}
    		}
    	
    	return null;
    }
    
    public Location getFirstFreeLocationForPass() {
    	for (int floor = 2; floor < getNumberOfFloors(); floor++) {
    		for (int row = 0; row <= 4; row++) {
    			for(int place = 0; place < getNumberOfPlaces(); place++) {
    				Location location = new Location(floor, row, place);
    					if(getCarAt(location) == null) {
    						return location;
    					}
    				}
    			}
    		}
    	
    	return null;
    }
    
    public Location getFirstFreeLocationForElec() {
    	for (int floor = 2; floor < getNumberOfFloors(); floor++) {
    		for (int row = 5; row <= 5 ; row++) {
    			for(int place = 0; place < getNumberOfPlaces(); place++) {
    				Location location = new Location(floor, row, place);
    					if(getCarAt(location) == null) {
    						return location;
    					}
    				}
    			}
    		}
    	
    	return null;
    }
    
    public Location getFirstFreeLocationForRegular() {
    	for (int floor = 0; floor < 2; floor++) {
    		for (int row = 0; row < getNumberOfRows(); row++) {
    			for(int place = 0; place < getNumberOfPlaces(); place++) {
    				Location location = new Location(floor, row, place);
    					if(getCarAt(location) == null) {
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
        System.out.println("Regular Cars: " + totalRegularCarsInPark);
        System.out.println("ParkingPass Cars: " + totalPassHoldersInPark);
        System.out.println("Total Cars: " + totalCars + "/" + totalSpace);
    }


	


	
}