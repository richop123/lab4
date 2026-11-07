package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import javax.swing.Timer;
import modelo.EstadoPartida;
import modelo.EtapaAhorcado;

public class PanelDibujo extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;
    private static final int INTERVALO_TIMER_MS = 35;
    private static final double INCREMENTO_TRAZO = 0.12;

    private EtapaAhorcado etapaActual = EtapaAhorcado.VACIO;
    private EtapaAhorcado etapaAnimando = EtapaAhorcado.VACIO;
    private EstadoPartida estadoPartida = EstadoPartida.EN_CURSO;

    private double progresoTrazo = 1.0;
    private double faseDerrota = 0.0;
    private final Timer timerAnimacion;

    public PanelDibujo() {
        initComponents();
        this.timerAnimacion = new Timer(INTERVALO_TIMER_MS, e -> repaint());
    }

    public void reiniciarDibujo() {
        if (timerAnimacion.isRunning()) {
            timerAnimacion.stop();
        }
        this.etapaActual = EtapaAhorcado.VACIO;
        this.etapaAnimando = EtapaAhorcado.VACIO;
        this.estadoPartida = EstadoPartida.EN_CURSO;
        this.progresoTrazo = 1.0;
        this.faseDerrota = 0.0;
        repaint();
    }

    public void actualizarEtapa(EtapaAhorcado nuevaEtapa, EstadoPartida nuevoEstado, Runnable alCompletar) {
        if (timerAnimacion.isRunning()) {
            timerAnimacion.stop();
        }

        boolean subioEtapa = nuevaEtapa.ordinal() > this.etapaActual.ordinal();
        this.etapaActual = nuevaEtapa;
        this.estadoPartida = nuevoEstado;

        if (subioEtapa) {
            this.etapaAnimando = nuevaEtapa;
            this.progresoTrazo = 0.0;
            this.faseDerrota = 0.0;

            for (var listener : timerAnimacion.getActionListeners()) {
                timerAnimacion.removeActionListener(listener);
            }
            timerAnimacion.addActionListener(e -> {
                progresoTrazo += INCREMENTO_TRAZO;
                if (progresoTrazo >= 1.0) {
                    progresoTrazo = 1.0;
                    if (estadoPartida == EstadoPartida.PERDIDA && faseDerrota < 1.0) {
                        faseDerrota += 0.25;
                    } else {
                        timerAnimacion.stop();
                        repaint();
                        if (alCompletar != null) {
                            alCompletar.run();
                        }
                        return;
                    }
                }
                repaint();
            });
            timerAnimacion.start();
        } else {
            this.etapaAnimando = EtapaAhorcado.VACIO;
            this.progresoTrazo = 1.0;
            repaint();
            if (alCompletar != null) {
                alCompletar.run();
            }
        }
    }

    public boolean isAnimando() {
        return timerAnimacion.isRunning();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

        int w = getWidth();
        int h = getHeight();

        int baseX1 = (int) (w * 0.15);
        int baseX2 = (int) (w * 0.75);
        int baseY = (int) (h * 0.88);
        int posteX = (int) (w * 0.28);
        int techoY = (int) (h * 0.12);
        int cuerdaX = (int) (w * 0.60);
        int cuerdaFinY = (int) (h * 0.22);

        g2.setColor(new Color(93, 64, 55));
        g2.setStroke(new BasicStroke(6f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.drawLine(baseX1, baseY, baseX2, baseY);
        g2.drawLine(posteX, baseY, posteX, techoY);
        g2.drawLine(posteX, techoY, cuerdaX, techoY);
        g2.drawLine(posteX, techoY + 35, posteX + 35, techoY);

        g2.setColor(new Color(161, 136, 127));
        g2.setStroke(new BasicStroke(3.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.drawLine(cuerdaX, techoY, cuerdaX, cuerdaFinY);

        int radioCabeza = 22;
        int centroCabezaY = cuerdaFinY + radioCabeza;
        int cuelloY = cuerdaFinY + radioCabeza * 2;
        int caderaY = cuelloY + 72;
        int hombroY = cuelloY + 14;

        Color colorFigura = new Color(44, 62, 80);
        if (estadoPartida == EstadoPartida.PERDIDA && progresoTrazo >= 1.0) {
            colorFigura = new Color(192, 57, 43);
        } else if (estadoPartida == EstadoPartida.GANADA) {
            colorFigura = new Color(39, 174, 96);
        }
        g2.setColor(colorFigura);
        g2.setStroke(new BasicStroke(4.2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        int ordinalMaximo = etapaActual.ordinal();

        if (ordinalMaximo >= EtapaAhorcado.CABEZA.ordinal()) {
            double p = obtenerProgresoPara(EtapaAhorcado.CABEZA);
            Arc2D.Double arcoCabeza = new Arc2D.Double(
                    cuerdaX - radioCabeza,
                    cuerdaFinY,
                    radioCabeza * 2.0,
                    radioCabeza * 2.0,
                    90,
                    -360.0 * p,
                    Arc2D.OPEN
            );
            g2.draw(arcoCabeza);

            if (p >= 1.0) {
                dibujarRostro(g2, cuerdaX, centroCabezaY);
            }
        }

        if (ordinalMaximo >= EtapaAhorcado.TRONCO.ordinal()) {
            dibujarSegmentoProgresivo(g2, cuerdaX, cuelloY, cuerdaX, caderaY, obtenerProgresoPara(EtapaAhorcado.TRONCO));
        }

        if (ordinalMaximo >= EtapaAhorcado.BRAZO_IZQ.ordinal()) {
            dibujarSegmentoProgresivo(g2, cuerdaX, hombroY, cuerdaX - 38, hombroY + 36, obtenerProgresoPara(EtapaAhorcado.BRAZO_IZQ));
        }

        if (ordinalMaximo >= EtapaAhorcado.BRAZO_DER.ordinal()) {
            dibujarSegmentoProgresivo(g2, cuerdaX, hombroY, cuerdaX + 38, hombroY + 36, obtenerProgresoPara(EtapaAhorcado.BRAZO_DER));
        }

        if (ordinalMaximo >= EtapaAhorcado.PIERNA_IZQ.ordinal()) {
            dibujarSegmentoProgresivo(g2, cuerdaX, caderaY, cuerdaX - 32, caderaY + 52, obtenerProgresoPara(EtapaAhorcado.PIERNA_IZQ));
        }

        if (ordinalMaximo >= EtapaAhorcado.PIERNA_DER.ordinal()) {
            dibujarSegmentoProgresivo(g2, cuerdaX, caderaY, cuerdaX + 32, caderaY + 52, obtenerProgresoPara(EtapaAhorcado.PIERNA_DER));
        }

        g2.setFont(new Font("SansSerif", Font.PLAIN, 12));
        g2.setColor(new Color(120, 130, 140));
        g2.drawString("Etapa: " + etapaActual.name() + " (" + etapaActual.ordinal() + "/6 errores)", 16, h - 12);

        g2.dispose();
    }

    private double obtenerProgresoPara(EtapaAhorcado etapa) {
        if (etapa == etapaAnimando) {
            return Math.min(1.0, progresoTrazo);
        }
        return 1.0;
    }

    private void dibujarSegmentoProgresivo(Graphics2D g2, double x1, double y1, double x2, double y2, double progreso) {
        double xf = x1 + (x2 - x1) * progreso;
        double yf = y1 + (y2 - y1) * progreso;
        g2.draw(new Line2D.Double(x1, y1, xf, yf));
    }

    private void dibujarRostro(Graphics2D g2, int cx, int cy) {
        BasicStroke trazoOriginal = (BasicStroke) g2.getStroke();
        g2.setStroke(new BasicStroke(2.2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        if (estadoPartida == EstadoPartida.PERDIDA && progresoTrazo >= 1.0) {
            int tamX = (int) (4 * Math.min(1.0, Math.max(0.4, faseDerrota)));
            g2.drawLine(cx - 9 - tamX, cy - 5 - tamX, cx - 9 + tamX, cy - 5 + tamX);
            g2.drawLine(cx - 9 + tamX, cy - 5 - tamX, cx - 9 - tamX, cy - 5 + tamX);
            g2.drawLine(cx + 9 - tamX, cy - 5 - tamX, cx + 9 + tamX, cy - 5 + tamX);
            g2.drawLine(cx + 9 + tamX, cy - 5 - tamX, cx + 9 - tamX, cy - 5 + tamX);
            g2.drawArc(cx - 8, cy + 6, 16, 8, 0, 180);
        } else if (estadoPartida == EstadoPartida.GANADA) {
            g2.fillOval(cx - 9, cy - 6, 4, 4);
            g2.fillOval(cx + 5, cy - 6, 4, 4);
            g2.drawArc(cx - 8, cy + 1, 16, 9, 180, 180);
        } else {
            g2.fillOval(cx - 9, cy - 6, 4, 4);
            g2.fillOval(cx + 5, cy - 6, 4, 4);
            g2.drawLine(cx - 6, cy + 7, cx + 6, cy + 7);
        }

        g2.setStroke(trazoOriginal);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(250, 251, 253));
        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(210, 218, 226), 1, true));
        setPreferredSize(new java.awt.Dimension(300, 320));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 298, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 318, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
