package oldfailure;
import java.util.*;
import java.io.*;


public class POJ1127 {
	int INF = Integer.MAX_VALUE / 1000;
	double DF = 0.0000000001;
	static Scanner sc = null;

	int n = 0;
	public void solve() throws Exception{

		int n = sc.nextInt();
		Point[] ps = new Point[n+1];
		for(int i = 0;i < n; i++){
			int xx1 = sc.nextInt();
			int yy1 = sc.nextInt();
			int xx2 = sc.nextInt();
			int yy2 = sc.nextInt();
			Point p = new Point(xx1, yy1, xx2, yy2);
			ps[i+1] = p;
		}
		
		boolean[][] con = new boolean[n+1][n+1];
		
		for(int i = 1; i <= n-1; i++){
			for(int j = i + 1; j <= n; j++){
				Point p1 = ps[i];
				Point p2 = ps[j];
				double xs1 = p1.x2 - p1.x1;
				double ys1 = p1.y2 - p1.y1;
				double xs2 = p2.x2 - p2.x1;
				double ys2 = p2.y2 - p2.y1;
				
				double a1 = 0;
				double b1 = 0;
				double a2 = 0;
				double b2 = 0;
				
				if(ys1 == 0){
					a1 = 0;
					b1 = p1.y1;
				}
				else{
					a1 = ys1 / xs1;
					b1 = p1.y1 - a1 * p1.x1;
				}
				if(ys2 == 0){
					a2 = 0;
					b2 = p2.y1;
				}
				else{
					a2 = ys2 / xs2;
					b2 = p2.y1 - a2 * p2.x1;
				}
				
				boolean kousa = false;
				if(a1 == a2){
					double y1 = a1 * p2.x1 + a1;
					if(Math.abs(y1 - p2.y1) <= DF){
						kousa = true;
					}
					double y2 = a2 * p2.x2 + a1;
					if(Math.abs(y2 - p2.y2) <= DF){
						kousa = true;
					}
				}
				else{
					double x = (b1 - b2) / (a2 - a1);
					double xl1 = Math.min(p1.x1, p1.x2);
					double xr1 = Math.max(p1.x1, p1.x2);
					double xl2 = Math.min(p2.x1, p2.x2);
					double xr2 = Math.max(p2.x1, p2.x2);
					if(xl1 <= x && x <= xr1 && xl2 <= x && x <= xr2){
						kousa = true;
					}
				}
				if(kousa){
					con[i][j] = true;
					con[j][i] = true;
				}
			}
		}
		
		while(true){
			int a = sc.nextInt();
			int b = sc.nextInt();
			if(a == 0 && b == 0){
				break;
			}
			else if(a == b){
				System.out.println("CONNECTED");
				continue;
			}
			
			boolean[] used = new boolean[n+1];
			int pos = a;
			used[pos] = true;
			boolean found = false;
			while(true){
				boolean upd = false;
				for(int i = 1; i <= n; i++){
					for(int j = 1; j <= n; j++){
						if(used[i] && con[i][j] && !used[j]){
							if(j == b){
								found = true;
							}
							upd = true;
							used[j] = true;
						}
					}
				}
				if(!upd){
					break;
				}
			}
			if(found){
				System.out.println("CONNECTED");
			}
			else{
				System.out.println("NOT CONNECTED");
			}
		}
		
	}
	
	


	
    public int[] readIntArray(int n) {
        int[] ret = new int[n];
        for (int i = 0; i < n; i++) {
            ret[i] = sc.nextInt();
        }
        return ret;
    }
	
    class Point{
    	public Point(int xx1, int yy1, int xx2, int yy2){
    		x1 = xx1;
    		y1 = yy1;
    		x2 = xx2;
    		y2 = yy2;
    	}
    	
    	public double x1 = 0;
    	public double y1 = 0;
    	public double x2 = 0;
    	public double y2 = 0;
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
		POJ1127 t = new POJ1127();
		t.solve();
	}


}