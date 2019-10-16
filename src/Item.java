public class Item
{
    int weight;
    int value;
    int node;
    public Item(int v, int w,int n)
    {
        weight=w;
        value=v;
        node=n;
    }

    public double ratio()
    {
        return value/weight;
    }
    @Override
    public String toString(){
        return node+" "+value+", "+weight;
    }
    public int getNode()
    {
        return node;
    }
    public int getWeight()
    {
        return weight;
    }
    public int getValue()
    {
        return value;
    }



}
