import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public interface Games {

    String LF = "\n";

    /*
        CSV format SQUARE based: Square, Cube-inside-square, Value.
        Order top rightmost is 1,1,<value>
     */
    String unclassified =
            "1,7,9" + LF + "1,9,1" + LF + "2,1,1" + LF + "2,3,5" + LF +
            "3,2,6" + LF + "3,4,8" + LF + "3,4,7" + LF + "3,6,1" + LF +
            "3,8,3" + LF + "4,3,7" + LF + "4,4,5" + LF + "5,2,2" + LF +
            "5,3,6" + LF + "5,7,8" + LF + "5,8,7" + LF + "6,6,3" + LF +
            "6,7,4" + LF + "7,2,3" + LF + "7,4,1" + LF + "7,6,5" + LF +
            "7,7,7" + LF + "7,8,9" + LF + "8,7,4" + LF + "8,9,1" + LF +
            "9,1,8" + LF + "9,3,5" + LF;

    static Map<Integer, Integer> unclassifiedData(){
        return Collections.unmodifiableMap(new HashMap<Integer, Integer>() {
            {
                put(4, 1); put(6, 5); put(8, 6); put(9, 8);
                put(16, 7); put(18, 1); put(19, 9); put(21, 1);
                put(26, 3); put(30, 7); put(32, 2); put(33, 6);
                put(37, 5); put(45, 3); put(49, 8); put(50, 7);
                put(52, 4); put(56, 3); put(61, 8); put(63, 5);
                put(64, 1); put(66, 5); put(73, 7); put(74, 9);
                put(76, 4); put(78, 1);
            }
        });
    }

}
