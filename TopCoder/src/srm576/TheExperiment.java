package srm576;


import java.util.*;
import static java.lang.Math.*;

// [残りスポンジ数][タイプ][残っている開始index]でdp
// タイプは0, 1の２通りで、0のときはそこから上に被せなければならない。
// 1のときはかぶせる必要がないからindex以降どこからでも開始できる
public class TheExperiment {

	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	int MOD = 1000000009;
	int L = 0;
	int len = 0;
	int A = 0;
	int B = 0;
	int[][][] dp = null;
	int[] it = null;
	public int countPlacements(String[] its, int M, int L, int A, int B){
		this.L = L;
		this.A = A;
		this.B = B;
		String tp = "";
		for(int i = 0; i < its.length; i++){
			tp += its[i];
		}
		len = tp.length();
		it = new int[len+1];
		for(int i = 0; i < len; i++){
			char c = tp.charAt(i);
			it[i+1] = c - '0';
		}
		
		dp = new int[M+1][2][len+1];
		for(int i = 0; i <= M; i++){
			for(int k = 0; k < 2; k++){
				for(int j = 0; j <= len; j++){
					dp[i][k][j] = -1;
				}
			}
		}
		
		int ret = 0;
		for(int i = 1; i <= L; i++){
			int sum = 0;
			for(int j = 1; j <= i; j++){
				sum += it[j];
			}
			if(sum >= A && sum <= B){
				int type = 0;
				if(i == L){
					type = 1;
				}
				ret += cnt(M-1, type, i+1);
				ret %= MOD;
			}
		}
		
		for(int i = 2; i <= len - L + 1; i++){
			int sum = 0;
			for(int j = i; j <= len && j - i + 1 <= L; j++){
				sum += it[j];
				if(sum >= A && sum <= B){
					int ntype = 0;
					if(j -i + 1 == L){
						ntype = 1;
					}
					ret += cnt(M-1, ntype, j+1);
					ret %= MOD;
				}
			}
		}
		
		
		return ret;
	}
	
	int cnt(int rem, int type, int idx){
		if(rem > len - idx + 1){
			return 0;
		}
		if(rem == 0 && type == 1){
			return 1;
		}
		else if(rem == 0){
			return 0;
		}
		if(idx > len){
			return 0;
		}
		
		if(dp[rem][type][idx] >= 0){
			return dp[rem][type][idx];
		}
		int ret = 0;
		
		if(type == 0){
			int sum = 0;
			for(int i = idx; i <= len && i - idx + 1 <= L; i++){
				sum += it[i];
				if(sum >= A && sum <= B){
					int ntype = 0;
					if(i-idx+1 == L){
						ntype = 1;
					}
					ret += cnt(rem-1, ntype, i+1);
					ret %= MOD;
				}
			}
		}
		else{
			int sum = 0;
			for(int i = idx; i <= len && i - idx + 1 <= L; i++){
				sum += it[i];
				if(sum >= A && sum <= B){
					ret += cnt(rem-1, 1, i+1);
					ret %= MOD;
				}
			}
			for(int i = idx+1; i <= len - L + 1; i++){
				sum = 0;
				for(int j = i; j <= len && j - i + 1 <= L; j++){
					sum += it[j];
					if(sum >= A && sum <= B){
						int ntype = 0;
						if(j -i + 1 == L){
							ntype = 1;
						}
						ret += cnt(rem-1, ntype, j+1);
						ret %= MOD;
					}
				}
			}
		}
		
		
		dp[rem][type][idx] = ret;
		return ret;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TheExperiment t = new TheExperiment();
		String[] its = {"53241845388809200299074898230783156026983794239870", "75911526673031313597574731938292733112228280844447", "40127349421676936215905664181723894461115195850962", "05920633799149806375543676720177090844211784635581", "24233937427205946897408563018884759503902116717251", "73498439492545800140134957553134994961929222585935"};
		int ret = t.countPlacements(its, 150, 51, 1, 2000);
		System.out.println(ret);
	}

}
