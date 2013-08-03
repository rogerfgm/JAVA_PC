package srm568;
import java.util.Iterator;



public class BallsSeparating {


	public int minOperations(int[] red, int[] green, int[] blue){
		
		if(red.length < 3){
			return -1;
		}
		int ans = Integer.MAX_VALUE;
		for(int i = 0; i < red.length; i++){
			for(int j = 0; j < red.length; j++){
				for(int k = 0; k < red.length; k++){
					if(i == j || i == k || j == k){
						continue;
					}
					int sum = 0;
					sum += green[i];
					sum += blue[i];
					sum += red[j];
					sum += blue[j];
					sum += red[k];
					sum += green[k];
					for(int m = 0; m < red.length; m++){
						if(m == i || m == j || m == k){
							continue;
						}
						int or = blue[m] + green[m];
						int ob = red[m] + green[m];
						int og = red[m] + blue[m];
						int min = Math.min(or, ob);
						min = Math.min(min, og);
						sum += min;
					}
					ans = Math.min(ans, sum);
				}
			}
		}
		
		return ans;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BallsSeparating b = new BallsSeparating();
		

	}

}
