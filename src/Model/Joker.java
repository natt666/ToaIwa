package Model;

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
}
