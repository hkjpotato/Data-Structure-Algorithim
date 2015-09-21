import java.util.ArrayList;
import java.util.List;

public class StringSearching implements StringSearchingInterface {

    @Override
    public List<Integer> kmp(CharSequence pattern, CharSequence text) {
        return null;
    }

    @Override
    public int[] buildFailureTable(CharSequence text) {
        return null;
    }



    /**
     * TODO delete this later
     * Boyer Moore algorithm that relies on last table. Make sure to implement
     * the table before implementing this method. Works better with large
     * alphabets.
     *
     * @throws IllegalArgumentException if the pattern is null or of length 0
     * @throws IllegalArgumentException if text is null
     * @param pattern a CharSequence you are searching for in a body of text
     * @param text the body of text where you search for pattern
     * @return list of integers representing the first index a match occurs or
     * an empty list if the text is of length 0
     */

    @Override
    public List<Integer> boyerMoore(CharSequence pattern, CharSequence text) {

        //TODO if buildLastTable is of O(alphabetSize) and used in boyerMoore,
        // does the big O of BM affected?


        if (pattern == null || pattern.length() == 0) {
            throw new IllegalArgumentException("The input pattern is illegal.");
        }

        if (text == null) {
            throw new IllegalArgumentException("The input text is null.");
        }

        List<Integer> list = new ArrayList<>();
        char a = pattern.charAt(2);
        System.out.print(a);


        int[] lastTable = buildLastTable(pattern);
        int location = pattern.length() - 1;
//        int locationKJ = location;

        //loop through the whole text to check all the match pattern.
        //loop from text[0] to text[text.length - pattern.length]
        while( location <= text.length() - 1) {


            //start from the last character
            //check how many characters match
            int currentIndex = pattern.length();
            boolean tempMatch = true;
            while (currentIndex >= 0 && tempMatch) {
                currentIndex--;

                //FIXME for testing
                char a1 = pattern.charAt(currentIndex);
                char b = text.charAt(location - (pattern.length() - 1 - currentIndex));



                if (pattern.charAt(currentIndex) != text.charAt(location - (pattern.length() - 1 - currentIndex))) {
                    tempMatch = false;
                }
            }



            if (tempMatch) {
                list.add(locationkj);
            }


            location = Math.max(location + 1,
                    location - (pattern.length() - 1 - currentIndex)
                            + pattern.length() - (lastTable[text.charAt(location - (pattern.length() - 1 - currentIndex))] + 1));



            //move to next Possible Place
            //check if the current char of the text exist in pattern




        }

        return list;



    }

    @Override
    public int[] buildLastTable(CharSequence pattern) {
        if (pattern == null) {
            throw new IllegalArgumentException("The input pattern is null.");

        }
        int[] table = new int[Character.MAX_VALUE+1];

        //take O(n) to build last table; n is the size of the alphabet
        for (int i = 0; i < table.length; i++) {
            table[i] = -1;
        }

        for (int i = pattern.length() - 1; i >= 0; i--) {
            char currentChar = pattern.charAt(i);
            if (table[currentChar] == -1) {
                table[currentChar] = i;
            }
        }

        return table;
    }

    @Override
    public int generateHash(CharSequence current, int length) {
        //TODO take care of other's exception
        if (current == null) {
            throw new IllegalArgumentException("The current string is null.");
        }

        if (length < 0 || length > current.length()) {
            throw new IllegalArgumentException("The input length is illegal.");

        }

        int hash = 0;
        int hashOfChar;

        for (int i = 0; i < length; i++) {
            hashOfChar = current.charAt(i);
            int exponent = length - 1 - i;
            while (exponent  > 0) {
                hashOfChar *= BASE;
                exponent--;
            }
            hash += hashOfChar;
        }

        return hash;
    }

    @Override
    public int updateHash(int oldHash, int length, char oldChar, char newChar) {
        if (length < 0) {
            throw new IllegalArgumentException("The input length is illegal.");

        }

        int oldCharValue = oldChar;
        while (length > 1) {
            oldCharValue *= BASE;
            length--;
        }

        return BASE * (oldHash - oldCharValue) + newChar;
    }

    @Override
    public List<Integer> rabinKarp(CharSequence pattern, CharSequence text) {
        return null;
    }
}
