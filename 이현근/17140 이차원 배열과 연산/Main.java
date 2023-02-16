import java.util.*;
import java.io.*;

public class Main {
	static int r, c, k;
	static int arr[][];
	static int col=3, row=3;
	static int ans=0;
	static Map<Integer, Integer> m = new HashMap<Integer, Integer>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		arr = new int[101][101];
		
		for (int i=1; i<=3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=1; j<=3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while (arr[r][c] != k && ans <=101) {
			if (row >= col) 
				calRow();
			else
				calCol();

			ans++;
		}
		if (ans == 102) ans = -1;
		System.out.println(ans);
	}
	
	public static void calRow() {
		// 길이 갱신을 위한 변수
		int tmp = 0;
		
		for (int i=1; i<=row; i++) {
			m.clear();
			// 각 열마다 계산
			for (int j=1; j<=col; j++) {
				// 맵 <숫자, 빈도수>
				if (arr[i][j] == 0) continue;
				
				if (m.get(arr[i][j]) == null) {
					m.put(arr[i][j], 1);
				}
				else {
					m.put(arr[i][j], m.get(arr[i][j])+1);
				}												
			}
			// 해쉬맵의 키를 값으로 정렬한 리스트
			List<Integer> list = new ArrayList<>(m.keySet());
			list.sort((o1, o2) -> {
				if (m.get(o1) != m.get(o2))
					return	m.get(o1) - m.get(o2);
				else
					return o1 - o2;
			});
			// 열 갱신
			for (int j=0; j<Math.min(list.size(), 50); j++) {								
				arr[i][j*2+1] = list.get(j);
				arr[i][j*2+2] = m.get(list.get(j));
			}
			// 뒷부분은 0으로 갱신
			for (int j=list.size()*2+1; j<=100; j++) {
				arr[i][j] = 0;
			}
			
			tmp = Math.max(tmp, Math.min(list.size()*2, 100));
			
		}
		col = tmp;
	}
	
	public static void calCol() {
		// 길이 갱신을 위한 변수
		int tmp = 0;
		
		for (int i=1; i<=col; i++) {
			m.clear();
			// 각 열마다 계산
			for (int j=1; j<=row; j++) {
				// 맵 <숫자, 빈도수>
				if (arr[j][i] == 0) continue;
				
				if (m.get(arr[j][i]) == null) {
					m.put(arr[j][i], 1);
				}
				else {
					m.put(arr[j][i], m.get(arr[j][i])+1);
				}												
			}
			// 해쉬맵의 키를 값으로 정렬한 리스트
			List<Integer> list = new ArrayList<>(m.keySet());
			list.sort((o1, o2) -> {
				if (m.get(o1) != m.get(o2))
					return	m.get(o1) - m.get(o2);
				else
					return o1 - o2;
			});
			// 갱신
			for (int j=0; j<Math.min(list.size(), 50); j++) {								
				arr[j*2+1][i] = list.get(j);
				arr[j*2+2][i] = m.get(list.get(j));
			}
			// 뒷부분은 0으로 갱신
			for (int j=list.size()*2+1; j<=100; j++) {
				arr[j][i] = 0;
			}
			
			tmp = Math.max(tmp, Math.min(list.size()*2, 100));
			
		}
		row = tmp;
	}
}