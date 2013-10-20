package com.teaminabox.eclipse.sortit

import org.eclipse.jface.viewers.ISelection
import org.eclipse.jface.action.IAction
import org.eclipse.ui.IWorkbenchWindow

class LengthSortAction extends SortItAction {
  def getComparator() = (a: String, b: String) => if (a.length == b.length) a < b else a.length < b.length
}