/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sig.visorcliente;

import com.sig.utilerias.FuncionesValidacion;
import com.sig.utilerias.entity.EntityManagerFactory;
import com.sig.visorcliente.form.EditorObservacionesForm;
import com.sig.visorcliente.form.HistoriaClinicaForm;
import com.sig.visorcliente.table.TextAreaEditor;
import com.six.dto.GcliCardex;
import com.six.dto.GcliCardexPK;
import com.six.dto.GcliCliente;
import com.six.dto.GcliHistoriaClinica;
import com.six.dto.GcliHistoriaClinicaPK;
import com.six.dto.GcliInfAsentamiento;
import com.six.dto.GcliInfCodigoPostal;
import com.six.dto.GcliInfDomicilio;
import com.six.dto.GcliInfPersona;
import com.six.dto.GcliInfProblemaMedico;
import com.six.dto.GcliUsuario;
import com.six.expclientes.ExploradorClientesTopComponent;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.swing.AbstractListModel;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.MutableComboBoxModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.cookies.SaveCookie;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.util.NbBundle.Messages;
import org.openide.util.RequestProcessor;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//com.sig.visorcliente//VisorCliente//EN",
autostore = false)
@TopComponent.Description(
        preferredID = "VisorClienteTopComponent",
//iconBase="SET/PATH/TO/ICON/HERE", 
persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = true)
@ActionID(category = "Window", id = "com.sig.visorcliente.VisorClienteTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_VisorClienteAction",
preferredID = "VisorClienteTopComponent")
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
    //private GcliInfCodigoPostal cp;
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

        this.jtProblemasMedicos.setRowHeight(100);

        this.jtProblemasMedicos.getColumnModel().getColumn(0).setPreferredWidth(50);
        this.jtProblemasMedicos.getColumnModel().getColumn(0).setCellRenderer(new ButtonRenderer());
        this.jtProblemasMedicos.getColumnModel().getColumn(0).setCellEditor(new ButtonEditor());

        this.jtProblemasMedicos.getColumnModel().getColumn(1).setPreferredWidth(400);

        this.jtProblemasMedicos.getColumnModel().getColumn(2).setPreferredWidth(800);
        this.jtProblemasMedicos.getColumnModel().getColumn(2).setCellEditor(new TextAreaEditor());
        this.jtProblemasMedicos.getColumnModel().getColumn(2).setCellRenderer(new com.sig.visorcliente.table.TextAreaRenderer());

        this.jtProblemasMedicos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    int column = target.getSelectedColumn();
                    //JOptionPane.showMessageDialog(null, "row::"+row+" column::"+column);
                    if (column == 1 || column == 2) {
                        MyModel modelo = (MyModel) jtProblemasMedicos.getModel();
                        final GcliHistoriaClinica historia = modelo.getValueAt(row);
                        final EditorObservacionesForm observacionesForm = new EditorObservacionesForm(historia.getGcliHistoriaClinicaPK());
                        JButton ok = new JButton();
                        ok.setText("Guardar");
                        JButton cancel = new JButton();
                        cancel.setText("Cancelar");

                        ok.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent arg0) {
                                EntityManager entityManager = EntityManagerFactory.getEntityManager();

                                entityManager.getTransaction().begin();
                                GcliHistoriaClinica historiaClinica = entityManager.find(GcliHistoriaClinica.class, historia.getGcliHistoriaClinicaPK());

                                historiaClinica.setObservaciones(observacionesForm.getJtaObservaciones().getText());
                                entityManager.merge(historiaClinica);
                                entityManager.flush();
                                entityManager.getTransaction().commit();
                                recargarTabla();
                            }
                        });

                        NotifyDescriptor nd = new NotifyDescriptor.Confirmation(observacionesForm, "Historia Clinica");
                        nd.setOptions(new Object[]{ok, cancel});
                        DialogDisplayer.getDefault().notifyLater(nd);
                    }
                }
            }
        });

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
        jpContenido = new javax.swing.JPanel();
        jpDetalleCardex = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jtGraduacionOD = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jFormattedTextField6 = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jdFechaCardex = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jcAtendio = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jFormattedTextField4 = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        jFormattedTextField5 = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jtGraduacionOI = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jFormattedTextField12 = new javax.swing.JFormattedTextField();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtCardex = new javax.swing.JTable();
        jbCardex = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jLabel1.text")); // NOI18N

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jPanel3.border.title"), 2, 0)); // NOI18N

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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 905, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jbAgregarProblemaMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jbAgregarProblemaMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
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
        jtTelefono.setPreferredSize(new java.awt.Dimension(100, 23));

        try {
            jtCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(###)-##-########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jtCelular.setPreferredSize(new java.awt.Dimension(100, 23));

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
                        .addGap(107, 107, 107)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlEstado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jpClienteLayout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtCp, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jlColonia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpClienteLayout.setVerticalGroup(
            jpClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpClienteLayout.createSequentialGroup()
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
                .addGroup(jpClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpClienteLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jlColonia))
                    .addGroup(jpClienteLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(jtCp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlMunicipio)
                    .addComponent(jlEstado)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jtpCliente.addTab(org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jpCliente.TabConstraints.tabTitle"), jpCliente); // NOI18N

        jpCardex.setPreferredSize(new java.awt.Dimension(600, 600));

        jpContenido.setPreferredSize(new java.awt.Dimension(600, 600));

        jpDetalleCardex.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jpDetalleCardex.border.title"), 2, 0, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(0, 0, 0))); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jPanel2.border.title"))); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(730, 100));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel11, org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jLabel11.text")); // NOI18N
        jLabel11.setPreferredSize(new java.awt.Dimension(130, 15));

        jtGraduacionOD.setText(org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jtGraduacionOD.text")); // NOI18N
        jtGraduacionOD.setPreferredSize(new java.awt.Dimension(200, 22));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel20, org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jLabel20.text")); // NOI18N
        jLabel20.setPreferredSize(new java.awt.Dimension(130, 15));

        jTextField10.setText(org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jTextField10.text")); // NOI18N
        jTextField10.setPreferredSize(new java.awt.Dimension(200, 22));

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
                        .addComponent(jtGraduacionOD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jFormattedTextField6))
                .addGap(56, 56, 56))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtGraduacionOD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jLabel6.text")); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(80, 15));

        jdFechaCardex.setDate(new java.util.Date());
        jdFechaCardex.setDateFormatString(org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jdFechaCardex.dateFormatString")); // NOI18N
        jdFechaCardex.setPreferredSize(new java.awt.Dimension(150, 22));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jLabel7.text")); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(80, 15));

        jcAtendio.setModel(new VisorClienteTopComponent.MyModelJcUsuario());
        jcAtendio.setPreferredSize(new java.awt.Dimension(150, 24));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jLabel8.text")); // NOI18N

        jFormattedTextField4.setText(org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jFormattedTextField4.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel9, org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jLabel9.text")); // NOI18N

        jFormattedTextField5.setText(org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jFormattedTextField5.text")); // NOI18N
        jFormattedTextField5.setPreferredSize(new java.awt.Dimension(150, 22));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel10, org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jLabel10.text")); // NOI18N

        jTextField8.setText(org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jTextField8.text")); // NOI18N
        jTextField8.setPreferredSize(new java.awt.Dimension(150, 22));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jPanel5.border.title"))); // NOI18N
        jPanel5.setPreferredSize(new java.awt.Dimension(718, 100));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel29, org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jLabel29.text")); // NOI18N
        jLabel29.setPreferredSize(new java.awt.Dimension(130, 15));

        jtGraduacionOI.setText(org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jtGraduacionOI.text")); // NOI18N
        jtGraduacionOI.setPreferredSize(new java.awt.Dimension(200, 22));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel30, org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jLabel30.text")); // NOI18N
        jLabel30.setPreferredSize(new java.awt.Dimension(130, 15));

        jTextField14.setText(org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jTextField14.text")); // NOI18N
        jTextField14.setPreferredSize(new java.awt.Dimension(200, 22));

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
                        .addComponent(jtGraduacionOI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jFormattedTextField12))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtGraduacionOI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        org.openide.awt.Mnemonics.setLocalizedText(jLabel22, org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jLabel22.text")); // NOI18N

        jScrollPane3.setViewportView(jTextPane1);

        javax.swing.GroupLayout jpDetalleCardexLayout = new javax.swing.GroupLayout(jpDetalleCardex);
        jpDetalleCardex.setLayout(jpDetalleCardexLayout);
        jpDetalleCardexLayout.setHorizontalGroup(
            jpDetalleCardexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDetalleCardexLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpDetalleCardexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpDetalleCardexLayout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdFechaCardex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(165, 165, 165)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcAtendio, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jpDetalleCardexLayout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpDetalleCardexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 762, Short.MAX_VALUE)
                            .addGroup(jpDetalleCardexLayout.createSequentialGroup()
                                .addComponent(jFormattedTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jFormattedTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 762, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jpDetalleCardexLayout.createSequentialGroup()
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpDetalleCardexLayout.setVerticalGroup(
            jpDetalleCardexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDetalleCardexLayout.createSequentialGroup()
                .addGroup(jpDetalleCardexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpDetalleCardexLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jpDetalleCardexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jdFechaCardex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(jpDetalleCardexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)))
                    .addGroup(jpDetalleCardexLayout.createSequentialGroup()
                        .addGroup(jpDetalleCardexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcAtendio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpDetalleCardexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFormattedTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jpDetalleCardexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel9)
                                .addComponent(jLabel10)
                                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout jpContenidoLayout = new javax.swing.GroupLayout(jpContenido);
        jpContenido.setLayout(jpContenidoLayout);
        jpContenidoLayout.setHorizontalGroup(
            jpContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpContenidoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jpDetalleCardex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jpContenidoLayout.setVerticalGroup(
            jpContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpContenidoLayout.createSequentialGroup()
                .addComponent(jpDetalleCardex, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jtCardex.setModel(new VisorClienteTopComponent.MyModelCardex());
        jtCardex.setPreferredSize(new java.awt.Dimension(300, 64));
        jtCardex.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtCardex.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jtCardex);
        jtCardex.getColumnModel().getColumn(0).setPreferredWidth(50);
        jtCardex.getColumnModel().getColumn(0).setHeaderValue(org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jtCardex.columnModel.title0")); // NOI18N
        jtCardex.getColumnModel().getColumn(1).setPreferredWidth(250);
        jtCardex.getColumnModel().getColumn(1).setHeaderValue(org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jtCardex.columnModel.title1")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jbCardex, org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jbCardex.text")); // NOI18N
        jbCardex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCardexActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jButton2, org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jButton2.text")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbCardex, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jbCardex)
                        .addGap(3, 3, 3)
                        .addComponent(jButton2)
                        .addContainerGap(72, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout jpCardexLayout = new javax.swing.GroupLayout(jpCardex);
        jpCardex.setLayout(jpCardexLayout);
        jpCardexLayout.setHorizontalGroup(
            jpCardexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCardexLayout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(jpCardexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpContenido, javax.swing.GroupLayout.DEFAULT_SIZE, 893, Short.MAX_VALUE))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jpCardexLayout.setVerticalGroup(
            jpCardexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCardexLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpContenido, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE))
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
        ok.setEnabled(false);
        JButton cancel = new JButton();
        cancel.setText("Cancelar");


        EntityManager em = EntityManagerFactory.getEntityManager();

        TypedQuery<GcliCliente> queryCliente = em.createNamedQuery("GcliCliente.findByIdCliente", GcliCliente.class);
        queryCliente.setParameter("idCliente", cliente.getIdCliente());
        cliente = queryCliente.getSingleResult();
        StringBuilder builder = new StringBuilder("SELECT g FROM GcliInfProblemaMedico g");
        if (cliente.getGcliHistoriaClinicaList() != null && !cliente.getGcliHistoriaClinicaList().isEmpty()) {
            builder.append(" where g.idProblema not in(");
            int cont = 0;
            for (GcliHistoriaClinica historiaClinica : cliente.getGcliHistoriaClinicaList()) {
                builder.append(historiaClinica.getGcliInfProblemaMedico().getIdProblema());
                if (cont < (cliente.getGcliHistoriaClinicaList().size() - 1)) {
                    builder.append(",");
                }
                cont++;
            }
            builder.append(")");
        }
        TypedQuery<GcliInfProblemaMedico> query = em.createQuery(builder.toString(),
                GcliInfProblemaMedico.class);
        List<GcliInfProblemaMedico> problemas = query.getResultList();
        if (problemas.size() > 0) {
            ok.setEnabled(true);
        }
        fire(false);
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
            GcliInfCodigoPostal cp;
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

    private void jbCardexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCardexActionPerformed
        EntityManager em = EntityManagerFactory.getEntityManager();
        jpContenido.add(jpDetalleCardex);

        jdFechaCardex.requestFocus();
        jdFechaCardex.setDate(new Date());

        TypedQuery<GcliUsuario> queryCliente = em.createNamedQuery("GcliUsuario.findAll", GcliUsuario.class);
        List<GcliUsuario> usuarios = queryCliente.getResultList();
        ((MyModelJcUsuario) this.jcAtendio.getModel()).addElements(usuarios);
        jpContenido.repaint();
        //Listo para Guardar la información
        fire(true);

    }//GEN-LAST:event_jbCardexActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
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
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JButton jbAgregarProblemaMedico;
    private javax.swing.JButton jbCardex;
    private javax.swing.JComboBox jcAtendio;
    private com.toedter.calendar.JDateChooser jdFechaCardex;
    private javax.swing.JLabel jlColonia;
    private javax.swing.JLabel jlEstado;
    private javax.swing.JLabel jlMunicipio;
    private javax.swing.JPanel jpCardex;
    private javax.swing.JPanel jpCliente;
    private javax.swing.JPanel jpContenido;
    private javax.swing.JPanel jpDetalleCardex;
    private javax.swing.JTextField jtApMaterno;
    private javax.swing.JTextField jtApPaterno;
    private javax.swing.JTextField jtCalle;
    private javax.swing.JTable jtCardex;
    private javax.swing.JFormattedTextField jtCelular;
    private javax.swing.JTextField jtCp;
    private javax.swing.JFormattedTextField jtEmail;
    private javax.swing.JTextField jtGraduacionOD;
    private javax.swing.JTextField jtGraduacionOI;
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
        p.setProperty("version", "1.0");
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
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
                jtTelefono.setText(gc.getIdPersona().getTelefonoFijo());
                jtCelular.setText(gc.getIdPersona().getTelefonoCelular());
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
        EntityManager em = EntityManagerFactory.getEntityManager();
        final TypedQuery<GcliCardex> query = em.createNamedQuery("GcliCardex.findByIdCliente", GcliCardex.class);
        query.setParameter("idCliente", cliente.getIdCliente());

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                List<GcliCardex> resumenCardex = query.getResultList();
                MyModelCardex modelo = (MyModelCardex) jtCardex.getModel();
                if (resumenCardex != null && !resumenCardex.isEmpty()) {
                    modelo.addRows(resumenCardex);
                } else {
                    modelo.clear();
                    jpContenido.remove(jpDetalleCardex);
                }
            }
        });
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
            int tab = jtpCliente.getSelectedIndex();
            switch (tab) {
                case 0:
                    if (validaGuardado()) {
                        NotifyDescriptor.Confirmation message = new NotifyDescriptor.Confirmation("Desea guardar al cliente \""
                                + jtNombre.getText() + " " + jtApPaterno.getText() + " " + jtApMaterno.getText() + "\"",
                                NotifyDescriptor.OK_CANCEL_OPTION,
                                NotifyDescriptor.QUESTION_MESSAGE);

                        Object result = DialogDisplayer.getDefault().notify(message);

                        if (NotifyDescriptor.YES_OPTION.equals(result)) {
                            fire(false);
                            EntityManager entityManager = EntityManagerFactory.getEntityManager();
                            entityManager.getTransaction().begin();
                            GcliInfCodigoPostal cp = entityManager.find(GcliInfCodigoPostal.class, jtCp.getText());
                            if (cliente != null && cliente.getIdCliente() != null) {
                                GcliCliente c = entityManager.find(GcliCliente.class, cliente.getIdCliente());
                                c.getIdPersona().setNombre(jtNombre.getText());
                                c.getIdPersona().setApellidoPaterno(jtApPaterno.getText());
                                c.getIdPersona().setApellidoMaterno(jtApMaterno.getText());
                                c.getIdPersona().setTelefonoFijo(jtTelefono.getText());
                                c.getIdPersona().setTelefonoCelular(jtCelular.getText());
                                c.getIdPersona().setCorreoElectronico(jtEmail.getText());
                                c.getIdPersona().getIdDomicilio().setCalle(jtCalle.getText());
                                c.getIdPersona().getIdDomicilio().setNumeroExterior(jtNoExterior.getText());
                                c.getIdPersona().getIdDomicilio().setNumeroInterior(jtNoInterior.getText());
                                c.getIdPersona().getIdDomicilio().setCodigoPostal(cp);
                                entityManager.merge(c);
                                entityManager.getTransaction().commit();
                            } else {
                                GcliCliente c = new GcliCliente();
                                GcliInfPersona persona = new GcliInfPersona();
                                persona.setNombre(jtNombre.getText());
                                persona.setApellidoPaterno(jtApPaterno.getText());
                                persona.setApellidoMaterno(jtApMaterno.getText());
                                persona.setCorreoElectronico(jtEmail.getText());
                                persona.setTelefonoFijo(jtTelefono.getText());
                                persona.setTelefonoCelular(jtCelular.getText());
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
                                jtpCliente.addTab(org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class,
                                        "VisorClienteTopComponent.jpCardex.TabConstraints.tabTitle"), jpCardex);
                            }
                            ExploradorClientesTopComponent.refreshNode();
                        }
                    }
                    break;
                case 1:
                    if (validaGuardadoCardex()) {
                        NotifyDescriptor.Confirmation message = new NotifyDescriptor.Confirmation("Desea guardar el cardex del cliente: \""
                                + jtNombre.getText() + " " + jtApPaterno.getText() + " " + jtApMaterno.getText() + "\"",
                                NotifyDescriptor.OK_CANCEL_OPTION,
                                NotifyDescriptor.QUESTION_MESSAGE);

                        Object result = DialogDisplayer.getDefault().notify(message);
                        if (NotifyDescriptor.YES_OPTION.equals(result)) {
                            fire(false);
                            EntityManager entityManager = EntityManagerFactory.getEntityManager();
                            entityManager.getTransaction().begin();
                            cliente = entityManager.find(GcliCliente.class, cliente.getIdCliente());
                            List<GcliCardex> cardexs = cliente.getGcliCardexList();
                            GcliCardex cardex = new GcliCardex(new GcliCardexPK(cliente.getIdCliente(), cardexs.size()), jdFechaCardex.getDate(), false, jtGraduacionOD.getText(), jtGraduacionOI.getText());
                            cardexs.add(cardex);
                            entityManager.merge(cliente);
                            entityManager.getTransaction().commit();
                        }
                    }
                    break;
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

        private boolean validaGuardadoCardex() {
            if (!FuncionesValidacion.esValido(jtGraduacionOD.getText())) {
                JOptionPane.showMessageDialog(null, "Graduación del ojo derecho no puede ser nulo o vacio", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if (!FuncionesValidacion.esValido(jtGraduacionOI.getText())) {
                JOptionPane.showMessageDialog(null, "Graduación del ojo izquierdo no puede ser nulo o vacio", "Error", JOptionPane.ERROR_MESSAGE);
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
                        return this.historiaClinica.get(rowIndex).getGcliInfProblemaMedico();
                    case 1:
                        return this.historiaClinica.get(rowIndex).getGcliInfProblemaMedico().getDescProblema();
                    case 2:
                        return this.historiaClinica.get(rowIndex).getObservaciones();
                }
            }
            return null;
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            if (rowIndex < this.historiaClinica.size()) {
                switch (columnIndex) {
                    case 0:
                        return true;
                    case 1:
                        return false;
                    case 2:
                        return false;
                }
            }
            return super.isCellEditable(rowIndex, columnIndex);
        }
    }

    private class MyModelCardex extends AbstractTableModel implements Serializable {

        List<GcliCardex> resumenCadex = new ArrayList<GcliCardex>();

        public void addRows(List<GcliCardex> historiaClinica) {
            this.resumenCadex.clear();
            this.resumenCadex.addAll(historiaClinica);
            fireTableRowsInserted(0, this.resumenCadex.size());
        }

        public void clear() {
            this.resumenCadex.clear();
            fireTableRowsInserted(0, this.resumenCadex.size());
        }

        @Override
        public String getColumnName(int column) {
            switch (column) {
                case 0:
                    return org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jtCardex.columnModel.title0");
                case 1:
                    return org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jtCardex.columnModel.title1");
            }
            return "";
        }

        @Override
        public int getRowCount() {
            return resumenCadex.size();
        }

        @Override
        public int getColumnCount() {
            return 3;
        }

        private GcliCardex getValueAt(int index) {
            if (index < this.resumenCadex.size()) {
                return this.resumenCadex.get(index);
            }
            return null;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if (rowIndex < this.resumenCadex.size()) {
                switch (columnIndex) {
                    case 0:
                        return this.resumenCadex.get(rowIndex).getFecha();
                    case 1:
                        GcliInfPersona persona = this.resumenCadex.get(rowIndex).getIdUsuarioAtendio().getIdPersona();
                        return persona.getNombre() + " " + persona.getApellidoPaterno() + " " + persona.getApellidoMaterno();
                }
            }
            return null;
        }
    }

    private class MyModelJcUsuario extends AbstractListModel<GcliUsuario> implements MutableComboBoxModel<GcliUsuario>, Serializable {

        private final List<GcliUsuario> usuarios = new ArrayList<GcliUsuario>();
        private GcliUsuario gcliUsuario = null;

        public MyModelJcUsuario() {
        }

        public MyModelJcUsuario(List<GcliUsuario> usuarios) {
            this.usuarios.clear();
            this.usuarios.addAll(usuarios);
        }

        public void addElements(List<GcliUsuario> usuarios) {
            this.usuarios.clear();
            this.usuarios.addAll(usuarios);
        }

        @Override
        public int getSize() {
            return usuarios.size();
        }

        @Override
        public GcliUsuario getElementAt(int index) {
            return usuarios.get(index);
        }

        @Override
        public void addElement(GcliUsuario item) {
            this.usuarios.add(item);
        }

        @Override
        public void removeElement(Object obj) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void insertElementAt(GcliUsuario item, int index) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void removeElementAt(int index) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void setSelectedItem(Object anObject) {
            if ((gcliUsuario != null && gcliUsuario.getIdUsuario().intValue() != ((GcliUsuario) anObject).getIdUsuario().intValue())
                    || gcliUsuario == null && anObject != null) {
                gcliUsuario = (GcliUsuario) anObject;
                fireContentsChanged(this, -1, -1);
            }
        }

        @Override
        public Object getSelectedItem() {
            return gcliUsuario;
        }
    }

    class ButtonRenderer extends JButton implements TableCellRenderer {

        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                setForeground(table.getSelectionForeground());
                setBackground(table.getSelectionBackground());
            } else {
                setForeground(table.getForeground());
                setBackground(UIManager.getColor("Button.background"));
            }
            setToolTipText(org.openide.util.NbBundle.getMessage(VisorClienteTopComponent.class, "VisorClienteTopComponent.jtCardex.columnModel.title0"));
            setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sig/visorcliente/resources/eliminar.png")));
            return this;
        }
    }

    class TextAreaRenderer implements TableCellRenderer {

        private JScrollPane scrollPane;
        private JTextArea textArea;

        public TextAreaRenderer() {
            setOpaque(true);
            textArea = new JTextArea();
            scrollPane = new JScrollPane(textArea);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                setForeground(table.getSelectionForeground());
                setBackground(table.getSelectionBackground());
            } else {
                setForeground(table.getForeground());
                setBackground(UIManager.getColor("Button.background"));
            }
            textArea.setText((value == null) ? "" : value.toString());
            return scrollPane;
        }
    }

    class ButtonEditor extends DefaultCellEditor {

        protected JButton button;
        private boolean isPushed;
        private GcliInfProblemaMedico problemaMedico;

        public ButtonEditor() {
            super(new JCheckBox());
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            if (isSelected) {
                button.setForeground(table.getSelectionForeground());
                button.setBackground(table.getSelectionBackground());
            } else {
                button.setForeground(table.getForeground());
                button.setBackground(table.getBackground());
            }
            isPushed = true;
            problemaMedico = (GcliInfProblemaMedico) value;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            if (isPushed) {
                int resp = JOptionPane.showConfirmDialog(null, "Desea eliminar el problema médico: " + problemaMedico.getDescProblema(), "Eliminar Problema Médico", JOptionPane.YES_NO_OPTION);
                if (resp == JOptionPane.YES_OPTION) {
                    final EntityManager em = EntityManagerFactory.getEntityManager();

                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            em.getTransaction().begin();
                            GcliHistoriaClinicaPK gcliHistoriaClinicaPK = new GcliHistoriaClinicaPK(cliente.getIdCliente(), problemaMedico.getIdProblema());
                            GcliHistoriaClinica historia = em.find(GcliHistoriaClinica.class, gcliHistoriaClinicaPK);
                            em.remove(historia);
                            em.flush();
                            em.getTransaction().commit();
                            recargarTabla();
                        }
                    });
                }
            }
            isPushed = false;
            return "";
        }

        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }
    }
}
