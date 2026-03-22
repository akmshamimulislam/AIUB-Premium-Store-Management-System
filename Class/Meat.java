package Class;

import Interface.Purchasable;

public class Meat extends CategoryBase 
{

    public static final int BEEF = 0;
    public static final int CHICKEN = 1;
    public static final int MUTTON = 2;

    public Meat(int beefCount, int chickenCount, int muttonCount,
                double beefPrice, double chickenPrice, double muttonPrice) 
    {
        super(3);
        stock[BEEF] = beefCount;       
        stock[CHICKEN] = chickenCount; 
        stock[MUTTON] = muttonCount;   

        price[BEEF] = beefPrice;
        price[CHICKEN] = chickenPrice;
        price[MUTTON] = muttonPrice;
    }
}
