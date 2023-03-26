import java.util.*;
import java.io.*;

public class Main {
	static int startX, startY, destX, destY, N, threshold = 1_000_000_000; 
	static List<Pos> list;
	static PriorityQueue<Pos> pq;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		startX = Integer.parseInt(st.nextToken());
		startY = Integer.parseInt(st.nextToken());
		destX = Integer.parseInt(st.nextToken());
		destY = Integer.parseInt(st.nextToken());
		list = new ArrayList<Pos>();
		pq = new PriorityQueue<>();
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		// 도시에 대한 거리의 정보 초기화
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			list.add(new Pos(x, y, threshold));
		}
		list.add(new Pos(destX, destY, threshold));
		
		pq.add(new Pos(startX, startY, 0));
		
		while (!pq.isEmpty()) {
			Pos p = pq.poll();
			
			int x = p.x;
			int y = p.y;
			int dist = p.dist;
			
			for (Pos pos : list) {
				int distance = (int)Math.sqrt(Math.pow(x - pos.x, 2) + Math.pow(y - pos.y, 2));
				
				// 두 위치의 거리가 소수가 아닌 경우
				if (!isPrime(distance)) continue;
				// 경유하는 거리가 기존의 거리와 크거나 같은 경우
				if (dist + distance >= pos.dist) continue;
				// 거리를 갱신한다.
				pos.dist = dist + distance;
				pq.add(pos);
			}
		}
		
		System.out.println(list.get(list.size()-1).dist == threshold? -1 : list.get(list.size()-1).dist);
	}
	
	public static boolean isPrime(int num) {
		if (num == 0)
			return false;
		if (num == 1)
			return false;
		if (num == 2)
			return true;
		for (int i=2; i<=Math.sqrt(num); i++) {
			if (num%i == 0)
				return false;
		}
		return true;
	}
}

class Pos implements Comparable<Pos>{
	int x;
	int y;
	int dist;
	
	Pos(int x, int y, int dist) {
		this.x = x;
		this.y = y;
		this.dist = dist;
	}

	// 거리가 짧은 녀석을 우선으로 방문한다.
	@Override
	public int compareTo(Pos o) {
		return this.dist - o.dist;
	}
}
