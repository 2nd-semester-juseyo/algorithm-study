import java.util.*;
import java.io.*;

public class Main {
	static int N, M, answer;
	static int[][] arr;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N+1][N+1];
		
		
		for (int i=1; i<=N; i++) {
			for (int j=1; j<=N; j++) {
				arr[i][j] = 100_000_001;
			}
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			arr[a][b] = 1;
		}
		
		for (int k=1; k<=N; k++)
			for (int i=1; i<=N; i++)
				for (int j=1; j<=N; j++) {
					arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
				}
		
		// 각 행마다 체크
		for (int i=1; i<=N; i++) {
			int cnt = 0;
			for (int j=1; j<=N; j++) {
				if (arr[i][j] != 100_000_001)
					cnt++;
			}
			if (cnt > N/2)
				answer++;
		}
		// 각 열마다 체크
		for (int i=1; i<=N; i++) {
			int cnt = 0;
			for (int j=1; j<=N; j++) {
				if (arr[j][i] != 100_000_001)
					cnt++;
			}
			if (cnt > N/2)
				answer++;
		}
		
		System.out.println(answer);
	}
}
