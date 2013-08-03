package old;
import java.util.*;


public class DivisibleSequence {

  int MOD = 1000000009;

	Map<String, Integer> map = new HashMap<String, Integer>();


	int ret = 0;
	public int count(int N, int H) {
		
		check(N, H -1);

		return ret;
	}

	void check(int N, int H){
		if(H == 0){
			ret++;
			ret = ret % MOD;
			return;
		}
		if(N == 1){
			ret++;
			return;
		}
		ret += H + 1;

		List<Integer> div = divisor(N);
		for(int i : div){
			check(i, H-1);
		}

	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DivisibleSequence d = new DivisibleSequence();
		int ret = d.count(6, 3);
		System.out.println(ret);
	}

	// –ñ”‚Ì—ñ‹“
	List<Integer> divisor(int n){
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 2; i * i <= n; i++){
			if(n % i == 0){
				list.add(i);
				if(i != n / i) list.add(n / i);
			}
		}
		return list;
	}

	List<Integer> prime(int n){
		List<Integer> res = new ArrayList<Integer>();
		for(int i = 2; i * i <= n; i++){
			while( n % i == 0){
				res.add(i);
				n /= i;
			}
		}
		if(n != 1){
			res.add(n);
		}	
		return res;
	}
}
