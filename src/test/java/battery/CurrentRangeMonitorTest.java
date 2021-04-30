package battery;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
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
    public void whenCurrentRangeReadingNull_expectNull()
    {
        assertNull(CurrentRangeMonitor.countCurrentRangeOccurrence(null));
    }
    
    @Test
    public void whenCurrentRangeReadingContainsNull_expectNull()
    {
        assertNull(CurrentRangeMonitor.countCurrentRangeOccurrence(Arrays.asList(1,null)));
    }
    
    @Test
    public void whenReadingsContainSingleValue_expectValueAsKeyAndCount1()
    {
    	Map<String,Integer> ranges = CurrentRangeMonitor.countCurrentRangeOccurrence(Arrays.asList(1));
    	Map<String,Integer> expectedRanges = new HashMap<String, Integer>();
    	expectedRanges.put("1", 1);
    	assertEquals(ranges,expectedRanges);
    }
    
    @Test
    public void whenReadingsContainSingleRange_expectSingleKeyAndLengthAsCount()
    {
    	Map<String,Integer> ranges = CurrentRangeMonitor.countCurrentRangeOccurrence(Arrays.asList(1,2,3,4,5));
    	Map<String,Integer> expectedRanges = new HashMap<String, Integer>();
    	expectedRanges.put("1-5", 5);
    	assertEquals(ranges,expectedRanges);
    }
    
    @Test
    public void whenReadingsContainSingleNveRange_expectSingleKeyAndLengthAsCount()
    {
    	Map<String,Integer> ranges = CurrentRangeMonitor.countCurrentRangeOccurrence(Arrays.asList(-1,-2,-3,-4,-5));
    	Map<String,Integer> expectedRanges = new HashMap<String, Integer>();
    	expectedRanges.put("-5--1", 5);
    	assertEquals(ranges,expectedRanges);
    }
    
    @Test
    public void whenReadingsContainMultiRange_expectEveryRange()
    {
    	Map<String,Integer> ranges = CurrentRangeMonitor.countCurrentRangeOccurrence(Arrays.asList(3,3,5,4,10,11,12));
    	Map<String,Integer> expectedRanges = new HashMap<String, Integer>();
    	expectedRanges.put("3-5", 4);
    	expectedRanges.put("10-12", 3);
    	assertEquals(ranges,expectedRanges);
    }
}
