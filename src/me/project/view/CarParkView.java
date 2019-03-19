package me.project.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JLabel;

import me.project.abstracts.AbstractView;
import me.project.abstracts.Car;
import me.project.logic.CarParkingLogic;
import me.project.model.AdHocCar;
import me.project.model.ElectricalCar;
import me.project.model.Location;
import me.project.model.ParkingPassCar;
import me.project.model.ReservationCar;

	/**
	 * This class contains the carParkView
	 * @author Bryan Dijkhuizen, Daphne Gritter, Thalisa Jagt
	 * @version 2.0
	 */

@SuppressWarnings("serial")
public class CarParkView extends AbstractView{

    private JLabel titleLabel;
    private Image carParkImage;
    private Dimension size;

    /**
     * Constructor creates an instance of the CarParkView.
     * @param model
     */
    
    public CarParkView(CarParkingLogic model) {
        super(model);
        
        size = new Dimension(0, 0); 
        
        titleLabel = new JLabel("Car Parking Simulation");
        setBackground(Color.BLACK);

        add(titleLabel);

    }

    
    /**
     * Scaling the carParkImage.
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
            // Rescale the previous image.
            g.drawImage(carParkImage, 0, 0, currentSize.width, currentSize.height, null);
           
        }
    }

    /**
     * Updates the CarParkView screen when changes happened.
     */
    
   
	public void updateView() {

        CarParkingLogic carPark = (CarParkingLogic) super.model;
        
        if (!size.equals(getSize())) {
            size = getSize();
            carParkImage = createImage(size.width, size.height);
        }

        Graphics graphics = carParkImage.getGraphics();

        for (int floor = 0; floor < carPark.getNumberOfFloors(); floor++) {
            for (int row = 0; row < carPark.getNumberOfRows(); row++) {
                for (int place = 0; place < carPark.getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = carPark.getCarAt(location);
                    
                    Color color = Colors.BACKGROUND_BLUE;
                    
                    /*
                     * This will give the specific car spots the right colors
                     */
                    
                    if (car == null) {
                    	if(location.getFloor() == 0 || location.getFloor() == 1) {
                    	color = Colors.PASTEL_RED;
                    	}else if (location.getFloor() == 2 && location.getRow() >= 0 && location.getRow() <= 2 ) {
                    		color = Colors.PASTEL_BLUE;
                    	}else if (location.getFloor() == 2 && location.getRow() >= 2 && location.getRow() <= 4) {
                    		color = Colors.PASTEL_GREEN;
                    	}else if (location.getFloor() == 2 && location.getRow() >= 3 && location.getRow() <= 5) {
                    		color = Colors.PASTEL_YELLOW;
                    	}
                    } 
                    
                    else if(car instanceof ParkingPassCar) {
                    	color = car.getColor();
                    } else if (car instanceof ElectricalCar) {
                    	color = car.getColor();
                    
                    } else if (car instanceof ReservationCar) {
                    	color = car.getColor();
                    } else if (car instanceof AdHocCar) {
                    	color = car.getColor();
                    }
                    drawPlace(graphics, location, color);
                    drawEntrances(graphics, location, color);
                    drawElectricalSpots(graphics, location, color);
                }
            }
        }

        setVisible(true);
        super.updateView();
    }
	
	/**
	 * Will draw the entire simulation screen
	 * @param graphics
	 * @param location
	 * @param color
	 */
    
	public void drawPlace(Graphics graphics, Location location, Color color) {
        graphics.setColor(color);
        graphics.fillRect(
                ((location.getFloor() * 260 + (1 + (int) Math.floor(location.getRow() * 0.5)) * 60 + (location.getRow() % 2) * 20) -59) + 50,
                location.getPlace() * 10 + 30,
                20 - 1,
                10 - 1); 
        
        
    }
	
	/**
	 * Will draw the entrances
	 * @param graphics
	 * @param location
	 * @param color
	 */
	
	public void drawEntrances(Graphics graphics, Location location, Color color) {
		graphics.setColor(Colors.DARK_RED);
        graphics.fillRect(10, 0, 5, 25);
        graphics.setColor(Colors.PASTEL_RED);
        graphics.fillRect(15, 0, 15, 25);
        graphics.setColor(Colors.DARK_RED);
        graphics.fillRect(30, 0, 5, 25);
        
        graphics.setColor(Colors.MEMBER_BLUE);
        graphics.fillRect(750, 0, 5, 25);
        graphics.setColor(Colors.PASTEL_BLUE);
        graphics.fillRect(755, 0, 20, 25);
        graphics.setColor(Colors.MEMBER_BLUE);
        graphics.fillRect(770, 0, 5, 25);
        
        graphics.setColor(Color.BLACK);
        graphics.fillRect(750, 315, 5, 25);
        graphics.setColor(Color.LIGHT_GRAY);
        graphics.fillRect(755, 315, 20, 25);
        graphics.setColor(Color.BLACK);
        graphics.fillRect(770, 315, 5, 25);
	}
	
	/**
	 * Will draw the electrical charging spots
	 * @param graphics
	 * @param location
	 * @param color
	 */
	
	public void drawElectricalSpots(Graphics graphics, Location location, Color color) {
		graphics.setColor(Color.BLACK);
		graphics.fillRect(730, 35, 6, 3);
		graphics.fillRect(730, 45, 6, 3);
		graphics.fillRect(730, 55, 6, 3);
		graphics.fillRect(730, 65, 6, 3);
		graphics.fillRect(730, 75, 6, 3);
		graphics.fillRect(730, 85, 6, 3);
		graphics.fillRect(730, 95, 6, 3);
		graphics.fillRect(730, 105, 6, 3);
		graphics.fillRect(730, 115, 6, 3);
		graphics.fillRect(730, 125, 6, 3);
		graphics.fillRect(730, 135, 6, 3);
		graphics.fillRect(730, 145, 6, 3);
		graphics.fillRect(730, 155, 6, 3);
		graphics.fillRect(730, 165, 6, 3);
		graphics.fillRect(730, 175, 6, 3);
		graphics.fillRect(730, 185, 6, 3);
		graphics.fillRect(730, 195, 6, 3);
		graphics.fillRect(730, 205, 6, 3);
		graphics.fillRect(730, 215, 6, 3);
		graphics.fillRect(730, 225, 6, 3);
		graphics.fillRect(730, 235, 6, 3);
		graphics.fillRect(730, 245, 6, 3);
		graphics.fillRect(730, 255, 6, 3);
		graphics.fillRect(730, 265, 6, 3);
		graphics.fillRect(730, 275, 6, 3);
		graphics.fillRect(730, 285, 6, 3);
		graphics.fillRect(730, 295, 6, 3);
		graphics.fillRect(730, 305, 6, 3);
		graphics.fillRect(730, 315, 6, 3);
		graphics.fillRect(730, 325, 6, 3);
	}
    
}
