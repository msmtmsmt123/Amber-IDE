package amber;

import amber.data.state.LazyState;
import amber.data.state.Scope;
import amber.data.state.node.IState;
import amber.gui.dialogs.AboutDialog;
import amber.gui.dialogs.NewProjectDialog;
import amber.gui.dialogs.ResourceDialog;
import amber.gui.editor.FileViewerPanel;
import amber.gui.editor.map.MapEditorPanel;
import amber.gui.editor.text.ScriptEditorPanel;
import amber.gui.editor.tool.ToolPanel;
import amber.gui.misc.FileTreeExplorer;
import amber.gui.misc.StartPagePanel;
import amber.swing.Dialogs;
import amber.swing.tabs.CloseableTabbedPane;
import amber.swing.tabs.TabCloseListener;
import amber.swing.tree.SmartExpander;
import amber.swing.tree.Trees;
import amber.tool.ToolDefinition;
import amber.tool.ToolManifest;
import java.awt.Component;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;

/**
 * @author Tudor
 */
public class IDE extends javax.swing.JFrame {

    protected HashMap<Component, String> activeFiles = new HashMap<Component, String>();

    @LazyState(scope = Scope.PROJECT, name = "ProjectTreeExpansion")
    protected String saveTreeExpansion() {
        return Trees.getExpansionState(treeView, 0);
    }

    /**
     * Creates new form Applet
     */
    public IDE() {
        initComponents();

        treeView.addFileTreeListener(new FileTreeExplorer(treeView));
        SmartExpander.installOn(treeView);
        activeFilesTabbedPane.add("Start Page", new StartPagePanel());
        activeFilesTabbedPane.addTabCloseListener(new TabCloseListener() {
            public void tabClosed(String title, Component comp, CloseableTabbedPane pane) {
                if (activeFiles.containsKey(comp)) {
                    Amber.getWorkspace().getOpenedFiles().remove(activeFiles.remove(comp));
                }
            }
        });
        treeView.setRootVisible(true);
    }

    void setTreeViewRoot(File root) {
        Amber.getStateManager().unregisterStateOwner(this);
        treeView.setRoot(root);
        IState treeState = Amber.getStateManager().getState(Scope.PROJECT, "ProjectTreeExpansion");

        if (treeState != null) {
            Trees.restoreExpanstionState(treeView, 0, (String) treeState.get());
        }
        Amber.getStateManager().registerStateOwner(this);
    }

    void addToolTab(final ToolDefinition tool) {
        ToolPanel toolPanel = new ToolPanel(tool);
        ToolManifest mf = tool.getManifest();
        try {
            activeFilesTabbedPane.add(mf.name(), toolPanel);
        } catch (RuntimeException ex) {
            activeFilesTabbedPane.remove(toolPanel);
            throw ex; // Propagate to the error handler in FileTreeExplorer
        }
        activeFilesTabbedPane.setToolTipTextAt(activeFilesTabbedPane.getTabCount() - 1,
                String.format("<html>"
                + "<b>%s</b> v%s by %s"
                + "<br/>"
                + "&nbsp;&nbsp;&nbsp;%s"
                + "</html>", mf.name(), mf.version(), Arrays.toString(mf.authors()), mf.description()));
        activeFilesTabbedPane.setSelectedIndex(activeFilesTabbedPane.getTabCount() - 1);
    }

