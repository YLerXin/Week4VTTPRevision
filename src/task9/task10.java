package task9;

import java.io.*;
import java.util.*;
import java.util.random.*;

public class task10 {
    
    public static String randnum(){
        Random rand = new Random();
        return String.valueOf(rand.nextInt(1,101));
    }
    public static String[] shuffle(String[] input){
        Random rand = new Random();

        String[] shuffled = input;

        for (int i=0; i<input.length;i++){
            int randomIndexToSwap = rand.nextInt(input.length);
            String temp = shuffled[randomIndexToSwap];
            shuffled[randomIndexToSwap] = shuffled[i];
            shuffled[i] = temp;
        }
        return shuffled;
    }

    public static void main(String[] args) {

        List<String> guess = new ArrayList<String>();
        String rand1 = randnum();
        String rand2 = randnum();
        String rand3 = randnum();
        String rand4 = randnum();
        String rand5 = randnum();
        String rand6 = randnum();
        String rand7 = randnum();
        String rand8 = randnum();
        String rand9 = randnum();
        String rand10 = randnum();

        List<String> shuffled = new ArrayList<String>();
        shuffled.add(rand1);
        shuffled.add(rand2);
        shuffled.add(rand3);
        shuffled.add(rand4);
        shuffled.add(rand5);
        shuffled.add(rand6);
        shuffled.add(rand7);
        shuffled.add(rand8);
        shuffled.add(rand9);
        shuffled.add(rand10);

        //convert arraylist to array
        String[] shuffled1 = new String[shuffled.size()];
        shuffled1 = shuffled.toArray(shuffled1);

        String[] shuffled2 = shuffle(shuffled1);
        //convert array to arraylist
        shuffled = new ArrayList<>(Arrays.asList(shuffled2));

        Console cons = System.console();


        while(true){
            for(int i=0;i<10;i++){

                if(i == 9){
                    System.out.println("Game Over");
                    return;

                }
                int counthigh = 0;
                int countlow = 0;
                System.out.println(shuffled.get(i));
                for(int j = (i+1);j<(10);j++){
                    if((Integer.parseInt(shuffled.get(j)))>(Integer.parseInt(shuffled.get(i)))){
                        counthigh++;
                    }else if((Integer.parseInt(shuffled.get(j)))<=(Integer.parseInt(shuffled.get(i)))){
                        countlow++;
                    }
                }
                System.out.println("There are " + counthigh + " values higher in the deck and "+countlow+" Values lower.");

                String input = cons.readLine("Guess num high or low: \n");
                guess.add(shuffled.get(i));

                if((input.equalsIgnoreCase("high")) && ((Integer.parseInt(shuffled.get(i))) < Integer.parseInt(shuffled.get(i+1)))){
                    System.out.println("Great you guessed correctly");
                }else if((input.equalsIgnoreCase("low")) && ((Integer.parseInt(shuffled.get(i))) >= Integer.parseInt(shuffled.get(i+1)))){
                    System.out.println("Great you guessed correctly");
                }else{
                    System.out.println("Nope, Wrong");
                }

            }
            
        }
    
    }
    
}
