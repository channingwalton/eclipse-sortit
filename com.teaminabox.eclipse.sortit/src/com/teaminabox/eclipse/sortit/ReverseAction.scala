package com.teaminabox.eclipse.sortit

import scala.collection.JavaConversions._

class ReverseAction extends ActionDelegate {
  override def process(collectionOfLines: List[String]): List[String] = collectionOfLines.reverse
}
