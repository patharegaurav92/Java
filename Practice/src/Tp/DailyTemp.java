package Tp;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Stack;

public class DailyTemp {
	public static void main(String args[]){
		int[] temp = {89,62,70,58,47,47,46,76,100,70};
		Instant start = Instant.now();
		int[] out = dailyTemperatures(temp);
		Instant end = Instant.now();
		Duration timeElapsed = Duration.between(start, end);
		System.out.println("Time taken: "+ timeElapsed.toMillis() +" milliseconds");
		
		for(int i:out){
			System.out.print(i+" ");
		}
	}
	public static int[] dailyTemperatures(int[] temperatures) {
        /*HashMap<Integer,Integer> hm = new HashMap<>();
        int n = temperatures.length;
        int[] output = new int[n];
        if(temperatures.length ==1 ) 
            return new int[]{0};
        for(int i=0;i<n;i++){ 
            hm.put(i,temperatures[i]);
        }
        for(int i=0;i<n;i++){
            if(i==n-1) { output[i]=0; break; }
            int days= 0;
            if(temperatures[i+1]>temperatures[i]) {
                days++;
                output[i]=days;
                continue;
            }    
            int l=i;
            while(l+1 <=n-1 && temperatures[l+1]<=temperatures[i]){
                days++;
                l++;
            }
            if(l+1 > n-1){
                    output[i] = 0;
                    continue;
            }
            output[i] = days+1;
        }
        return output;*/
		int[] result = new int[temperatures.length];
		Stack<Integer> stack = new Stack<>(); // Make it a stack of indices.
		for (int i = 0; i < temperatures.length; i++) {
		    while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
		        int index = stack.pop();
		        result[index] = i - index;
		    }
		    stack.push(i);
		}
		return result;
    }
}
