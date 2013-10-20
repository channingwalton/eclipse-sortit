package com.teaminabox.eclipse.sortit
import java.util.regex.Matcher
import java.util.regex.Pattern

class NumericSortAction extends SortItAction {

  private val number_pattern = Pattern.compile("[-+]?([0-9]*\\.)?[0-9]+([eE][-+]?[0-9]+)?")
  override def getComparator(): (String, String) => Boolean = (a: String, b: String) => compare(a, b) < 0

  def compare(first: String, second: String): Int = getNumber(first).compareTo(getNumber(second))

  def getNumber(text: String) =
    if (text == null || text.trim().length() == 0) {
      Double.MinValue
    } else {
      findNumber(text).toDouble
    }

  def findNumber(text: String): String = {
    val m = number_pattern.matcher(text)
    if (m.find()) {
      text.substring(0, m.end())
    } else {
      text
    }
  }
}
