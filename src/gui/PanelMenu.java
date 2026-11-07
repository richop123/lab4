package gui;

import excepciones.PalabraInvalidaException;
import gui.sonido.EfectoSonido;
import modelo.Modalidad;

public class PanelMenu extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;
    private VentanaPrincipal ventana;

    public PanelMenu() {
        this(null);
    }

    public PanelMenu(VentanaPrincipal ventana) {
        this.ventana = ventana;
        initComponents();
        btnAlAzar.setText(Modalidad.AL_AZAR.getTextoBoton());
        btnPalabraFija.setText(Modalidad.PALABRA_FIJA.getTextoBoton());
    }

    public void setVentana(VentanaPrincipal ventana) {
        this.ventana = ventana;
    }

    public void limpiarFormulario() {
        txtPalabraSecreta.setText("");
        lblMensajeError.setText(" ");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tarjetaCentral = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblSubtitulo = new javax.swing.JLabel();
        btnAlAzar = new javax.swing.JButton();
        panelFija = new javax.swing.JPanel();
        lblInstruccionFija = new javax.swing.JLabel();
        txtPalabraSecreta = new javax.swing.JPasswordField();
        btnPalabraFija = new javax.swing.JButton();
        btnAdministrar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        lblMensajeError = new javax.swing.JLabel();

        setBackground(new java.awt.Color(243, 246, 250));

        tarjetaCentral.setBackground(new java.awt.Color(255, 255, 255));
        tarjetaCentral.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(218, 224, 233), 1, true));

        lblTitulo.setFont(new java.awt.Font("SansSerif", 1, 28)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(30, 41, 59));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("JUEGO DEL AHORCADO");

        lblSubtitulo.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblSubtitulo.setForeground(new java.awt.Color(100, 116, 139));
        lblSubtitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSubtitulo.setText("Seleccione una modalidad");

        btnAlAzar.setBackground(new java.awt.Color(37, 99, 235));
        btnAlAzar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnAlAzar.setForeground(new java.awt.Color(255, 255, 255));
        btnAlAzar.setText("Jugar con Palabra al Azar");
        btnAlAzar.addActionListener(this::btnAlAzarActionPerformed);

        panelFija.setBackground(new java.awt.Color(248, 250, 252));
        panelFija.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(203, 213, 225)), "Modalidad Palabra Fija (2 Jugadores / Pruebas)"));

        lblInstruccionFija.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        lblInstruccionFija.setForeground(new java.awt.Color(51, 65, 85));
        lblInstruccionFija.setText("Palabra secreta (oculta al escribir):");

        txtPalabraSecreta.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        txtPalabraSecreta.addActionListener(this::txtPalabraSecretaActionPerformed);

        btnPalabraFija.setBackground(new java.awt.Color(16, 185, 129));
        btnPalabraFija.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnPalabraFija.setForeground(new java.awt.Color(255, 255, 255));
        btnPalabraFija.setText("Jugar con Palabra Fija");
        btnPalabraFija.addActionListener(this::btnPalabraFijaActionPerformed);

        javax.swing.GroupLayout panelFijaLayout = new javax.swing.GroupLayout(panelFija);
        panelFija.setLayout(panelFijaLayout);
        panelFijaLayout.setHorizontalGroup(
            panelFijaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFijaLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(panelFijaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPalabraFija, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelFijaLayout.createSequentialGroup()
                        .addComponent(lblInstruccionFija)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPalabraSecreta, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)))
                .addGap(14, 14, 14))
        );
        panelFijaLayout.setVerticalGroup(
            panelFijaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFijaLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelFijaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInstruccionFija)
                    .addComponent(txtPalabraSecreta, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPalabraFija, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        btnAdministrar.setBackground(new java.awt.Color(71, 85, 105));
        btnAdministrar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnAdministrar.setForeground(new java.awt.Color(255, 255, 255));
        btnAdministrar.setText("Administrar Banco de Palabras");
        btnAdministrar.addActionListener(this::btnAdministrarActionPerformed);

        btnSalir.setBackground(new java.awt.Color(226, 232, 240));
        btnSalir.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(51, 65, 85));
        btnSalir.setText("Salir del Juego");
        btnSalir.addActionListener(this::btnSalirActionPerformed);

        lblMensajeError.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblMensajeError.setForeground(new java.awt.Color(220, 38, 38));
        lblMensajeError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMensajeError.setText(" ");

        javax.swing.GroupLayout tarjetaCentralLayout = new javax.swing.GroupLayout(tarjetaCentral);
        tarjetaCentral.setLayout(tarjetaCentralLayout);
        tarjetaCentralLayout.setHorizontalGroup(
            tarjetaCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tarjetaCentralLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(tarjetaCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblSubtitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAlAzar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelFija, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdministrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblMensajeError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(60, 60, 60))
        );
        tarjetaCentralLayout.setVerticalGroup(
            tarjetaCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tarjetaCentralLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSubtitulo)
                .addGap(24, 24, 24)
                .addComponent(btnAlAzar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelFija, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAdministrar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblMensajeError, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(tarjetaCentral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(40, 40, 40))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(tarjetaCentral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(28, 28, 28))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAlAzarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlAzarActionPerformed
        if (ventana == null) {
            return;
        }
        try {
            lblMensajeError.setText(" ");
            ventana.iniciarJuegoAlAzar();
        } catch (PalabraInvalidaException ex) {
            lblMensajeError.setText(ex.getMessage());
            ventana.getGestorSonido().reproducir(EfectoSonido.ALERTA);
        }
    }//GEN-LAST:event_btnAlAzarActionPerformed

    private void btnPalabraFijaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPalabraFijaActionPerformed
        iniciarPalabraFija();
    }//GEN-LAST:event_btnPalabraFijaActionPerformed

    private void txtPalabraSecretaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPalabraSecretaActionPerformed
        iniciarPalabraFija();
    }//GEN-LAST:event_txtPalabraSecretaActionPerformed

    private void btnAdministrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdministrarActionPerformed
        if (ventana != null) {
            lblMensajeError.setText(" ");
            ventana.mostrarAdministracion();
        }
    }//GEN-LAST:event_btnAdministrarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        if (ventana != null) {
            ventana.dispose();
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    private void iniciarPalabraFija() {
        if (ventana == null) {
            return;
        }
        String palabra = new String(txtPalabraSecreta.getPassword());
        try {
            lblMensajeError.setText(" ");
            ventana.iniciarJuegoPalabraFija(palabra);
            txtPalabraSecreta.setText("");
        } catch (PalabraInvalidaException ex) {
            lblMensajeError.setText(ex.getMessage());
            ventana.getGestorSonido().reproducir(EfectoSonido.ALERTA);
            txtPalabraSecreta.selectAll();
            txtPalabraSecreta.requestFocusInWindow();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdministrar;
    private javax.swing.JButton btnAlAzar;
    private javax.swing.JButton btnPalabraFija;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel lblInstruccionFija;
    private javax.swing.JLabel lblMensajeError;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelFija;
    private javax.swing.JPanel tarjetaCentral;
    private javax.swing.JPasswordField txtPalabraSecreta;
    // End of variables declaration//GEN-END:variables
}
