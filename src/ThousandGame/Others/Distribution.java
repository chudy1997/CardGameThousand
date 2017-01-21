package ThousandGame.Others;

//KLASA KOMPLETNA

import ThousandGame.Main;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by karol on 21.12.16.
 */

public class Distribution {
    //DANE
    private boolean[] availabilityOfCardsTable; //tablica pokazująca czy karta jest jeszcze dostępna
    private static Random random = new Random(); //random
    private static LinkedList<Card> stockList; //lista z kartami na musik rozmiar 3

    //FUNKCJE
    //konstruktor
    public Distribution(){
        stockList =new LinkedList<>();

        //jedynkowanie tablicy dostępności
        availabilityOfCardsTable =new boolean[24];
        for (int i=0;i<24;i++) {
            availabilityOfCardsTable[i]=true;}

        //generowanie kart do musiku
        for (int i=0;i<3;i++) {
            String[] s = Card.getCardFromNumber(getAvailableIndex());
            stockList.add(new Card(s[0], s[1]));
        }

        //generowanie kart dla graczy
        for(int i=0;i<3;i++)
            for (int j=0;j<7;j++) {
                String[] s = Card.getCardFromNumber(getAvailableIndex());
                Main.players[i].addCardToSet(new Card(s[0],s[1]));
            }
    }

    //daje karty z musiku graczowi o podanym indeksie, a w wypadku graczy komputerowych oddaje również po jednej karcie przeciwnikom
    public void distributeStock(int indexOfPlayer){
        for(Card c: stockList)
            Main.players[indexOfPlayer].addCardToSet(c);

        if(indexOfPlayer!=0) {
            int[] i = Main.players[indexOfPlayer].giveIndexesOfBackCards(Main.players[indexOfPlayer]);
            Main.players[(indexOfPlayer + 1) % 3].addCardToSet(Main.players[indexOfPlayer].getCard(i[0]));
            Main.players[(indexOfPlayer + 2) % 3].addCardToSet(Main.players[indexOfPlayer].getCard(i[1]));
        }
    }

    //zwraca wolny indeks na podstawie tablicy availabilityOfCardsTable
    private int getAvailableIndex(){
        int i = -1;
        do{i=random.nextInt(24);}
        while(!availabilityOfCardsTable[i]);
        availabilityOfCardsTable[i]=false;
        return i;
    }

    //getter
    public LinkedList<Card> getStock() {return stockList;}
}
