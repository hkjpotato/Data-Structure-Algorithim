public class SearchableString implements CharSequence {
    private String str;
    private int count;

    /**
     * Creates the SearchableString
     * @param s the string for the SearchableString to be created from
     */
    public SearchableString(String s) {
        str = s;
        count = 0;
    }

    @Override
    public char charAt(int i) {
        count++;
        return str.charAt(i);
    }

    @Override
    public int length() {
        return str.length();
    }

    /**
     * Returns the number of times charAt has been called for this object
     * @return the number of times charAt has been called for this object
     */
    public int getCount() {
        return count;
    }

    /**
     * Do NOT use this. It will not help at all.
     * @param start a parameter that should not be used
     * @param end another parameter that should not be used
     * @return never
     */
    @Override
    public CharSequence subSequence(int start, int end) {
        throw new UnsupportedOperationException("Do not use method "
                + "subSequence.");
    }

    /**
     * Do NOT use this. It will not help at all.
     * @return "Do not use"
     */
    @Override
    public String toString() {
        return "Do not use.";
    }

    /**
     * Do NOT use this. It will not help at all.
     * @param o a parameter that should not be used
     * @return never
     */
    @Override
    public boolean equals(Object o) {
        throw new UnsupportedOperationException("Do not use method equals.");
    }
}
