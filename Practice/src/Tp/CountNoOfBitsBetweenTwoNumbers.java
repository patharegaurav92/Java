package Tp;

public class CountNoOfBitsBetweenTwoNumbers {

	public static void main(String args[]){
		int unsetbits = 0;
		int n = 9;
		
		while(n>0){
			if((n & 1) == 0)
				unsetbits++;
			n = n >> 1;
		}
		System.out.println(1 << unsetbits);
		
		int res = setBitNumber(85);
		System.out.println(res);
		System.out.println(Integer.toBinaryString(5));
		System.out.println(Integer.toBinaryString(-5));
	}
	
	public static int setBitNumber(int n)
	{      
	    // Below steps set bits after
	    // MSB (including MSB)
	 
	    // Suppose n is 273 (binary 
	    // is 100010001). It does following
	    // 100010001 | 010001000 = 110011001
	    n |= n>>1;
	 
	    // This makes sure 4 bits
	    // (From MSB and including MSB)
	    // are set. It does following
	    // 110011001 | 001100110 = 111111111
	    n |= n>>2;   
	 
	    n |= n>>4;  
	    n |= n>>8;
	    n |= n>>16;
	     
	    // Increment n by 1 so that
	    // there is only one set bit
	    // which is just before original
	    // MSB. n now becomes 1000000000
	    n = n + 1;
	 
	    // Return original MSB after shifting.
	    // n now becomes 100000000
	    return (n >> 1);
	}
}
