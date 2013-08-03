import java.util.*;


//    参考：　元はlongで返しているけど、int[] で返すように変更
//    http://topcoder.g.hatena.ne.jp/eller/20090929/1254228452

// 内部のCombinationクラスをコピーして、test()関数のように使う

public class CombinationLib {

	public static void main(String[] args) {
		CombinationLib c = new CombinationLib();
		c.test();
	}
	
	// 使い方例：
	public void test(){
		Combination c = new Combination(50, 2);
		Iterator<int[]> ite = c.iterator();
		while(ite.hasNext()){
			int[] ret = ite.next();
			for(int i = 0; i < ret.length; i++){
				System.out.print(ret[i] + " ");
			}
			System.out.println();
		}	
	}
	
	public class Combination implements Iterable<int[]> {
		private final int max;
		private final int select;
		
		public Combination(int max, int select) {
			if (max < 1 || 62 < max) {
				throw new IllegalArgumentException();
			}
			this.max = max;
			this.select = select;
		}
	
		public Iterator<int[]> iterator() {
			return new CombinationIterator(max, select);
		}
		
		private class CombinationIterator implements Iterator<int[]> {
			private long value;
			private final long max;
			private final int size;
			private int[] ret = null;
			public CombinationIterator(int max, int select) {
				this.value = (1L << select) - 1L;
				this.size = max;
				this.max = 1L << max;
				this.ret = new int[size];
			}

	
			public boolean hasNext() {
				return value < max;
			}


			public int[] next() {
				long stock = value;
				value = next(value);
				
				for(int i = 0; i < size; i++){
					long tmp = stock >> i;
					tmp = tmp & 1;
					ret[i] = (int)tmp;
				}
				
				return ret;
			}

	
			public void remove() {
				throw new UnsupportedOperationException();
			}

			private long next(long source) {
				long param1 = smallestBitOf(source);
				long param2 = param1 + source;
				long param3 = smallestBitOf(param2);
				long param5 = (param3 / param1) >>> 1;
				return param5 - 1 + param2;
			}

			private long smallestBitOf(long source) {
				long result = 1L;
				while (source % 2 == 0) {
					source >>>= 1;
					result <<= 1;
				}
				return result;
			}
		}
	}
}

