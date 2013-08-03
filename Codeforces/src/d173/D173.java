package d173;

import java.io.*;
import java.util.*;
import java.math.*;
	




public class D173 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static PrintWriter out = null;
	int n = 0;
	long b1 = 1000;
	long b2 = 1000000;
	Map<Long, Boolean> map = new HashMap<Long, Boolean>();
	int cnt = 0;
	public void solve() throws Exception{
		n = sc.nextInt();
		int[] d = new int[n];
		for(int i = 0; i < n; i++){
			d[i] = sc.nextInt();
		}
		Arrays.sort(d);
		map.put((long)0, false);
		
		if(n == 1){
			if(d[0] != 0){
				System.out.println("BitLGM");
			}
			else{
				System.out.println("BitAryo");
			}	
		}
		else if(n == 2){
			if(check2(d[0], d[1])){
				System.out.println("BitLGM");
			}
			else{
				System.out.println("BitAryo");
			}
		}
		else{
			if(check(d)){
				System.out.println("BitLGM");
			}
			else{
				System.out.println("BitAryo");
			}
		}
	}
	
	boolean check2(int d1, int d2){
		long ck = d1 + d2 * b1;
		if(map.containsKey(ck)){
			return map.get(ck);
		}
		if(d1 == 0){
			return true;
		}
		if(d1 == d2){
			return true;
		}
		
		// check d2
		for(int i = d2 -1; i >= 1; i--){
			int nd1 = Math.min(d1, d2 -i);
			int nd2 = Math.max(d1, d2 -i);
			if(!check2(nd1, nd2)){
				map.put(ck, true);
				return true;
			}
		}
		// check d1
		for(int i = d1 -1; i >= 1; i--){
			int nd1 = d1 -i;
			int nd2 = d2;
			if(!check2(nd1, nd2)){
				map.put(ck, true);
				return true;
			}
		}
		
		
		// check all
		for(int i = d1 -1; i >= 1; i--){
			int nd1 = d1 - i;
			int nd2 = d2 - i;
			if(!check2(nd1, nd2)){
				map.put(ck, true);
				return true;
			}
		}
		map.put(ck, false);
		return false;
	}
	
	boolean check(int[] c){
		int ck = c[0] ^ c[1] ^ c[2];
		if(ck > 0) return true;
		return false;
		/*
		long ck = 0;

		for(int i = 0; i < n; i++){
			ck += Math.pow(b1, i) * c[i];
		}
		
		if(map.containsKey(ck)){
			return map.get(ck);
		}
		boolean f = false;
		if(c[0] == 0){
			int k = c[1] ^ c[2];
			if(k > 0){
				map.put(ck, true);
				return true;
			}
		}
		else{
			for(int i = 0; i < n; i++){
				int[] nc = new int[n];
				for(int j = 0; j < n; j++){
					if(i == j){
						nc[j] = 0;
					}
					else{
						nc[j] = c[i];
					}
				}
				Arrays.sort(nc);
				if(!check(nc)){
					map.put(ck, true);
					return true;
				}
			}
			int [] nc = new int[n];
			nc[0] = 0;
			for(int i = 1; i < n; i++){
				nc[i] = c[i] - c[0];
			}
			if(!check(nc)){
				map.put(ck, true);
				return true;
			}
			for(int i = 1; i < c[0]; i++){
				nc = new int[n];
				for(int j = 0; j < n; j++){
					nc[j] = c[j] - i;
				}
				if(!check(nc)){
					map.put(ck, true);
					return true;
				}
			}
			for(int i = 1; i < c[0]; i++){
				nc = Arrays.copyOf(c, n);
				nc[0] -= i;
				if(!check(nc)){
					map.put(ck, true);
					return true;
				}
			}
			for(int i = 1; i < c[1]; i++){
				nc = Arrays.copyOf(c, n);
				nc[1] -= i;
				Arrays.sort(nc);
				if(!check(nc)){
					map.put(ck, true);
					return true;
				}
			}
			
			for(int i = 1; i < c[2]; i++){
				nc = Arrays.copyOf(c, n);
				nc[2] -= i;
				Arrays.sort(nc);
				if(!check(nc)){
					map.put(ck, true);
					return true;
				}
			}
			
		}

		
		map.put(ck, f);
		return f;
		*/
	}

    public int[] readIntArray(int n) {
        int[] ret = new int[n];
        for (int i = 0; i < n; i++) {
            ret[i] = sc.nextInt();
        }
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
		out = new PrintWriter(System.out);
		sc =  new Scanner(System.in);
		D173 t = new D173();
		t.solve();

	}

}