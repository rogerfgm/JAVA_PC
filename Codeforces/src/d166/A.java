package d166;
import java.io.*;
import java.util.*;





public class A {
	
	int INF = Integer.MAX_VALUE / 1000;
	static Scanner sc = null;
	public void solve() throws Exception{

	
		int n = sc.nextInt();
		n++;
		while(true){
			String s = Integer.toString(n);
			Set<String> set = new HashSet<String>();
			set.add(s.substring(0, 1));
			set.add(s.substring(1, 2));
			set.add(s.substring(2, 3));
			set.add(s.substring(3, 4));
			if(set.size() == 4){
				System.out.println(s);
				return;
			}
			n++;
		}
		
		
	}
	
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		File file = new File("input.txt");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream("input.txt")));
		}
		sc =  new Scanner(System.in);
		A t = new A();
		t.solve();
	}
}
