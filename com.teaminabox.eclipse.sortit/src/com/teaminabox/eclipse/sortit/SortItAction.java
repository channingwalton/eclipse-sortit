package com.teaminabox.eclipse.sortit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public abstract class SortItAction extends ActionDelegate {

	public abstract Comparator<String> getComparator();

	public ArrayList<String> process(ArrayList<String> collectionOfLines) {
		Collections.sort(collectionOfLines, getComparator());
		return collectionOfLines;
	}
}