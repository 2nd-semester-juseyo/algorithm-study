import java.util.*;
import java.io.*;

public class Main {
	static int N, M, X, answer;
	static List<List<Integer>> list;
	static Queue<Integer> q;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		q = new LinkedList<>();
		list = new ArrayList<>();
		visited = new boolean[N + 1];
		
		// 2차원 리스트를 초기화
		for (int i=0; i<=N; i++) 
			list.add(new ArrayList<Integer>());
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			// 선행 작업 a
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			list.get(b).add(a);
		}

		st = new StringTokenizer(br.readLine());
		X = Integer.parseInt(st.nextToken());

		visited[X] = true;
		answer = 0;
		q.add(X);

		while (!q.isEmpty()) {
			int num = q.poll();

			for (int n : list.get(num)) {
				// 선행 작업 n
				if (!visited[n]) {
					answer++;
					visited[n] = true;
					q.add(n);
				}
			}
		}

		System.out.println(answer);
	}
}
