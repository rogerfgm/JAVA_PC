package old;
import java.util.*;


public class PenguinPals {

	public int findMaximumMatching(String c){
		int ret = 0;
		while(c.length() > 1){
			boolean found = false;
			for(int i = 0; i < c.length() - 1; i++){
				if(c.charAt(i) == c.charAt(i + 1)){
					ret++;
					c = c.substring(0, i) + c.substring(i + 2);
					found = true;
					break;
				}
			}
			
			if(!found){
				if(c.charAt(0) == c.charAt(c.length() - 1)){
					found = true;
					c = c.substring(1, c.length() - 1);
					ret++;
				}
			}
			if(!found || c.length() < 2){
				break;
			}
		}
		if(c.length() >= 4){
			ret += c.length() / 2 - 1;
		}
		
		
		return ret;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 PenguinPals p = new  PenguinPals();
		 int ret = p.findMaximumMatching("R");
		 System.out.println(ret);

	}

}
