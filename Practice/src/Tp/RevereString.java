package Tp;

public class RevereString {
	public static void main(String args[]){
		System.out.println(reverse("abcde"));
		
		System.out.println(reverse(""));System.out.println(reverse(null));System.out.println(reverse("a"));
		
	}

	private static String reverse(String string) {
		String inputString  = string;
		String outputString = null;
		 if(inputString == null) return outputString;
		    if(inputString.length() == 1) return inputString;
		    for(int i=string.length()-1;i>=0;i--){
		    	if(outputString == null)
		        outputString=Character.toString(inputString.charAt(i));
		    	else{
		    		outputString+=Character.toString(inputString.charAt(i));
		    	}
		    }
			return outputString;
	}
}
