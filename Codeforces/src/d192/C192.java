package d192;



import java.io.*;
import java.util.*;
import java.math.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;




public class C192 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	static BufferedWriter bw = null;
	int N = 0;
	
	public void solve() throws Exception{
		int n = nextInt();
		boolean[][] f = new boolean[n][n];
		for(int i = 0; i < n; i++){
			String s = br.readLine();
			for(int j = 0; j < n; j++){
				if(s.charAt(j) == '.'){
					f[i][j] = true;
				}
			}
		}
		List<String> ans = new ArrayList<String>();
		boolean flag = true;
		for(int i = 0; i < n; i++){
			boolean fnd = false;
			for(int j = 0; j < n; j++){
				if(f[i][j]){
					fnd = true;
					int r = i + 1;
					int c = j + 1;
					ans.add(r + " " + c);
					break;
				}
			}
			if(!fnd){
				flag = false;
				break;
			}
		}
		if(!flag){
			flag = true;
			ans = new ArrayList<String>();
			for(int i = 0; i < n; i++){
				boolean fnd = false;
				for(int j = 0; j < n; j++){
					if(f[j][i]){
						fnd = true;
						int r = j + 1;
						int c = i + 1;
						ans.add(r + " " + c);
						break;
					}
				}
				if(!fnd){
					flag = false;
					break;
				}
			}
		}
		if(flag){
			for(int i = 0; i < ans.size(); i++){
				out.println(ans.get(i));
			}
		}
		else{
			out.println("-1");
		}
	
		
	}
	

    public int[] readIntArray(int n) {
        int[] ret = new int[n];
        for (int i = 0; i < n; i++) {
            ret[i] = sc.nextInt();
        }
        return ret;
    }

	private int nextInt() throws IOException{
		String s = br.readLine();
		return parseInt(s);
	}
	
	private int[] nextInts() throws IOException{
		String s = br.readLine();
		String[] sp = s.split(" ");
		int[] r = new int[sp.length];
		for(int i = 0;i < sp.length; i++){
			r[i] = parseInt(sp[i]);
		}
		return r;
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
		C192 t = new C192();
		t.solve();
		bw.close();
	}

}
