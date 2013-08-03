package srm566;


public class PenguinSledding {
	boolean[][] d = null;
	public long countDesigns(int n, int[] c1, int[] c2){
		long ans = 1 + c1.length;
		d = new boolean[n+1][n+1];
		for(int i = 0; i < c1.length; i++){
			int f = c1[i];
			int t = c2[i];
			d[f][t] = true;
			d[t][f] = true;
		}
		int[] numi = new int[n+1];
		for(int i = 1; i <= n; i++){
			int num = 0;
			for(int j = 1; j <= n; j++){
				if(d[i][j]){
					num++;
				}
			}
			numi[i] = num;
			
			
			//ans += Math.pow(2, num) - num -1;
			
			
			if(num >= 2){
				for(int j = 2; j <= num; j++){
					ans += ncl(num, j);
					System.out.println(num + " " + j + " " + ncl(num, j));
				}
			}
			
		}
		
		for(int i = 1; i<=n-2; i++){
			for(int j = i+1; j <= n-1; j++){
				for(int k = j+1; k <= n; k++){
					if(d[i][j] && d[j][k] && d[i][k]){
						ans++;
					}
				}
			}
		}

		
		
		return ans;
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
		PenguinSledding p = new PenguinSledding();
	
		int[] i1 = {40, 40, 40, 40, 40, 40, 40, 40, 
				 40, 40, 40, 40, 50, 40, 40, 40, 
				 23, 4, 24, 40, 22, 40, 8, 40, 40,
				 40, 34, 21, 40, 40, 38, 40, 40, 
				 40, 40, 40, 28, 40, 40, 40, 40, 
				 46, 13, 40, 40, 40, 47, 40, 40};
		int[] i2 = {45, 42, 20, 48, 12, 32, 25, 10, 
				 26, 39, 16, 2, 19, 43, 37, 17, 
				 19, 19, 19, 18, 19, 27, 19, 29, 
				 11, 36, 19, 19, 1, 41, 19, 35, 
				 14, 33, 49, 3, 19, 7, 5, 19, 31, 
				 19, 19, 6, 9, 15, 19, 44, 30};
		
		
		/*
		int[] i1 = {13, 24, 24, 20, 31, 16, 10, 36, 34, 32, 
				 28, 5, 20, 29, 23, 2, 14, 4, 9, 5, 19, 
				 21, 8, 1, 26, 27, 25, 15, 22, 30, 30, 
				 6, 11, 7, 2, 35, 13, 29, 4, 12, 33, 18, 
				 13, 14, 17, 35, 3};
		int[] i2 = {10, 15, 27, 1, 29, 11, 5, 18, 33, 1, 9,
				 2, 31, 6, 19, 10, 33, 18, 6, 27, 3, 22,
				 4, 12, 31, 30, 34, 16, 7, 24, 3, 28, 15,
				 21, 22, 8, 26, 20, 14, 32, 25, 17, 35,
				 8, 36, 26, 23};
		*/
		long r = p.countDesigns(50,  i1, i2);
		System.out.println(r);
	}

}
