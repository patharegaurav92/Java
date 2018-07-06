package Threading;

import java.util.Random;

class Account{
	private int balance;
	public String deposit(int money){
		balance+=money;
		return "Deposited $"+money;
	}
	public String withdraw(int money){
		if(balance>=money){
			balance-=money;
			return "Withdrawn $"+money;
		}else{
			return "Insufficient Balance to withdraw $"+money;
		}
		
	}
	public int getBalance(){
		return balance;
	}
}

class Transactions{
	Account account;
	
	public Transactions(Account account){
		this.account = account;
	}
	public void deposit(int money){
		System.out.println(account.deposit(money));
	}
	public void withdraw(int money){
		System.out.println(account.withdraw(money));
	}
	public void getBalance(){
		System.out.println("Balance is "+account.getBalance());
	}
	
}

public class BankApplication {
	public static void main(String args[]){
		Random r = new Random();
		Transactions t = new Transactions(new Account());
		int i=0;
		while(i<10){
			int n = r.nextInt(4);
			int money = r.nextInt(1000);
			if(n==1){
				t.deposit(money);
			}else if(n==2){
				t.withdraw(money);
			}else if(n==3){
				t.getBalance(); 
			}
			i++;
		}
		
		
	}
}
