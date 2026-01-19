package WishList;

import processing.core.PApplet;
import processing.core.PImage;

import java.awt.*;

public class Card {
    PImage img;
    String item, name;
    String price;
    String description;

    // Dimensions
    float x, y, w, h, b;

    // Constructors

    public Card(){
    }

    public Card(String item, String name, String price, String description){
        this.item = item;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public Card(String[] info){
        this.item = info[0];
        this.name = info[1];
        this.price = info[2];
        this.description = info[3];
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
        float textOffsetX = 40;
        p5.pushStyle();

        p5.stroke(0);
        if(selectedCard){
            p5.fill(200, 100, 100);
        }
        else if(this.mouseOver(p5)){
            p5.fill(200);
        }
        else {
            p5.fill(220);
        }
        p5.rect(x, y, w, h, b/2);

        // imatge descriptiva

//        float imgW = img.width/3;
        //   float imgH = img.height/3;
        float imgX = x + 10;
        float imgY = y + 10;
        float imgW = 225;
        float imgH = 265;


        if(img!=null){
            p5.image(img, imgX, imgY, imgW, imgH);
            img.resize(300, 0);
            p5.noFill(); p5.rect(x+b , y+b , imgW, imgH);
        }
        else {
            p5.fill(50);
        }
        p5.rect(x + b, y + b, imgW, imgH);

        // Títol
        p5.fill(0); p5.textSize(24); p5.textAlign(p5.CENTER);
        p5.text(name, x + 2*w/3 + textOffsetX, y + h/5);

        // Lloc
        p5.fill(0); p5.textSize(18); p5.textAlign(p5.CENTER);

        // Secció
        p5.fill(0); p5.textSize(18); p5.textAlign(p5.CENTER);

        p5.text(item, x + 2*w/3 + textOffsetX, y + h/5+40);

        // Descripció
        float textX = x + w/3 + b + textOffsetX;
        float textW = 2*w/3 - b*2 - textOffsetX;
        p5.text(price, textX, y + 2*h/3 - b-30, textW, h/4);

        p5.text(description, textX, y + 2*h/3 - b, textW, h/4);

        p5.popStyle();
    }

    public boolean mouseOver(PApplet p5){
        return this.x < p5.mouseX && p5.mouseX < this.x + this.w &&
                this.y < p5.mouseY && p5.mouseY < this.y + this.h;
    }
}