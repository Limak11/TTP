import java.util.ArrayList;
import java.util.Collections;

public class Tour
{
    ArrayList tour = new ArrayList<City>();
    Knapsack knap;
    double fitness=0;
    double distance=0;
    double vmin, vmax;

    public Tour()
    {
        for(int i =0; i< AvailableCities.size(); i++)
        {
            tour.add(null);

        }
        knap=new Knapsack();
    }
    public Tour(ArrayList t)
    {
        knap=new Knapsack();
        tour=t;
    }
    public void newIndividual()
    {
        for(int index=0; index<AvailableCities.size(); index++)
        {
            setCity(index, AvailableCities.getCity(index));
        }
        //Pomieszaj kolejność miast
        Collections.shuffle(tour);
    }

    public City getCity(int index)
    {
        return  (City)tour.get(index);
    }
    public void setCity(int index, City city)
    {
        tour.set(index,city);

        fitness=0;
        distance=0;
    }
    public double getTime()
    {
        double tourTime=0;
        double currentWeight=0;

        //1. Przejdz w pętli po miastach z trasy
        //2. Będąc w mieście o danym indeksie węzła (nodeNumber) spróbuj dodać najlepszy przedmiot do plecaka – knap.addItem(nodeNumber);
        //3. Przypisz nowa wage plecaka currentWeight=knap.getWeight()
        //4. Na podstawie currentWeight, AvailableItems.getWeightLimit(), vmax,vmin
        //oblicz prędkość poruszania się złodzieja przy opuszczaniu miasta
        //5. Sprawdz czy jesteśmy w ostatnim mieście podróży jeżeli tak to złodziej uda się do miasta początkowego, jeżeli nie to do miasta o indeksie większym o 1
        //6. Wykorzystując funkcję z klasy City distanceTo oblicz odległość pomiędzy miastem w którym znajduje się złodziej a tym do którego ma się udać
        //7. oblicz czas dzieląc odległość przez prędkość
        //8. Dodaj czas do zmiennej tourTime
        //9. Jeżeli następne miasto w podróży nie jest miastem początkowym to przejdz do kolejnej iteracji inaczej zakończ i zwróć czas
        for(int i =0; i< tour.size(); i++)
        {

            City fromCity = (City)tour.get(i);

            //calculate velocity
            knap.addItem(fromCity.getNodeNumber());
            currentWeight = knap.getWeight();
            double velocity = AvailableCities.getVMax() - currentWeight*( (AvailableCities.getVMax()-AvailableCities.getVMin())/AvailableItems.getWeightLimit()    );

            City destinationCity;

            //check if destination City is 1st city
            if(i+1 < size()){
                destinationCity = getCity(i+1);
            }
            else{
                destinationCity = getCity(0);
            }
            double tourDistance = fromCity.distanceTo(destinationCity);
            tourTime+=tourDistance/velocity;

            //System.out.println("Z miasta  "+ fromCity.getNodeNumber()+"  do miasta "+ destinationCity.getNodeNumber()+"  waga  "+currentWeight);

        }
        return tourTime;
    }
    public double getFitness()
    {
       // 1. Jeżeli fitness == 0 to fitness (funkcja G) = knap.getValue() - getTime();
        //2. Zwróć fitness

            if(fitness == 0)
            {
                double time = getTime();
                fitness = knap.getValue() - time;
            }
            return fitness;

    }
    public boolean containsCity(City city)
    {
        return tour.contains(city);
    }
    public int size()
    {
        return tour.size();
    }
}


