package Threading;

import java.util.Scanner;

class MyThread2 extends Thread{
	private volatile boolean running = true;
	@Override
	public void run() {
		while(running){
			System.out.println("Hello ");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void shutDown(){
		running =false;
	}
	
}

public class App4 {
	public static void main(String args[]){
	MyThread2 thread1 =new MyThread2();
	thread1.start();
	
	Scanner sc= new Scanner(System.in);
	sc.nextLine();
	thread1.shutDown();
	}
}
