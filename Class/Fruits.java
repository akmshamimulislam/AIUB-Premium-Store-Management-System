package Class;

import Interface.Purchasable;

public class Fruits extends CategoryBase 
{

    public static final int APPLE = 0;
    public static final int BANANA = 1;
    public static final int ORANGE = 2;
    public static final int MANGO = 3;

    public Fruits(int appleCount, int bananaCount, int orangeCount, int mangoCount,
                  double applePrice, double bananaPrice, double orangePrice, double mangoPrice) 
    {
        super(4);
        stock[APPLE] = appleCount;   
        stock[BANANA] = bananaCount; 
        stock[ORANGE] = orangeCount; 
        stock[MANGO] = mangoCount;   

        price[APPLE] = applePrice;
        price[BANANA] = bananaPrice;
        price[ORANGE] = orangePrice;
        price[MANGO] = mangoPrice;
    }
}
