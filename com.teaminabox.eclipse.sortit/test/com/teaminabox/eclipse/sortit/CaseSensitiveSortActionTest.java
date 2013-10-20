package com.teaminabox.eclipse.sortit;

import org.eclipse.core.runtime.CoreException;

public class CaseSensitiveSortActionTest extends SortItTest {

    private static final CaseSensitiveSortAction ACTION = new CaseSensitiveSortAction();

    public void testPreSorted() throws CoreException {
        runTest("a\nb\nc", "a\nb\nc", CaseSensitiveSortActionTest.ACTION);
    }
    
    public void testSorts() throws CoreException {
        runTest("c\nb\na", "a\nb\nc", CaseSensitiveSortActionTest.ACTION);
    }
    
    public void testSortsCaseSensitively() throws CoreException {
        runTest("a\nb\nA", "A\na\nb", CaseSensitiveSortActionTest.ACTION);
    }
    
    public void testSortsEmptyDocument() throws CoreException {
        runTest("", "", CaseSensitiveSortActionTest.ACTION);
    }
    
    public void testTrailingBlankLine() throws CoreException {
        runTest("a\nb\nc\n", "\na\nb\nc", CaseSensitiveSortActionTest.ACTION);
    }

}
