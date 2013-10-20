package com.teaminabox.eclipse.sortit;

import org.eclipse.core.runtime.CoreException;

public class NumericSortActionTest extends SortItTest {
    
    private static final NumericSortAction ACTION = new NumericSortAction();

    public void testPreSorted() throws CoreException {
        runTest("1\n2\n3", "1\n2\n3", NumericSortActionTest.ACTION);
    }
    
    public void testSortsIntegers() throws CoreException {
        runTest("3\n2\n1", "1\n2\n3", NumericSortActionTest.ACTION);
    }
    
    public void testSortScientificNotation() throws CoreException {
        runTest("3.0\n2e0\n1.1", "1.1\n2e0\n3.0", NumericSortActionTest.ACTION);
    }
    
    public void testSortsEmptyDocument() throws CoreException {
        runTest("", "", NumericSortActionTest.ACTION);
    }
    
    public void testTrailingBlankLine() throws CoreException {
        runTest("3\n2\n1\n", "\n1\n2\n3", NumericSortActionTest.ACTION);
    }
}
