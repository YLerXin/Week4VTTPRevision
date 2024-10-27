package hangman;

import java.util.*;
import java.io.*;
import java.util.random.*;

public class App {
    public static void main(String[] args) throws IOException,FileNotFoundException {
        if (args.length<1){
            System.err.println("Please input a textfile");
        }
        File textFile = new File(args[0]);

        FileReader fr = new FileReader(textFile);
        BufferedReader br = new BufferedReader(fr);
        String Line;
        List<String> words = new ArrayList<String>();
        while(br.readLine() != null){
            Line = br.readLine();
            if (Line.length()>0){
                words.add(Line);
            }
        }

        Random rand = new Random();
        int randint = rand.nextInt(words.size());
        String ans = words.get(randint);
        Console cons = System.console();

        String guess = "";
        while(true){
            System.out.println(ans);
            if(guess.equalsIgnoreCase("")){
                for(int i = 0; i < ans.length();i++){
                    guess = guess + "_";
                }
                System.out.println(guess);
            }else{
                System.out.println(guess);
            }
            String input = cons.readLine("Whats your guess: ");
            System.out.println("Input: " + input);

            char guessed = input.charAt(0);
            for(int j=0;j<ans.length();j++){
                if (ans.charAt(j) == guessed){
                    String guesstemp = guess.substring(0,j) + guessed + guess.substring(j+1);
                    guess = guesstemp;
                }
            }
            if (guess.equalsIgnoreCase(ans)){
                System.err.println("You Won! wow");
                break;
            }
        }




    }
}
