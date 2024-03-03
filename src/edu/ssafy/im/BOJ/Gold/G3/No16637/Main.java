package edu.ssafy.im.BOJ.Gold.G3.No16637;

import java.io.*;

public class Main {
    private static int N;
    private static char[] arr;
    private static int ans = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        String s = br.readLine();
        arr = new char[N];
        for (int i = 0; i < N; i++) {
            arr[i] = s.charAt(i);
        }

        recursive(2, arr[0] - '0');

        sb.append(ans);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void recursive(int idx, int res) {
        if (idx >= N) {
            ans = Math.max(ans, res);
            return;
        }

        if (idx + 2 < N) recursive(idx + 4, cal(res, arr[idx - 1], cal(arr[idx] - '0', arr[idx + 1], arr[idx + 2] - '0')));
        recursive(idx + 2, cal(res, arr[idx - 1], arr[idx] - '0'));
    }

    private static int cal(int x, char op, int y) {
        switch (op) {
            case '+': return x + y;
            case '-': return x - y;
            case '*': return x * y;
        }
        return -1;
    }
}
