package com.teaminabox.eclipse.sortit;

import java.util.ArrayList;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.ui.texteditor.ITextEditor;

class SelectionProcessor {

	private ActionDelegate processor;
	private ITextEditor textEditor;
	private IDocument document;
	private ITextSelection selection;
	private int startLineIndex;
	private int endLineIndex;

	public SelectionProcessor(ActionDelegate processor, ITextEditor textEditor) {
		this.processor = processor;
		this.textEditor = textEditor;
		document = textEditor.getDocumentProvider().getDocument(textEditor.getEditorInput());
		selection = (ITextSelection) textEditor.getSelectionProvider().getSelection();
		startLineIndex = selection.getStartLine();
		endLineIndex = selection.getEndLine();

		if (startLineIndex == endLineIndex) {
			startLineIndex = 0;
			endLineIndex = document.getNumberOfLines() - 1;
		}
	}

	public void processSelection() throws BadLocationException {
      ArrayList<String> processedlines = processor.process(collectLines());
      int startOffset = document.getLineOffset(startLineIndex);
      String replacementText = getReplacementLines(document, startLineIndex, endLineIndex, processedlines);
      document.replace(startOffset, replacementText.length(), replacementText);
      textEditor.getSelectionProvider().setSelection(selection);
    }

	private String getReplacementLines(IDocument doc, int startLineIndex, int endLineIndex, ArrayList<String> lines) throws BadLocationException {
      StringBuffer replacementText = new StringBuffer();
      for (int i = 0; i < lines.size(); i++) {		
        replacementText.append(lines.get(i));
        if (i < lines.size() - 1 || i == lines.size() - 1 && doc.getLineDelimiter(endLineIndex) != null) {
          replacementText.append(doc.getLineDelimiter(startLineIndex));
        }
      }
      return replacementText.toString();
    }

	private ArrayList<String> collectLines() throws BadLocationException {
      ArrayList<String> collectedLines = new ArrayList<String>();
      for (int line = startLineIndex; line <= endLineIndex; line++) {
        int delimiterLength;
        if (document.getLineDelimiter(line) == null)
        	delimiterLength = 0;
        else 
        	delimiterLength = document.getLineDelimiter(line).length();
        
        String lineText = document.get(document.getLineOffset(line), document.getLineLength(line) - delimiterLength);
        collectedLines.add(lineText);
      }
      return collectedLines;
    }
  }