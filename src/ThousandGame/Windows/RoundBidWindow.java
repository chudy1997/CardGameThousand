package ThousandGame.Windows;

import ThousandGame.Main;
import ThousandGame.Others.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by Karol on 2017-01-17.
 */
public class RoundBidWindow extends JFrame implements ActionListener{
    //DANE

    private final JLabel actualBidDescriptionLabel; //pole z napisem "Actual bid"
    private final JLabel actualBidderDescriptionLabel; //pole z napisem "Actual bidder"
    private final JLabel actualBidderNameLabel; //pole z aktualnie licytującym
    private final JLabel actualTopBidLabel; // pole z aktualnym top bidem
    private final JLabel badValueWarningLabel; //pole z ostrzeżeniem o złej podanej wartości
    private final JButton bidButton; //przycisk potwierdzający wprowadzenie podanej kwoty
    private final JLabel informationLabel; //pole z instrukcją
    private final JTextField givenBidTextField; //pole do wpisania zamierzonej wartości bidu
    private final JButton passButton; //przycisk do pasowania
    private final JLabel playerCardsDescriptionLabel; //pole z napisem "Your cards"
    private final JLabel roundNameLabel; //pole z nazwą rundy
    private final Timer timer; //timer
    private final JLabel topBidderNameDescriptionLabel; //pole z napisem "Actual leader"
    private final JLabel topBidderNameLabel; //pole z nazwą top biddera

    //FUNKCJE
    public RoundBidWindow() {
        //ustawienie parametrów okienka
        Main.initJFrame(this,"Thousand",new Dimension(900,600),Color.ORANGE,true,true);

        //aktualizacja gracza, który zaczyna licytacje i wartosci najwiekszego bidu
        Main.turnPlayerIndex =(Main.cardsDealerIndex +2)%3;
        Main.actualTopBid =100;

        //ustawienie tablicy z bidami graczy (100 dla gracza na musiku)
        Main.actualBids =new int[]{-1,-1,-1};
        Main.actualBids[(Main.cardsDealerIndex +1)%3]= Main.actualTopBid;

        //ustawienie parametrów paska z tekstem "Actual bidder"
        actualBidderDescriptionLabel=Main.initJLabel(this,"Actual bidder",
                JLabel.CENTER, new Rectangle(250, 200, 190, 30),20,Color.BLUE);

        //ustawienie parametrów paska z tekstem "Actual top bid"
        actualBidDescriptionLabel=Main.initJLabel(this,"Actual top bid",
                JLabel.CENTER, new Rectangle(250, 250, 190, 30),20,Color.BLUE);

        //ustawienie parametrów paska z aktualnie największym bidem
        actualTopBidLabel=Main.initJLabel(this,Integer.toString(Main.actualTopBid),
                JLabel.CENTER, new Rectangle(460, 250, 190, 30),20,Color.BLUE);

        //ustawienie parametrów paska z imieniem top biddera
        actualBidderNameLabel=Main.initJLabel(this,Main.players[(Main.cardsDealerIndex +1)%3].getPlayerName(),
                JLabel.CENTER, new Rectangle(460, 200, 190, 30),20,Color.BLUE);

        //ustawienie parametrów paska z ostrzezeniem o zlej podanej wartosci
        badValueWarningLabel=Main.initJLabel(this,"You gave bad value of bid",
                JLabel.CENTER, new Rectangle(350,60,200,30),20,Color.RED);
        badValueWarningLabel.setVisible(false);

        //ustawienie parametrów przycisku do licytacji
        bidButton=Main.initJButton(this,"Bid",new Rectangle(300,350,100,20),20,Color.GREEN,Color.BLUE);

        //ustawienie parametrów paska z instrukcją
        informationLabel=Main.initJLabel(this,"Give your bid",
                JLabel.CENTER, new Rectangle(250, 300, 190, 30),20,Color.BLUE);

        //ustawienie parametrów pola do wprowadzenia wartości bidu
        givenBidTextField = Main.initJTextField(this,new Rectangle(460, 300, 190, 30),20,Color.BLUE);
        givenBidTextField.setFocusable(true);

        //ustawienie parametrów przycisku do pasowania
        passButton=Main.initJButton(this,"Pass",new Rectangle(500,350,100,20),20,Color.GREEN,Color.BLUE);

        //ustawienie parametrów paska z tekstem "Your cards"
        playerCardsDescriptionLabel=Main.initJLabel(this,"Your cards",
                JLabel.CENTER, new Rectangle(350, 380, 200, 30),20,Color.BLUE);

        //ustawienie parametrów paska z numerem rundy
        roundNameLabel=Main.initJLabel(this,"Round no." + Main.roundNumber,
                JLabel.CENTER, new Rectangle(350, 20, 200, 30),20,Color.BLUE);

        //ustawienie parametrów paska z imieniem top biddera
        topBidderNameLabel=Main.initJLabel(this,Main.players[(Main.cardsDealerIndex +1)%3].getPlayerName(),
                JLabel.CENTER, new Rectangle(460, 150, 190, 30),20,Color.BLUE);

        //ustawienie parametrów paska z napisem "Top bidder"
        topBidderNameDescriptionLabel=Main.initJLabel(this,"Top bidder",
                JLabel.CENTER, new Rectangle(250, 150, 190, 30),20,Color.BLUE);

        //ustawienie parametrów timera
        timer = new Timer(500,this);

        //posortowanie i pokazanie kart gracza
        Collections.sort(Main.players[0].getPlayerCardsList());
        showCards(Main.players[0].getPlayerCardsList(),this);

        timer.start();
    }

