package srm563;
import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

// 難しかった。dpでやるとtleしてしまうから、直接組み立てる方式でやらないとだめ。
// こたえは１文字ずつ増やしていき、a - zで総当りで、その答えの文字（だんだん文字が増えていく）について、okかどうかでokならその文字にする（辞書順なのでaからでできたもんがち）
// okかどうかは、
// そのもじがその順でSにあるか。
// その順でSに見つけた最後の文字までで、構成に使わなかった文字が、全体のその文字の1/2を超えないこと。超えなければ少なくとも残りを残り順に並べれば答えは構築可能だから。
public class FoxAndHandle {

	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	String S = null;
	int[] cnt = null;
	public String lexSmallestName(String S){
		this.S = S;
		cnt = new int[27];
		int[] acnt = new int[27];
		for(int i = 0; i < S.length(); i++){
			int idx = S.charAt(i) - 'a';
			cnt[idx]++;
		}
		
		
		String ans = "";
		
		
		for(int i = 0; i < S.length() / 2; i++){
			for(char c = 'a'; c <= 'z'; c++){
				if(acnt[c-'a'] == cnt[c-'a'] / 2) continue;
				String ck = ans + c;
				if(ok(ck)){
					ans = ck;
					acnt[c - 'a']++;
					break;
				}
			}
		}
		
		
		return ans;
	}
	
	boolean ok(String ck){
		int[] tc = new int[27];
		int idx = 0;
		for(int i = 0; i < ck.length(); i++){
			while(true){
				if(idx == S.length()){
					return false;
				}
				int sidx = S.charAt(idx) - 'a';
				
				if(S.charAt(idx) == ck.charAt(i)){
					idx++;
					break;
				}
				else{
					tc[sidx]++;
					if(tc[sidx] > cnt[sidx] / 2){
						return false;
					}
				}
				idx++;
			}
		}
		
		return true;
	}
	
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FoxAndHandle t = new FoxAndHandle();
//		String S = "efbaebadacafecabcefaefbaebadacafecabcefa";
		String S = "fedcbafedcba"; // "afedcb"
		String ret = t.lexSmallestName(S);
		out.println(ret);

		/*
		 TLE
		 Args:
{"efbaebadacafecabcefaefbaebadacafecabcefa"}

Expected:
"aaaaaaefbebdcfecbcef"
 aaaaaaefbebdcfecbcef
Received:
The code execution time exceeded the 2 second time limit.
		 */
	}

}
