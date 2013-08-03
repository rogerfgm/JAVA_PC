import java.util.Comparator;
import java.util.List;


public class ListBinarySearch {

	int find(List<Integer> l, int v){
		if(l.size() == 0){
			return 0;
		}
		if(l.get(l.size()-1).intValue() < v){
			return l.size();
		}
		if(l.get(l.size()-1).intValue() == v){
			return l.size()-1;
		}
		if(l.get(0).intValue() >= v){
			return 0;
		}
		
		int min = 0;
		int max = l.size()-1;
		while(min + 1 < max){
			int mid = (min + max) / 2;
			int midval = l.get(mid).intValue();
			if(midval == v){
				return mid;
			}
			else if(midval > v){
				max = mid;
			}
			else{
				min = mid;
			}
		}
		return max;
	}
	
	<T> int find(List<T> l, T t, Comparator<T> comp){
		
		if(l.size() == 0){
			return 0;
		}
		if(comp.compare(l.get(l.size()-1), t) < 0){
			return l.size();
		}
		if(comp.compare(l.get(l.size()-1), t) == 0){
			return l.size()-1;
		}
		if(comp.compare(l.get(0), t) >= 0){
			return 0;
		}
		
		int min = 0;
		int max = l.size()-1;
		while(min + 1 < max){
			int mid = (min + max) / 2;
			T midval = l.get(mid);
			if(comp.compare(t, midval) == 0){
				return mid;
			}
			else if(comp.compare(t, midval) < 0){
				max = mid;
			}
			else{
				min = mid;
			}
		}
		return max;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
