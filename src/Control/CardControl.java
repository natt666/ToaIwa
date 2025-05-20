package Control;
import Model.Card;
import Model.Joker;
import Model.Spirit;
import Model.Warrior;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.*;
import java.util.List;

public class CardControl {
    public static void main(String[] args) {
    }

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
}
