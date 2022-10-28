package it.unibo.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

/**
 * Example class using {@link List} and {@link Map}.
 *
 */
public final class UseListsAndMaps {

    private static final int MIN = 1000;
    private static final int MAX = 2000;
    private static final int FIRST_ELEM_INDEX = 0;
    private static final int NEW_ADD = 100_000;
    private static final int READS = 1000;

    private UseListsAndMaps() {
    }

    public enum Continent {
        AFRICA("Africa", 1_110_635_000L),
        AMERICAS("Americas", 972_005_000L),
        ANTARCTICA("Antarctica", 0L),
        ASIA("Asia", 4_298_723_000L),
        EUROPE("Europe", 742_452_000L),
        OCEANIA("Oceania", 38_304_000L);

        private final String name;
        private final long population;

        private Continent(final String name, final long pop) {
            this.name = name;
            this.population = pop;
        }

        public String getName() {
            return this.name;
        }

        public long getPopulation() {
            return this.population;
        }
    }

    /**
     * @param s
     *            unused
     */
    public static void main(final String... s) {
        /*
         * 1) Create a new ArrayList<Integer>, and populate it with the numbers
         * from 1000 (included) to 2000 (excluded).
         */
        final List<Integer> list = new ArrayList<>();

        for (int i = MIN; i < MAX; i++) {
            list.add(i);
        }

        /*
         * 2) Create a new LinkedList<Integer> and, in a single line of code
         * without using any looping construct (for, while), populate it with
         * the same contents of the list of point 1.
         */
        final List<Integer> newList = new LinkedList<>(list);
        
        System.out.println(newList);
        /*
         * 3) Using "set" and "get" and "size" methods, swap the first and last
         * element of the first list. You can not use any "magic number".
         * (Suggestion: use a temporary variable)
         */
        final int temp = list.get(FIRST_ELEM_INDEX);
        list.set(FIRST_ELEM_INDEX, list.get(list.size() - 1));
        list.set(list.size() - 1, temp);

        /*
         * 4) Using a single for-each, print the contents of the arraylist.
         */
        final StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (final int elem: list) {
            builder.append(elem);
            builder.append(", ");
        }
        builder.append("]");
        if (builder.length() > 0) {
            builder.delete(builder.length() - 3, builder.length() - 1);
        }
        System.out.println(builder);

        /*
         * 5) Measure the performance of inserting new elements in the head of
         * the collection: measure the time required to add 100.000 elements as
         * first element of the collection for both ArrayList and LinkedList,
         * using the previous lists. In order to measure times, use as example
         * TestPerformance.java.
         */
        long time = System.nanoTime();
        
        for (int i = 0; i < NEW_ADD; i++) {
            list.add(FIRST_ELEM_INDEX, i);
        }
        time = System.nanoTime() - time;
        long millis = NANOSECONDS.toMillis(time);
        printMessage("Add of " + NEW_ADD + " new elements as first in ArrayList take " + time + "ns (" + millis + "ms)");

        time = System.nanoTime();
        for (int i = 0; i < NEW_ADD; i++) {
            newList.add(FIRST_ELEM_INDEX, i);
        }
        time = System.nanoTime() - time;
        millis = NANOSECONDS.toMillis(time);
        printMessage("Add of " + NEW_ADD + " new elements as first in LinkedList take " + time + "ns (" + millis + "ms)");
        
        /*
         * 6) Measure the performance of reading 1000 times an element whose
         * position is in the middle of the collection for both ArrayList and
         * LinkedList, using the collections of point 5. In order to measure
         * times, use as example PerfTest.java.
         */
        time = System.nanoTime();
        for (int i = 0; i < READS; i++) {
            list.get(list.size() / 2);
        }
        time = System.nanoTime() - time;
        millis = NANOSECONDS.toMillis(time);
        printMessage("Reading " + READS + " elements in the middle of ArrayList take " + time + "ns (" + millis + "ms)");

        time = System.nanoTime();
        for (int i = 0; i < READS; i++) {
            newList.get(newList.size() / 2);
        }
        time = System.nanoTime() - time;
        millis = NANOSECONDS.toMillis(time);
        printMessage("Reading " + READS + " elements in the middle of LinkedList take " + time + "ns (" + millis + "ms)");
        /*
         * 7) Build a new Map that associates to each continent's name its
         * population:
         *
         * Africa -> 1,110,635,000
         *
         * Americas -> 972,005,000
         *
         * Antarctica -> 0
         *
         * Asia -> 4,298,723,000
         *
         * Europe -> 742,452,000
         *
         * Oceania -> 38,304,000
         */
        final Map<String, Long> map = new HashMap<>();

        map.put(Continent.AFRICA.getName(), Continent.AFRICA.getPopulation());
        map.put(Continent.AMERICAS.getName(), Continent.AMERICAS.getPopulation());
        map.put(Continent.ANTARCTICA.getName(), Continent.ANTARCTICA.getPopulation());
        map.put(Continent.ASIA.getName(), Continent.ASIA.getPopulation());
        map.put(Continent.EUROPE.getName(), Continent.EUROPE.getPopulation());
        map.put(Continent.OCEANIA.getName(), Continent.OCEANIA.getPopulation());
        /*
         * 8) Compute the population of the world
         */

        long populationOfTheWorld = 0;

        for (final long population: map.values()) {
            populationOfTheWorld += population;
        }
        System.out.println("Total population of the world= " + populationOfTheWorld);
    }

    private static void printMessage(final String string) {
        System.out.println(string);
    }
}
