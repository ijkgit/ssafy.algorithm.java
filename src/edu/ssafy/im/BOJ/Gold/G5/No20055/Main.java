package edu.ssafy.im.BOJ.Gold.G5.No20055;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int K;
    private static int[] A;
    private static boolean[] visited;
    private static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[2*N];
        visited = new boolean[2*N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < A.length; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        sol();
        sb.append(ans);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void sol() {
        ans = 1;
        while(true) {
            rotate(); // 1번 동작
            move(); // 2번 동작
            put(); // 3번 동작
            if(count()) return; // 4번 동작
            ans++;
        }
    }

    // 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다.
    private static boolean count() {
        int cnt = 0;
        for (int i = 0; i < A.length; i++) {
            if(A[i] == 0) cnt++;
        }
        if (cnt >= K) return true;
        return false;
    }

    // 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
    private static void put() {
        if (A[0] != 0) {
            visited[0] = true;
            // 로봇을 올리는 위치에 올리거나 로봇이 어떤 칸으로 이동하면 그 칸의 내구도는 즉시 1만큼 감소한다.
            A[0]--;
        }
    }

    // 가장 먼저 벨트에 올라간 로봇 찾기
    private static int findLast() {
        for (int i = N-2; i >= 0; i--) {
            if(visited[i]) return i;
        }
        return -1;
    }

    private static void move() {
        int key = findLast();
        if (key == -1) return;
        for (int i = key; i >= 0; i--) { // 가장 먼저 벨트에 올라간 로봇부터,
            // 로봇이 이동하기 위해서는 이동하려는 칸에 로봇이 없으며, 그 칸의 내구도가 1 이상 남아 있어야 한다.
            if (visited[i] && !visited[i+1] && A[i+1] != 0) {
                visited[i] = false;
                visited[i+1] = true;
                // 로봇을 올리는 위치에 올리거나 로봇이 어떤 칸으로 이동하면 그 칸의 내구도는 즉시 1만큼 감소한다.
                A[i+1]--;
            }
        }
        // 언제든지 로봇이 내리는 위치에 도달하면 그 즉시 내린다.
        visited[N-1] = false;
    }

    // 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
    private static void rotate() {
        int tmp = A[2*N-1];
        boolean tmp2 = visited[2*N-1];
        for (int i = A.length - 1; i > 0; i--) {
            A[i] = A[i-1];
            visited[i] = visited[i-1];
        }
        A[0] = tmp;
        visited[0] = tmp2;
        visited[N-1] = false;
    }
}
