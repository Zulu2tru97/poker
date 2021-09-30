package poker;

public class enums 
{
    public enum Suit {C,D,H,S};

    public enum Rank
    {
        TWO(2),
        THR(3),
        FOR(4),
        FVE(5),
        SIX(6),
        SEV(7),
        EIT(8),
        NNE(9),
        T(10),
        J(11),
        Q(12),
        K(13),
        A(14);
        
        public int rank;
        Rank(int num)
        {
            this.rank = num;
        }
    }
     
    enum Rules 
    {
        StraightFlush(9),/*5 cards of the same suit with consecutive values*/
        FourofaKind(8),/*4 cards with the same value.*/
        FullHouse(7),/*3 cards of the same value, with the remaining 2 cards forming a pair*/
        Flush(6),/*Hand contains 5 cards of the same suit.*/
        Straight(5),/*Hand contains 5 cards with consecutive values.*/
        ThreeofaKind(4),/*3 of the cards in the hand have the same value*/
        TwoPairs(3),/*The hand contains 2 different pairs*/
        Pair(2),/*2 of the 5 cards in the hand have the same value*/
        HighCard(1),/*are ranked by the value of their highest card*/
        Nothing(0);/*hand not dealt or not dealt properly*/

        int value;

        Rules(int numb)
        {
            value = numb;
        }
    }
}
