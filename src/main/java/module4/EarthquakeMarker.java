package module4;

import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import processing.core.PGraphics;

import java.awt.*;

/** Implements a visual marker for earthquakes on an earthquake map
 * 
 * @author UC San Diego Intermediate Software Development MOOC team
 * @author Your name here
 *
 */
public abstract class EarthquakeMarker extends SimplePointMarker
{
	
	// Did the earthquake occur on land?  This will be set by the subclasses.
	protected boolean isOnLand;

	// The radius of the Earthquake marker
	// You will want to set this in the constructor, either
	// using the thresholds below, or a continuous function
	// based on magnitude. 
	protected float radius;
	
	
	/** Greater than or equal to this threshold is a moderate earthquake */
	public static final float THRESHOLD_MODERATE = 5;
	/** Greater than or equal to this threshold is a light earthquake */
	public static final float THRESHOLD_LIGHT = 4;

	/** Greater than or equal to this threshold is an intermediate depth */
	public static final float THRESHOLD_INTERMEDIATE = 70;
	/** Greater than or equal to this threshold is a deep depth */
	public static final float THRESHOLD_DEEP = 300;

	public static final String RADIUS_PROP = "radius";

	// ADD constants for colors
    public static final Color red = new Color(255, 0, 0);
    public static final Color yellow = new Color(255, 255, 0);
    public static final Color blue = new Color(0, 0, 255);

    public static enum EarthquakeAge{
        HOUR("Past Hour"),
        DAY("Past Day"),
        MONTH("Past Month");

        private final String age;

        EarthquakeAge(String s) {
            this.age = s;
        }

        public String getAge() {
            return age;
        }
    }

	
	// abstract method implemented in derived classes
	public abstract void drawEarthquake(PGraphics pg, float x, float y);
		
	
	// constructor
	public EarthquakeMarker (PointFeature feature) 
	{
		super(feature.getLocation());
		// Add a radius property and then set the properties
		java.util.HashMap<String, Object> properties = feature.getProperties();
		float magnitude = Float.parseFloat(properties.get("magnitude").toString());
		properties.put("radius", 2*magnitude );
		setProperties(properties);
		this.radius = 1.75f*getMagnitude();
	}
	

	// calls abstract method drawEarthquake and then checks age and draws X if needed
	public void draw(PGraphics pg, float x, float y) {
		// save previous styling
		pg.pushStyle();
			
		// determine color of marker from depth
		colorDetermine(pg);
		
		// call abstract method implemented in child class to draw marker shape
		drawEarthquake(pg, x, y);
		
		// OPTIONAL TODO: draw X over marker if within past day
        if(this.isPastPeriod(EarthquakeAge.DAY)){
            float half = radius/2 + 2;
            pg.line(x - half, y - half, x + half, y + half);
            pg.line(x + half, y - half, x - half, y + half);
        }

		
		// reset to previous styling
		pg.popStyle();
		
	}
	
	// determine color of marker from depth
	// We suggest: Deep = red, intermediate = blue, shallow = yellow
	// But this is up to you, of course.
	// You might find the getters below helpful.
	private void colorDetermine(PGraphics pg) {
		//TODO: Implement this method
        int color;
        if(this.getDepth() <= EarthquakeMarker.THRESHOLD_INTERMEDIATE){
            color = pg.color(EarthquakeMarker.yellow.getRGB());
        }
        else if (this.getDepth() > EarthquakeMarker.THRESHOLD_INTERMEDIATE &&
                this.getDepth() <= EarthquakeMarker.THRESHOLD_DEEP) {
            color = pg.color(EarthquakeMarker.blue.getRGB());
        }
        else {
            color = pg.color(EarthquakeMarker.red.getRGB());
        }
        pg.fill(color);
	}
	
	
	/*
	 * getters for earthquake properties
	 */
	
	public float getMagnitude() {
		return Float.parseFloat(getProperty("magnitude").toString());
	}
	
	public float getDepth() {
		return Float.parseFloat(getProperty("depth").toString());	
	}
	
	public String getTitle() {
		return (String) getProperty("title");	
		
	}
	
	public float getRadius() {
		return Float.parseFloat(getProperty("radius").toString());
	}

	public boolean isPastPeriod(EarthquakeAge targetAge){
	    boolean ta = false;
        if(getProperty("age") != null &&
                getProperty("age").
                        toString().
                        equalsIgnoreCase(targetAge.getAge())){
            ta = true;
        }

        return ta;
    }
	
	public boolean isOnLand()
	{
		return isOnLand;
	}
	
	
}
