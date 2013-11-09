/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sig.visorcliente;

import com.sig.utilerias.FuncionesCadena;
import com.sig.utilerias.FuncionesValidacion;
import com.sig.utilerias.entity.EntityManagerFactory;
import com.sig.visorcliente.form.HistoriaClinicaForm;
import com.six.dto.GcliCliente;
import com.six.dto.GcliHistoriaClinica;
import com.six.dto.GcliHistoriaClinicaPK;
import com.six.dto.GcliInfAsentamiento;
import com.six.dto.GcliInfCodigoPostal;
import com.six.dto.GcliInfDomicilio;
import com.six.dto.GcliInfPersona;
import com.six.dto.GcliInfProblemaMedico;
import com.six.expclientes.ExploradorClientesTopComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.cookies.SaveCookie;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;
import org.openide.util.RequestProcessor;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.windows.WindowManager;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//com.sig.visorcliente//VisorCliente//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "VisorClienteTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(mode = "editor", openAtStartup = true)
@ActionID(category = "Window", id = "com.sig.visorcliente.VisorClienteTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_VisorClienteAction",
        preferredID = "VisorClienteTopComponent"
)
@Messages({
    "CTL_VisorClienteAction=VisorCliente",
    "CTL_VisorClienteTopComponent=VisorCliente Window",
    "HINT_VisorClienteTopComponent=This is a VisorCliente window"
})
public final class VisorClienteTopComponent extends TopComponent implements LookupListener {

    private static String PREFERRED_ID = "VisorClienteTopComponent";
    private static VisorClienteTopComponent instance;
    private final SaveCookieImpl impl;
    private final InstanceContent content;
    private GcliCliente cliente;
    private GcliInfCodigoPostal cp;
    private Lookup.Result result = null;

    public JTabbedPane getJtpCliente() {
        return jtpCliente;
    }

    public JTextField getJtNombre() {
        return jtNombre;
    }

    public VisorClienteTopComponent() {
        initComponents();
        setName(Bundle.CTL_VisorClienteTopComponent());
        setToolTipText(Bundle.HINT_VisorClienteTopComponent());
        putClientProperty(TopComponent.PROP_CLOSING_DISABLED, Boolean.TRUE);
        putClientProperty(TopComponent.PROP_DRAGGING_DISABLED, Boolean.TRUE);
//        putClientProperty(TopComponent.PROP_MAXIMIZATION_DISABLED, Boolean.TRUE);
        putClientProperty(TopComponent.PROP_SLIDING_DISABLED, Boolean.TRUE);
        putClientProperty(TopComponent.PROP_UNDOCKING_DISABLED, Boolean.TRUE);
        putClientProperty(TopComponent.PROP_KEEP_PREFERRED_SIZE_WHEN_SLIDED_IN, Boolean.TRUE);

        MyDocumentListener myDocumentListener = new MyDocumentListener();
        jtNombre.getDocument().addDocumentListener(myDocumentListener);
        jtApPaterno.getDocument().addDocumentListener(myDocumentListener);
        jtApMaterno.getDocument().addDocumentListener(myDocumentListener);
        jtTelefono.getDocument().addDocumentListener(myDocumentListener);
        jtCelular.getDocument().addDocumentListener(myDocumentListener);
        jtEmail.getDocument().addDocumentListener(myDocumentListener);
        jtCalle.getDocument().addDocumentListener(myDocumentListener);
        jtNoExterior.getDocument().addDocumentListener(myDocumentListener);
        jtNoInterior.getDocument().addDocumentListener(myDocumentListener);
        jtCp.getDocument().addDocumentListener(myDocumentListener);
        jtpCliente.remove(jpCardex);
        jbAgregarProblemaMedico.setEnabled(false);

        //Create a new instance of our SaveCookie implementation:
        impl = new SaveCookieImpl();
        //Create a new instance of our dynamic object:
        content = new InstanceContent();
        //Add the dynamic object to the TopComponent Lookup:
        associateLookup(new AbstractLookup(content));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jtpCliente = new javax.swing.JTabbedPane();
        jpCliente = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtProblemasMedicos = new javax.swing.JTable();
        jbAgregarProblemaMedico = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jtNombre = new javax.swing.JTextField();
        jtApPaterno = new javax.swing.JTextField();
        jtApMaterno = new javax.swing.JTextField();
        jtTelefono = new javax.swing.JFormattedTextField();
        jtCelular = new javax.swing.JFormattedTextField();
        jtEmail = new javax.swing.JFormattedTextField();
        jtCalle = new javax.swing.JTextField();
        jtNoExterior = new javax.swing.JTextField();
        jtNoInterior = new javax.swing.JTextField();
        jtCp = new javax.swing.JTextField();
        jlColonia = new javax.swing.JLabel();
        jlMunicipio = new javax.swing.JLabel();
        jlEstado = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jpCardex = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jFormattedTextField6 = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jFormattedTextField4 = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        jFormattedTextField5 = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jFormattedTextField12 = new javax.swing.JFormattedTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jLabel1.text")); // NOI18N

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jPanel3.border.title"), javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION)); // NOI18N

        jScrollPane1.setBorder(null);

        jtProblemasMedicos.setModel(new VisorClienteTopComponent.MyModel());
        jtProblemasMedicos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jtProblemasMedicos);

