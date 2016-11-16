package photoshop;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFileChooser;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;
public class Photoshop extends PApplet{
    private HashMap<PShape, ArrayList<Float>> przyciski = new HashMap();
    private PShape wyborObrazu, opcja1, opcja2;
    private PImage img;
    public static void main(String[] args) {
        PApplet.main("photoshop.Photoshop");
    }
    public void settings(){
        size(800,600);
    }
    public void setup(){
        fill(120, 50, 240);
        ArrayList<Float> polozenie = new ArrayList();
        ustawLokacje(polozenie, 10, 10, 100, 20); 
        wyborObrazu = createShape(RECT, 10, 10, 100, 20);
        przyciski.put(wyborObrazu, polozenie);
        polozenie = new ArrayList();
        ustawLokacje(polozenie, 120, 10, 100, 20); 
        opcja1 = createShape(RECT, 120, 10, 100, 20);
        przyciski.put(opcja1, polozenie);
        polozenie = new ArrayList();
        ustawLokacje(polozenie, 230, 10, 100, 20); 
        opcja2 = createShape(RECT, 230, 10, 100, 20);
        przyciski.put(opcja2, polozenie);
        fill(0,0,0,0);
    }
    public void draw(){
        shape(wyborObrazu);
        shape(opcja1);
        shape(opcja2);
        ArrayList<Float> polozenie = przyciski.get(wyborObrazu); 
        text("WCZYTAJ OBRAZ", polozenie.get(0), polozenie.get(1),100,20);
        polozenie = przyciski.get(opcja1); 
        text("OPCJA1", polozenie.get(0), polozenie.get(1),100, 20);
        polozenie = przyciski.get(opcja2); 
        text("OPCJA2", polozenie.get(0), polozenie.get(1),100, 20);
    }
    public void mousePressed(){
        ArrayList<Float> polozenie = przyciski.get(wyborObrazu); 
        float x = polozenie.get(0), y = polozenie.get(1);
        if(mouseX > x && mouseX < x + polozenie.get(2) && mouseY > y && mouseY < y + polozenie.get(3)){
            JFileChooser fileChooser = new JFileChooser();
            if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                File selectedFile = fileChooser.getSelectedFile();
                img = loadImage(selectedFile.getAbsolutePath());
                image(img, 10, 50);
            }
        }
    }
    private void ustawLokacje(ArrayList<Float> lokacja, float x, float y, float width, float height){
        if(lokacja.size() < 1){
            lokacja.add(0, x); 
            lokacja.add(1, y); 
            lokacja.add(2, width); 
            lokacja.add(3, height); 
        }else{
            lokacja.set(0, x); 
            lokacja.set(1, y); 
            lokacja.set(2, width); 
            lokacja.set(3, height); 
        }
    }
}
