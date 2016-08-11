package com.teaminabox.eclipse.sortit;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumericSortAction extends SortItAction {

  private Pattern number_pattern = Pattern.compile("[-+]?([0-9]*\\.)?[0-9]+([eE][-+]?[0-9]+)?");
  
  public Comparator<String> getComparator() {
	  return new Comparator<String>() {
		@Override
		public int compare(String o1, String o2) {
			return compareStrings(o1, o2);
		}
	  };
  }

  private int compareStrings(String first, String second) {
	  double x = getNumber(first);
	  double y = getNumber(second);
	  if (x < y) {
		  return -1;
	  } else if (x == y) {
		  return 0;
	  }
	  return 1;
  }

  private double getNumber(String text) {
    if (text == null || text.trim().length() == 0) {
      return Double.MIN_VALUE;
    }
    return Double.valueOf(findNumber(text)).doubleValue();
  }

  private String findNumber(String text) {
    Matcher m = number_pattern.matcher(text);
    if (m.find()) {
      return text.substring(0, m.end());
    }
	return text;
  }
}
