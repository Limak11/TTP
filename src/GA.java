public class GA {

    private static  double mutationRate = 0.01;
    private static  double crossoverRate = 0.7;
    private static  int tournamentSize = 5;
    private static final boolean elitism = true;

    //ewolucja Populacji
    // tu
    public static Population evolve(Population pop) {
        Population newPop = new Population(pop.size(), false);

        //1. W pętli i =0 ; i< newPop.size() wybierz pierwszego rodzica za pomocą turnieju - tournament(pop)
        //2. Sprawdz czy zajdzie krzyżowanie jeżeli tak to wybierz turniejem drugiego rodzica i stwórz dziecko za pomocą funkcji crossover(parent1, parent2) – dodaj je do nowej populacji
        //3. Jeżeli krzyżowanie nie zajdzie to po prostu dodaj pierwszego rodzica do populacji
        //4. W kolejnej pętli o takiej samej ilości iteracji wywołuj funkcje mutate dla każdego osobnika
        //5. Po zakończonej ewolucji zwróć nową populacje

        Population newPopulation = new Population(pop.size(), false);

        // Keep our best individual if elitism is enabled
        int elitismOffset = 0;
        if (elitism) {
            newPopulation.saveTour(0, pop.getFittest());
            elitismOffset = 1;
        }

        // Crossover population
        // Loop over the new population's size and create individuals from
        // Current population
        for (int i = elitismOffset; i < newPopulation.size(); i++) {
            // Select parents
            Tour parent1 = tournament(pop);
            if(Math.random()<crossoverRate) {
                Tour parent2 = tournament(pop);
                // Crossover parents
                Tour child = crossover(parent1, parent2);
                // Add child to new population
                newPopulation.saveTour(i, child);
            }
            else newPopulation.saveTour(i,parent1);
        }

        // Mutate the new population a bit to add some new genetic material
        for (int i = elitismOffset; i < newPopulation.size(); i++) {
            mutate(newPopulation.getTour(i));
        }

        return newPopulation;
    }

    // tu
    private static Tour crossover(Tour parent1, Tour parent2) {
        //1. Utwórz nowego osobnika (dziecko)
        //2. Wylosuj początek i koniec podtrasy, którą weźmiemy z pierwszego rodzica
        //sprawdzając czy początek nie jest większy od końca
        //3.   Dodaj miasta z rodzica do dziecka na tych samych pozycjach
        //4.   W kolejnej pętli przeszukuj dziecko w poszukiwaniu pustych pozycji i wstawiaj w ich miejsca miasta z drugiego rodzica ( zachowując kolejność i pomijając miasta, które wzięto już z pierwszego rodzica).
        //5.   Zwróć trase-dziecko
        // Create new child tour
        Tour child = new Tour();

        // Get start and end sub tour positions for parent1's tour
        int startPos = (int) (Math.random() * parent1.size());
        int endPos = (int) (Math.random() * parent1.size());

        // Loop and add the sub tour from parent1 to our child
        for (int i = 0; i < child.size(); i++) {
            // If our start position is less than the end position
            if (startPos < endPos && i > startPos && i < endPos) {
                child.setCity(i, parent1.getCity(i));
            } // If our start position is larger
            else if (startPos > endPos) {
                if (!(i < startPos && i > endPos)) {
                    child.setCity(i, parent1.getCity(i));
                }
            }
        }

        // Loop through parent2's city tour
        for (int i = 0; i < parent2.size(); i++) {
            // If child doesn't have the city add it
            if (!child.containsCity(parent2.getCity(i))) {
                // Loop to find a spare position in the child's tour
                for (int ii = 0; ii < child.size(); ii++) {
                    // Spare position found, add city
                    if (child.getCity(ii) == null) {
                        child.setCity(ii, parent2.getCity(i));
                        break;
                    }
                }
            }
        }
        return child;
    }

    private static void mutate(Tour tour) {

        //1. Za pomocą Math.random() sprawdz czy zajedzie mutacja ( Math.random()<mutationRate)
        //2. Jeżeli tak to wylosuj dwie różne pozycje miast i zamień miejscami
        // Loop through tour cities
        for(int tourPos1=0; tourPos1 < tour.size(); tourPos1++){
            // Apply mutation rate
            if(Math.random() < mutationRate){
                // Get a second random position in the tour
                int tourPos2 = (int) (tour.size() * Math.random());

                // Get the cities at target position in tour
                City city1 = tour.getCity(tourPos1);
                City city2 = tour.getCity(tourPos2);

                // Swap them around
                tour.setCity(tourPos2, city1);
                tour.setCity(tourPos1, city2);
            }
        }

    }

    //tu
    private static Tour tournament(Population pop) {
        //1. Stwórz pusta populacje bez inicjalizacji – new Population(tournamentSize, false)
        //2. Wylosuj do niej tournamentSize osobników z populacji  pop
        //3. Wypełnij nowo utworzoną populacje wylosowanymi osobnikami
        //4. Wybierz i zwróć  najlepszego osobnika ( trase) z populacji – getFittest()}
        // Create a tournament population
        Population tournament = new Population(tournamentSize, false);
        // For each place in the tournament get a random candidate tour and
        // add it
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.size());
            tournament.saveTour(i, pop.getTour(randomId));
        }
        // Get the fittest tour
        Tour fittest = tournament.getFittest();
        return fittest;
    }
    public static void setRates( double mR, double cR, int tSize)
    {
        mutationRate=mR;
        crossoverRate=cR;
        tournamentSize = tSize;
    }

}