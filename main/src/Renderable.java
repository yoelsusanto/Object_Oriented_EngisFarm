package src;

public class Renderable {

    protected char renderChar;

    public Renderable() {
        this.renderChar = '?';
    }

    public Renderable(char renderChar) {
        this.renderChar=renderChar;
    }

    public char getRender() {
        return renderChar;
    }


}
