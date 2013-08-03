package srm566;
import java.util.Arrays;




public class PenguinEmperor {
	int MOD = 1000000007;
	
	int n = 0;
	long day = 0;
	public int countJourneys(int numCities, long daysPassed){
		n = numCities;
		day = daysPassed;
		long[] c = new long[n]; // cycle
		long[] a = new long[n]; // amari
		c[0] = 1;
		a[0] = 1;
		
		int amari = (int)(day % n);
		for(int i = 1; i <= n; i++){
			long[] d = new long[n];
			
			for(int j = 0; j < n; j++){
				int cw = (j + i) % n;
				int ccw = (j - i + n) % n;
				if(cw != ccw){
					d[cw] += c[j];
					d[ccw] += c[j];
				}
				else{
					d[cw] += c[j];
				}
				d[cw] %= MOD;
				d[ccw] %= MOD;
			}
			
			c = d;
			if(i == amari){
				a = new long[c.length];
				for(int j = 0; j < c.length; j++){
					a[j] = c[j];
				}
			}
		}
		if(day < n){
			return (int)a[0];
		}
		else{
			long ni = day / n;
			long[] ret = nijyou2(c, ni);
			ret = mul(ret, a);
			return (int)ret[0];
		}
		
		
		
	}
	
	long[] nijyou2(long[] d, long ni){
		long[] ret = new long[n];
		ret[0] = 1;
		while(ni > 0){
			if( (ni & 1) > 0){
				ret = mul(ret, d);
			}
			d = mul(d, d);
	
			ni = ni >> 1;
		}
		return ret;
	}
	
	long[] mul(long[] b, long[] d){
		long[] rl = new long[n];
		
		for(int i = 0; i < b.length; i++){
			long m = b[i];
			for(int j = 0; j < n; j++){
				int idx = (i + j) % n;
				rl[idx] += m * d[j];
				rl[idx] %= MOD;
			}
		}
		
		return rl;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PenguinEmperor p = new PenguinEmperor();
		long l = Long.parseLong("1000000000000000000");
		int ret = p.countJourneys(5, 36);
		
		System.out.println(ret);
	}

}
