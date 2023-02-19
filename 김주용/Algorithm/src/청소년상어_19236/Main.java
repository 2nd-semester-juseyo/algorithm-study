package 청소년상어_19236;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dx = {0,  -1, -1, -1, 0, 1, 1, 1};
	
	static int[] shark = {0, 0};
	
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int[][][] board = new int[4][4][2];
		for(int i = 0; i < 4; i++) {
			String[] line = in.readLine().split(" ");
			for(int j = 0; j < 8; j += 2) {
				int num = Integer.parseInt(line[j]);
				int direct = Integer.parseInt(line[j+1]) - 1;
				board[i][j/2] = new int[] {num, direct};
			}
		}
		
		int num = board[0][0][0];
		int dir = board[0][0][1];
		board[0][0] = new int[] {-1, board[0][0][1]};
		
		moveShark(board, 0, 0, dir, num);
		
		System.out.println(answer);
		
		
	}
	
	public static void moveShark(int[][][] board, int y, int x, int dir, int eat) {
		if(eat >= 136) {
			return;
		}
		
		
		
		answer = Math.max(answer, eat);
		
		board = moveFish(board.clone());
		
		
		System.out.println(eat);
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				System.out.print(board[i][j][0] + " " );
			}
			System.out.println("");
		}
		System.out.println("---------");
		
		for(int i = 1; i < 4; i++) {
			int ny = y + dy[dir] * i;
			int nx = x + dx[dir] * i;
			
			if(-1 < ny && ny < 4 && -1 < nx && nx < 4 ) {
				if(board[ny][nx][0] > 0) {
					
					int eatNum = board[ny][nx][0];
					int eatDir = board[ny][nx][1];
					board[ny][nx] = new int[] {-1, eatDir};
					moveShark(board.clone(), ny, nx, eatDir, eat + eatNum);	
					
					board[ny][nx] = new int []{eatNum, eatDir};
					
				}
			}
		}		
		
	}
	
	
	public static int[][][] moveFish(int[][][] board) {
		
		for(int num = 1; num <= 16; num++) {
			int[] index = indexOf(board, num);
			
			if(index[0] == -1) {
				continue;
			}
			int y = index[0], x = index[1];
			int d = board[y][x][1];
		
			
			for(int i = 0; i < 8; i++) {
				int nd = (d + i) % 8;
				int ny = y + dy[nd], nx = x + dx[nd];
				
				if(-1 < ny && ny < 4 && -1 < nx && nx < 4 ) {
					if(board[ny][nx][0] < 0) {
						continue;
					}
					if (board[ny][nx][0] > 0) {
						board[y][x] = board[ny][nx];
					} else {
						board[y][x] = new int[] {0, 0};
					}
					board[ny][nx] = new int[] {num, nd};
					break;
				}
			}
		}
		int[][][] newBoard = board.clone();
		return newBoard;
	}
	
	public static int[] indexOf(int[][][] board, int num) {
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				if(board[i][j][0] == num) {
					return new int[] {i, j};
				}
			}
		}
		return new int []{-1, -1};
	}

}
