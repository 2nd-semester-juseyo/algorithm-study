import java.util.*;
import java.io.*;

// 한 겹의 친구들을 쓰러트린다

// dfs로 4방향 이동하면서 방문처리를하고, 1인 경우 탐색을 중단한다.
// 그래프를 갱신하고 방문처리를 초기화 후 위를 반복한다.
public class Main {
	static boolean flag;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static boolean[][] visited;
	static char[][] arr;
	static int junanX, junanY, targetX, targetY, N, M, answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new char[N + 1][M + 1];
		visited = new boolean[N + 1][M + 1];

		st = new StringTokenizer(br.readLine());
		junanX = Integer.parseInt(st.nextToken());
		junanY = Integer.parseInt(st.nextToken());
		targetX = Integer.parseInt(st.nextToken());
		targetY = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			for (int j = 1; j <= M; j++) {
				arr[i][j] = s.charAt(j - 1);
			}
		}

		while (flag == false) {
			Init();
			dfs(junanX, junanY);
			answer++;
		}

		System.out.println(answer);
	}

	public static void dfs(int x, int y) {

		for (int i = 0; i < 4; i++) {
			int xx = x + dx[i];
			int yy = y + dy[i];

			if (xx <= 0 || yy <= 0 || xx > N || yy > M)
				continue;
			if (visited[xx][yy])
				continue;
			if (arr[xx][yy] == '1') {
				arr[xx][yy] = '0';
				visited[xx][yy] = true;
				continue;
			}
			if (arr[xx][yy] == '#') {
				visited[xx][yy] = true;
				flag = true;
				continue;
			}
			visited[xx][yy] = true;
			dfs(xx, yy);

		}
	}

	public static void Init() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				visited[i][j] = false;
			}
		}
		visited[junanX][junanY] = true;
	}
}
