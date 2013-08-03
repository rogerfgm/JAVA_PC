import static java.lang.Math.*;


// 区間に一様にaddしたりしたうえで最小値を求めるようなsegment木
// 最小値及び区間に一様にたされた値の両方を保持する
public class SegmentTreeAdd {

	int n;
	int min[] = null;
	int add[] = null;
	
	public SegmentTreeAdd(int N){
		init(N);
	}
	
	void init(int n_){
		n = 1;
		while(n < n_) n *= 2;
		min = new int[2*n];
		add = new int[2*n];
		for (int i = 0; i < 2 * n; i++) min[i] = Integer.MAX_VALUE;
	}
	
	// initialize value
	void  initialvalue(int k, int a){
		k += n-1;
		min[k] = a;
		while(k > 0){
			k = (k-1) / 2;
			min[k] = Math.min(min[k*2+1], min[k*2+2]);
		}
	}

	int query(int a, int b){
		return query(a, b, 0, 0, n-1);
	}
	void add(int a, int b, int v){
		add(a, b, 0, 0, n-1, v);
	}
	void add(int a, int b, int k, int l, int r, int v){
		
		if(r < a || b < l) return;
		
		if( a <= l && r <= b){
			add[k] += v; // don't need to update min value of myself and children.
			
			// update parent value
			while(k > 0){
				k = (k-1) / 2;
				min[k] = min(min[2*k+1] + add[2*k+1], min[2*k+2] + add[2*k+2]);
			}
			return;
		}
		
	
		add(a, b, k*2+1, l, (l+r) / 2, v);
		add(a, b, k*2+2, (l+r)/2+1, r, v);
	}
	
	int query(int a, int b, int k, int l, int r){
	
		if(r < a || b < l) return Integer.MAX_VALUE;
	
		if( a <= l && r <= b) return min[k] + add[k];
		else{
	
			int vl = query(a, b, k*2+1, l, (l+r) / 2);
			int vr = query(a, b, k*2+2, (l+r)/2+1, r);
			return min(vl, vr) + add[k];
			
		}
	}

}
