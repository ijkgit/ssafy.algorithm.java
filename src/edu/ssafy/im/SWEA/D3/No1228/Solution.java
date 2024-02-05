package edu.ssafy.im.SWEA.D3.No1228;

import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        new Solution().io();
    }

    private void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= 10; t++) {
            int n = Integer.parseInt(br.readLine());
            LinkedList<Integer> linkedList = new LinkedList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                linkedList.add(Integer.parseInt(st.nextToken()));
            }
            n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                st.nextToken();
                int idx = Integer.parseInt(st.nextToken());
                int m = Integer.parseInt(st.nextToken());
                for (int j = 0; j < m; j++) {
                    linkedList.add(idx + j, Integer.parseInt(st.nextToken()));
                }
            }
            sb.append("#").append(t).append(" ");
            for (int i=0; i<10; i++) {
                sb.append(linkedList.get(i)).append(" ");
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
