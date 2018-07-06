package Tp;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DuplicateStrings {
	public static String duplicate(int[] numbers){
	    
		HashMap<Integer,Integer> hm = new HashMap<Integer,Integer>();
		List<Integer> a = new ArrayList<Integer>();
		for(int num:numbers){
			if(hm.containsKey(num)) {
				a.add(num);
			} else {
				// put
				hm.put(num,hm.getOrDefault(num,0)+1);
			
			}
//		    int dup = hm.getOrDefault(num,0)+1;
//		    if(dup>1 && !a.contains(num)){
//		        a.add(num);
//		    }
		    
		}
		   Integer[] ans = a.toArray(new Integer[a.size()]); 
		   
		   Arrays.sort(ans);
		   
		   return Arrays.toString(ans);
		}
	
	
	public static void main(String args[]){
		int[] array = {1,3,2,4,1};
		System.out.println(duplicate(array));
	}
}
