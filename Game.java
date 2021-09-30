package poker;

import java.util.Scanner;
import java.util.Set;

/** Main Class Game  */
public class Game 
{
    
    public static void main(String[] args) 
    {   
        Scanner foo = new Scanner(System.in);
        String bar;

        //Get names and cards
        System.out.print("Enter player 1 name: ");
        String name1=foo.nextLine();

        System.out.print("now their cards: ");
        bar = foo.nextLine();
        
        String[] hand1Str = bar.split(" ");

        System.out.print("Enter player 2 name: ");
        String name2=foo.nextLine();

        System.out.print("now their cards: ");
        bar = foo.nextLine();
        
        String[] hand2Str = bar.split(" ");
        
        //Seperate cards into string array
        for(int i=0; i<hand1Str.length-1;i++)
        {   
            if (hand1Str[i].length()==0)
            {
                int index = i;
                System.arraycopy(hand1Str, index + 1, hand1Str, index, hand1Str.length - index - 1);
                System.out.println(hand1Str[i]);
            }
        }
        
        for(int i=0; i<hand2Str.length;i++)
        {   
            if (hand2Str[i].length()==0)
            {
                int index = i;
                System.arraycopy(hand2Str, index + 1, hand2Str, index, hand2Str.length - index - 1);
                System.out.println(hand2Str[i]);
            }
        }
        
        //make new hands
        Hand hand1 = new Hand(hand1Str);
        Hand hand2 = new Hand(hand2Str);
        
        
        // checks hand values and prints out put
        if(hand1.rule.value > hand2.rule.value)
        {
            Set<Card> bestHand = hand1.bestHand;
            System.out.println(" ");
            System.out.println(name1 + " wins with: " + hand1.rule.toString() );
            for (Card c : bestHand){c.showFace();}
        }
        else if(hand1.rule.value < hand2.rule.value)
        {
            Set<Card> bestHand = hand2.bestHand;
            System.out.println(" ");
            System.out.println(name2 + " wins with: " + hand2.rule.toString() );
            for (Card c : bestHand){c.showFace();}
        }
        else if(hand1.rule.value == hand2.rule.value)
        {
            if(hand1.highCard.compareTo(hand2.highCard)>0)
            {
                Set<Card> bestHand = hand1.bestHand;
                System.out.println(" ");
                System.out.println(name1 + " wins with: " + hand1.rule.toString() );
                for (Card c : bestHand){c.showFace();}
            }
            else if(hand1.highCard.compareTo(hand2.highCard)<0)
            {
                Set<Card> bestHand = hand2.bestHand;
                System.out.println(" ");
                System.out.println(name2 + " wins with: " + hand2.rule.toString() );
                for (Card c : bestHand){c.showFace();}
            }
            else if(hand1.highCard.compareTo(hand2.highCard) == 0)
            {
                System.out.println("tie");    
            }     
        }  
        foo.close();  
    }


}
