package srm565;

import java.util.*;
public class MonstersValley {


	
	public int minimumPrice(long[] d, int[] p){
		int ret = 0;
		
		List<Data> list = new ArrayList<Data>();
		{
		Data data = new Data();
		data.p = p[0];
		data.s = d[0];
		list.add(data);
		}
		
		for(int i = 1; i < d.length; i++){
			List<Data> nlist = new ArrayList<Data>();
			for(int j = 0; j < list.size(); j++){
				Data dt = list.get(j);
				
				if(dt.s >= d[i]){
					nlist.add(dt);
				}
				Data data = new Data();
				data.p = dt.p + p[i];
				data.s = dt.s + d[i];
				nlist.add(data);
			}
			Data[] datas = new Data[101];
			for(int j = 0; j < nlist.size(); j++){
				if(datas[nlist.get(j).p] == null){
					datas[nlist.get(j).p] = nlist.get(j);
				}
				else if(datas[nlist.get(j).p].s < nlist.get(j).s){
					datas[nlist.get(j).p] = nlist.get(j);
				}
			}
			
			list = new ArrayList<Data>();
			for(int j = 0; j < datas.length; j++){
				if(datas[j] != null){
					list.add(datas[j]);
				}
			}
			
		}
		
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < list.size(); i++){
			min = Math.min(min, list.get(i).p);
		}
		
		
		
		return min;
	}
	
	class Data{
		int p = 0;
		long s = 0;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MonstersValley m = new MonstersValley();
		long[] i = {200, 107, 105, 206, 307, 400};
		int[] d = {1, 2, 1, 1, 1, 2};
		int r = m.minimumPrice(i, d);
		System.out.println(r);
	}

}