    void addFileTab(final File file) {
        FileViewerPanel editor;
        try {
            editor = FileViewerPanel.fileViewerPanelFor(file);
            for (JMenu menu : editor.getContextMenus()) {
                System.out.println("adding menu " + menu);
                menuBar.add(menu);
                menuBar.revalidate();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            if (ex instanceof IOException) {
                Dialogs.errorDialog()
                        .setTitle("Exception while loading file.")
                        .setMessage("Failed to read file: " + ex)
                        .show();
            } else {
                Dialogs.errorDialog()
                        .setTitle("Failed to create editor display.")
                        .setMessage("An error occured: " + ex)
                        .show();
            }
            return;
        }

        try {
            activeFilesTabbedPane.add(file.getName(), editor);
        } catch (RuntimeException ex) {
            activeFilesTabbedPane.remove(editor);
            throw ex; // Propagate to the error handler in FileTreeExplorer
        }
        activeFiles.put(editor, file.getAbsolutePath());
        activeFilesTabbedPane.setSelectedIndex(activeFilesTabbedPane.getTabCount() - 1);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jSeparator1 = new javax.swing.JSeparator();
        list1 = new java.awt.List();
        jToggleButton1 = new javax.swing.JToggleButton();
        jToolBar1 = new javax.swing.JToolBar();
        newFileButton = new javax.swing.JButton();
        newProjectButton = new javax.swing.JButton();
        openButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        resourceButton = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        headerSeparator = new javax.swing.JSeparator();
        projectDivider = new amber.swing.misc.ThinSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        treeView = new amber.swing.tree.filesystem.FileSystemTree();
        activeFilesTabbedPane = new amber.swing.tabs.CloseableTabbedPane();
        footerSeparator = new javax.swing.JSeparator();
        memoryMonitorProgressBar1 = new amber.gui.misc.MemoryMonitorProgressBar();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        newProjectItem = new javax.swing.JMenuItem();
        newFileItem = new javax.swing.JMenuItem();
        openItem = new javax.swing.JMenuItem();
        saveItem = new javax.swing.JMenuItem();
        saveAsItem = new javax.swing.JMenuItem();
        synchItem = new javax.swing.JMenuItem();
        resourcesItem = new javax.swing.JMenu();
        manageItem = new javax.swing.JMenuItem();
        newResourceItem = new javax.swing.JMenu();
        newTilesetItem = new javax.swing.JMenuItem();
        newAudioItem = new javax.swing.JMenuItem();
        newModelItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        toolsMenu = new javax.swing.JMenu();
        helpMenu = new javax.swing.JMenu();
        aboutItem = new javax.swing.JMenuItem();

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("amber/Bundle"); // NOI18N
        jMenu3.setText(bundle.getString("IDE.jMenu3.text")); // NOI18N
        jMenuBar2.add(jMenu3);

        jMenu4.setText(bundle.getString("IDE.jMenu4.text")); // NOI18N
        jMenuBar2.add(jMenu4);

        jToggleButton1.setText(bundle.getString("IDE.jToggleButton1.text")); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(bundle.getString("IDE.title")); // NOI18N
        setFocusTraversalPolicyProvider(true);
        setIconImage(new javax.swing.ImageIcon(ClassLoader.getSystemResource("icon/Logo.png")).getImage());

        jToolBar1.setFloatable(false);

        newFileButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/IDE.NewFile.Large.png"))); // NOI18N
        newFileButton.setToolTipText(bundle.getString("IDE.newFileButton.toolTipText")); // NOI18N
        newFileButton.setFocusable(false);
        newFileButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        newFileButton.setMargin(new java.awt.Insets(14, 14, 14, 14));
        newFileButton.setMaximumSize(new java.awt.Dimension(28, 32));
        newFileButton.setMinimumSize(new java.awt.Dimension(28, 32));
        newFileButton.setPreferredSize(new java.awt.Dimension(28, 32));
        newFileButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        newFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newFileButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(newFileButton);

        newProjectButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/IDE.NewProject.Large.png"))); // NOI18N
        newProjectButton.setToolTipText(bundle.getString("IDE.newProjectButton.toolTipText")); // NOI18N
        newProjectButton.setFocusable(false);
        newProjectButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        newProjectButton.setMargin(new java.awt.Insets(14, 14, 14, 14));
        newProjectButton.setMaximumSize(new java.awt.Dimension(28, 32));
        newProjectButton.setMinimumSize(new java.awt.Dimension(28, 32));
        newProjectButton.setPreferredSize(new java.awt.Dimension(28, 32));
        newProjectButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        newProjectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newProjectButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(newProjectButton);

        openButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/IDE.OpenProject.Large.png"))); // NOI18N
        openButton.setToolTipText(bundle.getString("IDE.openButton.toolTipText")); // NOI18N
        openButton.setFocusable(false);
        openButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        openButton.setMargin(new java.awt.Insets(14, 14, 14, 14));
        openButton.setMaximumSize(new java.awt.Dimension(28, 32));
        openButton.setMinimumSize(new java.awt.Dimension(28, 32));
        openButton.setPreferredSize(new java.awt.Dimension(28, 32));
        openButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        openButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(openButton);

        saveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/IDE.Save.Large.png"))); // NOI18N
        saveButton.setFocusable(false);
        saveButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        saveButton.setMargin(new java.awt.Insets(14, 14, 14, 14));
        saveButton.setMaximumSize(new java.awt.Dimension(28, 32));
        saveButton.setMinimumSize(new java.awt.Dimension(28, 32));
        saveButton.setPreferredSize(new java.awt.Dimension(28, 32));
        saveButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(saveButton);
        jToolBar1.add(jSeparator4);

        resourceButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/IDE.Resources.Large.png"))); // NOI18N
        resourceButton.setFocusable(false);
        resourceButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        resourceButton.setMargin(null);
        resourceButton.setMaximumSize(new java.awt.Dimension(28, 32));
        resourceButton.setMinimumSize(new java.awt.Dimension(28, 32));
        resourceButton.setPreferredSize(new java.awt.Dimension(28, 32));
        resourceButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        resourceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resourceButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(resourceButton);
        jToolBar1.add(jSeparator2);

        projectDivider.setDividerLocation(150);
        projectDivider.setDividerSize(0);
        projectDivider.setMinimumSize(new java.awt.Dimension(0, 0));

        jScrollPane1.setBorder(null);
        jScrollPane1.setMinimumSize(new java.awt.Dimension(0, 0));
        jScrollPane1.setViewportView(treeView);

        projectDivider.setLeftComponent(jScrollPane1);
        projectDivider.setRightComponent(activeFilesTabbedPane);

        fileMenu.setText(bundle.getString("IDE.fileMenu.text")); // NOI18N

        newProjectItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        newProjectItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/IDE.NewProject.Small.png"))); // NOI18N
        newProjectItem.setText(bundle.getString("IDE.newProjectItem.text")); // NOI18N
        newProjectItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newProjectItemActionPerformed(evt);
            }
        });
        fileMenu.add(newProjectItem);

        newFileItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        newFileItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/IDE.NewFile.Small.png"))); // NOI18N
        newFileItem.setText(bundle.getString("IDE.newFileItem.text")); // NOI18N
        newFileItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newFileItemActionPerformed(evt);
            }
        });
        fileMenu.add(newFileItem);

        openItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        openItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/IDE.OpenProject.Small.png"))); // NOI18N
        openItem.setText(bundle.getString("IDE.openItem.text")); // NOI18N
        openItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openItemActionPerformed(evt);
            }
        });
        fileMenu.add(openItem);

        saveItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        saveItem.setText(bundle.getString("IDE.saveItem.text")); // NOI18N
        fileMenu.add(saveItem);

        saveAsItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        saveAsItem.setText(bundle.getString("IDE.saveAsItem.text")); // NOI18N
        fileMenu.add(saveAsItem);

        synchItem.setText(bundle.getString("IDE.synchItem.text")); // NOI18N
        synchItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                synchItemActionPerformed(evt);
            }
        });
        fileMenu.add(synchItem);

        resourcesItem.setText(bundle.getString("IDE.resourcesItem.text")); // NOI18N

        manageItem.setText(bundle.getString("IDE.manageItem.text")); // NOI18N
        resourcesItem.add(manageItem);

        newResourceItem.setText(bundle.getString("IDE.newResourceItem.text")); // NOI18N

        newTilesetItem.setText(bundle.getString("IDE.newTilesetItem.text")); // NOI18N
        newResourceItem.add(newTilesetItem);

        newAudioItem.setText(bundle.getString("IDE.newAudioItem.text")); // NOI18N
        newResourceItem.add(newAudioItem);

        newModelItem.setText(bundle.getString("IDE.newModelItem.text")); // NOI18N
        newResourceItem.add(newModelItem);

        resourcesItem.add(newResourceItem);

        fileMenu.add(resourcesItem);

        menuBar.add(fileMenu);

        editMenu.setText(bundle.getString("IDE.editMenu.text")); // NOI18N
        menuBar.add(editMenu);

        toolsMenu.setText(bundle.getString("IDE.toolsMenu.text")); // NOI18N
        menuBar.add(toolsMenu);

        helpMenu.setText(bundle.getString("IDE.helpMenu.text")); // NOI18N

        aboutItem.setText(bundle.getString("IDE.aboutItem.text")); // NOI18N
        aboutItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutItemActionPerformed(evt);
            }
        });
        helpMenu.add(aboutItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(headerSeparator, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(projectDivider, javax.swing.GroupLayout.DEFAULT_SIZE, 982, Short.MAX_VALUE)
            .addComponent(footerSeparator)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(memoryMonitorProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(headerSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(projectDivider, javax.swing.GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(footerSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(memoryMonitorProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void openButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openButtonActionPerformed
        JFileChooser browser = new JFileChooser("Choose project location...");
        browser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        browser.setApproveButtonText("Choose directory");
        browser.setVisible(true);
        if (browser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                Amber.initializeProject(browser.getSelectedFile());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_openButtonActionPerformed

    private void editorTabbedPaneStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_editorTabbedPaneStateChanged
    }//GEN-LAST:event_editorTabbedPaneStateChanged

    private void aboutItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutItemActionPerformed
        new AboutDialog(this).setVisible(true);
    }//GEN-LAST:event_aboutItemActionPerformed

    private void resourceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resourceButtonActionPerformed
        new ResourceDialog(this).setVisible(true);
    }//GEN-LAST:event_resourceButtonActionPerformed

    private void newFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newFileButtonActionPerformed
    }//GEN-LAST:event_newFileButtonActionPerformed

    private void newFileItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newFileItemActionPerformed
        newFileButtonActionPerformed(evt);
    }//GEN-LAST:event_newFileItemActionPerformed

    private void newProjectItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newProjectItemActionPerformed
        newProjectButtonActionPerformed(evt);
    }//GEN-LAST:event_newProjectItemActionPerformed

    private void newProjectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newProjectButtonActionPerformed
        new NewProjectDialog(this).setVisible(true);
    }//GEN-LAST:event_newProjectButtonActionPerformed

    private void synchItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_synchItemActionPerformed
        //treeView.refresh();
    }//GEN-LAST:event_synchItemActionPerformed

    private void openItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openItemActionPerformed
        openButtonActionPerformed(evt);
    }//GEN-LAST:event_openItemActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutItem;
    private amber.swing.tabs.CloseableTabbedPane activeFilesTabbedPane;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JSeparator footerSeparator;
    private javax.swing.JSeparator headerSeparator;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToolBar jToolBar1;
    private java.awt.List list1;
    private javax.swing.JMenuItem manageItem;
    private amber.gui.misc.MemoryMonitorProgressBar memoryMonitorProgressBar1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem newAudioItem;
    private javax.swing.JButton newFileButton;
    private javax.swing.JMenuItem newFileItem;
    private javax.swing.JMenuItem newModelItem;
    private javax.swing.JButton newProjectButton;
    private javax.swing.JMenuItem newProjectItem;
    private javax.swing.JMenu newResourceItem;
    private javax.swing.JMenuItem newTilesetItem;
    private javax.swing.JButton openButton;
    private javax.swing.JMenuItem openItem;
    private amber.swing.misc.ThinSplitPane projectDivider;
    private javax.swing.JButton resourceButton;
    private javax.swing.JMenu resourcesItem;
    private javax.swing.JMenuItem saveAsItem;
    private javax.swing.JButton saveButton;
    private javax.swing.JMenuItem saveItem;
    private javax.swing.JMenuItem synchItem;
    private javax.swing.JMenu toolsMenu;
    private amber.swing.tree.filesystem.FileSystemTree treeView;
    // End of variables declaration//GEN-END:variables
}
