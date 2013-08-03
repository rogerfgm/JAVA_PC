import java.math.BigInteger;
import java.util.*;


public class GcdLcm {

	// 最大公約数
	int gcd(int a, int b){
		if(a < b){
			int tmp = a;
			a = b;
			b=tmp;
		}
		if(b == 0) return a;
		return gcd(b, a%b);
	}
	
	// 最小公倍数
	int lcm(int a, int b){
		if(a == 0 || b == 0){
			return 0;
		}
		if(a < b){
			int tmp = a;
			a = b;
			b=tmp;
		}
		return a / gcd(a, b) * b;
	}
	
	// 最大公約数（BigInteger) static変数付き
	final static BigInteger b0 = new BigInteger("0");
    BigInteger gcd(BigInteger a, BigInteger b){
    	
		if(a.compareTo(b) < 0){
			BigInteger tmp = a;
			a = b;
			b=tmp;
		}
		if(b.equals(b0)) return a;
		return gcd(b, a.divideAndRemainder(b)[1]);
	}
	
	// 最小公倍数（BigInteger)
	BigInteger lcm(BigInteger a, BigInteger b){

		if(a.compareTo(b) < 0){
			BigInteger tmp = a;
			a = b;
			b=tmp;
		}
		BigInteger tmp = gcd(a, b);
		return a.divide(tmp).multiply(b);
	
	}
	
	//素数判定
	boolean is_prime(long n){
		for(long i = 2; i*i <= n; i++){
			if(n % i == 0) return false;
		}
		return n != 1; // 1の場合は除外
	}
	
	// 約数の列挙
	List<Integer> divisor(int n){
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 1; i * i <= n; i++){
			if(n % i == 0){
				list.add(i);
				if(i != n / i) list.add(n / i);
			}
		}
		return list;
	}
	
	//素因数分解
	Map<Integer, Integer> prime_factor(int n){
		Map<Integer, Integer> res = new HashMap<Integer, Integer>();
		for(int i = 2; i * i <= n; i++){
			int num = 0;
			while( n % i == 0){
				num++;
				n /= i;
			}
			if(num != 0){
				res.put(i, num);
			}
		}
		if(n != 1){
			if(!res.containsKey(n)){
				res.put(n, 1);
			}
			else{
				int num = res.get(n) + 1;
				res.put(n, num);
			}
		}
		
		return res;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
