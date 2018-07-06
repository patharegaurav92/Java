
public class Palindrome {
	public static boolean isPalindrome(String s) {
        int i=0,j=s.length()-1;
        s=s.toLowerCase();
        //System.out.println(!Character.isLetter(s.charAt(i)));
       
         while(i<j){
             while(i<j && !Character.isLetterOrDigit(s.charAt(i)))
                 i++;
             while(i<j && !Character.isLetterOrDigit(s.charAt(j)))
                 j--;
             if(s.charAt(i++) == s.charAt(j--)) continue ;
             else return false;
         }
        return true;
    }
public static void main(String args[]){
	System.out.println(Palindrome.isPalindrome("A man, a plan, a canal: Panama"));
}
}
