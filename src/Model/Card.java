package Model;

public abstract class Card {
    protected int value;
    protected String imgFront;
    protected String imgBack;

    public Card(int value, String imgFront, String imgBack) {
        this.value = value;
        this.imgFront = imgFront;
        this.imgBack = imgBack;
    }

    @Override
    public String toString() {
        return "Card{" +
                "value=" + value +
                ", imgFront='" + imgFront + '\'' +
                ", imgBack='" + imgBack + '\'' +
                '}';
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getImgFront() {
        return imgFront;
    }

    public void setImgFront(String imgFront) {
        this.imgFront = imgFront;
    }

    public String getImgBack() {
        return imgBack;
    }

    public void setImgBack(String imgBack) {
        this.imgBack = imgBack;
    }
}


