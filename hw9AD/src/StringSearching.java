import java.util.ArrayList;
import java.util.List;

public class StringSearching implements StringSearchingInterface {

    @Override
    public List<Integer> kmp(CharSequence pattern, CharSequence text) {
        if (pattern == null || pattern.length() == 0) {
            throw new IllegalArgumentException("The input pattern is illegal.");
        }

        if (text == null) {
            throw new IllegalArgumentException("The input text is null.");
        }

        List<Integer> list = new ArrayList<>();
        //location points to text
        int location = 0;
        //currentIndex points to pattern
        int currentIndex = 0;
        char currentChar;
        int[] failureTable = buildFailureTable(pattern);

        while (location <= text.length() - 1) {
            currentChar = text.charAt(location);
            if (pattern.charAt(currentIndex) == currentChar) {
                if (currentIndex == pattern.length() - 1) {
                    list.add(location - (pattern.length() - 1));
                    currentIndex = failureTable[currentIndex];
                    location++;
                } else {
                    location++;
                    currentIndex++;
                }
            } else {
                if (currentIndex == 0) {
                    location++;
                } else {
                    currentIndex = failureTable[currentIndex - 1];
                }
            }

        }
        return list;
    }

    @Override
    public int[] buildFailureTable(CharSequence text) {
        if (text == null) {
            throw new IllegalArgumentException("The input text is null.");
        }

        int[] failureTable = new int[text.length()];
        failureTable[0] = 0;
        int location = 1;
        int currentIndex = 0;
        while (location < text.length()) {
            if (text.charAt(location) == text.charAt(currentIndex)) {
                failureTable[location] = currentIndex + 1;
                location++;
                currentIndex++;
            } else {
                if (currentIndex > 0) {
                    currentIndex = failureTable[currentIndex - 1];
                } else {
                    failureTable[location] = 0;
                    location++;
                }
            }
        }
        return failureTable;
    }

    @Override
    public List<Integer> boyerMoore(CharSequence pattern, CharSequence text) {
        if (pattern == null || pattern.length() == 0) {
            throw new IllegalArgumentException("The input pattern is illegal.");
        }

        if (text == null) {
            throw new IllegalArgumentException("The input text is null.");
        }

        List<Integer> list = new ArrayList<>();

        if (text.length() >= pattern.length()) {

            int[] lastTable = buildLastTable(pattern);
            int location = pattern.length() - 1;
            int currentIndex = pattern.length() - 1;
            char currentChar;

            while (location <= text.length() - 1) {
                currentChar = text.charAt(location);
                if (pattern.charAt(currentIndex) == currentChar) {
                    if (currentIndex == 0) {
                        list.add(location);
                        location += pattern.length();
                        currentIndex = pattern.length() - 1;
                    } else {
                        currentIndex--;
                        location--;
                    }
                } else {
                    int lastIndex = lastTable[currentChar];
                    location += pattern.length()
                            - Math.min(currentIndex, 1 + lastIndex);
                    currentIndex = pattern.length() - 1;
                }
            }
        }
        return list;
    }

    @Override
    public int[] buildLastTable(CharSequence pattern) {
        if (pattern == null) {
            throw new IllegalArgumentException("The input pattern is null.");

        }
        int[] table = new int[Character.MAX_VALUE + 1];

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
        if (pattern == null || pattern.length() == 0) {
            throw new IllegalArgumentException("The input pattern is illegal.");
        }

        if (text == null) {
            throw new IllegalArgumentException("The input text is null.");
        }


        List<Integer> list = new ArrayList<>();

        if (text.length() >= pattern.length()) {
            int patternHash = generateHash(pattern, pattern.length());
            int textHash = generateHash(text, pattern.length());
            //location points to text
            int location = 0;

            while (location <= text.length() - pattern.length()) {
                if (patternHash == textHash) {
                    //currentIndex points to pattern
                    int currentIndex = 0;
                    while (currentIndex < pattern.length()
                            && text.charAt(location)
                            == pattern.charAt(currentIndex)) {
                        currentIndex++;
                        location++;
                    }

                    //reset the location to original point
                    location -= currentIndex;

                    if (currentIndex == pattern.length()) {
                        list.add(location);
                    }
                }

                if (location + pattern.length() < text.length()) {
                    textHash = updateHash(textHash, pattern.length(),
                            text.charAt(location),
                            text.charAt(location + pattern.length()));
                }

                location++;
            }
        }
        return list;
    }
}
