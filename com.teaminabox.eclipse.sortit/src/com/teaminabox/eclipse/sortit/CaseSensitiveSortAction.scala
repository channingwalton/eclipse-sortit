package com.teaminabox.eclipse.sortit

class CaseSensitiveSortAction extends SortItAction {

  override def getComparator() = (a: String, b: String) => a < b

}
