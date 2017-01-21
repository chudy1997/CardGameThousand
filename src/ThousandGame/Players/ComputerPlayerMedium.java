package ThousandGame.Players;

//KLASA KOMPLETNA

import ThousandGame.Others.*;

import java.util.Collections;

/**
 * Created by karol on 01.01.17.
 */
public class ComputerPlayerMedium extends Player{
    //Gracz średni - licytuje zawsze, gdy może, gdy ma meldunek licytuje do minimum 140 (lub wiecej jesli moze), oddaje najgorsze karty (wartością)
    //gdy zaczyna daje asa, jeśli ma i królową, jeśli ma meldunek, a jeśli nie to szuka najlepszej karty, a gdy kończy daje najlepszą kartę z koloru

    @Override
    public int bid(int bid){
        if(!this.hasMarriage() && bid<120)return bid+10;
        if(this.hasMarriage() && bid<Math.max(140, getMaxSumOfCards()-60))return bid+10;
        return 0;
    }

    @Override
    public int[] giveIndexesOfBackCards(Player player){
        return findWorse();
    }

    @Override
    public Card giveCardToFight(String  color){
        Collections.sort(getPlayerCardsList());
        for (Card c: getPlayerCardsList()) if(c.getColor().equals(color)){
            getPlayerCardsList().remove(c);return c;}
        return getCard(0);
    }
    @Override
    public Card giveCardToFight(){
        int max=findMax();
        if(getPlayerCardsList().get(max).getValue()==11)return getPlayerCardsList().remove(max);
        if(hasMarriage()) return findQueen();
        return getPlayerCardsList().remove(max);
    }
    public ComputerPlayerMedium(){
        super();
    }

    //znajduje najgorsza karte w talii
    private int[] findWorse(){
        int[] cards= new int[] {0,1};
        for(int j = 2; j<this.getPlayerCardsList().size(); j++){
            if(this.getPlayerCardsList().get(j).getValue()<this.getPlayerCardsList().get(cards[0]).getValue() && this.getPlayerCardsList().get(cards[0]).getValue()<=this.getPlayerCardsList().get(cards[1]).getValue())cards[1]=j;
            else if(this.getPlayerCardsList().get(j).getValue()<this.getPlayerCardsList().get(cards[1]).getValue() && this.getPlayerCardsList().get(cards[1]).getValue()<=this.getPlayerCardsList().get(cards[0]).getValue())cards[0]=j;
        }
        return cards;
    }

    //znajduje indeks najlepszej karty w talii
    private int findMax(){
        int i=0;
        for(int j = 1; j< getPlayerCardsList().size(); j++)if(getPlayerCardsList().get(j).getValue()> getPlayerCardsList().get(i).getValue())i=j;
        return i;
    }

    //znajduje królową w talii
    private Card findQueen(){
        for (int j = 0; j< getPlayerCardsList().size()-1; j++){
            if(getPlayerCardsList().get(j).getColor().equals(getPlayerCardsList().get(j+1).getColor()) && getPlayerCardsList().get(j).getShape().equals("King") && getPlayerCardsList().get(j+1).getShape().equals("Queen"))return getPlayerCardsList().remove(j+1);
        }
        return null;
    }
}
