package demos;


public class AirportUtil {

    public static String findAirportCode(String cityName, Airport[] airports) {
        String code = "";
        for (Airport airport : airports) {
            if (airport.getCity().equalsIgnoreCase(cityName)) {
                code = airport.getCode3();
            }
        }
        return code;
    }

    public static String findAirportCodebS(String toFind, Airport[] airports){
        int low = 0;
        int high = airports.length;
        int mid;
        while (low <= high){
            mid = low + ((high - low) / 2);
            int compare = toFind.compareTo(airports[mid].getCity());
            if (compare < 0){
                high = mid - 1;
            }
            else if (compare > 0) {
                low = mid + 1;
            }
            else {
                return airports[mid].getCode3();
            }
        }
        return null;
    }

    public static void selectionSort( int[] vals){

        for (int i = 0; i < vals.length; i++) {
            int indexMin = i;

            for (int j = i + 1; j < vals.length; j++) {
                if ( vals[j] < vals[indexMin]) {
                    indexMin = j;
                }
            }
            swap(vals, indexMin, i);
        }
    }

    public static void mysterySort( int[] vals ) {
        int currInd;

        for (int i = 0; i < vals.length; i++) {
            currInd = i;

            while (currInd > 0 && vals[currInd] < vals[currInd - 1]) {
                swap(vals, currInd, currInd - 1);
                currInd = currInd - 1;
            }
        }
    }

    public static void swap (int[] vals, int indexMin, int i) {
        int temp = vals[i];
        vals[i] = vals[indexMin];
        vals[indexMin] = temp;
    }
}