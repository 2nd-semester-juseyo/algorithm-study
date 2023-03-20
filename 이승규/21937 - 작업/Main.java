import java.io.*;
import java.util.*;

public class Main {
	
	static int n;
	static int m;
	static ArrayList<ArrayList<Integer>> list=new ArrayList<>();
	static int count=0;
	static boolean visited[];
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken()); //node 갯수
		m=Integer.parseInt(st.nextToken()); //간선 갯수
		visited=new boolean[n+1];
		
		for(int i=0; i<=n; i++)
			list.add(new ArrayList<Integer>());
		
		for(int i=0; i<m; i++)
		{
			st=new StringTokenizer(br.readLine());
			int num1=Integer.parseInt(st.nextToken());
			int num2=Integer.parseInt(st.nextToken());
			
			list.get(num2).add(num1);
		}
		
		int x=Integer.parseInt(br.readLine());
		
		DFS(x);
		System.out.println(count);
	}
	
	public static void DFS(int x)
	{
		visited[x]=true;
		
		for(int i=0; i<list.get(x).size(); i++)
		{
			int n=list.get(x).get(i);
			
			if(!visited[n])
			{
				count++;
				DFS(n);
			}
		}
	}
}
