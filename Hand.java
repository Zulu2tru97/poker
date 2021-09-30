package poker;

import java.util.HashSet;
import java.util.Set;
import poker.enums.*;

/** Hand Class takes in string array makes hand and checks it */
public class Hand 
{
     /** varibles */ 
    Rules rule = Rules.Nothing;
    Card highCard;
    Set<Card> hand = new HashSet<Card>();
    Set<Card> likeNumb = new HashSet<Card>();
    Set<Card> likeSuit = new HashSet<Card>();
    Set<Card> bestHand = new HashSet<Card>();
    boolean hasFlush = false, hasStraight = false; 
    
    //constructor makes cards 
    Hand(String[] handStr)
    {
        for(String c : handStr)
        {          
            String[] strs = c.split("");

            if (strs[0].equals("9")){strs[0]="NNE";}
            if (strs[0].equals("8")){strs[0]="EIT";}
            if (strs[0].equals("7")){strs[0]="SEV";}
            if (strs[0].equals("6")){strs[0]="SIX";}
            if (strs[0].equals("5")){strs[0]="FVE";}
            if (strs[0].equals("4")){strs[0]="FOR";}
            if (strs[0].equals("3")){strs[0]="THR";}
            if (strs[0].equals("2")){strs[0]="TWO";}

            Rank rank = Rank.valueOf(strs[0]);
            Suit suit = Suit.valueOf(strs[1]);
            Card card = new Card(rank,suit);
            hand.add(card);
        }
       highCard = getHiCard(hand); 
       checkHand();
       //System.out.println("highcard: "+highCard.face + " rule: " + rule.toString());
           
    }
    
    /** puts the like numbers and like suits into different list to be counted */
    public void counters() 
    {
        Card[] hanRay= new Card[hand.size()];
        hanRay = hand.toArray(hanRay);

        for (int i=0; i<hanRay.length; i++)
        {
            for (int j=0; j<hanRay.length; j++)
            {
                if (j==i) continue;
                if (hanRay[i].num==hanRay[j].num)
                {
                    likeNumb.add(hanRay[i]);
                    likeNumb.add(hanRay[j]);
                }

                if (hanRay[i].suit==hanRay[j].suit)
                {
                    likeSuit.add(hanRay[i]);
                    likeSuit.add(hanRay[j]);        
                }
            }
        }
        
        sameCard(likeNumb);
        flushes(likeSuit);
    }

    /** returs highest ranking card from a set of cards */
    public Card getHiCard(Set<Card> handList) 
    {
        Card[] hanRay= new Card[handList.size()];
        hanRay = handList.toArray(hanRay);
        Card temp=hanRay[0];
        for (int i = 1; i < hanRay.length; i++) 
        {
            if (hanRay[i].num.rank >= temp.num.rank)
            {
                temp = hanRay[i];
            }
        }
        return temp;

    }

    /** a function made for the set of cards that have the same number */
    public void sameCard(Set<Card> lCards) 
    {
        if (lCards.size()==5)
        {
            Rules tempRule = Rules.FullHouse;
            if (tempRule.value >= rule.value)
            {
                rule = tempRule;
                highCard = getHiCard(lCards);
                bestHand = lCards;
            }
        }

        Card[] hanRay= new Card[lCards.size()];
        hanRay = lCards.toArray(hanRay);
        Set<Card> lone = new HashSet<Card>(), ltwo = new HashSet<Card>();
        Card temp;
        if(hanRay.length>0)
        {
            temp= hanRay[0];

            for (int i = 0; i < hanRay.length-1; i++) 
            {
                if (temp.num == hanRay[i].num){lone.add(hanRay[i]);}
                else {ltwo.add(hanRay[i]);}
            }
        }    

        checkcount(lone,ltwo);
    }

    /** made to check if more than one pare exist or set the Rule for the hand */
    public void checkcount(Set<Card> lone, Set<Card> ltwo) 
    {
        Rules tempRule = Rules.Pair;
        int n = lone.size()+1, m = ltwo.size();
        
        if (m>0)
        {
            tempRule = Rules.TwoPairs;
            if(setRule(tempRule))
            {   
                if (getHiCard(lone).num.rank>getHiCard(ltwo).num.rank){highCard = getHiCard(lone);}
                else {highCard = getHiCard(ltwo);}
                bestHand=likeNumb;
            }
        }

        else if (n == 2)
        {
            if(setRule(tempRule))
            {
                bestHand=lone;
                highCard = getHiCard(lone);
            }
        }

        else if (n == 3)
        {
            tempRule = Rules.ThreeofaKind;
            if(setRule(tempRule))
            {
                bestHand=lone;
                highCard = getHiCard(lone);
            }
        }

        else if (n == 4)
        {
            tempRule = Rules.FourofaKind;
            if(setRule(tempRule))
            {
                bestHand=lone;
                highCard = getHiCard(lone);
            }
        }
    }

    /** for set of like Suits checks if Flush */
    public void flushes(Set<Card> lCards) 
    {
        if (lCards.size()==5)
        {
            if(setRule(Rules.Flush))
            {  
                highCard = getHiCard(hand);
                bestHand = lCards;
                hasFlush = true;
            }

        }
    }

    /** checks if the rule being set is lower than existing if greater sets new rule */
    public boolean setRule(Rules r) 
    {
        if (r.value >= rule.value)
            {
                rule = r;
                return true;  
            }

        return false;
    }

    /** checks the hand for a straight recursively */
    public void Straight( Card hiCard,Set<Card> hnd) 
    {
        Card temp = hiCard;
        Set<Card> tCards = hnd, toRmve = new HashSet<>();

        if (tCards.size()<=1)
        {
            hasStraight = true;
            if(setRule(Rules.Straight))
            {
                bestHand = hand;
                hiCard = getHiCard(hand);
            }
        }

        else
        {
            boolean yes = false;
            for (Card c : tCards)
            {   
                if (c.num.rank == temp.num.rank-1 )
                {
                    toRmve.add(temp);
                    temp = c;
                    yes = true;
                }   
            }

            if (yes) 
            {
                tCards.removeAll(toRmve);
                Straight(temp,tCards);
            }    
        }  
    }
    
    /** checks if has straight and a flush */
    public void StraightFlush() 
    {
        if (hasFlush && hasStraight)
        {
            setRule(Rules.StraightFlush);
            highCard = getHiCard(hand);
            bestHand = hand;
        }
    }

    /** runs the hand checks in order of least to greatest 
     * called in constructor **/
    public void checkHand() 
    {
        counters();
        Straight(highCard, hand);
        StraightFlush();
    }
}