import java.io.*;
import java.util.*;

class Main {
	static int N, M = 5, ans = Integer.MAX_VALUE;
	static int[][] nutrients;
	static int[] minN;
	static int[] select;

	static ArrayList<String> list = new ArrayList<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		nutrients = new int[N][M]; // N개의 영양분을 담을 배열
		minN = new int[4]; // 최저 영양소 기준을 담을 배열

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++)
			minN[i] = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				nutrients[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 1; i <= N; i++) {
			select = new int[N];
			choice(0, i, 0);
		}
		if (list.size() > 0) {
			System.out.println(ans);
			Collections.sort(list);
			String st1 = list.get(0);
			for (int i = 0; i < st1.length(); i++) {
				System.out.print(st1.charAt(i));
			}
		} else
			System.out.println(-1);
	}

	public static void choice(int cnt, int sel, int start) {
		if (cnt == sel) {
			isCheck(sel); // 조건체크
			return;
		}
		for (int i = start; i < N; i++) {
			select[cnt] = i;
			choice(cnt + 1, sel, i + 1);
		}

	}

	public static boolean isCheck(int sel) {
		int price = 0;
		int[] sum = new int[4];
		for (int i = 0; i < sel; i++) {
			sum[0] += nutrients[select[i]][0];
			sum[1] += nutrients[select[i]][1];
			sum[2] += nutrients[select[i]][2];
			sum[3] += nutrients[select[i]][3];
			price += nutrients[select[i]][4];
		}

		for (int i = 0; i < 4; i++) {
			if (minN[i] > sum[i])
				return false;
		}

		if (ans >= price) {
			if (ans > price) {
				list.clear();
			}
			String str = "";
			for (int i = 0; i < sel; i++) {
				str += (select[i] + 1 + " ");
			}
			list.add(str);
			ans = price;
		}
		return true;
	}
}
