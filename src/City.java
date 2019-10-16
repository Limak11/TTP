
//Miasto
public class City
{
    //deklaracja współrzędnych
    int x;
    int y;
    int node;
    //konstruktor
    public City(int x, int y,int node)
    {
        //Przypisanie wartości współrzędnych x i y
        this.x=x;
        this.y=y;
        this.node=node;
    }

    //funkcje zwracające wartości współrzędnych
    public int getX()
    {
        return this.x;
    }
    public int getY()
    {
        return this.y;
    }
    public int getNodeNumber()
    {
        return node;
    }
    public double distanceTo(City city)
    {
        //Deklaracja zmiennych odległości pomiędzy współrzędnymi x i y
        int xD = Math.abs(getX() - city.getX());
        int yD = Math.abs(getY() - city.getY());
        double distance = Math.sqrt( (xD*xD) + (yD*yD) );

        return distance;
    }


    @Override
    public String toString(){
        return node+" "+getX()+", "+getY();
    }
}

