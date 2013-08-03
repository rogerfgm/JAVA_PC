package oldfailure;
import java.util.*;
import java.io.*;

/**
White Bird
Angry Birds is a mobile game of a big craze all over the world.
 You were convinced that it was a waste of time to play the game, so you decided to create an automatic solver.


You are describing a routine that optimizes the white bird's strategy to defeat a pig (enemy) by hitting an egg bomb. 
The white bird follows a parabolic trajectory from the initial position, and it can vertically drop egg bombs on the way.

In order to make it easy to solve, the following conditions hold for the stages.

N obstacles are put on the stage.
Each obstacle is a rectangle whose sides are parallel to the coordinate axes.
The pig is put on the point (X,Y).
You can launch the white bird in any direction at an initial velocity V from the origin.
If the white bird collides with an obstacle, it becomes unable to drop egg bombs.
If the egg bomb collides with an obstacle, the egg bomb is vanished.
The acceleration of gravity is 9.8m⁄s2. Gravity exerts a force on the objects in the decreasing direction of y-coordinate.

 */

public class A2308 {
	int INF = Integer.MAX_VALUE / 1000;
	double DF = 0.0000000001;
	static Scanner sc = null;


	R[] rs = null;
	double G = 9.8;
	int n = 0;
	int V = 0;
	double X = 0;
	double Y = 0;
	public void solve() throws Exception{

		n = sc.nextInt();
		V = sc.nextInt();
		X = sc.nextInt();
		Y = sc.nextInt();
	
		rs = new R[n];
		
		for(int i = 0; i < n; i++){
			R r = new R();
			r.lx = sc.nextInt();
			r.by = sc.nextInt();
			r.rx = sc.nextInt();
			r.ty = sc.nextInt();

			
			rs[i] = r;
		}
		
		boolean flag = false;		
		
		if(check(X, Y)){
			flag = true;
		}
		
		for(int i = 0; i < n && !flag ; i++){
			R r = rs[i];
			flag = flag | check(r.lx, r.ty);
			flag = flag | check(r.rx, r.ty);
		}
		
		
		
		if(flag){
			System.out.println("Yes");
		}
		else{
			System.out.println("No");
		}
		
		
		
		
		
	}
	
	boolean check(double x, double y){
		
		double a = G * G;
		double b = 4 * (y * G - V * V);
		double c = 4 * (x*x + y*y);
		
		double ck = b*b - 4 * a * c;
		if(ck < 0 && Math.abs(ck) > DF){
			return false;
		}
		
		if(Math.abs(ck) < DF){
			ck = 0;
		}
		
		for(int z = 0; z < 2; z++){
			double k = 1;
			if(z == 0){
				k = -1;
			}
			double T = (-b + Math.sqrt(ck) * k ) / (2 * a);
			if(Math.abs(T) < DF){
				T = 0;
			}
			if(T == 0 || T < 0){
				continue;
			}
			double t = Math.sqrt(T);
			double vx = x / t;
			double vy = (y + G * t * t / 2) / t;
			if(Math.abs(vx) < DF){
				vx = 0;
			}
			if(vx == 0){
				continue;
			}
			double tt = X / vx;
			
			double ty = vy * tt - G * tt * tt / 2;
			if(Math.abs(ty - Y) < DF){
				ty = Y;
			}
			if(ty < Y){
				continue;
			}
			boolean flag = true;
			for(int i = 0; i < rs.length; i++){
				R r = rs[i];
				if(r.ty <= ty && r.ty >= Y && r.lx <= X && r.rx >= X){
					flag = false;
				}
				
				// left x
				t = r.lx / vx;
				double ly = vy * t - G * t * t /2;
				if(ly > r.by && ly < r.ty && Math.abs(ly - r.by) > DF &&  Math.abs(ly - r.ty) > DF){
					flag = false;
				}
				// left x
				t = r.rx / vx;
				double ry = vy * t - G * t * t /2;
				if(ry > r.by && ry < r.ty && Math.abs(ry - r.by) > DF &&  Math.abs(ry - r.ty) > DF){
					flag = false;
				}
				// top y
				{
					{
						double ta = G / 2;
						double tb = -1 * vy;
						double tc = r.ty;
						
						double tck = tb * tb - 4 * ta * tc;
						if(Math.abs(tck) < DF){
							tck = 0;
						}
						if(tck >= 0){
							for(int e = 0; e < 2; e++){
								int f = 1;
								if(e == 0){
									f = -1;
								}
								double ttt = (-tb + f * Math.sqrt(tck)) / (2 * a);
								double ckx = vx * ttt;
								if(ckx > r.lx && ckx < r.rx && Math.abs(ckx - r.rx) > DF &&  Math.abs(ckx - r.lx) > DF){
									flag = false;
								}
							}
						}
					}
					{
						double ta = G / 2;
						double tb = -1 * vy;
						double tc = r.by;
						
						double tck = tb * tb - 4 * ta * tc;
						if(Math.abs(tck) < DF){
							tck = 0;
						}
						if(tck >= 0){
							for(int e = 0; e < 2; e++){
								int f = 1;
								if(e == 0){
									f = -1;
								}
								double ttt = -tb + f * Math.sqrt(tck) / (2 * a);
								double ckx = vx * ttt;
								if(ckx > r.lx && ckx < r.rx && Math.abs(ckx - r.rx) > DF &&  Math.abs(ckx - r.lx) > DF){
									flag = false;
								}
							}
						}
					}
				}
				
			}
			if(flag){
				return flag;
			}
		}
		
		
		
		return false;
	}
	
	
	class R{
		double lx = 0;
		double by = 0;
		double rx = 0;
		double ty = 0;
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
		A2308 t = new A2308();
		t.solve();
	}


}