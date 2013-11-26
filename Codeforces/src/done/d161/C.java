package done.d161;
import java.io.*;
import java.util.*;







public class C {
	static Scanner sc = null;
	
	
	
	Map<Integer, Data> map = null;
	int n = 0;
	public void solve() throws Exception{

		n = sc.nextInt();
	
		map = new HashMap<Integer, Data>();
	
		
		for(int i = 0; i < 2 * n; i++){
			int d1 = sc.nextInt();
			int d2 = sc.nextInt();

			if(map.containsKey(d1)){
				map.get(d1).l.add(d2);
				map.get(d1).s.add(d2);
			}
			else{
				Data d = new Data();
				d.l.add(d2);
				d.s.add(d2);
				map.put(d1, d);
			}
			if(map.containsKey(d2)){
				map.get(d2).l.add(d1);
				map.get(d2).s.add(d1);
			}
			else{
				Data d = new Data();
				d.l.add(d1);
				d.s.add(d1);
				map.put(d2, d);
			}
		}
		
		for(int key : map.keySet()){
			Data d = map.get(key);
			if(d.l.size() != 4){
				System.out.println("-1");
				return;
			}
		}
		
		List<Integer> l1 = map.get(1).l;
		int[] ans = new int[n];
		
		Set<String> checked = new HashSet<String>();
		
		for(int i = 0; i < l1.size()-1; i++){ // 4
			for(int j = i+1; j < l1.size(); j++){ // 4
				
				Data d1 = map.get(l1.get(i));
				if(d1 != null && d1.s.contains(1) && d1.s.contains(l1.get(j))){
				
				
					for(int d1n : d1.l){
						if(d1n != 1 && d1n != l1.get(j) && d1n != l1.get(i)){
							ans = new int[n];
							ans[0] = 1;
							ans[1] = l1.get(i);
							ans[2] = l1.get(j);
							ans[3] = d1n;
//							String check = ans[1] + ":" + ans[2] + ":" + ans[3];
//							if(checked.contains(check)){
//								continue;
//							}
							if(check(ans)){
								return;
							}
//							checked.add(check);
						}
					}
				}
				d1 = map.get(l1.get(j));
				if(d1 != null && d1.s.contains(1) && d1.s.contains(l1.get(i))){
					for(int d1n : d1.l){
						if(d1n != 1 && d1n != l1.get(j) && d1n != l1.get(i)){
							ans = new int[n];
							ans[0] = 1;
							ans[1] = l1.get(j);
							ans[2] = l1.get(i);
							ans[3] = d1n;
//							String check = ans[1] + ":" + ans[2] + ":" + ans[3];
//							if(checked.contains(check)){
//								continue;
//							}
							if(check(ans)){
								return;
							}
//							checked.add(check);
						}
					}
				}
			}
		}
		
		System.out.println("-1");
		
	
	}
	
	boolean check(int[] ans){

		for(int i = 2; i < n-2; i++){
			List<Integer> l = map.get(ans[i]).l;
			Set<Integer> s = map.get(ans[i]).s;
			if(l == null){
				return false;
			}
			if(! s.contains(ans[i-2]) || !s.contains(ans[i-1]) || !s.contains(ans[i+1])){
				return false;
			}
	
			boolean found = false;
			for(int n4 : l){
				if(n4 == ans[i-2] || n4 == ans[i-1] || n4 == ans[i+1]){
					continue;
				}
				else{
					found = true;
					ans[i+2] = n4;
					break;
				}
			}
			if(!found){
				return false;
			}
		}
		
		Set<Integer> l = map.get(ans[n-2]).s;
		
		if(l == null || ! l.contains(ans[n-4]) || !l.contains(ans[n-3]) || !l.contains(ans[n-1]) || !l.contains(ans[0])){
			return false;
		}
		l = map.get(ans[n-1]).s;
		if(l == null || ! l.contains(ans[n-3]) || !l.contains(ans[n-2]) || !l.contains(ans[0]) || !l.contains(ans[1])){
			return false;
		}
		
		l = map.get(ans[0]).s;
		if(l == null || ! l.contains(ans[1]) || !l.contains(ans[2]) || !l.contains(ans[n-1]) || !l.contains(ans[n-2])){
			return false;
		}
		
		l = map.get(ans[1]).s;
		if(l == null || ! l.contains(ans[2]) || !l.contains(ans[3]) || !l.contains(ans[n-1]) || !l.contains(ans[0])){
			return false;
		}
		
		
		for(int i = 0; i < n; i++){
			System.out.print(ans[i]);
			if(i != n-1){
				System.out.print(" ");
			}
		}
		
		
		return true;
	}
	
	class Data {
		List<Integer> l = new ArrayList<Integer>();
		Set<Integer> s = new HashSet<Integer>();
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
		C t = new C();
		t.solve();

	}
	
	
}
