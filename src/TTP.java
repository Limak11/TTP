import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TTP {


    public void loadFromFile(){
        /*
        - Wczytać miasta z danego pliku i dodać je do cities
        - Wczytać obiekty z pliku pliku i dodać je do items
        - Wczytać pojemność plecaka i dodać do Const.KNAPSACK_CAPACITY
         */
        String line = null;

        try{
            JFileChooser jFile = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            int returnValue = jFile.showOpenDialog(null);

            File file= new File("");
            if(returnValue == JFileChooser.APPROVE_OPTION)
            {
                file = jFile.getSelectedFile();

            }
            FileReader fileReader = new FileReader(file);

            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int lineNumber = 1;
            boolean readItems = false;
            while((line = bufferedReader.readLine()) != null){
                //System.out.println(line);
                if (lineNumber == 5){
                    String[] strings = line.split(":");
                    strings[1] = strings[1].replaceAll("\t", "");
                    strings[1] = strings[1].replaceAll("\\s", "");
                    int number = Integer.parseInt(strings[1]);
                    AvailableItems.setWeightLimit(number);
                    //Const.KNAPSACK_CAPACITY = number;
                    //System.out.println(number);

                }



                if (lineNumber > 10){
                    if(line.equals("ITEMS SECTION\t(INDEX, PROFIT, WEIGHT, ASSIGNED NODE NUMBER): ")){
                        readItems = true;
                    } else if (readItems){
                        String[] strings = line.split("\t");
                        Integer.parseInt(strings[0]);
                        AvailableItems.addItem(new Item( Integer.parseInt(strings[1]), Integer.parseInt(strings[2]), Integer.parseInt(strings[3])));
                    } else {
                        String[] strings = line.split("\t");
                        int node = Integer.parseInt(strings[0]);
                        int x = (int)Double.parseDouble(strings[1]);
                        int y = (int)Double.parseDouble(strings[2]);
                        AvailableCities.addCity(new City( x, y, node));
                    }
                }

                lineNumber++;
            }
        } catch(FileNotFoundException e){
            System.out.println("Unable to open file: " + "trival_0.ttp");
        } catch(IOException e){
            e.printStackTrace();
        }
    }




    public static void main(String[] args)
    {

        //Wczytywanie
        int popSize=100;
        int gen = 1000;
        double mR =0.01;
        double cR = 0.7;
        int tSize=5;
        String input="";
        TTP ttp = new TTP();
        ttp.loadFromFile();


        Scanner in = new Scanner(System.in);

        System.out.print("Enter population size: ");

        popSize = in.nextInt();
        System.out.print("Enter number of generations: ");
        gen = in.nextInt();
        System.out.print("Enter mutation rate in % 1-100: ");
        mR = in.nextInt();
        System.out.print("Enter crossover rate in % 1-100: ");
        cR = in.nextInt();
        System.out.print("Enter tournament size: ");
        tSize = in.nextInt();
        double cR1 = cR/100;
        double mR1 = mR/100;
        GA.setRates(mR1,cR1,tSize);


        //Add vmin, vmax
        AvailableCities.addVelocities(0.1,1);


        for(int i=0; i<AvailableCities.size();i++)
        {
            String s =AvailableCities.getCity(i).toString();
            System.out.println(s);
        }


        System.out.println("sorted items:");
        AvailableItems.sort();
        for(int i=0; i<AvailableItems.size();i++)
        {
            String s =AvailableItems.getItem(i).toString();
            System.out.println(s);
        }

        System.out.println("");
        Knapsack knap = new Knapsack();
        System.out.println("currentWeight: "+knap.getWeight()+"   value: "+knap.getValue());
        for(int i =1; i<=10; i++)
        {
            knap.addItem(i);
            System.out.println("Miasto: "+ i +" currentWeight: "+knap.getWeight()+"   value: "+knap.getValue());
        }


        ArrayList testList = new ArrayList<City>();
        for(int i =0; i<AvailableCities.size();i++)
        {
            testList.add(AvailableCities.getCity(i));
        }
        Tour test = new Tour(testList);
        System.out.println(test.getTime());


        ArrayList scores = new ArrayList<Double>();
        for(int x = 0 ; x<gen;x++)
        {
            double temp = 0;
            scores.add(temp);
        }
        for(int j =0; j<10; j++) {


            Population pop = new Population(popSize, true);
            Tour tpop = pop.getFittest();
            //for (int i = 0; i < tpop.size(); i++) {
             //   System.out.print(tpop.getCity(i).getNodeNumber() + " ");
            //}
            //System.out.println("Fitness: " + tpop.getFitness());

            System.out.println("GA: "+ j);


            scores.set(0,tpop.getFitness() + (double)scores.get(0));
            for (int i = 1; i < gen; i++) {
                pop = GA.evolve(pop);
                //System.out.println(pop.getFittest().getFitness());
                //scores.add(pop.getFittest().getFitness());

                double temp = pop.getFittest().getFitness() + (double)scores.get(i);

                scores.set(i,temp);
            }
            //System.out.println( pop.getFittest().getFitness());

        }
        System.out.println("Fitness:");
        for ( int x = 0; x< scores.size(); x++)
        {
            double temp = (double)scores.get(x)/10;
            scores.set(x,temp);
            System.out.println(scores.get(x));
        }
        GraphPanel gP = new GraphPanel(scores);
        gP.draw();
    }


}
