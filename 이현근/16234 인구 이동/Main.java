import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int L;
	static int R;
	static int arr[][];
	static boolean visited[][];
	static int dr[] = {0,1,0,-1};
	static int dc[] = {1,0,-1,0};	
	static Queue<Pair> q = new LinkedList<Pair>();
	static int groupSum = 0;
	static int groupCount = 0;
	static int groupTotal = 0;
	static boolean flag = true;
	static int answer = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());		
		arr = new int[N][N];
		visited = new boolean[N][N];
		
		for (int i=0; i<N; i++) {			
			st = new StringTokenizer(br.readLine());
			
			for (int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}						
		
		// groupCount == N이면 종료
		while (flag) {			
			
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					if (!visited[i][j]) {
						// 한 그룹에 대해 방문이 끝나면 인구수를 정산한다.
						dfs(i, j);
						groupTotal++;
						cal();
					}					
				}
			}
//			System.out.println(groupTotal);
			if (groupTotal == N*N)
				flag = false;
			else
				answer += 1;			
			// 방문 초기화			
			clear();
			groupTotal = 0;
		}
		
		System.out.println(answer);
	}
	public static void clear() {
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				visited[i][j] = false;
			}
		}
	}
	public static void show() {
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
	public static void cal() {
		while (!q.isEmpty()) {
			int row = q.peek().r;
			int col = q.poll().c;
			
			arr[row][col] = groupSum/groupCount;			
		}
		
		groupCount = 0;
		groupSum = 0;
	}
	public static void dfs(int row, int col) {
		// 방문처리하고 위치 기록, 그룹의 합 계산
		visited[row][col] = true;
		q.add(new Pair(row, col));
		groupSum += arr[row][col];
		groupCount += 1;		
		
		// 4 방향에 대한 처리
		for (int i=0; i<4; i++) {
			int nr = row + dr[i];
			int nc = col + dc[i];
			
			// 범위를 벗어날 때
			if (nr < 0 || nc < 0 || nr >= N || nc >= N) 
				continue;
			
			// 이미 방문한 곳일때
			if (visited[nr][nc])
				continue;			
			
			// 열리는 조건인 경우
			if (L <= Math.abs(arr[nr][nc] - arr[row][col]) && Math.abs(arr[nr][nc] - arr[row][col]) <= R) {
				dfs(nr, nc);
			}
						
		}
	}
	
	public static class Pair{
		int r, c;
		Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}