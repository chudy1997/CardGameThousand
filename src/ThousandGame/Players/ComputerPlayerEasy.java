package ThousandGame.Players;

//KLASA KOMPLETNA

import ThousandGame.Others.*;

/**
 * Created by karol on 21.12.16.
 */
public class ComputerPlayerEasy extends Player {
    //Gracz łatwy - licytuje tylko do 120 i gdy ma meldunek, oddaje 2 pierwsze karty, daje pierwszą kartę z koloru lub
    //gdy zaczyna pierwszą kartę z talii

    public ComputerPlayerEasy(){super();}

    @Override
    public int bid(int bid){
        if(this.hasMarriage() && bid<120)return bid+10;
        return 0;
    }

    @Override
    public int[] giveIndexesOfBackCards(Player player){
        return new int[] {0,0};
    }

    @Override
    public Card giveCardToFight(){
        return getCard(0);
    }

    @Override
    public Card giveCardToFight(String  color){
        for (int i = 0; i< getPlayerCardsList().size(); i++) if(getPlayerCardsList().get(i).getColor().equals(color))return getCard(i);
        return getCard(0);
    }
}
