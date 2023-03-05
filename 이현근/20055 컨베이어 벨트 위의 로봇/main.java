import java.io.*;
import java.util.*;

public class Main {
	static int N, K, ans;
	static int[] belt;
	static boolean[] robot;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		belt = new int[2*N];
		robot = new boolean[N];
		ans = 0;
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<2*N; i++) {
			belt[i] = Integer.parseInt(st.nextToken());
		}
		
		// belt 회전 
		// 가장 먼저 올라간 로봇부터 벨트가 회전하는 방향으로 이동할 수 있으면 이동
		// 로봇이 없고, 내구도가 1이상 이어야 한다.
		// 올리는 위치의 내구도가 0이 아니면 로봇을 올린다.
		// 내구도가 0인 칸의 수가 K개 이상이면 종료
		
		while (check()) {
			turn();
			move();
			putRobot();
			ans++;
		}
		System.out.println(ans);
		
	}
	
	public static void turn() {
		// 벨트가 회전
		int tmp = belt[2*N-1];
		for (int i = 2*N-2; i>=0; i--) {
			belt[i+1] = belt[i];
		}
		belt[0] = tmp;
		
		// 로봇 위치도 한칸씩 옮겨짐.
		for (int i = N-2; i>=0; i--) {
			if (robot[i] == true) {
				robot[i+1] = true;
				robot[i] = false;
			}
		}
		
		// 로봇을 내린다
		if (robot[N-1])
			robot[N-1] = false;
	}
	
	public static void move() {
		// 가장 오른쪽의 로봇부터 이동시킨다.
		for (int i=N-2; i>=0; i--) {
			if(robot[i]== true && robot[i+1] == false && belt[i+1] >= 1) {
				robot[i+1] = true;
				robot[i] = false;
				belt[i+1]--;
				
				// 움직였는데 내리는 위치면 바로 내린다.
				if (i+1 == N - 1) {
					robot[i+1] = false;
				}
			}
			
			
		}
	}
	
	public static void putRobot() {
		if (belt[0] > 0) {
			robot[0] = true;
			belt[0]--;
		}
	}
	
	public static boolean check() {
		int cnt = 0;
		for (int i=0; i<2*N; i++) {
			if (belt[i] == 0)
				cnt++;
		}
		
		if (cnt >= K)
			return false;
		
		return true;
	}
}
