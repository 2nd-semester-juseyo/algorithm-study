import java.util.*;
import java.io.*;

public class Main {
	static int[][] gear;
	static int K, answer;
	static int[] d;
	
	public static void main(String[] args) throws IOException {
		gear = new int[4][8];
		answer = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			for (int j = 0; j < 8; j++) {
				gear[i][j] = s.charAt(j) - '0';
			}
		}

		st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());

			d = new int[4];

			int gearN = Integer.parseInt(st.nextToken()) - 1;
			int dir = Integer.parseInt(st.nextToken());

			d[gearN] = dir;

			// 각 톱니바퀴가 돌아갈 방향을 체크한다.
			check(gearN);
			
			// 방향에 맞춰 돌린다.
			turn();
		}
		if (gear[0][0] == 1)
			answer += 1;
		if (gear[1][0] == 1)
			answer += 2;
		if (gear[2][0] == 1)
			answer += 4;
		if (gear[3][0] == 1)
			answer += 8;
		
		System.out.println(answer);
		
	}

	public static void check(int gearN) {
		// 왼쪽
		for (int i = gearN - 1; i >= 0; i--) {
			if (gear[i][2] != gear[i + 1][6])
				d[i] = -d[i + 1];
			else
				break;
		}

		// 오른쪽
		for (int i = gearN + 1; i < 4; i++) {
			if (gear[i-1][2] != gear[i][6])
				d[i] = -d[i-1];
			else
				break;
		}
	}
	
	public static void turn() {
		for (int i=0; i<4; i++) {
			// 시계 방향
			if (d[i] == 1) {
				int tmp = gear[i][7];
				for (int j=6; j>=0; j--) {
					gear[i][j+1] = gear[i][j];
				}
				gear[i][0] = tmp;					
			}
			// 시계 반대 방향
			else if (d[i] == -1) {
				int tmp = gear[i][0];
				for (int j=0; j<7; j++) {
					gear[i][j] = gear[i][j+1];
				}
				gear[i][7] = tmp;
			}
		}
	}
}
