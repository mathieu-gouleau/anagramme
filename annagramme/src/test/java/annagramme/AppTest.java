package annagramme;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Unit test for simple App.
 */


class AppTest {

    public List<String> giveListAnagramme(List<String> words) {
        List<String> newListWord = new ArrayList<String>();
        List<String> finalListWord = new ArrayList<String>();

        
        if(words.size() <= 1 ){
            return words;
        }
        
        for(String i: words){
            char[] chars = i.toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);
            newListWord.add(sorted);
        }

        
        for(int i = 0; i<newListWord.size()-1; i++){
            for(int k =i+1;k <newListWord.size(); k++){
                if(newListWord.get(i).equals(newListWord.get(k))){
                    newListWord.remove(k);
                }
                
                
            }

            
        }

        return newListWord;

        
        
        
   } 

    /**
     * Rigorous Test.
     */
    @Test

    void shouldReturnTheSameLetter() {
        List<String> words = new ArrayList<String>();
        words.add("a");
        assertEquals(giveListAnagramme(words), Arrays.asList("a"));
    }


    @Test
    void shouldReturnAbWhenWordsAreAb_Ba() {
        List<String> words = new ArrayList<String>();
        words.add("ab");
        words.add("ba");
        assertEquals(giveListAnagramme(words), Arrays.asList("ab"));
    }
    
    @Test
    void shouldReturnAcAdWhenWordsAreAc_Ca_Ab_Ba() {
        List<String> words = new ArrayList<String>();
        words.add("ab");
        words.add("ba");
        words.add("ac");
        words.add("ca");
        assertEquals(giveListAnagramme(words), Arrays.asList("ab","ac"));
    }


    @Test
    void shouldReturnAbAcdcWhenWordsAreAb_ba_Ac_Ca_Cd() {
        List<String> words = new ArrayList<String>();
        words.add("ab");
        words.add("ba");
        words.add("ac");
        words.add("ca");
        words.add("cd");
        assertEquals(giveListAnagramme(words), Arrays.asList("ab","ac","cd"));
    }



    

    



}
