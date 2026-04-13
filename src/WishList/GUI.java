package WishList;

import static WishList.Mides.*;
import static java.util.Collections.copy;
import static processing.awt.ShimAWT.selectInput;
import static processing.core.PConstants.ARROW;
import static processing.core.PConstants.HAND;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

import java.io.File;
import java.util.ArrayList;


import javax.xml.crypto.Data;

public class GUI {
    static Colors colors;


    public DataBase db;
    //Logo
    PImage logo;
    PImage logoP1;
    PImage logo2P1;

    PImage user;
    PImage settings;
    PImage menu;
    PImage creu;

    PImage fons;
    File file;
    PImage imgProducte;
    String titolImatgeProducte;
    String rutaImatge;

    PImage brusa, top, maquillatge, auriculars, mescladordj;

    float[] porcentajes;
    String[] carpetas;
    float[] valores;

    int selectedCardIndex = -1;

    boolean pitjat;
    public static boolean colorMode;
    public static boolean menuObert = false;
    //Botons
    public static Botons b1, b2, b3, b4, b5, b6, bprevious, bnext;
    public static Botons guardar;


    public static RoundButton rb1, rb2, rb3, rb4, rb5, rb6;
    public Botons bLoad;

    public static PagedCard paged;

    public static Card card1;

    //Selects
    public String[] selectValues1 = {"Solid Mode", "Color Mode"};
    public String[] selectValues2 = {"euros", "dollars", "pounds"};
    public static Select s1, s2;

    public static SectorDiagram sd1;
    //Enumerat de les pantalles de l'app
    public enum PANTALLA {LOGIN, INICIAL, MENU, SETTINGS, STATISTICS, CARD};

    //Pantalla actual
    public static PANTALLA pantallaActual;

    //Text Field
    public static Text_Field text1, text2, text3;
    public static Text_Field nom, preu, marca, carpeta;

    String errorLogin = "";
    public String usuarioActual = "";



    public GUI(PApplet p5, DataBase db){
        this.db = db;
        pantallaActual = PANTALLA.LOGIN;

        colors = new Colors(p5);

        fons = p5.loadImage("data/fotofons.jpeg");
        fons.resize(p5.width, p5.height);


        logo = p5.loadImage("data/LOGO.JPG");
        logoP1 = p5.loadImage("data/logoP1.JPG");
        logo2P1 = p5.loadImage("data/logo2P1.JPG");

        user = p5.loadImage("data/botoUser.png");
        settings = p5.loadImage("data/botoAjustes.png");
        menu = p5.loadImage("data/botoMenu.png");
        creu = p5.loadImage("data/creu.png");

        brusa = p5.loadImage("data/foto1.jpg");
        top = p5.loadImage("data/foto2.jpg");
        maquillatge = p5.loadImage("data/Foto3.png");
        auriculars = p5.loadImage("data/Foto4.png");
        mescladordj = p5.loadImage("data/foto5.png");


        b1 = new Botons(p5,"Login", p5.width/2-200,p5.height/2+200,400,50);
        b2 = new Botons(p5, "Home", 50, 300, 200, 50);
        b3 = new Botons(p5, "Add Item", 50, 370, 200, 50);
        b4 = new Botons(p5, "Settings", 50, 440, 200, 50);
        b5 = new Botons(p5, "User", 50, 510, 200, 50);
        b6 = new Botons(p5, "Statistics", 50, 580, 200, 50);

        bprevious = new Botons (p5, "<", p5.width/2-10, p5.height-100, 100, 50);
        bnext = new Botons (p5, ">", p5.width/2+100, p5.height-100, 100, 50);
        bLoad = new Botons(p5, "LOAD", (p5.width-300)/2 + 420, 480, 120, 50);
        guardar = new Botons(p5, "SAVE", (p5.width-300)/2, 540, 400, 50);


        rb1 = new RoundButton(p5, user, p5.width/2+550, p5.height/2-375, 60);
        rb2 = new RoundButton(p5, settings, p5.width/2+650, p5.height/2-375, 60);
        rb3 = new RoundButton(p5, menu, 50, 250, 50);
        rb4 = new RoundButton(p5, settings, 120, 250, 50);
        rb5 = new RoundButton(p5, user, 100, 250, 50);
        rb6 = new RoundButton(p5, creu, p5.width-100, 250, 50);

        s1 =new Select(selectValues1, 300,p5.height/2-120,300, 50);
        s2 = new Select(selectValues2, 300,p5.height/2+100,300, 50);

        text1 = new Puntets(p5, p5.width/2-200, p5.height/2+125, 400,50);
        text2 = new Text_Field(p5, p5.width/2-200, p5.height/2+70, 400,50);
        text3 = new Text_Field(p5, p5.width/2-200, p5.height/2+70, 400, 50);
        nom = new Text_Field(p5, (p5.width-300)/2, 300, 400, 50);
        preu = new Text_Field(p5, (p5.width-300)/2, 360, 400, 50);
        marca = new Text_Field(p5, (p5.width-300)/2, 420, 400, 50);
        carpeta = new Text_Field(p5, (p5.width-300)/2, 480, 400, 50);


        card1 = new Card("iphone 17", "1800€", "Apple");
        card1.setDimensions(300, 350, p5.width/2+100, p5.height/2, 100);
        card1.setImage(mescladordj);


        sd1 = new SectorDiagram(p5.width/2, p5.height/2, 200);

        text1.setPlaceholder("Password");
        text2.setPlaceholder("User");

        nom.setPlaceholder("Name");
        preu.setPlaceholder("Price");
        marca.setPlaceholder("Brand");
        carpeta.setPlaceholder("File");


    }
    //Pantalles GUI

