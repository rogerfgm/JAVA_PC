package srm562;
import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

// 562 500
// 凸包ではなくeditorialのやり方で
public class CheckerFreeness {

	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	
	
	public String isFree(String[] wxs, String[] wys, String[] bxs, String[] bys) {
		String wx = "";
		String wy = "";

		for(int i = 0; i < wxs.length; i++){
			wx += wxs[i];
		}
		for(int i = 0; i < wys.length; i++){
			wy += wys[i];
		}	
		String[] sx = wx.split(" ");
		String[] sy = wy.split(" ");
		
		
		P[] wp = new P[sx.length];
		for(int i = 0; i < sx.length; i++){
			wp[i] = new P(parseInt(sx[i]), parseInt(sy[i]));
		}
		wx = wy = "";
		for(int i = 0; i < bxs.length; i++){
			wx += bxs[i];
		}
		for(int i = 0; i < bys.length; i++){
			wy += bys[i];
		}	
		sx = wx.split(" ");
		sy = wy.split(" ");
		P[] bp = new P[sx.length];
		for(int i = 0; i < sx.length; i++){
			bp[i] = new P(parseInt(sx[i]), parseInt(sy[i]));
		}
		
		
		
		int W = wp.length;
		int B = bp.length;
		int T = W + B;
		long[][][] dp = new long[T][T][4];
		
		for(int i = 0; i < W; i++){
			for(int j = i+1; j < T; j++){
				
				for(int k = 0; k < B; k++){
					if(j == k + W) continue;
					P pi = wp[i];
					P pj = null;
					if(j < W){
						pj = wp[j];
					}
					else{
						pj = bp[j-W];
					}
					P pk = bp[k];
					int ki = k / 64;
					int kp = k % 64;
					if(ccw(pi, pj, pk) > 0){
						dp[i][j][ki] |= b << kp;
					}
					else{
						dp[j][i][ki] |= b << kp;
					}
				}
			}
		}

		for(int i = 0; i < W-1; i++){
			for(int j = i+1; j < W; j++){
				for(int k = 0; k < B; k++){
					boolean f = ccw(wp[i], wp[j], bp[k]) > 0;
					for(int p = 0; p < 4; p++){
						
						if(f){
							long m = dp[j][j][p] & dp[W+k][i][p];
							m &= dp[j][W+k][p];
							
							if(m != 0){
								return "NO";
							}
						}
						else{
							long m = dp[i][j][p] & dp[W+k][j][p];
							m &= dp[i][W+k][p];
							
							if(m != 0){
								return "NO";
							}
						}
					}
//					if(ccw(wp[i], wp[j], bp[k]) > 0){
//						for(int l = 0; l < 4; l++){
//							long p = dp[j][i][l] & dp[j][W+k][l];
//							if( (p & dp[i][W+k][l]) > 0){
//								return "YES";
//							}
//						}
//					}
//					else{
//						for(int l = 0; l < 4; l++){
//							long p = dp[i][j][l] & dp[j][W+k][l];
//							if( (p & dp[W+k][i][l]) > 0){
//								return "YES";
//							}
//						}
//					}
				}
			}
		}

		
		return "YES";
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CheckerFreeness t = new CheckerFreeness();
		
		{
			String[] i1 = {"1 2"};
			String[] i2 = {"2 1"};
			String[] i3 ={"1 2"};
			String[] i4 ={"1 2"};
			String r = t.isFree(i1, i2, i3, i4);
			out.println("NO : " + r);
		}
		
		
		{
			String[] i1 = {"2", "5", "3", " ", "1", "7", "3"};
			String[] i2 = {"180 254"};
			String[] i3 ={"32", "5 1", "42"};
			String[] i4 ={"462 423"};
			String r = t.isFree(i1, i2, i3, i4);
			out.println("YES : " + r);
		}
		
		{
			String[] i1 = {"6115 9723 3794 2275 2268 2702 3657 915 7953 2743 7"
					,"716 9645 2547 9490 9365 326 6601 5215 6771 7153 72"
					,"93 5922 714 2258 4369 9524 302 8417 6620 1143"};
			String[] i2 = {"621 1611 7140 503 5345 7202 681 4908 2510 5908 279"
					,"6 6286 6873 6682 9197 6710 8517 1913 7784 8533 665"
					,"4 446 3561 7241 6168 2025 4739 9501 5340 6446"};
			String[] i3 ={"6833 131 4151 1776 1959 7210 1903 6107 598 6220 94"
					,"24 5374 6718 2919 6068 6644 5070 710 7121 1630 370"
					,"3 1051 5739 9294 8798 3371 8107 2130 6608 534"};
			String[] i4 ={"7496 2412 2801 3473 5810 2714 7853 9714 5470 3558 "
					,"8143 2391 8234 7292 9311 1636 8978 1107 2262 9175 "
					,"7259 8842 5294 7209 2317 3825 3413 820 3774 5393"};
			String r = t.isFree(i1, i2, i3, i4);
			out.println("NO : " + r);
		}
		
		{
			String[] i1 =  {"219211 1996214 1706774 3634920 909831 1774128 8503"
					,"52 2233050 2099138 3380396 1128982 3682525 1483700"
					," 763080 487867 8203 1791027 463556 1103323 1406861"
					," 6374234 760949 4340236 727393 2073832 1289052 103"
					,"8147 4448130 151066 412440 1068735 377239 2677933 "
					,"1299598 339843 289973 3707319 555280 230418 431719"};
			String[] i2 =  {"1638083 5698739 3105504 9726902 9745795 5049444 15"
					,"80592 3952120 6606668 7460527 7239299 8726128 4913"
					,"547 6264100 5701660 8865937 4969114 8014845 327236"
					,"1 6389154 9739755 2561669 9412417 5452337 3150506 "
					,"5832197 1571976 8779325 3306446 948271 5133709 949"
					,"394 6919798 7525636 3568024 6833196 9237472 733313"
					,"1 9939064 9720014"};
			String[] i3 = {"5860334 8007503 7455523 4864927 9573526 2718360 81"
					,"12104 6684287 9921506 4840886 5415948 3451454 5320"
					,"996 9268757 9261724 8254668 2292750 8035828 233352"
					,"1 7676906 5234406 8533320 6562751 4884098 4971897 "
					,"5569360 8519168 3100295 9351613 7733878 7579030 32"
					,"46775 7297460 8370542 7099759 5782969 2978083 3390"
					,"488 7482758 1332401 6094629 9717305 5503121 572842"
					,"1 4903563 6331656 2867455 3410007 7751527 7228221 "
					,"4111694 5171296 6847697 4601273 7599828 5515929 94"
					,"60593 9332762 5389080 4512091 8668538 5711743 5838"
					,"534 4825079 8145628 3810005 2964724 5594550 785748"
					,"3 6283769"};
			String[] i4 = {"5911694 8009943 212555 5838688 9896256 607434 5857"
					,"663 4616750 1477573 7168026 3090917 4379806 326465"
					,"7 4189076 2104028 3279691 94211 8503556 78457 4394"
					,"360 3344176 3223317 2624498 4883494 1532240 732937"
					,"1 1518674 1353567 892134 5536454 8527392 2603965 6"
					,"623264 8830827 2030444 3002706 83058 4475866 20876"
					,"25 1790695 4034441 5409379 3571098 4600050 736561 "
					,"250475 3733256 3011439 2144994 4523046 3119883 607"
					,"582 8361403 6525451 7518329 926803 4884524 8424659"
					," 7088689 5762049 9532481 4914186 7314621 4339084 3"
					,"741685 3837953 3177252 612550 9688871 5872931"};
			String r = t.isFree(i1, i2, i3, i4);
			out.println("YES : " + r);
		}
		
		


	}

	
	public class P{
		public double x = 0;
		public double y = 0;
		public P(){}
		public P(double x, double y){
			this.x = x;
			this.y = y;
		}
		public P(P p){
			x = p.x;
			y = p.y;
		}
		
		public P add(P p){
			P np = new P(this);
			np.x += p.x;
			np.y += p.y;
			return np;
		}
		public P subtract(P p){
			P np = new P(this);
			np.x -= p.x;
			np.y -= p.y;
			return np;
		}
		public P mul(P p){
			P np = new P();
			np.x = x * p.x - y * p.y;
			np.y = x * p.y + y * p.x;
			return np;
		}


		public P add(double d){
			P np = new P(this);
			np.x += d;
			np.y += d;
			return np;
		}
		public P mul(double d){
			P np = new P(this);
			np.x *= d;
			np.y *= d;
			return np;
		}
		public P divide(double d){
			P np = new P(this);
			np.x /= d;
			np.y /= d;
			return np;
		}
	}

	public double crossProduct(P p1, P p2, P p3){
		double ret = (p2.x - p1.x) * (p3.y - p1.y) - (p2.y - p1.y) * (p3.x - p1.x);
		if(abs(ret) < DF){
			ret = 0;
		}
		return ret;
	}

	int ccw(P a, P b, P c) {
		double cp = crossProduct(a, b, c);
		if(cp > 0) return 1; // counter clockwise
		else return -1;
		
	
	}
}
