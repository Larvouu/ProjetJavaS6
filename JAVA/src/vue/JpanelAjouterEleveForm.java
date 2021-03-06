/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

/**
 * Page pour ajouter un élève
 * @author Sarah
 */
public class JpanelAjouterEleveForm extends javax.swing.JPanel {

    /**
     * Creates new form JpanelAjouterEleveForm
     */
    public JpanelAjouterEleveForm() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldNomEleve = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldPrenomEleve = new javax.swing.JTextField();
        jRadioButtonCP = new javax.swing.JRadioButton();
        jRadioButtonCE1 = new javax.swing.JRadioButton();
        jRadioButtonCE2 = new javax.swing.JRadioButton();
        jRadioButtonCM1 = new javax.swing.JRadioButton();
        jRadioButtonCM2 = new javax.swing.JRadioButton();
        jButtonCreerEleve = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Entrer les informations du nouvel élève à ajouter :", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 3, 14))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Nom de l'élève :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Prénom de l'élève :");

        jRadioButtonCP.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRadioButtonCP);
        jRadioButtonCP.setSelected(true);
        jRadioButtonCP.setText("CP");

        jRadioButtonCE1.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRadioButtonCE1);
        jRadioButtonCE1.setText("CE1");

        jRadioButtonCE2.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRadioButtonCE2);
        jRadioButtonCE2.setText("CE2");

        jRadioButtonCM1.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRadioButtonCM1);
        jRadioButtonCM1.setText("CM1");

        jRadioButtonCM2.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRadioButtonCM2);
        jRadioButtonCM2.setText("CM2");

        jButtonCreerEleve.setText("Créer élève");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButtonCM2)
                    .addComponent(jRadioButtonCM1)
                    .addComponent(jRadioButtonCE2)
                    .addComponent(jRadioButtonCE1)
                    .addComponent(jRadioButtonCP)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(37, 37, 37))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(51, 51, 51)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldPrenomEleve)
                            .addComponent(jTextFieldNomEleve, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(71, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonCreerEleve)
                .addGap(151, 151, 151))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldNomEleve, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldPrenomEleve, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(jRadioButtonCP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButtonCE1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButtonCE2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButtonCM1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButtonCM2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonCreerEleve)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(191, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(181, 181, 181))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButtonCreerEleve;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButtonCE1;
    private javax.swing.JRadioButton jRadioButtonCE2;
    private javax.swing.JRadioButton jRadioButtonCM1;
    private javax.swing.JRadioButton jRadioButtonCM2;
    private javax.swing.JRadioButton jRadioButtonCP;
    private javax.swing.JTextField jTextFieldNomEleve;
    private javax.swing.JTextField jTextFieldPrenomEleve;
    // End of variables declaration//GEN-END:variables

public javax.swing.JTextField getjTextFieldNomEleve()
{
    return jTextFieldNomEleve;
}

public javax.swing.JTextField getjTextFieldPrenomEleve()
{
    return jTextFieldPrenomEleve;
}

public javax.swing.JRadioButton getjRadioButtonCP()
{
    return jRadioButtonCP;
}

public javax.swing.JRadioButton getjRadioButtonCE1()
{
    return jRadioButtonCE1;
}
        
public javax.swing.JRadioButton getjRadioButtonCE2()
{
    return jRadioButtonCE2;
}

public javax.swing.JRadioButton getjRadioButtonCM1()
{
    return jRadioButtonCM1;
}
 
public javax.swing.JRadioButton getjRadioButtonCM2()
{
    return jRadioButtonCM2;
}
        
public javax.swing.ButtonGroup getbuttonGroup1()
{
    return buttonGroup1;
}
        
public javax.swing.JButton getjButtonCreerEleve()
{
    return jButtonCreerEleve;
}
       
        
}
