package gui;

import excepciones.LetraInvalidaException;
import excepciones.LetraRepetidaException;
import excepciones.PalabraInvalidaException;
import excepciones.PartidaTerminadaException;
import gui.sonido.EfectoSonido;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import modelo.EstadoPartida;
import modelo.JuegoAhorcado;
import modelo.JuegoBase;
import modelo.JuegoPalabraFija;
import modelo.Modalidad;
import modelo.ResultadoLetra;

public class PanelJuego extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;

    private static final String[] FILAS_TECLADO = {
        "QWERTYUIOP",
        "ASDFGHJKLÑ",
        "ZXCVBNM"
    };

    private VentanaPrincipal ventana;
    private transient JuegoAhorcado juego;
    private final Map<Character, JButton> botonesTeclado;
    private final PanelDibujo panelDibujo;

    public PanelJuego() {
        this(null);
    }

    public PanelJuego(VentanaPrincipal ventana) {
        this.ventana = ventana;
        this.botonesTeclado = new HashMap<>();
        this.panelDibujo = new PanelDibujo();
        initComponents();
        this.contenedorDibujo.add(this.panelDibujo, BorderLayout.CENTER);
        inicializarBotonesTeclado();
        if (this.ventana != null) {
            configurarAudio();
        }
    }

    public void setVentana(VentanaPrincipal ventana) {
        this.ventana = ventana;
        if (this.ventana != null) {
            configurarAudio();
        }
    }

    private void configurarAudio() {
        ventana.getGestorSonido().setCallbackSinAudio(this::mostrarAvisoSinAudio);
        if (!ventana.getGestorSonido().isAudioDisponible()) {
            mostrarAvisoSinAudio();
        }
    }

    private void inicializarBotonesTeclado() {
        for (String fila : FILAS_TECLADO) {
            JPanel panelFila = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 2));
            panelFila.setOpaque(false);

            for (int i = 0; i < fila.length(); i++) {
                char letra = fila.charAt(i);
                JButton btnTecla = new JButton(String.valueOf(letra));
                btnTecla.setFont(new Font("SansSerif", Font.BOLD, 13));
                btnTecla.setMargin(new Insets(4, 8, 4, 8));
                btnTecla.setPreferredSize(new Dimension(42, 34));
                btnTecla.setBackground(new Color(241, 245, 249));
                btnTecla.setForeground(new Color(30, 41, 59));
                btnTecla.setFocusPainted(false);
                btnTecla.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                btnTecla.addActionListener(e -> enviarJugada(String.valueOf(letra)));

                botonesTeclado.put(letra, btnTecla);
                panelFila.add(btnTecla);
            }
            panelTeclado.add(panelFila);
        }
    }

    public void iniciarPartida(JuegoAhorcado nuevoJuego) {
        this.juego = nuevoJuego;
        panelDibujo.reiniciarDibujo();
        restablecerColoresTeclado();
        actualizarVistaCompleta();
        mostrarMensajeGuia("Partida en curso. Ingrese una letra (A–Z o Ñ) o presione el teclado.");
        habilitarControlesEntrada(true);
        txtLetra.setText("");
        txtLetra.requestFocusInWindow();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelSuperior = new javax.swing.JPanel();
        lblModalidad = new javax.swing.JLabel();
        lblIntentos = new javax.swing.JLabel();
        lblAvisoAudio = new javax.swing.JLabel();
        btnSonido = new javax.swing.JButton();
        contenedorDibujo = new javax.swing.JPanel();
        panelDerecho = new javax.swing.JPanel();
        lblPalabraParcial = new javax.swing.JLabel();
        cajaAcertadas = new javax.swing.JPanel();
        lblAcertadas = new javax.swing.JLabel();
        cajaErradas = new javax.swing.JPanel();
        lblErradas = new javax.swing.JLabel();
        lblIngresar = new javax.swing.JLabel();
        txtLetra = new javax.swing.JTextField();
        btnProbar = new javax.swing.JButton();
        panelTeclado = new javax.swing.JPanel();
        panelInferior = new javax.swing.JPanel();
        lblMensaje = new javax.swing.JLabel();
        btnJugarDeNuevo = new javax.swing.JButton();
        btnVolverMenu = new javax.swing.JButton();

        setBackground(new java.awt.Color(243, 246, 250));

        panelSuperior.setBackground(new java.awt.Color(255, 255, 255));
        panelSuperior.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(218, 224, 233), 1, true));

        lblModalidad.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        lblModalidad.setForeground(new java.awt.Color(30, 41, 59));
        lblModalidad.setText("Modalidad: -");

        lblIntentos.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lblIntentos.setForeground(new java.awt.Color(37, 99, 235));
        lblIntentos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIntentos.setText("Intentos: 6 / 6");

        lblAvisoAudio.setFont(new java.awt.Font("SansSerif", 2, 12)); // NOI18N
        lblAvisoAudio.setForeground(new java.awt.Color(180, 83, 9));
        lblAvisoAudio.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblAvisoAudio.setText(" ");

        btnSonido.setBackground(new java.awt.Color(226, 232, 240));
        btnSonido.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnSonido.setForeground(new java.awt.Color(30, 41, 59));
        btnSonido.setText("Sonido: Activado");
        btnSonido.addActionListener(this::btnSonidoActionPerformed);

        javax.swing.GroupLayout panelSuperiorLayout = new javax.swing.GroupLayout(panelSuperior);
        panelSuperior.setLayout(panelSuperiorLayout);
        panelSuperiorLayout.setHorizontalGroup(
            panelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSuperiorLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblModalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblIntentos, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblAvisoAudio, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSonido, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        panelSuperiorLayout.setVerticalGroup(
            panelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSuperiorLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblModalidad)
                    .addComponent(lblIntentos)
                    .addComponent(lblAvisoAudio)
                    .addComponent(btnSonido, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        contenedorDibujo.setBackground(new java.awt.Color(250, 251, 253));
        contenedorDibujo.setPreferredSize(new java.awt.Dimension(300, 320));
        contenedorDibujo.setLayout(new java.awt.BorderLayout());

        panelDerecho.setBackground(new java.awt.Color(255, 255, 255));
        panelDerecho.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(218, 224, 233), 1, true));

        lblPalabraParcial.setFont(new java.awt.Font("Monospaced", 1, 30)); // NOI18N
        lblPalabraParcial.setForeground(new java.awt.Color(15, 23, 42));
        lblPalabraParcial.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPalabraParcial.setText("_ _ _ _");

        cajaAcertadas.setBackground(new java.awt.Color(240, 253, 244));
        cajaAcertadas.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(134, 239, 172)), "Letras Acertadas"));

        lblAcertadas.setFont(new java.awt.Font("Monospaced", 1, 15)); // NOI18N
        lblAcertadas.setForeground(new java.awt.Color(21, 128, 61));
        lblAcertadas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAcertadas.setText("Ninguna");

        javax.swing.GroupLayout cajaAcertadasLayout = new javax.swing.GroupLayout(cajaAcertadas);
        cajaAcertadas.setLayout(cajaAcertadasLayout);
        cajaAcertadasLayout.setHorizontalGroup(
            cajaAcertadasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cajaAcertadasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAcertadas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        cajaAcertadasLayout.setVerticalGroup(
            cajaAcertadasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cajaAcertadasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAcertadas, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cajaErradas.setBackground(new java.awt.Color(254, 242, 242));
        cajaErradas.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(252, 165, 165)), "Letras Erradas"));

        lblErradas.setFont(new java.awt.Font("Monospaced", 1, 15)); // NOI18N
        lblErradas.setForeground(new java.awt.Color(185, 28, 28));
        lblErradas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblErradas.setText("Ninguna");

        javax.swing.GroupLayout cajaErradasLayout = new javax.swing.GroupLayout(cajaErradas);
        cajaErradas.setLayout(cajaErradasLayout);
        cajaErradasLayout.setHorizontalGroup(
            cajaErradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cajaErradasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblErradas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        cajaErradasLayout.setVerticalGroup(
            cajaErradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cajaErradasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblErradas, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblIngresar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblIngresar.setText("Ingresar letra:");

        txtLetra.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        txtLetra.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtLetra.addActionListener(this::txtLetraActionPerformed);

        btnProbar.setBackground(new java.awt.Color(37, 99, 235));
        btnProbar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnProbar.setForeground(new java.awt.Color(255, 255, 255));
        btnProbar.setText("Probar");
        btnProbar.addActionListener(this::btnProbarActionPerformed);

        panelTeclado.setBackground(new java.awt.Color(255, 255, 255));
        panelTeclado.setLayout(new java.awt.GridLayout(3, 1, 0, 4));

        javax.swing.GroupLayout panelDerechoLayout = new javax.swing.GroupLayout(panelDerecho);
        panelDerecho.setLayout(panelDerechoLayout);
        panelDerechoLayout.setHorizontalGroup(
            panelDerechoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDerechoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelDerechoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPalabraParcial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelTeclado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelDerechoLayout.createSequentialGroup()
                        .addComponent(cajaAcertadas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cajaErradas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelDerechoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblIngresar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtLetra, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnProbar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(20, 20, 20))
        );
        panelDerechoLayout.setVerticalGroup(
            panelDerechoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDerechoLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblPalabraParcial, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelDerechoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cajaAcertadas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cajaErradas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelDerechoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIngresar)
                    .addComponent(txtLetra, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnProbar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelTeclado, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );

        panelInferior.setBackground(new java.awt.Color(255, 255, 255));
        panelInferior.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(218, 224, 233), 1, true));

        lblMensaje.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblMensaje.setForeground(new java.awt.Color(51, 65, 85));
        lblMensaje.setText("Ingrese una letra (A–Z o Ñ) para comenzar.");

        btnJugarDeNuevo.setBackground(new java.awt.Color(16, 185, 129));
        btnJugarDeNuevo.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        btnJugarDeNuevo.setForeground(new java.awt.Color(255, 255, 255));
        btnJugarDeNuevo.setText("Jugar de nuevo");
        btnJugarDeNuevo.addActionListener(this::btnJugarDeNuevoActionPerformed);

        btnVolverMenu.setBackground(new java.awt.Color(71, 85, 105));
        btnVolverMenu.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        btnVolverMenu.setForeground(new java.awt.Color(255, 255, 255));
        btnVolverMenu.setText("Volver al menú");
        btnVolverMenu.addActionListener(this::btnVolverMenuActionPerformed);

        javax.swing.GroupLayout panelInferiorLayout = new javax.swing.GroupLayout(panelInferior);
        panelInferior.setLayout(panelInferiorLayout);
        panelInferiorLayout.setHorizontalGroup(
            panelInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInferiorLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnJugarDeNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVolverMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        panelInferiorLayout.setVerticalGroup(
            panelInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInferiorLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMensaje)
                    .addComponent(btnJugarDeNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVolverMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelSuperior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelInferior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(contenedorDibujo, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panelDerecho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(panelSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(contenedorDibujo, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                    .addComponent(panelDerecho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelInferior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSonidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSonidoActionPerformed
        if (ventana == null) {
            return;
        }
        boolean nuevoEstado = !ventana.getGestorSonido().isSilenciado();
        ventana.getGestorSonido().setSilenciado(nuevoEstado);
        btnSonido.setText(nuevoEstado ? "Sonido: Silenciado" : "Sonido: Activado");
    }//GEN-LAST:event_btnSonidoActionPerformed

    private void txtLetraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLetraActionPerformed
        procesarEntradaCampo();
    }//GEN-LAST:event_txtLetraActionPerformed

    private void btnProbarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProbarActionPerformed
        procesarEntradaCampo();
    }//GEN-LAST:event_btnProbarActionPerformed

    private void btnJugarDeNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJugarDeNuevoActionPerformed
        accionJugarDeNuevo();
    }//GEN-LAST:event_btnJugarDeNuevoActionPerformed

    private void btnVolverMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverMenuActionPerformed
        if (ventana != null) {
            ventana.mostrarMenu();
        }
    }//GEN-LAST:event_btnVolverMenuActionPerformed

    private void procesarEntradaCampo() {
        String entrada = txtLetra.getText();
        txtLetra.setText("");
        enviarJugada(entrada);
        if (txtLetra.isEnabled()) {
            txtLetra.requestFocusInWindow();
        }
    }

    private void enviarJugada(String entrada) {
        if (juego == null || ventana == null || panelDibujo.isAnimando()) {
            return;
        }

        try {
            ResultadoLetra resultado = juego.ingresarLetra(entrada);
            EstadoPartida estadoActual = juego.getEstado();

            actualizarTextosYTeclado();

            if (resultado == ResultadoLetra.ERROR) {
                habilitarControlesEntrada(false);
                panelDibujo.actualizarEtapa(juego.getEtapa(), estadoActual, () -> {
                    reproducirSonidoJugada(resultado, estadoActual);
                    evaluarEstadoFinalOMensajeTurno(resultado, estadoActual);
                });
            } else {
                panelDibujo.actualizarEtapa(juego.getEtapa(), estadoActual, null);
                reproducirSonidoJugada(resultado, estadoActual);
                evaluarEstadoFinalOMensajeTurno(resultado, estadoActual);
            }

        } catch (LetraInvalidaException ex) {
            mostrarMensajeError(ex.getMessage());
            ventana.getGestorSonido().reproducir(EfectoSonido.ALERTA);
        } catch (LetraRepetidaException ex) {
            mostrarMensajeError(ex.getMessage());
            ventana.getGestorSonido().reproducir(EfectoSonido.ALERTA);
        } catch (PartidaTerminadaException ex) {
            mostrarMensajeError(ex.getMessage());
            ventana.getGestorSonido().reproducir(EfectoSonido.ALERTA);
        }
    }

    private void reproducirSonidoJugada(ResultadoLetra resultado, EstadoPartida estado) {
        EfectoSonido efectoEstado = switch (estado) {
            case GANADA -> EfectoSonido.VICTORIA;
            case PERDIDA -> EfectoSonido.DERROTA;
            case EN_CURSO -> null;
        };

        if (efectoEstado != null) {
            ventana.getGestorSonido().reproducir(efectoEstado);
            return;
        }

        EfectoSonido efectoTurno = switch (resultado) {
            case ACIERTO -> EfectoSonido.ACIERTO;
            case ERROR -> EfectoSonido.ERROR;
        };
        ventana.getGestorSonido().reproducir(efectoTurno);
    }

    private void evaluarEstadoFinalOMensajeTurno(ResultadoLetra resultado, EstadoPartida estado) {
        switch (estado) {
            case GANADA -> {
                habilitarControlesEntrada(false);
                mostrarMensajeExito("¡Ganaste! Completaste la palabra.");
            }
            case PERDIDA -> {
                habilitarControlesEntrada(false);
                mostrarMensajeDerrota(
                        "Perdiste. Se agotaron los 6 intentos. La palabra era "
                        + juego.getPalabraSecreta() + "."
                );
            }
            case EN_CURSO -> {
                habilitarControlesEntrada(true);
                if (resultado == ResultadoLetra.ACIERTO) {
                    mostrarMensajeExito("¡Acierto! La letra forma parte de la palabra.");
                } else {
                    mostrarMensajeError("Letra incorrecta. Intentos restantes: " + juego.getIntentosRestantes() + ".");
                }
                txtLetra.requestFocusInWindow();
            }
        }
    }

    private void accionJugarDeNuevo() {
        if (juego == null || ventana == null) {
            return;
        }

        try {
            if (juego.getModalidad() == Modalidad.PALABRA_FIJA && juego instanceof JuegoPalabraFija juegoFija) {
                JPasswordField campoNuevaPalabra = new JPasswordField(16);
                campoNuevaPalabra.setEchoChar('•');
                Object[] contenido = {
                    "Ingrese la nueva palabra secreta (3 a 15 letras, A–Z o Ñ):",
                    campoNuevaPalabra
                };
                int opcion = JOptionPane.showConfirmDialog(
                        this,
                        contenido,
                        "Nueva Partida — Palabra Fija",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );
                if (opcion != JOptionPane.OK_OPTION) {
                    return;
                }
                String nuevaPalabra = new String(campoNuevaPalabra.getPassword());
                juegoFija.cambiarPalabra(nuevaPalabra);
            }

            juego.reiniciar();
            panelDibujo.reiniciarDibujo();
            restablecerColoresTeclado();
            actualizarVistaCompleta();
            habilitarControlesEntrada(true);
            mostrarMensajeGuia("Nueva partida iniciada. ¡Buena suerte!");
            txtLetra.setText("");
            txtLetra.requestFocusInWindow();

        } catch (PalabraInvalidaException ex) {
            mostrarMensajeError(ex.getMessage());
            ventana.getGestorSonido().reproducir(EfectoSonido.ALERTA);
        }
    }

    private void actualizarVistaCompleta() {
        if (juego == null) {
            return;
        }
        lblModalidad.setText("Modalidad: " + juego.getModalidad().getTextoBoton());
        actualizarTextosYTeclado();
    }

    private void actualizarTextosYTeclado() {
        lblPalabraParcial.setText(juego.getPalabraMostrada());
        int restantes = juego.getIntentosRestantes();
        lblIntentos.setText("Intentos: " + restantes + " / " + JuegoBase.MAX_INTENTOS);
        if (restantes <= 2) {
            lblIntentos.setForeground(new Color(220, 38, 38));
        } else {
            lblIntentos.setForeground(new Color(37, 99, 235));
        }

        List<Character> acertadas = juego.getLetrasAcertadas();
        List<Character> erradas = juego.getLetrasErradas();

        lblAcertadas.setText(acertadas.isEmpty() ? "Ninguna" : formatearListaLetras(acertadas));
        lblErradas.setText(erradas.isEmpty() ? "Ninguna" : formatearListaLetras(erradas));

        for (Character c : acertadas) {
            JButton btn = botonesTeclado.get(c);
            if (btn != null) {
                btn.setBackground(new Color(34, 197, 94));
                btn.setForeground(Color.WHITE);
            }
        }
        for (Character c : erradas) {
            JButton btn = botonesTeclado.get(c);
            if (btn != null) {
                btn.setBackground(new Color(239, 68, 68));
                btn.setForeground(Color.WHITE);
            }
        }
    }

    private String formatearListaLetras(List<Character> letras) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < letras.size(); i++) {
            if (i > 0) {
                sb.append(" · ");
            }
            sb.append(letras.get(i));
        }
        return sb.toString();
    }

    private void restablecerColoresTeclado() {
        for (JButton btn : botonesTeclado.values()) {
            btn.setBackground(new Color(241, 245, 249));
            btn.setForeground(new Color(30, 41, 59));
        }
    }

    private void habilitarControlesEntrada(boolean habilitar) {
        txtLetra.setEnabled(habilitar);
        btnProbar.setEnabled(habilitar);
        for (JButton btn : botonesTeclado.values()) {
            btn.setEnabled(habilitar);
        }
    }

    private void mostrarAvisoSinAudio() {
        lblAvisoAudio.setText("Audio no disponible (silencioso)");
        btnSonido.setEnabled(false);
        btnSonido.setText("Sin Audio");
    }

    private void mostrarMensajeGuia(String texto) {
        lblMensaje.setForeground(new Color(51, 65, 85));
        lblMensaje.setText(texto);
    }

    private void mostrarMensajeExito(String texto) {
        lblMensaje.setForeground(new Color(21, 128, 61));
        lblMensaje.setText(texto);
    }

    private void mostrarMensajeError(String texto) {
        lblMensaje.setForeground(new Color(217, 119, 6));
        lblMensaje.setText(texto);
    }

    private void mostrarMensajeDerrota(String texto) {
        lblMensaje.setForeground(new Color(220, 38, 38));
        lblMensaje.setText(texto);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnJugarDeNuevo;
    private javax.swing.JButton btnProbar;
    private javax.swing.JButton btnSonido;
    private javax.swing.JButton btnVolverMenu;
    private javax.swing.JPanel cajaAcertadas;
    private javax.swing.JPanel cajaErradas;
    private javax.swing.JPanel contenedorDibujo;
    private javax.swing.JLabel lblAcertadas;
    private javax.swing.JLabel lblAvisoAudio;
    private javax.swing.JLabel lblErradas;
    private javax.swing.JLabel lblIngresar;
    private javax.swing.JLabel lblIntentos;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JLabel lblModalidad;
    private javax.swing.JLabel lblPalabraParcial;
    private javax.swing.JPanel panelDerecho;
    private javax.swing.JPanel panelInferior;
    private javax.swing.JPanel panelSuperior;
    private javax.swing.JPanel panelTeclado;
    private javax.swing.JTextField txtLetra;
    // End of variables declaration//GEN-END:variables
}
