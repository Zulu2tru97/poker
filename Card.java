package poker;

import poker.enums.*;

public class Card implements Comparable<Card>
{
    public static int count;
    Rank num;
    Suit suit;
    String face;


    public Card(Rank rank, Suit suit) 
    {
        this.num = rank;
        this.suit = suit;
        if (num.rank < 10) {face = num.rank + suit.toString();}
        else {face = num.toString() + suit.toString();}
        count +=1;
    }

    public void showFace() 
    {
        System.out.print(face + " ");
        
    }

    @Override
    public int compareTo(Card card) 
    {
        if (this.num.rank == card.num.rank) {return 0;} 
        else if (this.num.rank > card.num.rank) {return 1;} 
        else {return -1;}
    }

}
/** */
