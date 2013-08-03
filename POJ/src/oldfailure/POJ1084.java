package oldfailure;
import java.util.*;
import java.awt.Point;
import java.io.*;


public class POJ1084 {
	int INF = Integer.MAX_VALUE / 1000;
	static Scanner sc = null;

	int n = 0;
	int[] d = null;
	Set<Integer> set = null;
	int size = 0;
	public void solve() throws Exception{

		n = sc.nextInt();
		int z = sc.nextInt();
		d = readIntArray(z);
		size = n * (n+1) * 2;
		long l = ((long)1 << size+1) -1;

		for(int i = 0; i < d.length; i++){
			l = del(l, d[i]);
		}
		
		int ret = check(l, 1);
		System.out.println(ret);
	}
	
	int check(long l, int pos){
		
		int num = num(l);
		if(num == 0){
			return 0;
		}
		if(pos > size){
			return INF;
		}
	
		if(!has(l, pos)){
			return check(l, pos+1);
		}
		
		int ret = check(l, pos + 1);
		l = l ^ ( (long)1 << pos );
		int nnum = num(l);
		if(nnum < num){
			ret = Math.min(ret, check(l,  pos + 1) + 1);
		}
		
		return ret;
	}
	
	int num(long p){
		int ret = 0;
		int base = n + n + 1;
		for(int s = 1; s <= n; s++){
			for(int i = 1; i <= n - s + 1; i++){
				for(int j = 1; j <= n - s + 1; j++){
					boolean f = true;
					int t = base * (i-1) + j;
					int l = base * (i-1) + n + j;
					int r = base * (i-1) + n + j + s;
					int b = base * (i-1) + base * s + j;
					
					for(int k = 0; k < s; k++){
						int top = t + k;
						if(!has(p, top)){
							f = false;
						}
					}
					if(f){
						for(int k = 0; k < s; k++){
							int left = l + (base) * k;
							if(!has(p, left)){
								f = false;
							}
						}
					}
					if(f){
					for(int k = 0; k < s; k++){
						int right = r + (base) * k;
						if(!has(p, right)){
							f = false;
						}
					}
					}
					if(f){
					for(int k = 0; k < s; k++){
						int bottom = b + k;
						if(!has(p, bottom)){
							f = false;
						}
					}
					}
					if(f){
						ret++;
					}
				}
			}
		}
		
		return ret;
	}
	
	
	boolean has(long l, int pos){
		long dl = (long)1 << pos;
		long ck = l & dl;
		return ck > 0;
	}
	
	long del(long l, int pos){
		long dl = (long)1 << pos;
		l = l ^ dl;
		return l;
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
		POJ1084 t = new POJ1084();
		int T = sc.nextInt();
		while(T-- > 0){
			t.solve();
		}
	}


}