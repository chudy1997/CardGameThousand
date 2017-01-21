package ThousandGame.Windows;

//KLASA KOMPLETNA

import ThousandGame.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

/**
 * Created by Karol on 2017-01-17.
 */
public class GiveBackCardsWindow extends JFrame implements ActionListener {
    //DANE
    private final JRadioButton[] cardsButtonsTable; //tablica przycisków do wybrania karty do walki
    private final ButtonGroup cardsButtonGroup; //grupa przycisków pozwalająca na to by jeden był wybrany
    private final JButton giveButton; //przycisk potwierdzający oddanie karty
    private final JLabel informationLabel; //pole z tekstem informującym

    //FUNKCJE
    public GiveBackCardsWindow(){
        //ustawienie parametrów okienka
        Main.initJFrame(this,"Thousand",new Dimension(900,600),Color.ORANGE,true,true);

        //stworzenie odpowiednio dużej tablicy przycisków i grupy przycisków
        cardsButtonsTable =new JRadioButton[Main.players[0].getPlayerCardsList().size()];
        cardsButtonGroup=new ButtonGroup();

        //ustawienie parametrów przycisku give
        giveButton=Main.initJButton(this,"Give",new Rectangle(400,330,100,20),20,Color.GREEN,Color.BLUE);

        //ustawienie parametrów paska informationLabel
        informationLabel=Main.initJLabel(this,"Choose a card to give",JLabel.CENTER,
                new Rectangle(275,100,350,30),20,Color.BLUE);

        //wstawianie przycisków w odpowiednie miejsca
        for(int k = 0, j = (952 - 90 * Main.players[0].getPlayerCardsList().size()) / 2; k< Main.players[0].getPlayerCardsList().size(); k++,j+=90){
            cardsButtonsTable[k] = new JRadioButton();
            cardsButtonsTable[k].setBounds(j, 520, 20, 20);
            cardsButtonGroup.add(cardsButtonsTable[k]);
            this.add(cardsButtonsTable[k]);
            cardsButtonsTable[k].addActionListener(this);
        }

        Collections.sort(Main.players[0].getPlayerCardsList()); //sortujemy karty gracza do wyświetlania
        RoundBidWindow.showCards(Main.players[0].getPlayerCardsList(),this); //wypisujemy karty gracza
    }

    //sprawdzamy czy został wciśnięty przycisk i czy jest zaznaczona któraś z kart
    //jeśli tak to oddajemy ją za 1 razem pierwszemu przeciwnikowi, za 2 razem drugiemu przeciwnikowi
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==giveButton){
            int i=0, time = Main.players[0].getPlayerCardsList().size()==10 ? 1 : 2; //time jest potrzebne, żeby wiedzieć, który to raz
            for(;i<11-time;i++)if(cardsButtonsTable[i].isSelected())break;

            if(i!=11-time) {
                Main.players[time].addCardToSet(Main.players[0].getPlayerCardsList().remove(i));
                Main.giveBackCardsWindow.setVisible(false);
                Main.giveBackCardsWindow = new GiveBackCardsWindow();
                Main.giveBackCardsWindow.setFocusable(true);
                Main.giveBackCardsWindow.setVisible(true);
                if (time == 2) {
                    if (Main.actualTopBid < Main.players[0].getMaxSumOfCards()) Main.mustWindow = new MustWindow();
                    else {
                        Main.giveBackCardsWindow.setVisible(false);
                        Main.fightWindow = new FightWindow();
                        Main.fightWindow.setFocusable(true);
                        Main.fightWindow.setVisible(true);
                    }
                }
            }
        }
    }
}
