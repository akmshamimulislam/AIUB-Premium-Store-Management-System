package Interface;

public interface Purchasable 
{
    double calculateTotal(int itemIndex, int qty);
    boolean hasStock(int itemIndex, int qty);
    void reduceStock(int itemIndex, int qty);
}
