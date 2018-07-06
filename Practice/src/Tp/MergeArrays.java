package Tp;

public class MergeArrays {

	public static int[] merge(int[] arrLeft, int[] arrRight){
	    if(arrLeft.length == 0) return arrRight;
	    if(arrRight.length == 0) return arrLeft;
	int f=0,s=0,t=0;
	int fsize = arrLeft.length;
	int ssize = arrRight.length;
	int[] third = new int[arrLeft.length+arrRight.length];
	int tsize = third.length;
	while(t<tsize){
	    
	        while(s<ssize && f<fsize){
	            if(arrLeft[f]>arrRight[s]){
	                third[t] = arrRight[s];
	                s++;
	                t++;
	            }else if(arrLeft[f]<=arrRight[s]){
	                third[t] = arrLeft[f];
	                f++;
	                t++;
	            }
	        }
	        while(s<ssize && f == fsize){
	            third[t] = arrRight[s];
	            s++;
	            t++;
	        }
	        while(s==ssize && f< fsize){
	            third[t] = arrLeft[f];
	            f++;
	            t++;
	        }
	}

	return third;

	}
	
	public static void main(String args[]){
		int[] first = {2,5,7,8};
		int[] second = {2,4,7,12,32};
		int[] third = merge(first,second);
		for(int num: third){
			System.out.print(num+" ");
		}
	}
}
