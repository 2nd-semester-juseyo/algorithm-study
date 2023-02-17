package 이차원배열과연산_17140;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Stream;

public class Main {
	static int R, C, K;
	static int[][] board;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] line = in.readLine().split(" ");
		R = Integer.parseInt(line[0]);
		C = Integer.parseInt(line[1]);
		K = Integer.parseInt(line[2]);
		
		board = new int[3][3];
		for(int i = 0; i < 3; i++) {
			line = in.readLine().split(" ");
			board[i] = Stream.of(line).mapToInt(Integer::parseInt).toArray();
			
		}
		

		System.out.println(solution());
		
	}
	
	public static int solution() {
		int time = 0;
		while(time < 100) {
			if(R-1 < board.length && C-1 < board[0].length && board[R-1][C-1] == K) {
				return time;
			}
			time++;
			// R연산
			if(board.length >= board[0].length) {
				int maxLength = 0;
				for(int i = 0; i < board.length; i++) {
					int[] sortedRow = doSort(board[i]);
					board[i] = sortedRow;
					maxLength = Math.max(maxLength, board[i].length);
				}
				for(int i = 0; i < board.length; i++) {
					if(board[i].length < maxLength) {
						int [] newLine = new int[maxLength];
						System.arraycopy(board[i], 0, newLine, 0, board[i].length);
						board[i] = newLine;
					}
				}
			} else {
				int maxLength = 0;
				ArrayList<int []> newCols = new ArrayList<>();
				for(int i = 0; i < board[0].length; i++) {
					int[] col = new int[board.length];
					for(int j = 0; j < board.length; j++) {
						col[j] = board[j][i];
					}
					int[] newCol = doSort(col);
					maxLength = Math.max(maxLength, newCol.length);
					newCols.add(newCol);
				}

				int[][] newBoard = new int[maxLength][board[0].length];
				for(int i = 0; i < newCols.size(); i++) {
					int[] col = newCols.get(i);
					for(int j = 0; j < col.length; j++) {
						newBoard[j][i] = col[j];
					}
				}
				board = newBoard;
		
				if(newBoard[0].length >= 100 || newBoard.length >= 100) {
					board = new int[100][100];
					for(int i = 0; i < 100; i++) {
						for(int j = 0; j < 100; j++) {
							board[i][j] = newBoard[i][j];
						}
					}
				}
					
			}
				
		}
		return -1;
	}
	
	public static int[] doSort(int [] line) {
		PriorityQueue<Element> queue = new PriorityQueue<>();
		int[] counts = new int[101];
		for(int i = 0; i < line.length; i++) {
			counts[line[i]] += 1;
		}

		for(int i = 0; i < line.length; i++) {
			if(line[i] != 0) {
				queue.add(new Element(line[i], counts[line[i]]));
			}
		}
		boolean [] visited = new boolean[101];
		ArrayList<Integer> ret = new ArrayList<>();;
		while(!queue.isEmpty()) {
			Element e = queue.poll();
			if(!visited[e.value]) {
				visited[e.value] = true;
				ret.add(e.value);
				ret.add(e.count);
			}
		}
		int[] newRet = new int[ret.size()];
		for(int i = 0; i < ret.size(); i++) {
			newRet[i] = ret.get(i);
		}
		return newRet;
	}
	
	public static class Element implements Comparable<Element>{
		public int value;
		public int count;
		public Element(int value, int count) {
			this.value = value;
			this.count = count;
		}
		
		@Override
		public int compareTo(Element o) {
			if(this.count == o.count) {
				return this.value - o.value;
			}
			return this.count - o.count;
		}

		@Override
		public String toString() {
			return "Element [value=" + value + ", count=" + count + "]";
		}
		
		
	}

}
