package battery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author {@literal Pronab Mukherjee (Pronab.Mukherjee@in.bosch.com)}
 */

public final class CurrentRangeMonitor 
{
    public static Map<String,Integer> countCurrentRangeOccurrence(List<Integer> readings)
    {
    	if(readings.isEmpty())
    		return null;
    	
    	return new HashMap<String,Integer>();
    }
}
