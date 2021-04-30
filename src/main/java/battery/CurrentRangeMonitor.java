package battery;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;


public final class CurrentRangeMonitor 
{
	private CurrentRangeMonitor() {}
	
	private static <T> Predicate<T> isNull() {
		return Objects::isNull;
	}

	private static Predicate<Object> isNull = isNull();
	private static Predicate<List<Integer>> isListNull = isNull();
	private static Predicate<List<Integer>> isEmpty = x -> x.isEmpty();
	private static Predicate<List<Integer>> containsNull = x -> x.stream().anyMatch(isNull);
	private static Predicate<List<Integer>> isInvalid = isListNull.or(isEmpty.or(containsNull));
	
    public static Map<String,Integer> countCurrentRangeOccurrence(List<Integer> readings)
    {
    	return (isInvalid.test(readings)) ? null : getCurrentRangeCount(readings);
    }
    
    private static Map<String,Integer> getCurrentRangeCount(List<Integer> readings){
    	Collections.sort(readings);
    	Map<String,Integer> map = new HashMap<String,Integer>();
    	int index,rangeMin,rangeMax,count;
    	index = count = 1;
    	rangeMax = rangeMin = readings.get(0);
    	while(index < readings.size()) {
    		if(readings.get(index)-readings.get(index-1)<2) {
    			count++;
    		}
    		else {
    			map.put(getRangeName(rangeMin,rangeMax), count);
    			rangeMin = readings.get(index);
    			count = 1;
    		}
    		rangeMax = readings.get(index);
    		index++;
    	}
    	map.put(getRangeName(rangeMin,rangeMax), count);
    	return map;
    }

	private static String getRangeName(int min, int max) {
		return (min==max) ? String.valueOf(min) : String.format("%d-%d", min, max);
	}
}
