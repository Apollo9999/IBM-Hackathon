package applet;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.marker.Marker;
import processing.core.PApplet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create world map of life expectancy.
 */
public class LifeExpectancy extends PApplet {
    UnfoldingMap map;
    HashMap<String, Float> lifeExpMap;
    List<Feature> countries;
    List<Marker> countryMarkers;

    @Override
    public void setup() {
        Map<String, Float> lifeExpMap = new HashMap<>();
    }

    @Override
    public void draw() {

    }
}
