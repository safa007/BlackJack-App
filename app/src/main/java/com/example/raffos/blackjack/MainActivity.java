package com.example.raffos.blackjack;

import android.media.Image;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    ImageView[] playerCards = new ImageView[6]; //To store the playerCard images
    ImageView[] dealerCards = new ImageView[6]; //To store the dealerCard images
    Button startBtn, hitBtn, standBtn;
    TextView dealerTotalText, playerTotalText;

    //To access the dealer and the player(s)
    Player[] players = new Player[2];
    Player dealer = new Player(0);
    Player playerOne = new Player(1);

    boolean isGameOver = false;
    int cardNum = 0; //Keeps track of which card we are at in the deck
    int turn = 0; //Keeps track of whose turn it is

    //Set up the deck
    ArrayList<Card> deck = new ArrayList<>();

    //Used to update the totals for players
    Handler playerTextHandler = new Handler();
    Runnable updatePlayerText = new Runnable() {
        @Override
        public void run() {
            //Update dealer total
            //dealerTotalText.setText(Integer.toString(players[0].getTotal()));
            //Update player total
            playerTotalText.setText(Integer.toString(players[1].getTotal()));

            playerTextHandler.postDelayed(this, 100);
        }
    };

    Handler winHandler = new Handler();
    Runnable updateWin = new Runnable() {
        @Override
        public void run() {

            if(players[1].getTotal() > 21){
                //Player loses

                startBtn.setEnabled(true);
                standBtn.setEnabled(false);
                hitBtn.setEnabled(false);
            }
            //If player has a Blackjack
            else if(players[1].getTotal() == 21 && players[1].getNumCards() == 2){

                startBtn.setEnabled(true);
                standBtn.setEnabled(false);
                hitBtn.setEnabled(false);
            }
            //If the player hit the stand button or has a total over 21, find out who the winner is
            if(!standBtn.isEnabled()) {
                if (players[0].getTotal() > players[1].getTotal()) {
                    //Dealer wins
                }
                else if (players[0].getTotal() == players[1].getTotal()){
                    //It's a tie
                }
                else{
                    //Player wins
                }
                startBtn.setEnabled(true);
            }
            winHandler.postDelayed(this, 100);
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Various design elements that need to be accessed globally
        dealerCards[0] = (ImageView) findViewById(R.id.dealerCard1);
        dealerCards[1] = (ImageView) findViewById(R.id.dealerCard2);
        dealerCards[2] = (ImageView) findViewById(R.id.dealerCard3);
        dealerCards[3] = (ImageView) findViewById(R.id.dealerCard4);
        dealerCards[4] = (ImageView) findViewById(R.id.dealerCard5);
        dealerCards[5] = (ImageView) findViewById(R.id.dealerCard6);

        playerCards[0] = (ImageView) findViewById(R.id.playerCard1);
        playerCards[1] = (ImageView) findViewById(R.id.playerCard2);
        playerCards[2] = (ImageView) findViewById(R.id.playerCard3);
        playerCards[3] = (ImageView) findViewById(R.id.playerCard4);
        playerCards[4] = (ImageView) findViewById(R.id.playerCard5);
        playerCards[5] = (ImageView) findViewById(R.id.playerCard6);

        dealerTotalText = (TextView) findViewById(R.id.dealerTotalText);
        playerTotalText = (TextView) findViewById(R.id.playerTotalText);

        players[0] = dealer;
        players[1] = playerOne;

        startBtn = (Button) findViewById(R.id.startBtn);
        hitBtn = (Button) findViewById(R.id.hitBtn);
        standBtn = (Button) findViewById(R.id.standBtn);

        //Reset total fields
        playerTotalText.setText("");
        dealerTotalText.setText("");

        deck.add(new Card(11, 'H', '0', R.drawable.ace_of_hearts));
        deck.add(new Card(11, 'S', '0', R.drawable.ace_of_spades));
        deck.add(new Card(11, 'D', '0', R.drawable.ace_of_diamonds));
        deck.add(new Card(11, 'C', '0', R.drawable.ace_of_clubs));
        deck.add(new Card(2, 'H', '0', R.drawable.two_of_hearts));
        deck.add(new Card(2, 'S', '0', R.drawable.two_of_spades));
        deck.add(new Card(2, 'D', '0', R.drawable.two_of_diamonds));
        deck.add(new Card(2, 'C', '0', R.drawable.two_of_clubs));
        deck.add(new Card(3, 'H', '0', R.drawable.three_of_hearts));
        deck.add(new Card(3, 'S', '0', R.drawable.three_of_spades));
        deck.add(new Card(3, 'D', '0', R.drawable.three_of_diamonds));
        deck.add(new Card(3, 'C', '0', R.drawable.three_of_clubs));
        deck.add(new Card(4, 'H', '0', R.drawable.four_of_hearts));
        deck.add(new Card(4, 'S', '0', R.drawable.four_of_spades));
        deck.add(new Card(4, 'D', '0', R.drawable.four_of_diamonds));
        deck.add(new Card(4, 'C', '0', R.drawable.four_of_clubs));
        deck.add(new Card(5, 'H', '0', R.drawable.five_of_hearts));
        deck.add(new Card(5, 'S', '0', R.drawable.five_of_spades));
        deck.add(new Card(5, 'D', '0', R.drawable.five_of_diamonds));
        deck.add(new Card(5, 'C', '0', R.drawable.five_of_clubs));
        deck.add(new Card(6, 'H', '0', R.drawable.six_of_hearts));
        deck.add(new Card(6, 'S', '0', R.drawable.six_of_spades));
        deck.add(new Card(6, 'D', '0', R.drawable.six_of_diamonds));
        deck.add(new Card(6, 'C', '0', R.drawable.six_of_clubs));
        deck.add(new Card(7, 'H', '0', R.drawable.seven_of_hearts));
        deck.add(new Card(7, 'S', '0', R.drawable.seven_of_spades));
        deck.add(new Card(7, 'D', '0', R.drawable.seven_of_diamonds));
        deck.add(new Card(7, 'C', '0', R.drawable.seven_of_clubs));
        deck.add(new Card(8, 'H', '0', R.drawable.eight_of_hearts));
        deck.add(new Card(8, 'S', '0', R.drawable.eight_of_spades));
        deck.add(new Card(8, 'D', '0', R.drawable.eight_of_diamonds));
        deck.add(new Card(8, 'C', '0', R.drawable.eight_of_clubs));
        deck.add(new Card(9, 'H', '0', R.drawable.nine_of_hearts));
        deck.add(new Card(9, 'S', '0', R.drawable.nine_of_spades));
        deck.add(new Card(9, 'D', '0', R.drawable.nine_of_diamonds));
        deck.add(new Card(9, 'C', '0', R.drawable.nine_of_clubs));
        deck.add(new Card(10, 'H', 'T', R.drawable.ten_of_hearts));
        deck.add(new Card(10, 'S', 'T', R.drawable.ten_of_spades));
        deck.add(new Card(10, 'D', 'T', R.drawable.ten_of_diamonds));
        deck.add(new Card(10, 'C', 'T', R.drawable.ten_of_clubs));
        deck.add(new Card(10, 'H', 'J', R.drawable.jack_of_hearts));
        deck.add(new Card(10, 'S', 'J', R.drawable.jack_of_spades));
        deck.add(new Card(10, 'D', 'J', R.drawable.jack_of_diamonds));
        deck.add(new Card(10, 'C', 'J', R.drawable.jack_of_clubs));
        deck.add(new Card(10, 'H', 'Q', R.drawable.queen_of_hearts));
        deck.add(new Card(10, 'S', 'Q', R.drawable.queen_of_spades));
        deck.add(new Card(10, 'D', 'Q', R.drawable.queen_of_diamonds));
        deck.add(new Card(10, 'C', 'Q', R.drawable.queen_of_clubs));
        deck.add(new Card(10, 'H', 'K', R.drawable.king_of_hearts));
        deck.add(new Card(10, 'S', 'K', R.drawable.king_of_spades));
        deck.add(new Card(10, 'D', 'K', R.drawable.king_of_diamonds));
        deck.add(new Card(10, 'C', 'K', R.drawable.king_of_clubs));

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Reset total fields
                playerTotalText.setText("");
                dealerTotalText.setText("");

                //Reset the game when start is pressed
                resetGame();
                startBtn.setEnabled(false); //Gray out the start button
                playBlackJack();
            }
        });
    }

    //Game engine
    public void playBlackJack(){

        //Run the threads to update text and check for wins
        playerTextHandler.postDelayed(updatePlayerText, 100);
        winHandler.postDelayed(updateWin, 100);

        //Deal cards to all of the players
        deal();

        //When hit button is pressed
        hitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (players[1].getTotal() < 21) {
                    //Add another card to player's hand
                    players[1].addCard(deck.get(cardNum++));
                    //Set the image of the next card of the dealer to the new card
                    playerCards[players[1].getNumCards() -
                            1].setImageResource(players[1].getCard(players[1].getNumCards() - 1).getCardID());
                    playerCards[players[1].getNumCards() - 1].setVisibility(View.VISIBLE);
                }
            }
        });

        //When stand button is pressed
        standBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dealerCards[0].setImageResource(players[0].getCard(0).getCardID());
                dealerTotalText.setText(Integer.toString(players[0].getTotal()));

                //While the dealer's total is less than 17
                while(players[0].getTotal() < 17){

                    //Add another card to dealer's hand
                    players[0].addCard(deck.get(cardNum++));
                    //Set the image of the next card of the dealer to the new card
                    dealerCards[players[0].getNumCards() -
                            1].setImageResource(players[0].getCard(players[0].getNumCards() - 1).getCardID());
                    dealerCards[players[0].getNumCards() - 1].setVisibility(View.VISIBLE);
                    dealerTotalText.setText(Integer.toString(players[0].getTotal()));
                }
                standBtn.setEnabled(false);
                hitBtn.setEnabled(false);
            }
        });
    }

    //Shuffle the deck using a random shuffling algorithm
    public void shuffle(){
        //Shuffle the deck three times
        Collections.shuffle(deck);
        Collections.shuffle(deck);
        Collections.shuffle(deck);
    }

    //Deal cards to each player
    public void deal(){
        //Deal to player first
        turn = (turn + 1) % 2;
        players[turn].addCard(deck.get(cardNum++));
        playerCards[0].setImageResource(players[turn].getCard(0).getCardID());
        playerCards[0].setVisibility(View.VISIBLE);

        //Deal to the dealer last
        turn = (turn + 1) % 2;
        players[turn].addCard(deck.get(cardNum++));
        dealerCards[0].setImageResource(R.drawable.back);
        dealerCards[0].setVisibility(View.VISIBLE);

        turn = (turn + 1) % 2;
        players[turn].addCard(deck.get(cardNum++));
        playerCards[1].setImageResource(players[turn].getCard(1).getCardID());
        playerCards[1].setVisibility(View.VISIBLE);

        turn = (turn + 1) % 2;
        players[turn].addCard(deck.get(cardNum++));
        dealerCards[1].setImageResource(players[turn].getCard(1).getCardID());
        dealerCards[1].setVisibility(View.VISIBLE);

        turn = (turn + 1) % 2;
    }

    //Needs to reset the text fields, images, and deck
    public void resetGame(){

        //Reset and shuffle deck
        cardNum = 0;
        shuffle();
        players[0].resetCards();
        players[1].resetCards();
        standBtn.setEnabled(true);
        hitBtn.setEnabled(true);

        //Reset images of cards on the panels
        for(int i = 2; i < playerCards.length; ++i){
            playerCards[i].setVisibility(View.GONE);
        }
        for(int i = 2; i < dealerCards.length; ++i){
            dealerCards[i].setVisibility(View.GONE);
        }
        turn = 0; //Reset turn
    }
}
