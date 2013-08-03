package srm570;
import java.util.Arrays;


public class RobotHerb {

	public long getdist(int T, int[] a){
		long ans = 0;
		int num = 0;
		long x = 0;
		long y = 0;
		int[][] dir = new int[4][2];
		dir[0][0] = 0;
		dir[0][1] = 1;
		dir[1][0] = 1;
		dir[1][1] = 0;
		dir[2][0] = 0;
		dir[2][1] = -1;
		dir[3][0] = -1;
		dir[3][1] = 0;
		int d = 0;
		while(T > 0){
			for(int i = 0; i < a.length; i++){
				x += a[i] * dir[d][0];
				y += a[i] * dir[d][1];
				d += a[i];
				d %= 4;
			}
			num++;
			T--;
			if(T == 0){
				break;
			}
			if(d == 0){
				int wari = T / num;
				if(wari > 0){
					T %= num;
					wari++;
					x *= wari;
					y *= wari;
				}
			}
			
		}
		
		return Math.abs(x) + Math.abs(y);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RobotHerb r = new RobotHerb();
		int[] in = {2013,2,13,314,271,1414,1732};
		long ret = r.getdist(570, in);
		System.out.println(ret);
	}

}
