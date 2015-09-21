/**
 * Created by kaijiehuang on 15-3-26.
 */
//print out Random seed in quicksorts.

import org.junit.Before;
import org.junit.Test;

import java.lang.Long;
import java.util.Comparator;
import java.util.Random;
import java.util.ArrayList;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertNotNull;

/**
 * HVZ-themed Sorting Tests. Expansion to the original TA-provided JUnit
 *
 * @author Adithya Nott
 * @version 1.5
 */
public class HVZSortingTests {
    private Zombie[] horde;
    private Zombie[] hordeByName;
    private Zombie[] hordeByKills;
    private Zombie[] hordeByDay;

    private Human[] party;
    private Human[] partyByName;
    private Human[] partyByStuns;
    private Human[] partyByZombify;

    private Human[] gpb;

    private final String seed = "0x" + getRandomSeed();
    private final long randomSeed = (long) (Math.random() * Long.MAX_VALUE);

    private static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        horde = new Zombie[10];
        horde[0] = new Zombie("Albert", 0, 7);
        horde[1] = new Zombie("Emily", 1, 1);
        horde[2] = new Zombie("Hemen", 0, 0);
        horde[3] = new Zombie("Kush", 4, 2);
        horde[4] = new Zombie("OZ", 3, 2);
        horde[5] = new Zombie("OZ", 4, 2);
        horde[6] = new Zombie("Saikrishna", 999, 2);
        horde[7] = new Zombie("Jonathan", 0, 2);
        horde[8] = new Zombie("Monica", 400, 58);
        horde[9] = new Zombie("David Smith", 1, 180);
        hordeByName = new Zombie[10];
        hordeByName[0] = horde[0];
        hordeByName[1] = horde[9];
        hordeByName[2] = horde[1];
        hordeByName[3] = horde[2];
        hordeByName[4] = horde[7];
        hordeByName[5] = horde[3];
        hordeByName[6] = horde[8];
        hordeByName[7] = horde[4];
        hordeByName[8] = horde[5];
        hordeByName[9] = horde[6];
        hordeByKills = new Zombie[10];
        hordeByKills[0] = horde[0];
        hordeByKills[1] = horde[2];
        hordeByKills[2] = horde[7];
        hordeByKills[3] = horde[1];
        hordeByKills[4] = horde[9];
        hordeByKills[5] = horde[4];
        hordeByKills[6] = horde[3];
        hordeByKills[7] = horde[5];
        hordeByKills[8] = horde[8];
        hordeByKills[9] = horde[6];
        hordeByDay = new Zombie[10];
        hordeByDay[0] = horde[2];
        hordeByDay[1] = horde[1];
        hordeByDay[2] = horde[3];
        hordeByDay[3] = horde[4];
        hordeByDay[4] = horde[5];
        hordeByDay[5] = horde[6];
        hordeByDay[6] = horde[7];
        hordeByDay[7] = horde[0];
        hordeByDay[8] = horde[8];
        hordeByDay[9] = horde[9];

        party = new Human[10];
        party[0] = new Human("Michael", 42, 0.4);
        party[1] = new Human("James", 20, 0.7);
        party[2] = new Human("Aaron Friesen", 99999, 0.0);
        party[3] = new Human("Kevin", 123, 0.5);
        party[4] = new Human("Farhan", 50, 0.6);
        party[5] = new Human("George P. Burdell",
                Integer.MAX_VALUE, Double.NEGATIVE_INFINITY);
        party[6] = new Human("Siddu", 40, 0.4);
        party[7] = new Human("Carey", 30, 0.5);
        party[8] = new Human("Jack", 10, 0.1);
        party[9] = new Human("Chris Simpkins", 9001, Math.PI / 10);
        partyByName = new Human[10];
        partyByName[0] = party[2];
        partyByName[1] = party[7];
        partyByName[2] = party[9];
        partyByName[3] = party[4];
        partyByName[4] = party[5];
        partyByName[5] = party[8];
        partyByName[6] = party[1];
        partyByName[7] = party[3];
        partyByName[8] = party[0];
        partyByName[9] = party[6];
        partyByStuns = new Human[10];
        partyByStuns[0] = party[8];
        partyByStuns[1] = party[1];
        partyByStuns[2] = party[7];
        partyByStuns[3] = party[6];
        partyByStuns[4] = party[0];
        partyByStuns[5] = party[4];
        partyByStuns[6] = party[3];
        partyByStuns[7] = party[9];
        partyByStuns[8] = party[2];
        partyByStuns[9] = party[5];
        partyByZombify = new Human[10];
        partyByZombify[0] = party[5];
        partyByZombify[1] = party[2];
        partyByZombify[2] = party[8];
        partyByZombify[3] = party[9];
        partyByZombify[4] = party[0];
        partyByZombify[5] = party[6];
        partyByZombify[6] = party[3];
        partyByZombify[7] = party[7];
        partyByZombify[8] = party[4];
        partyByZombify[9] = party[1];


