package Threading;

public class App5 {
	
	public static void main(String args[]){
		App5 a = new App5();
		a.doWork();
	}
	
	private int count;
	
	public synchronized void increment(){
		count++;
	}
	
	private void doWork() {

		Thread t1 = new Thread(new Runnable(){

			public void run() {
				for(int i=0;i<10000;i++){
					increment();
				}
			}
			
		});
		Thread t2= new Thread(new Runnable(){

			public void run() {
				for(int i=0;i<10000;i++){
					increment();
				}
			}
			
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(count);
	}
}