    public void dibujoPantallaLogIn(PApplet p5){
        if(colorMode == true){
            p5.background (fons);
        }else{
            p5.background(colors.getColorAt(0));
        }
        logoLogIn(p5, logo);
        b1.display(p5);
        text1.display(p5);
        text2.display(p5);

        p5.fill(255, 0, 0);
        p5.textAlign(p5.CENTER);
        p5.text(errorLogin, p5.width/2, p5.height/2 + 100);

        // p5.textSize(20);
        //p5.text("contrasenya", p5.width/2-190, p5.height/2+157);
        // p5.textSize(20);
        //p5.text("usuari", p5.width/2-190, p5.height/2+105);

    }

    public void dibujoPantallaInicial(PApplet p5){
        if(colorMode == true){
            p5.background (fons);
        }else{
            p5.background(colors.getColorAt(0));
        }
        p5.fill(0);
        p5.rect(0, 0, p5.width, 200);
        logoP1(p5, logoP1);
        logo2P1(p5, logo2P1);
        //s1.display(p5);
        rb1.display(p5);
        rb2.display(p5);
        rb3.display(p5);

        paged.display(p5);
        bnext.display(p5);
        bprevious.display(p5);
        if(menuObert){
            sideBar(p5);
            b2.display(p5);
            b3.display(p5);
            b4.display(p5);
            b5.display(p5);
            b6.display(p5);
        }
    }

    public void dibujoPantallaSettings(PApplet p5) {

        if(colorMode == true){
            p5.background (fons);
        }else{
            p5.background(colors.getColorAt(0));
        }

        p5.fill(0);
        p5.rect(0, 0, p5.width, 200);
        logoP1(p5, logoP1);
        logo2P1(p5, logo2P1);
        p5.fill(colors.getColorAt(4));
        p5.rect(0, 210, p5.width, 80);
        rb3.display(p5);
        rb4.display(p5);
        rb6.display(p5);
        p5.noStroke();
        p5.fill(colors.getColorAt(2));
        p5.textSize(60);
        p5.textWidth((char) 30);
        p5.text("Settings", 500, 270);

        s1.display(p5);
        s2.display(p5);

        if(menuObert){
            sideBar(p5);
            b2.display(p5);
            b3.display(p5);
            b4.display(p5);
            b5.display(p5);
            b6.display(p5);
        }
    }

    public void dibujoPantallaStatistics(PApplet p5){
        if(colorMode == true){
            p5.background (fons);
        }else{
            p5.background(colors.getColorAt(0));
        }

        p5.fill(0);
        p5.rect(0, 0, p5.width, 200);
        logoP1(p5, logoP1);
        logo2P1(p5, logo2P1);

        rb1.display(p5);
        rb2.display(p5);
        rb3.display(p5);
        rb6.display(p5);

        sd1.display(p5);
        if(menuObert){
            sideBar(p5);
            b2.display(p5);
            b3.display(p5);
            b4.display(p5);
            b5.display(p5);
            b6.display(p5);
        }
    }

