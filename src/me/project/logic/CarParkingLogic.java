package me.project.logic;

import java.util.Random;
import me.project.model.AbstractModel;
import me.project.model.AdHocCar;
import me.project.model.Car;
import me.project.model.CarQueue;
import me.project.model.Location;
import me.project.model.ParkingPassCar;
import me.project.view.SimulatorView;

/**
 * Class for the "Main" model of Simulator
 * @author Bryan Dijkhuizen, Daphne Gritter, Kevin Wu, Thalisa Jagt
 *
 */

public class CarParkingLogic extends AbstractModel {
	
	private static final String AD_HOC = "1";
	private static final String PASS = "2";
	
	private static CarQueue entranceCarQueue, entrancePassQueue, paymentCarQueue, exitCarQueue;
    private static SimulatorView simulatorView;
    
    private static int day = 0;
    private static int hour = 0;
    private static int minute = 0;
   
  
    private static int totalAdHocCars = 0;
    private static int totalPassCars = 0;
    private static int totalCars = 0;

    private static int tickPause = 100;
    
    private static int numberOfCars;
    
    private static boolean run = true;

    static int weekDayArrivals= 100; // average number of arriving cars per hour
    static int weekendArrivals = 200; // average number of arriving cars per hour
    static int weekDayPassArrivals= 50; // average number of arriving cars per hour
    static int weekendPassArrivals = 5; // average number of arriving cars per hour

    static int enterSpeed = 3; // number of cars that can enter per minute
    static int paymentSpeed = 7; // number of cars that can pay per minute
    static int exitSpeed = 5; // number of cars that can leave per minute
    
    /**
	 * Simulator Constructor, creates entranceCarQueue, entrancePassQueue, paymentCarQueue, exitCarQueue and a SimulatorView
	 * @param numberOfFloors
	 * @param numberOfRows
	 * @param numberOfPlaces
	 */
    
    public CarParkingLogic() {
        entranceCarQueue = new CarQueue();
        entrancePassQueue = new CarQueue();
        paymentCarQueue = new CarQueue();
        exitCarQueue = new CarQueue();
        simulatorView = new SimulatorView(3, 6, 30);
        
    }
    
    /**
   	 * Stops the simulation but doesn't quit.
   	 */
    
    public static void Stop(){
    	run = false;
    }
    
    /**
   	 * Continues the simulation.
   	 */
    
    public static void Continue() {
    	run = true;
    	buttonTick(10000);
    }
    
    /**
	 * Runs the simulation a couple of times, dependent on the number you enter.
	 * @param getal
	 */
    
    public static void buttonTick(int getal) {
    	int i = getal;
    	while(i > 0 && run == true){
    		tick();
    		i--; }
    	}
    
    /**
	 * Forwards the simulation by advanceTime();, cars exit by handleExit(); 
	 * and updates view with updateViews();
	 */
    
    public static void tick() {
    	advanceTime();
    	handleExit();
    	updateViews();
    	
  
        try {
            Thread.sleep(tickPause);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    	handleEntrance();
    }

    private static void advanceTime(){
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

    }

    private static void handleEntrance(){
    	carsArriving();
    	carsEntering(entrancePassQueue);
    	carsEntering(entranceCarQueue);  	
    }
    
    private static void handleExit(){
        carsReadyToLeave();
        carsLeaving();
    }
    
    private static void updateViews(){
    	simulatorView.tick();
        simulatorView.updateView();	
    }
    
    public static void carsArriving(){
    	numberOfCars=getNumberOfCars(weekDayArrivals, weekendArrivals);
        addArrivingCars(numberOfCars, AD_HOC);
        
        numberOfCars=getNumberOfCars(weekDayArrivals, weekendArrivals);
        addArrivingCars(numberOfCars, PASS);   
        
    }
    
    private static void carsEntering(CarQueue queue){
        int i=0;

	    	while (queue.carsInQueue()>0 && simulatorView.getNumberOfOpenSpots()>0 && i<enterSpeed) {
	    		Car car = queue.removeCar();
	            	Location freeLocation = simulatorView.getFirstFreeLocation();
	            		simulatorView.setCarAt(freeLocation, car);
	            			i++;
	            			        				
        }
    }
    
    private static void carsReadyToLeave(){
        Car car = simulatorView.getFirstLeavingCar();
        
        while (car!=null) {
        	if (car.getHasToPay()){
	            car.setIsPaying(true);
	            paymentCarQueue.addCar(car);
        	} else {
        		carLeavesSpot(car);
        	}
        	
            car = simulatorView.getFirstLeavingCar();
        }
    }
    
    private static void carsLeaving(){
	    int i=0;
	    	
	    while (exitCarQueue.carsInQueue()>0 && i < exitSpeed){
	    	exitCarQueue.removeCar();
	    	 i++;
	    	 	
	    }	
	  }
    
    public static int getNumberOfCars(int weekDay, int weekend){
        Random random = new Random();

        int averageNumberOfCarsPerHour = day < 5 ? weekDay : weekend;

        double standardDeviation = averageNumberOfCarsPerHour * 0.3;
        double numberOfCarsPerHour = averageNumberOfCarsPerHour + random.nextGaussian() * standardDeviation;
        
        return (int)Math.round(numberOfCarsPerHour / 60);	
    }
    
    private static void addArrivingCars(int numberOfCars, String type){
    	switch(type) {
    	case AD_HOC: 
            for (int i = 0; i < numberOfCars; i++) {
            	entranceCarQueue.addCar(new AdHocCar());
            	totalAdHocCars++;
            	totalCars++;
            }
            break;
    	case PASS:
            for (int i = 0; i < numberOfCars; i++) {
            	entrancePassQueue.addCar(new ParkingPassCar());
            	totalPassCars++;
            	totalCars++;
            }
    	    break;

    	}
    }
    
    private static void carLeavesSpot(Car car){
    	simulatorView.removeCarAt(car.getLocation());
        exitCarQueue.addCar(car);
    }

	public static int getTotalCars() {
		return totalCars;
		
	}
	
	public static int getTotalAdHocCars() {
		return totalAdHocCars;
	}
	
	public static int getTotalPassCars() {
		return totalPassCars;
	}
	
	public static void incrementEnteringSpeed() {
		enterSpeed++;
	}
	
	public static void decrementEnteringSpeed() {
		enterSpeed--;
	}
	
	public static int getEnterSpeed() {
		return enterSpeed;
	}
	
	public static void incrementWeekDayArrivals() {
		weekDayArrivals++;
	}
	
	public static void decrementWeekDayArrivals() {
		weekDayArrivals--;
	}
	
	public static int getWeekDayArrivals() {
		return weekDayArrivals;
	}
	
	public static void resetAll() {
		weekDayArrivals = 100;
		enterSpeed = 3;
	}
	
	
	
}