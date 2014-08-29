package srm625;
import java.security.AllPermission;
import java.util.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;

import java.io.*;

public class ConundrumReloaded {

	final int INF = Integer.MAX_VALUE / 10;
	final double EPS = 1E-8;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	
	char H = 'H';
	char L = 'L';
	char U = '?';
	
	public int minimumLiars(String a){
		N = a.length();
		int idx = -1;
		for(int i = 0; i < a.length(); i++){
			if(a.charAt(i) == U){
				idx = i;
				break;
			}
		}
		if(idx >= 0){
			a = a.substring(idx) + a.substring(0, idx); 
		}
		String[] sp = a.split("\\?+");
		int ans = 0;
		for(String s : sp){
			if(s.length() == a.length()){
				ans += get(s, true);
			}
			else{
				if(s.length() > 0){
					ans += get(s, false);
				}
			}
		}
		if(ans >= INF){
			return -1;
		}
		return ans;
	}
	
	int get(String a, boolean all){
		int ans = INF;
		{
			int cnt = 0;
			int idx = a.length()-1;
			boolean next = true;
			while(idx >= 0){
				char c = a.charAt(idx);
				next = true;
				if(c == L){
					next = false;
					cnt++;
				}
				idx--;
				while(idx >= 0 ){
					c = a.charAt(idx);
					if(next){
						if(c == H){
							next = true;
						}
						else{
							next = false;
							if(all && idx == 0){
								
							}
							else{
								cnt++;
							}
						}
					}
					else{
						if(c == H){
							next = false;
							if(all && idx == 0){
								
							}
							else{
								cnt++;
							}
						}
						else{
							next = true;
						}
					}
					idx--;
				}
			}
			if(all && !next ){
				cnt = INF;
			}
			ans = Math.min(ans, cnt);
			
		}
		{
			int cnt = 1;
			int idx = a.length()-1;
			boolean next = false;
			while(idx >= 0){
				char c = a.charAt(idx);
				if(c == L){
					next = true;
				}
				else{
					next = false;
					if(all && idx == 0){
						
					}
					else{
					cnt++;
					}
				}
				idx--;
				while(idx >= 0 ){
					c = a.charAt(idx);
					if(next){
						if(c == H){
							next = true;
						}
						else{
							next = false;
							if(all && idx == 0){
								
							}
							else{
							cnt++;
							}
						}
					}
					else{
						if(c == H){
							next = false;
							if(all && idx == 0){
								
							}
							else{
							cnt++;
							}
						}
						else{
							next = true;
						}
					}
					idx--;
				}
			}
			if(all && next){
				cnt = INF;
			}
			ans = Math.min(ans, cnt);
		}
		return ans;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ConundrumReloaded t = new ConundrumReloaded();
		String a = "LHL";
		int r = t.minimumLiars(a);
		out.println(r);
	}

}
