package applet;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * A simple PApplet that uses the sun color as
 * an indication of the time of day.
 */
public class ClockApplet extends PApplet {
    private PImage backgroundImg;
    //private int width = 200;
    //private int height = 200;


    @Override
    public void setup() {


        size(200, 200);

        backgroundImg = this.loadImage("palmTrees.jpg");
    }

    /** Return the RGB color of the sun at this number of hours in the day */
    public int[] sunColorSec(float hours)
    {
        int[] rgb = new int[3];
        // Scale the brightness of the yellow based on the hours.  0 hours
        // is black.  12 hours is bright yellow.
        float diffFrom12 = Math.abs(12-hours);

        float ratio = diffFrom12/12;
        rgb[0] = (int)(255*ratio);
        rgb[1] = (int)(255*ratio);
        rgb[2] = 0;

        //System.out.println("R" + rgb[0] + " G" + rgb[1] + " B" + rgb[2]);
        return rgb;
    }

    @Override
    public void draw() {
        backgroundImg.resize(0, height);
        background(0);
        image(backgroundImg, 0, 0);
        int[] color = sunColorSec(hour());		//calculate color code for sun
        fill(color[0],color[1],color[2]);	//set sun color

        ellipse(width / 5, height / 5, width / 5, height / 5);
    }
}
