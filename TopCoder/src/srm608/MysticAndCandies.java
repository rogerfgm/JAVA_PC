package srm608;

import java.util.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;

import java.io.*;


public class MysticAndCandies {

	final int INF = Integer.MAX_VALUE / 10;
	final double EPS = 1E-8;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	
	public int minBoxes(int C, int X, int[] l, int[] h){
		int ans = 0;
		N = l.length;
		Data[] dls = new Data[N];
		Data[] dhs = new Data[N];
		for(int i = 0; i < N; i++){
			Data d = new Data();
			d.l = l[i];
			d.h = h[i];
			d.idx = i;
			dls[i] = d;
			d = new Data();
			d.l = l[i];
			d.h = h[i];
			d.idx = i;
			dhs[i] = d;
		}
		Arrays.sort(dls, new Comparator<Data>() {
			@Override
			public int compare(Data o1, Data o2) {
				// TODO Auto-generated method stub
				return o2.l - o1.l;
			}
		});
		Arrays.sort(dhs, new Comparator<Data>() {
			@Override
			public int compare(Data o1, Data o2) {
				// TODO Auto-generated method stub
				return o1.h - o2.h;
			}
		});
		
		for(int i = 1; i <= N; i++){
			int lsum = 0;
			for(int j = 0; j < i; j++){
				lsum += dls[j].l;
			}
			if(lsum >= X){
				return i;
			}
			
			int hsum = 0;
			for(int j = 0; j < N - i; j++){
				hsum += dhs[j].h;
			}
			if(C - hsum >= X){
				return i;
			}
		}
		return N;
	
	}
	
	class Data {
		int idx;
		int l;
		int h;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MysticAndCandies t = new MysticAndCandies();
		int[] low = {5, 2, 3};
		int[] high = {49, 48, 47};
		int r = t.minBoxes(60, 8, low, high);
		out.println(r);
	}

}
