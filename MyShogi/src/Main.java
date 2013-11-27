
public class Main {

	void run() throws Exception{
		MyThread mythread = new MyThread();
		Thread t1 = new Thread(mythread);
		t1.start();
		
		int cnt = 0;
		while(true){
			String cmd = mythread.getNextCommand();
			if(cmd != null){
				cnt++;
				System.out.println("Main : " + cmd);
			}
			else{
				System.out.println("None");
			}
			if(cnt >= 3) break;
			Thread.sleep(1000);
		}
		
	}
	
	
	public static void main(String[] args) throws Exception{
		Main main = new Main();
		main.run();
		
		
	}

}
