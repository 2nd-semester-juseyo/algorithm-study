import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int answer = Integer.MIN_VALUE;
	static ArrayList<Integer> num = new ArrayList<>();
	static ArrayList<Character> op = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		String t = br.readLine();

		for (int i = 0; i < t.length(); i++) {
			if (i % 2 == 0)
				num.add(t.charAt(i) - '0');
			else
				op.add(t.charAt(i));
		}
		
		dfs(0, num.get(0));
		
		System.out.println(answer);
	}

	public static void dfs(int pos, int sum) {
		// 탈출 조건
		if (pos >= op.size())
		{
			answer = Math.max(answer, sum);
			return;
		}
		
		// 순서대로 계산
		int sum1 = cal(pos, sum, num.get(pos+1));
		dfs(pos+1, sum1);
		
		// 괄호치고 계산 ==> 현재 위치의 값은 그대로 두고 뒤의 두 값을 먼저 계산하는 방식
		if (pos+2 <= op.size())
		{					
		int sum2_1 = cal(pos+1, num.get(pos+1), num.get(pos+2));
		int sum2_2 = cal(pos, sum, sum2_1);
		dfs(pos+2, sum2_2);
		}
	}
	
	// num과 op에 접근해서 숫자를 계산
	// a는 i, b는 i+1에 해당하는 위치의 값
	public static int cal(int op_idx, int a, int b) {
		char op_char = op.get(op_idx);

		switch (op_char) {
		case ('+'):
			return a + b;
		case ('-'):
			return a - b;
		case ('*'):
			return a * b;
		}
		
		return 0;
	}
}