        //fun fact, you can't iteratively copy the elements over to a new array
        //and change the properties without Java changing the properties with
        //both arrays.....
        gpb = new Human[10];
        gpb[0] = new Human("George P. Burdell", 42, 0.4);
        gpb[1] = new Human("George P. Burdell", 20, 0.7);
        gpb[2] = new Human("George P. Burdell", 99999, 0.0);
        gpb[3] = new Human("George P. Burdell", 123, 0.5);
        gpb[4] = new Human("George P. Burdell", 50, 0.6);
        gpb[5] = new Human("George P. Burdell",
                Integer.MAX_VALUE, Double.NEGATIVE_INFINITY);
        gpb[6] = new Human("George P. Burdell", 40, 0.4);
        gpb[7] = new Human("George P. Burdell", 30, 0.5);
        gpb[8] = new Human("George P. Burdell", 10, 0.1);
        gpb[9] = new Human("George P. Burdell", 9001, Math.PI / 10);
    }

    @Test(timeout = TIMEOUT)
    public void testAllTheExceptions() {
        somethingIsWrongWithBubbleSort();
        somethingIsWrongWithMergeSort();
        somethingIsWrongWithQuickSort();
        somethingIsWrongWithShellSort();
        somethingIsWrongWithRadixSort();
    }

    @Test(timeout = TIMEOUT)
    public void testZombieNameBubblesort() {
        Sorting.bubblesort(horde, (new Zombie(null, 0, 0)).getNameComparator());
        assertArrayEquals(hordeByName, horde);
    }

    @Test(timeout = TIMEOUT)
    public void testZombieKillBubblesort() {
        Sorting.bubblesort(
                horde, (new Zombie(null, 0, 0)).getKillsComparator());
        assertArrayEquals(hordeByKills, horde);
    }

    @Test(timeout = TIMEOUT)
    public void testZombieDayBubblesort() {
        Sorting.bubblesort(horde,
                (new Zombie(null, 0, 0)).getDaysSinceDeathComparator());
        assertArrayEquals(hordeByDay, horde);
    }

    @Test(timeout = TIMEOUT)
    public void testHumanNameBubblesort() {
        Sorting.bubblesort(party, (new Human(null, 0, 0)).getNameComparator());
        assertArrayEquals(partyByName, party);
    }

    @Test(timeout = TIMEOUT)
    public void testHumanStunBubblesort() {
        Sorting.bubblesort(party, (new Human(null, 0, 0)).getStunsComparator());
        assertArrayEquals(partyByStuns, party);
    }

    @Test(timeout = TIMEOUT)
    public void testHumanKilledBubblesort() {
        Sorting.bubblesort(party,
                (new Human(null, 0, 0)).getLikelinessToGetKilledComparator());
        assertArrayEquals(partyByZombify, party);
    }

    @Test(timeout = 2*TIMEOUT)
    public void testExtremeBubblesort() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>(5000);

        //note that this for loop adds the numbers 1-4999 in random locations.
        for (int i = 0; i < 5000; i++) {
            arrayList.add((int) (i * Math.random()), i);
        }

        //very inefficient code is very inefficient.
        Object[] unsortedObjectArray = arrayList.toArray();
        Integer[] unsortedArray = new Integer[5000];
        for (int i = 0; i < unsortedObjectArray.length; i++) {
            unsortedArray[i] = new Integer((int) unsortedObjectArray[i]);
        }

        Integer[] sortedArray = new Integer[5000];
        for (int i = 0; i < 5000; i++) {
            sortedArray[i] = new Integer(i);
        }
        Sorting.bubblesort(unsortedArray, getIntegerComparator());
        assertArrayEquals(sortedArray, unsortedArray);
    }

    @Test(timeout = TIMEOUT)
    public void testZombieNameInsertionsort() {
        Sorting.insertionsort(horde, (new Zombie(null, 0, 0))
                .getNameComparator());
        assertArrayEquals(hordeByName, horde);
    }

    @Test(timeout = TIMEOUT)
    public void testZombieKillInsertionsort() {
        Sorting.insertionsort(horde, (new Zombie(null, 0, 0))
                .getKillsComparator());
        assertArrayEquals(hordeByKills, horde);
    }

    @Test(timeout = TIMEOUT)
    public void testZombieDayInsertionsort() {
        Sorting.insertionsort(horde,
                (new Zombie(null, 0, 0)).getDaysSinceDeathComparator());
        assertArrayEquals(hordeByDay, horde);
    }

    @Test(timeout = TIMEOUT)
    public void testHumanNameInsertionsort() {
        Sorting.insertionsort(
                party, (new Human(null, 0, 0)).getNameComparator());
        assertArrayEquals(partyByName, party);
    }

    @Test(timeout = TIMEOUT)
    public void testHumanStunInsertionsort() {
        Sorting.insertionsort(
                party, (new Human(null, 0, 0)).getStunsComparator());
        assertArrayEquals(partyByStuns, party);
    }

    @Test(timeout = TIMEOUT)
    public void testHumanKilledInsertionsort() {
        Sorting.insertionsort(party,
                (new Human(null, 0, 0)).getLikelinessToGetKilledComparator());
        assertArrayEquals(partyByZombify, party);
    }

    @Test(timeout = 2*TIMEOUT)
    public void testExtremeInsertionsort() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>(5000);

        //note that this for loop adds the numbers 1-4999 in random locations.
        for (int i = 0; i < 5000; i++) {
            arrayList.add((int) (i * Math.random()), i);
        }

        //very inefficient code is very inefficient.
        Object[] unsortedObjectArray = arrayList.toArray();
        Integer[] unsortedArray = new Integer[5000];
        for (int i = 0; i < unsortedObjectArray.length; i++) {
            unsortedArray[i] = new Integer((int) unsortedObjectArray[i]);
        }

        Integer[] sortedArray = new Integer[5000];
        for (int i = 0; i < 5000; i++) {
            sortedArray[i] = new Integer(i);
        }
        Sorting.insertionsort(unsortedArray, getIntegerComparator());
        assertArrayEquals(sortedArray, unsortedArray);
    }


    @Test(timeout = TIMEOUT)
    public void testZombieNameShellsort() {
        Sorting.shellsort(horde, (new Zombie(null, 0, 0)).getNameComparator());
        for (int i = 1; i < horde.length; i++) {
            assertTrue(horde[i].getName().compareTo(horde[i - 1].getName())
                    >= 0);
        }
    }

    @Test(timeout = TIMEOUT)
    public void testZombieKillShellsort() {
        Sorting.shellsort(horde, (new Zombie(null, 0, 0)).getKillsComparator());
        for (int i = 1; i < horde.length; i++) {
            assertTrue(horde[i].getKills() - (horde[i - 1].getKills())
                    >= 0);
        }
    }

    @Test(timeout = TIMEOUT)
    public void testZombieDayShellsort() {
        Sorting.shellsort(horde, (
                new Zombie(null, 0, 0)).getDaysSinceDeathComparator());
        for (int i = 1; i < horde.length; i++) {
            assertTrue(horde[i].getDaysSinceDeath()
                    - (horde[i - 1].getDaysSinceDeath()) >= 0);
        }
    }

    @Test(timeout = TIMEOUT)
    public void testHumanNameShellsort() {
        Sorting.shellsort(party, (new Human(null, 0, 0)).getNameComparator());
        for (int i = 1; i < party.length; i++) {
            assertTrue(party[i].getName().compareTo(party[i - 1].getName())
                    >= 0);
        }
    }

    @Test(timeout = TIMEOUT)
    public void testHumanStunShellsort() {
        Sorting.shellsort(party, (new Human(null, 0, 0)).getStunsComparator());
        for (int i = 1; i < party.length; i++) {
            assertTrue(party[i].getStuns() - (party[i - 1].getStuns())
                    >= 0);
        }
    }

    @Test(timeout = TIMEOUT)
    public void testHumanKilledShellsort() {
        Sorting.shellsort(party, (
                new Human(null, 0, 0)).getLikelinessToGetKilledComparator());
        for (int i = 1; i < party.length; i++) {
            assertTrue(party[i].getLikelinessToGetKilled()
                    - (party[i - 1].getLikelinessToGetKilled()) >= 0);
        }
    }

    @Test(timeout = 2*TIMEOUT)
    public void testExtremeShellsort() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>(5000);

        //note that this for loop adds the numbers 1-4999 in random locations.
        for (int i = 0; i < 5000; i++) {
            arrayList.add((int) (i * Math.random()), i);
        }

        //very inefficient code is very inefficient.
        Object[] unsortedObjectArray = arrayList.toArray();
        Integer[] unsortedArray = new Integer[5000];
        for (int i = 0; i < unsortedObjectArray.length; i++) {
            unsortedArray[i] = new Integer((int) unsortedObjectArray[i]);
        }

        Integer[] sortedArray = new Integer[5000];
        for (int i = 0; i < 5000; i++) {
            sortedArray[i] = new Integer(i);
        }
        Sorting.shellsort(unsortedArray, getIntegerComparator());
        assertArrayEquals(sortedArray, unsortedArray);
    }


    @Test(timeout = TIMEOUT)
    public void testZombieNameQuicksort() {
        Sorting.quicksort(horde, (new Zombie(null, 0, 0)).getNameComparator(),
                new Random(0x600dc0de));
        for (int i = 1; i < horde.length; i++) {
            assertTrue(horde[i].getName().compareTo(horde[i - 1].getName())
                    >= 0);
        }
    }

    @Test(timeout = TIMEOUT)
    public void testZombieKillQuicksort() {
        Sorting.quicksort(horde, (new Zombie(null, 0, 0)).getKillsComparator(),
                new Random(0x600dc0de));
        for (int i = 1; i < horde.length; i++) {
            assertTrue(horde[i].getKills() - (horde[i - 1].getKills())
                    >= 0);
        }
    }

    @Test(timeout = TIMEOUT)
    public void testZombieDayQuicksort() {
        Sorting.quicksort(
                horde, (new Zombie(null, 0, 0)).getDaysSinceDeathComparator(),
                new Random(0x600dc0de));
        for (int i = 1; i < horde.length; i++) {
            assertTrue(horde[i].getDaysSinceDeath()
                    - (horde[i - 1].getDaysSinceDeath()) >= 0);
        }
    }

    @Test(timeout = TIMEOUT*2)
    public void testHumanNameQuicksort() {
        Sorting.quicksort(party, (new Human(null, 0, 0)).getNameComparator(),
                new Random(randomSeed));
        //if you want to test a specific seed you failed with, replace
        //randomSeed with the hexadecimal value the test failure message gave
        for (int i = 1; i < party.length; i++) {
            assertTrue("The Random seed this failed with was " + seed + ".",
                    party[i].getName().compareTo(party[i - 1].getName())
                            >= 0);
        }

    }

    @Test(timeout = TIMEOUT)
    public void testHumanStunQuicksort() {
        Sorting.quicksort(party, (new Human(null, 0, 0)).getStunsComparator(),
                new Random(randomSeed));
        //if you want to test a specific seed you failed with, replace
        //randomSeed with the hexadecimal value the test failure message gave
        for (int i = 1; i < party.length; i++) {
            assertTrue("The Random seed this failed with was " + seed + ".",
                    party[i].getStuns() - (party[i - 1].getStuns())
                            >= 0);
        }
    }

    @Test(timeout = TIMEOUT)
    public void testHumanKilledQuicksort() {
        Sorting.quicksort(
                party,
                (new Human(null, 0, 0)).getLikelinessToGetKilledComparator(),
                new Random(randomSeed));
        //if you want to test a specific seed you failed with, replace
        //randomSeed with the hexadecimal value the test failure message gave
        for (int i = 1; i < horde.length; i++) {
            assertTrue("The Random seed this failed with was " + seed + ".",
                    party[i].getLikelinessToGetKilled()
                            - (party[i - 1].getLikelinessToGetKilled()) >= 0);
        }
    }

    @Test(timeout = 2*TIMEOUT)
    public void testExtremeQuicksort() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>(5000);

        //note that this for loop adds the numbers 1-4999 in random locations.
        for (int i = 0; i < 5000; i++) {
            arrayList.add((int) (i * Math.random()), i);
        }

        //very inefficient code is very inefficient.
        Object[] unsortedObjectArray = arrayList.toArray();
        Integer[] unsortedArray = new Integer[5000];
        for (int i = 0; i < unsortedObjectArray.length; i++) {
            unsortedArray[i] = new Integer((int) unsortedObjectArray[i]);
        }

        Integer[] sortedArray = new Integer[5000];
        for (int i = 0; i < 5000; i++) {
            sortedArray[i] = new Integer(i);
        }
        Sorting.quicksort(unsortedArray, getIntegerComparator(),
                new Random(randomSeed));
        //if you want to test a specific seed you failed with, replace
        //randomSeed with the hexadecimal value the test failure message gave
        assertArrayEquals("The Random seed this failed with was " + seed + ".",
                sortedArray, unsortedArray);
    }


    @Test(timeout = TIMEOUT)
    public void testZombieNameMergesort() {
        Sorting.mergesort(horde, (new Zombie(null, 0, 0)).getNameComparator());
        assertArrayEquals(hordeByName, horde);
    }

    @Test(timeout = TIMEOUT)
    public void testZombieKillMergesort() {
        Sorting.mergesort(horde, (new Zombie(null, 0, 0)).getKillsComparator());
        assertArrayEquals(hordeByKills, horde);
    }

    @Test(timeout = TIMEOUT)
    public void testZombieDayMergesort() {
        Sorting.mergesort(horde,
                (new Zombie(null, 0, 0)).getDaysSinceDeathComparator());
        assertArrayEquals(hordeByDay, horde);
    }

    @Test(timeout = TIMEOUT)
    public void testHumanNameMergesort() {
        Sorting.mergesort(party, (new Human(null, 0, 0)).getNameComparator());
        assertArrayEquals(partyByName, party);
    }

    @Test(timeout = TIMEOUT)
    public void testHumanStunMergesort() {
        Sorting.mergesort(party, (new Human(null, 0, 0)).getStunsComparator());
        assertArrayEquals(partyByStuns, party);
    }

    @Test(timeout = TIMEOUT)
    public void testHumanKilledMergesort() {
        Sorting.mergesort(party,
                (new Human(null, 0, 0)).getLikelinessToGetKilledComparator());
        assertArrayEquals(partyByZombify, party);
    }

    @Test(timeout = 2*TIMEOUT)
    public void testExtremeMergesort() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>(5000);

        //note that this for loop adds the numbers 1-4999 in random locations.
        for (int i = 0; i < 5000; i++) {
            arrayList.add((int) (i * Math.random()), i);
        }

        //very inefficient code is very inefficient.
        Object[] unsortedObjectArray = arrayList.toArray();
        Integer[] unsortedArray = new Integer[5000];
        for (int i = 0; i < unsortedObjectArray.length; i++) {
            unsortedArray[i] = new Integer((int) unsortedObjectArray[i]);
        }

        Integer[] sortedArray = new Integer[5000];
        for (int i = 0; i < 5000; i++) {
            sortedArray[i] = new Integer(i);
        }
        Sorting.mergesort(unsortedArray, getIntegerComparator());
        assertArrayEquals(sortedArray, unsortedArray);
    }


    @Test(timeout = TIMEOUT)
    public void testRegularPositiveRadixsort() {
        int[] unsortedArray = new int[] {54, 28, 58, 84, 20, 122, 85, 3};
        int[] sortedArray = new int[] {3, 20, 28, 54, 58, 84, 85, 122};
        assertArrayEquals(sortedArray, Sorting.radixsort(unsortedArray));
    }

    @Test(timeout = 2*TIMEOUT)
    public void testExtremePositiveRadixsort() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>(10000);

        //note that this for loop adds the numbers 1-10000 in random locations.
        for (int i = 0; i < 10000; i++) {
            arrayList.add((int) (i * Math.random()), i + 1);
        }

        //very inefficient code is very inefficient.
        Object[] unsortedObjectArray = arrayList.toArray();
        int[] unsortedArray = new int[10000];
        for (int i = 0; i < unsortedObjectArray.length; i++) {
            unsortedArray[i] = (int) unsortedObjectArray[i];
        }

        int[] sortedArray = new int[10000];
        for (int i = 0; i < 10000; i++) {
            sortedArray[i] = i + 1;
        }
        assertArrayEquals(sortedArray, Sorting.radixsort(unsortedArray));
    }

    @Test(timeout = TIMEOUT)
    public void testRegularNegativeRadixsort() {
        int[] unsortedArray =
                new int[] {-54, -28, -58, -84, -20, -122, -85, -3};
        int[] sortedArray = new int[] {-122, -85, -84, -58, -54, -28, -20, -3};
        assertArrayEquals(sortedArray, Sorting.radixsort(unsortedArray));

    }

    @Test(timeout = TIMEOUT)
    public void testRegularNegativePositiveZeroRadixsort() {
        int[] unsortedArray =
                new int[] {-54, 28, -58, -84, 0, -122, -85, 33};
        int[] sortedArray = new int[] {-122, -85, -84, -58, -54, 0, 28, 33};
        assertArrayEquals(sortedArray, Sorting.radixsort(unsortedArray));

    }


    @Test(timeout = 2*TIMEOUT)
    public void testExtremeNegativeRadixsort() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>(10000);

        //note this for loop adds the numbers -9999 to -1 in random locations.
        for (int i = 1; i < 10000; i++) {
            arrayList.add((int) (i * Math.random()), -i);
        }

        //very inefficient code is very inefficient.
        Object[] unsortedObjectArray = arrayList.toArray();
        int[] unsortedArray = new int[10000];
        for (int i = 0; i < unsortedObjectArray.length; i++) {
            unsortedArray[i] = (int) unsortedObjectArray[i];
        }

        int[] sortedArray = new int[10000];
        int value = -9999;
        for (int i = 0; i < sortedArray.length; i++) {
            sortedArray[i] = value;
            value++;
        }
