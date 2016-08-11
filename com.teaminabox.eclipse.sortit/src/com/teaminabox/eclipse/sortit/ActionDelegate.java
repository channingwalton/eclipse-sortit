package com.teaminabox.eclipse.sortit;

import java.util.ArrayList;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.texteditor.ITextEditor;

abstract class ActionDelegate implements IWorkbenchWindowActionDelegate, IEditorActionDelegate {

  IWorkbenchWindow window = null;

  public void run(IAction action) {
    try {
		new ProcessSelection(this);
	} catch (BadLocationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }

  /**
   * Subclasses must process the lines given in the list. The processed list will be used to rebuild the lines in the editor.
   * @return the processed list
   */
  public abstract ArrayList<String> process(ArrayList<String> collectionOfLines);

  public void dispose() {}

  public void init(IWorkbenchWindow newWindow) {
    window = newWindow;
  }

  public void selectionChanged(IAction action, ISelection selection) {
    if (selection instanceof TextSelection) {
      action.setEnabled(true);
      return;
    }
    if (window != null && window.getActivePage() != null) {
      IWorkbenchPage activePage = window.getActivePage();
      action.setEnabled(activePage.getActivePart() instanceof ITextEditor);
    }
  }

  public void setActiveEditor(IAction action, IEditorPart editorPart) {
  }
}
