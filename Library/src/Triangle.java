import static java.lang.Math.*;

import java.awt.image.CropImageFilter;


public class Triangle {


	/**
	 * p3がp1からp2に引いた先の、時計回り側にあるか、反時計回り側にあるか。
	 * プラスなら反時計回り。
	 * マイナスなら時計回り。
	 * ０なら同一直線上にある
	 * @param p1
	 * @param p2
	 * @param p3
	 * @return
	 */
	public static double crossProduct(double[] p1, double[] p2, double[]p3){
		return (p2[0] - p1[0]) * (p3[1] - p1[1]) - (p2[1] - p1[1]) * (p3[0] - p1[0]);
	}
	
	
	
	public double menseki3Hen(double a, double b, double c){
		double s = (a + b + c) /2;
		s = s * (s-a)*(s-b)*(s-c);
		return sqrt(s);
	}
	
	/**
	 * 2辺とその間の角度（何度）がわかっている時
	 * @param a
	 * @param b
	 * @param kakudo 360 degree
	 * @return
	 */
	public double menseki2Hen(double a, double b, double kakudo){
		
		return a * b * Math.sin(Math.toRadians(kakudo)) / 2;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double[] p1 = {0, 0};
		double[] p2 = {1, 1};
		double[] p3 = {10, 0};
		double ret = crossProduct(p1, p2, p3);
		System.out.println(ret);
	}

}
