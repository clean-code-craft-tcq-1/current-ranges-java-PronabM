package battery;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import org.junit.Test;

public class CurrentRangeMonitorTest 
{
	
    @Test
    public void whenCurrentRangeReadingEmpty_expectNull()
    {
        assertNull(CurrentRangeMonitor.countCurrentRangeOccurrence(Collections.emptyList()));
    }
    
    @Test
    public void whenReadingsContainSingleRange_expectSingleKeyWithLengthAsValue()
    {
    	Map<String,Integer> ranges = CurrentRangeMonitor.countCurrentRangeOccurrence(Arrays.asList(1,2,3));
        assertTrue(ranges.size() == 1);
    	assertTrue(ranges.get("1-3") == 3);
    }
}
