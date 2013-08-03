
public class Test2 {

	void run() throws Exception{
		int a = -10;
		a = a % 100;
		
		
		System.out.println(a);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		Test2 t = new Test2();
		t.run();

	}
	public class BIT0 {

		public BIT0(int n){
			this.N = n;
			bit = new int[N+1]; 
		}
		
	    int N = 100000;
	    int bit[] = null; 
		


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
}
