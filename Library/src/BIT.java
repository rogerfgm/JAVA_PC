
/*
 * ある区間のsumを高速に計算する。
 * sum関数は1からiまでのsumを計算するので、jからiまでのsumを計算したい時はsum(i) - sum(j-1)を計算する
 */
public class BIT {

	public BIT(int n){
		this.N = n;
		bit = new int[N+2];
	}
	
    int N = 100000;
    int bit[] = null; 
	
  
    int sum0(int i){
    	return sum(i+1);
    }
    
    void add0(int i, int x){
    	add(i+1, x);
    }
    

    int sum(int i){
    	int s = 0;
    	while(i > 0){
    		s += bit[i];
    		i-=i&-i;
    	}
    	return s;
    }
    

    void add(int i, int x){
    	while(i <= N){
    		bit[i] = bit[i] + x;
    		i += i & -i;
    	}
    }
}
