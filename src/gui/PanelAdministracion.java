package gui;

import excepciones.PalabraDuplicadaException;
import excepciones.PalabraInvalidaException;
import gui.sonido.EfectoSonido;
import java.awt.Color;
import java.util.List;
import javax.swing.DefaultListModel;
import modelo.AdministradorPalabras;

public class PanelAdministracion extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;

    private VentanaPrincipal ventana;
    private AdministradorPalabras administrador;
    private final DefaultListModel<String> modeloLista;

    public PanelAdministracion() {
        this(null, null);
    }

    public PanelAdministracion(VentanaPrincipal ventana, AdministradorPalabras administrador) {
        this.ventana = ventana;
        this.administrador = administrador;
        this.modeloLista = new DefaultListModel<>();
        initComponents();
        listaPalabras.setModel(modeloLista);
        listaPalabras.setFixedCellHeight(28);
        if (this.administrador != null) {
            refrescarVista();
        }
    }

    public void configurar(VentanaPrincipal ventana, AdministradorPalabras administrador) {
        this.ventana = ventana;
        this.administrador = administrador;
        refrescarVista();
    }

    public void refrescarVista() {
        if (administrador == null) {
            return;
        }
        modeloLista.clear();
        List<String> palabras = administrador.getPalabras();
        for (int i = 0; i < palabras.size(); i++) {
            modeloLista.addElement(String.format("%2d.  %s", i + 1, palabras.get(i)));
        }
        lblCantidad.setText("Total almacenadas: " + administrador.cantidad() + " palabras");
        txtNuevaPalabra.setText("");
        lblMensajeEstado.setForeground(new Color(71, 85, 105));
        lblMensajeEstado.setText("Ingrese una palabra (se normaliza automáticamente a mayúsculas sin tildes).");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        lblCantidad = new javax.swing.JLabel();
        panelCentro = new javax.swing.JPanel();
        lblNueva = new javax.swing.JLabel();
        txtNuevaPalabra = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        scrollLista = new javax.swing.JScrollPane();
        listaPalabras = new javax.swing.JList<>();
        lblMensajeEstado = new javax.swing.JLabel();
        btnVolver = new javax.swing.JButton();

        setBackground(new java.awt.Color(243, 246, 250));

        lblTitulo.setFont(new java.awt.Font("SansSerif", 1, 22)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(30, 41, 59));
        lblTitulo.setText("Administración de Palabras (Modalidad al Azar)");

        lblCantidad.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblCantidad.setForeground(new java.awt.Color(37, 99, 235));
        lblCantidad.setText("Total almacenadas: 12 palabras");

        panelCentro.setBackground(new java.awt.Color(255, 255, 255));
        panelCentro.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(218, 224, 233), 1, true));

        lblNueva.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblNueva.setText("Nueva palabra (3 a 15 letras, A–Z o Ñ):");

        txtNuevaPalabra.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtNuevaPalabra.addActionListener(this::txtNuevaPalabraActionPerformed);

        btnAgregar.setBackground(new java.awt.Color(37, 99, 235));
        btnAgregar.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar.setText("Agregar Palabra");
        btnAgregar.addActionListener(this::btnAgregarActionPerformed);

        scrollLista.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(203, 213, 225)), "Palabras Registradas (Copia de Solo Lectura)"));

        listaPalabras.setFont(new java.awt.Font("Monospaced", 1, 15)); // NOI18N
        scrollLista.setViewportView(listaPalabras);

        lblMensajeEstado.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblMensajeEstado.setForeground(new java.awt.Color(71, 85, 105));
        lblMensajeEstado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMensajeEstado.setText("Ingrese una palabra para agregarla al diccionario.");

        javax.swing.GroupLayout panelCentroLayout = new javax.swing.GroupLayout(panelCentro);
        panelCentro.setLayout(panelCentroLayout);
        panelCentroLayout.setHorizontalGroup(
            panelCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCentroLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollLista)
                    .addComponent(lblMensajeEstado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelCentroLayout.createSequentialGroup()
                        .addComponent(lblNueva)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNuevaPalabra, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
        panelCentroLayout.setVerticalGroup(
            panelCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCentroLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(panelCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNueva)
                    .addComponent(txtNuevaPalabra, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollLista, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblMensajeEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        btnVolver.setBackground(new java.awt.Color(71, 85, 105));
        btnVolver.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnVolver.setForeground(new java.awt.Color(255, 255, 255));
        btnVolver.setText("Volver al Menú");
        btnVolver.addActionListener(this::btnVolverActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelCentro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTitulo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblCantidad))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitulo)
                    .addComponent(lblCantidad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelCentro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtNuevaPalabraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNuevaPalabraActionPerformed
        intentarAgregarPalabra();
    }//GEN-LAST:event_txtNuevaPalabraActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        intentarAgregarPalabra();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        if (ventana != null) {
            ventana.mostrarMenu();
        }
    }//GEN-LAST:event_btnVolverActionPerformed

    private void intentarAgregarPalabra() {
        if (administrador == null || ventana == null) {
            return;
        }
        String entrada = txtNuevaPalabra.getText();
        try {
            administrador.agregar(entrada);
            refrescarVista();
            lblMensajeEstado.setForeground(new Color(22, 163, 74));
            lblMensajeEstado.setText("¡Palabra agregada exitosamente! Total actual: " + administrador.cantidad() + ".");
            ventana.getGestorSonido().reproducir(EfectoSonido.ACIERTO);
            txtNuevaPalabra.requestFocusInWindow();
        } catch (PalabraInvalidaException ex) {
            lblMensajeEstado.setForeground(new Color(220, 38, 38));
            lblMensajeEstado.setText(ex.getMessage());
            ventana.getGestorSonido().reproducir(EfectoSonido.ALERTA);
            txtNuevaPalabra.selectAll();
            txtNuevaPalabra.requestFocusInWindow();
        } catch (PalabraDuplicadaException ex) {
            lblMensajeEstado.setForeground(new Color(217, 119, 6));
            lblMensajeEstado.setText(ex.getMessage());
            ventana.getGestorSonido().reproducir(EfectoSonido.ALERTA);
            txtNuevaPalabra.selectAll();
            txtNuevaPalabra.requestFocusInWindow();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblMensajeEstado;
    private javax.swing.JLabel lblNueva;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JList<String> listaPalabras;
    private javax.swing.JPanel panelCentro;
    private javax.swing.JScrollPane scrollLista;
    private javax.swing.JTextField txtNuevaPalabra;
    // End of variables declaration//GEN-END:variables
}
