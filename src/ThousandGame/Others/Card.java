package ThousandGame.Others;

//KLASA KOMPLETNA

import java.io.File;
import java.io.IOException;

/**
 * Created by karol on 08.12.16.
 */
public class Card implements Comparable<Card> {
    //DANE

    //kolory
    private enum Color {
        Spades,  //pik
        Clubs,  //trefl
        Diamonds, //karo
        Hearts; //kier
    }
    //figury
    private enum Shape{
        Ace,
        Ten,
        King,
        Queen,
        Jack,
       Nine;
    }

    private Color color; //kolor karty
    private Shape shape; //wzór karty
    private String imagePath; //ścieżka do obrazku karty

    //FUNKCJE

    //konstruktor
    public Card(String color,String shape){
        try {
            this.color = Color.valueOf(color);
            this.shape = Shape.valueOf(shape);
        }
        catch (IllegalArgumentException e){
            System.err.println("IllegalArgumentException: Bad color or shape argument");
        }
        try {
            File currentDirectory = new File(new File(".").getCanonicalPath());
            imagePath = currentDirectory.getAbsolutePath() + "\\src\\ThousandGame\\Images\\" + color + shape + ".png";
        } catch (IOException e){
            System.err.println("IO Exception");
        }
    }

    //Funkcja do porównywania dwóch kart
    @Override
    public int compareTo(Card c) {
        int i = this.getReport()<c.getReport() ? 1 : (this.getReport()==c.getReport() ? 0 : -1);
        int j = this.getValue()<c.getValue() ? 1 :(this.getValue()<c.getValue() ? 0 : -1);
        if(i!=0)return i;
        return j;
    }

    //gettery
    public String getColor(){return color.toString();}
    public String getShape(){return shape.toString();}
    public String getImagePath() {return imagePath;}

    //zwraca tablice stringów z kolorem i wzorem dla danego numeru
    static String[] getCardFromNumber(int number){
        Color[] color = Color.values();
        Shape[] shape = Shape.values();
        return new String[] {color[number/6].toString(),shape[number%6].toString()};
    }

    public String toString(){
        return this.color.toString()+' '+this.shape.toString();
    }

    //zwraca wartość meldunku
    public int getReport(){
        switch(this.color){
            case Spades: return 40;
            case Clubs: return 60;
            case Diamonds: return 80;
            case Hearts: return 100;
            default: return -1; //Nigdy nie zajdzie
        }
    }

    //zwraca wartość danej karty
    public int getValue(){
        switch(this.shape){
            case Ace: return 11;
            case Ten: return 10;
            case King: return 4;
            case Queen: return 3;
            case Jack: return 2;
            case Nine: return 0;
            default: return -1; //Nigdy nie zajdzie
        }
    }

    @Override
    public boolean equals(Object obj){
        if(obj==null)return false;
        if(obj==this)return true;
        if (!(obj instanceof Card))
            return false;
        Card p = (Card)obj;
        return (this.color==p.color && this.shape==p.shape);
    }
}