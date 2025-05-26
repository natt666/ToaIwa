package Model;

public class Warrior extends Card{
    private Colour color;

    public Warrior(int value, String imgFront, String imgBack, Colour color) {
        super(value, imgFront, imgBack);
        this.color = color;
    }

    public enum Colour{orange, green, red, blue}

    @Override
    public String toString() {
        return "Warrior{" +
                "color=" + color +
                ", value=" + value +
                ", imgFront='" + imgFront + '\'' +
                ", imgBack='" + imgBack + '\'' +
                '}';
    }

    public Colour getColor() {
        return color;
    }

    public void setColor(Colour color) {
        this.color = color;
    }
}
