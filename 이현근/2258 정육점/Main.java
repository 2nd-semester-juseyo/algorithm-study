import java.util.*;
import java.io.*;

public class Main {
	static int N, M, answer;
	static boolean flag;
	static meat[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new meat[N];
		
		answer = 2_147_483_647;
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			
			arr[i] = (new meat(w, p));			
		}
		Arrays.sort(arr);
		
		// 정렬된 정렬을 순회하면서 무게와 가격을 증가시킨다.
		// 구매한 고기보다 싼 고기들은 무료로 가질 수 있다.
		// 가격이 같은 고기면 같은 돈을 주고 구매를 해야함.
		int sumWeight = arr[0].weight;
		int sumPrice = arr[0].price;
		for (int i=1; i<arr.length; i++) {
			// i번째 고기를 구매한다.
			sumWeight += arr[i].weight;
			if (arr[i-1].price == arr[i].price)
				sumPrice += arr[i].price;
			else
				sumPrice = arr[i].price;
			
			if (sumWeight >= M) {
				answer = Math.min(answer, sumPrice);
				flag = true;				
			}
			
		}

		System.out.println(flag == true? answer : -1);
		
	}
	
	
}
class meat implements Comparable<meat>{
	int weight;
	int price;
	
	meat(int weight, int price) {
		this.weight = weight;
		this.price = price;
	}

	// 가격이 낮은 녀석이 먼저 오도록 정렬함
	// 같은 가격인 경우 무게가 높은 녀석이 먼저 오도록 정렬함
	@Override
	public int compareTo(meat o) {
		// TODO Auto-generated method stub
		if (this.price == o.price)
			return o.weight - this.weight;
		return this.price - o.price;
	}
}
