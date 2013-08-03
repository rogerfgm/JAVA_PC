package old;
import java.util.Arrays;


public class FoxAndKgram {
	public int maxK(int[] len){
		int ret = 0;
		Arrays.sort(len);
		
		for(int i = 1; i <= 50; i++){
			int num = 0;
			boolean[] used = new boolean[len.length];
			for(int j = 0; j < len.length; j++){
				if(used[j]){
					continue;
				}
				if(len[j] < i){
					for(int k = j+1; k < len.length; k++){
						if(used[k]){
							continue;
						}
						if(len[j] + len[k] == i - 1){
							used[k] = true;
							num++;
							break;
						}
					}
				}
				else if(len[j] == i){
					num++;
				}
				else{
					break;
				}
			}
			if(num >= i){
				ret = i;
			}

		}
		
		
		return ret;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FoxAndKgram f = new FoxAndKgram();
		int[] in = {2,3,5,7,11,13,17,19,23,29};
		int ret = f.maxK(in);
		System.out.println(ret);
	}

}
