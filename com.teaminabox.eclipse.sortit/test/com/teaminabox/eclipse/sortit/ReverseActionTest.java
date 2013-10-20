package com.teaminabox.eclipse.sortit;

import org.eclipse.core.runtime.CoreException;

public class ReverseActionTest extends SortItTest {
    
    private static final ReverseAction ACTION = new ReverseAction();

    public void testProcessEvenSizedList() throws CoreException {
        runTest("1\n2\n3\n4", "4\n3\n2\n1", ReverseActionTest.ACTION);
    }
    
    public void testProcessOddSizedList() throws CoreException {
        runTest("1\n2\n3\n", "\n3\n2\n1", ReverseActionTest.ACTION);
    }
    
    public void testSingleEntryIn() throws CoreException {
        runTest("1", "1", ReverseActionTest.ACTION);
    }
    
    public void testSortsEmptyDocument() throws CoreException {
        runTest("", "", ReverseActionTest.ACTION);
    }
    
    public void testTrailingBlankLine() throws CoreException {
        runTest("1\n2\n3\n4\n", "\n4\n3\n2\n1", ReverseActionTest.ACTION);
    }

}
