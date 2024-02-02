package edu.ssafy.im.BOJ.Silver.S3.No18115;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        new Main().sol();
    }

    private void sol() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        String input = br.readLine();
        StringTokenizer st = new StringTokenizer(input);

        int a[] = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        int r[] = new int[n+1];
        int c1 = 0, c2 = 1, c3 = n - 1, num = n;
        for (int i = 0; i < n; i++) {
            switch (a[i]) {
                case 1:
                    r[c1] = num;
                    if(r[c1 +1] == 0) c1++;
                    else c1 = c2 + 1;
                    break;
                case 2:
                    if(r[c1+1] == 0) c2 = c1 + 1;
                    else c2++;
                    r[c2] = num;
                    break;
                case 3:
                    r[c3] = num;
                    c3--;
                    break;
            }
            num--;
        }

        for (int i = 0; i < n; i++) {
            sb.append(r[i]).append(" ");
        }
        System.out.print(sb);
    }
}
