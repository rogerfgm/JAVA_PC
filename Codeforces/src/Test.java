import java.io.*;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		int max = 1000000000;
		int n = 100000;
		BufferedWriter bw = new BufferedWriter(new FileWriter("test.txt"));
		for(int i = 0; i < 280; i++){
			if(i != 0){
				bw.write(" ");
			}
			bw.write("100000");
		}
		bw.close();
	}

}
