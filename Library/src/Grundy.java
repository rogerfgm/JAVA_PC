import java.util.*;

public class Grundy {

	int grundy(int x){
		Set<Integer> set = new HashSet<Integer>();
		
		//今の状態から一手でいける状態のgrundy数をsetに追加していく
		// たとえば今の状態がxだとしてkをとるとx-iが次の一手でとり得る値
		for(int i = 1; i <= x; i++){
			set.add(grundy(x-i));
		}
		
		// Sに含まれない最小の非負の整数がxのgrundy数
		for(int i = 0; ; i++){
			if(!set.contains(i)){
				return i;
			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
