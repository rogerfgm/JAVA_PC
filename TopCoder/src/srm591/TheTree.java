package srm591;
import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class TheTree {

	int[] c = null;
	public int maximumDiameter(int[] cnt) {
		c = new int[cnt.length+1];
		for(int i = 0; i < cnt.length; i++){
			c[i+1] = cnt[i];
		}
		
		int ans = check(0);
		for(int i = 1; i < c.length; i++){
			if(c[i] == 1){
				ans = max(ans, check(i));
			}
		}
		return ans;
	}
	
	int check(int idx){
		int fst = 0;
		for(int i = idx + 1; i < c.length; i++){
			if(c[i] != 1){
				fst++;
			}
			else{
				break;
			}
		}
		int scd = c.length - idx - 1;
		return fst + scd;
	}
	
	public static void main(String[] args){
		int[] cnt = new int[]{3};
		TheTree t = new TheTree();
		int r = t.maximumDiameter(cnt);
		System.out.println(r);
	}

}
