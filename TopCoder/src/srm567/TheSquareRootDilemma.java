package srm567;
import java.util.*;

public class TheSquareRootDilemma {

	List<Integer> list = new ArrayList<Integer>();
	public int countPairs(int N, int M){
		if(N > M){
			int t = M;
			M = N;
			N = t;
		}
		int ans = 0;

		list = new ArrayList<Integer>();
	

		for(int i = 2; i*i <= M; i++){
			int b = i * i;
			list.add(b);
		}
		
		Map<Integer, Integer> minmap = new HashMap<Integer, Integer>();
		for(int i = 1; i <= M; i++){
			int m = min(i);
			minmap.put(i, m);
		}
		
		Map<Integer, Integer> nummap = new HashMap<Integer, Integer>();
		for(int n : minmap.values()){
			if(nummap.containsKey(n)){
				continue;
			}
			int num = 0;
			for(int j = 0; j < list.size(); j++){
				long m = n * list.get(j);
				if(m > M){
					break;
				}
				num++;
			}
			nummap.put(n, num);
		}
		
		for(int i = 1; i <= N; i++){
			int n = minmap.get(i);
			ans++;
			ans += nummap.get(n);
		}
		
		return ans;
	}
	
	int min(int l){
		int idx = 0;
		while(l > 1){
			if(idx == list.size()){
				break;
			}
			if(list.get(idx) > l){
				break;
			}
			while(l % list.get(idx) == 0){
				l /= list.get(idx);
			}
			idx++;
		}
		return l;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TheSquareRootDilemma t = new TheSquareRootDilemma();
		int ret = t.countPairs(100, 100);
		System.out.println(ret);

	}

}
