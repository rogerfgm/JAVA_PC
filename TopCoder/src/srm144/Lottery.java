package srm144;
import java.io.*;
import java.math.BigInteger;
import java.util.*;

import static java.lang.Math.*;

public class Lottery {

	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	static PrintStream out = System.out;
	
	
	public String[] sortByOdds(String[] rs){
		Data[] d = new Data[rs.length];
		for(int i = 0; i < rs.length; i++){
			d[i] = new Data();
			String[] sp = rs[i].split(":");
			d[i].name = sp[0];
			sp = sp[1].trim().split(" ");
			int a = Integer.parseInt(sp[0]);
			BigInteger ba = new BigInteger(sp[0]);
			int b = Integer.parseInt(sp[1]);
			boolean sort = sp[2].equals("T");
			boolean uniq = sp[3].equals("T");

			if(!sort && !uniq){
				for(int j = 0; j < b; j++){
					d[i].b = d[i].b.multiply(ba);
				}
			}
			else if(sort && !uniq){
				d[i].b = ncb(a + b  -1, b);
			}
			else if(!sort && uniq){
				for(int j = 2; j <= b; j++){
					d[i].b = d[i].b.multiply(BigInteger.valueOf(j));
				}
				BigInteger bnc = ncb(a, b);
				d[i].b = d[i].b.multiply(bnc);
			}
			else{
				d[i].b = ncb(a, b);
			}
		}
		
		Arrays.sort(d, new Comparator<Data>() {
			public int compare(Data o1, Data o2) {
				if(o1.b.compareTo(o2.b)!= 0 ){
					return o1.b.compareTo(o2.b);
				}
				return o1.name.compareTo(o2.name);
			}
		});
		String[] ret = new String[d.length];
		for(int i = 0; i < ret.length; i++){
			ret[i] = d[i].name;
		}
		
		return ret;
	}
	
	static BigInteger ncb(final int n, int c) {
		if(n < c){
			return null;
		}
		if(n-c < c){
			c = n-c;
		}
	    BigInteger ret = BigInteger.ONE;
	    for (int k = 0; k < c; k++) {
	        ret = ret.multiply(BigInteger.valueOf(n-k))
	                 .divide(BigInteger.valueOf(k+1));
	    }
	    return ret;
	}

	class Data{
		String name = null;
		BigInteger b = new BigInteger("1");
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Lottery t = new Lottery();

		String[] in = {"INDIGO: 93 8 T F",
				 "VIOLET: 76 6 F F"
				};
		String[] ret = t.sortByOdds(in);
		for(String s : ret){
			out.println(s);
		}
		System.out.println();
	}

}
