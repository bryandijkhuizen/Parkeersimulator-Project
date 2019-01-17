package me.project.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JLabel;

import me.project.logic.CarParkingLogic;
import me.project.model.Car;
import me.project.model.Location;
import me.project.model.ParkingPassCar;

	/**
	 * This class contains the carParkView
	 * @author Bryan Dijkhuizen, Daphne Gritter, Kevin Wu, Thalisa Jagt
	 *
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
                    

                    Color color = Color.WHITE;
                    if (car == null) {
                    	color = Color.WHITE;
                    } else if(car instanceof ParkingPassCar) {
                    	color = car.getColor();
                    } else {
                    	color = car.getColor();
                    }
                    drawPlace(graphics, location, color);
                }
            }
        }

        setVisible(true);
        super.updateView();
    }

    /**
     * Paint a parking spot on the screen.
     */
    
    private void drawPlace(Graphics graphics, Location location, Color color) {
        graphics.setColor(color);
        graphics.fillRect(
                (location.getFloor() * 260 + (1 + (int) Math.floor(location.getRow() * 0.5)) * 60 + (location.getRow() % 2) * 20) -59,
                location.getPlace() * 10 + 30,
                20 - 1,
                10 - 1); 
    }
}
