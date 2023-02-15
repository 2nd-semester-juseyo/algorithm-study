import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int[][] person;
	static int sumTotal = 0;
	static int ans = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		person = new int[N+1][N+1];
		
		for (int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=1; j<=N; j++) {
				person[i][j] = Integer.parseInt(st.nextToken());
				sumTotal += person[i][j];
			}
		}
		
		for (int d1=1; d1<=N; d1++) {
			for (int d2=1; d2<=N; d2++) {
				for (int x=1; x<=N; x++) {
					for (int y=1; y<=N; y++) {
						if (y-d1 <= 0 || y+d2 > N || x+d1+d2>N)
							continue;
						
						solve(x,y,d1,d2);
						
					}
				}
			}
		}
		
		System.out.println(ans);
	}
	
	public static void solve(int x, int y, int d1, int d2) {				
		int sum[] = new int[5];
		sum[4] = sumTotal;
		
		// 구역 설정
		boolean border[][] = new boolean[N+1][N+1];
		border[x][y] = true;
		
		
		for (int i=0; i<=d1; i++) {
			border[x+i][y-i] = true;
		}
		for (int i=0; i<=d2; i++) {
			border[x+i][y+i] = true;
		}
		for (int i=1; i<=d2; i++) {
			border[x+d1+i][y-d1+i] = true;
		}
		for (int i=1; i<=d1; i++) {
			border[x+d2+i][y+d2-i] = true;
		}
		
		
		for (int i=1; i<x+d1; i++) {
			for (int j=1; j<=y; j++) {
				if (border[i][j])
					break;
				sum[0] += person[i][j]; 
			}
		}		
		for (int i=1; i<=x+d2; i++) {
			for (int j=N; j>y; j--) {
				if (border[i][j])
					break;
				sum[1] += person[i][j];
			}
		}
		for (int i=x+d1; i<=N; i++) {
			for (int j=1; j<y-d1+d2; j++) {
				if (border[i][j])
					break;
				sum[2] += person[i][j];
			}
		}
		for (int i=x+d2+1; i<=N; i++) {
			for (int j=N; j>=y-d1+d2; j--) {
				if (border[i][j])
					break;
				sum[3] += person[i][j];
			}
		}
		sum[4] = sum[4] - sum[3] - sum[2] - sum[1] - sum[0];
		
		Arrays.sort(sum);
		
		ans = Math.min(ans, sum[4] - sum[0]);
	}
	
}