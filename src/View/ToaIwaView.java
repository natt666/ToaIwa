package View;
import javax.swing.*;
import java.awt.*;

public class ToaIwaView {
    private JPanel Title;
    private JLabel Carta1, Carta2, Carta3;

    public ToaIwaView() {
        JFrame frame = new JFrame("ToaIwa");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Title = new JPanel();
        Dimension mobileSize = new Dimension(540, 960);
        Title.setPreferredSize(mobileSize);
        Title.setSize(mobileSize);
        Title.setBackground(Color.blue);
        Title.setLayout(null);

        Carta2 = new JLabel();
        Carta2.setSize(50, 120);
        Carta2.setLocation((Title.getWidth()/2)-(Carta2.getWidth()/2), 20);
        Carta2.setBackground(Color.red);
        Title.add(Carta2);

        frame.setContentPane(Title);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        new ToaIwaView();
    }
}
