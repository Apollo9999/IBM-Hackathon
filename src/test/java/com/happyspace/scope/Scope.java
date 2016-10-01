package com.happyspace.scope;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;

/**
 * A few simple scope tests.
 */
@RunWith(PowerMockRunner.class)
public class Scope {

    @Test
    public void testArrayScope(){
        double[] coords = {5.0, 0.0};
        ArrayLocation accra = new ArrayLocation(coords);
        coords[0] = 32.9;
        coords[1] = -117.2;
        double[] coords1 = accra.getCoords();

        assertEquals(coords[0], coords1[0], 0.0);
    }

    public static class ArrayLocation{
        public double[] getCoords() {
            return coords;
        }

        private final double coords[];

        public ArrayLocation(double[] coords){
            this.coords = coords;
        }
    }

}


