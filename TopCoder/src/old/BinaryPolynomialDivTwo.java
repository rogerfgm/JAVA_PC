package old;

public class BinaryPolynomialDivTwo {

	public int countRoots(int[] a){
		int ans = 0;
		int base = 0;
		while(base < 2){
			int sum = 0;
			for(int i = 0; i < a.length; i++){
				if(i == 0){
					if(a[i] == 1){
						sum++;
					}
				}
				else{
					if(base == 0){
						continue;
					}
					else{
						if(a[i] == 1){
							sum++;
						}
					}
				}
				
			}
			if(sum % 2 == 0){
				ans++;
			}
			base++;
		}
		
		
		return ans;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] in = {0, 1, 0, 1};
		BinaryPolynomialDivTwo b = new BinaryPolynomialDivTwo();
		int r = b.countRoots(in);
		System.out.println(r);
	}

}
