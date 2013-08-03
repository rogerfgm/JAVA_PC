package d166;

import java.util.*;
import java.io.*;


public class D {
	
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
		
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		Set<String> ans = new HashSet<String>();
		
		map.put("", 0);
		for(int i = 0; i < s.length(); i++){
			String target = s.substring(i, i + 1);
			int idx = s.charAt(i) - 'a';
			Map<String, Integer> nmap = new HashMap<String, Integer>();
			nmap.put("", 0);
			for(String key : map.keySet()){
				if(f[idx]){
					String ns = key + target;
					if(!map.containsKey(ns)){
						nmap.put(ns, map.get(key));
					}
					else{
						nmap.put(ns,  0);
					}
					ans.add(ns);
				}
				else{
					if(map.get(key).intValue() < k){
						String ns = key + target;
						nmap.put(ns, map.get(key) + 1);
						ans.add(ns);
					}
				}	
			}
			map = nmap;
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
		D t = new D();
		t.solve();

	}
	
	
}