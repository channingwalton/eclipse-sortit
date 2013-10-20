package com.teaminabox.eclipse.sortit;

import java.io.ByteArrayInputStream;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.texteditor.ITextEditor;

public abstract class SortItTest extends TestCase {
    
    public static final String TEST_FILE = "test.txt";
    private static final long FILE_CREATE_SLEEP = 200;
    private IProject project;

    protected void tearDown() throws Exception {
        closeAllEditors();
        if (project != null) {
            project.delete(true, true, null);
        }
    }
    
    private IEditorPart createDocumentAndOpen(String content) throws CoreException {
        if (project == null) {
            project = createProject();
        }
        IFile file = create(content, TEST_FILE);
        return open(file);
    }
    
    private IFile create(String content, String fileName) {
        try {
            Path path = new Path(fileName);

            IFile file = project.getFile(path);
            byte[] buffer = content.getBytes();
            ByteArrayInputStream source = new ByteArrayInputStream(buffer);
            file.create(source, true, new NullProgressMonitor());
            Thread.sleep(FILE_CREATE_SLEEP);
            return file;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    private IEditorPart open(IFile file) throws PartInitException {
        IEditorPart editor = IDE.openEditor(SortItPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage(), file, true);
        editor.setFocus();
        return editor;
    }
    
    private IProject createProject() throws CoreException {
        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
        IProject project = root.getProject("SortitTest");
        project.create(null);
        project.open(null);
        return project;
    }
    
    private void closeAllEditors() {
        if (PlatformUI.getWorkbench().getActiveWorkbenchWindow() == null) {
            return;
        }
        IWorkbenchPage[] pages = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getPages();
        for (int i = 0; i < pages.length; i++) {
            pages[i].closeAllEditors(false);
        }
    }

    protected void runTest(String content, String expected, ActionDelegate action) throws CoreException {
        ITextEditor textEditor = (ITextEditor) createDocumentAndOpen(content);
        textEditor.setFocus();
        action.setActiveEditor(null, textEditor);
        action.run(null);
        IDocument document = textEditor.getDocumentProvider().getDocument(textEditor.getEditorInput());
        
        assertEquals(expected, document.get());
    }
    
}
