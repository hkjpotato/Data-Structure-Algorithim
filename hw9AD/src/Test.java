/**
 * Created by kaijiehuang on 15-4-9.
 */
public class Test {

    public static void main(String[] args) {
        SearchableString pattern = new SearchableString("xabzabzab");
        bmPreprocess1(pattern);
    }






    static void bmPreprocess1(SearchableString pattern ) {
        int m = pattern.length();
        int i = m;
        int j = m + 1;
        int[] s = new int[m + 1];
        int[] f = new int[m + 1];
        f[m] = m + 1;


        while (i > 0) {
            while (j <= m && pattern.charAt(i - 1) != pattern.charAt(j - 1)) {
                if (s[j] == 0) {
                    s[j] = j - i;
                }
                j = f[j];

            }
            i--; j--;
            f[i] = j;
        }

        System.out.println("aababab");


        for (int x : f) {
            System.out.print(x);

        }
        System.out.println();

        for (int x : s) {
            System.out.print(x);

        }




    }

}
