package done.d171;

import java.io.*;
import java.util.*;
	




public class C {
	static Scanner sc = null;

	int n = 0;
	
	
	
	public void solve() throws Exception{

		n = sc.nextInt();
		int m = sc.nextInt();
		int[] d = readIntArray(n);
		List<Point> list = new ArrayList<Point>();
		for(int i = 0; i < d.length-1; i++){
			
			if(d[i] > d[i+1]){
				while(i < d.length-1 && d[i] > d[i+1]  ){
					i++;
				}
				i--;
			}
			
			
			if(i >= d.length-2){
				break;
			}
			if(d[i] > d[i+1]){
				Point p = new Point();
				p.l = i + 1;
				while(i < d.length-1){
					if(d[i] < d[i+1]){
						p.r = i+1 + 1;
						list.add(p);
						break;
					}
					i++;
				}
			}
		}
		Point[] ps = new Point[list.size()];
		for(int i = 0; i < list.size(); i++){
			ps[i] = list.get(i);
		}
		for(int i = 0; i < m; i++){
			int a = sc.nextInt();
			int b = sc.nextInt();
			if(list.size() == 0){
				System.out.println("Yes");
				continue;
			}
			else if(a > ps[ps.length-1].l){
				System.out.println("Yes");
				continue;
			}
			else if(b < ps[0].r){
				System.out.println("Yes");
				continue;
			}
			int min = 0;
			int max = list.size()-1;
			int pos = 0;
			if(a <= ps[0].l){
				pos = 0;
			}
			else if(a == ps[ps.length-1].l){
				pos = ps.length-1;
			}
			else{
				pos = max;
				while(min + 1 < max){
					int mid = (min + max) / 2;
					Point tp = ps[mid];
					if(tp.l == a){
						pos = mid;
						break;
					}
					else if(tp.l < a){
						min = mid;
					}
					else{
						max = mid;
					}
					pos = max;
				}
			}
			Point tp = ps[pos];
			if(a <= tp.l && tp.r <= b){
				System.out.println("No");
			}
			else{
				System.out.println("Yes");
			}
		}
		
	}
	
	class Point{
		public int l = 0;
		public int r = 0;
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
		C t = new C();
		t.solve();
		int MAX_N = 1 << 17;
	
	}
	
	
}
