package amber.gui.dialogs;

import amber.Amber;
import amber.data.state.Scope;
import amber.data.state.State;
import amber.os.Natives;
import amber.gl.model.obj.WavefrontObject;
import amber.gui.editor.map.ModelSelector;
import amber.gui.misc.ErrorHandler;
import amber.os.filechooser.FileDialogFactory;
import amber.os.filechooser.IFileDialog;
import amber.swing.UIUtil;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.table.TableModel;

/**
 *
 * @author Tudor
 */
public class NewModelDialog extends javax.swing.JDialog {

    private IFileDialog browser;
    private WavefrontObject model;
    @State(Scope.PROJECT)
    private static String lastImportLocation;

    static {
        Amber.getStateManager().registerStateOwner(NewModelDialog.class);
    }

    /**
     * Creates new form NewModelDialog
     */
    public NewModelDialog(java.awt.Frame parent) {
        super(parent);
        initComponents();

        browser = FileDialogFactory.newFileDialog("Select model...", this);
        browser.setFilter("Model files (*obj)|*.obj");

        UIUtil.adjustColumnPreferredWidths(detailsTable);
    }

    private void checkCreateableStatus() {
        if (Amber.getResourceManager().getModel(nameField.getText()) != null) {
            return;
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                createButton.setEnabled(new File(audioLocationField.getText()).exists() && !nameField.getText().isEmpty());
            }
        });
    }

    private void updatePreview() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                final File dir = new File(audioLocationField.getText());
                if (dir.exists()) {
                    UIUtil.setTreeEnabled(detailsTable, true);

                    try {
                        model = new WavefrontObject(dir);
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                        ErrorHandler.alert(ex);
                        return;
                    }

                    try {
                        previewLabel.setText("");
                        previewLabel.setIcon(new ImageIcon(ModelSelector.makeImage(model, 100, 100)));
                        previewGroup.validate();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    TableModel mod = detailsTable.getModel();
                    mod.setValueAt("Wavefront OBJ", 0, 1);
                    mod.setValueAt(model.getGroups().size() + " groups", 1, 1);
                    mod.setValueAt(model.getVertices().size() + " vertices", 2, 1);
                    mod.setValueAt(model.getNormals().size() + " normals", 3, 1);
                    mod.setValueAt(model.getMaterials().size() + " materials", 4, 1);
                    UIUtil.adjustColumnPreferredWidths(detailsTable);
                } else {
                    UIUtil.setTreeEnabled(detailsTable, false);
                }
                previewGroup.revalidate();
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cancelButton = new javax.swing.JButton();
        createButton = new javax.swing.JButton();
        previewGroup = new javax.swing.JPanel();
        previewLabel = new javax.swing.JLabel();
        detailsScrollPane = new javax.swing.JScrollPane();
        detailsTable = new javax.swing.JTable();
        audioGroup = new javax.swing.JPanel();
        nameLabel = new javax.swing.JLabel();
        audioLocationField = new javax.swing.JTextField();
        nameField = new javax.swing.JTextField();
        browseButton = new javax.swing.JButton();
        clipLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("New model...");
        setModal(true);
        setResizable(false);

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("amber/Bundle"); // NOI18N
        cancelButton.setText(bundle.getString("NewTilesetDialog.cancelButton.text")); // NOI18N
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        createButton.setText(bundle.getString("NewTilesetDialog.createButton.text")); // NOI18N
        createButton.setEnabled(false);
        createButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createButtonActionPerformed(evt);
            }
        });

        previewGroup.setBorder(javax.swing.BorderFactory.createTitledBorder("Preview"));
        previewGroup.setMaximumSize(new java.awt.Dimension(177, 132));
        previewGroup.setMinimumSize(new java.awt.Dimension(177, 132));

        previewLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        previewLabel.setText("No preview available.");

        javax.swing.GroupLayout previewGroupLayout = new javax.swing.GroupLayout(previewGroup);
        previewGroup.setLayout(previewGroupLayout);
        previewGroupLayout.setHorizontalGroup(
            previewGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(previewLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        previewGroupLayout.setVerticalGroup(
            previewGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(previewLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        detailsScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Details"));
        detailsScrollPane.setMaximumSize(new java.awt.Dimension(462, 423));
        detailsScrollPane.setMinimumSize(new java.awt.Dimension(462, 423));

        detailsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Format", null},
                {"Faces", null},
                {"Vertices", null},
                {"Normals", null},
                {"Materials", null}
            },
            new String [] {
                "Property", "Value"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        detailsTable.getTableHeader().setReorderingAllowed(false);
        detailsScrollPane.setViewportView(detailsTable);

        audioGroup.setBorder(javax.swing.BorderFactory.createTitledBorder("Model"));
        audioGroup.setMaximumSize(new java.awt.Dimension(104, 94));
        audioGroup.setMinimumSize(new java.awt.Dimension(104, 94));

        nameLabel.setText(bundle.getString("NewTilesetDialog.nameLabel.text")); // NOI18N

        audioLocationField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                audioLocationFieldKeyTyped(evt);
            }
        });

        nameField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nameFieldKeyTyped(evt);
            }
        });

        browseButton.setText(bundle.getString("NewTilesetDialog.browseButton.text")); // NOI18N
        browseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseButtonActionPerformed(evt);
            }
        });

        clipLabel.setText("Model:");

        javax.swing.GroupLayout audioGroupLayout = new javax.swing.GroupLayout(audioGroup);
        audioGroup.setLayout(audioGroupLayout);
        audioGroupLayout.setHorizontalGroup(
            audioGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(audioGroupLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(audioGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(clipLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(audioGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(audioGroupLayout.createSequentialGroup()
                        .addComponent(audioLocationField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(browseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(nameField))
                .addContainerGap())
        );
        audioGroupLayout.setVerticalGroup(
            audioGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(audioGroupLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(audioGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(audioGroupLayout.createSequentialGroup()
                        .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(audioGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(audioLocationField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(browseButton)
                            .addComponent(clipLabel)))
                    .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(createButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton))
                    .addComponent(audioGroup, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(detailsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(previewGroup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(audioGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(detailsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(previewGroup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(createButton))
                .addGap(6, 6, 6))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void createButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createButtonActionPerformed
        File file = new File(audioLocationField.getText());
        lastImportLocation = file.getParent();
        Amber.getResourceManager().importModel(nameField.getText(), model, file);
        dispose();
    }//GEN-LAST:event_createButtonActionPerformed

    private void audioLocationFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_audioLocationFieldKeyTyped
        updatePreview();
        checkCreateableStatus();
    }//GEN-LAST:event_audioLocationFieldKeyTyped

    private void nameFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nameFieldKeyTyped
        checkCreateableStatus();
    }//GEN-LAST:event_nameFieldKeyTyped

    private void browseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseButtonActionPerformed
        if (lastImportLocation != null) {
            File dir = new File(lastImportLocation);
            if (dir.exists() && dir.isDirectory()) {
                browser.setInitial(dir);
            }
        }
        setEnabled(false);
        if (browser.show()) {
            audioLocationField.setText(browser.getFile().getAbsolutePath());
            updatePreview();
            checkCreateableStatus();
        }
        setEnabled(true);
    }//GEN-LAST:event_browseButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel audioGroup;
    private javax.swing.JTextField audioLocationField;
    private javax.swing.JButton browseButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel clipLabel;
    private javax.swing.JButton createButton;
    private javax.swing.JScrollPane detailsScrollPane;
    private javax.swing.JTable detailsTable;
    private javax.swing.JTextField nameField;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JPanel previewGroup;
    private javax.swing.JLabel previewLabel;
    // End of variables declaration//GEN-END:variables
}
