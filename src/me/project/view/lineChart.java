package me.project.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import me.project.abstracts.AbstractView;
import me.project.logic.CarParkingLogic;

@SuppressWarnings("serial")
public class lineChart extends AbstractView {
	
	private static int MAX_VALUE = 1;
    private static int MIN_VALUE = 0;
    private static final int width = 260;
    private static final int height = 520;
    private static final int borderSpace = 30;
    private static final int borderSpaceLeft = 50;
    private static final Color graphColor = new Color(255, 55, 0);
    private static final Stroke graphLine = new BasicStroke(3f);
    public static List<Integer> values = new ArrayList<>();
    private int index = 0;

    public lineChart(CarParkingLogic cpl) {
        super(cpl);
        values.add(cpl.getTotalCars());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        CarParkingLogic cpl = (CarParkingLogic) super.model;
        index++;

        if (index % 15 == 0) {
        	values.add(cpl.getTotalCars());
	            if (values.size() > 500) {
	            	values.remove(0);
	            }
	           
        }

        Graphics2D twoDimensionalGraphics = (Graphics2D) g;
        twoDimensionalGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        MAX_VALUE = java.lang.Math.max(Collections.max(values), MAX_VALUE);
        MIN_VALUE = java.lang.Math.max(Collections.min(values), MIN_VALUE);

        twoDimensionalGraphics.drawLine(borderSpaceLeft, getHeight() - borderSpace, borderSpaceLeft, borderSpace);
        twoDimensionalGraphics.drawLine(borderSpaceLeft, getHeight() - borderSpace, getWidth() - borderSpace, getHeight() - borderSpace);

        double xScale = ((double) getWidth() - 2 * borderSpace) / (values.size() - 1);
        double yScale = ((double) getHeight() - 2 * borderSpace) / (MAX_VALUE - MIN_VALUE);

        twoDimensionalGraphics.setColor(Color.BLACK);
        twoDimensionalGraphics.drawString("Max Amount of Cars", 35, 25);

        List<Point> values2 = new ArrayList<>();
        for (int i = 0; i < values.size(); i++) {
            int x1 = (int) (i * xScale + borderSpaceLeft);
            int y1 = (int) (((MAX_VALUE - values.get(i)) * yScale) + borderSpace);
            values2.add(new Point(x1, y1));
            twoDimensionalGraphics.setColor(graphColor);
            twoDimensionalGraphics.setStroke(graphLine);
	            if (i > 0) {
	                int x0 = values2.get(i - 1).x;
	                int y0 = values2.get(i - 1).y;
	                twoDimensionalGraphics.drawLine(x0, y0, x1, y1);
	            }
        }

        twoDimensionalGraphics.setColor(new Color(50, 50, 50));
        twoDimensionalGraphics.drawString(new Integer(MIN_VALUE).toString(), 10, 420);
        twoDimensionalGraphics.drawString(new Integer(MAX_VALUE).toString(), 10, 50);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width,height);
    }
}
