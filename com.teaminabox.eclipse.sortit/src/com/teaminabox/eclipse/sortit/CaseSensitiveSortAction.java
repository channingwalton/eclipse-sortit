package com.teaminabox.eclipse.sortit;

import java.util.Comparator;

public class CaseSensitiveSortAction extends SortItAction {

  public Comparator<String> getComparator()  {
	 return new Comparator<String>() {
		@Override
		public int compare(String o1, String o2) {
			return o1.compareTo(o2);
		}
	};
  }
}