package cphbusiness.ufo.letterfrequencies;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static java.util.stream.Collectors.toMap;

/**
 * Frequency analysis Inspired by
 * https://en.wikipedia.org/wiki/Frequency_analysis
 *
 * @author kasper
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        String fileName = "/home/dunkl/Documents/School/UFO/Assignment2/letterfrequencies-master/src/main/resources/FoundationSeries.txt";
        int timesToRun = 100;
        int[] timesInMillis = new int[timesToRun];


        for(int i = 0; i < timesToRun; i++) {
            long startTime = System.nanoTime();
            Reader reader = new FileReader(fileName);
            Map<Integer, Long> freq = new HashMap<>();
            tallyChars(reader, freq);
            long duration = System.nanoTime() - startTime;
//            print_tally(freq);
            timesInMillis[i] = (int) duration / 1_000_000;
        }
        printIntArr(timesInMillis);


        for(int i = 0; i < timesToRun; i++) {
            long startTime = System.nanoTime();
            Reader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
            Map<Integer, Integer> freq = new HashMap<>();
            tallyChars2(reader, freq);
            long duration = System.nanoTime() - startTime;
//            print_tally2(freq);
            timesInMillis[i] = (int) duration / 1_000_000;
        }
        printIntArr(timesInMillis);


        for(int i = 0; i < timesToRun; i++) {
            long startTime = System.nanoTime();
            Map<Integer, Integer> freq = new HashMap<>();
            Files.lines(Paths.get(fileName), StandardCharsets.US_ASCII).forEach(line -> tallyChars3(line, freq));
            long duration = System.nanoTime() - startTime;
//            print_tally2(freq3);
            timesInMillis[i] = (int) duration / 1_000_000;
        }
        printIntArr(timesInMillis);



    }

    private static void tallyChars3(String line, Map<Integer, Integer> freq) {
        int length = line.length();
        for (int i = 0; i < length; i++) {
            int b = line.charAt(i);
            try {
                freq.put(b, (freq.get(b) + 1));
            } catch (NullPointerException np) {
                freq.put(b, 1);
            };
        }
    }

    private static void tallyChars2(Reader reader, Map<Integer, Integer> freq) throws IOException {
        int b;
        while ((b = reader.read()) != -1) {
            try {
                freq.put(b, (freq.get(b) + 1));
            } catch (NullPointerException np) {
                freq.put(b, 1);
            };
        }
    }

    private static void tallyChars(Reader reader, Map<Integer, Long> freq) throws IOException {
        int b;
        while ((b = reader.read()) != -1) {
            try {
                freq.put(b, freq.get(b) + 1);
            } catch (NullPointerException np) {
                freq.put(b, 1L);
            };
        }
    }

    private static void print_tally(Map<Integer, Long> freq) {
        int dist = 'a' - 'A';
        Map<Character, Long> upperAndlower = new LinkedHashMap();
        for (Character c = 'A'; c <= 'Z'; c++) {
            upperAndlower.put(c, freq.getOrDefault(c, 0L) + freq.getOrDefault(c + dist, 0L));
        }
        Map<Character, Long> sorted = upperAndlower
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(
                        toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new));
        for (Character c : sorted.keySet()) {
            System.out.println("" + c + ": " + sorted.get(c));;
        }
    }
    private static void print_tally2(Map<Integer, Integer> freq) {
        Map<Integer, Long> freqL = new HashMap<>();
        for (Map.Entry<Integer, Integer> e : freq.entrySet()) {
            freqL.put(e.getKey(), (long) e.getValue());
        }
        print_tally(freqL);
    }

    private static void printIntArr(int[] iArr) {
        String csv = "";
        int sum = 0;
        for (int i:iArr) {
            csv += i + ",";
            sum += i;
        }
        System.out.println(csv.substring(0, csv.length() - 1));
        System.out.println("Mean: " +  (sum/iArr.length));
    }
}
