package d166;
import java.util.*;
import java.io.*;


public class Dold {
	
	int INF = Integer.MAX_VALUE / 1000;
	static Scanner sc = null;
	
	public void solve() throws Exception{

		String s = sc.next();
		String ck = sc.next();
		int k = sc.nextInt();
		boolean[] f = new boolean[26];
		for(int i = 0; i < 26; i++){
			if(ck.charAt(i) == '1'){
				f[i] = true;
			}
		}
		
		List<Set<String>> list = new ArrayList<Set<String>>();
		for(int i = 0; i <= k; i++){
			Set<String> set = new HashSet<String>();
			list.add(set);
		}
		list.get(0).add("");

		Set<String> ans = new HashSet<String>();
		
		for(int i = 0; i < s.length(); i++){
			String target = s.substring(i, i + 1);
			int idx = s.charAt(i) - 'a';
			List<Set<String>> nlist = new ArrayList<Set<String>>();
			if(f[idx]){
				
				for(int j = 0; j <= k; j++){
					Set<String> nset = new HashSet<String>();
					for(String prev : list.get(j)){
						String ns = prev + target;
						nset.add(ns);
						ans.add(ns);
					}
					nlist.add(nset);
				}
			}
			else{
		
				Set<String> nset = new HashSet<String>();
				nset.add("");
				nlist.add(nset);
				for(int j = 1; j <= k; j++){
					
					nset = new HashSet<String>();
					for(String prev : list.get(j-1)){
						String ns = prev + target;
						nset.add(ns);
						ans.add(ns);
					}
					nlist.add(nset);
				}
			}
			list = nlist;
		}
		
		System.out.println(ans.size());
		
		
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
		Dold t = new Dold();
		t.solve();

	}
	
	
}