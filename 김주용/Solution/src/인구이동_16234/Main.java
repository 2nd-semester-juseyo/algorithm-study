package 인구이동_16234;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;

public class Main {
    static int N, L, R;
    static int[][] board;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        int answer = 0;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] line = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = line[0];
        L = line[1];
        R = line[2];
        board = new int[N][N];
        visited = new boolean[N][N];
        for(int i = 0; i < N; i++) {
            board[i] = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        answer = solution();
        out.write(Integer.toString(answer));

        in.close();
        out.close();
    }
    public static int solution(){
        int day = 0;
        while (true) {
            int flag = 0;
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(!visited[i][j]) {
                        flag = Math.max(flag, bfs(i, j));
                    }
                }
            }
            if (flag == 0) {
                break;
            }
            visited = new boolean[N][N];
            day++;
        }
        return day;
    }

    public static int bfs(int y, int x) {
        int[] dy = {1, -1, 0, 0};
        int[] dx = {0, 0, 1, -1};
        Queue<int[]> group = new LinkedList<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {y, x});
        group.add(new int[] {y, x});
        visited[y][x] = true;
        int peopleSum = board[y][x];

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cy = cur[0], cx = cur[1];

            for(int i = 0; i < 4; i++) {
                int ny = cy + dy[i], nx = cx + dx[i];
                if(canMove(cy, cx, ny, nx)) {
                    group.add(new int[] {ny, nx});
                    peopleSum +=  board[ny][nx];
                    queue.add(new int[] {ny, nx});
                    visited[ny][nx] = true;
                }
            }
        }
        if (group.size() == 1) {
            return 0;
        }
        move(group, peopleSum);
        return 1;
    }

    public static boolean canMove(int cy, int cx, int ny, int nx) {
        if (ny < 0 || ny > N-1 || nx < 0 || nx > N-1 || visited[ny][nx]) {
            return false;
        }
        int people = Math.abs(board[cy][cx] - board[ny][nx]);
        return L <= people && people <= R;
    }

    public static void move(Queue<int []> group, int peopleSum) {
        int n = peopleSum / group.size();
        while (!group.isEmpty()) {
            int[] cur = group.poll();
            board[cur[0]][cur[1]] = n;
        }
    }
}
