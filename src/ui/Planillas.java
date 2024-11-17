package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.Empleado;

public class Planillas extends JFrame implements Runnable {

    private static final long serialVersionUID = 1L;
    private JLabel lblMensaje, lblEmpleado, lblPC, lblIP, lblFecha, lblHora;
    private Empleado empleado = new Empleado();
    private Thread thHora;
    private JButton btnTrabajadores, btnPlanillas, btnInformes, btnConfiguraciones, btnUtilitarios;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Planillas frame = new Planillas();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Planillas() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 1024, 600);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);
        setUndecorated(true);

        JPanel pnlLogo = new JPanel();
        pnlLogo.setBounds(0, 0, 1024, 60);
        pnlLogo.setBackground(new Color(94, 17, 90));
        pnlLogo.setLayout(null);
        getContentPane().add(pnlLogo);

        JLabel imgLogo = new JLabel(new ImageIcon(getClass().getResource("/ui/img/logo.png")));
        imgLogo.setBounds(10, 7, 208, 43);
        pnlLogo.add(imgLogo);

        // Botones de navegación
        btnTrabajadores = new JButton("TRABAJADORES");
        btnPlanillas = new JButton("PLANILLAS");
        btnInformes = new JButton("INFORMES");
        btnConfiguraciones = new JButton("CONFIGURACIONES");
        btnUtilitarios = new JButton("UTILITARIOS");

        JButton[] botones = {btnTrabajadores, btnPlanillas, btnInformes, btnConfiguraciones, btnUtilitarios};
        int xPos = 230;

        for (JButton boton : botones) {
            boton.setBounds(xPos, 15, 120, 30);
            boton.setForeground(Color.WHITE);
            boton.setBackground(new Color(94, 17, 90));
            boton.setBorderPainted(false);
            boton.setFocusPainted(false);
            pnlLogo.add(boton);

            boton.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    boton.setBounds(boton.getX(), 0, boton.getWidth(), pnlLogo.getHeight());
                    boton.setBackground(new Color(64, 0, 64));
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    boton.setBounds(boton.getX(), 15, 120, 30);
                    boton.setBackground(new Color(94, 17, 90));
                }
            });

            xPos += 130;
        }

        JLabel imgSalir = new JLabel(new ImageIcon(getClass().getResource("/ui/img/salir.png")));
        imgSalir.setBounds(980, 13, 24, 34);
        pnlLogo.add(imgSalir);

        // Imagen de fondo con tamaño completo
        JLabel imgFondo = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/ui/img/fondoApp.png"));
                Image img = iconoOriginal.getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };
        imgFondo.setBounds(0, 60, 1024, 500);
        getContentPane().add(imgFondo);

        lblMensaje = new JLabel();
        lblMensaje.setBounds(20, 71, 1024, 39);
        getContentPane().add(lblMensaje);

        // Labels en la parte inferior
        int baseY = 570;
        lblEmpleado = new JLabel("Empleado : ");
        lblEmpleado.setBounds(20, baseY, 100, 30);
        getContentPane().add(lblEmpleado);

        lblPC = new JLabel("PC : ");
        lblPC.setBounds(150, baseY, 100, 30);
        getContentPane().add(lblPC);

        lblIP = new JLabel("IP : ");
        lblIP.setBounds(280, baseY, 100, 30);
        getContentPane().add(lblIP);

        lblFecha = new JLabel("Fecha : ");
        lblFecha.setBounds(410, baseY, 100, 30);
        getContentPane().add(lblFecha);

        lblHora = new JLabel("Hora : ");
        lblHora.setBounds(540, baseY, 100, 30);
        getContentPane().add(lblHora);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                form_windowOpened();
            }
        });

        imgSalir.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                imgSalirMouseClicked();
            }
        });

        thHora = new Thread(this);
    }

    public void form_windowOpened() {
        lblMensaje.setText("Hola " + empleado.getApellidoPaterno() + ", Bienvenido al sistema de planillas");
        lblEmpleado.setText(String.format("Empleado : %s %s %s", empleado.getNombres(), empleado.getApellidoPaterno(), empleado.getApellidoMaterno()));
        lblPC.setText("PC : Nombre de la PC");
        lblIP.setText("IP : Dirección IP");
        lblFecha.setText(new SimpleDateFormat("'Fecha :' dd/MM/yyyy").format(new Date()));
        thHora.start();
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    protected void imgSalirMouseClicked() {
        if (JOptionPane.showConfirmDialog(this, "¿Deseas salir?", "Salir", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            thHora.interrupt();
            System.exit(0);
        }
    }

    @Override
    public void run() {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        while (!Thread.currentThread().isInterrupted()) {
            lblHora.setText("Hora : " + df.format(new Date()));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}