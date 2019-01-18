package src.me.project.model;

import java.util.LinkedList;
import java.util.Queue;

import me.project.abstracts.Car;

/**
 * Class for the queue's of cars
 * @author Bryan Dijkhuizen, Daphne Gritter, Kevin Wu, Thalisa Jagt
 *
 */

public class CarQueue {
    private Queue<Car> queue = new LinkedList<>();

    
    /**
     * Ads a car to the queue and returns if the car has been added
     * @param car
     * @return boolean
     */
    
    public boolean addCar(Car car) {
        return queue.add(car);
    }

    /**
     * removes a car from the queue
     * @return car
     */
    
    public Car removeCar() {
        return queue.poll();
    }

    /**
     * returns carsInQueue 
     * @return cars queue in size
     */
    
    public int carsInQueue(){
    	return queue.size();
    }
    
    /**
     * return carsInSecondQueue
     * @return cars in second queue in size
     */
    
    public int carsInSecondQueue() {
    	return queue.size
    }
}