package 게리맨더링2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
	static int N;
	static int[][] board;
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
		N = Integer.parseInt(in.readLine());
		board = new int[N+1][N+1];
		for(int i = 1; i <= N; i++) {
			String[] line = in.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				board[i][j+1] = Integer.parseInt(line[j]);
			}
		}

		
		int answer = solution();		
		System.out.println(answer);
		
	}
	
	public static int solution() {
		int answer = Integer.MAX_VALUE;
		
		for (int x = 1; x <= N; x++) {
			for (int y = 1; y <= N; y++) {
				for(int d1 = 1; d1 <= N; d1++) {
					for(int d2 = 1; d2 <= N; d2++) {
						if(!canGo(x, y, d1, d2)) {
							continue;
						}
						answer = Math.min(answer, divide(x, y, d1, d2));
					}
				}
				
			}
		}
		
		return answer; 
	}
	
	public static boolean canGo(int x, int y, int d1, int d2) {
		boolean ret = false;
		if(x + d1 + d2 <= N && 1 <= y - d1 && y + d2 <= N) {
			return true;
		}
		return false;
	}
	

	
	public static int divide(int x, int y, int d1, int d2) {
		int[][] visited = new int[N+1][N+1];	
		
		for(int i = 0; i <= d1; i++) {
			visited[x + i][y - i] = 5;
			visited[x + d2 + i][y + d2 - i] = 5;
		}
		for(int i = 0; i <= d2; i++) {
			visited[x + i][y + i] = 5;
			visited[x + d1 + i][y - d1 + i] = 5;
		}
		
		
		for(int i = 1; i < x + d1; i++) {
			for(int j = 1; j <= y; j++) {
				if(visited[i][j] == 5) {
					break;
				}
				visited[i][j] = 1;
			}
		}
		
		for(int i = 1; i <= x + d2; i++) {
			for(int j = N; j > y; j--) {
				if(visited[i][j] == 5) {
					break;
				}
				visited[i][j] = 2;
			}
		}
		
		for(int i = x + d1; i <= N; i++) {
			for(int j = 1; j < y - d1 + d2; j++) {
				if(visited[i][j] == 5) {
					break;
				}
				visited[i][j] = 3;
			}
		}
		
		for(int i = x + d2 + 1; i <= N; i++) {
			for(int j = N; j >= y - d1 + d2; j--) {
				if(visited[i][j] == 5) {
					break;
				}
				visited[i][j] = 4;
			}
		}
		
	
		int[] peopleArr = new int[6];
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				peopleArr[visited[i][j]] += board[i][j];
				if(visited[i][j] == 0) {
					peopleArr[5] += board[i][j];
				}
			}
		}
		
		int minPeople = Integer.MAX_VALUE;
		int maxPeople = 0;
		for(int i = 1; i < 6; i++) {
			if(peopleArr[i] == 0) {
				return Integer.MAX_VALUE;
			}
			minPeople = Math.min(minPeople, peopleArr[i]);
			maxPeople = Math.max(maxPeople, peopleArr[i]);
		}
		
		
		return maxPeople - minPeople;
	}

}
