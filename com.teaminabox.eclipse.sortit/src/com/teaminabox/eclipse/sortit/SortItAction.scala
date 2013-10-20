package com.teaminabox.eclipse.sortit;

trait SortItAction extends ActionDelegate {

  def getComparator(): (String, String) => Boolean

  def process(collectionOfLines: List[String]): List[String] = collectionOfLines.sortWith(getComparator());
}
