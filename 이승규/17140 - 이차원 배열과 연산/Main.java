import java.io.*;
import java.util.*;

public class Main {

	static int r, c, k;
	static int A[][] = new int[101][101];
	static int xLeng = 3; // 행
	static int yLeng = 3; // 열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= 3; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// ----인풋값 세팅
		System.out.println(result());
	}

	static int result() {
		for (int res = 0; res <= 100; res++) {
			if (A[r][c] == k) return res;
			operation();
		}
		return -1;
	}

	static void operation() {
		// R연산 할지, C연산 할지
		if (xLeng >= yLeng) {
			for (int i = 1; i <= xLeng; i++) {
				operR(i);
			}
		} else {
			for (int i = 1; i <= yLeng; i++) {
				operC(i);
			}
		}
	}

	static void operR(int key) {
		// R 연산 행연산 A[fixed][move]
		// HashMap k: num, v: count, key가 없는 경우 해당 key로 map 생성
		// 배열 max 101 설정 넘어가면 없어짐
		// 숫자와 카운트를 받을 해쉬맵
		PriorityQueue<Pair> q = new PriorityQueue<>();
		HashMap<Integer, Integer> map = new HashMap<>(); 
		for (int j = 1; j <= yLeng; j++) {
			// 0인 값은 건너뛰기
			if (A[key][j] == 0) continue;
			map.compute(A[key][j], (num, count) -> count == null ? 1 : count + 1);
			
//			if (map.containsKey(j)) {
//				// 해당 키를 가지고 있다면
//				map.compute(j, (k, v) -> v++);
//				map.put(j,(map.get(j)+1));
//			} else {
//				// 해당 키를 가지고 있지 않다면
//				map.put(j, 1);
//			}
		}
		// 맵에 있는 값들 우선순위 큐에 담기
		for (Map.Entry<Integer, Integer> pair : map.entrySet()) {
			q.add(new Pair(pair.getKey(), pair.getValue()));
		}
//		map.forEach((intKey, intValue) -> {
//			q.offer(new Pair(intKey, intValue));
//		});

		// 큐에 Pair 꺼내서 새로운 배열 만들기
		int index = 1;
		while (!q.isEmpty()) {
			Pair numPair = q.poll();

			A[key][index++] = numPair.num;
			A[key][index++] = numPair.cnt;
		}

		yLeng = Math.max(yLeng, index); // yLeng 최대 값 설정해주기

		while (index <= 99) {
			// 나머지 빈 값 0으로 채워주기
			A[key][index++] = 0;
			A[key][index++] = 0;
		}

	}

	static void operC(int key) {
		// C 연산 A[move][fixed]
		// HashMap k: num, v: count, key가 없는 경우 해당 key로 map 생성
		// 배열 max 101 설정 넘어가면 없어짐
		// 숫자와 카운트를 받을 해쉬맵
		PriorityQueue<Pair> q = new PriorityQueue<>();
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int j = 1; j <= xLeng; j++) {
			// 0인 값은 건너뛰기
			if (A[j][key] == 0) continue;
//			if (map.containsKey(j)) {
//				// 해당 키를 가지고 있다면
//				map.compute(j, (k, v) -> v++);
//				map.put(j,(map.get(j)+1));
//			} else {
//				// 해당 키를 가지고 있지 않다면
//				map.put(j, 1);
//			}
			map.compute(A[j][key], (num, count) -> count == null ? 1 : count + 1);
		}
		// 맵에 있는 값들 우선순위 큐에 담기
		for (Map.Entry<Integer, Integer> pair : map.entrySet()) {
			q.add(new Pair(pair.getKey(), pair.getValue()));
		}
		
//		map.forEach((intKey, intValue) -> {
//			q.offer(new Pair(intKey, intValue));
//		});

		// 큐에 Pair 꺼내서 새로운 배열 만들기
		int index = 1;
		while (!q.isEmpty()) {
			Pair numPair = q.poll();

			A[index++][key] = numPair.num;
			A[index++][key] = numPair.cnt;
		}

		xLeng = Math.max(xLeng, index); // yLeng 최대 값 설정해주기

		while (index <= 99) {
			// 나머지 빈 값 0으로 채워주기
			A[index++][key] = 0;
			A[index++][key] = 0;
		}
	}

	static class Pair implements Comparable<Pair> {
		int num;
		int cnt;

		public Pair(int n, int c) {
			this.num = n;
			this.cnt = c;
		}

		@Override
		public int compareTo(Pair o) {
			if (this.cnt > o.cnt) {
				return 1;
			} else if (this.cnt == o.cnt) {
				return this.num - o.num;
			} else {
				return -1;
			}
		}

	}
}
