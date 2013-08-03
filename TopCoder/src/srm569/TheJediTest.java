package srm569;

public class TheJediTest {

	public int minimumSupervisors(int[] st, int K){

		int ans = 0;

		while(true){
			int idx = 0;
			boolean flag = false;
			for(int i = 0; i < st.length; i++){
				if(st[i] > 0){
					idx = i;
					flag = true;
					break;
				}
			}
			
			
			if(!flag){
				break;
			}
			else{
				if(idx == st.length-1){
					ans += st[idx] / K;
					if(st[idx] % K > 0){
						ans++;
					}
					break;
				}
				else{
					ans += st[idx] / K;
					st[idx] %= K;
					if(st[idx] > 0) ans++;
					if(st[idx] == 0) continue;
					if(st[idx] + st[idx+1] >= K){
						int k = K;
						k -= st[idx];
						st[idx] = 0;
						st[idx+1] -= k;
					}
					else{
						int k = K;
						for(int i = 0; i < 3 && idx+i < st.length; i++){
							if(k > st[idx+i]){
								k -= st[idx+i];
								st[idx+i] = 0;
							}
							else{
								st[idx+i] -= k;
								k = 0;
							}
						}
					}
				}
				
			}
		}
		
	
		
		
		
		return ans;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TheJediTest t = new TheJediTest();
		int[] s ={29541663, 85732198, 69829763, 77760462, 32153432, 79240058, 13641353, 50236888, 83780217, 82884996, 5909451, 68242201, 64320036, 82420030, 16758585, 12089161, 92006984, 90761986};
		int k = 67854681; // expect 16


		int ret = t.minimumSupervisors(s, k);
		System.out.println(ret);
	}

}
