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
class Anagram {


    private String word;
    private String sortedWord;

    public Anagram(String word, String sorted) {
        this.word = word;
        this.sortedWord = sorted;
    }

    public String getWord() {
        return word;
    }

    public String getSortedWord() {
        return sortedWord;
    }

    //constructors, getter/setters
}

class AppTest {





    public Map<String, Set<String>> giveListAnagrammeLong(List<String> words) {


        words.replaceAll(String::toLowerCase);
        words = words.stream().map(word -> word.replace("\'", "")).collect(Collectors.toList());
        words = words.stream().distinct().collect(Collectors.toList());
        List<Anagram> items = new ArrayList<>();

        for (String i : words) {
            char[] chars = i.toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);
            items.add(new Anagram(i, sorted));

        }


        Map<String, Set<String>> topTen =
            items.stream().parallel().collect(
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
        Map<String, Set<String>> result = new HashMap<>();

        Set setA = new HashSet();

        setA.add("a");

        result.put("a", setA);


        words.add("a");
        assertEquals(giveListAnagrammeLong(words), result);
    }


    @Test
    void shouldReturnAbWhenWordsAreAb_Ba() {
        List<String> words = new ArrayList<String>();
        Map<String, Set<String>> result = new HashMap<>();

        Set setA = new HashSet();

        setA.add("ab");
        setA.add("ba");
        result.put("ab", setA);

        words.add("ab");
        words.add("ba");
        assertEquals(giveListAnagrammeLong(words), result);
    }

    @Test
    void shouldReturnAcAdWhenWordsAreAc_Ca_Ab_Ba() {
        List<String> words = new ArrayList<String>();
        Map<String, Set<String>> result = new HashMap<>();

        Set setA = new HashSet();

        setA.add("ab");
        setA.add("ba");
        result.put("ab", setA);
        Set setB = new HashSet();

        setB.add("ac");
        setB.add("ca");
        result.put("ac", setB);


        words.add("ab");
        words.add("ac");
        words.add("ca");
        words.add("ba");
        assertEquals(giveListAnagrammeLong(words), result);
    }


    @Test
    void shouldReturnAbAcdcWhenWordsAreAb_ba_Ac_Ca_Cd() {
        List<String> words = new ArrayList<>();
        Map<String, Set<String>> result = new HashMap<>();

        Set setA = new HashSet();

        setA.add("ab");
        setA.add("ba");
        result.put("ab", setA);
        Set setB = new HashSet();

        setB.add("ac");
        setB.add("ca");
        result.put("ac", setB);
        Set setC = new HashSet();

        setC.add("cd");
        result.put("cd", setC);


        words.add("ab");
        words.add("ba");
        words.add("ac");
        words.add("ca");
        words.add("cd");
        assertEquals(giveListAnagrammeLong(words), result);
    }


    @Test
    void shouldReturnAbAcdcWhenWordsAreAb_Ac_Ba_Ca_Cd() {
        List<String> words = new ArrayList<String>();
        Map<String, Set<String>> result = new HashMap<>();


        Set setA = new HashSet();

        setA.add("ab");
        setA.add("ba");
        result.put("ab", setA);
        Set setB = new HashSet();

        setB.add("ac");
        setB.add("ca");
        result.put("ac", setB);
        Set setC = new HashSet();

        setC.add("cd");
        result.put("cd", setC);

        words.add("ab");
        words.add("ac");
        words.add("ba");
        words.add("ca");
        words.add("cd");
        assertEquals(giveListAnagrammeLong(words), result);
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
