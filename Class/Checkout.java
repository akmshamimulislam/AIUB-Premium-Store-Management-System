package Class;
import Class.Fruits;
import Class.Vegetable;
import Class.Meat;

public class Checkout 
{
    private Fruits fruits;
    private Vegetable vegetables;
    private Meat meat;

    public Checkout(Fruits fruits, Vegetable vegetables, Meat meat) 
    {
        this.fruits = fruits;
        this.vegetables = vegetables;
        this.meat = meat;
    }

    public double grandTotal(int a,int b,int o,int m,int p,int c,int t,int cu,int beef,int chick,int mut) 
    {

        double total = 0;

        total =total + fruits.calculateTotal(Fruits.APPLE, a);
        total =total + fruits.calculateTotal(Fruits.BANANA, b);
        total =total + fruits.calculateTotal(Fruits.ORANGE, o);
        total =total + fruits.calculateTotal(Fruits.MANGO, m);

        total =total + vegetables.calculateTotal(Vegetable.POTATO, p);
        total =total + vegetables.calculateTotal(Vegetable.CARROT, c);
        total =total + vegetables.calculateTotal(Vegetable.TOMATO, t);
        total =total + vegetables.calculateTotal(Vegetable.CUCUMBER, cu);

        total =total + meat.calculateTotal(Meat.BEEF, beef);
        total =total + meat.calculateTotal(Meat.CHICKEN, chick);
        total =total + meat.calculateTotal(Meat.MUTTON, mut);

        return total;
    }

    public double processSale(int a,int b,int o,int m,int p,int c,int t,int cu,int beef,int chick,int mut) 
    {

        if (a < 0 || b < 0 || o < 0 || m < 0 || p < 0 || c < 0 || t < 0 || cu < 0 || beef < 0 || chick < 0 || mut < 0) 
            {return -1;}

        boolean ok =
                fruits.hasStock(Fruits.APPLE, a) &&
                fruits.hasStock(Fruits.BANANA, b) &&
                fruits.hasStock(Fruits.ORANGE, o) &&
                fruits.hasStock(Fruits.MANGO, m) &&

                vegetables.hasStock(Vegetable.POTATO, p) &&
                vegetables.hasStock(Vegetable.CARROT, c) &&
                vegetables.hasStock(Vegetable.TOMATO, t) &&
                vegetables.hasStock(Vegetable.CUCUMBER, cu) &&

                meat.hasStock(Meat.BEEF, beef) &&
                meat.hasStock(Meat.CHICKEN, chick) &&
                meat.hasStock(Meat.MUTTON, mut);

        if (!ok) 
            {return -1;}

        double total = grandTotal(a,b,o,m,p,c,t,cu,beef,chick,mut);

        fruits.reduceStock(Fruits.APPLE, a);
        fruits.reduceStock(Fruits.BANANA, b);
        fruits.reduceStock(Fruits.ORANGE, o);
        fruits.reduceStock(Fruits.MANGO, m);

        vegetables.reduceStock(Vegetable.POTATO, p);
        vegetables.reduceStock(Vegetable.CARROT, c);
        vegetables.reduceStock(Vegetable.TOMATO, t);
        vegetables.reduceStock(Vegetable.CUCUMBER, cu);

        meat.reduceStock(Meat.BEEF, beef);
        meat.reduceStock(Meat.CHICKEN, chick);
        meat.reduceStock(Meat.MUTTON, mut);

        return total;
    }
}
