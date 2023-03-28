import java.io.*;
import java.util.*;

public class Main {
	static int n, q, count;
	static int INF = 1000000000;
	static ArrayList<Node>[] graph;
	static ArrayList<Integer> countList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[n+1];
		countList = new ArrayList<>();
		
		for(int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < n-1; i++) {
			st = new StringTokenizer(br.readLine());
			
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			
			graph[p].add(new Node(q,r));
			graph[q].add(new Node(p,r));
		}
		
		for(int i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine());
			
			int k = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			int count = 0;
			
			Queue<Integer> q = new LinkedList<>();
			boolean[] visited = new boolean[n+1];
			
			visited[v] = true;
			q.offer(v);
			
			while(!q.isEmpty()) {
				int num = q.poll();
				List<Node> list = graph[num];
				
				for(int j = 0; j < list.size(); j++) {
					if(visited[list.get(j).idx]) continue;
					
					if(list.get(j).val < k) continue;
					
					count++;
					q.offer(list.get(j).idx);
					visited[list.get(j).idx] = true;
				}
			}
			countList.add(count);
		}
		
		for(int i = 0; i < countList.size(); i++) {
			System.out.println(countList.get(i));
		}
		
	}

	public static class Node {
		int idx, val;

		public Node(int idx, int val) {
			this.idx = idx;
			this.val = val;
		}
	}
}
