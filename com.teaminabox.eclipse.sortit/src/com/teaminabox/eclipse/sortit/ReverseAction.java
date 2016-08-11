package com.teaminabox.eclipse.sortit;

import java.util.ArrayList;
import java.util.Collections;

public class ReverseAction extends ActionDelegate {
  public ArrayList<String> process(ArrayList<String> collectionOfLines) {
	  Collections.reverse(collectionOfLines);
	  return collectionOfLines;
  }
}