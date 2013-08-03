package old;
import java.util.*;




public class MergersDivTwo {

	double ans = -100000000;
	int k = 0;
	public double findMaximum(int[] r, int kk){
		
		k = kk;
		
		
		double[] rr = new double[r.length];
		for(int i =0; i < r.length; i++){
			rr[i] = r[i];
		}
		Arrays.sort(rr);
		check(rr);
		
		
		return ans;
	}
	
	void check(double[] r){
		if(r.length == 1){
			ans = Math.max(ans, r[0]);
			return;
		}
		else if(r.length < k){
			return;
		}
		else if(r.length == k){
			double sum = 0;
			for(int i = 0; i < r.length; i++){
				sum += r[i];
			}
			sum /= k;
			ans = Math.max(ans, sum);
			return;
		}
		
		for(int i = k; i <= r.length; i++){
			
			
			
			double sum = 0;
			int idx = 0;
			double[] nr = new double[r.length - i + 1];
			for(int j = 0; j < i; j++){
				sum+= r[j];
			}
			for(int j = i; j < r.length; j++){
				nr[idx++] = r[j];
			}
			nr[nr.length-1] = sum / i;
			Arrays.sort(nr);
			check(nr);
		}
	}
		
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MergersDivTwo m = new MergersDivTwo();
		int[] in ={869, 857, -938, -290, 79, -901, 32, -907, 256, -167, 510, -965, -826, 808, 890,
				 -233, -881, 255, -709, 506, 334, -184, 726, -406, 204, -912, 325, -445, 440, -368};
		int k = 7;
		double r = m.findMaximum(in, k);
		System.out.println(r);
	}

	
	
}
