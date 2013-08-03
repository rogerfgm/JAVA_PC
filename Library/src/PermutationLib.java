import java.util.*;

//　参考：http://topcoder.g.hatena.ne.jp/eller/20090929/1254228452
//内部のPermutationクラスをコピーして、test()関数のように使う

public class PermutationLib {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PermutationLib p = new PermutationLib();
		p.test();
	}
	
	public void test(){
		Permutation p = new Permutation(8);
		Iterator<int[]> ite = p.iterator();
		while(ite.hasNext()){
			int[] d = ite.next();
			for(int i = 0; i < d.length; i++){
				System.out.print(d[i] + " ");
			}
			System.out.println("");
		}
	}

	public class Permutation implements Iterable<int[]> {
		private final int size;

		/**
		 * <p>順列を表すクラスのコンストラクタ。反復子が返す配列の要素数を指定する。
		 * @param size 順列の要素数（10程度が妥当な最大値）
		 * @throws IllegalArgumentException 引数（順列の要素数）が0以下の場合
		 */
		public Permutation(int size) {
			if (size <= 0) throw new IllegalArgumentException();
			this.size = size;
		}

		public Iterator<int[]> iterator() {
			return new Iter(size);
		}

		private class Iter implements Iterator<int[]> {
			private final int[] source;
			private boolean isFirst = true;

			private Iter(int size) {
				source = new int[size];
				for(int i = 0; i < size; ++i) {
					source[i] = i;
				}
			}

			/**
			 * <p>反復子がまだ順列を返しうるか調べる。
			 * 本メソッドは{@link Iter#next()}呼び出し前に必ず1回ずつ実行する必要がある。
			 * @return 順列が存在する場合はtrue
			 */
			public boolean hasNext() {
				if (isFirst) {
					isFirst = false;
					return true;
				}

				int n = source.length;
				for (int i = n - 1; i > 0; i--) {
					if (source[i - 1] < source[i]) {
						int j = n;
						while (source[i - 1] >= source[--j]);
						swap(source, i - 1, j);
						reverse(source, i, n);
						return true;
					}
				}
				reverse(source, 0, n);
				return false;
			}

			/**
			 * <p>次の順列を表すint型配列を返す。
			 * <p>このメソッドが返す参照は常に同じ配列に対する参照である。
			 * このため配列の要素を変更したり次回の{@link Iter#next()}呼び出し後も参照する場合は、
			 * クラスの呼び出し側が配列のクローンを生成して利用する必要がある。
			 * @return 順列を表すint型配列
			 */
			public int[] next() {
				return source;
			}

			public void remove() {
				throw new UnsupportedOperationException();
			}

			private void swap(int[] is, int i, int j) {
				int t = is[i];
				is[i] = is[j];
				is[j] = t;
			}

			private void reverse(int[] is, int s, int t) {
				while (s < --t) swap(is, s++, t);
			}
		}
	}
}
