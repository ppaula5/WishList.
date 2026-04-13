package WishList;

import processing.core.PApplet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WishList extends PApplet {

    Fonts appFonts;
    GUI appGUI;
    DataBase db;

    String rutaCarpeta = "/Users/paula/IdeaProjects/jjjj/data/fotos";

    public static void main(String[] args) {
        PApplet.main("WishList.WishList");

    }

    public void settings() {
        fullScreen();
    }

    public void setup() {
        db = new DataBase("admin", "12345", "WishList");
        db.connect();
        appFonts = new Fonts(this);
        appGUI = new GUI(this, db);
    }

    public void draw() {

        background(0);

        textFont(appFonts.getFirstTipografia());
        text("Titulo de la App", 50, 200);

        fill(50);
        textFont(appFonts.getSecondTipografia());
        text("Subtitulo de la App", 50, 250);

        fill(55, 0, 0);
        textFont(appFonts.getThirdTipografia());
        text("Paragrafo de la App", 50, 300);

        switch (GUI.pantallaActual) {
            case LOGIN:
                appGUI.dibujoPantallaLogIn(this);
                break;

            case INICIAL:
                appGUI.dibujoPantallaInicial(this);
                break;

            case SETTINGS:
                appGUI.dibujoPantallaSettings(this);
                break;

            case STATISTICS:
                appGUI.cargarEstadisticas(appGUI.usuarioActual);
                appGUI.dibujoPantallaStatistics(this);
                break;

            case CARD:
                appGUI.dibujoPantallaCard(this);
                break;

        }

        appFonts.displayTipografia(this, 100, 400, 500);

    }

    public void keyPressed() {

        appGUI.text1.keyPressed(key);
        appGUI.text2.keyPressed(key);
        appGUI.text3.keyPressed(key);
        appGUI.nom.keyPressed(key);
        appGUI.preu.keyPressed(key);
        appGUI.marca.keyPressed(key);
        appGUI.carpeta.keyPressed(key);
        if (keyCode == DELETE) {
            appGUI.eliminarCard();
        }


    }

    public void keyTyped() {
        appGUI.text1.keyTyped(key);
        appGUI.text2.keyTyped(key);
        appGUI.text3.keyTyped(key);
        appGUI.nom.keyTyped(key);
        appGUI.preu.keyTyped(key);
        appGUI.marca.keyTyped(key);
        appGUI.carpeta.keyTyped(key);


    }

    public void mousePressed() {
        if (GUI.rb3.mouseOverButton(this)) {
            GUI.menuObert = !GUI.menuObert;
            return;
        }

        if (GUI.menuObert && mouseX > 300) {
            GUI.menuObert = false;
        }

        if (GUI.menuObert) {

            if (GUI.b2.mouseOverButton(this)) {
                GUI.pantallaActual = GUI.PANTALLA.INICIAL;
            }

            if (GUI.b3.mouseOverButton(this)) {
                GUI.pantallaActual = GUI.PANTALLA.CARD;
            }

            if (GUI.b4.mouseOverButton(this)) {
                GUI.pantallaActual = GUI.PANTALLA.SETTINGS;
            }

            if (GUI.b5.mouseOverButton(this)) {
                GUI.pantallaActual = GUI.PANTALLA.LOGIN;
            }

            if (GUI.b6.mouseOverButton(this)) {
                GUI.pantallaActual = GUI.PANTALLA.STATISTICS;
            }

            return;
        }


        if (appGUI.pantallaActual == GUI.PANTALLA.LOGIN) {

            // activar textfields
            appGUI.text1.isPressed(this);
            appGUI.text2.isPressed(this);

            // botón login
            if (appGUI.b1.mouseOverButton(this)) {
                println("B1 has been pressed!!");

                String nom = appGUI.text2.getText();
                String password = appGUI.text1.getText();

                boolean ok = db.loginCorrecte(nom, password);
                if (db.loginCorrecte(nom, password)) {
                    System.out.println("Login correcto");
                    appGUI.usuarioActual = nom; // <-- guarda el usuario logueado aquí
                    appGUI.pantallaActual = GUI.PANTALLA.INICIAL;
                }

                if (ok) {
                    println("LOGIN CORRECTO");
                    appGUI.usuarioActual = nom;
                    appGUI.cargarCardsDesdeBD(this);
                    appGUI.pantallaActual = GUI.PANTALLA.INICIAL;
                } else {
                    println("LOGIN INCORRECTO");
                    appGUI.errorLogin = "Usuario o contraseña incorrectos";
                }
            }
        }
        else if (appGUI.pantallaActual == GUI.PANTALLA.INICIAL) {
            if(appGUI.bprevious.mouseOverButton(this)){
                appGUI.paged.prevPage();
            }else if(appGUI.bnext.mouseOverButton(this)){
                appGUI.paged.nextPage();
            }
            if (appGUI.s1.mouseOverSelect(this)) {
                if (appGUI.s1.isCollapsed()) {

                    appGUI.s1.toggle();
                } else {

                    appGUI.s1.update(this);
                    appGUI.s1.setCollapsed(true);
                    println("Seleccionado: " + appGUI.s1.getSelectedValue());
                }
            } else {

                appGUI.s1.setCollapsed(true);
            }
            if (appGUI.rb1.mouseOverButton(this)) {
                println("RB1 has been pressed!!");
                appGUI.pantallaActual = GUI.PANTALLA.LOGIN;
            }
            if (appGUI.rb2.mouseOverButton(this)) {
                println("RB2 has been pressed!!");
                appGUI.pantallaActual = GUI.PANTALLA.SETTINGS;
            }
            if (appGUI.rb3.mouseOverButton(this)) {
                appGUI.menuObert = !appGUI.menuObert;
            }
            if (GUI.menuObert && !GUI.rb3.mouseOverButton(this)) {
                if (mouseX > 300) {
                    GUI.menuObert = false;
                }
            }

            if (appGUI.paged.checkMouseOver(this)) {

                int anterior = appGUI.paged.selectedCard;

                appGUI.paged.checkCardSelection(this);

                if (anterior == appGUI.paged.selectedCard && anterior != -1) {
                    appGUI.paged.deleteSelectedCard(this, db, appGUI.usuarioActual);
                }
            }

        }
        else if (appGUI.pantallaActual == GUI.PANTALLA.SETTINGS) {
            if (appGUI.rb6.mouseOverButton(this)) {
                println("RB6 has been pressed!!");
                appGUI.pantallaActual = GUI.PANTALLA.INICIAL;
            } else if (appGUI.rb3.mouseOverButton(this)) {
                appGUI.menuObert = !appGUI.menuObert;
            }
            if (appGUI.s1.mouseOverSelect(this)) {
                if (appGUI.s1.isCollapsed()) {
                    appGUI.s1.toggle();
                } else {
                    appGUI.s1.update(this);
                    appGUI.s1.setCollapsed(true);
                    println("Seleccionado: " + appGUI.s1.getSelectedValue());
                }
                if (appGUI.s1.getSelectedValue() == "Color Mode") {
                    appGUI.colorMode = true;
                } else {
                    appGUI.colorMode = false;
                }
            }
            if (appGUI.s2.mouseOverSelect(this)) {
                if (appGUI.s2.isCollapsed()) {
                    appGUI.s2.toggle();
                } else {
                    appGUI.s2.update(this);
                    appGUI.s2.setCollapsed(true);
                    println("Seleccionado: " + appGUI.s2.getSelectedValue());
                }
            }
        }
        else if (appGUI.pantallaActual == GUI.PANTALLA.STATISTICS) {


            if (appGUI.rb6.mouseOverButton(this)) {
                appGUI.pantallaActual = GUI.PANTALLA.INICIAL;
            }
            if (appGUI.rb5.mouseOverButton(this)) {
                appGUI.pantallaActual = GUI.PANTALLA.LOGIN;
            }
            if (appGUI.b6.mouseOverButton(this)) {
                appGUI.pantallaActual = GUI.PANTALLA.STATISTICS;
            }
            if (appGUI.b5.mouseOverButton(this)) {
                appGUI.pantallaActual = GUI.PANTALLA.LOGIN;
            }
            if (appGUI.b4.mouseOverButton(this)) {
                appGUI.pantallaActual = GUI.PANTALLA.SETTINGS;
            }
            if (appGUI.b2.mouseOverButton(this)) {
                appGUI.pantallaActual = GUI.PANTALLA.INICIAL;
            }

        }
        else if (appGUI.pantallaActual == GUI.PANTALLA.CARD) {
            if (appGUI.rb6.mouseOverButton(this)) {
                println("B4 has been pressed!!");
                appGUI.pantallaActual = GUI.PANTALLA.INICIAL;
            }
            if (appGUI.rb1.mouseOverButton(this)) {
                appGUI.pantallaActual = GUI.PANTALLA.LOGIN;
            }
            if (appGUI.rb2.mouseOverButton(this)) {
                appGUI.pantallaActual = GUI.PANTALLA.SETTINGS;
            }

            appGUI.text3.isPressed(this);
            appGUI.nom.isPressed(this);
            appGUI.preu.isPressed(this);
            appGUI.marca.isPressed(this);
            appGUI.carpeta.isPressed(this);

            text("Nom:", 100, 180);
            text("Preu:", 100, 240);
            text("Marca:", 100, 300);

            if (appGUI.guardar.mouseOverButton(this)) {

                String nom = appGUI.nom.getText();
                String preu = appGUI.preu.getText();
                String marca = appGUI.marca.getText();
                String carpeta = appGUI.carpeta.getText();
                String usuari = appGUI.usuarioActual;

                db.insertProducte(nom, Float.valueOf(preu), marca, carpeta, usuari, appGUI.titolImatgeProducte);

                appGUI.nom.clear();
                appGUI.preu.clear();
                appGUI.marca.clear();
                appGUI.carpeta.clear();

                copiar(appGUI.file, rutaCarpeta, appGUI.titolImatgeProducte);

                appGUI.cargarCardsDesdeBD(this);
            }

            else if(appGUI.bLoad.mouseOverButton(this)){
                selectInput("Selecciona una imatge ...", "fileSelected");
            }
        }
    }

    public void fileSelected(File selection) {
        if (selection == null) {
            println("No s'ha seleccionat cap fitxer.");
        } else {
            // Referència al fitxer imatge
            appGUI.file = selection;
            // Obtenim la ruta del fitxer seleccionat
            appGUI.rutaImatge = selection.getAbsolutePath();
            appGUI.imgProducte = loadImage(appGUI.rutaImatge);  // Actualitzam imatge
            appGUI.titolImatgeProducte = selection.getName();  // Actualitzam títol (igual)
        }
    }

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