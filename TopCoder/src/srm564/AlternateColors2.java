package srm564;

public class AlternateColors2 {

	public long countWays(int n, int k){
	
		long[] all = new long[3];
		long[] rb = new long[3];
		long[] rg = new long[3];
		long[] bg = new long[3];
		long[] r = new long[3];;
		long[] b = new long[3];
		long[] g = new long[3];
		
		all[0] = rb[0] = rg[0] = bg[0] = r[0] = b[0] = g[0]  = 1;
		
		if(n == 2){
			all[0] = 0;
		}
		if(n == 1){
			all[0] = rb[0] = rg[0] = 0;
		}
		for(int i = 1; i < n; i++){
			int aidx = i % 3;
			int apidx = (i - 1 + 3) % 3;
			long[] nall = new long[3];
			long[] nrb = new long[3];
			long[] nrg = new long[3];
			long[] nbg = new long[3];
			long[] nr = new long[3];
			long[] nb = new long[3];
			long[] ng = new long[3];
			if(i < n-2)
				nall[aidx] = all[apidx];
			if(i < n-1){
				nrb[0] = rb[1] + rb[2];
				nrb[1] = rb[0];
				nrb[2] = all[1];
			
				nrg[0] = rg[2];
				nrg[1] = all[0];
				nrg[2] = rg[0] + rg[1];
				
				
				nbg[0] = all[2];
				nbg[1] = bg[0] + bg[2];
				nbg[2] = bg[1];
			}
			if(i == n-1){
				if(i == k-1){
					return r[0] + r[1] + r[2];
				}
				else{
					long ans = 0;
					for(int j = 0; j < 3; j++){
						ans += r[j] + b[j] + g[j];
					}
					return ans;
				}
			}
			nr[0] = r[0] + r[1] + r[2];
			nr[1] = rb[0];
			nr[2] = rg[0] + rg[1];
			
			nb[0] = rb[1] + rb[2];
			nb[1] = b[0] + b[1] + b[2];
			nb[2] = bg[1];
			
			ng[0] = rg[2];
			ng[1] = bg[0] + bg[2];
			ng[2] = g[0] + g[1] + g[2]; 
			
			
			if(i == k-1){
				nall[1] = nall[2] = nrb[1] = nrb[2] = nrg[1] = nrg[2] = nbg[1] = nbg[2] = nr[1] = nr[2] = nb[1] = nb[2] = ng[1] = ng[2] = 0;
			}
			all = nall;
			rb = nrb;
			rg  = nrg;
			bg = nbg;
			r = nr;
			b = nb;
			g = ng;
		}
		
		return r[0];
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AlternateColors2 a = new AlternateColors2();
		long ret = a.countWays(6, 4);
		System.out.println(ret);
	}

}