//        int[] test = Sorting.radixsort(unsortedArray);
//        for (int i = 0; i < sortedArray.length; i++) {
//            System.out.println(test[i]);
//        }
        assertArrayEquals(sortedArray, Sorting.radixsort(unsortedArray));
    }

    @Test(timeout = 2*TIMEOUT)
    public void testExtremeNegativeZeroPositiveRadixsort() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>(10000);

        //note this for loop adds the numbers -5000 to 4999 in random locations.
        for (int i = -5000; i < 5000; i++) {
            arrayList.add((int) ((i + 5000) * Math.random()), i);
        }

        //very inefficient code is very inefficient.
        Object[] unsortedObjectArray = arrayList.toArray();
        int[] unsortedArray = new int[10000];
        for (int i = 0; i < unsortedObjectArray.length; i++) {
            unsortedArray[i] = (int) unsortedObjectArray[i];
        }

        int[] sortedArray = new int[10000];
        int value = -5000;
        for (int i = 0; i < sortedArray.length; i++) {
            sortedArray[i] = value;
            value++;
        }
        int[] test = Sorting.radixsort(unsortedArray);
//        for (int i = 0; i < sortedArray.length; i++) {
//            System.out.println(test[i]);
//        }

        assertArrayEquals(sortedArray, Sorting.radixsort(unsortedArray));
    }

    @Test(timeout = TIMEOUT)
    public void georgePBurdellApprovesTheStabilityOfBubbleSort() {
        Human[] gpbClone = new Human[10];
        for (int i = 0; i < gpb.length; i++) {
            gpbClone[i] = gpb[i];
        }
        Sorting.bubblesort(
                gpbClone, new Human(null, 0, 0.0).getNameComparator());
        assertArrayEquals(gpb, gpbClone);
    }

    @Test(timeout = TIMEOUT)
    public void georgePBurdellApprovesTheStabilityOfInsertionSort() {
        Human[] gpbClone = new Human[10];
        for (int i = 0; i < gpb.length; i++) {
            gpbClone[i] = gpb[i];
        }
        Sorting.insertionsort(
                gpbClone, new Human(null, 0, 0.0).getNameComparator());
        assertArrayEquals(gpb, gpbClone);
    }


    @Test(timeout = TIMEOUT)
    public void georgePBurdellApprovesTheStabilityOfMergeSort() {
        Human[] gpbClone = new Human[10];
        for (int i = 0; i < gpb.length; i++) {
            gpbClone[i] = gpb[i];
        }
        Sorting.mergesort(
                gpbClone, new Human(null, 0, 0.0).getNameComparator());
        assertArrayEquals(gpb, gpbClone);
    }


    /*Can't figure out a way to test stability with RadixSort given it only
      involves ints....you're on you're own for that one, if it matters.
     */


    /**
     * Randomizes and stores the Random seed as a String.
     * Does so through the use of bit-shifting (2110 material right here :P)
     * Thank you Java API for not having a getSeed() method in Random.
     *
     * @return  Random's new seed in the form of a hexadecimal string
     */
    private String getRandomSeed() {
        long newSeed = 0;
        Random randBoolean = new Random(randomSeed);
        for (int i = 0; i < 48; i++) {
            if (randBoolean.nextBoolean()) {
                newSeed += 1 << i;
            }
        }
        if (newSeed < 0) {
            newSeed = Math.abs(newSeed);
        }

        String s = "";
        if (newSeed == 0) {
            s += "0";
        } else {
            while (newSeed != 0) {
                long num = (newSeed & (0xF));
                if (num < 10) {
                    s = num + s;
                } else {
                    s = ((char) (num + 55)) + s;
                }
                newSeed = newSeed >> 4;
            }
        }
        return s;
    }


    /**
     * Tests exceptions with bubblesort
     */
    private void somethingIsWrongWithBubbleSort() {
        try {
            Sorting.bubblesort(
                    null, (new Zombie(null, 0, 0).getNameComparator()));
            fail("You didn't even throw the exception for the array.");
        } catch (IllegalArgumentException e) {
            assertNotNull(
                    "Good job throwing the exception...but you need a message!",
                    e.getMessage());
        }
        try {
            Sorting.bubblesort(horde, null);
            fail("You didn't even throw the exception for the comparator.");
        } catch (IllegalArgumentException e) {
            assertNotNull(
                    "Good job throwing the exception...but you need a message!",
                    e.getMessage());
        }
    }

    /**
     * Tests exceptions with merge sort
     */
    private void somethingIsWrongWithMergeSort() {
        try {
            Sorting.mergesort(
                    null, (new Zombie(null, 0, 0).getNameComparator()));
            fail("You didn't even throw the exception for the array.");
        } catch (IllegalArgumentException e) {
            assertNotNull(
                    "Good job throwing the exception...but you need a message!",
                    e.getMessage());
        }
        try {
            Sorting.mergesort(horde, null);
            fail("You didn't even throw the exception for the comparator.");
        } catch (IllegalArgumentException e) {
            assertNotNull(
                    "Good job throwing the exception...but you need a message!",
                    e.getMessage());
        }
    }

    /**
     * Tests exceptions with quick sort
     */
    private void somethingIsWrongWithQuickSort() {
        try {
            Sorting.quicksort(
                    null, (new Zombie(null, 0, 0).getNameComparator()),
                    new Random(0x600dc0de));
            fail("You didn't even throw the exception for the array.");
        } catch (IllegalArgumentException e) {
            assertNotNull(
                    "Good job throwing the exception...but you need a message!",
                    e.getMessage());
        }
        try {
            Sorting.quicksort(horde, null, new Random(0x600dc0de));
            fail("You didn't even throw the exception for the comparator.");
        } catch (IllegalArgumentException e) {
            assertNotNull(
                    "Good job throwing the exception...but you need a message!",
                    e.getMessage());
        }
        try {
            Sorting.quicksort(horde,
                    (new Zombie(null, 0, 0).getNameComparator()), null);
            fail("You didn't even throw the exception for the random.");
        } catch (IllegalArgumentException e) {
            assertNotNull(
                    "Good job throwing the exception...but you need a message!",
                    e.getMessage());
        }
    }

    /**
     * Tests exceptions with shell sort
     */
    private void somethingIsWrongWithShellSort() {
        try {
            Sorting.shellsort(
                    null, (new Zombie(null, 0, 0).getNameComparator()));
            fail("You didn't even throw the exception for the array.");
        } catch (IllegalArgumentException e) {
            assertNotNull(
                    "Good job throwing the exception...but you need a message!",
                    e.getMessage());
        }
        try {
            Sorting.shellsort(horde, null);
            fail("You didn't even throw the exception for the comparator.");
        } catch (IllegalArgumentException e) {
            assertNotNull(
                    "Good job throwing the exception...but you need a message!",
                    e.getMessage());
        }
    }

    /**
     * Tests exceptions with radix sort.
     */
    private void somethingIsWrongWithRadixSort() {
        try {
            Sorting.radixsort(null);
            fail("You didn't even throw the exception for the array.");
        } catch (IllegalArgumentException e) {
            assertNotNull(
                    "Good job throwing the exception...but you need a message!",
                    e.getMessage());
        }
    }


    /**
     * Integer comparator for some tests.......yeah.
     * @return  Comparator that can do basic subtraction. Woah.
     */
    private Comparator<Integer> getIntegerComparator() {
        return new Comparator<Integer>() {
            @Override
            public int compare(Integer integer1, Integer integer2) {
                return integer1 - integer2;
            }
        };
    }
    /**
     * Because if you're going to shamelessly promote Humans versus Zombies...
     * a Zombies class alone isn't good enough!
     */
    private class Human {
        private String name;
        private int stuns;
        private double likelinessToGetKilled;

        /**
         * Create a human
         *
         * @param name  name of zombie
         * @param stuns number of zombie stuns from the human
         * @param likelinessToGetKilled scale of 1-10 for likeliness to zombify
         */
        public Human(String name, int stuns, double likelinessToGetKilled) {
            this.name = name;
            this.stuns = stuns;
            this.likelinessToGetKilled = likelinessToGetKilled;
        }
        /**
         * Get the name of the zombie.
         *
         * @return name of zombie
         */
        public String getName() {
            return name;
        }

        /**
         * Get the number of stuns by the human.
         *
         * @return number of stuns by the human
         */
        public int getStuns() {
            return stuns;
        }

        /**
         * Get the likeliness for the human to become a zombie.
         *
         * @return likeliness for the human to become a zombie.
         */
        public double getLikelinessToGetKilled() {
            return likelinessToGetKilled;
        }

        /**
         * Set the name of the human.
         *
         * @param name name of the human
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * Set the number of kills by the human.
         *
         * @param stuns number of stuns by the human
         */
        public void setStuns(int stuns) {
            this.stuns = stuns;
        }

        /**
         * Set the likeliness for the human to die.
         *
         * @param likelinessToGetKilled new likeliness for human to die
         */
        public void setLikelinessToGetKilled(int likelinessToGetKilled) {
            this.likelinessToGetKilled = likelinessToGetKilled;
        }

        /**
         * Create a comparator that compares the names of the humans.
         *
         * @return comparator that compares the names of the humans
         */
        public Comparator<Human> getNameComparator() {
            return new Comparator<Human>() {
                @Override
                public int compare(Human human1, Human human2) {
                    return human1.getName().compareTo(human2.getName());
                }
            };
        }

        /**
         * Create a comparator that compares the number of stuns from each human
         *
         * @return comparator that compares the number of stuns
         */
        public Comparator<Human> getStunsComparator() {
            return new Comparator<Human>() {
                @Override
                public int compare(Human human1, Human human2) {
                    return human1.getStuns() - human2.getStuns();
                }
            };
        }

        /**
         * Create a comparator that compares the likelihood to become a zombie.
         *
         * @return comparator that compares the likelihood to become a zombie.
         */
        public Comparator<Human> getLikelinessToGetKilledComparator() {
            return new Comparator<Human>() {
                @Override
                public int compare(Human human1, Human human2) {
                    return (int) (100 * (human1.getLikelinessToGetKilled()
                            - human2.getLikelinessToGetKilled()));
                }
            };
        }

        /**
         * Made a toString() method for Human to help with debugging :D
         * @return String representation of Human
         */
        public String toString() {
            return name + " with " + stuns + " stuns is "
                    + likelinessToGetKilled + " likely to die.";
        }
    }


    /**
     * This is a Zombie class that may or may not be related to Humans versus
     * Zombies (hvz.gatech.edu).
     * However it might just be a nondescript zombie. This isn't an
     * advertisement for the best game on campus.
     */
    private class Zombie {
        private String name;
        private int kills;
        private int daysSinceDeath;

        /**
         * Create a zombie.
         *
         * @param name name of zombie
         * @param kills number of kills by the zombie
         * @param daysSinceDeath number of days since the zombie died
         */
        public Zombie(String name, int kills, int daysSinceDeath) {
            this.name = name;
            this.kills = kills;
            this.daysSinceDeath = daysSinceDeath;
        }

        /**
         * Get the name of the zombie.
         *
         * @return name of zombie
         */
        public String getName() {
            return name;
        }

        /**
         * Get the number of kills by the zombie.
         *
         * @return number of kill by the zombie
         */
        public int getKills() {
            return kills;
        }

        /**
         * Get the number of days since the zombie died.
         *
         * @return number of days since the zombie died
         */
        public int getDaysSinceDeath() {
            return daysSinceDeath;
        }

        /**
         * Set the name of the zombie.
         *
         * @param name name of the zombie
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * Set the number of kills by the zombie.
         *
         * @param kills number of kills by the zombie
         */
        public void setKills(int kills) {
            this.kills = kills;
        }

        /**
         * Set the days since the zombie died.
         *
         * @param daysSinceDeath days since the zombie died
         */
        public void setDaysSinceDeath(int daysSinceDeath) {
            this.daysSinceDeath = daysSinceDeath;
        }

        /**
         * Create a comparator that compares the names of the zombies.
         *
         * @return comparator that compares the names of the zombies
         */
        public Comparator<Zombie> getNameComparator() {
            return new Comparator<Zombie>() {
                @Override
                public int compare(Zombie zombie1, Zombie zombie2) {
                    return zombie1.getName().compareTo(zombie2.getName());
                }
            };
        }

        /**
         * Create a comparator that compares the number of kills of each zombie.
         *
         * @return comparator that compares the number of kills
         */
        public Comparator<Zombie> getKillsComparator() {
            return new Comparator<Zombie>() {
                @Override
                public int compare(Zombie zombie1, Zombie zombie2) {
                    return zombie1.getKills() - zombie2.getKills();
                }
            };
        }

        /**
         * Create a comparator that compares the number of days since death.
         *
         * @return comparator that compares the number of days since death
         */
        public Comparator<Zombie> getDaysSinceDeathComparator() {
            return new Comparator<Zombie>() {
                @Override
                public int compare(Zombie zombie1, Zombie zombie2) {
                    return zombie1.getDaysSinceDeath()
                            - zombie2.getDaysSinceDeath();
                }
            };
        }

        /**
         * Made a toString() method for Zombie to help with debugging :D
         * @return String representation of Zombie
         */
        public String toString() {
            return name + " with " + kills + " kills died " + daysSinceDeath
                    + " days ago.";
        }

    }
}