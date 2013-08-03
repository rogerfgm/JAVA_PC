import java.math.BigInteger;


public class Nc {

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
