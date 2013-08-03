package giveup;


import java.util.*;
import java.awt.geom.Line2D;
import java.io.*;



import static java.lang.Math.*;

// WA
//凸包して、Lはなれたpoligonと、頂点部分は円の長さになる
// 円はL離れたpoligonと元のpoligonの線分の交点が円と線分の境目になるという風にやったらwa
public class POJ1113 {

	static PrintStream out = null;
	int INF = Integer.MAX_VALUE / 10;
	static Scanner sc = null;
	double DF = 0.000000000001;

	static int N = 0;
	static int L = 0;
	public void solve() throws Exception{
		P[] ps = new P[N];
		for(int i = 0; i < N; i++){
			int x = sc.nextInt();
			int y = sc.nextInt();
			ps[i] = new P(x, y);
		}
		List<P> list = convec_hull(ps);

		List<L> ls = new ArrayList<L>();
		for(int i = 0; i < list.size(); i++){
			P a = list.get(i);
			P b = list.get((i+1)%list.size());
			P vec = vectr(a, b);
			double len = abs(vec);
			vec = vec.mul(L/len);
			vec = rotate(vec, toRadians(90));
			L l = new L(a.add(vec), b.add(vec));
			ls.add(l);
		}
		
		if(ls.size() == 3){
			P a = crosspoint(ls.get(0), ls.get(1));
			P b = crosspoint(ls.get(1), ls.get(2));
			P c = crosspoint(ls.get(0), ls.get(2));
			
			double ans = dist(a, b) + dist(b, c) + dist(c, a);
			int ret = (int)(ans + 0.5);
			out.println(ret);
			return;
		}
		
		int n = ls.size();
		P first = null;
		P prev = null;
		P last = null;
		double ans = 0;
		for(int i = 0; i < ls.size(); i++){
			P a = list.get(i);
			P b = list.get((i+1)%n);
			P c = list.get((i+2)%n);
			
			L l1 = ls.get(i);
			L l2 = ls.get((i+1)%n);
			
			L lbc = new L(b, c);
			L lab = new L(a, b);
			
			P ap = crosspoint(l1, lbc);
			P bp = crosspoint(lab, l2);
			
			if(prev != null){
				ans += dist(prev, ap);
				prev = bp;
			}
			else{
				first = ap;
			}
			prev = bp;
			last = bp;
			
			ap = ap.subtract(b);
			bp = bp.subtract(b);
			double ag = angle(ap) - angle(bp);
			if(ag < 0) ag += 2*PI;
			ag %= 2*PI;
			
			
			ans += 2 * PI * L  * ag / (2*PI);
		}
		
		ans += dist(first, last);
		
		int ret = (int)(ans + 0.5);

		
		out.println(ret);
	}


	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		out = System.out;
		File file = new File("input.txt");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream("input.txt")));
		}
		sc =  new Scanner(System.in);
		POJ1113 p = new POJ1113();
		while(true){
			
			try{
				N = sc.nextInt();
				L = sc.nextInt();
				if(N == 0 && L == 0){
					break;
				}
			}
			catch(Exception ex){
				break;
			}
		
			p.solve();
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
	
	class C{
		P p;
		double r = 0;
		
		public C(P p, double r){
			this.p = p;
			this.r = r;
		}
		
		public C(double x, double y, double r){
			p = new P(x, y);
			this.r = r;
		}
		
		boolean contain(C c){
			if(dist(c) + c.r + EPS <= r){
				return true;
			}
			return false;
		}
		
		double dist(C c){
			double dx = c.p.x - p.x;
			double dy = c.p.y - p.y;
			return sqrt(dx * dx + dy * dy);
		}
	}
	
	// 円の交点
	List<P> cc_cross(C c1, C c2) {
		List<P> res = new ArrayList<P>();
	    double d = dist(c1.p, c2.p);
	    if(d > c1.r + c2.r + EPS){
	    	return res;
	    }
	    
	    boolean cf = true;
	    if(Math.abs(d - (c1.r + c2.r)) <= EPS){
	    	cf = false;
	    }
	    
	    double rc = (d*d + c1.r*c1.r - c2.r*c2.r) / (2*d);
	    double rs = sqrt(c1.r*c1.r - rc*rc);
	 
	    P diff = vectr(c1.p, c2.p).divide(d);

	    res.add(c1.p.add(diff.mul(new P(rc, rs))));
	    if(cf) res.add(c1.p.add(diff.mul(new P(rc, -rs))));
	    return res;
	}
	
	// 円と直線の交点
	List<P> cl_cross(C c, L l) {
		  P h = projection(l, c.p);
		  double d = dist(h,  c.p);
		  List<P> res = new ArrayList<P>();
		  if(d < c.r - EPS){
		    P x = vectr(l.a, l.b).divide(dist(l.a, l.b)).mul(sqrt(c.r*c.r - d*d));
		    res.add(h.add(x));
		    res.add(h.subtract(x));
		  }else if(d < c.r + EPS){
		    res.add(h);
		  }
		  return res;
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
	public P vectr(P a, P b){
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
		P p = vectr(a, b);
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
		
		b = vectr(a, b);
		c = vectr(a, c);

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
	
	// 三角形の面積
	public double area(P p1, P p2, P p3){
		return menseki3Hen(dist(p1, p2), dist(p1, p3), dist(p2, p3));
	}
	
	public double menseki3Hen(double a, double b, double c){
		double s = (a + b + c) /2;
		s = s * (s-a)*(s-b)*(s-c);
		return sqrt(s);
	}
	
	/**
	 * 多角形の面積
	 * @param g
	 * @return
	 */
	public double area(G g){
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
	
	// 円の交差部分の面積
	double cc_area(C c1, C c2) {
	    double d = c1.dist(c2);
	    if (c1.r + c2.r <= d + EPS) {
	        return 0.0;
	    } else if (d <= Math.abs(c1.r - c2.r) + EPS) {
	        double r = min(c1.r, c2.r);
	        return r * r * PI;
	    } else {
	        double rc = (d*d + c1.r*c1.r - c2.r*c2.r) / (2*d);
	        double theta = acos(rc / c1.r);
	        double phi = acos((d - rc) / c2.r);
	        return c1.r*c1.r*theta + c2.r*c2.r*phi - d*c1.r*sin(theta);
	    }
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
		  double A = cross(vectr(l.a, l.b), vectr(m.a, m.b));
		  double B = cross(vectr(l.a, l.b), vectr(m.a, l.b));
		  if (marume(A) == 0 && marume(B) == 0) return m.a; // same line
		  if (marume(A) == 0) return null; // !!!PRECONDITION NOT SATISFIED!!!
		  P vm = vectr(m.a, m.b);
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
	 * TODO: 未検証
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
	
	// ただcontainsをよんでいるだけ
	public int convex_contains(G g, P p){
		return contains(g, p);
	}

	/**
	 * 凸包
	 * 点列を x 座標でソートした後，下側凸包と上側凸包を別々に求め，併合して凸包を構成する．
	 * 下側凸包を求めるときは点集合を左から右に見ていき，進行方向が左側であるような
	 * 最も右よりの点を取っていく．上側凸包の場合は右から左に見ていく以外は全く同一である．
	 * AOJ0068
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
		for(int i = 1; i < n; i++){
			P p = ps[i];

			int k = list.size()-2;

			while(k >= 0 && ccw(list.get(k), p, list.get(k+1)) <= 0){
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
			
			while(k >= t && ccw(list.get(k), p, list.get(k+1)) <= 0){
				list.remove(k+1);
				k--;
			}
			if(i != 0) list.add(p);
		}
		
		return list;
	}
	
	/**
	 * 凸角形同士の共通部分
	 * 無駄に配列にコピーしているので、出来れば配列のほうの関数を使う
	 * @param g1
	 * @param g2
	 * @return
	 */
	public G interCovex(G g1, G g2){
		double[][] d1 = new double[g1.size()][2];
		double[][] d2 = new double[g2.size()][2];
		for(int i = 0; i < g1.size(); i++){
			d1[i][0] = g1.get(i).x;
			d1[i][0] = g1.get(i).y;
		}
		for(int i = 0; i < g2.size(); i++){
			d2[i][0] = g2.get(i).x;
			d2[i][0] = g2.get(i).y;
		}
		double[][] d = interConvex(d1, d2);
		G g = new G();
		for(int i = 0; i < d.length; i++){
			g.add(d[i][0], d[i][1]);
		}
		return g;
	}
	
	
//	/**
//	 * convex_intersectのヘルパー関数。
//	 * 判定のほか、rに値を設定する
//	 * @return
//	 */
//	private boolean intersect_1pt(P a, P b, P c, P d, P r) {
//		double D = cross(b.subtract(a), d.subtract(c));
//		if (marume(D) == 0) return false;
//		double t =  cross(c.subtract(a), d.subtract(c)) / D;
//		double s = -cross(a.subtract(c), b.subtract(a)) / D;
//		P nr = a.add( (b.subtract(a)).mul(t) );
//		r.x = nr.x;
//		r.y = nr.y;
//		return t >= 0 && t <= 1 && s >= 0 && s <= 1;
//	}
//	
//	/**
//	 * 凸多角形の共通部分
//	 * TODO : 検証 (簡単なテストでダメだからコメントアウトしておく）
//	 * @param P
//	 * @param Q
//	 * @return
//	 */
//	public G convex_intersect(G P, G Q) {
//		int n = P.size(), m = Q.size();
//		int a = 0, b = 0, aa = 0, ba = 0;
//		int Pin = 0, Qin = 1, Unknown = 2;
//		int in = Unknown;
//		
//		G R = new G();
//		  
//		  do {
//		    int a1 = (a+n-1) % n, b1 = (b+m-1) % m;
//		    double C = cross(P.get(a).subtract(P.get(a1)), Q.get(b).subtract(Q.get(b1)));
//		    double A = cross(P.get(a1).subtract(Q.get(b)), P.get(a).subtract(Q.get(b)));
//		    double B = cross(Q.get(b1).subtract(P.get(a)), Q.get(b).subtract(P.get(a)));
//		    P r = new P();
//		    if (intersect_1pt(P.get(a1), P.get(a), Q.get(b1), Q.get(b), r)) {
//		      if (in == Unknown) aa = ba = 0;
//		      R.add( r );
//		      in = B > 0 ? Pin : A > 0 ? Qin : in;
//		    }
//		    if (C == 0 && B == 0 && A == 0) {
//		      if (in == Pin) { b = (b + 1) % m; ++ba; }
//		      else           { a = (a + 1) % m; ++aa; }
//		    } else if (C >= 0) {
//		      if (A > 0) { if (in == Pin) R.add((P.get(a))); a = (a+1)%n; ++aa; }
//		      else       { if (in == Qin) R.add(Q.get(b)); b = (b+1)%m; ++ba; }
//		    } else {
//		      if (B > 0) { if (in == Qin) R.add(Q.get(b)); b = (b+1)%m; ++ba; }
//		      else       { if (in == Pin) R.add(P.get(a)); a = (a+1)%n; ++aa; }
//		    }
//		  } while ( (aa < n || ba < m) && aa < 2*n && ba < 2*m );
//		  if (in == Unknown) {
//		    if (convex_contains(Q, P.get(0)) > 0) return P;
//		    if (convex_contains(P, Q.get(0)) > 0) return Q;
//		  }
//		  return R;
//	}
//
//	/**
//	 * 凸多角形の共通部分
//	 * @param P
//	 * @param Q
//	 * @return
//	 */
//	public G convex_common(G P, G Q){
//		return convex_intersect(P, Q);
//	}

	
	private double extreme_help_dot(P p, P l){
		return dot(p, l);
	}
	
	
	/**
	 * aからbに向かった方向に一番遠い点？
	 * extreme は点集合 P と直線 l が与えられたとき，点集合の中で l の方向に最も遠い点を求める．これを行うには，すべての点について l へ射影したときの l 上の距離を比較することになる．入力が凸多角形の場合はそれに特化した凸多角形の端点を用いることで，より高速に計算できる．
	 * TODO:検証
	 * @return
	 */
	public P extreme(List<P> ps, L l) {
	  int k = 0;
	  P pl = l.b.subtract(l.a);
	  for (int i = 1; i < ps.size(); ++i)
	    if (extreme_help_dot(ps.get(i), pl) > extreme_help_dot(ps.get(k), pl)) k = i;
	  return ps.get(k);
	}

	public double areaPoly(double[][] co)
	{
		int n = co.length;
		double s = 0;
		for(int i = 0;i < n - 1;i++){
			s += (co[i][0]*co[i+1][1]-co[i+1][0]*co[i][1]) / 2;
		}
		s += (co[n-1][0]*co[0][1]-co[0][0]*co[n-1][1]) / 2;
		return Math.abs(s);
	}
	
	
	public double[] interLines(double ax, double ay, double bx, double by, double cx, double cy, double dx, double dy)
	{
		double eps = 1E-9;
		double aa = bx - ax;
		double cc = by - ay;
		double bb = cx - dx;
		double dd = cy - dy;
		double xx = cx - ax;
		double yy = cy - ay;
		double det = aa * dd - bb * cc;
		if(Math.abs(det) < eps)return null;
		double t = (dd * xx - bb * yy) / det;
		double u = (-cc * xx + aa * yy) / det;
		return new double[]{ax + (bx - ax) * t, ay + (by - ay) * t, t, u};
	}
	
	public double[][] interConvex(double[][] co1, double[][] co2){
		double EPS = 1E-9;
		int m = co1.length;
		int n = co2.length;
		double[] cx = new double[2*(m+n)];
		double[] cy = new double[2*(m+n)];
		int p = 0;
		for(int i = 0;i < m;i++){
			for(int j = 0;j < n;j++){
				int ni = (i+1)%m;
				int nj = (j+1)%n;
				double[] il = interLines(
						co1[i][0], co1[i][1], co1[ni][0], co1[ni][1],
						co2[j][0], co2[j][1], co2[nj][0], co2[nj][1]
								);
				if(il != null && il[2] > -EPS && il[2] < 1+EPS && il[3] > -EPS && il[3] < 1+EPS){
					cx[p] = il[0];
					cy[p] = il[1];
					p++;
				}
			}
		}
		for(int i = 0;i < m;i++){
			if(isOnIn(co1[i][0], co1[i][1], co2)){
				cx[p] = co1[i][0];
				cy[p] = co1[i][1];
				p++;
			}
		}
		for(int i = 0;i < n;i++){
			if(isOnIn(co2[i][0], co2[i][1], co1)){
				cx[p] = co2[i][0];
				cy[p] = co2[i][1];
				p++;
			}
		}
		
		cx = Arrays.copyOf(cx, p);
		cy = Arrays.copyOf(cy, p);
		convexSort(cx, cy);
		
		double[][] rc = new double[p][];
		for(int i = 0;i < p;i++){
			rc[i] = new double[]{cx[i], cy[i]};
		}
		return rc;
	}
	
	public void convexSort(double[] sx, double[] sy)
	{
		double gx = 0, gy = 0;
		for(double x : sx)gx += x;
		for(double y : sy)gy += y;
		int n = sx.length;
		gx /= n; gy /= n;
		
		final double[] th = new double[n];
		for(int i = 0;i < n;i++){
			th[i] = Math.atan2(sy[i]-gy, sx[i]-gx);
		}
		Integer[] ord = new Integer[n];
		for(int i = 0;i < n;i++)ord[i] = i;
		Arrays.sort(ord, new Comparator<Integer>(){
			public int compare(Integer a, Integer b){
				return Double.compare(th[a], th[b]);
			}
		});
		
		double[] cx = new double[n];
		double[] cy = new double[n];
		System.arraycopy(sx, 0, cx, 0, n);
		System.arraycopy(sy, 0, cy, 0, n);
		for(int i = 0;i < n;i++){
			sx[i] = cx[ord[i]];
			sy[i] = cy[ord[i]];
		}
	}

	public boolean isOnIn(double x, double y, double[][] co){
		int n = co.length;
		int cc = Line2D.relativeCCW(co[n-1][0], co[n-1][1], co[0][0], co[0][1], x, y);
		if(cc == 0)return true;
		for(int i = 0;i < n-1;i++){
			int ccc = Line2D.relativeCCW(co[i][0], co[i][1], co[i+1][0], co[i+1][1], x, y);
			if(ccc == 0)return true;
			if(cc != ccc)return false;
		}
		return true;
	}
	
	
}
