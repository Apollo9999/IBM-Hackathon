package guimodule;

import processing.core.PApplet;

/**
 * A simple applet to draw a happy face.
 */
public class Smiles extends PApplet {
    @Override
    public void setup() {
        size(400, 400);
        this.background(200, 200, 200);
    }

    @Override
    public void draw() {
        fill(255, 255, 0);
        ellipse(200, 200, 390, 390);
        fill(0, 0, 0);
        ellipse(120, 130, 50, 70);
        ellipse(280, 130, 50, 70);

        noFill();
        // fill(255, 255, 255);
        stroke(0);
        strokeWeight(10);
        strokeCap(BEVEL);
        arc(200, 280, 75, 75, 0, PI);

    }
}
