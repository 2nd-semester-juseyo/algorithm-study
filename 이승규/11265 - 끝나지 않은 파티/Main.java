import java.io.*;
import java.util.*;

public class Main {
	static int n, m;
	static int arr[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[n+1][n+1];
		
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		floydWarshall();
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			if(arr[x][y] > Integer.parseInt(st.nextToken())) {
				System.out.println("Stay here");
			}else {
				System.out.println("Enjoy other party");
			}
		}
	}
	
	static void floydWarshall() {
		// k : 경유지
		for(int k = 1; k <= n; k++) {
			// i : 출발지
			for(int i = 1; i <= n; i++) {
				if(i == k) continue;
				// j : 도착지
				for(int j = 1; j <= n; j++) {
					if(j == k) continue;
					arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
				}
			}
		}
	}
}
