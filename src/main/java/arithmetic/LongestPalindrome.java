package arithmetic;

public class LongestPalindrome {


    /**
     * 暴力算法
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if(s.isEmpty()){
            return s;
        }
        String res=s.substring(0,1);
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                String k=s.substring(i,j);
                String rk=new StringBuffer(k).reverse().toString();
                if(k.equals(rk)&&k.length()>res.length()){
                    res=k;
                }
            }

        }
        return res;
    }

}
