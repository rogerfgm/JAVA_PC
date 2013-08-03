
public class Nijyou {

	long nijyou(long b, int n){
		if(n == 1){
			return b;
		}
		if(n % 2 == 1){
			return nijyou(b, n-1) * b;
		}
		b = nijyou(b, n/2);
		return b * b;
	}
	
	long nijyou2(long b, int n){
		long ret = 1;
		while(n > 0){
			if( (n & 1) > 0){
				ret *= b;
			}
			b *= b;
			n = n >> 1;
		}
		return ret;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Nijyou n = new Nijyou();
		long t = n.nijyou2(2, 0);
		System.out.println(t);
	}

}
