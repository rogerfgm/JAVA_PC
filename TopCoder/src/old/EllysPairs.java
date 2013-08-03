package old;
import java.util.*;


public class EllysPairs {

	public int getDifference(int[] k){
		int ret = 0;
		Arrays.sort(k);
		if(k.length == 2){
			return 0;
		}
		
		int s = 0;
		Set<Integer> set = new HashSet<Integer>();
		for(int i = 0; i < k.length / 2; i++){
			int j = k.length - 1 - i;
			int sum = k[i] + k[j];
			if(set.size() == 0){
				
			}
			else{
				int max = 0;
				for(int num : set){
					max = Math.max(max, Math.abs(num - sum));
				}
				s = Math.max(max, s);
			}
			set.add(sum);
		}
		
		
		return s;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EllysPairs e = new EllysPairs();
		int[] in = {5, 1, 8, 8, 13, 7, 6, 2, 1, 9, 5, 11, 3, 4};
		int ret = e.getDifference(in);
		System.out.println(ret);
	}

}
