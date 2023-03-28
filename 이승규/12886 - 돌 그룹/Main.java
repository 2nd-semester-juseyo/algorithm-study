import java.io.*;
import java.util.*;

public class Main {
	
	static int A, B, C;
	static boolean visited[][];
	static int result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		bfs(A,B,C);
		
		System.out.println(result);
	}
	
	public static void bfs(int a, int b, int c) {
		if((a+b+c) % 3 != 0) return;
		
		Queue<Stone> q = new LinkedList<>();
		visited = new boolean[1501][1501];
	
		q.offer(new Stone(a,b,c));
		visited[a][b] = true;
		
		while(!q.isEmpty()) {
			Stone cur = q.poll();
			
			if(cur.a == cur.b && cur.b == cur.c) {
				result = 1;
				return;
			}
			
			if(cur.a != cur.b) {
				int na = cur.a > cur.b? cur.a - cur.b: cur.a * 2;
				int nb = cur.b > cur.a? cur.b - cur.a: cur.b * 2;
				
				if(!visited[na][nb]) {
					q.offer(new Stone(na, nb, cur.c));
					visited[na][nb] = true;
				}
			}
			
			if(cur.b != cur.c) {
				int nb = cur.b > cur.c? cur.b - cur.c: cur.b * 2;
				int nc = cur.c > cur.b? cur.c - cur.b: cur.c * 2;
				
				if(!visited[nb][nc]) {
					q.offer(new Stone(cur.a, nb, nc));
					visited[nb][nc] = true;
				}
			}
			
			if(cur.a != cur.c) {
				int na = cur.a > cur.c? cur.a - cur.c: cur.a * 2;
				int nc = cur.c > cur.a? cur.c - cur.a: cur.c * 2;
				
				if(!visited[na][nc]) {
					q.offer(new Stone(na, cur.b, nc));
					visited[na][nc] = true;
				}
			}
		}
		
	}
	
	public static class Stone{
		int a, b, c;
		
		public Stone(int a, int b, int c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}
	}
}
