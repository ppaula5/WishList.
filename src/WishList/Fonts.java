package WishList;

import processing.core.PApplet;
import processing.core.PFont;

import static WishListFonts.Mides.*;

public class Fonts {
    PFont[] tipografia;

    //Constructor de les Fonts de l'App
    public Fonts(WishList p5){
        this.setTipografia(p5);
    }

    void setTipografia(WishList p5){
        this.tipografia = new PFont[3];
        this.tipografia[0] = p5.createFont("data/VerilySerifMono.otf", midaTitol);
        this.tipografia[1] = p5.createFont("data/F25_Bank_Printer_Bold.ttf", midaSubtitol);
        this.tipografia[2] = p5.createFont("data/secrcode.ttf", midaParagraf);
    }

    public int getNumFonts(){
        return this.tipografia.length;
    }

    // Getter de la font primaria
    public PFont getFirstTipografia(){
        return  this.tipografia[0];
    }

    // Getter del font secundaria
    public PFont getSecondTipografia(){
        return  this.tipografia[1];
    }

    // Getter del la font terciaria
    public PFont getThirdTipografia(){
        return  this.tipografia[2];
    }

    // Getter de la font i-èssima
    public PFont getTipografiaAt(int i){
        return this.tipografia[i];
    }

    // Dibuixa les font de l'App
    public void displayTipografia(PApplet p5, float x, float y, float h){
        p5.pushStyle();
        for(int i=0; i<getNumFonts(); i++){
            p5.fill(0); p5.stroke(0); p5.strokeWeight(3);
            p5.textFont(getTipografiaAt(i));
            //p5.text("Tipografia "+i, x, y + i*h);
        }
        p5.popStyle();
    }
}
