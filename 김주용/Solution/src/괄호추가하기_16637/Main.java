package 괄호추가하기_16637;

import java.io.*;

public class Main {
    static int N;
    static String line;
    static String operators = "+-*/";

    public static void main(String[] args) throws IOException {
        int answer = 0;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(in.readLine());
        line = in.readLine();

        solution();
        out.write(Integer.toString(answer));
        in.close();
        out.close();
    }

    public static void solution() {
        int operator = line.length() / 2; //연산자 수
        int maxNum = (int) Math.round(operator / 2.0); // 최대 추가 괄호 쌍 수
        for (int i = 1; i <= 1; i++) {
            boolean[] visited = new boolean[line.length()];
            String formula = "";
            dfs(formula, visited, 0, 0, i);
        }
    }

    public static void dfs(String formula, boolean[] visited, int cur, int cnt, int num) {
        if (cnt == num) {
            System.out.println(formula + line.substring(cur));
            return;
        }
        System.out.println(formula);

        for(int i = cur; i < line.length() - 3; i++) {
            if (!visited[cur]) {
                if (operators.indexOf(line.charAt(cur)) == -1) {
                    formula += ("(" + line.substring(cur, cur + 3) + ")");
                    visited[cur] = true;
                    dfs(formula, visited, cur + 3, cnt + 1, num);
                    formula = formula.substring(0, formula.length() - 5);
                    visited[cur] = false;
                } else {
                    formula += line.charAt(cur);
                    dfs(formula, visited, cur + 1, cnt, num);
                    formula = formula.substring(0, formula.length() - 1);
                }
            }

        }
    }

}
