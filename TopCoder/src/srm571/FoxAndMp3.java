package srm571;
import java.util.*;

public class FoxAndMp3 {

	int n = 0;
	List<String> ans = new ArrayList<String>();
	public String[] playList(int n){
		this.n = n;
		int t = 1;
		check(t);
		String[] ret = null;
		if(ans.size() >= 50){
			ret = new String[50];
		}
		else{
			ret = new String[ans.size()];
		}
		for(int i = 0; i < Math.min(50, ans.size()); i++){
			ret[i] = ans.get(i) + ".mp3";
		}
		return ret;
		
	}
	
	void check(long t){
		if(t > n){
			return;
		}
		if(ans.contains("" + t)){
			return;
		}
		ans.add("" + t);
		if(ans.size() >= 50){
			return;
		}
		long T = t * 10;
		if(T <= n){
			check(T);
		}
		if(ans.size() >= 50){
			return;
		}
		int max = 9;
		if(t == 1){
			max = 8;
		}
		for(int i = 1; i <= max; i++){
			T = t + i;
			if(T <= n){
				ans.add("" + T);
			}
			check(T * 10);
			if(ans.size() >= 50){
				return;
			}
		}
		return;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FoxAndMp3 f = new FoxAndMp3();
		String[] ret = f.playList(10);
		for(String s : ret){
			System.out.println(s);
		}
	}

}
