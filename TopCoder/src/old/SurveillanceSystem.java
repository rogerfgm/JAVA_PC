package old;
import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class SurveillanceSystem {

	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	
	int[] r = null;
	int L = 0;

	boolean[] st = null;


	public String getContainerInfo(String c, int[] reports, int L){
		N = c.length();
	
		r = reports;
		this.L = L;

		st = new boolean[N];
		for(int i = 0; i < N; i++){
			if(c.charAt(i) == 'X'){
				st[i] = true;
			}
		}
		Map<Integer, Integer> rm = new HashMap<Integer, Integer>();
		for(int i = 0; i < r.length; i++){
			int cnt = 0;
			if(rm.containsKey(r[i])){
				cnt = rm.get(r[i]);
			}
			cnt++;
			rm.put(r[i], cnt);
		}


		
		String ret = "";
		for(int i = 0; i < N; i++){
			// 必ず使われない
			Map<Integer, Integer> map = new HashMap<Integer, Integer>();
			for(int j = 0; j < i - L + 1; j++){
				int num = 0;
				for(int k = 0; k < L; k++){
					if(st[j+k]){
						num++;
					}
				}
				int cnt = 0;
				if(map.containsKey(num)){
					cnt = map.get(num);
				}
				cnt++;
				map.put(num,  cnt);
			}
			for(int j = i+1; j < N - L + 1; j++){
				int num = 0;
				for(int k = 0; k < L; k++){
					if(st[j+k]){
						num++;
					}
				}
				int cnt = 0;
				if(map.containsKey(num)){
					cnt = map.get(num);
				}
				cnt++;
				map.put(num,  cnt);
			}
			boolean f = true;
			for(int key : rm.keySet()){
				if(!map.containsKey(key)){
					f = false;
				}
				else{
					if(rm.get(key).intValue() > map.get(key).intValue()){
						f = false;
					}
				}
			}
			if(!f){
				ret += "+";
			}
			else{
				map = new HashMap<Integer, Integer>();
				for(int j = max(i - L + 1, 0); j <= min( i, N - L); j++){
					int num = 0;
					for(int k = 0; k < L; k++){
						if(st[j+k]){
							num++;
						}
					}
					int cnt = 0;
					if(map.containsKey(num)){
						cnt = map.get(num);
					}
					cnt++;
					map.put(num,  cnt);
				}
				f = false;
				for(int key : rm.keySet()){
					if(map.containsKey(key)){
						f = true;
					}
				}
				if(f){
					ret += "?";
				}
				else{
					ret += "-";
				}
			}
			
		}

		return ret;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SurveillanceSystem t = new SurveillanceSystem();
		String c = "-XX--X-XX-X-X--X---XX-X---XXXX-----X";
		int[] reports = {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3};
		int L = 7;
		String r = t.getContainerInfo(c, reports, L);
		out.println(r);
	}
}
