package arithmetic;

public class ReverseString {

    public static void main(String[] args) {

        String name = "zhangliming";

        char[] s = name.toCharArray();

        reverseString(s);

        System.out.println(s);
    }

    public static void reverseString(char[] s) {
        int n = s.length;
        for (int left = 0, right = n - 1; left < right; left++, --right) {
            char tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;
        }
    }
}
