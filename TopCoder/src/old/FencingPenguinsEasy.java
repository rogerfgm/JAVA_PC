package old;
import java.util.*;

public class FencingPenguinsEasy {

	int numPOsts = 0;
	int radius = 0;
	int[] X;
	int[] Y;
	
	double MAX = Double.MAX_VALUE;
	double ans = MAX;
	List<double[]> posts = null;
	
	public double calculateMinArea(int numPosts, int radius, int[] X, int[] Y){
		double ret = -1;
		this.numPOsts = numPosts;
		this.X = X;
		this.Y = Y;
		double kaku = 360.0 / numPosts;
		posts = new ArrayList<double[]>();
		for(int i = 0; i < numPosts; i++){
			double[] p = new double[2];
			p[0] = radius * Math.sin(Math.toRadians(kaku * i));
			p[1] = radius * Math.cos(Math.toRadians(kaku * i));
			posts.add(p);
			//System.out.println(p[1]);
		}
		
		for(int i = 0; i < posts.size() - 1; i++){
			double[] p1 = posts.get(i);
			double[] p2 = posts.get(i+1);
			for(int j = 0; j < X.length; j++){
				int x = X[j];
				int y = Y[j];
				if(x <  Math.min(p1[0], p2[0]) ||  Math.max(p1[0], p2[0]) < x){
					continue;
				}
				if( y < Math.min(p1[1], p2[1]) || Math.max(p1[1], p2[1]) < y){
					continue;
				}
				double xlen = x - p1[0];
				double checky = p1[1] + (p2[1] - p1[1]) * xlen / (p2[0] - p1[0]);
				if(Math.abs(checky - y) < 0.000001){
					return -1;
				}
			}
		}
		
		List<Integer> ps = new ArrayList<Integer>();
		check(ps, 0);
		
		
		
		
		if(ans == MAX){
			return -1;
		}
		return ans;
	}
	
	private void check(List<Integer> ps, int pos){
		if(pos == numPOsts){
			calc(ps);
		}
		List<Integer> nps = new ArrayList<Integer>(ps);
		check(nps, pos + 1);
		nps =  new ArrayList<Integer>(ps);
		nps.add(pos);
		check(nps, pos + 1);
	}
	
	private void calc(List<Integer> ps){
		if(ps.size() < 3){
			return;
		}
		double men = 0;
		boolean[] p = new boolean[X.length];
		
		int top = ps.get(0);
	
		for(int i = 1; i < ps.size() - 1; i++){
			int mid = ps.get(i);
			int left = ps.get(i + 1);
			
			double x1 = posts.get(top)[0];
			double y1 = posts.get(top)[1];
			
			
		}
		
		boolean check = true;
		for(int i = 0; i < p.length; i++){
			if(!p[i]){
				check = false;
			}
		}
		if(check){
			ans = Math.min(ans, men);
		}
	}
	

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FencingPenguinsEasy f = new FencingPenguinsEasy();
		int[] x = {0};
		int[] y = {5};
		double r = f.calculateMinArea(3, 5, x, y);
		System.out.println(r);
	}

}
