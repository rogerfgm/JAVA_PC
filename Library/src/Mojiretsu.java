
public class Mojiretsu {

	final long B = 100000007;
	long h = (long)Math.pow(2, 60);
	
	/**
	 * aがbに含まれるか
	 * @param a
	 * @param b
	 * @return
	 */
	boolean contain(String a, String b){
		int al = a.length();
		int bl = b.length();
		
		long t = 1;
		for(int i = 0; i < al; i++){
			t *= B;
			t %= h;
		}
		
		long ah = 0;
		long bh = 0;
		for(int i = 0; i < al; i++){
			int c = a.charAt(i);
			ah = B * ah + c;
			ah %= h;
		}
		for(int i = 0; i < al; i++){
			int c = b.charAt(i);
			bh = B * bh + c;
			bh %= h;
		}
		
		for(int i = al; i <= bl; i++){
			if(ah == bh){
				return true;
			}
			if(i < bl){
				bh = bh * B + b.charAt(i) - t * b.charAt(i-al);
				bh %= h;
			}
		}
		
		return false;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
