package xshape.Model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

public class Polygon extends ShapeAbstact
{
    private int nbSides = 7;
    private int sideLength = 4;
    int[] xOffset;
    int[] yOffset;
    int[] xPoints;
    int[] yPoints;

    public Polygon(Point position, int nbSides, int sideLength) {
        super(position, new Dimension(sideLength, sideLength));
        this.nbSides = nbSides;
        this.sideLength = sideLength;

        calculatePoints(nbSides, sideLength);
        Rectangle bounds = calculateBounds();
        this.size = bounds.size();
        this.margin = 3;
        this.name = "Polygon";
    }

    private void calculatePoints(int nbSides, int sideLength)
    {
        xOffset = new int[nbSides];
        yOffset = new int[nbSides];
        xPoints = new int[nbSides];
        yPoints = new int[nbSides];

        double theta = 2 * Math.PI / nbSides; // Angle between vertices

        double firstAngle = Math.PI / 2 - theta / 2;

        for (int i = 0; i < nbSides; i++) 
        {
            double angle = firstAngle + i * theta;
            xOffset[i] = sideLength + (int)(sideLength * Math.cos(angle));
            yOffset[i] = sideLength + (int)(sideLength * Math.sin(angle));
            xPoints[i] = position.x + xOffset[i];
            yPoints[i] = position.y + yOffset[i];
        }
    }

    Rectangle calculateBounds() {
        //cf java.awt.Polygon
        int boundsMaxX = Integer.MIN_VALUE;
        int boundsMaxY = Integer.MIN_VALUE;

        for (int i = 0; i < nbSides; i++) {
            boundsMaxX = Math.max(boundsMaxX, xPoints[i]);
            boundsMaxY = Math.max(boundsMaxY, yPoints[i]);
        }
        return new Rectangle(this.position.x, this.position.y,
                               boundsMaxX - this.position.x,
                               boundsMaxY - this.position.y);
    }


    @Override
    public void setSize(int width, int height) {
        int minDim = Math.min(width, height);
        this.sideLength = (int)(minDim / (2 * Math.tan(Math.PI / this.nbSides)));
        calculatePoints(nbSides, sideLength);
        Rectangle bounds = calculateBounds();
        super.setSize(bounds.size.width, bounds.size.height);
    }

    @Override
    public boolean contains(int x, int y) {
        // TODO inventer la roue carre
        return calculateBounds().contains(x, y);
    }

    @Override
    public void drawAt(Graphics g, int x, int y) {

        for (int i = 0; i < nbSides; i++) {
            xPoints[i] = x + xOffset[i];
            yPoints[i] = y + yOffset[i];
        }

        g.fillPolygon(xPoints, yPoints, nbSides);
    }

    @Override
    public void drawSelection(Graphics g, int margin) 
    {
        int[] xSelectPoints = new int[nbSides];
        int[] ySelectPoints = new int[nbSides];

        double marginMult = 1 + margin * 0.1;

        for (int i = 0; i < nbSides; i++) {
            xSelectPoints[i] = (int)(position.x + sideLength + marginMult * (xOffset[i] - sideLength));
            ySelectPoints[i] = (int)(position.y + sideLength + marginMult * (yOffset[i] - sideLength));
        }

        g.drawPolygon(xSelectPoints, ySelectPoints, nbSides);
    }

    @Override
    public Shape clone() {
        // TODO Auto-generated method stub
        return new Polygon(new Point(position), nbSides, sideLength);
    }

    public int nbSides() {
        return nbSides;
    }

    public int sideLength() {
        return sideLength;
    }
    
}
