package Class;

import Interface.Purchasable;

public abstract class CategoryBase implements Purchasable 
{

    protected int[] stock;
    protected double[] price;

    protected CategoryBase(int itemCount) 
    {
        stock = new int[itemCount];
        price = new double[itemCount];
    }

 
    public double calculateTotal(int itemIndex, int qty) 
    {
        return qty * price[itemIndex];
    }


    public boolean hasStock(int itemIndex, int qty) 
    {
        return qty >= 0 && qty <= stock[itemIndex];
    }


    public void reduceStock(int itemIndex, int qty) 
    {
        stock[itemIndex] -= qty;
    }

    public int getStock(int idx) 
    { return stock[idx]; }
    public double getPrice(int idx) 
    { return price[idx]; }

    public void setStock(int idx, int val) 
    { stock[idx] = val; }
    public void setPrice(int idx, double val) 
    { price[idx] = val; }
}
