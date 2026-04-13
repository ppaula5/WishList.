package WishList;

import WishList.Botons;
import processing.core.PApplet;
import processing.core.PImage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LoadAndCopyImageTest extends PApplet {

    // Elements de la Interfície Gràfica (Load Image)

    // Imatge
    PImage img;
    String titol="";
    File file;
    String rutaCarpeta = "C:\\WishList\\Producte\\foto\\";

    // Botó
    public static Botons bLoad, bSave;

    // Color de fons
    int bgColor;


    public static void main(String[] args) {
        PApplet.main("WishList.LoadAndCopyImageTest", args);
    }

    public void settings(){
        size(800, 800);
        smooth(10);
    }

    public void setup(){

        // Color de fons
        bgColor = color(255);

        // Creació del Botó
        bLoad = new Botons(this, "LOAD", 50, height-120, 200, 80);
        bSave= new Botons(this, "SAVE", 300, height-120, 200, 80);

    }

    public void draw() {

        // Fons de la finestra
        background(bgColor);

        // Dibuixa la imatge
        if(img!=null){
            image(img, 50, 50, 700, 600);
            textSize(34); textAlign(RIGHT);
            fill(0);
            text(titol, 750, 750);
        }
        else{
            fill(255);
            rect(50, 50, 700, 600);
            textSize(34); textAlign(RIGHT);
            text("Sense imatge", 750, 750);
        }

        // Dibuixa el botó
        bLoad.display(this);
        bSave.display(this);

        // Actualitza el cursor
        updateCursor(this);
    }

    // Modifica el cursor
    void updateCursor(PApplet p5){
        if(bLoad.mouseOverButton(p5) || bSave.mouseOverButton(p5)){
            cursor(HAND);
        }
        else {
            cursor(ARROW);
        }
    }

    // ******************* KEYBOARD interaction ***************************** //

    public void keyPressed(){
    }

    // ******************* MOUSE interaction ***************************** //

    // En cas de pitjar el ratolí
    public void mousePressed(){
        // Si pitjam sobre el botó
        if(bLoad.mouseOverButton(this)){
            // Obrim el dialeg
            selectInput("Selecciona una imatge ...", "fileSelected");
        }
        else if(bSave.mouseOverButton(this)){
            // Guardar la imatge en una carpeta de l'ordinador
            copiar(file, rutaCarpeta, titol);
        }
    }

    public void mouseDragged(){
        println("MOUSE DRAGGED");
    }

    public void mouseReleased() {
        println("MOUSE RELEASED");
    }


    // Carrega Imatge
    public void fileSelected(File selection) {
        if (selection == null) {
            println("No s'ha seleccionat cap fitxer.");
        } else {
            // Referència al fitxer imatge
            file = selection;

            // Obtenim la ruta del fitxer seleccionat
            String rutaImatge = selection.getAbsolutePath();

            img = loadImage(rutaImatge);  // Actualitzam imatge
            titol = selection.getName();  // Actualitzam títol (igual)
        }
    }

    // Copia un fitxer a una altra ubicació
    public void copiar(File file, String rutaCopia, String titol){
        Path original = Paths.get(file.getAbsolutePath());
        Path copia    = Paths.get(rutaCopia+"/"+titol);
        try{
            Files.copy(original, copia);
            println("OK: fitxer copiat a la carpeta.");
        } catch (IOException e) {
            println("ERROR: No s'ha pogut copiar el fitxer.");
        }
    }

}