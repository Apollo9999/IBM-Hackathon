package module3;

//Java utilities libraries
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.utils.MapUtils;
import parsing.ParseFeed;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//import java.util.Collections;
//import java.util.Comparator;
//Processing library
//Unfolding libraries
//Parsing library

/** EarthquakeCityMap
 * An application with an interactive map displaying earthquake data.
 * Author: UC San Diego Intermediate Software Development MOOC team
 * @author Your name here
 * Date: July 17, 2015
 * */
public class EarthquakeCityMap extends PApplet {

	// You can ignore this.  It's to keep eclipse from generating a warning.
	private static final long serialVersionUID = 1L;

	// IF YOU ARE WORKING OFFLINE, change the value of this variable to true
	private static final boolean offline = false;
	
	// Less than this threshold is a light earthquake
	public static final float THRESHOLD_MODERATE = 5;
	// Less than this threshold is a minor earthquake
	public static final float THRESHOLD_LIGHT = 4;

	/** This is where to find the local tiles, for working without an Internet connection */
	public static String mbTilesString = "blankLight-1-3.mbtiles";
	
	// The map
	private UnfoldingMap map;
	
	//feed with magnitude 2.5+ Earthquakes
	private String earthquakesURL = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.atom";

	
	public void setup() {
		size(950, 600, OPENGL);

		if (offline) {
		    map = new UnfoldingMap(this, 200, 50, 700, 500, new MBTilesMapProvider(mbTilesString));
		    earthquakesURL = "2.5_week.atom"; 	// Same feed, saved Aug 7, 2015, for working offline
		}
		else {
			map = new UnfoldingMap(this, 200, 50, 700, 500, new Google.GoogleMapProvider());
			// IF YOU WANT TO TEST WITH A LOCAL FILE, uncomment the next line
			//earthquakesURL = "2.5_week.atom";
		}
		
	    map.zoomToLevel(2);
	    MapUtils.createDefaultEventDispatcher(this, map);	
			
	    // The List you will populate with new SimplePointMarkers
	    List<Marker> markers = new ArrayList<Marker>();

	    //Use provided parser to collect properties for each earthquake
	    //PointFeatures have a getLocation method
	    List<PointFeature> earthquakes = ParseFeed.parseEarthquake(this, earthquakesURL);
	    
	    // These print statements show you (1) all of the relevant properties 
	    // in the features, and (2) how to get one property and use it
        markers.addAll(earthquakes.stream().map(this::createMarker).collect(Collectors.toList()));

		map.addMarkers(markers);
	    
	    // Here is an example of how to use Processing's color method to generate 
	    // an int that represents the color yellow.  
	    int yellow = color(255, 255, 0);
	    
	    //TODO: Add code here as appropriate
	}
		
	// A suggested helper method that takes in an earthquake feature and 
	// returns a SimplePointMarker for that earthquake
	// TODO: Implement this method and call it from setUp, if it helps
	private SimplePointMarker createMarker(PointFeature feature)
	{
        Object magObj = feature.getProperty("magnitude");
        float mag = Float.parseFloat(magObj.toString());

	    SimplePointMarker marker = new SimplePointMarker(feature.getLocation());
        if(mag < THRESHOLD_LIGHT){
            marker.setRadius(4);
            marker.setColor(color(0,0,255));
        } else if (mag >= THRESHOLD_LIGHT && mag < THRESHOLD_MODERATE) {
            marker.setRadius(6);
            marker.setColor(color(255, 255, 0));
        }
        else {
            marker.setRadius(10);
            marker.setColor(color(255, 0, 0));
        }

        marker.setProperties(feature.getProperties());
        // finish implementing and use this method, if it helps.
		return marker;
	}
	
	public void draw() {
	    background(10);
	    map.draw();
	    addKey();
	}


	// helper method to draw key in GUI
	// TODO: Implement this method to draw the key
	private void addKey() 
	{
	    fill(255, 255, 230);
		// Remember you can use Processing's graphics methods here
        rect(20, 50, 140, 200, 6);
        rectMode(CORNER);

        ellipseMode(CENTER);
        textSize(14);
        fill(50);
        text("Earthquake Key", 30, 100);

        fill(255, 0, 0);
        ellipse(40, 120, 10, 10);
        textSize(10);
        fill(50);
        text("5.0+ Magnitude", 60, 120);

        fill(255, 255, 0);
        ellipse(40, 140, 6, 6);
        textSize(10);
        fill(50);
        text("4.0+ Magnitude", 60, 140);

        fill(0, 0, 255);
        ellipse(40, 160, 4, 4);
        textSize(10);
        fill(50);
        text("Below 4.0", 60, 160);

    }
}