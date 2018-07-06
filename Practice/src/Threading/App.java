package Threading;

class MyThread extends Thread{
	public void run(){
		for(int i=0;i<10;i++){
			System.out.println("Hello "+i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

public class App {
	public static void main(String argsp[]){
		MyThread t1 = new MyThread();
		t1.start();
		MyThread t2 = new MyThread();
		t2.start();
	}

}