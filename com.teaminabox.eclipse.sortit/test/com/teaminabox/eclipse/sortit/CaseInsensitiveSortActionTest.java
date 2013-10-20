package com.teaminabox.eclipse.sortit;

import org.eclipse.core.runtime.CoreException;

public class CaseInsensitiveSortActionTest extends SortItTest {

    public static final ActionDelegate ACTION = new CaseInsensitiveSortAction();

    public void testPreSorted() throws CoreException {
        runTest("a\nb\nc", "a\nb\nc", ACTION);
    }

    public void testSorts() throws CoreException {
        runTest("c\nb\na", "a\nb\nc", ACTION);
    }

    public void testSortsCaseInsensitively() throws CoreException {
        runTest("C\nb\nA", "A\nb\nC", ACTION);
    }

    public void testSortsEmptyDocument() throws CoreException {
        runTest("", "", ACTION);
    }
    
    public void testTrailingBlankLine() throws CoreException {
        runTest("a\nb\nc\n", "\na\nb\nc", ACTION);
    }

}
