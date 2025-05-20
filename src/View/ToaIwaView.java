package View;
import Control.CardControl;
import Model.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Deque;

import static Control.CardControl.InsertarManoUI;

public class ToaIwaView {
    private JPanel Title;
    private JButton pila, botonPausa;
    private JLabel Carta1, Carta2, Carta3, CartaJ1, CartaJ2, CartaJ3, CartaJ4,
            CartaO1, CartaO2, CartaO3, labelTimer;
    private static int seconds = 0;
    private boolean isPaused = false;
    private Card cartaSeleccionada = null;
    private int indiceCartaSeleccionada = -1;
    private Card[] manoJug;
    private JLabel[] manoJugLabel;



    public ToaIwaView() {

        Deque<Card> pila = CardControl.CreateStack();
        Card[] manoJug = CardControl.ManoInicial(pila);
        Card[] manoOp = CardControl.ManoInicial(pila);

        JFrame frame = new JFrame();
        crearFrame(frame);
        JLabel[] manoJugLabel = {CartaJ1, CartaJ2, CartaJ3, CartaJ4};

        CardControl.InsertarManoUI(manoJug, manoJugLabel);
        for (int i = 0; i < manoJugLabel.length; i++) {
            final int idx = i;
        }
    }

    public void crearFrame (JFrame frame){
        frame = new JFrame("ToaIwa");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        int cartaWidth = 90;
        int cartaHeight = 150;
        int espacio = 15;
        int totalCartas = 4;
        int width_carta_mesa = 90;
        int height_carta_mesa = 140;
        int location_c2;

        int anchoTotal = totalCartas * cartaWidth + (totalCartas - 1) * espacio;
        int inicioX = (540 - anchoTotal) / 2;

        Title = new JPanel();
        Dimension mobileSize = new Dimension(540, 950);
        Title.setPreferredSize(mobileSize);
        Title.setSize(mobileSize);
        Title.setBackground(Color.blue);
        Title.setVisible(true);
        Title.setLayout(null);

        CartaJ1 = new JLabel();
        CartaJ1.setBounds(inicioX, 680, cartaWidth, cartaHeight);

        CartaJ2 = new JLabel();
        CartaJ2.setBounds(inicioX + (cartaWidth + espacio) * 1, 680, cartaWidth, cartaHeight);

        CartaJ3 = new JLabel();
        CartaJ3.setBounds(inicioX + (cartaWidth + espacio) * 2, 680, cartaWidth, cartaHeight);

        CartaJ4 = new JLabel();
        CartaJ4.setBounds(inicioX + (cartaWidth + espacio) * 3, 680, cartaWidth, cartaHeight);

        Title.add(CartaJ1);
        Title.add(CartaJ2);
        Title.add(CartaJ3);
        Title.add(CartaJ4);

        Carta2 = new JLabel();
        Carta2.setSize(width_carta_mesa, height_carta_mesa);
        location_c2 = (Title.getWidth()/2)-(Carta2.getWidth()/2);
        Carta2.setLocation(location_c2, 520);
        Carta2.setBackground(Color.red);
        Carta2.setOpaque(true);
        Title.add(Carta2);

        Carta1 = new JLabel();
        Carta1.setSize(width_carta_mesa, height_carta_mesa);
        Carta1.setLocation(location_c2 - 130, 520);
        Carta1.setBackground(Color.green);
        Carta1.setOpaque(true);
        Title.add(Carta1);

        Carta3 = new JLabel();
        Carta3.setSize(width_carta_mesa, height_carta_mesa);
        Carta3.setLocation(location_c2 + 130, 520);
        Carta3.setBackground(Color.pink);
        Carta3.setOpaque(true);
        Title.add(Carta3);

        CartaO2 = new JLabel();
        CartaO2.setSize(80, 130);

        int height = 15;

        location_c2 = (Title.getWidth()/2)-(CartaO2.getWidth()/2);
        CartaO2.setLocation(location_c2, height);
        CartaO2.setBackground(Color.red);
        CartaO2.setOpaque(true);
        Title.add(CartaO2);

        CartaO1 = new JLabel();
        CartaO1.setSize(80, 130);
        CartaO1.setLocation(location_c2 - 130, height);
        CartaO1.setBackground(Color.green);
        CartaO1.setOpaque(true);
        Title.add(CartaO1);

        CartaO3 = new JLabel();
        CartaO3.setSize(80, 130);
        CartaO3.setLocation(location_c2 + 130, height );
        CartaO3.setBackground(Color.pink);
        CartaO3.setOpaque(true);
        Title.add(CartaO3);

        pila = new JButton();
        pila.setSize(110, 60);
        pila.setLocation(Title.getWidth()/2 + 100, 320);
        pila.setBackground(Color.yellow);
        pila.setOpaque(true);
        Title.add(pila);

        Timer timer = new Timer(1000, new TimeActionListener());
        timer.start();

        labelTimer = new JLabel();labelTimer.setSize(120, 30);
        labelTimer.setLocation(35,40);
        labelTimer.setForeground(Color.white);
        Title.add(labelTimer);


        botonPausa = new JButton();
        botonPausa.setSize(40, 40);
        botonPausa.setLocation(470,40);
        botonPausa.addMouseListener(new ButtonPauseListener(timer));
        Title.add(botonPausa);

        frame.setResizable(false);
        frame.setLocation(500, 0);
        frame.setContentPane(Title);
        frame.pack();
    }

    private class TimeActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int minutes = seconds / 60;
            int secs = seconds % 60;
            labelTimer.setText(String.format("%d:%02d", minutes, secs));
            seconds++;
        }
    }

    private class ButtonPauseListener extends MouseAdapter {
        Timer timer;

        public ButtonPauseListener(Timer timer){
            this.timer = timer;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            if (isPaused) {
                timer.start();
            } else {
                timer.stop();
            }
            isPaused = !isPaused;
        }
    }

    public static void main(String[] args) {
        new ToaIwaView();
    }
}
