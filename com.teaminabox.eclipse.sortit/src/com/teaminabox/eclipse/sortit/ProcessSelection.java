package com.teaminabox.eclipse.sortit;
import org.eclipse.jface.text.*;
import org.eclipse.ui.*;
import org.eclipse.ui.texteditor.ITextEditor;

public class ProcessSelection {
  public ProcessSelection(ActionDelegate processor) throws BadLocationException {
    IEditorPart activeEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
    if (activeEditor instanceof ITextEditor) {
      new SelectionProcessor(processor, (ITextEditor) activeEditor).processSelection();
    }
  }
}
 