package srm605;
import java.util.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;

import java.io.*;

public class AlienAndHamburgers {

        final int INF = Integer.MAX_VALUE / 10;
        final double EPS = 1E-8;
        static PrintStream out = System.out;
        final int MOD = 1000000007;
        
        int N = 0;
        long b = 1;
        Map<Integer, Integer> map = null;
        Set<Integer> set = null;
        List<Integer> list = null;
        
        
        public int getNumber(int[] ty, int[] ta){

        	List<D> dlist = new ArrayList<D>();
        	for(int i = 1; i <= 100; i++){
        		List<Integer> ts = new ArrayList<Integer>();
        		for(int j = 0; j < ty.length; j++){
        			if(ty[j] == i){
        				ts.add(ta[j]);
        			}
        		}
        		if(ts.size() == 0){
        			continue;
        		}
        		Collections.sort(ts);
        		if(ts.get(ts.size()-1) < 0){
        			D d = new D();
        			d.sum = ts.get(ts.size()-1);
        			dlist.add(d);
        		}
        		else{
        			D d = new D();
        			for(int j = ts.size()-1; j >= 0; j--){
        				if(ts.get(j) > 0){
        					d.sum += ts.get(j);
        				}
        			}
        			dlist.add(d);
        		}
        	}
        	
        	Collections.sort(dlist, new Comparator<D>() {
				@Override
				public int compare(D o1, D o2) {
					return o2.sum - o1.sum;
				}
			});
        	
        	if(dlist.get(0).sum <= 0){
        		return 0;
        	}
        	
        	int type = 0;
        	int sum = 0;
        	int ans = 0;
        	
        	for(int i = 0; i < dlist.size(); i++){
        		if( (type + 1) * (sum + dlist.get(i).sum ) >= ans){
        			ans = (type + 1) * (sum + dlist.get(i).sum );
        			type++;
        			sum += dlist.get(i).sum;
        		}
        		else{
        			break;
        		}
        	}
        	
        	return ans;
        }
        
        class D{
        	int sum = 0;
        }
        
        /**
         * @param args
         */
        public static void main(String[] args) {
                AlienAndHamburgers t = new AlienAndHamburgers();
                int[] t1 = {81, 58};
                int[] t2 = {-5889, 231};
                int r = t.getNumber(t1, t2);
                out.println(r);
        }

}
