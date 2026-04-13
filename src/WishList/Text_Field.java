package WishList;

import processing.core.PApplet;
import static processing.core.PConstants.BACKSPACE;

public class Text_Field {
    int x, y, h, w;

    int bgColor, fgColor, selectedColor, borderColor;
    int borderWeight = 1;
    boolean esContraseña = false;

    public String text = "";
    int textSize = 24;

    boolean selected = false;

    String placeholder = "";

    public void setPlaceholder(String p){
        this.placeholder = p;
    }

    public Text_Field(PApplet p5, int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.bgColor = p5.color(140, 140, 140);
        this.fgColor = p5.color(0, 0, 0);
        this.selectedColor = p5.color(190, 190, 60);
        this.borderColor = p5.color(30, 30, 30);
        this.borderWeight = 1;
    }

    // Dibuixa el Camp de Text
    public void display(PApplet p5) {
        p5.pushStyle();
        if (selected) {
            p5.fill(selectedColor);
        } else {
            p5.fill(bgColor);
        }

        p5.strokeWeight(borderWeight);
        p5.stroke(borderColor);
        p5.rect(x, y, w, h, 5);

        p5.fill(fgColor);
        p5.textSize(textSize); p5.textAlign(p5.LEFT, p5.CENTER);


        if(this.text.length() == 0){
            p5.fill(150); // gris
            p5.text(placeholder, x + 10, y + h/2 + 5);
        } else {
            p5.fill(0);
            p5.text(this.text, x + 10, y + h/2 + 5);
        }

        p5.popStyle();
    }

    // Afegeix i/o lleva el text que es tecleja
    public void keyPressed(char key, int keyCode) {
        if (selected) {
            if (keyCode == (int)BACKSPACE) {
                removeText();
            } else if (keyCode == 32) {
                addText(' '); // SPACE
            } else {

                boolean isKeyCapitalLetter = (key >= 'A' && key <= 'Z');
                boolean isKeySmallLetter = (key >= 'a' && key <= 'z');
                boolean isKeyNumber = (key >= '0' && key <= '9');

                if (isKeyCapitalLetter || isKeySmallLetter || isKeyNumber) {
                    addText(key);
                }
            }
        }
    }

    public void keyPressed(int keyCode) {
        if (!selected) return;

        if (keyCode == BACKSPACE) {
            removeText();
        }
    }

    // Gestiona entrada de text real (inclou accents)
    public void keyTyped(char key) {
        if (!selected) return;

        // Evita caracteres de control
        if (key == '\n' || key == '\r' || key == '\b') return;

        addText(key);
    }

    // Afegeix la lletra c al final del text
    public void addText(char c) {
        if (this.text.length() < 25) {
            this.text += c;
        }
    }

    // Lleva la darrera lletra del text
    public void removeText() {
        if (text.length() > 0) {
            text = text.substring(0, text.length() - 1);
        }
    }

    public void removeAllText(){
        this.text = "";
    }

    public String getText(){
        return this.text;
    }


    public void setText(String t){
        this.text= t;
    }

    public boolean mouseOverTextField(PApplet p5) {
        return (p5.mouseX >= this.x && p5.mouseX <= this.x + this.w && p5.mouseY >= this.y && p5.mouseY <= this.y + this.h);
    }


    public void isPressed(PApplet p5) {
        if (mouseOverTextField(p5)) {
            selected = true;
        } else {
            selected = false;
        }
    }
    public void clear() {
        text = "";
    }
}
