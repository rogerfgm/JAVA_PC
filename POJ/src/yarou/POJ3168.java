package yarou;



import java.util.*;
import java.io.*;
import static java.lang.Math.*;

// TLE
// 平面走査
// 左から見ていき、既存のと重なるかどうか判定する
public class POJ3168 {

	static PrintStream out = null;
	int INF = Integer.MAX_VALUE / 10;
	static Scanner sc = null;
	double DF = 0.000000000001;

	static int N = 0;
	public void solve() throws Exception{
		
		Data[] ds = new Data[N];
		for(int i = 0; i < N; i++){
			int lx = sc.nextInt();
			int ly = sc.nextInt();
			int rx = sc.nextInt();
			int ry = sc.nextInt();
			ds[i] = new Data(lx, ly, rx, ry, i);
		}
		Arrays.sort(ds, new Comparator<Data>() {
			@Override
			public int compare(Data o1, Data o2) {
				if(o1.lx == o2.lx){
					return o1.ly - o2.ly;
				}
				return o1.lx - o2.lx;
			}
		});
		

		List<Data> list = new ArrayList<Data>();
		int idx = 0;
		boolean[] del = new boolean[N];
		
		for(int x = 0; x <= 1000000; x++){
			while(idx < ds.length && ds[idx].lx == x){
				Data d = ds[idx++];
				for(int i = list.size()-1; i >= 0; i--){
					Data dt = list.get(i);
					if(dt.rx < x){
						list.remove(i);
					}
					else{
						if(dt.ly == d.ry){
							del[dt.idx] = del[d.idx]= true; 
						}
						else if(dt.ry == d.ly){
							del[dt.idx] = del[d.idx]= true; 
						}
						if(x == dt.rx){
							boolean f = false;
							if(dt.ry <= d.ry){
								if(dt.ry >= d.ly){
									f = true;
								}
							}
							else{
								if(d.ry >= dt.ly){
									f = true;
								}
							}
							del[dt.idx] = del[d.idx]= f; 
						}
					}
				}
				list.add(d);
			}
			if(idx >= ds.length){
				break;
			}
		}
		int ans = 0;
		for(int i = 0; i < N; i++){
			if(!del[i]){
				ans++;
			}
		}
		out.println(ans);
	}

	class Data{
		public Data(int lx, int ly, int rx, int ry, int idx){
			this.lx = lx;
			this.ly = ly;
			this.rx = rx;
			this.ry = ry;
			this.idx = idx;
		}
		int lx = 0;
		int ly = 0;
		int rx = 0;
		int ry = 0;
		int idx = 0;
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
		POJ3168 p = new POJ3168();
		while(true){
			
			try{
				N = sc.nextInt();

				if(N == 0 ){
					break;
				}
			}
			catch(Exception ex){
				break;
			}
		
			p.solve();
		}
	}

}
