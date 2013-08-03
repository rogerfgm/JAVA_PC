package srm573;
import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class TeamContest {

	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	public int worstRank(int[] st){
		int my = max(max(st[0], st[1]), st[2]) + min(min(st[0], st[1]), st[2]);
		int[] d = new int[st.length-3];
		for(int i = 3; i < st.length; i++){
			d[i-3] = st[i];
		}
		Arrays.sort(d);

		int N = d.length;
		boolean[] used = new boolean[N];
		for(int i = 0; i < N; i++){
			boolean f = false;
			if(used[i]) continue;
			for(int j = i+1; j < N; j++){
				if(!used[j]){
					if(d[j] + d[i] > my){
						for(int k = i+1; k < N; k++){
							if(k != j && !used[k]){
								used[i] = used[j] = used[k] = true;
								f = true;
								break;
							}
						}
					}
				}
				if(f){
					break;
				}
			}
		}
		int cnt = 0;
		for(int i = 0; i < N; i++){
			if(used[i]){
				cnt++;
			}
		}
		int ans = cnt / 3 + 1;
		return ans;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TeamContest t = new TeamContest();
		int[] in = {5, 7, 3, 5, 7, 3, 5, 7, 3};
		int ret = t.worstRank(in);
		out.println(ret);
	}

}
