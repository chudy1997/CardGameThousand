package ThousandGame.Players;

//KLASA KOMPLETNA

import ThousandGame.Others.*;

import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by karol on 17.12.16.
 */
public class Player {
    //DANE
    private LinkedList<Card> playerCardsList; //lista kart gracza
    private String playerName; //imie gracza
    private static int numberOfPlayers=0; //liczba graczy zainicjalizowanych (do nr gracza i nazwy)
    private int playerPoints; //liczba punktow gracza

    //FUNKCJE
    public Player (){
        String[] defaultNamesTable={"Player 1","Player 2","Player 3"};
        this.playerName =defaultNamesTable[numberOfPlayers++];
        this.playerPoints =0;
        this.playerCardsList =new LinkedList<>();
    }
    public Player(String playerName){
        this.playerPoints =0;
        this.playerName = playerName;
        this.playerCardsList =new LinkedList<>();
        numberOfPlayers=1;
    }

    public void addCardToSet(Card card){this.playerCardsList.add(card);} //dodaje karte do setu
    public void addPoints(int points) {this.playerPoints +=points;} //dodaje punkty graczowi
    public int bid(int bid){return -1;} // bid - nadpisywany potem - u komputerowych graczy zwraca bid
    public LinkedList<Card> getPlayerCardsList(){Collections.sort(playerCardsList); return playerCardsList;} //zwraca set kart
    public String getPlayerName() {return playerName;} //zwraca imie
    public int getPlayerPoints() {return playerPoints;} //zwraca liczbę punktow
    public Card getCard(int index){return playerCardsList.remove(index);} //zwraca kartę o podanym indeksie
    public int[] giveIndexesOfBackCards(Player player){return null;} //u komputerowych graczy zwraca indeksy kart do oddania sąsiadom

    //zwraca pierwszą kartę z odpowiedniego koloru lub jeśli niepotrzebne to pierwszą z setu
    public Card giveCardToFight(){
        return getCard(0);
    }
    public Card giveCardToFight(String  color) {
        for (Card c : playerCardsList)
            if (c.getColor().equals(color)) {
                playerCardsList.remove(c);
                return c;
            }
        return getCard(0);
    }

    //sprawdza czy w secie występuje karta
    public boolean hasCard(Card c){
        for (Card d: playerCardsList) if(c.equals(d))return true;
        return false;
    }

    //sprawdza czy w secie jest karta danego koloru
    public boolean hasCardInColor(String color){
        for (Card c: playerCardsList)if(c.getColor().equals(color))return true;
        return false;
    }

    //sprawdza czy w secie jest para
    public boolean hasMarriage(){
        for(Card c: playerCardsList)
            if(c.getShape().equals("King")){
                for(Card d: playerCardsList)
                    if(d.getShape().equals("Queen") && c.getColor().equals(d.getColor()))return true;
            }
        return false;
    }

    //zwraca maksymalna liczba punktow do ugrania
    public int getMaxSumOfCards(){
        int sum=120;
        for(int i = 0; i< playerCardsList.size(); i++)
            for(int j = i+1; j< playerCardsList.size(); j++)
                if(playerCardsList.get(i).getColor().equals(playerCardsList.get(j).getColor()) && playerCardsList.get(i).getValue()+ playerCardsList.get(j).getValue()==7)sum+= playerCardsList.get(i).getReport();
        return sum;
    }
}