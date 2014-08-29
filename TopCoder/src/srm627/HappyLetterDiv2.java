package srm627;
import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class HappyLetterDiv2 {

	final int INF = Integer.MAX_VALUE / 10;
	final double EPS = 1E-8;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	char no = '.';
	public char getHappyLetter(String s){
		List<Character> list = new ArrayList<Character>();
		for(int i = 0; i < s.length(); i++){
			list.add(s.charAt(i));
		}
		
		Collections.sort(list);
		
		return get(s);
	}
	
	char get(String s){
		if(s.length() == 0){
			return no;
		}
		boolean f = true;
		char c = s.charAt(0);
		for(int i = 1; i < s.length(); i++){
			if(s.charAt(i) != c){
				f = false;
				break;
			}
		}
		
		if(f){
			return c;
		}
		char ret = '-';
		for(int i = 0; i < s.length()-1; i++){
			for(int j = i+1; j < s.length(); j++){
				if(s.charAt(i) != s.charAt(j)){
					String ns = s.substring(0, i) + s.substring(i+1, j) + s.substring(j+1);
					char r = get(ns);
					if(r == no){
						return no;
					}
					if(ret == '-'){
						ret = r;
					}
					else if(ret != r){
						return no;
					}
				}
			}
		}
		
		return ret;
	}
	
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HappyLetterDiv2 t = new HappyLetterDiv2();
		String a = "aaaaaaaaaabbaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

		char c = t.getHappyLetter(a);
		out.println(c);
	}

}
