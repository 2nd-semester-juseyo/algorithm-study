import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static List<Integer> menNeg, womenPos, menPos, womenNeg;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		menNeg = new ArrayList<Integer>();
		womenPos = new ArrayList<Integer>();
		menPos = new ArrayList<Integer>();
		womenNeg = new ArrayList<Integer>();

		st = new StringTokenizer(br.readLine());
		// 남자 
		for (int i = 0; i < N; i++) {
			int val = Integer.parseInt(st.nextToken());
			
			if (val < 0) menNeg.add(val);
			else		 menPos.add(val);
		}
		// 여자
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int val = Integer.parseInt(st.nextToken());
			
			if (val < 0) womenNeg.add(val);
			else		 womenPos.add(val);
		}

		Collections.sort(menPos);
		Collections.sort(womenPos);
		Collections.sort(menNeg);
		Collections.reverse(menNeg);
		Collections.sort(womenNeg);
		Collections.reverse(womenNeg);
		
		int cnt = 0;
		int idxM = 0;
		int idxW = 0;
		
		// 남자- 여자+ 의 경우
		// 작은 사람들끼리 만난다.
		while (true) {
			if (idxM >= menNeg.size() || idxW >= womenPos.size())
				break;
			
			if (womenPos.get(idxW) >= Math.abs(menNeg.get(idxM))) {
				idxM++;
			} else {
				cnt++;
				idxM++;
				idxW++;
			}
		}
		
		idxM = idxW = 0;
		// 남자+ 여자-의 경우
		// 맹 작은 사람들끼리 만난다.
		while (true) {
			if (idxM >= menPos.size() || idxW >= womenNeg.size())
				break;
			
			if (menPos.get(idxM) >= Math.abs(womenNeg.get(idxW))) {
				idxW++;
			} else {
				cnt++;
				idxM++;
				idxW++;
			}
		}
		
		System.out.println(cnt);
	}
}
