
// POJ 2236
public class UnionFind {
	int Max_N = 100000;
	int[] par = null; // 親
	int[] rank = null; // 木の深さ
	
	public UnionFind(int n){
		Max_N = n;
		init();
	}
	
	
	// n要素で初期化
	void init(){
		par = new int[Max_N];
		rank = new int[Max_N];
		for(int i = 0; i < Max_N; i++){
			par[i] = i;
			rank[i] = 0;
		}
	}
	
	int find(int x){
		if(par[x] == x){
			return x;
		}
		else{
			return par[x] = find(par[x]);
		}
	}
	
	void unite(int x, int y){
		x = find(x);
		y = find(y);
		if(x == y) return;
		
		if(rank[x] < rank[y]){
			par[x] = y;
		}
		else{
			par[y] = x;
			if(rank[x] == rank[y]) rank[x]++;
		}
	}
	
	boolean same(int x, int y){
		return find(x) == find(y);
	}
}
