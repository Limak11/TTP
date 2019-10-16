//Miasta które można odwiedzić

import java.util.ArrayList;

public class AvailableCities
        {
            //Utworzenie pustej listy miast. Beda dodawane w loaderze
            private static ArrayList cities = new ArrayList<City>();
            private static ArrayList velocities = new ArrayList<Double>();
            //Funkcja pozwalająca na dodawanie miast
            public static void  addCity(City city)
            {
                cities.add(city);
             }
            //Funkcja zwracająca miasto o danym indeksie
            public static City getCity(int i)
             {
                return (City)cities.get(i);
             }
            public static int size()
            {
                return cities.size();
            }
            public static void addVelocities(double vmin, double vmax)
              {
                 velocities.add(vmin);
                 velocities.add(vmax);

                }
            public static double getVMin()
                {	return (double)velocities.get(0);}
            public static double getVMax()
              {	return (double)velocities.get(1);}
        }
