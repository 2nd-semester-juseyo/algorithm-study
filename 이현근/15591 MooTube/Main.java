import java.io.*;
import java.util.*;

public class Main {
	static int N, Q, k;
	static boolean[] visited;
	static List<List<Pair>> list;
	static int answer;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		list = new ArrayList<>();
		for (int i=0; i<=5000; i++) {
			list.add(new ArrayList<Pair>());
		}
		visited = new boolean[N+1];
		
		for (int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int usado = Integer.parseInt(st.nextToken());
		
			list.get(a).add(new Pair(b, usado));
			list.get(b).add(new Pair(a, usado));
		}
		
		for (int i=0; i<Q; i++) {
			st = new StringTokenizer(br.readLine());
			
			init();
			
			// 유사도가 k이상
			k = Integer.parseInt(st.nextToken());
			// v로부터 dfs 시작
			int v = Integer.parseInt(st.nextToken());
			visited[v] = true;
			
			dfs(v, 1_000_000_001);
			
			System.out.println(answer);
		}
		
	}
	
	public static void dfs(int node, int currUsado) {		
		
		for (Pair p : list.get(node)) {
			if (!visited[p.node]) { 
				visited[p.node] = true;
				
				int usado = Math.min(p.usado, currUsado);
				
				if (usado >= k) {
					answer++;
				}
				
				dfs(p.node, usado);
			}
		}
	}
	
	public static void init() {
		// 방문 초기화
		for (int i=1; i<=N; i++)
			visited[i] = false;
		
		answer = 0;
	}
}

class Pair {
	int node;
	int usado;
	
	Pair(int node, int usado) {
		this.node = node;
		this.usado = usado;
	}
}
