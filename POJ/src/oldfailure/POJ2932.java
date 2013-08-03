package oldfailure;
import java.util.*;
import java.io.*;


public class POJ2932 {
	int INF = Integer.MAX_VALUE / 1000;
	double DF = 0.0000000001;
	static Scanner sc = null;

	int n = 0;
	Point[] ps = null;
	public void solve() throws Exception{

		int n = sc.nextInt();
		ps = new Point[n];
		for(int i = 0; i < n; i++){
			double R = sc.nextDouble();
			double x = sc.nextDouble();
			double y = sc.nextDouble();
			ps[i] = new Point(x, y, R, i+1);
			
		}
		
		Arrays.sort(ps, new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				if(o1.l < o2.l){
					return -1;
				}
				else{
					return 1;
				}
			}
		});
		
		List<Point> prevs = new ArrayList<Point>();
		List<Integer> ans = new ArrayList<Integer>();
		for(int i = 0; i < n; i++){
			boolean flag = false;
			Point p = ps[i];
			for(int j = prevs.size() -1; j >= 0; j--){
				if(prevs.get(j).r < p.l){
					prevs.remove(j);
				}
				else{
					Point pv = prevs.get(j);
					if(pv.R > kyori(pv.x, pv.y, p.x, p.y)+ p.R ){
						flag = true;
						break;
					}
				}
			}
			if(!flag){
				ans.add(p.idx);
				prevs.add(p);
			}
		}
		System.out.println(ans.size());
		for(int i = 0; i < ans.size(); i++){
			if(i != 0){
				System.out.print(" ");
			}
			System.out.print(ans.get(i));
		}
		
		
	}
	
	double kyori(double x1, double y1, double x2, double y2){
		double k2 = (x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1);
		return Math.sqrt(k2);
	}
	


	
    public int[] readIntArray(int n) {
        int[] ret = new int[n];
        for (int i = 0; i < n; i++) {
            ret[i] = sc.nextInt();
        }
        return ret;
    }
	
    class Point{
 
    	public Point(double xx, double yy, double rr, int idx){
    		x = xx;
    		y = yy;
    		R = rr;
    		this.idx = idx;
    		l = x - R;
    		r = x + R;
    	}
    	public int idx = 0;
    	public double x = 0;
    	public double y = 0;
    	public double R = 0;
    	
    	public double r = 0;
    	public double l = 0;

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
		POJ2932 t = new POJ2932();
		t.solve();
	}


}