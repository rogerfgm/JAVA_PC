package srm564;

public class KnightCircuit2 {

	public int maxSize(int w, int h){
		
		int x = Math.max(w, h);
		int y = Math.min(w, h);
		
		if(y == 1){
			return 1;
		}
		if(y == 2){
			if(x == 2){
				return 1;
			}
			else{
				return (x+1) / 2; 
			}
		}
		if(y == 3 && x == 3){
			return 8;
		}
		else{
			return x * y;
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
