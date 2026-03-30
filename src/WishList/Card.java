package WishList;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

import java.awt.*;

public class Card {
    PImage img;
    String nom;
    String preu;
    String marca;

    // Dimensions
    float x, y, w, h, b;

    // Constructors

    public Card(){
    }

    public Card(String nom, String preu, String marca){
        this.nom = nom;
        this.preu = preu;
        this.marca = marca;
    }

    public Card(String[] info){
        this.nom = info[0];
        this.preu = info[1];
        this.marca = info[2];
    }

    //Setters

    public void setDimensions(float x, float y, float w, float h, float b){
        this.x = x; this.y = y;
        this.w = w; this.h = h;
        this.b = b;
    }

    public void setImage(PImage img){
        this.img = img;
    }



    public void display(PApplet p5, boolean selectedCard){

        p5.pushStyle();

        // ====== COLORES ======
        int bg = p5.color(245);
        int hover = p5.color(230);
        int selected = p5.color(200, 100, 100);

        // ====== FONDO ======
        p5.stroke(180);
        if(selectedCard){
            p5.fill(selected);
        } else if(this.mouseOver(p5)){
            p5.fill(hover);
        } else {
            p5.fill(bg);
        }

        p5.rect(x, y, w, h, b);

        // ====== MÁRGENES ======
        float margin = 15;

        // ====== ZONA IMAGEN ======
        float imgX = x + margin;
        float imgY = y + margin;
        float imgW = w * 0.35f;
        float imgH = h - 2 * margin;

        // fondo imagen
        p5.fill(210);
        p5.noStroke();
        p5.rect(imgX, imgY, imgW, imgH, 10);

        if(img != null){
            p5.imageMode(PConstants.CORNER);
            p5.image(img, imgX, imgY, imgW, imgH);
        }

        // ====== ZONA TEXTO ======
        float textX = imgX + imgW + margin;
        float textW = w - imgW - 3 * margin;

        p5.fill(0);
        p5.textAlign(PConstants.LEFT);

        // ====== NOMBRE ======
        p5.textSize(20);
        p5.text(nom, textX, y + 40);

        // ====== MARCA ======
        p5.textSize(16);
        p5.fill(80);
        p5.text(marca, textX, y + 70);

        // ====== PRECIO ======
        p5.textSize(18);
        p5.fill(0);
        p5.text("€ " + preu, textX, y + 110);

        p5.popStyle();
    }

    public boolean mouseOver(PApplet p5){
        return this.x < p5.mouseX && p5.mouseX < this.x + this.w &&
                this.y < p5.mouseY && p5.mouseY < this.y + this.h;
    }
}