package annagramme;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for simple App.
 */


class AppTest {

    public HashMap<String, List<String>> giveListAnagramme(List<String> words) {
        words.stream().sorted();

        List<String> newwords = new ArrayList<>();
        HashMap<String, List<String>> newListWord = new HashMap<>();
        Map<String, String> map = new TreeMap<>();
        HashMap<String, List<String>> newfinalListWord = new HashMap<>();
        words.replaceAll(String::toLowerCase);
        words = words.stream().map(word -> word.replace("\'", "")).collect(Collectors.toList());
        words = words.stream().distinct().collect(Collectors.toList());
        if (words.size() <= 1) {
            String element = words.get(0);
            newListWord.put(element, words);
            return newListWord;
        }
        int rang = 0;
        for (String i : words) {
            char[] chars = i.toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);
            newwords.add(sorted);
            map.put(i, sorted);

            rang++;

        }


        Map<String, String> topTen =
            map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.naturalOrder()))
                .collect(Collectors.toMap(
                    Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        List<String> listAnagram = new ArrayList<>();
        for (int j = 0; j < topTen.keySet().size(); j++) {


            listAnagram.add(topTen.keySet().toArray()[j].toString());
            newListWord.put(topTen.values().toArray()[j].toString(), listAnagram);
            if ((j >= topTen.keySet().size() - 1) || !topTen.values().toArray()[j + 1].toString().equals(topTen.values().toArray()[j].toString())) {
                listAnagram = new ArrayList<>();

            }
            topTen.remove(topTen.keySet().toArray()[j], topTen.values().toArray()[j]);
            j--;
        }

        return newListWord;

    }

    public class Anagram {

        public String getWord() {
            return word;
        }

        public String getSortedWord() {
            return sortedWord;
        }

        private String word;
        private String sortedWord;

        public Anagram(String word, String sorted) {
            this.word = word;
            this.sortedWord = sorted;
        }


        //constructors, getter/setters
    }

    public Map<String, Set<String>> giveListAnagrammeLong(List<String> words) {


        Map<String, String> map = new TreeMap<>();
        words.replaceAll(String::toLowerCase);
        words = words.stream().map(word -> word.replace("\'", "")).collect(Collectors.toList());
        words = words.stream().distinct().collect(Collectors.toList());
        List<Anagram> items = new ArrayList<>();

        int rang = 0;
        for (String i : words) {
            char[] chars = i.toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);
            items.add(new Anagram(i, sorted));

            rang++;

        }


        Map<String, Set<String>> topTen =
            items.stream().collect(
                groupingBy(Anagram::getSortedWord,
                    Collectors.mapping(Anagram::getWord, Collectors.toSet())));


        return topTen;

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
        result.put("ab", Arrays.asList("ab", "ba"));

        words.add("ab");
        words.add("ba");
        assertEquals(giveListAnagramme(words), result);
    }

    @Test
    void shouldReturnAcAdWhenWordsAreAc_Ca_Ab_Ba() {
        List<String> words = new ArrayList<String>();
        HashMap<String, List<String>> result = new HashMap<String, List<String>>();
        result.put("ab", Arrays.asList("ab", "ba"));
        result.put("ac", Arrays.asList("ac", "ca"));

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
        result.put("ab", Arrays.asList("ab", "ba"));
        result.put("ac", Arrays.asList("ac", "ca"));
        result.put("cd", Arrays.asList("cd"));


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
        result.put("ab", Arrays.asList("ab", "ba"));
        result.put("ac", Arrays.asList("ac", "ca"));
        result.put("cd", Arrays.asList("cd"));

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

        listOfLines.sort(String::compareToIgnoreCase);

        Map<String, Set<String>> result;
        result = giveListAnagrammeLong(listOfLines);
        result.forEach((key, value) -> {
            if (value.size() > 12)
                System.out.println(key + ":" + value);
        });

    }
}
