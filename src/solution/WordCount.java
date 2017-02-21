/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solution;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cguttula
 */
public class WordCount {
    
    /**
     * Perform word count and print the result.
     */
    public static void wordCount(){
        //The File should be available at class path.
        String fileName = "wordcount.txt";
        String line = null;
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            List<String> words;
            Map<String,Integer> wordCount = new HashMap<>();
            while((line = bufferedReader.readLine()) != null) {
                words = getWords(line.toLowerCase());
                for(String word:words){
                    if(wordCount.containsKey(word)){
                        int count = wordCount.get(word);
                        wordCount.put(word, count+1);
                    }else{
                        wordCount.put(word, 1);
                    }
                }
            }
            wordCount.keySet().stream().forEach((String word) -> {
                System.out.println(wordCount.get(word)+" "+word);
            });
           
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            Logger.getLogger(WordCount.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(IOException ex) {
            Logger.getLogger(WordCount.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try { 
                fileReader.close();
            } catch (IOException ex) {
                Logger.getLogger(WordCount.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * Get the words by stripping the ending and beginning for special characters.
     * 
     * @param line
     * @return 
     */
    public static List<String> getWords(String line){
        String [] words = line.split(" ");
        List<String> wordz = new ArrayList<>();
        for(String word : words){
            if(word.matches(".*[a-zA-Z]+.*")){//("\\w+")){
                if(word.matches("^(\\W).*$")){
                    word = word.replaceFirst("\\W", "");
                } 
                if(word.matches("^.*(\\W)$")){
                    word = word.substring(0, word.length()-1);
                }
                wordz.add(word);
            }
        }   
        return wordz;
    }
}
