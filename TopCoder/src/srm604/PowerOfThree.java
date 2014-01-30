package srm604;
import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class PowerOfThree {

        final int INF = Integer.MAX_VALUE / 10;
        final double EPS = 1E-8;
        static PrintStream out = System.out;
        final int MOD = 1000000007;
        
        int N = 0;
        long b = 1;
        Map<Integer, Integer> map = null;
        Set<Integer> set = null;
        List<Integer> list = null;
        String P = "Possible";
        String I = "Impossible";
        
        
        public String ableToGet(int x, int y){
        	x = abs(x);
        	y = abs(y);
        	if(x == 0 && y == 0){
        		return P;
        	}
        	for(int i = 0; i <= 20; i++){
        		if(check(i, max(x, y), min(x, y))){
        			return P;
        		}
        	}
        	
        	return I;
        }
        
        boolean check(int p, int x, int y){
        	List<Integer> ps = new ArrayList<Integer>();
        	for(int i = 0;i <= p; i++){
        		ps.add((int)pow(3, i));
        	}
        	for(int i = ps.size()-1; i >= 0; i--){
        		if(x >= 0 && y >= 0){
        			x -= ps.get(i);
        		}
        		else if(x < 0 && y < 0){
        			y += ps.get(i);
        		}
        		else{
        			if(abs(x) > abs(y)){
        				x -= ps.get(i);
        			}
        			else{
        				y += ps.get(i);
        			}
        		}
        		
        		int X = max(x, y);
        		int Y = min(x, y);
        		x = X;
        		y = Y;
        	}
        	if( x == 0 && y == 0){
        		return true;
        	}
        	
        	return false;
        }
        
        /**
         * @param args
         */
        public static void main(String[] args) {
                PowerOfThree t = new PowerOfThree();
                String r = t.ableToGet(1000000000, 18252);
                out.println(r);
        }

}
