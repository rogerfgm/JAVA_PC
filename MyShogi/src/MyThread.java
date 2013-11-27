import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class MyThread  implements Runnable {

	List<String> commands = new ArrayList<String>();
	public String getNextCommand(){
		if(commands.size() == 0) return null;
		String cmd = commands.remove(0);
		return cmd;
	}
	@Override
	public void run() {
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			while(true){
				String cmd = br.readLine();
				System.out.println("In Thread : " + cmd);;
				commands.add(cmd);
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}

}
