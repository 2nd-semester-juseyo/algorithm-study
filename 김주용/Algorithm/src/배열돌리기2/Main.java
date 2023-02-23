package 기출문제.배열돌리기2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	static int[] dy = { 1, 0, -1, 0 };
	static int[] dx = { 0, 1, 0, -1 };
	
	static int[][] board;
	
	static int N;
	static int M;
	static int R;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		board = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < M; j++) {
				int cur = Integer.parseInt(st.nextToken());
				board[i][j] = cur;
			}
		}

		solution();
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(board[i][j]).append(" ");
			}
			sb.append("\n");
		}
		out.write(sb.toString());
		in.close();
		out.close();
	}

	public static void solution() {
		int layer = Math.min(N, M) / 2;
		int[] rotateCnt = new int[layer]; 
		for (int i = 0; i < layer; i++) {
			int cnt = 2*(N - 1 - 2*i) + 2*(M - 1 - 2*i);//이만큼 돌리면 원래 상태로 돌아옴 
			rotateCnt[i] = R % cnt;
		}
		// 10000 
		boolean[][] visited = new boolean[N][M];
		
		for (int i = 0; i < layer; i++){
	
			rotate(i, rotateCnt[i], i, i);
		}

	}

	public static void rotate(int layer, int rotateCnt, int startY, int startX) {
		int minX = layer, maxX = M - layer;
		int minY = layer, maxY = N - layer;
		for(int i = 0; i < rotateCnt; i++) {
			int cy = startY, cx = startX;
			int cd = 0;
			int[][] newBoard = copy();
			while (true) {
				int ny = cy + dy[cd];
				int nx = cx + dx[cd];
				//System.out.printf("%d: %d, %d\n", cd, ny, nx);
				
				if (minY > ny || ny >= maxY || minX > nx || nx >= maxX) {
					cd = (cd + 1) % 4;
					continue;
				}
				
				board[ny][nx] = newBoard[cy][cx];
				if (ny == startY && nx == startX) {
					break;
				}
				cy = ny;
				cx = nx;
			}
		}
	}

	public static int[][] copy() {
		int[][] newArr = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				newArr[i][j] = board[i][j];
			}
		}
		return newArr;
	}
}
