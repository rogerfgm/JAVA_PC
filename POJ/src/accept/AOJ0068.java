package accept;

import java.util.*;
import java.io.*;


public class AOJ0068 {
	int INF = Integer.MAX_VALUE / 1000;
	static Scanner sc = null;

	static int n = 0;
	int m = 0;
	

	public void solve() throws Exception{
		P[] ps = new P[n];
		for(int i = 0; i < n; i++){
			String s = sc.next();
			String[] ss = s.split(",");
			P p = new P();
			p.x = Double.parseDouble(ss[0]);
			p.y = Double.parseDouble(ss[1]);
			ps[i] = p;
		}
		List<P> ret = convec_hull(ps);
		int ans = n - ret.size();
		System.out.println(ans);
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
		AOJ0068 t = new AOJ0068();
	
		while(true){
			n = sc.nextInt();
			if(n == 0){
				break;
			}
			t.solve();
		}
	}
	final double EPS = 0.00000000001;
	
	/**
	 * 点　Point
	 */
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
		
		public P divide(P z){
			double norm_z = norm(z);
			double re_tmp = (x * z.x + y * z.y) / norm_z;
			double im_tmp = (y * z.x - x * z.y) / norm_z;
			P p = new P(re_tmp, im_tmp);
			return p;
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
	
	public double real(P p){
		return p.x;
	}
	public double imag(P p){
		return p.y;
	}
	
	/**
	 * 線分
	 */
	public class L {
		public P a = null;
		public P b = null;
		
		public L(P a, P b){
			this.a = a;
			this.b = b;
		}
		public L(double x1, double y1, double x2, double y2){
			a = new P(x1, y1);
			b = new P(x2, y2);
		}
	}
	
	/**
	 * 多角形
	 * 反時計回りを前提
	 */
	public class G {
		List<P> ps = new ArrayList<P>();
		public int size(){
			return ps.size();
		}
		public P get(int i){
			return ps.get(i);
		}
		public void add(double x, double y){
			P p = new P(x, y);
			ps.add(p);
		}
		public void add(P p){
			ps.add(p);
		}
		public void addNew(P p){
			P np = new P(p);
			ps.add(np);
		}
	}
	
	public P prev(G g, int i){
		if(i == 0) i = g.size()-1;
		else i--;
		return g.get(i);
	}
	
	public P curr(G g, int i){
		i %= g.size();
		return g.get(i);
	}
	
	public P next(G g, int i){
		i++;
		return curr(g, i);
	}
	
	public class C {
		public P p;
		public double r;
		public C(P p, double r){
			this.p = p;
			this.r = r;
		}
	};
	
	
	/**
	 * 絶対値がEPS未満の値を0に丸める
	 * @param d
	 * @return
	 */
	public double marume(double d){
		if(Math.abs(d) < EPS){
			return 0;
		}
		return d;
	}
	
	
	/**
	 * 内積
	 */
	public double dot(P a, P b){
		return a.x * b.x + a.y * b.y;
	}
	
	/**
	 * 外積
	 */
	public double cross(P a, P b){
		return a.x * b.y - b.x * a.y;
	}
	
	/**
	 * aからbに対するベクトルを算出。aが起点。b - a 
	 */
	public P vectr(P b, P a){
		P p = new P();
		p.x = b.x - a.x;
		p.y = b.y - a.y;
		return p;
	}
	
	/**
	 * ベクトルの長さ（０からの距離）
	 */
	public double dist(P p){
		return Math.sqrt(p.x * p.x + p.y * p.y);
	}
	
	public double norm(P p){
		return p.x * p.x + p.y * p.y;
	}
	
	// abs == dist
	public double abs(P p){
		return dist(p);
	}
	
	/**
	 * 2点間の距離
	 */
	public double dist(P a, P b){
		P p = vectr(b, a);
		return dist(p);
	}
	
	public P projection(L l, P p) {
		  double t = dot(p.subtract(l.a), l.a.subtract(l.b)) / norm(l.a.subtract(l.b));
		  P r = l.a.subtract(l.b);
		  r.x *= t;
		  r.y *= t;
		  return l.a.add((r));
	}
	public P reflection(L l, P p) {
		P t = new P(2, 0);
		P q = projection(l, p).subtract(p);
		t = t.mul(q);
	  return p.add(t);
	}
	
	public boolean intersectLL(L l, L m) {
		  return Math.abs(cross(l.b.subtract(l.a), m.b.subtract(m.a))) > EPS || // non-parallel
				  Math.abs(cross(l.b.subtract(l.a), m.a.subtract(l.a))) < EPS;   // same line
	}
	
	public boolean intersectLS(L l, L s) {
	  return cross(l.b.subtract(l.a), s.a.subtract(l.a))*       // s[0] is left of l
	         cross(l.b.subtract(l.a), s.b.subtract(l.a)) < EPS; // s[1] is right of l
	}
	
	
	public boolean intersectLP(L l, P p) {
	  return Math.abs(cross(l.b.subtract(p), l.a.subtract(p))) < EPS;
	}
		
		
	public boolean intersectSS(L s, L t) {
	  return ccw(s.a,s.b,t.a)*ccw(s.a,s.b,t.b) <= 0 &&
	         ccw(t.a,t.b,s.a)*ccw(t.a,t.b,s.b) <= 0;
	}
		
	public boolean intersectSS2(L s, L t) { // 接しているやつは交差と考えない
		if (ccw(s.a, s.b, t.a) == 0) {
		      int c = ccw(s.a,s.b,t.b);
		      if (s.a == t.a) {
		        if (c!=-2&&c > 0) return false;
		      } else if (s.b == t.a) {
		        if (c!=2&&c > 0) return false;
		      } else if (Math.abs(c)==1) return false;
		}
		if (ccw(s.a, s.b, t.a) == 0) {
		      int c = ccw(s.a,s.b,t.a);
		      if (s.a == t.b) {
		        if (c!=-2&& c > 0) return false;
		      } else if (s.b == t.b) {
		        if (c!=2&&c > 0) return false;
		      } else if (Math.abs(c)==1) return false;
		}
	  return ccw(s.a,s.b,t.a)*ccw(s.a,s.b,t.b) <= 0 &&
	         ccw(t.a,t.b,s.a)*ccw(t.a,t.b,s.b) <= 0;
	}
	
	public boolean isPara(L s, L t){
		double a1 = angle(s) % Math.PI;
		double a2 = angle(t) % Math.PI;
		if(Math.abs(a1-a2) < EPS) return true;
		return false;
	}
		
		
	public boolean intersectSP(L s, P p) {
	  return abs(s.a.subtract(p)) + abs(s.b.subtract(p)) - abs(s.b.subtract(s.a)) < EPS; // triangle inequality
	}
	
	
	
	
	/**
	 * 線分a, b　に対するcの位置関係。（a から　bをみたときにcがどちら側にあるか）
	 * 0　 線分上
	 * 2  c--a--b on line
	 * -2  a--b--c on line
	 * 1 反時計回り方向
	 * -1 時計回り方向
	 */
	int ccw(P a, P b, P c) {
		double cp = crossProduct(a, b, c);
		if(cp != 0){
			if(cp > 0) return 1; // counter clockwise
			else return -1;
		}
		
		b = vectr(b, a);
		c = vectr(c, a);

	    if (dot(b, c) < 0)     return +2;       // c--a--b on line
	    if (norm(b) < norm(c)) return -2;       // a--b--c on line
		  return 0;                    // on line
	}
	
	/**
	 * p3がp1からp2に引いた先の、時計回り側にあるか、反時計回り側にあるか。
	 * プラスなら反時計回り。
	 * マイナスなら時計回り。
	 * ０なら同一直線上にある
	 */
	public double crossProduct(P p1, P p2, P p3){
		double ret = (p2.x - p1.x) * (p3.y - p1.y) - (p2.y - p1.y) * (p3.x - p1.x);
		if(Math.abs(ret) < EPS){
			ret = 0;
		}
		return ret;
	}
	
	/**
	 * p3がp1からp2に引いた先の、時計回り側にあるか、反時計回り側にあるか。
	 * プラスなら反時計回り。
	 * マイナスなら時計回り。
	 * ０なら同一直線上にある
	 */
	public double crossProduct(double[] p1, double[] p2, double[]p3){
		double ret = (p2[0] - p1[0]) * (p3[1] - p1[1]) - (p2[1] - p1[1]) * (p3[0] - p1[0]);
		
		if(Math.abs(ret) < EPS){
			ret = 0;
		}
		return ret;
	}
	
	/**
	 * 多角形の面積
	 * @param g
	 * @return
	 */
	public static double area(G g){
		List<P> ps = g.ps;
		int n = ps.size();
		double s = 0;
		for(int i = 0; i < n -1; i++){
			P p1 = ps.get(i);
			P p2 = ps.get(i+1);
			s +=  (p1.x*p2.y - p2.x * p1.y ) / 2;
		}
		P p1 = ps.get(n-1);
		P p2 = ps.get(0);
		s +=  (p1.x*p2.y - p2.x * p1.y ) / 2;
		
		return Math.abs(s);
	}
	
	/**
	 * 多角形を線分で切った時、線分l (a, b)からみて、左側（反時計回り側）を残す　
	 * @param g
	 * @param l
	 * @return
	 */
	public G convex_cut(G g, L l) {
		  G Q = new G();
		  List<P> ps = g.ps;
		  for(int i = 0; i < g.ps.size(); i++) {
		    P A = ps.get(i);
		    P B = i == ps.size() -1 ? ps.get(0) : ps.get(i+1);
		    if (ccw(l.a, l.b, A) != -1) Q.add(A);
		    if (ccw(l.a, l.b, A)*ccw(l.a, l.b, B) < 0)
		      Q.ps.add(crosspoint(new L(A, B), l));
		  }
		  return Q;
	}
	
	/**
	 * 垂直二等分線
	 * a->bを半時計回りに９０度回転した向き。これで凸多角形を切断すれば、点aを含む方を残すことが出来る。
	 * @return
	 */
	public L bisector(P a, P b) {
		P p = new P(0.5, 0);
		P A = (a.add(b)).mul(p);
		P B = A.add(b.subtract(a).mul(new P(0, Math.PI/2)));
		return new L(A, B);
	}
	
	/**
	 * 線分同士の交点
	 */
	public P crosspoint(L l, L m) {
		  double A = cross(vectr(l.b, l.a), vectr(m.b, m.a));
		  double B = cross(vectr(l.b, l.a), vectr(l.b, m.a));
		  if (marume(A) == 0 && marume(B) == 0) return m.a; // same line
		  if (marume(A) == 0) return null; // !!!PRECONDITION NOT SATISFIED!!!
		  P vm = vectr(m.b, m.a);
		  P p = new P(m.a);
		  p.x += B/A * vm.x;
		  p.y += B/A * vm.y;
		  return p;
	}
	
	// 偏角
	public double arg(P p){
		return Math.atan2(p.y, p.x);
	}

	
	public double angle(L l){
		P p = l.b.subtract(l.a);
		return angle(p);
	}
	
	public double angle(P p){
		double s = Math.acos(Math.abs(p.x)/Math.sqrt(p.x*p.x+p.y*p.y));
		if(p.x >= 0 && p.y >= 0){
			return s;
		}
		else if(p.x < 0 && p.y >= 0){
			return Math.PI - s;
		}
		else if(p.x < 0 && p.y < 0){
			return Math.PI + s;
		}
		else{
			return Math.PI * 2 - s;
		}
	}
	
	// ボロノイ領域
	public G voronoi_cell(G g, List<P> v, int s) {
	  for(int i = 0; i < v.size(); i++)
	    if (i!=s)
	      g = convex_cut(g, bisector(v.get(s), v.get(i)));
	  return g;
	}
	
	// 角度関連
	
	 // ベクトルａからみたベクトルｂの角度の計算[0,2pi)
	public double angle(P a, P b) {       
	  double ret = angle(b)-angle(a);
	  return (ret>=0) ? ret : ret + 2 * Math.PI;
	}
	
	// ベクトルaとベクトルbの間の角度で小さい方
	double angle2(P a, P b) { 
	  return Math.min(angle(a,b), angle(b,a));
	}
	
	// ラジアン→度 標準ライブラリ使う
	double rtod(double rad) {       
	  return Math.toDegrees(rad);
	}
	
	// 度→ラジアン 標準ライブラリを使う
	double dtor(double deg) {       
	  return Math.toRadians(deg);
	}
	
	// ベクトルの回転
	P rotate(P p, double angrad) {
		P k = new P(Math.cos(angrad), Math.sin(angrad));
	  return p.mul(k);
	}
	
	// 原点周りの直線の回転
	L rotate(L l, double ang) {
	  return new L(rotate(l.a, ang), rotate(l.b, ang));
	}
	
	/**
	 * 多角形の摂動変形 (右（内側）にずらして縮小する）
	 * 反時計回りの多角形の各辺を len だけ右に平行移動した多角形を作る．位相的破綻に弱いアルゴリズムの補強用に用いると便利．
	 * P は反時計回りにしておく．このとき len は右方向のずらし量になる．ずらしすぎると摂動変形自体が位相的に破綻するので len は十分小さく取ること．
	 * @param g
	 * @param len
	 * @return
	 */
	G shrink_polygon(G g, double len) {
		  G res = new G();
		  for (int i = 0; i < g.size(); ++i) {
		    P a = prev(g,i), b = curr(g,i), c = next(g,i);
		    P u = (b.subtract(a)).divide(abs(b.subtract(a)));
		    double th = arg((c.subtract(b)).divide(u)) * 0.5;
		    P t = new P(-Math.sin(th), Math.cos(th));
		    res.add( b.add(u.mul(t).mul(len).divide(Math.cos(th))) );
		  }
		  return res;
	}


	
	/**
	 * 
	 * OUT 0
	 * On 1
	 * In 2
	 */
	public int contains(G g, P p) {
		  boolean in = false;
		  for (int i = 0; i < g.size(); ++i) {
		    P a = curr(g,i).subtract(p);
		    P b = next(g,i).subtract(p);
		    if (imag(a) > imag(b)){
		    	P tmp = a;
		    	a = b;
		    	b = tmp;
		    }
		    if (imag(a) <= 0 && 0 < imag(b))
		      if (cross(a, b) < 0) in = !in;
		    if (cross(a, b) == 0 && dot(a, b) <= 0) return 1;
		  }
		  return in ? 2 : 0;
	}

	/**
	 * 凸包
	 * 点列を x 座標でソートした後，下側凸包と上側凸包を別々に求め，併合して凸包を構成する．
	 * 下側凸包を求めるときは点集合を左から右に見ていき，進行方向が左側であるような
	 * 最も右よりの点を取っていく．上側凸包の場合は右から左に見ていく以外は全く同一である．
	 * 進行方向に対して同一直線上にある場合は，最も近いものを取ることで凸包上にある点を取りこぼすことなく
	 * 凸包に入れることができる．
	 */
	public List<P> convec_hull(P[] ps){
		List<P> list = new ArrayList<P>();
		int n = ps.length;
		Arrays.sort(ps, new Comparator<P>() {
			public int compare(P o1, P o2) {
				if(o1.x == o2.x){
					if(o1.y > o2.y) return -1;
					else if(o1.y < o2.y)return 1; 
					return 0;
				}
				if(o1.x < o2.x)return -1;	
				return 1;
			}
		});
		list.add(ps[0]);
		P lp = ps[0];
		for(int i = 1; i < n; i++){
			P p = ps[i];

			int k = list.size()-2;

			while(k >= 0 && ccw(list.get(k), p, list.get(k+1)) < 0){
				list.remove(k+1);
				k--;
			}
				
			if(i != n-1) list.add(p);
		}
		list.add(ps[n-1]);
		int t = list.size()-1;
		for(int i = n-2; i >= 0; i--){
			P p = ps[i];

			int k = list.size()-2;
			
			while(k >= t && ccw(list.get(k), p, list.get(k+1)) < 0){
				list.remove(k+1);
				k--;
			}
			if(i != 0) list.add(p);
		}
		
		return list;
	}
	

}