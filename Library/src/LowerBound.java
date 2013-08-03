import java.util.Arrays;


public class LowerBound {
	
	
	 /**
     * t以上（含む）が始まるindexを返す
     * 存在しない場合（最後がt未満）の場合、-1を返す
     * @param in
     * @param t
     * @return
     */
    public int lower_bound(int[] in, int t){
  
    	
    	if(in[in.length-1] < t){
    		return -1;
    	}
    	if(in[0] >= t){
    		return 0;
    	}
      	int min = 0;
    	int max = in.length-1;
    	while(true){
    		int mid = (min + max) /2;
    		if(in[mid] < t){
    			min = mid;
    		}
    		else{
    			max = mid;
    		}
    		if(min + 1 >= max){
    			break;
    		}
    	}
    	
    	return min+1;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] in = { 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6 };
		int ret = Arrays.binarySearch(in, 4);
		System.out.println(ret);
	}
}
