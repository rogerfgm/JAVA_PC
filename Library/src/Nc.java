import java.math.BigInteger;


public class Nc {
	
	// nCr mod p
	public long C(int n, int r, int p)
	{
		long ret = 1;
		while(true){
			if(r == 0)break;
			int N = n % p;
			int R = r % p;
			if(N < R)return 0;
	 
			for(int i = 0;i < R;i++){
				ret = ret * (N-i) % p;
			}
			long imul = 1;
			for(int i = 0;i < R;i++){
				imul = imul * (i+1) % p;
			}
			ret = ret * mod_inverse(imul, p) % p;
			n /= p; r /= p;
		}
		return ret;
	}
	
	long mod_inverse(long a, long b){
		BigInteger x = BigInteger.valueOf(a);
		BigInteger y = BigInteger.valueOf(b);
		return x.modInverse(y).longValue();
	}

	static BigInteger ncb(final int n, int c) {
		if(n < c){
			return null;
		}
		if(n-c < c){
			c = n-c;
		}
	    BigInteger ret = BigInteger.ONE;
	    for (int k = 0; k < c; k++) {
	        ret = ret.multiply(BigInteger.valueOf(n-k))
	                 .divide(BigInteger.valueOf(k+1));
	    }
	    return ret;
	}
	
	int nci(int n, int c){
		if(n < c){
			return 0;
		}
		if(n-c < c){
			c = n-c;
		}
		
		long ret = 1;
	    for (int k = 0; k < c; k++) {
	        ret = ret * (n-k) / (k+1);
	    }
		
		return (int)ret;
	}
	
	long ncl(int n, int c){
		if(n < c){
			return 0;
		}
		if(n-c < c){
			c = n-c;
		}
		
		long ret = 1;
	    for (int k = 0; k < c; k++) {
	        ret = ret * (n-k) / (k+1);
	    }
		
		return ret;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
