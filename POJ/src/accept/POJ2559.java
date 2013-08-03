package accept;

import java.io.*;
import java.util.*;




public class POJ2559 {

	
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;

	
	
	public void solve() throws Exception{

		
		while(true){
			int n = sc.nextInt();
			if(n == 0){
				break;
			}
			int[] d= readIntArray(n);
			List<Data> list = new ArrayList<Data>();
			long ans = 0;
			for(int i = 0; i < d.length; i++){
				long h = d[i];
				if(list.size() > 0 && list.get(list.size()-1).h > h){
					int idx = list.get(list.size()-1).idx;
					while(list.size() > 0 && list.get(list.size()-1).h > h){
						Data data = list.remove(list.size()-1);
						ans = Math.max(ans, data.h * (i-data.idx));
						idx = data.idx;
					}
					if(list.size() > 0 && list.get(list.size()-1).h < h || list.size() == 0){
						Data data = new Data();
						data.h = d[i];
						data.idx = idx;
						list.add(data);
					}
				}
				else if(list.size() == 0 || list.get(list.size() -1).h < h){
					Data data = new Data();
					data.h = d[i];
					data.idx = i;
					list.add(data);
				}
			}
			for(int i = 0; i < list.size(); i++){
				ans = Math.max(ans, list.get(i).h * (n-list.get(i).idx));
			}
			System.out.println(ans);
		}
	}
	
	class Data{
		int idx = 0;
		long h = 0;
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
		POJ2559 t = new POJ2559();
		t.solve();

	}

}
