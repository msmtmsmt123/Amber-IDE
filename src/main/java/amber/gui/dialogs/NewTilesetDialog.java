package amber.gui.dialogs;

import amber.Amber;
import amber.data.res.Tileset;
import amber.data.state.Scope;
import amber.data.state.State;
import amber.swing.UIUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @author Tudor
 */
public class NewTilesetDialog extends javax.swing.JDialog {

    private JFileChooser browser;
    @State(Scope.PROJECT)
    private static String lastImportLocation;

    static {
        Amber.getStateManager().registerStateOwner(NewTilesetDialog.class);
    }

    /**
     * Creates new form NewTilesetDialog
     */
    public NewTilesetDialog(java.awt.Frame parent) {
        super(parent);
        initComponents();

        browser = new JFileChooser("Select tileset image...");
        browser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); // ech
        browser.setApproveButtonText("Choose image");
        browser.setFileFilter(UIUtil.makeFileFilter(
                "Image files (*.png; *.jpeg; *.jpg; *.gif)",
                "png",
                "jpeg",
                "jpg",
                "gif"));
    }

    public NewTilesetDialog(java.awt.Frame parent, File image) {
        this(parent);
        imageLocationField.setText(image.getAbsolutePath());
        updatePreview();
        checkCreateableStatus();
    }

    private void checkCreateableStatus() {
        if (Amber.getResourceManager().getTileset(nameField.getText()) != null) {
            return;
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                createButton.setEnabled(new File(imageLocationField.getText()).exists() && !nameField.getText().isEmpty());
            }
        });
    }

    private void updatePreview() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    previewLabel.setText("");
                    previewLabel.setIcon(new ImageIcon(ImageIO.read(new File(imageLocationField.getText()))));
                } catch (Exception e) {
                    previewLabel.setIcon(null);
                    previewLabel.setText("No preview available.");
                    previewLabel.setSize(previewLabel.getPreferredSize());
                    previewGroup.validate();
                }
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

        tilesetGroup = new javax.swing.JPanel();
        nameLabel = new javax.swing.JLabel();
        imageLocationField = new javax.swing.JTextField();
        imageLabel = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        browseButton = new javax.swing.JButton();
        transparencyBox = new javax.swing.JCheckBox();
        colorChooser = new amber.swing.misc.ColorChooserButton();
        tileSizeGroup = new javax.swing.JPanel();
        widthSpinner = new javax.swing.JSpinner();
        widthLabel = new javax.swing.JLabel();
        heightLabel = new javax.swing.JLabel();
        heightSpinner = new javax.swing.JSpinner();
        spacingLabel = new javax.swing.JLabel();
        spacingSpinner = new javax.swing.JSpinner();
        marginSpinner = new javax.swing.JSpinner();
        marginLabel = new javax.swing.JLabel();
        createButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        previewGroup = new javax.swing.JScrollPane();
        previewLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("amber/Bundle"); // NOI18N
        setTitle(bundle.getString("NewTilesetDialog.title")); // NOI18N
        setModal(true);
        setResizable(false);

        tilesetGroup.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("NewTilesetDialog.tilesetGroup.border.title"))); // NOI18N
        tilesetGroup.setMaximumSize(new java.awt.Dimension(348, 107));
        tilesetGroup.setMinimumSize(new java.awt.Dimension(348, 107));

        nameLabel.setText(bundle.getString("NewTilesetDialog.nameLabel.text")); // NOI18N

        imageLocationField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                imageLocationFieldKeyTyped(evt);
            }
        });

        imageLabel.setText(bundle.getString("NewTilesetDialog.imageLabel.text")); // NOI18N

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

        transparencyBox.setText(bundle.getString("NewTilesetDialog.transparencyBox.text")); // NOI18N
        transparencyBox.setAlignmentY(0.0F);
        transparencyBox.setMargin(new java.awt.Insets(0, 0, 0, 0));
        transparencyBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transparencyBoxActionPerformed(evt);
            }
        });

        colorChooser.setBorder(null);
        colorChooser.setText(bundle.getString("NewTilesetDialog.colorChooser.text")); // NOI18N
        colorChooser.setMargin(new java.awt.Insets(0, 0, 0, 0));
        colorChooser.setMaximumSize(new java.awt.Dimension(20, 20));
        colorChooser.setMinimumSize(new java.awt.Dimension(20, 20));
        colorChooser.setPreferredSize(new java.awt.Dimension(20, 20));

        javax.swing.GroupLayout tilesetGroupLayout = new javax.swing.GroupLayout(tilesetGroup);
        tilesetGroup.setLayout(tilesetGroupLayout);
        tilesetGroupLayout.setHorizontalGroup(
            tilesetGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tilesetGroupLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tilesetGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imageLabel)
                    .addComponent(nameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tilesetGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tilesetGroupLayout.createSequentialGroup()
                        .addComponent(imageLocationField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(browseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tilesetGroupLayout.createSequentialGroup()
                        .addGroup(tilesetGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(tilesetGroupLayout.createSequentialGroup()
                                .addComponent(transparencyBox, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(colorChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        tilesetGroupLayout.setVerticalGroup(
            tilesetGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tilesetGroupLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(tilesetGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(tilesetGroupLayout.createSequentialGroup()
                        .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tilesetGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(imageLocationField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(browseButton)))
                    .addGroup(tilesetGroupLayout.createSequentialGroup()
                        .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(imageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(4, 4, 4)
                .addGroup(tilesetGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(transparencyBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(colorChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        tileSizeGroup.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("NewTilesetDialog.tileSizeGroup.border.title"))); // NOI18N
        tileSizeGroup.setMaximumSize(new java.awt.Dimension(139, 150));
        tileSizeGroup.setMinimumSize(new java.awt.Dimension(139, 150));

        widthSpinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(16), Integer.valueOf(1), null, Integer.valueOf(1)));

        widthLabel.setText(bundle.getString("NewTilesetDialog.widthLabel.text")); // NOI18N

        heightLabel.setText(bundle.getString("NewTilesetDialog.heightLabel.text")); // NOI18N

        heightSpinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(16), Integer.valueOf(1), null, Integer.valueOf(1)));

        spacingLabel.setText(bundle.getString("NewTilesetDialog.spacingLabel.text")); // NOI18N

        spacingSpinner.setModel(new javax.swing.SpinnerNumberModel());
        spacingSpinner.setMinimumSize(new java.awt.Dimension(31, 20));

        marginSpinner.setModel(new javax.swing.SpinnerNumberModel());
        marginSpinner.setMinimumSize(new java.awt.Dimension(31, 20));

        marginLabel.setText(bundle.getString("NewTilesetDialog.marginLabel.text")); // NOI18N

        javax.swing.GroupLayout tileSizeGroupLayout = new javax.swing.GroupLayout(tileSizeGroup);
        tileSizeGroup.setLayout(tileSizeGroupLayout);
        tileSizeGroupLayout.setHorizontalGroup(
            tileSizeGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tileSizeGroupLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tileSizeGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tileSizeGroupLayout.createSequentialGroup()
                        .addGroup(tileSizeGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(widthLabel)
                            .addComponent(heightLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(tileSizeGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(widthSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(heightSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(tileSizeGroupLayout.createSequentialGroup()
                        .addGroup(tileSizeGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(marginLabel)
                            .addComponent(spacingLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(tileSizeGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(marginSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(spacingSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20))
        );
        tileSizeGroupLayout.setVerticalGroup(
            tileSizeGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tileSizeGroupLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(tileSizeGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(widthLabel)
                    .addComponent(widthSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tileSizeGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(heightLabel)
                    .addComponent(heightSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(tileSizeGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(marginLabel)
                    .addComponent(marginSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tileSizeGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spacingLabel)
                    .addComponent(spacingSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        createButton.setText(bundle.getString("NewTilesetDialog.createButton.text")); // NOI18N
        createButton.setEnabled(false);
        createButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createButtonActionPerformed(evt);
            }
        });

        cancelButton.setText(bundle.getString("NewTilesetDialog.cancelButton.text")); // NOI18N
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        previewGroup.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("NewTilesetDialog.previewGroup.border.title"))); // NOI18N
        previewGroup.setViewportBorder(javax.swing.BorderFactory.createEtchedBorder());
        previewGroup.setAlignmentX(0.0F);
        previewGroup.setAlignmentY(0.0F);
        previewGroup.setAutoscrolls(true);
        previewGroup.setMaximumSize(new java.awt.Dimension(119, 41));
        previewGroup.setMinimumSize(new java.awt.Dimension(119, 41));

        previewLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        previewLabel.setText(bundle.getString("NewTilesetDialog.previewLabel.text")); // NOI18N
        previewLabel.setAlignmentY(0.0F);
        previewLabel.setAutoscrolls(true);
        previewLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        previewLabel.setMaximumSize(null);
        previewLabel.setPreferredSize(null);
        previewGroup.setViewportView(previewLabel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(tileSizeGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(previewGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(226, 226, 226)
                        .addComponent(createButton)
                        .addGap(6, 6, 6)
                        .addComponent(cancelButton))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tilesetGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(tilesetGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tileSizeGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(previewGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createButton)
                    .addComponent(cancelButton))
                .addGap(10, 10, 10))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void browseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseButtonActionPerformed
        if (lastImportLocation != null) {
            File dir = new File(lastImportLocation);
            if (dir.exists() && dir.isDirectory()) {
                browser.setCurrentDirectory(dir);
            }
        }
        if (browser.showOpenDialog(Amber.getUI()) == JFileChooser.APPROVE_OPTION) {
            imageLocationField.setText(browser.getSelectedFile().getAbsolutePath());
            updatePreview();
            checkCreateableStatus();
        }
    }//GEN-LAST:event_browseButtonActionPerformed

    private void imageLocationFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_imageLocationFieldKeyTyped
        updatePreview();
        checkCreateableStatus();
    }//GEN-LAST:event_imageLocationFieldKeyTyped

    private void transparencyBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transparencyBoxActionPerformed
        if (transparencyBox.isSelected()) {
        }
    }//GEN-LAST:event_transparencyBoxActionPerformed

    private void nameFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nameFieldKeyTyped
        checkCreateableStatus();
    }//GEN-LAST:event_nameFieldKeyTyped

    private void createButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createButtonActionPerformed
        File file = new File(imageLocationField.getText());
        lastImportLocation = file.getParent();
        BufferedImage img = (BufferedImage) ((ImageIcon) previewLabel.getIcon()).getImage();
        if (img != null) {
            Amber.getResourceManager().importTileset(nameField.getText(),
                    new Tileset.Parser(
                    new Dimension((Integer) widthSpinner.getValue(),
                    (Integer) heightSpinner.getValue()),
                    (Integer) marginSpinner.getValue(),
                    (Integer) spacingSpinner.getValue()).parse(img), file);
        }
        dispose();
    }//GEN-LAST:event_createButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton browseButton;
    private javax.swing.JButton cancelButton;
    private amber.swing.misc.ColorChooserButton colorChooser;
    private javax.swing.JButton createButton;
    private javax.swing.JLabel heightLabel;
    private javax.swing.JSpinner heightSpinner;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JTextField imageLocationField;
    private javax.swing.JLabel marginLabel;
    private javax.swing.JSpinner marginSpinner;
    private javax.swing.JTextField nameField;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JScrollPane previewGroup;
    private javax.swing.JLabel previewLabel;
    private javax.swing.JLabel spacingLabel;
    private javax.swing.JSpinner spacingSpinner;
    private javax.swing.JPanel tileSizeGroup;
    private javax.swing.JPanel tilesetGroup;
    private javax.swing.JCheckBox transparencyBox;
    private javax.swing.JLabel widthLabel;
    private javax.swing.JSpinner widthSpinner;
    // End of variables declaration//GEN-END:variables
}
