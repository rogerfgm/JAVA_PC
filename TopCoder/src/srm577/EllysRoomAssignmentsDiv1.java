package srm577;
import java.util.Arrays;
import java.util.Comparator;


public class EllysRoomAssignmentsDiv1 {

	public double getAverage(String[] r){
		String s = "";
		for(int i = 0; i < r.length; i++){
			s += r[i];
		}
		String[] ss = s.split(" ");
		int N = ss.length;

		Integer[] d = new Integer[N];
		for(int i = 0; i < ss.length; i++){
			d[i] = Integer.parseInt(ss[i]);
		}
		int el = d[0];
		Arrays.sort(d, new Comparator<Integer>() {

			public int compare(Integer arg0, Integer arg1) {
				int o1 = arg0.intValue();
				int o2 = arg1.intValue();
				return o2 - o1;
			}
		});
		int R = d.length / 20;
		if(d.length % 20 > 0){
			R++;
		}
		
		double S = 0;
		int kai = N / R;
		boolean F = false;
		for(int i = 0; i < kai; i++){
			int st = i * R;
			double sum = 0;
			boolean f = false;
			for(int j = 0; j < R; j++){
				int idx = st + j;
				sum += d[idx];
				if(d[idx] == el){
					f = true;
					F = true;
				}
			}
			if(f){
				S += el;
			}
			else{
				S += sum / R;
			}
		}
		double ans = 0;
		
		if(N % R == 0){
			int nin = N / R;
			ans = S / nin ;
		}
		else{
			if(!F){
				S += el;
				ans = S / (N/R + 1);
			}
			else{
				double amari = N % R;
				for(int i = 0; i < amari; i++){
					int idx = R * (N/R) + i;
					ans += (S + d[idx]) / (N/R + 1) / R;
				}
				ans += S /(N/R) * (R - amari) / R;
			}
		}
		return ans;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EllysRoomAssignmentsDiv1 e = new EllysRoomAssignmentsDiv1();
		String[] ss = {"1924 1242 1213 1217 2399 1777 2201 2301 1683 2045 ", "1396 2363 1560 2185 1496 2244 2117 2207 2098 1319 ",
				 "2216 1223 1256 2359 2394 1572 2151 2191 2147 2253 ", "1633 2217 2211 1591 1310 1209 1430 1445 1988 2030 ",
		 "1947 1202 1203"};
		
//		String[] ss = {"3380 3413 3254 3515 2885 2946 2790 3140"};
		double ret = e.getAverage(ss);
		System.out.println(ret);

	}

}
