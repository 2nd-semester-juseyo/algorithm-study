import java.io.*;
import java.util.*;

public class Main {
	
	static int n, count;
	static int INF = 1000000000;
	static char[] want, origin;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		count = INF;
		origin = br.readLine().toCharArray();
		want = br.readLine().toCharArray();
		char[] firstOn = new char[n];
		char[] firstOff = new char[n];
		
		for(int i = 0; i < n; i++) {
			firstOn[i] = origin[i];
			firstOff[i] = origin[i];
		}
		
		check(1, 0, firstOff);
		check(1, 1, change(0, firstOn));
		
		if(count == INF) System.out.println("-1");
		else System.out.println(count);
		
	}
	
	public static void check(int idx, int cnt, char[] cArr) {
		
		if(idx == n) {
			if(want[idx-1] == cArr[idx-1]) {
				count = Math.min(cnt, count);				
			}
			return;
		}
		
		if(cArr[idx-1] != want[idx-1]) {
			check(idx+1, cnt+1, change(idx, cArr));
		}else {
			check(idx+1, cnt, cArr);
		}
		
	}
	
	public static char[] change(int idx, char[] cArr) {
		for(int i = idx-1; i <= idx + 1; i++) {
			if(i >= 0 && i < n) {
				if(cArr[i] == '1') cArr[i] = '0';
				else if(cArr[i] == '0') cArr[i] = '1';
			}
		}
		return cArr;
	}
}
