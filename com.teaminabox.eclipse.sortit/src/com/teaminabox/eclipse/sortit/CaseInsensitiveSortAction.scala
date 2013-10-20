package com.teaminabox.eclipse.sortit

class CaseInsensitiveSortAction extends SortItAction {

  override def getComparator(): (String, String) => Boolean = (a: String, b: String) => a.toLowerCase < b.toLowerCase

}
