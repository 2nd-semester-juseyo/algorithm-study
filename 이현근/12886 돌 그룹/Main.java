import java.io.*;
import java.util.*;

public class Main {
	static boolean flag;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		visited = new boolean[1501][1501];

		if ((A + B + C) % 3 != 0) {
			System.out.println(0);
		} else {
			dfs(A, B, C);
			System.out.println(flag == true? 1 : 0);
		}

	}
	
	public static void dfs(int a, int b, int c) {

		if (a == b && b == c) {
			flag = true;
			return;
		}
		// 돌을 고르는 경우는 3가지가 존재
		// visited를 처리하지 않으면 돌의 개수가 반복되는 경우가 생겨서 StackOverFlow발생
		// 2차원 배열을 통해 한 번 가졌던 개수의 돌이 나오지 않게 함
		if (a != b) {
			if (a > b) {
				if (!visited[a - b][b + b]) {
					visited[a - b][b + b] = true;
					visited[b + b][a - b] = true;
					dfs(a - b, b + b, c);
				}
			} else {
				if (!visited[a + a][b - a]) {
					visited[a + a][b - a] = true;
					visited[b - a][a + a] = true;
					dfs(a + a, b - a, c);
				}
			}
		}
		if (a != c) {
			if (a > c) {
				if (!visited[a - c][c + c]) {
					visited[a - c][c + c] = true;
					visited[c + c][a - c] = true;
					dfs(a - c, b, c + c);
				}
			} else {
				if (!visited[a + a][c - a]) {
					visited[a + a][c - a] = true;
					visited[c - a][a + a] = true;
					dfs(a + a, b, c - a);
				}
			}
		}
		if (b != c) {
			if (b > c) {
				if (!visited[b-c][c+c]) {
					visited[b-c][c+c] = true;
					visited[c+c][b-c] = true;
					dfs(a, b - c, c + c);
				}
			} else {
				if (!visited[b+b][c-b]) {
					visited[b+b][c-b] = true;
					visited[c-b][b+b] = true;
					dfs(a, b + b, c - b);
				}
			}
		}
	}
}
