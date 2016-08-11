package com.teaminabox.eclipse.sortit;

import java.util.Comparator;

public class LengthSortAction extends SortItAction {
	public Comparator<String> getComparator() {
		return new Comparator<String>() {
			@Override
			public int compare(String a, String b) {
				if (a.length() == b.length()) {
					return a.compareTo(b);
				}
				return a.length() - b.length();
			}
		};
	}
}