package Class;

import Interface.Purchasable;

public class Vegetable extends CategoryBase 
{

    public static final int POTATO = 0;
    public static final int CARROT = 1;
    public static final int TOMATO = 2;
    public static final int CUCUMBER = 3;

    public Vegetable(int potatoCount, int carrotCount, int tomatoCount, int cucumberCount,
                     double potatoPrice, double carrotPrice, double tomatoPrice, double cucumberPrice) 
    {
        super(4);
        stock[POTATO] = potatoCount;     
        stock[CARROT] = carrotCount;     
        stock[TOMATO] = tomatoCount;     
        stock[CUCUMBER] = cucumberCount; 

        price[POTATO] = potatoPrice;
        price[CARROT] = carrotPrice;
        price[TOMATO] = tomatoPrice;
        price[CUCUMBER] = cucumberPrice;
    }
}
