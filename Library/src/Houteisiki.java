
public class Houteisiki {

	/**
	 * 二次方程式を解いて答えを返す。答えがないとき、retのサイズは0。１つのみの時、retのサイズは１．２つ答えがあるとき、retのサイズは2
	 * @param a
	 * @param b
	 * @param c
	 * @return
	 */
	double[] nijiHouteisiki(double a, double b, double c){
		double[] ret = null;
		double DF = 0.0000000001;
		double ck = b * b - 4 * a * c;
		if(Math.abs(ck) < DF){
			ck = 0;
		}
		if(ck < 0){
			ret = new double[0];
			return ret;
		}
		else if(ck == 0){
			ret = new double[1];
			ret[0] = -b / (2 * a);
			return ret;
		}
		else{
			ret = new double[2];
			ret[0] = (-b + Math.sqrt(ck) ) / (2 * a);
			ret[1] = (-b - Math.sqrt(ck) ) / (2 * a);
			return ret;
		}
	}
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
