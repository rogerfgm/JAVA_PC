import java.util.Comparator;
import java.util.List;


public class ListBinarySearch {

	int higher(List<Integer> l, int v){
		if(l.size() == 0){
			return -1;
		}
		int R = l.size()-1;
		if(l.get(R).intValue() <= v){
			return -1;
		}
		int L = 0;
		if(l.get(L).intValue() > v){
			return 0;
		}
		while(L + 1 < R){
			int m = (R + L)/2;
			if(l.get(m).intValue() > v){
				R = m;
			}
			else{
				L = m;
			}
		}
		return R;
	}
	
	<T> int higher(List<T> l, T v, Comparator<T> comp){
		
		if(l.size() == 0){
			return -1;
		}
		
		int R = l.size()-1;
		int L = 0;
		if(comp.compare(l.get(R), v) <= 0){
			return -1;
		}
		if(comp.compare(l.get(L), v) > 0){
			return 0;
		}
		


		while(L + 1 < R){
			int mid = (L + R) / 2;
			T midval = l.get(mid);
			if(comp.compare(midval, v) > 0){
				L = mid;
			}
			else{
				R = mid;
			}
		}
		return R;
	}
	
	int equalhigher(List<Integer> l, int v){
		if(l.size() == 0){
			return -1;
		}
		int R = l.size()-1;
		if(l.get(R).intValue() < v){
			return -1;
		}
		int L = 0;
		if(l.get(L).intValue() >= v){
			return 0;
		}
		while(L + 1 < R){
			int m = (R + L)/2;
			if(l.get(m).intValue() >= v){
				R = m;
			}
			else{
				L = m;
			}
		}
		return R;
	}
	
	<T> int equalhigher(List<T> l, T v, Comparator<T> comp){
		
		if(l.size() == 0){
			return -1;
		}
		
		int R = l.size()-1;
		int L = 0;
		if(comp.compare(l.get(R), v) < 0){
			return -1;
		}
		if(comp.compare(l.get(L), v) >= 0){
			return 0;
		}
		


		while(L + 1 < R){
			int mid = (L + R) / 2;
			T midval = l.get(mid);
			if(comp.compare(midval, v) >= 0){
				L = mid;
			}
			else{
				R = mid;
			}
		}
		return R;
	}
	
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
