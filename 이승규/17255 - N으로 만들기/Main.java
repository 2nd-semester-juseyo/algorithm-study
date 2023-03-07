import java.io.*;
import java.util.*;

public class Main {

	static char arr[];
	static Set<String> set;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		arr = new char[str.length()];
		set = new HashSet<>();
		
		for(int i = 0; i < str.length(); i++) {
			arr[i] = str.charAt(i);
		}
		
		for(int i = 0; i < str.length(); i++) {
			dfs(i,i,arr[i]+"",arr[i]+"");
		}
		System.out.println(set.size());
	}
	static void dfs(int left, int right, String str, String cases ) {
		// 좌우로 dfs 돌면서 모든 경우의 수 탐색
		// 최종적으로 다 돌면 Set 값으로 저장해서 중복제거
		if(left == 0 && right == arr.length-1) {
			set.add(cases);
			return;
		}
		
		if((left-1) >= 0) {
			dfs(left-1, right, arr[left-1]+str, cases+arr[left-1]+str);
		}
		
		if((right+1) <= arr.length-1) {
			dfs(left, right+1, str+arr[right+1], cases+str+arr[right+1]);
		}
	}
}
