package newfailure;


import java.util.*;
import java.io.*;
import static java.lang.Math.*;


/*
 http://quiz.fuqinho.net/blog/2012/06/12/poj-1201-intervals/
不等式x + d ≧ y が、グラフ上でxからyへコストdの辺を張ることに対応するのを利用して、グラフの最短経路問題に帰着する。
d[i]をi以下の整数でZに含まれるものの個数とすると、下記の不等式が成り立つ。
d[i+1] ≧ d[i]
d[i+1] ≦ d[i] + 1
d[Bi] - d[Ai-1] ≧ Ci
それぞれ、下記の辺を張ることに対応する
i+1からiにコスト0の辺を張る
iからi+1にコスト1の辺を張る
BiからAi-1にコスト-Ciの辺を張る
制約より、50000から0までの最短経路の-1倍が答えになる。負の辺を含むのでベルマンフォード法で求める。
 */
public class POJ1201 {

	static PrintStream out = null;
	int INF = Integer.MAX_VALUE / 10;
	static Scanner sc = null;
	double DF = 0.000000000001;

	static int N = 0;

	public void solve() throws Exception{
		
	}
		
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		out = System.out;
		File file = new File("input.txt");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream("input.txt")));
		}
		sc =  new Scanner(System.in);
		POJ1201 p = new POJ1201();
	
		while(true){
			try{
				N = sc.nextInt();
			}
			catch(Exception ex){
				break;
			}
			if(N == 0 ) break;
			p.solve();
		}
	}
}
