package d172;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;


public class B {
	int INF = Integer.MAX_VALUE / 1000;
	static Scanner sc = null;

	int scale = 30;
	public void solve() throws Exception{

		int X = sc.nextInt();
		int Y = sc.nextInt();
		int n = sc.nextInt();
		

		
		BigDecimal near = new BigDecimal("10000000000");
		int a = -1;
		int b = -1;
		BigDecimal x = BigDecimal.valueOf(X);
		BigDecimal y = BigDecimal.valueOf(Y);
		BigDecimal target = x.divide(y, scale, BigDecimal.ROUND_FLOOR);
		
		BigDecimal zero = new BigDecimal(0);
		for(int i = 1; i <= n; i++){
		
				double bt = (double)i * X / (double)Y ;
			
				BigDecimal b1 = BigDecimal.valueOf((int)Math.floor(bt));
				BigDecimal b2 = BigDecimal.valueOf((int)Math.floor(bt) + 1);
			
				BigDecimal ac = BigDecimal.valueOf(i);
				BigDecimal t1 = b1.divide(ac, scale, BigDecimal.ROUND_FLOOR);
				BigDecimal t2 = b2.divide(ac, scale, BigDecimal.ROUND_FLOOR);
				
				BigDecimal diff = target.subtract(near);
				if(diff.compareTo(zero) < 0){
					diff = near.subtract(target);
				}
				if(!near.equals(t1)){
					BigDecimal diff1 = target.subtract(t1);
					if(diff1.compareTo(zero) < 0){
						diff1 = t1.subtract(target);
					}
					if(diff1.compareTo(diff) < 0){
						near = t1;
						a = (int)Math.floor(bt);
						b = i;
						diff = diff1;
					}
				}
				if(!near.equals(t2)){
					BigDecimal diff1 = target.subtract(t2);
					if(diff1.compareTo(zero) < 0){
						diff1 = t2.subtract(target);
					}
					if(diff1.compareTo(diff) < 0){
						near = t2;
						a = (int)Math.floor(bt) + 1;
						b = i;
					}
				}
		}
		System.out.println(a + "/" + b);
	}
	
	 public int[] readIntArray(int n) {
	        int[] ret = new int[n];
	        for (int i = 0; i < n; i++) {
	            ret[i] = sc.nextInt();
	        }
	        return ret;
	    }
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		File file = new File("input.txt");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream("input.txt")));
		}
		sc =  new Scanner(System.in);
		B t = new B();
		t.solve();

	}
	
	
}
