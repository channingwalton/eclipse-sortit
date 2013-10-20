package com.teaminabox.eclipse.sortit;

import org.eclipse.core.runtime.CoreException;

public class LengthSortActionTest extends SortItTest {
  private static final LengthSortAction ACTION = new LengthSortAction();
  
  public void testPreSorted() throws CoreException {
    runTest("a\naa\naaa", "a\naa\naaa", ACTION);
  }
  
  public void testSorts() throws CoreException {
      runTest("aaa\naa\na", "a\naa\naaa", ACTION);
  }
  
  public void testSortsCaseSensitivelyIfLinesAreSameLength() throws CoreException {
      runTest("aA\naaa\nAa", "Aa\naA\naaa", ACTION);
  }
  
  public void testSortsEmptyDocument() throws CoreException {
      runTest("", "", ACTION);
  }
  
  public void testTrailingBlankLine() throws CoreException {
      runTest("aaa\naa\na\n", "\na\naa\naaa", ACTION);
  }
}