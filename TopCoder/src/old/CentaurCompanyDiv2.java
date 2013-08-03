package old;
import java.util.*;





public class CentaurCompanyDiv2 {

	long ans = 2;
	List<List<Integer>> es = new ArrayList<List<Integer>>(); 
	public long count(int[] a, int[] b){
		int N = a.length + 1;
		//ans = (long)Math.pow(2, N);
		
		for(int i = 0; i < N + 1; i++){
			List<Integer> l = new ArrayList<Integer>();
			es.add(l);
		}
		
		for(int i = 0; i < a.length; i++){
			int f = a[i];
			int t = b[i];
			es.get(f).add(t);
			es.get(t).add(f);
		}
		
		
		
		while(true){
			int st = 0;
			for(int i = 1; i <= N; i++){
				if(es.get(i).size() == 1){
					st = i;
					break;
				}
			}
			if(st == 0){
				break;
			}
			

			int p = st;
			
			long sum = check(p, -1) -1;
			ans += sum;
			
			
			
			int to = es.get(st).get(0);
			es.get(st).remove(0);
			for(int i = 0; i < es.get(to).size(); i++){
				if(es.get(to).get(i).intValue() == st){
					es.get(to).remove(i);
					break;
				}
			}
		}
		
		
		return ans;
	}
	
	long check(int p, int prev){
		long sum = 1;
		
		for(int i = 0; i < es.get(p).size(); i++){
			if(es.get(p).get(i).intValue() == prev){
				continue;
			}
			sum *= check(es.get(p).get(i), p);
		}
		
		sum++; //  not use
		return sum;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CentaurCompanyDiv2 c = new CentaurCompanyDiv2();
		int[] a = {10, 7, 2, 5, 6, 2, 4, 9, 7};
		int[] b = {8, 10, 10, 4, 1, 6, 2, 2, 3};
		long ret = c.count(a, b);
		System.out.println(ret);
	}

}
