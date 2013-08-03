import java.util.*;

public class Sort {

	public static void dsort(int[] in){
		Arrays.sort(in);
		for(int i = 0; i < in.length / 2; i++){
			int tmp = in[i];
			in[i] = in[in.length-i-1];
			in[in.length-i-1] = tmp;
		}
	}
	public static void dsort(long[] in){
		Arrays.sort(in);
		for(int i = 0; i < in.length / 2; i++){
			long tmp = in[i];
			in[i] = in[in.length-i-1];
			in[in.length-i-1] = tmp;
		}
	}
	public static void dsort(double[] in){
		Arrays.sort(in);
		for(int i = 0; i < in.length / 2; i++){
			double tmp = in[i];
			in[i] = in[in.length-i-1];
			in[in.length-i-1] = tmp;
		}
	}
	public static void dsort(String[] in){
		Arrays.sort(in, Collections.reverseOrder());
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] in = { 1, 2, 3, 4, 5, 6 };
		dsort(in);
		
		for(int i = 0; i < in.length; i++){
			System.out.println(in[i]);
		}
	}

}
