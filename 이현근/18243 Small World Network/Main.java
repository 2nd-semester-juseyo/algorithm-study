import java.util.*;
import java.io.*;

public class Main {
	static int N, K;
	static int[][] arr;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N+1][N+1];
		for (int i=1; i<=N; i++) {
			for (int j=1; j<=N; j++) {
				arr[i][j] = 1_000_000;
			}
		}
		
		for (int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			arr[a][b] = 1;
			arr[b][a] = 1;
		}
		
		for (int k=1; k<=N; k++)
			for (int i=1; i<=N; i++)
				for (int j=1; j<=N; j++) {
					arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
				}
		
		for (int i=1; i<=N; i++) {
			for (int j=1; j<=N; j++) {				
				if (arr[i][j] > 6) {
					System.out.println("Big World!");
					System.exit(0);				
				}
			}			
		}
		
		System.out.println("Small World!");
		
	}
}
