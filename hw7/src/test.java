/**
 * Created by kaijiehuang on 15-3-9.
 */
public class test {
    int i = 2;
    public static void main(String[] args) {
        boolean testboolean = false;
        method1(testboolean);
        if (testboolean) {
            System.out.print("haha");
        }
    }

    public static void method1(boolean testb) {
        testb = true;
    }

}
