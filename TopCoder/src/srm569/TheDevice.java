package srm569;

public class TheDevice {

	public int minimumAdditional(String[] p){
		int len = p[0].length();
		int[] ones = new int[len];
		int[] zeros = new int[len];
		for(int i = 0; i < p.length; i++){
			for(int j = 0; j < len; j++){
				if(p[i].charAt(j) == '1'){
					ones[j]++;
				}
				else{
					zeros[j]++;
				}
			}
		}
		boolean of1 = false;
		boolean of0 = false;
		boolean tf = false;
		for(int i = 0; i < len; i++){
			if(ones[i] == 1){
				of1 = true;
			}
			else if(ones[i] == 0){
				tf = true;
			}
			if(zeros[i] == 0){
				of0 = true;
			}
		}
		if(tf || p.length == 1){
			return 2;
		}
		
		if(of1 || of0){
			return 1;
		}
		
		return 0;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TheDevice t = new TheDevice();
		String[] s = {"010",
				 "011",
		 "101"};
		int ret = t.minimumAdditional(s);
		System.out.println(ret);
	}

}
