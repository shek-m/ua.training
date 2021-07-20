package arrayElementsCounting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArrayElementsCounter {
    public static String countElements(List<Integer> list) {
        Integer temp;
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer i: list) {
            if (map.containsKey(i)){
                temp = map.get(i);
                map.replace(i, temp+1);
            } else {
                map.put(i, 1);
            }
        }
        return convertMapToString(map);
    }

    private static String convertMapToString(Map<Integer, Integer> map){
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, Integer> pair: map.entrySet()) {
            sb.append(pair.getKey()).append(" ").append(pair.getValue()).append("\n");
        }
        sb.trimToSize();
        return sb.toString();
    }
}