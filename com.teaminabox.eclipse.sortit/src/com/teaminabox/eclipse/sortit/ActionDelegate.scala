package com.teaminabox.eclipse.sortit

import org.eclipse.core.runtime.IStatus
import org.eclipse.core.runtime.Status
import org.eclipse.jface.action.IAction
import org.eclipse.jface.dialogs.MessageDialog
import org.eclipse.jface.text.TextSelection
import org.eclipse.jface.viewers.ISelection
import org.eclipse.swt.widgets.Shell
import org.eclipse.ui.IEditorActionDelegate
import org.eclipse.ui.IEditorPart
import org.eclipse.ui.IWorkbenchWindow
import org.eclipse.ui.IWorkbenchWindowActionDelegate
import org.eclipse.ui.PlatformUI
import org.eclipse.ui.texteditor.ITextEditor

trait ActionDelegate extends IWorkbenchWindowActionDelegate with IEditorActionDelegate {

  var window: Option[IWorkbenchWindow] = None

  override def run(action: IAction) {
    ProcessSelection(this)
  }

  /**
   * Subclasses must process the lines given in the list. The processed list will be used to rebuild the lines in the editor.
   * @return the processed list
   */
  def process(collectionOfLines: List[String]): List[String]

  override def dispose() {}

  def init(newWindow: IWorkbenchWindow) {
    window = Option(newWindow)
  }

  override def selectionChanged(action: IAction, selection: ISelection) {
    if (selection.isInstanceOf[TextSelection]) {
      action.setEnabled(true)
      return
    }
    for (w <- window; activePage <- Option(w.getActivePage())) {
      action.setEnabled(activePage.getActivePart().isInstanceOf[ITextEditor])
    }
  }

  override def setActiveEditor(action: IAction, editorPart: IEditorPart) {
  }

  
}
