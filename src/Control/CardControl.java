package Control;
import Model.Card;
import Model.Joker;
import Model.Spirit;
import Model.Warrior;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.*;
import java.util.List;

public class CardControl {

    public static Deque<Card> CreateStack(){
        String db_url = "jdbc:mysql://localhost:3306/toaiwa";
        String name = "natalia";
        String passw = "natalia1";
        String query = "select * from cartes";
        int valor, id;
        String imgFront, imgBack;
        List<Card> pila = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(db_url, name, passw);
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {
                id = rs.getInt("id_cartes");
                valor = rs.getInt("valor");
                imgFront = rs.getString("imgFront");
                imgBack = rs.getString("imgBack");
                switch (valor) {
                    case 0:
                        Spirit s = new Spirit(valor, imgFront, imgBack);
                        pila.add(s);
                        break;
                    case -1:
                        Joker j = new Joker(valor, imgFront, imgBack);
                        pila.add(j);
                        break;
                    default:
                        String queryColor = "select colour from warrior where id_cartes = ?";

                        try (PreparedStatement pss = con.prepareStatement(queryColor)) {
                            pss.setInt(1, id);
                            ResultSet rs2 = pss.executeQuery();
                            while (rs2.next()) {
                                String col = rs2.getString("colour");
                                Warrior.Colour colour = Warrior.Colour.valueOf(col);
                                Warrior w = new Warrior(valor, imgFront, imgBack, colour);
                                pila.add(w);
                            }
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                        break;
                }
            }
        } catch (SQLException e) {
            System.out.println("la conexión ha fallado:");
            e.printStackTrace();
        }
        Collections.shuffle(pila);
        Deque<Card> pilaSuffle = new ArrayDeque<>();
        pilaSuffle.addAll(pila);
        return pilaSuffle;
    }

    public static Card[] ManoInicial (Deque<Card> pila){
        Card[] mano = new Card[4];
        for (int i = 0; i < 4; i++) {
            mano[i] = pila.removeFirst();
        }
    return  mano;
    }

    public static void InsertarManoUI (Card[] mano, JLabel[] manoLabel){
        int i = 0;
        int ancho = 90;
        int alto = 150;
        for (Card card : mano) {
            System.out.println(card);
            ImageIcon original = new ImageIcon(mano[i].getImgFront());
            Image imagenEscalada = original.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);
            manoLabel[i].setIcon(iconoEscalado);
            i++;
        }
    }

    public static class CambiarMano extends MouseAdapter {
        private final Card[] manoJug;
        private final Deque<Card> pila;
        private final JLabel[] manoJugLabel;

        public CambiarMano (Card[] manoJug, Deque<Card> pila, JLabel[] manoJugLabel) {
            this.manoJug = manoJug;
            this.pila = pila;
            this.manoJugLabel = manoJugLabel;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("Cambiando mano...");

            for (int i = 0; i < manoJug.length; i++) {
                if (!pila.isEmpty()) {
                    manoJug[i] = CardControl.robarCarta(pila);
                    CardControl.AsignarImgFront(manoJugLabel[i], manoJug[i], manoJugLabel[i].getWidth(), manoJugLabel[i].getHeight());
                } else {
                    System.out.println("La pila está vacía, no se puede robar más");
                }
            }
        }
    }

    public static class AnadirMouseAdapterDestino extends MouseAdapter{

        public void mouseClicked(MouseEvent e, Card[] cartasEnDestino,Card cartaOrigen, int indiceCartaOrigen,
                                 JLabel destinoLabel, int index, Card[] manoJug, Deque<Card>pila, JLabel[] manoJugLabel) {

            super.mouseClicked(e);
            Card cartaDestino = cartasEnDestino[index];

            for (Card c : cartasEnDestino){
                System.out.println(c);
            }

            if (cartaOrigen == null || indiceCartaOrigen == -1) {
                System.out.println("No hay carta seleccionada para mover.");
                return;
            }

            if (cartaDestino == null) {
                cartasEnDestino[index] = cartaOrigen;

                AsignarImgFront(destinoLabel, cartaOrigen, destinoLabel.getWidth(), destinoLabel.getHeight());
                manoJug[indiceCartaOrigen] = robarCarta(pila);
                AsignarImgFront(manoJugLabel[indiceCartaOrigen], manoJug[indiceCartaOrigen], manoJugLabel[indiceCartaOrigen].getWidth(), manoJugLabel[indiceCartaOrigen].getHeight());
                indiceCartaOrigen = -1;
            } else {
                if (cartaOrigen instanceof Warrior && cartaDestino instanceof Warrior) {
                    Warrior wOrigen = (Warrior) cartaOrigen;
                    Warrior wDestino = (Warrior) cartaDestino;

                    if ((wOrigen.getColor().equals(wDestino.getColor())) && (wOrigen.getValue() != wDestino.getValue())) {
                        cartasEnDestino[index] = cartaOrigen;
                        AsignarImgBack(destinoLabel, cartaOrigen, destinoLabel.getWidth(), destinoLabel.getHeight());
                        manoJug[indiceCartaOrigen] = robarCarta(pila);
                        AsignarImgFront(manoJugLabel[indiceCartaOrigen], manoJug[indiceCartaOrigen], manoJugLabel[indiceCartaOrigen].getWidth(), manoJugLabel[indiceCartaOrigen].getHeight());
                        indiceCartaOrigen = -1;
                    } else {
                        System.out.println("La carta origen no puede reemplazar a la carta destino: valor menor o igual.");
                    }
                } else {
                    System.out.println("La carta origen y destino tienen el mismo color, no se puede reemplazar.");
                }
            }
            cartaOrigen = null;
            cartaDestino = null;
        }
    }

    public static void AsignarImgBack(JLabel label, Card carta, int width, int height) {
        ImageIcon icono = new ImageIcon(carta.getImgBack());
        Image imgEscalada = icono.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(imgEscalada));
    }

    public static void AsignarImgFront(JLabel label, Card carta, int ancho, int alto) {
        ImageIcon icono = new ImageIcon(carta.getImgFront());
        Image imagen = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(imagen));
    }

    public static Card robarCarta (Deque<Card>pila){
        Card  carta = pila.pollLast();
        return carta;
    }

}
