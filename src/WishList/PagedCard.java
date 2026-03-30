package WishList;

import processing.core.PApplet;
import processing.core.PImage;

public class PagedCard {
    Card[] cards;
    String[][] cardsData;

    int numCards;            // Número total de Cards
    int numCardsPage;        // Número de Cards en 1 Pàgina

    int numPage;
    int numTotalPages;

    float x, y, w, h;
    int selectedCard = -1;

    // Constructor
    public PagedCard(int ncp) {
        this.numCardsPage = ncp;
        this.numPage = 0;
    }

    // Setters

    public void setDimensions(float x, float y, float w, float h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public void setData(String[][] d) {
        this.cardsData = d;
        this.numCards = d.length;
        this.numTotalPages = (int) Math.ceil((float) numCards / numCardsPage);
    }

    public void setCards() {

        cards = new Card[this.cardsData.length];

        int cardsPerPage = 4;
        int cols = 2;
        int rows = 2;

        float cardW = w / cols;
        float cardH = h / rows;
        float b = 10;

        for (int np=0; np<=numTotalPages; np++) {
            int firstCardPage = cardsPerPage * np;
            int lastCardPage  = firstCardPage + cardsPerPage - 1;

            for (int i = firstCardPage; i <= lastCardPage; i++) {

                if (i < cards.length) {

                    int localIndex = i - firstCardPage;

                    int col = localIndex % cols;
                    int row = localIndex / cols;

                    float xCard = x + col * cardW;
                    float yCard = y + row * cardH;

                    cards[i] = new Card(cardsData[i]);
                    cards[i].setDimensions(xCard, yCard, cardW - b, cardH - b, b);
                }
            }
        }
    }

    public void setImages(PImage img1, PImage img2, PImage img3, PImage img4) {
        PImage img;
        for (int i=0; i<cards.length; i++) {
            if (cards[i]!=null) {
                if (cards[i].nom=="Item 1") {
                    img = img1;
                } else if(cards[i].nom=="Item 2"){
                    img = img2;
                } else if(cards[i].nom=="Item 3"){
                    img = img3;
                } else {
                    img = img4;
                }

                cards[i].setImage(img);
            }
        }
    }

    public void nextPage() {
        if (this.numPage<this.numTotalPages) {
            this.numPage++;
        }
    }

    public void prevPage() {
        if (this.numPage>0) {
            this.numPage--;
        }
    }

    // Dibuixa taula
    public void display(PApplet p5) {

        p5.pushStyle();

        // Dibuixa Cards corresponent a la Pàgina
        int firstCardPage = numCardsPage*numPage;
        int lastCardPage  = numCardsPage*(numPage+1) - 1;
        for (int i = firstCardPage; i <= lastCardPage; i++) {
            if (i<cards.length && cards[i]!=null) {
                cards[i].display(p5, i==this.selectedCard);
            }
        }

        // Informació de la Pàgina
        p5.fill(0);
        p5.text("Pag: "+(this.numPage+1)+" / "+(this.numTotalPages+1), x + w + 50, y+10);

        p5.popStyle();
    }

    public void checkCardSelection(PApplet p5){

        boolean selected = false;
        int firstCardPage = numCardsPage*numPage;
        int lastCardPage  = numCardsPage*(numPage+1) - 1;
        for (int i = firstCardPage; i <= lastCardPage; i++) {
            if (i<cards.length && cards[i]!=null && cards[i].mouseOver(p5)) {
                selectedCard = i;
                selected = true;
                break;
            }
        }
        if(!selected){
            selectedCard = -1;
        }
    }

    public boolean checkMouseOver(PApplet p5){

        int firstCardPage = numCardsPage*numPage;
        int lastCardPage  = numCardsPage*(numPage+1) - 1;
        for (int i = firstCardPage; i <= lastCardPage; i++) {
            if (i<cards.length && cards[i]!=null && cards[i].mouseOver(p5)) {
                return true;
            }
        }
        return false;
    }

    public void printSelectedCard(PApplet p5){
        if(selectedCard !=-1){
            Card cSelected = cards[selectedCard];
            p5.pushStyle();
            p5.fill(0); p5.textSize(18);
            p5.text("Seleccionada: ", 900, 300);
            p5.textSize(24);
            p5.text(cSelected.nom, 900, 340);
            p5.popStyle();
        }
    }

    public PagedCard(String[][] data){

        cards = new Card[data.length];

        for(int i = 0; i < data.length; i++){
            cards[i] = new Card(data[i]);
        }
    }

}

