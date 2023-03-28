import java.io.*;
import java.util.*;

public class Main {
	static int n, m, count;
	static int arr[][], big[], small[];
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[n+1][n+1];
		
		for(int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			int front = Integer.parseInt(st.nextToken());
			int back = Integer.parseInt(st.nextToken());
			
			arr[front][back] = 1;
			arr[back][front] = -1;
		}
		
		floydWarshall();
		divideBall();
		countBall((n-1)/2);
		
		System.out.println(count);
	}
	
	public static void floydWarshall() {
		// k : 경유지
		for(int k = 1; k <= n; k++) {
			// i : 출발지
			for(int i = 1; i <= n; i++) {
				if(k == i) continue;
				// j : 도착지
				for(int j = 1; j <= n; j++) {
					if(j == i) continue;
					
					if(arr[j][k] != 0 && arr[i][k] == arr[k][j])
						arr[i][j] = arr[i][k];
				}
			}
		}
	}
	
	public static void divideBall() {
		big = new int[n+1];
		small = new int[n+1];
		
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				if(arr[i][j] == 1) big[i]++;
				if(arr[i][j] == -1) small[i]++;
			}
		}
		
	}
	
	public static void countBall(int num) {
		for(int i = 1; i <= n; i++) {
			if(big[i] > num) count++;
			if(small[i] > num) count++;
		}
	}
}
