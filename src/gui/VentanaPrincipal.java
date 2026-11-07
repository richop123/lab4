package gui;

import excepciones.PalabraInvalidaException;
import gui.sonido.GestorSonido;
import java.awt.CardLayout;
import modelo.AdministradorPalabras;
import modelo.JuegoAhorcado;
import modelo.JuegoAlAzar;
import modelo.JuegoPalabraFija;

public class VentanaPrincipal extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;

    public static final String CARD_MENU = "MENU";
    public static final String CARD_JUEGO = "JUEGO";
    public static final String CARD_ADMIN = "ADMIN";

    private final AdministradorPalabras administradorPalabras;
    private final transient GestorSonido gestorSonido;
    private transient JuegoAhorcado juegoActual;

    private final PanelMenu panelMenu;
    private final PanelJuego panelJuego;
    private final PanelAdministracion panelAdministracion;

    public VentanaPrincipal() {
        this(new AdministradorPalabras());
    }

    public VentanaPrincipal(AdministradorPalabras administradorPalabras) {
        this.administradorPalabras = administradorPalabras;
        this.gestorSonido = new GestorSonido();
        initComponents();

        this.panelMenu = new PanelMenu(this);
        this.panelJuego = new PanelJuego(this);
        this.panelAdministracion = new PanelAdministracion(this, administradorPalabras);

        this.contenedorPantallas.add(this.panelMenu, CARD_MENU);
        this.contenedorPantallas.add(this.panelJuego, CARD_JUEGO);
        this.contenedorPantallas.add(this.panelAdministracion, CARD_ADMIN);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        contenedorPantallas = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(920, 600));

        contenedorPantallas.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contenedorPantallas, javax.swing.GroupLayout.DEFAULT_SIZE, 960, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contenedorPantallas, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public void iniciarJuegoAlAzar() throws PalabraInvalidaException {
        this.juegoActual = new JuegoAlAzar(administradorPalabras);
        this.panelJuego.iniciarPartida(this.juegoActual);
        mostrarTarjeta(CARD_JUEGO);
    }

    public void iniciarJuegoPalabraFija(String palabraSecreta) throws PalabraInvalidaException {
        this.juegoActual = new JuegoPalabraFija(palabraSecreta);
        this.panelJuego.iniciarPartida(this.juegoActual);
        mostrarTarjeta(CARD_JUEGO);
    }

    public void mostrarMenu() {
        this.panelMenu.limpiarFormulario();
        mostrarTarjeta(CARD_MENU);
    }

    public void mostrarAdministracion() {
        this.panelAdministracion.refrescarVista();
        mostrarTarjeta(CARD_ADMIN);
    }

    private void mostrarTarjeta(String nombreTarjeta) {
        CardLayout layout = (CardLayout) contenedorPantallas.getLayout();
        layout.show(contenedorPantallas, nombreTarjeta);
    }

    public GestorSonido getGestorSonido() {
        return gestorSonido;
    }

    public JuegoAhorcado getJuegoActual() {
        return juegoActual;
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new VentanaPrincipal(new AdministradorPalabras()).setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel contenedorPantallas;
    // End of variables declaration//GEN-END:variables
}
