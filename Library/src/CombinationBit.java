
public class CombinationBit {

	// nCk
	//{0, 1, .., n-1}に含まれるサイズkの部分集合
	public static void combi (){
		int n = 5;
		int k = 3;
		int comb = (1 << k) -1;
		while(comb < 1 << n){
			//ここに処理を入れる
			{
				for(int i = n-1; i >= 0; i--){
					if( (comb >> i  & 1) > 0) System.out.print("1");
					else System.out.print("0");
				}
				System.out.println();
				
			}
			
			//以下、次のパターン
			if(comb == 0) break;
			int x = comb & -comb, y = comb + x;
			comb = ( (comb & ~y) / x >> 1 ) | y;
		}
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		combi();
	}

}
