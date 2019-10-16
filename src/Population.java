//
public class Population
{
    //Utworzenie tablicy przechowującej osobników ( trasy)
    Tour[] tours;

    public Population( int popSize, boolean initialise)
    {
        tours = new Tour[popSize];
        //Jeżeli populacje trzeba zainicjalizować to wypełnimy ją nowo utworzonymi
        //osobnikami za pomocą funkcji newIndividual()
        if(initialise)
        {
            for(int i =0; i<size(); i++)
            {
                Tour temp = new Tour();
                temp.newIndividual();
                saveTour(i, temp);
            }
        }
    }

    public void saveTour(int i, Tour tour)
    {
        tours[i]=tour;
    }

    public Tour getTour(int i)
    {
        return tours[i];
    }
    public int size()
    { return tours.length;}

    //Funkcja zwracająca najlepszego osobnika
    public Tour getFittest()
    {
        //1. Utwórz trase pomocniczą fittest
        //2. Iteruj po indeksach tablicy tours
        //3./ Gdy osobnik na danym indeksie posiada fitness > osobnika fittest to przypisz go do fittest
        //4. Zwróć najlepiej przystosowanego osobnika fittest

        Tour fittest=tours[0];
        for(int i = 1; i< size();i++)
        {
            if(tours[i].getFitness() > fittest.getFitness())
            {
                fittest = tours[i];
            }
        }
        return fittest;
    }

}



