package Model;

import javax.swing.*;

public class Joker extends Card{

    public Joker(int value, String imgFront, String imgBack) {
        super(value, imgFront, imgBack);
    }

    @Override
    public String toString() {
        return "Joker{" +
                "value=" + value +
                ", imgFront='" + imgFront + '\'' +
                ", imgBack='" + imgBack + '\'' +
                '}';
    }

    public static Warrior QueCartaEs (){
        int valor = -1;
        while (valor < 1 || valor > 8) {
            String valorStr = JOptionPane.showInputDialog(null, "¿De qué valor es tu carta? (entre 1 y 8)");
            try {
                valor = Integer.parseInt(valorStr);
                if (valor < 1 || valor > 8) {
                    JOptionPane.showMessageDialog(null, "El valor debe estar entre 1 y 8.", "Valor inválido", JOptionPane.WARNING_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Introduce un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        String nom ="";
        Warrior.Colour color = null;
        switch (valor) {
            case 1,8:
                color = Warrior.Colour.red;
                nom = "rojo";
            break;
            case 2,7:
                color = Warrior.Colour.orange;
                nom = "naranja";
            break;
            case 3,6:
                color = Warrior.Colour.green;
                nom = "verde";
            break;
            case 4,5:
                color = Warrior.Colour.blue;
                nom = "azul";
            break;
        }

        String resumen = "Tu carta es de valor " + valor + " y color " + nom + ".";
        JOptionPane.showMessageDialog(null, resumen, "Resumen de tu carta", JOptionPane.INFORMATION_MESSAGE);

        String imgFront = "src/resources/cartes-mauwi/" + valor + ".png";
        String imgBack = "src/resources/cartes-mauwi/9.png";

        Warrior warrior = new Warrior(valor, imgFront, imgBack, color);

        return warrior;
    }
}