    public void dibujoPantallaCard(PApplet p5){
        if(colorMode == true){
            p5.background (fons);
        }else{
            p5.background(colors.getColorAt(0));
        }

        p5.fill(0);
        p5.rect(0, 0, p5.width, 200);
        logoP1(p5, logoP1);
        logo2P1(p5, logo2P1);

        rb1.display(p5);
        rb2.display(p5);
        rb3.display(p5);
        rb6.display(p5);
        bLoad.display(p5);

        if(menuObert){
            sideBar(p5);
            b2.display(p5);
            b3.display(p5);
            b4.display(p5);
            b5.display(p5);
            b6.display(p5);
        }

        if(imgProducte!=null){
            float imgX = (p5.width - 300)/2 - 300;
            float imgY = 300;

            float maxSize = 300;

            float ratio = (float) imgProducte.width / imgProducte.height;

            float newW, newH;

            if(ratio > 1){
                newW = maxSize;
                newH = maxSize / ratio;
            } else {
                newH = maxSize;
                newW = maxSize * ratio;
            }

            p5.fill(220);
            p5.rect(imgX - 10, imgY - 10, newW + 20, newH + 20, 10);

            p5.image(imgProducte, imgX, imgY, newW, newH);
        }

        //card1.display(p5, true);
        //text3.display(p5);
        nom.display(p5);
        preu.display(p5);
        marca.display(p5);
        carpeta.display(p5);

        p5.textAlign(p5.CORNER);

        p5.text("Name", ((p5.width-300)/2)+10, 330);
        p5.text("Price", ((p5.width-300)/2)+10, 390);
        p5.text("Brand", ((p5.width-300)/2)+10, 450);
        p5.text("File", ((p5.width-300)/2)+10, 510);

        p5.fill(255);
        p5.textAlign(p5.CENTER);
        p5.textSize(60);
        p5.text("Add item", p5.width/2, 280);

        guardar.display(p5);
    }

    //Zones de la GUI
    public static void logoP1(PApplet p5, PImage logoP1){
        p5.fill(250, 250, 0);
        //p5.circle(p5.width/2,p5.height/2, 30);
        logoP1.resize(150, 0);
        p5.imageMode(PApplet.CENTER);
        p5.image(logoP1, 150, 100);
        p5.imageMode(PApplet.CORNER);

    }
    public static void logo2P1(PApplet p5, PImage logo2P1){
        p5.fill(250, 250, 0);
        //p5.circle(p5.width/2,p5.height/2, 30);
        logo2P1.resize(500, 0);
        p5.imageMode(PApplet.CENTER);
        p5.image(logo2P1, 465, 100);
        p5.imageMode(PApplet.CORNER);
    }

    public static void logoLogIn(PApplet p5, PImage logo){
        p5.fill(250, 250, 0);
        //p5.circle(p5.width/2,p5.height/2, 30);
        logo.resize(300, 0);
        p5.imageMode(PApplet.CENTER);
        p5.image(logo, p5.width/2, p5.height/2-100);
        p5.imageMode(PApplet.CORNER);
    }

    public static void zonaLogo(PApplet p5){
        p5.fill(200,50,100);
        p5.rect(marginH, marginV, logoWidth, logoHeight);
        p5.fill(0);
        p5.text("LOGO", marginH + logoWidth/2, marginV + logoHeight/2);

    }

    public static void sideBar(PApplet p5){
        p5.fill(colors.getColorAt(1));
        p5.rect(0, 200, 300, p5.height);
        p5.fill(0);
        // p5.text("SIDEBAR", marginH + sidebarWidth/2, marginV + logoHeight + sidebarHeight/2);
    }
    public void cargarCardsDesdeBD(PApplet p5){
        String[][] data = db.getProductesUsuari(usuarioActual);
        paged = new PagedCard(8);

        paged.setDimensions(350, 250, 900, 600);
        paged.setData(data);
        paged.setCards(p5);
    }

    public void eliminarCard(){
        ArrayList<Card> nuevas = new ArrayList<>();

        for(Card c : paged.cards){
            if(c != null){
                nuevas.add(c);
            }
        }

        paged.cards = nuevas.toArray(new Card[0]);

        selectedCardIndex = -1;
    }
    public void cargarEstadisticas(String usuario) {
        String[][] data = db.getGastoPorCarpeta(usuario);


        if (data == null || data.length == 0) {
            System.out.println("No hay datos para mostrar");
            return;
        }

        int n = data.length;
        carpetas = new String[n];
        valores = new float[n];

        for (int i = 0; i < n; i++) {
            carpetas[i] = data[i][0];
            valores[i] = Float.parseFloat(data[i][1]);
        }

        // Inicializamos el diagrama una sola vez con los valores reales
        sd1 = new SectorDiagram(800, 500, 200); // Ajusta la X e Y a tu gusto
        sd1.setTexts(carpetas);
        sd1.setValues(valores);
        sd1.setColors(null); // Generará colores aleatorios automáticamente
    }
    }
