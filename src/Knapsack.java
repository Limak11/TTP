import java.util.ArrayList;

//
public class Knapsack
{
    ArrayList<Item> items= new ArrayList<Item>();
    int weightLimit;
    int value;
    int currentWeight;
    public Knapsack()
    {
        //Przy tworzeniu plecaka posortuj liste dostępnych przedmiotów
        //AvailableItems.sort();
        //przypisz wartości
        value=0;
        weightLimit=AvailableItems.getWeightLimit();
        currentWeight=0;
    }
    //czesc algorytmu zachlannego plecaka
    public void addItem(int node)
    {
      //  1. Przejdź liste przedmiotow AvailableItems w poszukiwaniu przedmiotu z danego miasta o najlepszym współczynniku wartość/waga
      //  2. Jeżeli przedmiot istnieje i zmieści się w plecaku to dodaj go do listy, dodaj jego wage do currentWeight i wartość do value
      //  3. Jeżeli przedmiot istnieje ale jest zbyt ciężki to szukaj dalej
      //  4. Jeżeli nie znaleziony zostanie żaden przedmiot spełniający wymagania to zakończ
        boolean item_added=false;

        for(int i =0; i< AvailableItems.size();i++)
        {
            Item temp = AvailableItems.getItem(i);
            if(temp.getNode() == node && item_added==false)
            {
                if(currentWeight+temp.getWeight() <= weightLimit)
                {
                    currentWeight = currentWeight + temp.getWeight();
                    value = value+ temp.getValue();
                    item_added=true;
                }
            }
        }
    }

    public int getWeight()
    {
        return currentWeight;
    }
    public int getValue()
    {
        return value;
    }

}

