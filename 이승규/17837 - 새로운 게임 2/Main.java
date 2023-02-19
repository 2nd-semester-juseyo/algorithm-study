import java.io.*;
import java.util.*;


public class Main {
	static int N,K;
	static int[][] map, horses;
	static Stack<Integer>[][] visited;
	static Stack<Integer> stack = new Stack<>();
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		visited = new Stack[N][N];
		horses = new int[K][3];
		
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N ; j++) {
				visited[i][j] = new Stack<>();
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0 ; i < K ; i++) {
			st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int dir = Integer.parseInt(st.nextToken()) - 1;
			horses[i][0] = x;
			horses[i][1] = y;
			horses[i][2] = dir;
			
			visited[x][y].add(i);
		}
		
		int time = 0;
		
		Loop : while(time != 1000) {
			time++;
			for(int idx = 0 ; idx <K ; idx++) {
				int x = horses[idx][0];
				int y = horses[idx][1];
				int dir = horses[idx][2];
				
				int nx = x + dx[dir];
				int ny = y + dy[dir];
				
				// 포개진 상황 고려하기, 임시 큐를 사용하여 담기
				while(visited[x][y].size() != 0) {
					int tmp = visited[x][y].pop();
					stack.add(tmp);
					if(idx == tmp) {
						break;
					}	
				}
				// 파란색 블록 or 범위 밖 
				if(0 > nx || nx >= N || 0 > ny || ny >= N || map[nx][ny] == 2) {
					// 홀수 
					if(dir % 2 != 0) dir -= 1;
					// 짝수 
					else dir += 1;
					
					nx = x + dx[dir];
					ny = y + dy[dir];
					horses[idx][2] = dir;
					// 파란색 블록 or 범위 밖 
					if(0 > nx || nx >= N || 0 > ny || ny >= N || map[nx][ny] == 2) {
						while(!stack.isEmpty()) {
							nx = x;
							ny = y;
							visited[x][y].add(stack.pop());
						}
					}
					else {
						zoneOfRedWhite(nx,ny);
					}
				}
				// 빨간색 블록 뒤집기
				else {
					zoneOfRedWhite(nx, ny);
				}
				if(visited[nx][ny].size() >= 4) break Loop; 
			}
		}
		if(time == 1000) System.out.println(-1);
		else System.out.println(time);
	}
	
	private static void zoneOfRedWhite(int nx, int ny) {
		if(map[nx][ny] == 1) {
			Stack<Integer> stackTmp = new Stack<Integer>();
			while(!stack.isEmpty()) {
				stackTmp.add(stack.pop());
			}
			while(!stackTmp.isEmpty()) {
				int tmp = stackTmp.pop();
				horses[tmp][0] = nx;
				horses[tmp][1] = ny;
				visited[nx][ny].add(tmp);
			}
		}
		// 하얀색 
		else {
			while(!stack.isEmpty()) {
				int tmp = stack.pop();
				horses[tmp][0] = nx;
				horses[tmp][1] = ny;
				visited[nx][ny].add(tmp);
			}
		}
	}
}
