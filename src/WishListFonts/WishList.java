package WishListFonts;

import processing.core.PApplet;

public class WishList extends PApplet {
    // Tipografies de l'App
    Fonts fontsApp;

    public static void main(String[] args) {
        PApplet.main("WishListFonts.WishList", args);
    }

    public void settings(){
        size(800, 800, P2D);
        smooth(10);
    }

    public void setup(){

        // Constructor de les fonts de l'App
        fontsApp = new Fonts(this);
    }

    public void draw(){
        // Dibuixa el fons (blanc)
        background(255);

        textFont(fontsApp.getFirstFont());
        text("Titol de l'App", 50, 200);

        fill(50);
        textFont(fontsApp.getSecondFont());
        text("Subtitol de l'App", 50, 250);

        fill(55, 0, 0);
        textFont(fontsApp.getThirdFont());
        text("Paràgraf de l'App", 50, 300);


        // Mostra totes les fonts de l'App
        fontsApp.displayFonts(this, 100, 400, 50);


    }


    // ******************* KEYBOARD interaction ***************************** //

    public void keyPressed(){
        println("KEY PRESSED");
    }

    // ******************* MOUSE interaction ***************************** //

    public void mousePressed(){
        println("MOUSE PRESSED");
    }

    public void mouseDragged(){
        println("MOUSE DRAGGED");
    }

    public void mouseReleased() {
        println("MOUSE RELEASED");
    }

}
