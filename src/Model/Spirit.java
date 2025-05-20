package Model;

public class Spirit extends Card {

    public Spirit(int value, String imgFront, String imgBack) {
        super(value, imgFront, imgBack);
    }

    @Override
    public String toString() {
        return "Spirit{" +
                "value=" + value +
                ", imgFront='" + imgFront + '\'' +
                ", imgBack='" + imgBack + '\'' +
                '}';
    }
}
