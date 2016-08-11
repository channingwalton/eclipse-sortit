package com.teaminabox.eclipse.sortit;

import junit.framework.TestCase;

public class NumericSortComparatorTest extends TestCase {
    
    private NumericSortAction comparator = new NumericSortAction();
    
    public void testIntegers() {
        assertEquals("1,2", -1, comparator.compareStrings("1", "2"));
        assertEquals("2,1", 1, comparator.compareStrings("2", "1"));
        assertEquals("-1,2", -1, comparator.compareStrings("-1", "2"));
        assertEquals("1,1", 0, comparator.compareStrings("1", "1"));
    }
    
    public void testDoubles() {
        assertEquals("3.141,2.73", 1, comparator.compareStrings("3.141", "2.73"));
        assertEquals("1.141,2.73", -1, comparator.compareStrings("1.141", "2.73"));
        assertEquals("-3.141,-3.141", 0, comparator.compareStrings("-3.141", "-3.141"));
    }
    
    public void testScientificNotation() {
        assertEquals("3.141E0,2.73E1", -1, comparator.compareStrings("3.141E0", "2.73E1"));
        assertEquals("1.141E29,2.73E3", 1, comparator.compareStrings("1.141E29", "2.73E3"));
        assertEquals("-3.141E4,-3.141E4", 0, comparator.compareStrings("-3.141E4", "-3.141E4"));
        
        assertEquals("-3.141e4,-3.141e4", 0, comparator.compareStrings("-3.141e4", "-3.141e4"));
    }
    
    public void testIntegersFollowedByText() {
        assertEquals("1foo, 2bar", -1, comparator.compareStrings("1foo", "2bar"));
        assertEquals("2foo, 1bar", 1, comparator.compareStrings("2foo", "1bar"));
        assertEquals("2foo, 2bar", 0, comparator.compareStrings("2foo", "2bar"));
        assertEquals("-2foo, -1bar", -1, comparator.compareStrings("-2foo", "-1bar"));
    }
    
    public void testScientificNotationFollowedByText() {
        assertEquals("1E3foo, 2E4bar", -1, comparator.compareStrings("1E3foo", "2E4bar"));
        assertEquals("2E4foo, 1E3bar", 1, comparator.compareStrings("2E4foo", "1E3bar"));
        assertEquals("2E4foo, 2E4bar", 0, comparator.compareStrings("2E4foo", "2E4bar"));
        assertEquals("-2E4foo, -1E3bar", -1, comparator.compareStrings("-2E4foo", "-1E3bar"));
    }
    
    public void testBlankText() {
        assertEquals("1, [blank]", 1, comparator.compareStrings("1", ""));
    }
}
