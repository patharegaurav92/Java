package Tp;

import java.util.LinkedHashMap;

public class findSumOfPairs {

	public boolean hasSumOfPairs(int[] items,int sum){
		int i,j,comp;
		LinkedHashMap<Integer,Integer> hash=new LinkedHashMap<Integer,Integer>();
		for(int item:items){
			comp=sum-item;
			if(hash.containsValue(comp)) return true;
			hash.put(item, item);
		}
		return false;
	}
	
	public static void main(String args[]){
		int[] a = new int[]{12,4,2};
		int sum=8;
		findSumOfPairs fsop=new findSumOfPairs();
		if(fsop.hasSumOfPairs(a,sum)){
			System.out.println("It has");
		}else{
			System.out.println("Nope");
		}
	}

}
