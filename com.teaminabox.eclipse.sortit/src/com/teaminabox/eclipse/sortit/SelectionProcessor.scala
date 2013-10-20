package com.teaminabox.eclipse.sortit
import org.eclipse.jface.text.{ ITextSelection, IDocument }
import org.eclipse.ui.{ PlatformUI, IEditorPart }
import org.eclipse.ui.texteditor.ITextEditor

object ProcessSelection {
  def apply(processor: ActionDelegate) {
    val activeEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor()
    if (activeEditor.isInstanceOf[ITextEditor]) {
      new SelectionProcessor(processor, activeEditor.asInstanceOf[ITextEditor]).processSelection()
    }
  }

  class SelectionProcessor(processor: ActionDelegate, textEditor: ITextEditor) {

    private val document = textEditor.getDocumentProvider().getDocument(textEditor.getEditorInput())
    private val selection = textEditor.getSelectionProvider().getSelection().asInstanceOf[ITextSelection]
    private var startLineIndex = selection.getStartLine()
    private var endLineIndex = selection.getEndLine()

    if (startLineIndex == endLineIndex) {
      startLineIndex = 0
      endLineIndex = document.getNumberOfLines() - 1
    }

    def processSelection() {
      val processedlines = processor.process(collectLines())
      val startOffset = document.getLineOffset(startLineIndex)
      val replacementText = getReplacementLines(document, startLineIndex, endLineIndex, processedlines)
      document.replace(startOffset, replacementText.length(), replacementText)
      textEditor.getSelectionProvider().setSelection(selection)
    }

    private def getReplacementLines(doc: IDocument, startLineIndex: Int, endLineIndex: Int, lines: List[String]): String = {
      val replacementText = new StringBuffer()
      for (i <- 0 until lines.length) {
        replacementText.append(lines(i))
        if (i < lines.length - 1 || i == lines.length - 1 && doc.getLineDelimiter(endLineIndex) != null) {
          replacementText.append(doc.getLineDelimiter(startLineIndex))
        }
      }
      replacementText.toString()
    }

    private def collectLines(): List[String] = {
      val collectedLines = new scala.collection.mutable.ArrayBuffer[String]()
      for (line <- startLineIndex until endLineIndex + 1) {
        val delimiterLength = if (document.getLineDelimiter(line) == null) 0 else document.getLineDelimiter(line).length()
        val lineText = document.get(document.getLineOffset(line), document.getLineLength(line) - delimiterLength)
        collectedLines += lineText
      }
      collectedLines.toList
    }
  }
}
