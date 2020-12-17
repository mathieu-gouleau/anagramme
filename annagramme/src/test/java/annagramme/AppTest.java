package annagramme;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for simple App.
 */


class AppTest {

    public HashMap<String, List<String>> giveListAnagramme(List<String> words) {
        words.stream().sorted();
        HashMap<String, List<String>> newListWord = new HashMap<String, List<String>>();
        HashMap<String, List<Integer>> newfinalListWord = new HashMap<String, List<Integer>>();

        if (words.size() <= 1) {
            String element = words.get(0);
            newListWord.put(element, words);
            return newListWord;
        }
        Integer rang = 0;
        for (String i : words) {
            i = i.replace("\'", "");
            char[] chars = i.toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);

            if (newfinalListWord.containsKey(sorted)) {
                newfinalListWord.get(sorted).add(rang);
            } else {
                newfinalListWord.put(sorted, new ArrayList<Integer>(rang));
            }
            rang++;
            System.out.println(rang);
        }
        Map<String, List<Integer>> map = new TreeMap<String, List<Integer>>(newfinalListWord);
        for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
            String key = entry.getKey();
            List<Integer> value = entry.getValue();
            List<String> ListAnagramme = new ArrayList<String>();
            for (Integer rang1 : value) {
                ListAnagramme.add(words.get(rang1));

            }
            newListWord.put(key, ListAnagramme);
        }


        // finalListWord = finalListWord.stream().distinct().collect(Collectors.toList());

        return newListWord;

    }


    /**
     * Rigorous Test.
     */
    @Test
    void shouldReturnTheSameLetter() {
        List<String> words = new ArrayList<String>();
        HashMap<String, List<String>> result = new HashMap<String, List<String>>();
        result.put("a", Arrays.asList("a"));


        words.add("a");
        assertEquals(giveListAnagramme(words), result);
    }


    @Test
    void shouldReturnAbWhenWordsAreAb_Ba() {
        List<String> words = new ArrayList<String>();
        HashMap<String, List<String>> result = new HashMap<String, List<String>>();
        result.put("ab", Arrays.asList("ba"));

        words.add("ab");
        words.add("ba");
        assertEquals(giveListAnagramme(words), result);
    }

    @Test
    void shouldReturnAcAdWhenWordsAreAc_Ca_Ab_Ba() {
        List<String> words = new ArrayList<String>();
        HashMap<String, List<String>> result = new HashMap<String, List<String>>();
        result.put("ab", Arrays.asList("ba"));
        result.put("ac", Arrays.asList("ca"));

        words.add("ab");
        words.add("ac");
        words.add("ca");
        words.add("ba");
        assertEquals(giveListAnagramme(words), result);
    }


    @Test
    void shouldReturnAbAcdcWhenWordsAreAb_ba_Ac_Ca_Cd() {
        List<String> words = new ArrayList<String>();
        HashMap<String, List<String>> result = new HashMap<String, List<String>>();
        result.put("ab", Arrays.asList("ba"));
        result.put("ac", Arrays.asList("ca"));
        result.put("cd", Arrays.asList());


        words.add("ab");
        words.add("ba");
        words.add("ac");
        words.add("ca");
        words.add("cd");
        assertEquals(giveListAnagramme(words), result);
    }


    @Test
    void shouldReturnAbAcdcWhenWordsAreAb_Ac_Ba_Ca_Cd() {
        List<String> words = new ArrayList<String>();
        HashMap<String, List<String>> result = new HashMap<String, List<String>>();
        result.put("ab", Arrays.asList("ba"));
        result.put("ac", Arrays.asList("ca"));
        result.put("cd",Arrays.asList());

        words.add("ab");
        words.add("ac");
        words.add("ba");
        words.add("ca");
        words.add("cd");
        assertEquals(giveListAnagramme(words), result);
    }


    @Test
    void shouldReturnEnumWhenWordsAreEnum() throws IOException {
        BufferedReader bufReader = new BufferedReader(new FileReader("/home/jcgouleau/Téléchargements/wordlist.txt"));
        ArrayList<String> listOfLines = new ArrayList<>();
        String line = bufReader.readLine();
        while (line != null) {
            listOfLines.add(line);
            line = bufReader.readLine();
        }
        bufReader.close();


        HashMap<String, List<String>> result = new HashMap<String, List<String>>();
        result = giveListAnagramme(listOfLines);
        result.forEach((k, v) -> {
            System.out.format("key: %s, value: %s%n", k, v);
        });
    }


}
