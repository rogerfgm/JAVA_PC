package newfailure;


import java.util.*;
import java.io.*;
import static java.lang.Math.*;

//web上で解法見てしまった
public class POJ3292 {

	static PrintStream out = null;
	int INF = Integer.MAX_VALUE / 10;
	static Scanner sc = null;
	double DF = 0.000000000001;

	static int N = 0;
	public void solve() throws Exception{
		
//		List<Long> list = new ArrayList<Long>();
//		for(int i = 1; ; i++){
//			long next = i * 4 + 1;
//			if(next > N){
//				break;
//			}
//			list.add(next);
//		}
//		
//	
//		int sin = 0;
//		int lin = 0;
//		int num = 0;
//		Set<Long> set = new HashSet<Long>();
//		
//		for(int i = 0; i < list.size(); i++){
//			for(int j = i; j < list.size(); j++){
//				long inum = list.get(i);
//				long jnum = list.get(j);
//				long mul = inum * jnum;
//				if(mul > N){
//					break;
//				}
//				set.add(mul);
//				
//				if(!set.contains(inum) && !set.contains(jnum)){
//					num++;
//				}
//			}
//		}
//		
//		
//		out.println(N + " " + num);
	}
	
	

	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		out = System.out;
		File file = new File("input.txt");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream("input.txt")));
		}
		sc =  new Scanner(System.in);
		POJ3292 p = new POJ3292();

		while(true){
			try{
				N = sc.nextInt();
				if(N == 0){
					break;
				}
			}
			catch(Exception ex){
				break;
			}
			p.solve();
		}
	}

}
