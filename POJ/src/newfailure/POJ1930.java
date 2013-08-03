package newfailure;



import java.util.*;
import java.io.*;
import static java.lang.Math.*;

// 問題がわかりづらい
// どこからcycleになっているかわからないので、全部ためして最小になるパターンを求める
// 循環小数から分数にするほうほうは検索すればすぐ出てくる
public class POJ1930 {

	static PrintStream out = null;
	int INF = Integer.MAX_VALUE / 10;
	static Scanner sc = null;
	double DF = 0.000000000001;

	static int N = 0;
	static String S = null;
	public void solve() throws Exception{
		String shou = S.substring(2);
		shou = shou.substring(0, shou.length()-3);
		
		long[] r = {Integer.MAX_VALUE, Integer.MAX_VALUE};
		
		for(int i = 0; i < shou.length(); i++){
			long[] ret = get(shou, i);
			if(r[1] > ret[1]){
				r = ret;
			}
			else if(r[1] == ret[1]){
				if(r[0] > ret[0]){
					r = ret;
				}
			}
		}
		
		long b = Long.parseLong(shou);
		if(b % 10 == 0){
			while(b % 10 == 0){
				b /= 10;
			}
			shou = Long.toString(b);
			for(int i = 0; i < shou.length(); i++){
				long[] ret = get(shou, i);
				if(r[1] > ret[1]){
					r = ret;
				}
				else if(r[1] == ret[1]){
					if(r[0] > ret[0]){
						r = ret;
					}
				}
			}
		}
		out.println(r[0] + "/" + r[1]);
	}
	
	
	long[] get(String b, int idx){
		long[] ret = new long[2];
		int not = idx;
		int cycle = b.length() - idx;
		long bo = (long)pow(10, not + cycle) - (long)pow(10, not);
		long si = Long.parseLong(b);
		if(idx != 0){
			si -=  Long.parseLong(b.substring(0, idx));
		}
		for(int i = 2; i <= sqrt(si); i++){
			while(bo % i == 0 && si % i == 0){
				bo /= i;
				si /= i;
			}
		}
		if(bo % si == 0){
			bo /= si;
			si = 1;
		}
		ret[0] = si;
		ret[1] = bo;
		return ret;
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
		POJ1930 p = new POJ1930();
	
		while(true){
			try{
				//N = sc.nextInt();
				S = sc.next();
				if(S.equals("0")){
					break;
				}
			}
			catch(Exception ex){
				break;
			}
			//if(N == 0 && K == 0) break;
			p.solve();
		}
	}

}
