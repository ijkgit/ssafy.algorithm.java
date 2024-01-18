package edu.ssafy.im.SWEA;

import java.io.*;
import java.util.StringTokenizer;

public class Solution1859 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCase = Integer.parseInt(br.readLine());

        for (int t = 1; t <= testCase; t++) {
            int n = Integer.parseInt(br.readLine());
            String input = br.readLine();
            StringTokenizer st = new StringTokenizer(input);
            int[] list = new int[n];

            for (int i = 0; i < n; i++) {
                list[i] = Integer.parseInt(st.nextToken());
            }


            int max = 0;
            int[] sum = new int[n];
            for (int j = n - 1; j >= 0; j--) {
                if (max < list[j]) max = list[j];
                sum[j] = max - list[j];
            }

            long res = 0;
            for (int i = 0; i < n; i++)
                res += sum[i];

            bw.write("#" + t + " " + res + "\n");
        }
        bw.flush();
        bw.close();
    }
}
