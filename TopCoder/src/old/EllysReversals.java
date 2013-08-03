package old;
import java.util.*;

public class EllysReversals {

	public int getMin(String[] w){
		List<String> list = new ArrayList<String>();
		for(String s : w){
			list.add(s);
		}
		while(true){
			boolean f = false;
			for(int i = 0; i < list.size()-1; i++){
				String s1 = list.get(i);
				for(int j = i+1; j < list.size(); j++){
					String s2 = list.get(j);
					f = check(s1, s2);
					if(f){
						list.remove(j);
						list.remove(i);
						break;
					}
				}
				if(f){
					break;
				}
			}
			if(!f){
				break;
			}
		}
		
		
		return list.size();
	}
	
	boolean check(String s1, String s2){
		if(s1.length() != s2.length()){
			return false;
		}
		if(s1.length() % 2 == 1){
			if(s1.charAt(s1.length()-1) != s2.charAt(s2.length()-1)){
				return false;
			}
		}
		String[] s1s = new String[s1.length()/2];
		String[] s2s = new String[s1.length()/2];
		for(int i = 0;  i < s1.length()/2; i++){
			String t = s1.substring(2*i, 2 *i+2);
			if(t.charAt(0) > t.charAt(1)){
				t = t.charAt(1) + ""  + t.charAt(0);
			}
			s1s[i] = t;
			t = s2.substring(2*i, 2 * i+2);
			if(t.charAt(0) > t.charAt(1)){
				t = t.charAt(1) + ""  + t.charAt(0);
			}
			s2s[i] = t;
		}
		Arrays.sort(s1s);
		Arrays.sort(s2s);
		for(int i = 0; i < s1s.length; i++){
			if(!s1s[i].equals(s2s[i])){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EllysReversals e = new EllysReversals();
		String[] in = {"esprit", "god", "redotopc", "odcpoter", "dog"};
		int ret = e.getMin(in);
		System.out.println(ret);
	}

}
