package WishList;

import processing.core.PApplet;

public class Botons {
    float x, y, w, h;  // Posició (x, y) i dimensions (w, h)
    int fillColor, strokeColor; // Colors del boto (fill / stroke).
    int fillColorOver, fillColorDisabled;  // Colors del boto (actiu / inactiu).
    String textBoto;  // Text
    public boolean enabled;  // Estat del botó (actiu / inactiu).
    Colors colors;
    // Constructor
    public Botons(PApplet p5, String text, float x, float y, float w, float h){
        this.colors = new Colors(p5);
        this.textBoto = text;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.enabled = true;
        this.fillColor = p5.color(colors.getColorAt(2));
        this.fillColorOver = p5.color(colors.getColorAt(1));
        this.fillColorDisabled = p5.color(100);
        this.strokeColor = p5.color(0);
    }

    // Setters

    public void setEnabled(boolean b){
        this.enabled = b;
    }

    public void setTextBoto(PApplet p5, String t){
        p5.textSize(30);
        this.textBoto = t;
    }

    public void setColors(int cFill, int cStroke, int cOver, int cDisabled){
        this.fillColor = cFill;
        this.strokeColor = cStroke;
        this.fillColorOver = cOver;
        this.fillColorDisabled = cDisabled;
    }

    // Getters

    public boolean isEnabled(){
        return  this.enabled;
    }

    // Dibuixa el botó
    public void display(PApplet p5){
        p5.pushStyle();
        if(!enabled){
            p5.fill(fillColorDisabled);  // Color desabilitat
        }
        else if(mouseOverButton(p5)){
            p5.fill(fillColorOver);      // Color quan ratolí a sobre
        }
        else{
            p5.fill(fillColor);          // Color actiu però ratolí fora
        }
        p5.stroke(strokeColor); p5.strokeWeight(2);        //Color i gruixa del contorn
        p5.rect(this.x, this.y, this.w, this.h, 10);    // Rectangle del botó

        // Text (color, alineació i mida)
        p5.fill(0); p5.textAlign(p5.CENTER); p5.textSize(20);
        p5.text(textBoto, this.x + this.w/2, this.y + this.h/2 + 10);
        p5.popStyle();
    }

    public boolean mouseOverButton(PApplet p5){
        return (p5.mouseX >= this.x) && (p5.mouseX <= this.x + this.w) &&
                (p5.mouseY >= this.y) && (p5.mouseY <= this.y + this.h);
    }

    // Indica si cal posar el cursor a HAND
    public boolean updateHandCursor(PApplet p5){
        return mouseOverButton(p5) && enabled;
    }
}

