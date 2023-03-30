import java.io.*;
import java.util.*;

public class Main {
	
	static int n, pair;
	static ArrayList<Integer>[] manList, womanList;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		manList = new ArrayList[2];
		womanList = new ArrayList[2];
		
		// 키카 작은 여자를 선호하는 남자 0
		// 키가 큰 여자를 선호하는 남자 1
		// 키가 작은 남자를 선호하는 여자 0
		// 키가 큰 여자를 선호하는 여자 1
		for(int i = 0; i < 2; i++) {
			manList[i] = new ArrayList<>();
			womanList[i] = new ArrayList<>();
		}

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			int man = Integer.parseInt(st.nextToken());
	
			if(man > 0) {
				manList[1].add(man);
			}else {
				manList[0].add(man*-1);
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			int woman = Integer.parseInt(st.nextToken());
			
			if(woman > 0) {
				womanList[0].add(woman);
			}else {
				womanList[1].add(woman*-1);
			}
		}
		
		for(int i = 0; i < 2; i++) {
			Collections.sort(manList[i]);
			Collections.sort(womanList[i]);
		}
		
		findPair(0);
		findPair(1);
		
		System.out.println(pair);
	}
	
	public static void findPair(int type) {
		
		for(int i = 0, j = 0; i < manList[type].size() && j < womanList[type].size();) {
			int tall = 0;
			int small = 0;
			
			if(type == 0) {
				tall = manList[type].get(i);
				small = womanList[type].get(j);
			}else if(type == 1) {
				tall = womanList[type].get(j);
				small = manList[type].get(i);
			}
			
			if(tall <= small) {
				if(type == 0) {
					i++;
				}else {
					j++;
				}
				continue;
			}
			
			pair++;
			i++;
			j++;
			
		}
	}
}