    //Funkcja sprawdzająca czy już koniec licytacji
    private static boolean checkIfIsBidFinished(int[] bids){
        int i=0;
        for(int j=0;j<3;j++)if(bids[j]!=0)i++;
        return (i==1);
    }

    //Funkcja pokazujaca karty z talii
    static void showCards(LinkedList<Card> cards, JFrame GUI) {
        int i = (900 - 90 * cards.size()) / 2;
        for (Card c : cards) {
            JLabel cardLabel = new JLabel();
            cardLabel.setBounds(i, 407, 73, 103);

            ImageIcon image = new ImageIcon(c.getImagePath());
            cardLabel.setIcon(image);

            GUI.add(cardLabel);
            i += 90;
        }
    }

    //Obsluga przycisków (z racji na podział na kilka ifów - każdy opisałem osobno)
    @Override
    public void actionPerformed(ActionEvent e) {
        //jeśli gracz wciśnie "Bid" lub enter na polu do wprowadzania i jednocześnie była jego kolej (timer zatrzymany) to:
        //sprawdzamy czy jego bid jest odpowiednią wartością (%10 i w zakresie) - jeśli nie - wyświetlamy ostrzeżenie i czekamy - jeśli tak to:
        //wczytujemy podaną wartość i ustawiamy odpowiednie pola gui
        if((e.getSource()== givenBidTextField || e.getSource()==bidButton) && !timer.isRunning()){
            badValueWarningLabel.setVisible(false);
           if((Integer.parseInt(givenBidTextField.getText())%10==0) && Integer.parseInt(givenBidTextField.getText())> Main.actualTopBid &&
                   Integer.parseInt(givenBidTextField.getText())<= Main.players[0].getMaxSumOfCards()) {
                Main.actualBids[0] = Integer.parseInt(givenBidTextField.getText());
                Main.actualTopBid = Main.actualBids[0];
                actualTopBidLabel.setText(Integer.toString(Main.actualTopBid));
                topBidderNameLabel.setText(Main.players[0].getPlayerName());
                Main.turnPlayerIndex =(Main.turnPlayerIndex +1)%3;
                timer.start();
            }
            else {
                badValueWarningLabel.setVisible(true);
                givenBidTextField.setText("");
            }
        }
        //kiedy gracz pasuje zerujemy jego bid
        else if(e.getSource()==passButton){
            if(!checkIfIsBidFinished(Main.actualBids)&& Main.actualBids[0]!=0){
                Main.actualBids[0]=0;
                timer.start();
            }
            Main.turnPlayerIndex =(Main.turnPlayerIndex +1)%3;
        }
        else if(e.getSource()==timer){
            //kiedy licytacja skonczona - liczy kto zwyciężył i rozdaje karty
            if(checkIfIsBidFinished(Main.actualBids)){
                timer.stop();
                Main.turnPlayerIndex = Main.actualBids[0] != 0 ? 0 : (Main.actualBids[1]!=0 ? 1 : 2);
                Main.distribution.distributeStock(Main.turnPlayerIndex);
                Main.showStockWindow =new ShowStockWindow();
                Main.mustValue = Main.actualTopBid;
            }
            // jesli nie ma konca, to sprawdza czy gracz, którego teraz kolej, jeszcze licytuje
            else if (Main.actualBids[Main.turnPlayerIndex]!=0){
                actualBidderNameLabel.setText(Main.players[Main.turnPlayerIndex].getPlayerName());
                //w zależności czy teraz kolej gracza czy bota wykonujemy odpowiednio:
                //jeśli gracza i ma on możliwość bidowania zatrzymujemy timer, aby mógł wykonać bid
                if(Main.turnPlayerIndex ==0 && Main.actualTopBid < Main.players[0].getMaxSumOfCards())timer.stop();
                //jeśli gracza i nie ma on możliwości bidowania zerujemy jego bid
                else if(Main.turnPlayerIndex ==0){
                    Main.actualBids[Main.turnPlayerIndex]=0;
                    Main.turnPlayerIndex =(Main.turnPlayerIndex +1)%3;}
                //jeśli bota i ma możliwość bidowania to biduje i aktualizujemy odpowiednie komponenty gui
                else {
                    Main.actualBids[Main.turnPlayerIndex] = Main.players[Main.turnPlayerIndex].bid(Main.actualTopBid);
                    if (Main.actualBids[Main.turnPlayerIndex] > Main.actualTopBid) {
                        Main.actualTopBid = Main.actualBids[Main.turnPlayerIndex];
                        actualTopBidLabel.setText(Integer.toString(Main.actualTopBid));
                        topBidderNameLabel.setText(Main.players[Main.turnPlayerIndex].getPlayerName());
                    }
                    Main.turnPlayerIndex =(Main.turnPlayerIndex +1)%3;
                }
            }
            //jeśli gracz lub bot nie może bidować przechodzimy dalej
            else Main.turnPlayerIndex =(Main.turnPlayerIndex +1)%3;
        }
    }
}
