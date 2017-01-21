package ThousandGame.Players;

//KLASA KOMPLETNA

import ThousandGame.Others.*;

/**
 * Created by karol on 21.12.16.
 */
public class ComputerPlayerHard extends Player {
    //Gracz trudny - niezrobiony, zaimplementowany przejściowo , jak gracz latwy

    public ComputerPlayerHard(){super();}

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
