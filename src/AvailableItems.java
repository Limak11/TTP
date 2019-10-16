import java.util.ArrayList;
import java.util.Collections;

public class AvailableItems
{
    //Dodawane z loadera
    private static ArrayList<Item> itemPool = new ArrayList<Item>();
    private static int weightLimit;
    public static void sort()
    {
        //Sortowanie według stosunku wartości:wagi
        for (int size = itemPool.size(); size != 1; --size) {
            for (int i = 0; i < size - 1; i++) {
                Item temp1 = itemPool.get(i + 1);
                Item temp2 = itemPool.get(i);
                if (temp2.ratio() > temp1.ratio()) {
                    itemPool.set(i, temp1);
                    itemPool.set(i + 1, temp2);
                }
            }
        }
        Collections.reverse(itemPool);
    }
    //Funkcja pozwalająca na dodawanie przedmiotów
    public static void  addItem(Item item)
    {
        itemPool.add(item);
    }
    //Funkcja zwracająca przedmiot o danym indeksie
    public static Item getItem(int i)
    {
        return (Item)itemPool.get(i);
    }
    public static int size()
    {
        return itemPool.size();
    }
    public static void setWeightLimit(int w)
    {
        weightLimit=w;
    }
    public static int getWeightLimit()
    {
        return weightLimit;
    }

}

