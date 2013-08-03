
// 区間の最小値を求める単純なセグメント木
public class SegmentTree {
	int n;
	int dat[] = null;
	
	public SegmentTree(int N){
		init(N);
	}
	

	void init(int n_){
		
		
		n = 1;
		while(n < n_) n *= 2;
		dat = new int[2*n];
		
		for (int i = 0; i < 2 * n; i++) dat[i] = Integer.MAX_VALUE;
	}
	

	void  update(int k, int a){
		
		k += n-1;
		dat[k] = a;
		while(k > 0){
			k = (k-1) / 2;
			dat[k] = Math.min(dat[k*2+1], dat[k*2+2]);
		}
	}
	

	int query(int a, int b){
		return query(a, b, 0, 0, n-1);
	}
	

	
	int query(int a, int b, int k, int l, int r){
	
		if(r < a || b < l) return Integer.MAX_VALUE;
		
	
		if( a <= l && r <= b) return dat[k];
		else{
	
			int vl = query(a, b, k*2+1, l, (l+r) / 2);
			int vr = query(a, b, k*2+2, (l+r)/2+1, r);
			return Math.min(vl, vr);
			
		}
	}
		
}

