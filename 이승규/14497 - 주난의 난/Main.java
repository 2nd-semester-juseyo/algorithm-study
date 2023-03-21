import java.io.*;
import java.util.*;

public class Main {
	static int n, m, startX, startY, endX, endY;
	static char room[][];
	static int dx[] = {0,-1,0,1};
	static int dy[] = {1,0,-1,0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		room = new char[n+1][m+1];
		
		st = new StringTokenizer(br.readLine());
		startX = Integer.parseInt(st.nextToken());
		startY = Integer.parseInt(st.nextToken());
		endX = Integer.parseInt(st.nextToken());
		endY = Integer.parseInt(st.nextToken());
		
		for(int i = 1; i <= n; i++) {
			String roomInput = br.readLine();
			for(int j = 1; j <= m; j++) {
				room[i][j] = roomInput.charAt(j-1);
			}			
		}
		
		dijkstra();
		
	}
	public static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean visited[][] = new boolean[n+1][m+1];
		
		pq.offer(new Node(startX, startY, 0));
		visited[startX][startY] = true;
		
		while(!pq.isEmpty()) {
			Node current = pq.poll();
			
			if(current.x == endX && current.y == endY) {
				System.out.println(current.time);
				return;
			}
			
			for(int i = 0; i < 4; i++) {
				int nx = current.x + dx[i];
				int ny = current.y + dy[i];
				
				if(nx <= 0 || nx > n || ny <= 0 || ny > m || visited[nx][ny]) continue;
				
				visited[nx][ny] = true;
				
				pq.offer(new Node(nx, ny, room[nx][ny] == '0' ? current.time : current.time+1));
				
			}
		}
	}
	
	public static class Node implements Comparable<Node>{
		int x, y, time;
		
		public Node(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.time - o.time;
		}
	}
}
