package yarou;



import java.util.*;
import java.io.*;
import static java.lang.Math.*;

// TLE
// 左から単純に見ていくだけなのでもっと良い手法を考える
/*
 * 
 * 
 komiyam氏の解法
 平面走査する。イベントラインは区間への加算をサポートするRMQで持っておけばよい。つまり、点(x,y)を追加するとき[y,y+H)に書かれている数値を足し込む。削除する時は負の値を足し込めばよい。区間加算をサポートするRMQは、区間内に一様に足された数とそれを除いた最大値を各節点で持つようにすればよい。
 */
public class POJ2482 {

	static PrintStream out = null;
	int INF = Integer.MAX_VALUE / 10;
	static Scanner sc = null;
	double DF = 0.000000000001;

	static int N = 0;
	static int W = 0;
	static int H = 0;
	public void solve() throws Exception{
		Data[] dx = new Data[N];
		Data[] dy = new Data[N];
		for(int i = 0; i < N; i++){
			int x = sc.nextInt();
			int y = sc.nextInt();
			int c = sc.nextInt();
			dx[i] = new Data(x, y, c);
			dy[i] = new Data(x, y, c);
		}
		Arrays.sort(dx, new Comparator<Data>() {
			@Override
			public int compare(Data o1, Data o2) {
				if(o1.x != o2.x){
					return o1.x - o2.x;
				}
				return o1.y - o2.y;
			}
		});
		Arrays.sort(dx, new Comparator<Data>() {
			@Override
			public int compare(Data o1, Data o2) {
				if(o1.y != o2.y){
					return o1.y - o2.y;
				}
				return o1.x - o2.x;
			}
		});
		
		int ans = 0;
		
		for(int i = 0; i < dx.length; i++){
			Data xd = dx[i];
			int x = xd.x;
			int y = xd.y;
			for(int j = 0; j < dy.length; j++){
				Data yd = dy[j];
				boolean bf = false;
				int sx = x;
				int ex = x + W -1;
				int sy = 0;
				int ey = 0;
				if(yd.x < sx || yd.x > ex){
					continue;
				}
				if(yd.y >= y){
					bf = true;
					sy = y;
				}
				else if(abs(yd.y - y) >= H){
					continue;
				}
				else{
					sy = yd.y;
				}
				ey = sy + H -1;
				int sum = 0;
				for(int k = j; k < dy.length; k++){
					Data cd = dy[k];
					if(cd.x >= sx && cd.x <= ex && cd.y >= sy && cd.y <= ey){
						sum += cd.c;
					}
				}
				ans = max(ans, sum);
				
				if(bf){
					break;
				}
			}
		}
		out.println(ans);
	}

	class Data{
		
		public Data(int x, int y, int c){
			this.x = x;
			this.y = y;
			this.c = c;
		}
		
		int x = 0;
		int y = 0;
		int c = 0;
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
		POJ2482 p = new POJ2482();
		while(true){
			
			try{
				N = sc.nextInt();
				W = sc.nextInt();
				H = sc.nextInt();
			}
			catch(Exception ex){
				break;
			}
			if(N == 0 && W == 0 && H == 0){
				break;
			}
			p.solve();
		}
	}

}
