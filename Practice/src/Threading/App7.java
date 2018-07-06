package Threading;

import java.util.LinkedList;
import java.util.Random;

public class App7 {

	private LinkedList<Integer> list = new LinkedList<>();
	private final int LIMIT=10;
	public Object lock = new Object();
	
	public void produce() throws InterruptedException{
		int value = 0;
		while(true){
			
			synchronized(lock){
				if(list.size()==LIMIT)
					lock.wait();
				list.add(value++);
				lock.notify();
			}
		}
		
	}
	public void consume() throws InterruptedException{
		Random rand = new Random();
		while(true){
			synchronized(lock){
				if(list.size()==0)
					lock.wait();
				System.out.print("List size is "+list.size());
				int val = list.removeFirst();
				System.out.println("; value is "+val);
				lock.notify();
			}
			Thread.sleep(rand.nextInt(1000));
		}
	}
	
	public static void main(String args[]){
		App7 app = new App7();
		Thread t1 = new Thread(new Runnable(){

			@Override
			public void run() {
				try {
					app.produce();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		Thread t2 = new Thread(new Runnable(){

			@Override
			public void run() {
				try {
					app.consume();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
