package old;
import java.util.Arrays;


public class TheFrog {

	double diff = 0.0000000001;
	int D = 0;
	int[] L = null;
	int[] R = null;
	
	double DF = 0.05;
	
	public double getMinimum(int D, int[] L, int[] R){
		this.D = D;
		Arrays.sort(R);
		Arrays.sort(L);
		this.L = L;
		this.R = R;
		double ans = D;
		double right = D;
		for(int i = R.length-1; i >= 0; i--){
			double left = R[i];
			if(check(left)){
				ans = left;
			}
			else{
				ans = Math.min(ans,calc2(left, right));
			}
			right = L[i];
		}
		ans = Math.min(ans, calc2(0, right));
		
		return ans;
	}
	
	double calc2(double left, double right){

		double prevleft = left;
		left = left + DF;
		double ans = D;
		while(right > left){
			if(check(left)){
				ans = Math.min(ans, calc(prevleft, left));
				return ans;
			}
			prevleft = left;
			left += DF;
		}
		return ans;
	}
	
	boolean check(double st){
		if(st == 0){
			return false;
		}
		double pos = st;
		while(pos < D){
			
			for(int i = 0; i < L.length; i++){
				if(pos > L[i] && pos < R[i]){
					return false;
				}
			}
			pos += st;
		}
		
		return true;
	}
	
	public double calc(double min, double max){
		while(min + diff < max){
			double h = (min + max) / 2;
			if(check(h)){
				max = h;
			}
			else{
				min = h;
			}
		}
		
		return max;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		TheFrog t = new TheFrog();
		int D = 30000;
		int[] L = {17, 25281, 5775, 2825, 14040};
		int[] R = {55, 26000, 5791, 3150, 14092};
		
		double ret = t.getMinimum(D, L, R);
		System.out.println(ret);
	}

}
