import java.util.*;
import java.io.*;

public class Main {
	static int[][] arr;
	static boolean[][] visited;
	static boolean[][] visited2;
	static Queue<Integer> q = new LinkedList<>();
	static int dx[] = {0,1,0,-1};
	static int dy[] = {1,0,-1,0};
	static int N, M, R;
	static int cnt = 0;
	static int tmp = 0;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		visited = new boolean[N][M];
		visited2 = new boolean[N][M];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int x = 0, y = 0;
		
		// 다 방문하면 끝
		while (cnt != N*M) {
			// 방문해서 q에 집어넣는다.
			tmp = 0;
			travel(x, y);
			
			
			// 로테이션 돌린다.
			// 앞에걸 맨 뒤로 보낸다.
			int r = R % tmp;
			while (r > 0) {
				q.add(q.poll());
				r--;
			}
			
			// arr의 위치를 갱신한다.
			reArrange(x, y);
			
			x++;
			y++;
		}
		
		for (int i=0; i<N; i++) {
			
			for (int j=0; j<M; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println("");
		}
		
	}
	
	public static void travel(int row, int col) {
		q.add(arr[row][col]);
		visited[row][col] = true;		
		tmp++;
		
		for (int i=0; i<4; i++) {
			while(true) {
				int nrow = row + dx[i];
				int ncol = col + dy[i];
				
				if (ncol >= M || nrow >= N || ncol < 0 || nrow < 0) break;
				if (visited[nrow][ncol]) break;
				
				q.add(arr[nrow][ncol]);
				visited[nrow][ncol] = true;
				
				row = nrow;
				col = ncol;
				tmp++;
			}
		}
		
	}
	
	public static void reArrange(int row, int col) {
		arr[row][col] = q.poll();
		visited2[row][col] = true;
		cnt++;
		
		for (int i=0; i<4; i++) {
			while(true) {
				int nrow = row + dx[i];
				int ncol = col + dy[i];
				
				if (ncol >= M || nrow >= N || ncol < 0 || nrow < 0) break;
				if (visited2[nrow][ncol]) break;
				
				visited2[nrow][ncol] = true;
				arr[nrow][ncol] = q.poll();
				cnt++;
				
				row = nrow;
				col = ncol;
			}
		}
	}
	
}
