package old;
import java.util.Arrays;


public class RollingDiceDivTwo {

	public int minimumFaces(String[] r){
		int[] a = new int[r[0].length()];
		
		for(int i =0; i < r.length; i++){
			String s = r[i];
			int[] aa = new int[s.length()];
			for(int j = 0; j < s.length(); j++){
				aa[j] = s.charAt(j) - '0';
			}
			Arrays.sort(aa);
			for(int j = 0; j < s.length(); j++){
				a[j] = Math.max(a[j], aa[j]);
			}
		}
		int ans = 0;
		for(int i = 0; i < a.length; i++){
			ans += a[i];
		}
		
		return ans;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RollingDiceDivTwo r = new RollingDiceDivTwo();
		String[] ro = {"1112", "1111", "1211", "1111"};
		int a = r.minimumFaces(ro);
		System.out.println(a);
	}

}
