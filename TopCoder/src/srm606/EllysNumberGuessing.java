package srm606;
import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class EllysNumberGuessing {

	final int INF = Integer.MAX_VALUE / 10;
	final double EPS = 1E-8;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	
	public int getNumber(int[] gs, int[] as){
		int[] p = new int[2];
		p[0] = p[1] = -1;
		List<Integer> ls = new ArrayList<Integer>();
		int p1 = gs[0] + as[0];
		int p2 = gs[0] - as[0];
		int idx = 0;
		if(p1 >= 1 && p1 <= 1000000000){
			ls.add(p1);
		}
		if(p2 >= 1 && p2 <= 1000000000){
			ls.add(p2);
		}
		for(int i = 1; i < gs.length; i++){
			p1 = gs[i] + as[i];
			p2 = gs[i] - as[i];
			List<Integer> ps = new ArrayList<Integer>();
			
			
			if(p1 >= 1 && p1 <= 1000000000){
				ps.add(p1);
			}
			if(p2 >= 1 && p2 <= 1000000000){
				ps.add(p2);
			}
			boolean flag = false;
			List<Integer> fs = new ArrayList<Integer>();
			for(int j = 0; j < ls.size(); j++){
				for(int k = 0; k < ps.size(); k++){
					if(ls.get(j).intValue() == ps.get(k).intValue()){
						int target = ls.get(j);
						if(!fs.contains(target)){
							fs.add(target);
						}
					}
				}
			}
			if(fs.size() == 0){
				return -2;
			}
			ls = fs;
		}
		if(ls.size() == 0){
			return -2;
		}
		if(ls.size() == 1){
			return ls.get(0);
		}
		return -1;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EllysNumberGuessing t = new EllysNumberGuessing();
		int[] gs = {600, 594};
		int[] as = {6, 12};
		int r = t.getNumber(gs, as);
		out.println(r);
	}

}
