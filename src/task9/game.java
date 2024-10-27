package task9;

import java.io.*;
import java.util.*;
import java.util.random.*;


public class game {

    public static void main(String[] args) {
        
        Console cons = System.console();
        
        String ans = genrand();

        while(true){
            String input = cons.readLine("Lets play a game guess a number from 0 to 999999: \n");
            if(input.length() < 6){
                for (int i = 0; i < (6 - input.length());i++){
                    input = "0" + input;
                }
            }

            int inputint = Integer.parseInt(input);

            if(input.equals(ans)){
                System.out.println("you Won!");
                break;
            }else if(inputint > Integer.valueOf(ans)){
                System.out.println("Too high");
                System.out.println(ans);
            }
            else if(inputint < Integer.valueOf(ans)){
                System.out.println("Too low");
                System.out.println(ans);

            }
        
    
    
    
    
    
        }

    }
    public static String genrand(){

        Random rand = new Random();

        String num1 = String.valueOf(rand.nextInt(10));
        String num2 = String.valueOf(rand.nextInt(10));
        String num3 = String.valueOf(rand.nextInt(10));
        String num4 = String.valueOf(rand.nextInt(10));
        String num5 = String.valueOf(rand.nextInt(10));
        String num6 = String.valueOf(rand.nextInt(10));

        return num1+num2+num3+num4+num5+num6;

        //or
        /// Generate a number from 0 to 999999 and pad with leading zeros to 6 digits
        //return String.format("%06d", rand.nextInt(1000000));

    }
    
}
