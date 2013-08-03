package srm562;
import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class PastingPaintingDivOne {

	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	String[] c = null;
	int Y = 0;
	int X = 0;
	int[] ind = null;
	int T = 0;
	long[] ret = null;
	public long[] countColors(String[] clipboard, int T){
		this.T = T;
		c = clipboard;
		ret = new long[3];
		Y = c.length;
		X = c[0].length();

		for(int i = 0; i < c[0].length(); i++){
			check(0, i);
		}
		for(int i = 1; i < c.length; i++){
			check(i, 0);
		}
	
		
		
		return ret;
	}
	
	void check(int y, int x){

		while(true){
			if(y >= Y || x >= X){
				return;
			}
			if(c[y].charAt(x) != '.'){
				break;
			}
			y++;
			x++;
		}
		char a = c[y].charAt(x);
		if(a == 'R'){
			ret[0] += T;
		}
		else if(a == 'G'){
			ret[1] += T;
		}
		else{
			ret[2] += T;
		}
		y++;
		x++;
		long cnt = 0;
		while(true){
			if(y >= Y || x >= X){
				return;
			}
			if(c[y].charAt(x) != '.'){
				a = c[y].charAt(x);
				if(a == 'R'){
					ret[0] += min(cnt+1, T);
				}
				else if(a == 'G'){
					ret[1] += min(cnt+1, T);
				}
				else{
					ret[2] += min(cnt+1, T);
				}
				cnt = 0;
			}
			else{
				cnt++;
			}
			y++;
			x++;
		}

	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PastingPaintingDivOne t = new PastingPaintingDivOne();
		String[] s = {
				"..........G..........",
				".........G.G.........",
				"........G...G........",
				".......G.....G.......",
				"......G..B.B..G......",
				".....G...B.B...G.....",
				"....G...........G....",
				"...G...R.....R...G...",
				"..G.....RRRRRR....G..",
				".G..........RR.....G.",
				"GGGGGGGGGGGGGGGGGGGGG"
				};
		int T = 1000000000;
		long[] r = t.countColors(s, T);
		out.println(r[0] + " " + r[1] + " " + r[2]);
	}

}