        jbAgregarProblemaMedico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sig/visorcliente/resources/agregar.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jbAgregarProblemaMedico, org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jbAgregarProblemaMedico.text")); // NOI18N
        jbAgregarProblemaMedico.setToolTipText(org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jbAgregarProblemaMedico.toolTipText")); // NOI18N
        jbAgregarProblemaMedico.setPreferredSize(new java.awt.Dimension(30, 30));
        jbAgregarProblemaMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAgregarProblemaMedicoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 903, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jbAgregarProblemaMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jbAgregarProblemaMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jLabel5.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel12, org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jLabel12.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel13, org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jLabel13.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel14, org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jLabel14.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel15, org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jLabel15.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel16, org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jLabel16.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel17, org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jLabel17.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel18, org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jLabel18.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel19, org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jLabel19.text")); // NOI18N

        jtNombre.setDocument(new com.sig.utilerias.text.JTextFieldLimit(100));
        jtNombre.setText(org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jtNombre.text")); // NOI18N

        jtApPaterno.setDocument(new com.sig.utilerias.text.JTextFieldLimit(100));
        jtApPaterno.setText(org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jtApPaterno.text")); // NOI18N

        jtApMaterno.setDocument(new com.sig.utilerias.text.JTextFieldLimit(100));
        jtApMaterno.setText(org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jtApMaterno.text")); // NOI18N

        try {
            jtTelefono.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##-##-##-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jtTelefono.setPreferredSize(new java.awt.Dimension(100, 22));

        try {
            jtCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(###)-##-########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jtCelular.setPreferredSize(new java.awt.Dimension(100, 22));

        jtEmail.setDocument(new com.sig.utilerias.text.JTextFieldLimit(50));
        jtEmail.setText(org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jtEmail.text")); // NOI18N

        jtCalle.setDocument(new com.sig.utilerias.text.JTextFieldLimit(100));
        jtCalle.setText(org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jtCalle.text")); // NOI18N

        jtNoExterior.setDocument(new com.sig.utilerias.text.JTextFieldLimit(50));
        jtNoExterior.setText(org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jtNoExterior.text")); // NOI18N

        jtNoInterior.setDocument(new com.sig.utilerias.text.JTextFieldLimit(50));
        jtNoInterior.setText(org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jtNoInterior.text")); // NOI18N

        jtCp.setDocument(new com.sig.utilerias.text.JTextFieldLimit(5));
        jtCp.setText(org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jtCp.text")); // NOI18N
        jtCp.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtCpFocusLost(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jlColonia, org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jlColonia.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jlMunicipio, org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jlMunicipio.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jlEstado, org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jlEstado.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jLabel2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jLabel3.text")); // NOI18N

        javax.swing.GroupLayout jpClienteLayout = new javax.swing.GroupLayout(jpCliente);
        jpCliente.setLayout(jpClienteLayout);
        jpClienteLayout.setHorizontalGroup(
            jpClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpClienteLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(12, 12, 12))
            .addGroup(jpClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpClienteLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtNombre))
                    .addGroup(jpClienteLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtApPaterno))
                    .addGroup(jpClienteLayout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtApMaterno))
                    .addGroup(jpClienteLayout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jpClienteLayout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtEmail))
                    .addGroup(jpClienteLayout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtCalle))
                    .addGroup(jpClienteLayout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtNoExterior))
                    .addGroup(jpClienteLayout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtNoInterior))
                    .addGroup(jpClienteLayout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpClienteLayout.createSequentialGroup()
                                .addComponent(jtCp, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jlColonia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jpClienteLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlEstado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jpClienteLayout.setVerticalGroup(
            jpClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jtApPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jtApMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jtCalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jtNoExterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jtNoInterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jtCp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlColonia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlMunicipio)
                    .addComponent(jlEstado)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jtpCliente.addTab(org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jpCliente.TabConstraints.tabTitle"), jpCliente); // NOI18N

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Fecha", "Atendio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class
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
        jTable2.setPreferredSize(new java.awt.Dimension(300, 64));
        jTable2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable2.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTable2.getColumnModel().getColumn(0).setHeaderValue(org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jTable2.columnModel.title0")); // NOI18N
            jTable2.getColumnModel().getColumn(1).setPreferredWidth(250);
            jTable2.getColumnModel().getColumn(1).setHeaderValue(org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jTable2.columnModel.title1")); // NOI18N
        }

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jPanel1.border.title"), javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION)); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jPanel2.border.title"))); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel11, org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jLabel11.text")); // NOI18N
        jLabel11.setPreferredSize(new java.awt.Dimension(130, 15));

        jTextField9.setText(org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jTextField9.text")); // NOI18N
        jTextField9.setPreferredSize(new java.awt.Dimension(200, 19));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel20, org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jLabel20.text")); // NOI18N
        jLabel20.setPreferredSize(new java.awt.Dimension(130, 15));

        jTextField10.setText(org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jTextField10.text")); // NOI18N
        jTextField10.setPreferredSize(new java.awt.Dimension(200, 19));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel21, org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jLabel21.text")); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(130, 15));

        jFormattedTextField6.setText(org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jFormattedTextField6.text")); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jFormattedTextField6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jLabel6.text")); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(80, 15));

        jDateChooser1.setDate(new java.util.Date());
        jDateChooser1.setDateFormatString(org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jDateChooser1.dateFormatString")); // NOI18N
        jDateChooser1.setPreferredSize(new java.awt.Dimension(150, 19));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jLabel7.text")); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(80, 15));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.setPreferredSize(new java.awt.Dimension(150, 24));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jLabel8.text")); // NOI18N

        jFormattedTextField4.setText(org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jFormattedTextField4.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel9, org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jLabel9.text")); // NOI18N

        jFormattedTextField5.setText(org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jFormattedTextField5.text")); // NOI18N
        jFormattedTextField5.setPreferredSize(new java.awt.Dimension(150, 19));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel10, org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jLabel10.text")); // NOI18N

        jTextField8.setText(org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jTextField8.text")); // NOI18N
        jTextField8.setPreferredSize(new java.awt.Dimension(150, 19));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jPanel5.border.title"))); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel29, org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jLabel29.text")); // NOI18N
        jLabel29.setPreferredSize(new java.awt.Dimension(130, 15));

        jTextField13.setText(org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jTextField13.text")); // NOI18N
        jTextField13.setPreferredSize(new java.awt.Dimension(200, 19));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel30, org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jLabel30.text")); // NOI18N
        jLabel30.setPreferredSize(new java.awt.Dimension(130, 15));

        jTextField14.setText(org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jTextField14.text")); // NOI18N
        jTextField14.setPreferredSize(new java.awt.Dimension(200, 19));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel31, org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jLabel31.text")); // NOI18N
        jLabel31.setPreferredSize(new java.awt.Dimension(130, 15));

        jFormattedTextField12.setText(org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jFormattedTextField12.text")); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jFormattedTextField12))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jFormattedTextField4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jFormattedTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jFormattedTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jFormattedTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        org.openide.awt.Mnemonics.setLocalizedText(jButton2, org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jButton2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jButton3, org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jButton3.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel22, org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jLabel22.text")); // NOI18N

        jScrollPane3.setViewportView(jTextPane1);

        javax.swing.GroupLayout jpCardexLayout = new javax.swing.GroupLayout(jpCardex);
        jpCardex.setLayout(jpCardexLayout);
        jpCardexLayout.setHorizontalGroup(
            jpCardexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCardexLayout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(jpCardexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 784, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpCardexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel22)
                        .addGroup(jpCardexLayout.createSequentialGroup()
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addGroup(jpCardexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jpCardexLayout.setVerticalGroup(
            jpCardexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCardexLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpCardexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpCardexLayout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jtpCliente.addTab(org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jpCardex.TabConstraints.tabTitle"), jpCardex); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtpCliente)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtpCliente)
        );
    }// </editor-fold>//GEN-END:initComponents

    public static synchronized VisorClienteTopComponent findInstance() {
        TopComponent win = WindowManager.getDefault().findTopComponent(PREFERRED_ID);
        if (win == null) {
            Logger.getLogger(VisorClienteTopComponent.class.getName()).warning(
                    "Cannot find " + PREFERRED_ID + " component. It will not be located properly in the window system.");
            return getDefault();
        }
        if (win instanceof VisorClienteTopComponent) {
            return (VisorClienteTopComponent) win;
        }
        Logger.getLogger(VisorClienteTopComponent.class.getName()).warning(
                "There seem to be multiple components with the '" + PREFERRED_ID
                + "' ID. That is a potential source of errors and unexpected behavior.");
        return getDefault();
    }

    public static synchronized VisorClienteTopComponent getDefault() {
        if (instance == null) {
            instance = new VisorClienteTopComponent();
        }
        return instance;
    }

    private void jbAgregarProblemaMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAgregarProblemaMedicoActionPerformed
        final HistoriaClinicaForm clinicaForm = new HistoriaClinicaForm(this.cliente.getIdCliente());
        JButton ok = new JButton();
        ok.setText("Guardar");
        JButton cancel = new JButton();
        cancel.setText("Cancelar");

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                GcliInfProblemaMedico objeto = (GcliInfProblemaMedico) clinicaForm.getJcProblemasMedicos().getModel().getSelectedItem();
                EntityManager entityManager = EntityManagerFactory.getEntityManager();

                entityManager.getTransaction().begin();
                GcliHistoriaClinica historiaClinica = new GcliHistoriaClinica();
                GcliHistoriaClinicaPK pk = new GcliHistoriaClinicaPK(cliente.getIdCliente(), objeto.getIdProblema());
                historiaClinica.setGcliHistoriaClinicaPK(pk);
                historiaClinica.setObservaciones(clinicaForm.getJtaObservaciones().getText());
                entityManager.merge(historiaClinica);
                entityManager.flush();
                entityManager.getTransaction().commit();
                recargarTabla();
            }
        });

        NotifyDescriptor nd = new NotifyDescriptor.Confirmation(clinicaForm, "Historia Clinica");
        nd.setOptions(new Object[]{ok, cancel});
        DialogDisplayer.getDefault().notifyLater(nd);
    }//GEN-LAST:event_jbAgregarProblemaMedicoActionPerformed

    private void jtCpFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtCpFocusLost
        // TODO add your handling code here:
        if (FuncionesValidacion.esValido(jtCp.getText())) {
            EntityManager entityManager = EntityManagerFactory.getEntityManager();
            TypedQuery<GcliInfCodigoPostal> query = entityManager.createNamedQuery("GcliInfCodigoPostal.findByCodigoPostal", GcliInfCodigoPostal.class);
            query.setParameter("codigoPostal", jtCp.getText());
            try {
                cp = query.getSingleResult();
            } catch (NoResultException e) {
                cp = null;
            }
            if (cp != null) {
                String colonia = "";
                if (cp.getGcliInfAsentamientoList() != null) {
                    for (GcliInfAsentamiento asentamiento : cp.getGcliInfAsentamientoList()) {
                        colonia = asentamiento.getCodigoTipoAsentamiento().getNombreTipoAsentamiento() + ": " + asentamiento.getNombreAsentamiento();
                        break;
                    }
                }
                jlColonia.setText(colonia);
                jlMunicipio.setText(cp.getGcliInfMunicipio().getNombreMunicipio());
                jlEstado.setText(cp.getGcliInfMunicipio().getGcliInfEstado().getNombreEstado());
            } else {
                JOptionPane.showMessageDialog(null, "Codigo Postal no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                resetDatosCp();
            }
        } else {
            JOptionPane.showMessageDialog(null, "El Codigo Postal no puede estar vacio o nulo.", "Error", JOptionPane.ERROR_MESSAGE);
            resetDatosCp();
        }
    }//GEN-LAST:event_jtCpFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jComboBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JFormattedTextField jFormattedTextField12;
    private javax.swing.JFormattedTextField jFormattedTextField4;
    private javax.swing.JFormattedTextField jFormattedTextField5;
    private javax.swing.JFormattedTextField jFormattedTextField6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JButton jbAgregarProblemaMedico;
    private javax.swing.JLabel jlColonia;
    private javax.swing.JLabel jlEstado;
    private javax.swing.JLabel jlMunicipio;
    private javax.swing.JPanel jpCardex;
    private javax.swing.JPanel jpCliente;
    private javax.swing.JTextField jtApMaterno;
    private javax.swing.JTextField jtApPaterno;
    private javax.swing.JTextField jtCalle;
    private javax.swing.JFormattedTextField jtCelular;
    private javax.swing.JTextField jtCp;
    private javax.swing.JFormattedTextField jtEmail;
    private javax.swing.JTextField jtNoExterior;
    private javax.swing.JTextField jtNoInterior;
    private javax.swing.JTextField jtNombre;
    private javax.swing.JTable jtProblemasMedicos;
    private javax.swing.JFormattedTextField jtTelefono;
    private javax.swing.JTabbedPane jtpCliente;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
        RequestProcessor.getDefault().post(new Runnable() {
            @Override
            public void run() {
                readCustomer();
            }
        });
    }

    private void readCustomer() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TopComponent tc = WindowManager.getDefault().findTopComponent("ExploradorClientesTopComponent");
                if (tc == null) {
                    JOptionPane.showConfirmDialog(null, "Top Component no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                result = tc.getLookup().lookupResult(GcliCliente.class);
                result.addLookupListener(VisorClienteTopComponent.this);
                resultChanged(new LookupEvent(result));
            }
        });
    }

    @Override
    public void componentClosed() {
        result.removeLookupListener(this);
        result = null;
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    public void fire(boolean modified) {
        if (modified) {
            //If the text is modified,
            //we add SaveCookie impl to Lookup:
            content.add(impl);
        } else {
            //Otherwise, we remove the SaveCookie impl from the lookup:
            content.remove(impl);
        }
    }

    void resetFields() {
        Logger.getLogger(VisorClienteTopComponent.class.getName()).warning("se limpian los fields");
        jtNombre.setText("");
        jtApPaterno.setText("");
        jtApMaterno.setText("");
        jtTelefono.setText("");
        jtCelular.setText("");
        jtEmail.setText("");
        jtCalle.setText("");
        jtNoExterior.setText("");
        jtNoInterior.setText("");
        jtCp.setText("");
        ((MyModel) jtProblemasMedicos.getModel()).clear();
        resetDatosCp();
        jbAgregarProblemaMedico.setEnabled(false);
        jtpCliente.setSelectedIndex(0);
        jtpCliente.remove(jpCardex);
        jtNombre.requestFocus();
        cliente = null;
    }

    @Override
    public void resultChanged(LookupEvent lookupEvent) {
        Lookup.Result r = (Lookup.Result) lookupEvent.getSource();
        Collection<GcliCliente> coll = r.allInstances();
        if (!coll.isEmpty()) {
            for (GcliCliente gc : coll) {
                this.cliente = gc;
                jtNombre.setText(gc.getIdPersona().getNombre());
                jtApPaterno.setText(gc.getIdPersona().getApellidoPaterno());
                jtApMaterno.setText(gc.getIdPersona().getApellidoMaterno());
                jtTelefono.setText(gc.getIdPersona().getTelefonoFijo() != null ? gc.getIdPersona().getTelefonoFijo().toString() : null);
                jtCelular.setText(gc.getIdPersona().getTelefonoCelular() != null ? gc.getIdPersona().getTelefonoCelular().toString() : null);
                jtEmail.setText(gc.getIdPersona().getCorreoElectronico());
                jtCalle.setText(gc.getIdPersona().getIdDomicilio().getCalle());
                jtNoExterior.setText(gc.getIdPersona().getIdDomicilio().getNumeroExterior());
                jtNoInterior.setText(gc.getIdPersona().getIdDomicilio().getNumeroInterior());
                jtCp.setText(gc.getIdPersona().getIdDomicilio().getCodigoPostal().getCodigoPostal());
                //Colocamos los datos del codigo postal
                String colonia = "";
                if (gc.getIdPersona().getIdDomicilio().getCodigoPostal().getGcliInfAsentamientoList() != null) {
                    for (GcliInfAsentamiento asentamiento : gc.getIdPersona().getIdDomicilio().getCodigoPostal().getGcliInfAsentamientoList()) {
                        colonia = asentamiento.getCodigoTipoAsentamiento().getNombreTipoAsentamiento() + ": " + asentamiento.getNombreAsentamiento();
                        break;
                    }
                }
                jlColonia.setText(colonia);
                jlMunicipio.setText(gc.getIdPersona().getIdDomicilio().getCodigoPostal().getGcliInfMunicipio().getNombreMunicipio());
                jlEstado.setText(gc.getIdPersona().getIdDomicilio().getCodigoPostal().getGcliInfMunicipio().getGcliInfEstado().getNombreEstado());
                jbAgregarProblemaMedico.setEnabled(true);
                //Se recargan los problemas medicos
                recargarTabla();
                //Se cargan los datos del cardex
                jtpCliente.addTab(org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jpCardex.TabConstraints.tabTitle"), jpCardex);
                recargaCardex();
            }
        } else {
            resetFields();
        }
    }

    private void resetDatosCp() {
        jlColonia.setText("");
        jlMunicipio.setText("");
        jlEstado.setText("");
    }

    private void recargarTabla() {
        EntityManager em = EntityManagerFactory.getEntityManager();
        final TypedQuery<GcliHistoriaClinica> query = em.createNamedQuery("GcliHistoriaClinica.findByIdCliente", GcliHistoriaClinica.class);
        query.setParameter("idCliente", cliente.getIdCliente());
        System.out.println("Inicia" + new Date());
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                System.out.println("Inicia:RUN:" + new Date());
                @SuppressWarnings("unchecked")
                List<GcliHistoriaClinica> problemasMedicos = query.getResultList();
                MyModel modelo = (MyModel) jtProblemasMedicos.getModel();
                if (problemasMedicos != null && !problemasMedicos.isEmpty()) {
                    modelo.addRows(problemasMedicos);
                } else {
                    modelo.clear();
                }
                System.out.println("Fin:RUN:" + new Date());
            }
        });
        System.out.println("fin" + new Date());
    }

    private void recargaCardex() {
        
    }

    private class MyDocumentListener implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent arg0) {
            fire(true);
        }

        @Override
        public void removeUpdate(DocumentEvent arg0) {
            fire(true);
        }

        @Override
        public void changedUpdate(DocumentEvent arg0) {
            fire(true);
        }

    }

    private class SaveCookieImpl implements SaveCookie {

        @Override
        public void save() throws IOException {

            if (validaGuardado()) {
                NotifyDescriptor.Confirmation message = new NotifyDescriptor.Confirmation("Desea guardar al cliente \""
                        + jtNombre.getText() + " " + jtApPaterno.getText() + " " + jtApMaterno.getText(),
                        NotifyDescriptor.OK_CANCEL_OPTION,
                        NotifyDescriptor.QUESTION_MESSAGE);

                Object result = DialogDisplayer.getDefault().notify(message);

                if (NotifyDescriptor.YES_OPTION.equals(result)) {
                    fire(false);
                    EntityManager entityManager = EntityManagerFactory.getEntityManager();
                    entityManager.getTransaction().begin();
                    if (cliente != null && cliente.getIdCliente() != null) {
                        GcliCliente c = entityManager.find(GcliCliente.class, cliente.getIdCliente());
                        c.getIdPersona().setNombre(jtNombre.getText());
                        c.getIdPersona().setApellidoPaterno(jtApPaterno.getText());
                        c.getIdPersona().setApellidoMaterno(jtApMaterno.getText());
                        c.getIdPersona().setTelefonoFijo(jtTelefono.getValue() != null ? new BigInteger(jtTelefono.getValue().toString()) : null);
                        c.getIdPersona().setTelefonoCelular(jtCelular.getValue() != null ? new BigInteger(jtCelular.getValue().toString()) : null);
                        c.getIdPersona().setCorreoElectronico(jtEmail.getText());
                        c.getIdPersona().getIdDomicilio().setCalle(jtCalle.getText());
                        c.getIdPersona().getIdDomicilio().setNumeroExterior(jtNoExterior.getText());
                        c.getIdPersona().getIdDomicilio().setNumeroInterior(jtNoInterior.getText());
                        c.getIdPersona().getIdDomicilio().setCodigoPostal(cp);
                        entityManager.getTransaction().commit();
                    } else {
                        GcliCliente c = new GcliCliente();
                        GcliInfPersona persona = new GcliInfPersona();
                        persona.setNombre(jtNombre.getText());
                        persona.setApellidoPaterno(jtApPaterno.getText());
                        persona.setApellidoMaterno(jtApMaterno.getText());
                        persona.setCorreoElectronico(jtEmail.getText());
                        persona.setTelefonoFijo(jtTelefono.getValue() != null ? new BigInteger(FuncionesCadena.limpiaCadenaTelefono(jtTelefono.getValue().toString())) : null);
                        persona.setTelefonoCelular(jtCelular.getValue() != null ? new BigInteger(FuncionesCadena.limpiaCadenaTelefono(jtCelular.getValue().toString())) : null);
                        GcliInfDomicilio domicilio = new GcliInfDomicilio();
                        domicilio.setCalle(jtCalle.getText());
                        domicilio.setNumeroExterior(jtNoExterior.getText());
                        domicilio.setNumeroInterior(jtNoInterior.getText());
                        domicilio.setCodigoPostal(cp);
                        persona.setIdDomicilio(domicilio);
                        c.setIdPersona(persona);
                        cliente = entityManager.merge(c);
                        entityManager.getTransaction().commit();
                        jbAgregarProblemaMedico.setEnabled(true);
                        jtpCliente.addTab(org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jpCardex.TabConstraints.tabTitle"), jpCardex);
                    }
                    ExploradorClientesTopComponent.refreshNode();
                }
            }
        }

        private boolean validaGuardado() {
            if (!FuncionesValidacion.esValido(jtNombre.getText())) {
                JOptionPane.showMessageDialog(null, "Nombre no puede ser nulo o vacio", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if (!FuncionesValidacion.esValido(jtApPaterno.getText())) {
                JOptionPane.showMessageDialog(null, "Apellido paterno no puede ser nulo o vacio", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if (!FuncionesValidacion.esValido(jtCalle.getText())) {
                JOptionPane.showMessageDialog(null, "Calle no puede ser nulo o vacio", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if (!FuncionesValidacion.esValido(jtNoExterior.getText())) {
                JOptionPane.showMessageDialog(null, "Número exterior no puede ser nulo o vacio", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if (!FuncionesValidacion.esValido(jtCp.getText())) {
                JOptionPane.showMessageDialog(null, "Código postal no puede ser nulo o vacio", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            return true;
        }
    }

    private class MyModel extends AbstractTableModel implements Serializable {

        List<GcliHistoriaClinica> historiaClinica = new ArrayList<GcliHistoriaClinica>();

        public void addRows(List<GcliHistoriaClinica> historiaClinica) {
            this.historiaClinica.clear();
            this.historiaClinica.addAll(historiaClinica);
            fireTableRowsInserted(0, this.historiaClinica.size());
        }

        public void clear() {
            this.historiaClinica.clear();
            fireTableRowsInserted(0, this.historiaClinica.size());
        }

        @Override
        public String getColumnName(int column) {
            switch (column) {
                case 0:
                    return org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jTable1.columnModel.title0");
                case 1:
                    return org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jTable1.columnModel.title1");
                case 2:
                    return org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jTable1.columnModel.title2");
            }
            return "";
        }

        @Override
        public int getRowCount() {
            return historiaClinica.size();
        }

        @Override
        public int getColumnCount() {
            return 3;
        }

        private GcliHistoriaClinica getValueAt(int index) {
            if (index < this.historiaClinica.size()) {
                return this.historiaClinica.get(index);
            }
            return null;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if (rowIndex < this.historiaClinica.size()) {
                switch (columnIndex) {
                    case 0:
                        return "";
                    case 1:
                        return this.historiaClinica.get(rowIndex).getGcliInfProblemaMedico().getDescProblema();
                    case 2:
                        return this.historiaClinica.get(rowIndex).getObservaciones();
                }
            }
            return null;
        }

    }
}
