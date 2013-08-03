package srm572;
import java.util.*;
public class NewArenaPassword {

	public int minChange(String s, int K){
		int ans = 0;

		char[] cs = new char[s.length()];
		for(int i = 0; i < s.length(); i++){
			cs[i] = s.charAt(i);
		}
		
		
		
		/*
		 * 
		 * 
amavckdkz
avckdkz		 */
			
		int li = s.length()-K;
		int fi = 0;
		int diff = li-fi;
		
		if(diff < K){
			for(int i = 0; i < diff; i++){
				Map<String, Integer> map = new HashMap<String, Integer>();
				for(int j = 0; ; j++){
					int idx = j * diff + i;
					if(idx >= s.length()){
						break;
					}
					String ch = s.substring(idx, idx+1);
					if(map.containsKey(ch)){
						int num = map.get(ch) + 1;
						map.put(ch, num);
					}
					else{
						map.put(ch,  1);
					}
				}
				int sum = 0;
				int max = 0;
				for(String ch : map.keySet()){
					int num = map.get(ch);
					max = Math.max(max, num);
					sum += num;
				}
				ans += (sum - max);
			}
		}
		else{
			for(int i = 0; i < K; i++){
				if(cs[fi+i] != cs[li+i]){
					ans++;
				}
			}
		}
			
		
		return ans;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NewArenaPassword n = new NewArenaPassword();
		String s = "ppppppbppppppppppcpvpppppempppeppipppsppxpvppcpppb";
		int ret = n.minChange(s, 40);
		System.out.println(ret);
		
		/*
		 * 
		 * 
		 * 
		{"ppppppbppppppppppcpvpppppempppeppipppsppxpvppcpppb", 40}

Expected:
12

Received:
13


amavckdkz    ==     5
		 */
		
	}

}
