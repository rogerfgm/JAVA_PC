package d186;



import java.io.*;
import java.util.*;
import java.math.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;


public class C186 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	static BufferedWriter bw = null;
	int N = 0;
	
	public void solve() throws Exception{
		int n4 = parseInt(br.readLine());
		String s = br.readLine();
		String[] sp = s.split(" ");
		long[] d = new long[sp.length];
		for(int i = 0; i < sp.length; i++){
			d[i] = parseInt(sp[i]);
		}
		Arrays.sort(d);
		long ans = 0;
		long sum = 0;
		
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 0; ; i++){
			int a = (int)pow(4, i);
			list.add(a);
			if(a == n4){
				break;
			}
		}
		
		int num = 0;
		for(int i = 0; i < d.length; i++){
			int idx = d.length - 1 - i;
			sum += d[idx];
			num++;
			if(num == list.get(0).intValue()){
				ans += sum;
				list.remove(0);
			}
		}
		out.println(ans);
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
		out = System.out;
		bw = new BufferedWriter(new PrintWriter(out));
		//sc =  new Scanner(System.in);
		br = new BufferedReader(new InputStreamReader(System.in));
		C186 t = new C186();
		t.solve();
		bw.close();
	}

}
