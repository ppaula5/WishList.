package WishList;

import processing.core.PApplet;

public class WishList extends PApplet {

    Fonts appFonts;
    GUI appGUI;

    public static void main(String[] args) {
        PApplet.main("WishList.WishList");
    }

    public void settings() {
        fullScreen();
    }

    public void setup() {
        appFonts = new Fonts(this);
        appGUI = new GUI(this);
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
                appGUI.dibujoPantallaStatistics(this);
                break;

            case CARD:
                appGUI.dibujoPantallaCard(this);
                break;

        }

        appFonts.displayTipografia(this, 100, 400, 500);

    }


    public void keyPressed() {
        if (key == '0') {
            GUI.pantallaActual = GUI.PANTALLA.LOGIN;
        } else if (key == '1') {
            GUI.pantallaActual = GUI.PANTALLA.INICIAL;
        } else if (key == '2') {
            GUI.pantallaActual = GUI.PANTALLA.MENU;
        } else if (key == '3') {
            GUI.pantallaActual = GUI.PANTALLA.SETTINGS;
        }
        GUI.text1.keyPressed(key, keyCode);
        GUI.text2.keyPressed(key, keyCode);

    }

    public void mousePressed() {
        if (GUI.pantallaActual == GUI.PANTALLA.LOGIN) {
            if (GUI.b1.mouseOverButton(this)) {
                println("B1 has been pressed!!");
                GUI.pantallaActual = GUI.PANTALLA.INICIAL;
            }
            GUI.text1.isPressed(this);
            GUI.text2.isPressed(this);

        } else if (GUI.pantallaActual == GUI.PANTALLA.INICIAL) {
            // --- Gestión del Select ---
            if (GUI.s1.mouseOverSelect(this)) {
                if (GUI.s1.isCollapsed()) {

                    GUI.s1.toggle();
                } else {

                    GUI.s1.update(this);
                    GUI.s1.setCollapsed(true);
                    println("Seleccionado: " + GUI.s1.getSelectedValue());
                }
            } else {

                GUI.s1.setCollapsed(true);
            }
            if (GUI.rb1.mouseOverButton(this)) {
                println("RB1 has been pressed!!");
                GUI.pantallaActual = GUI.PANTALLA.LOGIN;
            }
            if (GUI.rb2.mouseOverButton(this)) {
                println("RB2 has been pressed!!");
                GUI.pantallaActual = GUI.PANTALLA.SETTINGS;
            }
            if (GUI.rb3.mouseOverButton(this)) {
                GUI.menuObert = !GUI.menuObert;
            }
            if (GUI.menuObert) {

                if (GUI.b2.mouseOverButton(this)) {
                    println("HOME");
                    GUI.menuObert = false;
                }

                if (GUI.b3.mouseOverButton(this)) {
                    println("ADD ITEM");
                    GUI.menuObert = false;
                    GUI.pantallaActual = GUI.PANTALLA.CARD;
                }

                if (GUI.b4.mouseOverButton(this)) {
                    println("SETTINGS");
                    GUI.menuObert = false;
                    GUI.pantallaActual = GUI.PANTALLA.SETTINGS;
                }

                if (GUI.b5.mouseOverButton(this)) {
                    println("USER");
                    GUI.menuObert = false;
                    GUI.pantallaActual = GUI.PANTALLA.LOGIN;
                }

                if (GUI.b6.mouseOverButton(this)) {
                    println("STATISTICS");
                    GUI.menuObert = false;
                    GUI.pantallaActual = GUI.PANTALLA.STATISTICS;
                }

                return;

            }
        } else if (GUI.pantallaActual == GUI.PANTALLA.SETTINGS) {
            if (GUI.rb6.mouseOverButton(this)) {
                println("RB6 has been pressed!!");
                GUI.pantallaActual = GUI.PANTALLA.INICIAL;
            } else if (GUI.rb3.mouseOverButton(this)) {
                GUI.menuObert = !GUI.menuObert;
            }
            if (GUI.s1.mouseOverSelect(this)) {
                if (GUI.s1.isCollapsed()) {
                    GUI.s1.toggle();
                } else {
                    GUI.s1.update(this);
                    GUI.s1.setCollapsed(true);
                    println("Seleccionado: " + GUI.s1.getSelectedValue());
                }
                if(GUI.s1.getSelectedValue()=="Color Mode"){
                    GUI.colorMode = true;
                }else{
                    GUI.colorMode = false;
                }
            }
            if (GUI.s2.mouseOverSelect(this)) {
                if (GUI.s2.isCollapsed()) {
                    GUI.s2.toggle();
                } else {
                    GUI.s2.update(this);
                    GUI.s2.setCollapsed(true);
                    println("Seleccionado: " + GUI.s2.getSelectedValue());
                }
            }
            if (GUI.rb3.mouseOverButton(this)) {
                GUI.menuObert = !GUI.menuObert;
            }
            if (GUI.menuObert) {

                if (GUI.b2.mouseOverButton(this)) {
                    println("HOME");
                    GUI.menuObert = false;
                }

                if (GUI.b3.mouseOverButton(this)) {
                    println("ADD ITEM");
                    GUI.menuObert = false;
                }

                if (GUI.b4.mouseOverButton(this)) {
                    println("SETTINGS");
                    GUI.menuObert = false;
                    GUI.pantallaActual = GUI.PANTALLA.SETTINGS;
                }

                if (GUI.b5.mouseOverButton(this)) {
                    println("USER");
                    GUI.menuObert = false;
                    GUI.pantallaActual = GUI.PANTALLA.LOGIN;
                }

                if (GUI.b6.mouseOverButton(this)) {
                    println("STATISTICS");
                    GUI.menuObert = false;
                    GUI.pantallaActual = GUI.PANTALLA.STATISTICS;
                }
            }
        } else if (GUI.pantallaActual == GUI.PANTALLA.STATISTICS) {

            if (GUI.rb6.mouseOverButton(this)) {
                println("B4 has been pressed!!");
                GUI.pantallaActual = GUI.PANTALLA.INICIAL;
            }
            if (GUI.b6.mouseOverButton(this)) {
                println("B6 has been pressed!!");
                GUI.pantallaActual = GUI.PANTALLA.STATISTICS;
            }
            if (GUI.b5.mouseOverButton(this)) {
                println("B5 has been pressed!!");
                GUI.pantallaActual = GUI.PANTALLA.LOGIN;
            }
            if (GUI.b4.mouseOverButton(this)) {
                println("B5 has been pressed!!");
                GUI.pantallaActual = GUI.PANTALLA.SETTINGS;
            }
            if (GUI.b2.mouseOverButton(this)) {
                println("B2 has been pressed!!");
                GUI.pantallaActual = GUI.PANTALLA.INICIAL;
            }
            if (GUI.rb3.mouseOverButton(this)) {
                GUI.menuObert = !GUI.menuObert;
            }
            if (GUI.menuObert) {

                if (GUI.b2.mouseOverButton(this)) {
                    println("HOME");
                    GUI.menuObert = false;
                }

                if (GUI.b3.mouseOverButton(this)) {
                    println("ADD ITEM");
                    GUI.menuObert = false;
                }

                if (GUI.b4.mouseOverButton(this)) {
                    println("SETTINGS");
                    GUI.menuObert = false;
                    GUI.pantallaActual = GUI.PANTALLA.SETTINGS;
                }

                if (GUI.b5.mouseOverButton(this)) {
                    println("USER");
                    GUI.menuObert = false;
                    GUI.pantallaActual = GUI.PANTALLA.LOGIN;
                }

                if (GUI.b6.mouseOverButton(this)) {
                    println("STATISTICS");
                    GUI.menuObert = false;
                    GUI.pantallaActual = GUI.PANTALLA.STATISTICS;
                }
            }

        }
    }
}
