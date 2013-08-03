package srm578;
import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;


public class WolfInZooDivOne {

	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int[] dl = null;
	int[] dr = null;
	int N = 0;

	
	List<Map<Long, Integer>> list = null;
	
	List<List<Data>> blist = null;
	public int count(int N, String[] L, String[] R) {
		this.N = N;
		int ret = 0;
		
		blist = new ArrayList<List<Data>>();
		for(int i = 0; i< N; i++){
			List<Data> lst = new ArrayList<Data>();
			blist.add(lst);
		}
		
		String l = "";
		String r = "";
		for(String s : L){
			l += s;
		}
		for(String s : R){
			r += s;
		}
		list = new ArrayList<Map<Long, Integer>>();
		for(int i = 0; i < N; i++){
			Map<Long, Integer> map = new HashMap<Long, Integer>();
			list.add(map);
		}
		
		
		String[] spl = l.split(" ");
		String[] spr = r.split(" ");
		dl = new int[spl.length];
		dr = new int[spr.length];
		for(int i = 0 ;i < spl.length; i++){
			dl[i] = parseInt(spl[i]);
			dr[i] = parseInt(spr[i]);
			Data d = new Data();
			d.idx = i+1;
			d.cnt = 0;
			d.r = dr[i];
			if(dl[i] != dr[i]) blist.get(dl[i]).add(d);
			
		}
				
		ret = check(0, 0);
		return ret;
	}
	
	long getidx(int r0, int r1, int r2){
		return r0 + r1 * 1000 + r2 * (long)1000000;
	}
	
	int getr0(long p){
		return (int)(p % 1000);
	}
	int getr1(long p){
		p /= 1000;
		return (int)(p % 1000);
	}
	int getr2(long p){
		p /= 1000000;
		return (int)(p % 1000);
	}
	
	
	
	int check(int idx, long p){
		long ret = 0;
		if(idx == N) return 1;
	
		if(list.get(idx).containsKey(p)){
			return list.get(idx).get(p);
		}
		
		int r0 = getr0(p);
		int r1 = getr1(p);
		int r2 = getr2(p);
	
		for(Data d : blist.get(idx)){
			r0 = max(r0, d.r);
		}
		long np = getidx(r0, r1, r2);
		ret = check(idx+1, np);
		
		if(idx == 0 || r2 < idx){
			r2 = r1;
			r1 = r0;
			np = getidx(r0, r1, r2);
			ret += check(idx+1, np);
			ret %= MOD;
		}
		
		list.get(idx).put(p, (int)ret);
		

		return (int)ret;
	}
	
	class Data{
		int idx = 0;
		int cnt = 0;
		int r = 0;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		WolfInZooDivOne t = new WolfInZooDivOne();
//		int N = 5;
//		String[] L ={"0 1"};
//		String[] R ={"2 4"};
//		int N = 10;
//		String[] L = {"0 4 2 7"};
//		String[] R = {"3 9 5 9"};

		out.println(new Date());
		int N = 100;
		String[] L = {"0 2 2 7 10 1","3 16 22 30 33 38"," 42 44 49 51 57 60 62"," 65 69 72 74 77 7","8 81 84 88 91 93 96"};
		String[] R = {"41 5 13 22 12 13 ","33 41 80 47 40 ","4","8 96 57 66 ","80 60 71 79"," 70 77 ","99"," 83 85 93 88 89 97 97 98"};
		int ret = t.count(N, L, R);
		out.println(ret);
		out.println(new Date());
	}

}
