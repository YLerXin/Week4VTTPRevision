package practice;

import java.io.*;
import java.util.*;

public class Readcsvemail {
    
    public static String[] parseLine(String line) {
        List<String> result = new ArrayList<>();
        StringBuilder currentField = new StringBuilder();
        boolean insideQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char currentChar = line.charAt(i);

            if (currentChar == '\"') {
                // Toggle insideQuotes when encountering a quote character
                insideQuotes = !insideQuotes;
            } else if (currentChar == ',' && !insideQuotes) {
                // If we encounter a comma outside of quotes, it's the end of a field
                result.add(currentField.toString().trim());
                currentField.setLength(0); // Reset the field buffer
            } else {
                // Add the character to the current field
                currentField.append(currentChar);
            }
        }

        // Add the last field to the result (since the line might not end with a comma)
        result.add(currentField.toString().trim());

        return result.toArray(new String[0]);
    }
    public static Map<String, Contacts> parseCSV(String filePath) throws IOException {
        Map<String, Contacts> contactMap = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String[] headers = parseLine(reader.readLine()); // Skip the header line
            String line;

            while ((line = reader.readLine()) != null) {
                String[] values = parseLine(line);

                // Create a Customer object using the values from the CSV
                Contacts contact = new Contacts(
                    values[0],  // firstName
                    values[1],  // lastName
                    values[2],  // companyName
                    values[3],  // address
                    values[4],  // city
                    values[5],  // county
                    values[6],  // state
                    values[7],  // zip
                    values[8],  // phone1
                    values[9],  // phone
                    values[10] // email
                );

                // Add to map using Customer Id as key, avoiding duplicates
                contactMap.putIfAbsent(contact.getZip(), contact);
            }
        }
        return contactMap;
    }


    public static void main(String[] args) throws IOException{
        String filePath = args[0]; //contact file
        String filePathemail = "mail.txt"; //email file
        Map<String,Contacts> contactMap = parseCSV(filePath);

        FileReader fr = new FileReader(filePathemail);
        BufferedReader br = new BufferedReader(fr);

        String name = contactMap.get("48116").getFirstName();
        String address = contactMap.get("48116").getAddress();
        String line;
        Console cons = System.console();

        while(true){ 
            String input = cons.readLine("What do you want to do? \n");

            switch(input.toLowerCase()){
                case("email"):

                    while((line = br.readLine()) != null){
                        line = line.replaceAll("\\b__name__\\b",name);
                        line = line.replaceAll("\\b__address__\\b",address);
        
                        System.out.println(line);
                    }
                break;

                case("count"):
                    while(true){
                        input = cons.readLine("Which word do you want to count? \n");
                        int wordcount = 0;
                        fr = new FileReader(filePathemail);
                        br = new BufferedReader(fr);
                        if(input.toLowerCase().equals("exit")){
                            System.out.println("Oh no");
                            break;
                        }
                        while((line = br.readLine()) != null){
                            line.toLowerCase();
                            line.replaceAll("\\p{Punct}", "");//remove punctuations
                            String[] word = line.split(" ");
                            for (int i=0; i <word.length;i++){
                                if(word[i].toLowerCase().equals(input.toLowerCase())){
                                    wordcount ++;
                                }
                            }
                        }
                        System.out.println("There are "+wordcount+ " occurances of "+ input +".");
                    }

                break;

                case("read"):
                    FileInputStream fis = new FileInputStream(filePathemail);
                    BufferedInputStream bis = new BufferedInputStream(fis);
                    DataInputStream dis = new DataInputStream(bis);

                    byte[] buffer = new byte[4 * 1024];
                    int bytesRead;
                    while ((bytesRead = bis.read(buffer)) != -1) {
                        // Convert the chunk into a string and process it
                        String chunk = new String(buffer, 0, bytesRead);
                        System.out.println("Chunk read:\n" + chunk); // Display the chunk or process it as needed
                        // Here, you can perform other operations on the chunk, such as finding word occurrences
                    }
                    // to get word count by chunk
                    // byte[] buffer = new byte[chunkSize];
                    // int bytesRead;
        
                    // while ((bytesRead = bis.read(buffer)) != -1) {
                    //     // Convert the chunk into a string for processing
                    //     String chunk = new String(buffer, 0, bytesRead);
                    //     chunk = chunk.toLowerCase().replaceAll("\\p{Punct}", "");  // Remove punctuation and lowercase
                    //     String[] words = chunk.split(" ");
        
                    //     // Count occurrences of the target word in this chunk
                    //     int wordCount = 0;
                    //     for (String word : words) {
                    //         if (word.equals(input.toLowerCase())) {
                    //             wordCount++;
                    //         }
                    //     }
                    //     totalWordCount += wordCount;
                    // }
                    // System.out.println("There are " + totalWordCount + " occurrences of '" + input + "'.");
                break;


            }

        }


    }










}
