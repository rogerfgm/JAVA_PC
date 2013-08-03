package gcj2013_2;
import java.io.*;
import java.util.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;

public class A {
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintWriter out = null;
	static PrintStream sysout = System.out;
	static Random rnd = new Random();

	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;

	long b = 1;
	int N = 0;
	int M = 0;

	long MOD = 1000002013;

	public void solve() throws Exception {
		N = sc.nextInt();
		M = sc.nextInt();

		long ans = 0;

		Pt[] pts = new Pt[M];
		for (int i = 0; i < M; i++) {
			Pt pt = new Pt();
			pt.f = sc.nextLong();
			pt.t = sc.nextLong();
			pt.n = sc.nextLong();
			pts[i] = pt;
		}

		Comparator<Pt> cmp = new Comparator<Pt>() {
			@Override
			public int compare(Pt o1, Pt o2) {
				if (o1.f < o2.f) {
					return -1;
				} else if (o1.f > o2.f) {
					return 1;
				}
				return 0;
			}
		};

		Arrays.sort(pts, cmp);

		long org = 0;

		List<Data> done = new ArrayList<Data>();
		List<Dt2> froms = new ArrayList<Dt2>();
		List<Dt2> tos = new ArrayList<Dt2>();

		for (int i = 0; i < pts.length; i++) {

			Pt pt = pts[i];
			long a = get(pt.f, pt.t);
			a %= MOD;
			a *= pt.n;
			a %= MOD;
			org += a;
			org %= MOD;
	

			while (tos.size() > 0) {
				if (tos.get(0).d < pt.f) {
					Dt2 from = froms.remove(0);
					Dt2 to = tos.remove(0);
					long nm = min(from.n, to.n);
					Data dt = new Data();
					dt.f = from.d;
					dt.t = to.d;
					dt.n = nm;
					done.add(dt);
					if (to.n > nm) {
						to.n -= nm;
						tos.add(0, to);
					}
					if (from.n > nm) {
						from.n -= nm;
						froms.add(0, from);
					}
				} else {
					break;
				}
			}
			Dt2 from = new Dt2();
			from.n = pts[i].n;
			from.d = pts[i].f;
			froms.add(0, from);

			Dt2 to = new Dt2();
			to.n = pts[i].n;
			to.d = pts[i].t;
			tos.add(to);

			Collections.sort(tos, new Comparator<Dt2>() {
				@Override
				public int compare(Dt2 o1, Dt2 o2) {
					if (o1.d < o2.d) {
						return -1;
					} else if (o1.d == o2.d) {
						return 0;
					}
					return 1;
				}
			});

		}

		while (tos.size() > 0) {

			Dt2 from = froms.remove(0);
			Dt2 to = tos.remove(0);
			long nm = min(from.n, to.n);
			Data dt = new Data();
			dt.f = from.d;
			dt.t = to.d;
			dt.n = nm;
			done.add(dt);
			if (to.n > nm) {
				to.n -= nm;
				tos.add(0, to);
			}
			if (from.n > nm) {
				from.n -= nm;
				froms.add(0, from);
			}

		}
		for (int i = 0; i < done.size(); i++) {
			Data dt = done.get(i);
			long a = get(dt.f, dt.t);
			a %= MOD;
			a *= dt.n;
			a %= MOD;
			ans += a;

			ans %= MOD;
		}
		ans = org - ans;
		if(ans < 0){
			ans += MOD;
		}
		out.println(ans);
	}

	long get(long f, long t) throws Exception {
		if (f > t) {
			throw new Exception("test");
		}
		long n = t - f;
		long a = n * N + (-n * n + n) / 2;
		return a;
	}

	class Data {
		long f = 0;
		long t = 0;
		long n = 0;
	}

	class Dt2 {
		long d = 0;
		long n = 0;
	}

	class Pt {
		long f = 0;
		long t = 0;
		long n = 0;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		File file = new File("A-large-practice.in");
		// File file = new File("input.txt");
		if (file.exists()) {
			System.setIn(new BufferedInputStream(new FileInputStream(file)));
		}
		sc = new Scanner(System.in);
		// br = new BufferedReader(new InputStreamReader(System.in));
		FileWriter fw = new FileWriter(new File("output.txt"));
		out = new PrintWriter(fw);

		A b = new A();
		int T = 0;
		if (sc != null) {
			T = sc.nextInt();
		} else {
			T = parseInt(br.readLine());
		}
		int t = 1;
		while (t <= T) {
			out.print("Case #" + t + ": ");
			b.solve();
			t++;
		}
		out.close();
		fw.close();
	}
}
