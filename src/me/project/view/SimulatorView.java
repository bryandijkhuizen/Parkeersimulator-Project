package me.project.view;

import javax.swing.*;

import java.awt.*;


import me.project.controller.Controller;
import me.project.logic.CarParkingLogic;
import me.project.model.AdHocCar;
import me.project.model.Car;
import me.project.model.Location;
import me.project.model.ParkingPassCar;


/**
 * Class SimulatorView
 * The Class that creates graphical interface for the simulation.
 * @author Bryan Dijkhuizen, Daphne Gritter, Kevin Wu, Thalisa Jagt 
 */

@SuppressWarnings("serial")
public class SimulatorView extends JFrame {
	
    private int numberOfFloors;
    private int numberOfRows;
    private int numberOfPlaces;
    private int numberOfOpenSpots;
    
    private Car[][][] cars;
    private Controller controller;
    private CarParkingLogic CarParkingLogic;
    
    private CarParkView carParkView;
 
  
	/**
	 * The SimulatorView Constructor
	 * @param numberOfFloors
     * @param numberOfRows
     * @param numberOfPlaces
	 */
	
    public SimulatorView(int numberOfFloors, int numberOfRows, int numberOfPlaces) {
        this.numberOfFloors = numberOfFloors;
        this.numberOfRows = numberOfRows;
        this.numberOfPlaces = numberOfPlaces;
        this.numberOfOpenSpots =numberOfFloors*numberOfRows*numberOfPlaces;
        
        cars = new Car[numberOfFloors][numberOfRows][numberOfPlaces];
        controller= new Controller(CarParkingLogic);
        carParkView = new CarParkView();
        
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(1, 1));

        contentPane.add(carParkView);
        contentPane.add(controller);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        pack();
        setVisible(true);
        
        updateView();
    }
    
    public void updateView() {
        carParkView.updateView();
    }
    
	public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }
    
    public int getNumberOfOpenSpots(){
    	return numberOfOpenSpots;
    }
    
    public Car getCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        
        return cars[location.getFloor()][location.getRow()][location.getPlace()];
    }

    public boolean setCarAt(Location location, Car car) {
        if (!locationIsValid(location)) {
            return false;
        }
        
        Car oldCar = getCarAt(location);
        
        if (oldCar == null) {
            cars[location.getFloor()][location.getRow()][location.getPlace()] = car;
            car.setLocation(location);
            numberOfOpenSpots--;
            return true;
        }
        
        return false;
    }
    
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
        numberOfOpenSpots++;
        return car;
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

    public Car getFirstLeavingCar() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = getCarAt(location);
                    if (car != null && car.getMinutesLeft() <= 0 && !car.getIsPaying()) {
                        return car;
                    }
                }
            }
        }
        
        return null;
    }

    public void tick() {
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
     * Creates the view of the Carpark
     *@author Bryan Dijkhuizen, Daphne Gritter, Kevin Wu, Thalisa Jagt
     *
     */
    
    private class CarParkView extends JPanel {
        
        private Dimension size;
        private Image carParkImage;  
        
        
        /*
        * CarParkView Constructor
        */
        
        public CarParkView() {
            size = new Dimension(0, 0);
        }
    
        /**
         * Overridden. Tell the GUI manager how big we would like to be.
         */
        
        public Dimension getPreferredSize() {
            return new Dimension(800, 500);
        }
    
        /**
         * Overriden. The car park view component needs to be redisplayed. Copy the
         * internal image to screen.
         */
        
        public void paintComponent(Graphics g) {
            if (carParkImage == null) {
                return;
            }
    
            Dimension currentSize = getSize();
            if (size.equals(currentSize)) {
                g.drawImage(carParkImage, 0, 0, null);
            }
            else {
                
                g.drawImage(carParkImage, 0, 0, currentSize.width, currentSize.height, null);
            }
        }
    
        public void updateView() {        	
            if (!size.equals(getSize())) {
                size = getSize();
                carParkImage = createImage(size.width, size.height);
            }
            
            Graphics graphics = carParkImage.getGraphics();
            for(int floor = 0; floor < getNumberOfFloors(); floor++) {
                for(int row = 0; row < getNumberOfRows(); row++) {
                    for(int place = 0; place < getNumberOfPlaces(); place++) {
                        Location location = new Location(floor, row, place);
                        Car car = getCarAt(location);
	                        if (car == null) {
	                        	Color color = Color.white;
	                        		drawPlace(graphics, location, color);  
	                        
	                        } else if (car != null && car.getClass().equals(AdHocCar.class)){      
	                        	Color color2 = car.getColor();
	                        		drawPlace(graphics, location, color2);
	                        } else if (car != null && car.getClass().equals(ParkingPassCar.class)){
	                        	Color color3 = car.getColor();
	                        		drawPlace(graphics, location, color3);
	                        }
                    	}
                };
            }
            
            repaint();
            
        }
    
        /**
         * Paint a place on this car park view in a given color.
         */
        
        private void drawPlace(Graphics graphics, Location location, Color color) {
            graphics.setColor(color);
            graphics.fillRect(
                    location.getFloor() * 260 + (1 + (int)Math.floor(location.getRow() * 0.5)) * 75 + (location.getRow() % 2) * 20,
                    60 + location.getPlace() * 10,
                    20 - 1,
                    10 - 1); 
        }

    }
			
}
