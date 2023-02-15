import java.io.*;
import java.util.*;

public class Main {
    
	static int n,l,r;
	static int arr[][];
	static boolean visited[][];
	static ArrayList<Node> list;
	static int dx[] = {1,0,-1,0};
	static int dy[] = {0,1,0,-1};
	
    public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 값 대입
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		arr = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(moveCount());
	}
    
    public static int moveCount() {
    	// result 일 수 반환
    	int result = 0;
    	while(true) {
    		boolean moved = false; // 인구 이동이 일어났는지 판단하는 변수
    		visited = new boolean[n][n];
    		for(int i = 0; i < n; i++) {
    			for(int j = 0; j < n; j++) {
    				if(!visited[i][j]) {
    					int sum = bfs(i,j); // bfs 탐색 인구 이동할 인원 총 합 구하기
    					if(list.size() > 1) {
    						dividePeople(sum);
    						moved = true;
    					}
    				}
    			}
    		}
    		if(!moved) return result; // 인구이동이 일어나지 않았다면 결과 반환
    		result++;
    	}
    }
    
    public static int bfs(int x, int y) {
    	Queue<Node> queue = new LinkedList<>();
    	list = new ArrayList<>();
    	
    	queue.offer(new Node(x,y));
    	list.add(new Node(x,y));
    	visited[x][y] = true;
    	
    	int sum = arr[x][y];
    	while(!queue.isEmpty()) {
    		Node now = queue.poll();
    		
    		for(int i = 0; i < 4; i++) {
    			int nx = now.x + dx[i];
    			int ny = now.y + dy[i];
    			
    			if(nx >= 0 && nx < n && ny >= 0 && ny < n && !visited[nx][ny]) {
    				int dif = Math.abs(arr[now.x][now.y] - arr[nx][ny]);
    				if(dif >= l && dif <= r) {
    					queue.offer(new Node(nx,ny));
    					list.add(new Node(nx, ny));
    					sum += arr[nx][ny];
    					visited[nx][ny] = true;
    				}
    			}
    		}
    	}
    	return sum;
    }
    
    public static void dividePeople(int sum) {
    	// 평균 대로 인원 분배
    	int avg = sum / list.size();
    	
    	for(Node n : list) {
    		arr[n.x][n.y] = avg;
    	}
    }
    
    public static class Node{
    	int x;
    	int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
    }

}
