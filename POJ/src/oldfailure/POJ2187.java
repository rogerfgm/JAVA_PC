package oldfailure;
import java.util.*;
import java.awt.Point;
import java.io.*;


public class POJ2187 {
	int INF = Integer.MAX_VALUE / 1000;
	static Scanner sc = null;

	int n = 0;
	Point[] ps = null;
	public void solve() throws Exception{

		int n = sc.nextInt();
		ps = new Point[n];
		for(int i = 0; i < n; i++){
			ps[i] = new Point();
			ps[i].x = sc.nextInt();
			ps[i].y = sc.nextInt();
		}
		
		Arrays.sort(ps, new Comparator<Point>() {
			public int compare(Point o1, Point o2) {
				if(o1.x == o2.x){
					return o2.y - o1.y;
				}
				return o1.x - o2.x;
			}
		});
		
		List<Point> list1 = new ArrayList<Point>();
		{
			Point bp = ps[0];
			int by = bp.y;
			for(int i = 1; i < ps.length; i++){
				if(ps[i].y > by) continue;
				Point tp = ps[i];
				for(int j = list1.size()- 1; j >=0; j-- ){
					Point cp = list1.get(j);
					int v1x = tp.x - bp.x;
					int v1y = tp.y - bp.y;
					int v2x = cp.x - bp.x;
					int v2y = cp.y - bp.y;
					
					int gai = v1x*v2y - v2x *v1y;
					if(gai > 0){
						list1.remove(j);
					}
				}
				list1.add(tp);
			}
		}
		List<Point> list2 = new ArrayList<Point>();
		{
			Point bp = ps[0];
			int by = bp.y;
			for(int i = 1; i < ps.length; i++){
				if(ps[i].y <= by) continue;
				Point tp = ps[i];
				for(int j = list2.size()- 1; j >=0; j-- ){
					Point cp = list2.get(j);
					int v1x = tp.x - bp.x;
					int v1y = tp.y - bp.y;
					int v2x = cp.x - bp.x;
					int v2y = cp.y - bp.y;
					
					int gai = v1x*v2y - v2x *v1y;
					if(gai < 0){
						list2.remove(j);
					}
				}
				list2.add(tp);
			}
		}
		List<Point> list = new ArrayList<Point>();
		
		list.addAll(list1);
		for(int i = list2.size() -1; i >= 0; i--){
			list.add(list2.get(i));
		}
		list.add(ps[0]);
		
		Point bp = ps[0];
		
		int mpi = 0;
		int md = 0;
		for(int i = 0; i < list.size(); i++){
			Point cp = list.get(i);
			int dist = (bp.x - cp.x)*(bp.x - cp.x) + (bp.y - cp.y)*(bp.y - cp.y) ;
			if(dist > md){
				md = dist;
				mpi = i;
			}
		}
		for(int i = 0; i < list.size() -1; i++){
			while(true){
				int dist1 = dist(list.get(i), list.get(mpi));
				int dist2 = dist(list.get(i), list.get(mpi+1));
				if(dist2 >= dist1){
					mpi++;
					md = Math.max(md, dist2);
					if(mpi == list.size()-1){
						System.out.println(md);
						return;
					}
				}
				else{
					md = Math.max(md, dist1);
					break;
				}
			}
		}
		
	}
	
	int dist(Point p1, Point p2){
		return (p1.x - p2.x)*(p1.x - p2.x) + (p1.y - p2.y)*(p1.y - p2.y);
	}
	
	
	
    public int[] readIntArray(int n) {
        int[] ret = new int[n];
        for (int i = 0; i < n; i++) {
            ret[i] = sc.nextInt();
        }
        return ret;
    }
    
    class Point{
    	public int x = 0;
    	public int y = 0;
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
		POJ2187 t = new POJ2187();
		int T = 1;
		while(T-- > 0){
			t.solve();
		}
	}


}