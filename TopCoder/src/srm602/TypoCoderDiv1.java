package srm602;
import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class TypoCoderDiv1 {

        final int INF = Integer.MAX_VALUE / 10;
        final double EPS = 1E-8;
        static PrintStream out = System.out;
        final int MOD = 1000000007;
        
        int N = 0;
        long b = 1;
        Map<Integer, Integer> map = null;
        Set<Integer> set = null;
        List<Integer> list = null;
        
        int[][] dp = null;
        int[] D = null;
        
        public int getmax(int[] D, int X){
        	this.D = D;
        	dp = new int[D.length][2201];
        	for(int i = 0; i < D.length; i++){
        		for(int j = 0; j <= 2200; j++){
        			dp[i][j] = -1;
        		}
        	}
        	
        	
        	return check(0, X);
        }
        
        int check(int idx, int X){
        	if(idx >= D.length){
        		return 0;
        	}
        	if(X <= 2200){
        		if(dp[idx][X] >= 0){
        			return dp[idx][X];
        		}
        	}
        	int ans = 0;
        	if(X >= 2200){
        		X = max(0, X - D[idx]);
        		if(X >= 2200){
        			return -INF;
        		}
        		return 1 + check(idx+1, X);
        	}
        	else{
        		int nx = X + D[idx];
        		if(nx >= 2200){
        			ans = 1 + check(idx+1, nx);
        		}
        		else{
        			ans = check(idx+1, nx);
        		}
        		nx = max(0, X - D[idx]);
        		ans = max(ans, check(idx+1, nx));
        		dp[idx][X] = ans;
        	}
        	return ans;
        }
        
        /**
         * @param args
         */
        public static void main(String[] args) {
                TypoCoderDiv1 t = new TypoCoderDiv1();
                int[] in = {1000000000,1000000000,10000,100000,2202,1};
                int i2 = 1000;
                int r = t.getmax(in, i2);
                out.println(r);
        }

}
