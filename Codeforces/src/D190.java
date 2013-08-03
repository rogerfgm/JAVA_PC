


import java.io.*;
import java.util.*;
import java.math.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;



// TLE on 26
public class D190 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	static BufferedWriter bw = null;
	int N = 0;
	
	public void solve() throws Exception{
		int n = sc.nextInt();
		int m = sc.nextInt();

		List<Integer> as = new ArrayList<Integer>();
		List<Integer> ds = new ArrayList<Integer>();
		
		
		
		for(int i = 0; i < n; i++){
			String s = sc.next();
			int st = sc.nextInt();
			
			if(s.equals("DEF")){
				ds.add(st);
			}
			else{
				as.add(st);
			}
		}
		Collections.sort(as);
		Collections.sort(ds);
		
		
		Integer[] cs = new Integer[m];
		for(int i = 0; i < m; i++){
			cs[i] = sc.nextInt();
		}
		Arrays.sort(cs);
		int AL = 0;
		int AU = as.size()-1;
		int DL = 0;
		int DU = ds.size()-1;
		if(AU < 0){
			AL = 1;
			AU = 0;
		}
		if(DU < 0){
			DL = 1;
			DU = 0;
		}
	
		int fst = getnum(AU, AL, DU, DL);
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(fst, 0);
		for(int i = cs.length-1; i >= 0; i--){
			Map<Integer, Integer> nmap = new HashMap<Integer, Integer>();
			int ST = cs[i];
			for(int st : map.keySet()){
				nmap.put(st, map.get(st));
				int sst = st;
				int dl = sst % 100;
				sst/= 100;
				int du = sst % 100;
				sst /= 100;
				int al = sst % 100;
				sst /= 100;
				int au = sst;
				if(dl <= du){
					if(ST >= ds.get(dl)){
						int na = map.get(st);
						int nk = getnum(au, al, du, dl+1);
						if(nmap.containsKey(nk)){
							if(nmap.get(nk).intValue() < na){
								nmap.put(nk, na);
							}
						}
						else{
							nmap.put(nk, na);
						}
					}
				}
				if(dl < du){
					if(ST >= ds.get(du)){
						int na = map.get(st);
						int nk = getnum(au, al, du-1, dl);
						if(nmap.containsKey(nk)){
							if(nmap.get(nk).intValue() < na){
								nmap.put(nk, na);
							}
						}
						else{
							nmap.put(nk, na);
						}
					}
				}
				if(al <= au){
					if(ST >= as.get(al)){
						int add = ST - as.get(al);
						int na = map.get(st) + add;
						int nk = getnum(au, al+1, du, dl);
						if(nmap.containsKey(nk)){
							if(nmap.get(nk).intValue() < na){
								nmap.put(nk, na);
							}
						}
						else{
							nmap.put(nk, na);
						}
					}
				}
				if(al < au){
					if(ST >= as.get(au)){
						int add = ST - as.get(au);
						int na = map.get(st) + add;
						int nk = getnum(au-1, al, du, dl);
						if(nmap.containsKey(nk)){
							if(nmap.get(nk).intValue() < na){
								nmap.put(nk, na);
							}
						}
						else{
							nmap.put(nk, na);
						}
					}
				}
				if(al > au && dl > du){
					int val = map.get(st) + ST;
					if(nmap.containsKey(st)){
						if(nmap.get(st).intValue() < val){
							nmap.put(st, val);
						}
					}
					else{
						nmap.put(st, val);
					}
				}
			}
			map = nmap;
		}
		int ans = 0;
		for(int key : map.keySet()){
			ans = max(ans, map.get(key));
		}
		out.println(ans);
	}
	
	class Data{
		boolean at = true;
		int st = 0;
	}

	int getnum(int au, int al, int du, int dl){
		int ret = au * 1000000 + al * 10000 + du * 100 + dl;
		return ret;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		File file = new File("input.txt");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream("input.txt")));
		}
		out = System.out;
		bw = new BufferedWriter(new PrintWriter(out));
		sc =  new Scanner(System.in);
		//br = new BufferedReader(new InputStreamReader(System.in));
		D190 t = new D190();
		t.solve();
		bw.close();
	}

}
