
The extension to the map allows rollover titles to be show over other markers.
In implementation before this extension, titles could be drawn behind markers for earthquakes or cities.
This happened most often where earthquakes are close together. Roll over text is now always shown over markers on the map.

To implement this extension a new PGraphics buffer must be created. Unfolding maps creates and uses PGraphics buffers to draw markers and the map itself. 
The order that graphics are drawn into the buffer will determine which objects will be shown on top of other objects. 
By creating a new PGraphics buffer for the text, this buffer can be drawn after the PGraphics buffers used by Unfolding maps have been drawn (map.draw()).
 
A new butter called textPG is created in the setup method of EarthquakeCityMap. The API for showTitle defined in CommonMarker remains the same but the new PGraphics (textPG) is passed in.

public abstract void showTitle(PGraphics pg, float x, float y);

The text is drawn into the buffer between the commands beginDraw() and endDraw(). The buffer also is cleared between hovers so that old titles are not shown. 
The showTitle method is not called from CommonMarker but from the main draw method in EarthquakeCityMap. 
This allows showTitle to be called after the main map and all the other markers have been drawn. 
Once the text has been rendered into the buffer, the buffer is drawn using the image(PGGraphic, int, int) graphics object of Processing. 
