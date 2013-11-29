package srm598;
import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class BinPacking {

	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	
	public int minBins(int[] it){
		
		int ans = 0;
		Arrays.sort(it);
		int idx = 0;
		List<Integer> list = new ArrayList<Integer>();
		for(int i : it){
			list.add(i);
		}
		while(true){
			boolean flag = false;
			
			for(int i = list.size()-1; i >= 0; i--){
				if(list.get(i).intValue() <= 150) break;
				for(int j = i-1; j >= 0; j--){
					if(list.get(i).intValue() + list.get(j).intValue() <= 300){
						list.remove(i);
						list.remove(j);
						flag = true;
						ans++;
						break;
					}
				}
				if(flag) break;
			}
			if(!flag){
				break;
			}
		}
		
		
		while(true){
			if(list.size() > 2 && list.get(0).intValue() == 100 && list.get(1).intValue() == 100 && list.get(2).intValue() == 100){
				list.remove(0);
				list.remove(0);
				list.remove(0);
				ans++;
			}
			else{
				break;
			}
		}
		while(list.size() > 0){
			if(list.size() == 1){
				list.remove(0);
				ans++;
			}
			else{
				if(list.get(0) + list.get(1) <= 300){
					ans++;
				}
				else{
					ans += 2;
				}
				list.remove(0);
				list.remove(0);
			}
		}
		
		
		return ans;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BinPacking t = new BinPacking();
		int[] it = {100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 182, 181, 248, 155, 244, 195, 220, 130, 143, 191, 131, 253, 125};
		int r = t.minBins(it);
		out.println(r);
	}

}
