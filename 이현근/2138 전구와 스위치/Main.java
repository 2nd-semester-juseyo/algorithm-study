import java.util.*;
import java.io.*;

// 0번 스위치를 누르는 경우와 누르지 않는 경우로 나눈다
public class Main {
	static int N, cnt, answer;
	static char[] input, target, tmp;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		input = sc.next().toCharArray();
		target = sc.next().toCharArray();
		tmp = input.clone();
		answer = 1_000_000_000;

		if (isSame_input()) {
			answer = Math.min(answer, cnt);
		}
		// 0번 스위치를 누른다.
		for (int i = 0; i < 2; i++) {
			if (input[i] == '1')
				input[i] = '0';
			else
				input[i] = '1';
		}
		cnt++;
		// 마지막에서 한 칸 앞의 버튼까지 고려한다.
		for (int i = 1; i < input.length - 1; i++) {
			if (input[i - 1] != target[i - 1]) {
				button_input(i);
			}
		}
		// 마지막 버튼을 고려한다.
		if (input[N - 1] != target[N - 1]) {
			if (input[N - 1] == '1')
				input[N - 1] = '0';
			else
				input[N - 1] = '1';
			if (input[N - 2] == '1')
				input[N - 2] = '0';
			else
				input[N - 2] = '1';
			cnt++;
		}
		// 같은지 체크
		if (isSame_input()) {
			answer = Math.min(answer, cnt);
		}

		cnt = 0;
		// 0번 스위치를 누르지 않는 경우
		// 마지막에서 한 칸 앞의 버튼까지 고려한다.
		for (int i = 1; i < tmp.length - 1; i++) {
			if (tmp[i - 1] != target[i - 1]) {
				button_tmp(i);
			}
		}
		// 마지막 버튼을 고려한다.
		if (tmp[N - 1] != target[N - 1]) {
			if (tmp[N - 1] == '1')
				tmp[N - 1] = '0';
			else
				tmp[N - 1] = '1';
			if (tmp[N - 2] == '1')
				tmp[N - 2] = '0';
			else
				tmp[N - 2] = '1';
			cnt++;
		}

		// 같은지 체크
		if (isSame_tmp()) {
			answer = Math.min(answer, cnt);
		}
		
		System.out.println(answer == 1_000_000_000? -1 : answer);
	}

	public static boolean isSame_input() {
		for (int i = 0; i < N; i++) {
			if (input[i] != target[i])
				return false;
		}
		return true;
	}

	public static boolean isSame_tmp() {
		for (int i = 0; i < N; i++) {
			if (tmp[i] != target[i])
				return false;
		}
		return true;
	}

	public static void button_input(int idx) {
		if (input[idx - 1] == '1')
			input[idx - 1] = '0';
		else
			input[idx - 1] = '1';
		if (input[idx] == '1')
			input[idx] = '0';
		else
			input[idx] = '1';
		if (input[idx + 1] == '1')
			input[idx + 1] = '0';
		else
			input[idx + 1] = '1';
		cnt++;
	}

	public static void button_tmp(int idx) {
		if (tmp[idx - 1] == '1')
			tmp[idx - 1] = '0';
		else
			tmp[idx - 1] = '1';
		if (tmp[idx] == '1')
			tmp[idx] = '0';
		else
			tmp[idx] = '1';
		if (tmp[idx + 1] == '1')
			tmp[idx + 1] = '0';
		else
			tmp[idx + 1] = '1';
		cnt++;
	}
}
