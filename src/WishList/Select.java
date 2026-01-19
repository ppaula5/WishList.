package WishList;

import processing.core.PApplet;

public class Select {
    float x, y, w, h;          // Posició i dimensions
    String[] texts;            // Valors possibles
    String selectedValue;      // Valor Seleccionat

    boolean collapsed = true;  // Plegat / Desplegat
    boolean enabled;           // Abilitat / desabilitat

    float lineSpace = 15;      // Espai entre línies

    public Select(String[] texts, float x, float y, float w, float h){

        this.texts = texts;
        this.selectedValue = "";
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.enabled = true;
        this.collapsed = true;
    }


    public  boolean isEnabled(){
        return  this.enabled;
    }

    public  boolean isCollapsed(){
        return  this.collapsed;
    }

    public String getSelectedValue(){
        return  this.selectedValue;
    }

    public void display(PApplet p5){
        p5.pushStyle();
        p5.stroke(0); p5.strokeWeight(2); p5.fill(255);
        p5.rect(x, y, w, h);

        p5.fill(100);
        p5.rect(x + w - 30, y, 30, h);

        p5.fill(0); p5.stroke(0);
        p5.triangle(x + w - 25, y+5, x + w - 15, y + 25, x + w - 5 , y+5);

        p5.fill(0); p5.textSize(14);
        p5.textAlign(p5.LEFT);
        p5.text(selectedValue, x + 10, y + 20);

        if(!this.collapsed){

            p5.fill(255); p5.stroke(0);
            p5.rect(x, y+h, w, (h + lineSpace)*texts.length);

            for(int i=0; i<texts.length; i++){

                if(i== clickedOption(p5)){
                    p5.fill(200); p5.noStroke();
                    p5.rect(x+4, y+4 + h + (h + lineSpace)*i - 2, w -8, h + lineSpace - 8);
                }

                p5.fill(0);
                p5.text(texts[i], x + 10, y + h + 25 + (h + lineSpace)*i);
            }
        }
        p5.popStyle();
    }

    public void setCollapsed(boolean b){
        this.collapsed = b;
    }

    public void toggle(){
        this.collapsed = !this.collapsed;
    }


    public void update(PApplet p5){
        int option = clickedOption(p5);
        selectedValue = texts[option];
    }

    // Indica si el cursor està sobre el select
    public boolean mouseOverSelect(PApplet p5){
        if(this.collapsed){
            return (p5.mouseX >= x) &&
                    (p5.mouseX <= x + w) &&
                    (p5.mouseY >= y) &&
                    (p5.mouseY <= y + h);
        }
        else {
            return (p5.mouseX>= x) &&
                    (p5.mouseX<= x + w) &&
                    (p5.mouseY>= y) &&
                    (p5.mouseY<= y + h + (h + lineSpace)*texts.length);
        }
    }

    int clickedOption(PApplet p5){
        int i = (int)p5.map(p5.mouseY, y + h, y + h + (h + lineSpace)*texts.length,
                0, texts.length);
        return i;
    }
}

