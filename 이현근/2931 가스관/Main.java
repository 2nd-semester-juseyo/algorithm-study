import java.util.*;
import java.io.*;

public class Main {
	static int R;
	static int C;
	static char arr[][];
	static int row = 0;
	static int col = 0;
	static boolean flag = true;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new char[R][C];

		// 배열 초기화
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			for (int j = 0; j < C; j++) {
				arr[i][j] = s.charAt(j);
				
			}
		}

		// 순회한다
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				// 특정 파이프의 모양인 경우 연결된 것을 확인한다.
				if (arr[i][j]!=('.')) {
					check(i, j, arr[i][j]);
//					System.out.println(arr[i][j]);
				}
			}
		}
		
		System.out.println((row+1) + " " + (col+1) + " " + solve(row,col));
		
	}

	public static void check(int r, int c, char pipe) {
		if (pipe == ('|')) {
			if (arr[r-1][c]==('.') || arr[r-1][c]==('2') ||
					arr[r-1][c]==('3') || arr[r-1][c]==('-')) {
				row = r-1;
				col = c;
				flag = false;
			}
			else if (arr[r+1][c]==('.') || arr[r+1][c]==('1') ||
					arr[r+1][c]==('4') || arr[r+1][c]==('-') ) {
				row = r+1;
				col = c;
				flag = false;
			}
//			System.out.println("|");	
		}
		else if (pipe==('-')) {
			if (arr[r][c+1]==('.') || arr[r][c+1]==('1') ||
					arr[r][c+1]==('2') || arr[r][c+1]==('|')) {
				row = r;
				col = c+1;
				flag = false;
			}
			else if (arr[r][c-1]==('.') || arr[r][c-1]==('3') ||
					arr[r][c-1]==('4') || arr[r][c-1]==('|') ) {
				row = r;
				col = c-1;
				flag = false;
			}
//			System.out.println("-");
		}
		else if (pipe==('+')) {
			if (arr[r-1][c]==('.') || arr[r-1][c]==('2') ||
					arr[r-1][c]==('3') || arr[r-1][c]==('-')) {
				row = r-1;
				col = c;
				flag = false;
			}
			else if (arr[r+1][c]==('.') || arr[r+1][c]==('1') ||
					arr[r+1][c]==('4') || arr[r+1][c]==('-') ) {
				row = r+1;
				col = c;
				flag = false;
			}
			else if (arr[r][c+1]==('.') || arr[r][c+1]==('1') ||
					arr[r][c+1]==('2') || arr[r][c+1]==('|')) {
				row = r;
				col = c+1;
				flag = false;
			}
			else if (arr[r][c-1]==('.') || arr[r][c-1]==('3') ||
					arr[r][c-1]==('4') || arr[r][c-1]==('|') ) {
				row = r;
				col = c-1;
				flag = false;
			}
//			System.out.println("+");
		}
		else if (pipe==('1')) {
			if (arr[r][c+1]==('.') || arr[r][c+1]==('1') ||
					arr[r][c+1]==('2') || arr[r][c+1]==('|')) {
				row = r;
				col = c+1;
				flag = false;
			}
			else if (arr[r+1][c]==('.') || arr[r+1][c]==('1') ||
					arr[r+1][c]==('4') || arr[r+1][c]==('-') ) {
				row = r+1;
				col = c;
				flag = false;
			}
//			System.out.println("1");
		}
		else if (pipe==('2')) {
			if (arr[r-1][c]==('.') || arr[r-1][c]==('2') ||
					arr[r-1][c]==('3') || arr[r-1][c]==('-')) {
				row = r-1;
				col = c;
				flag = false;
			}
			else if (arr[r][c+1]==('.') || arr[r][c+1]==('1') ||
					arr[r][c+1]==('2') || arr[r][c+1]==('|')) {
				row = r;
				col = c+1;
				flag = false;
			}
//			System.out.println("2");
		}
		else if (pipe==('3')) {
			if (arr[r-1][c]==('.') || arr[r-1][c]==('2') ||
					arr[r-1][c]==('3') || arr[r-1][c]==('-')) {
				row = r-1;
				col = c;
				flag = false;
			}
			else if (arr[r][c-1]==('.') || arr[r][c-1]==('3') ||
					arr[r][c-1]==('4') || arr[r][c-1]==('|') ) {
				row = r;
				col = c-1;
				flag = false;
			}
//			System.out.println("3");
		}
		else if (pipe==('4')) {
			if (arr[r][c-1]==('.') || arr[r][c-1]==('3') ||
					arr[r][c-1]==('4') || arr[r][c-1]==('|') ) {
				row = r;
				col = c-1;
				flag = false;
			}
			else if (arr[r+1][c]==('.') || arr[r+1][c]==('1') ||
					arr[r+1][c]==('4') || arr[r+1][c]==('-') ) {
				row = r+1;
				col = c;
				flag = false;
			}
//			System.out.println("4");
		}
	}
	
	// 무엇이 와야 할까
	public static char solve(int r, int c) {
				
		flag = true;
		if (r-1 >= 0 && r+1 <R && c-1 >= 0 && c+1 <C) {
			check(r, c, '+');
			if (arr[r-1][c] == 'M' || arr[r-1][c] == 'Z')
				flag = false;
			if (arr[r+1][c] == 'M' || arr[r+1][c] == 'Z')
				flag = false;
			if (arr[r][c+1] == 'M' || arr[r][c+1] == 'Z')
				flag = false;
			if (arr[r][c-1] == 'M' || arr[r][c-1] == 'Z')
				flag = false;
			
			if (flag)
				return '+';			
		}
		
		flag = true;
		if (c-1 >= 0 && c+1 < C) {
			check(r, c, '-');			
			if (arr[r][c+1] == 'M' || arr[r][c+1] == 'Z')
				flag = false;
			if (arr[r][c-1] == 'M' || arr[r][c-1] == 'Z')
				flag = false;
			
			if (flag)
				return '-';			
		}
		
		flag = true;
		if (r-1 >= 0 && r+1 < R) {
			check(r, c, '|');
			if (arr[r-1][c] == 'M' || arr[r-1][c] == 'Z')
				flag = false;
			if (arr[r+1][c] == 'M' || arr[r+1][c] == 'Z')
				flag = false;
			
			if (flag)
				return '|';			
		}
		
		flag = true;
		if (r+1 < R && c+1 < C) {
			check(r, c, '1');
			
			if (arr[r+1][c] == 'M' || arr[r+1][c] == 'Z')
				flag = false;
			if (arr[r][c+1] == 'M' || arr[r][c+1] == 'Z')
				flag = false;
			
			
			if (flag)
				return '1';
						
		}
		
		flag = true;
		if (r-1 >= 0 && c+1 < C) {
			check(r, c, '2');
			if (arr[r-1][c] == 'M' || arr[r-1][c] == 'Z')
				flag = false;
			
			if (arr[r][c+1] == 'M' || arr[r][c+1] == 'Z')
				flag = false;
			
			
			if (flag)
				return '2';			
		}
		
		flag = true;
		if (c-1 >= 0 && r-1 >= 0) {
			check(r, c, '3');
			if (arr[r-1][c] == 'M' || arr[r-1][c] == 'Z')
				flag = false;
			
			
			if (arr[r][c-1] == 'M' || arr[r][c-1] == 'Z')
				flag = false;
			if (flag)
				return '3';			
		}
		
		if (c-1 >= 0 && r+1 < R) {
			flag = true;
			check(r, c, '4');
			
			if (arr[r+1][c] == 'M' || arr[r+1][c] == 'Z')
				flag = false;
			
			if (arr[r][c-1] == 'M' || arr[r][c-1] == 'Z')
				flag = false;
			if (flag)
				return '4';			
		}
		
		
		return '0';
	}
}
