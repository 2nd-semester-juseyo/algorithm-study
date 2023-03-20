import java.io.*;
import java.util.*;

public class Main {
	static int n, k;
	static int arr[][];
	static int INF = 10000;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		arr = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(i != j) arr[i][j] = INF;
			}
		}
		
		for(int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			
			arr[x][y] = arr[y][x] = 1;
		}
		
		floydWarshall();
		
		if(checkWorld())
			System.out.println("Small World!");
		else
			System.out.println("Big World!");
	}
	
	static void floydWarshall() {
		// k : 경유지
		for(int k = 0; k < n; k++) {
			// i : 출발지
			for(int i = 0; i < n; i++) {
				if(i == k) continue;
				// j : 도착지
				for(int j = 0; j < n; j++) {
					if(j == k) continue;
					
					arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
				}
			}
		}
	}
	
	static boolean checkWorld() {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(arr[i][j] > 6) return false;
			}
		}
		return true;
	}
}
