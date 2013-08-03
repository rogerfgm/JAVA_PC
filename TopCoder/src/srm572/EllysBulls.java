package srm572;
import java.util.*;

// 1つがtleになってしまったが、javaということでとりあえずはあきらめる
public class EllysBulls {

	int len = 0;
	String[] gs = null;
	int[] bs = null;

	String ans = null;
	boolean amb = false;
	
	public String getNumber(String[] gess, int[] bulls){
		amb  = false;
		ans = null;
		gs = gess;
		bs = bulls;
		len = gs[0].length();
	
		int[] hit = new int[len];
		for(int i = 0;i < len; i++){
			hit[i] = -1;
		}
		boolean[][] nots = new boolean[len][10];
		
		
		check(0, nots, hit);
		
		if(amb){
			return "Ambiguity";
		}
		else if(ans == null){
			return "Liar";
		}
		return ans;
	}
	
	void check(int idx, boolean[][] nots, int[] hit){
		if(idx == gs.length){
			StringBuilder sb = new StringBuilder();
			
			for(int i = 0; i < len;i++){
				if(hit[i] >= 0){
					sb.append(hit[i]);
				}
				else{
					int num = 0;
					for(int j = 0; j < nots[i].length; j++){
						if(nots[i][j]){
							num++;
						}
					}
					if(num != 9){
						amb = true;
						return;
					}
					else{
						for(int j = 0; j <= 9; j++){
							if(!nots[i][j]){
								sb.append(j);
								break;
							}
						}
					}
				}
			}
			if(ans != null){
				amb = true;
			}
			else{
				ans = sb.toString();
			}
			return;
		}
		String s = gs[idx];
		
		int n = len;
		int k = bs[idx];
		int comb = (1 << k) -1;
		while(comb < 1 << n){
			//ここに処理を入れる
			{
				boolean flag = true;
				for(int i = n-1; i >= 0; i--){
					int ii = s.charAt(i) - '0';
					if( (comb >> i  & 1) > 0){
						if(hit[i] >= 0){
							if(hit[i] != ii){
								flag = false;
							}
						}
						if(nots[i][ii]){
							flag = false;
						}
					}
					else{
						if(hit[i] >= 0){
							if(hit[i] == ii){
								flag = false;
							}
						}
						if(!nots[i][ii]){
							int num = 0;
							for(int j = 0; j <= 9; j++){
								if(nots[i][j]){
									num++;
								}
							}
							if(num == 9){
								flag = false;
							}
						}
					}
				}
				if(flag){
					int[] nhit = new int[hit.length];
					for(int i = 0;i < hit.length; i++){
						nhit[i] = hit[i];
					}
					
					boolean[][] nnot = new boolean[nots.length][nots[0].length];
					for(int j = 0; j < nots.length; j++){
						for(int l = 0; l < nots[0].length; l++){
							nnot[j][l] = nots[j][l];
						}
					}
					
					
					for(int i = n-1; i >= 0; i--){
						int ii = s.charAt(i) - '0';
						if( (comb >> i  & 1) > 0){
							nhit[i] = ii;
						}
						else{
							nnot[i][ii] = true;
						}
					}

					check(idx+1, nnot, nhit);
				}
				
			}
			if(comb == 0) break;
			//以下、次のパターン
			int x = comb & -comb, y = comb + x;
			comb = ( (comb & ~y) / x >> 1 ) | y;
		}
	}
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		EllysBulls e = new EllysBulls();
		
		// TLE
		String[] guesses =    {"000000000", "111111111", "222222222", "333333333", "444444444", "555555555", "666666666", "777777777", "000000000", "111111111", "222222222", "333333333", "444444444", "555555555", "666666666", "777777777", "000000000", "111111111", "222222222", "333333333", "444444444", "555555555", "666666666", "777777777", "000000000", "111111111", "222222222", "333333333", "444444444", "555555555", "666666666", "777777777", "000000000", "111111111", "222222222", "333333333", "444444444", "555555555", "666666666", "777777777", "000000000", "111111111", "222222222", "333333333", "444444444", "555555555", "666666666", "777777777"};
		int[] bulls ={2, 2, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1};
		String ret = e.getNumber(guesses, bulls);
		System.out.println(ret);
	}
	
	

}
