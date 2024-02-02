package edu.ssafy.im.SWEA.D3.No2805;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(bufferedReader.readLine());

		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(bufferedReader.readLine());
			int[][] graph = new int[N][N];

			for (int r = 0; r < N; r++) {
				String row = bufferedReader.readLine();
				for (int c = 0; c < N; c++) {
					graph[r][c] = row.toCharArray()[c] - '0';
				}
			}

			int start = (N - 1) / 2, end = (N - 1) / 2;
			int cnt = 0;
			boolean state = true;
			
			for (int r = 0; r < N; r++) {
				for (int c = start; c <= end; c++) {
					cnt += graph[r][c];
				}
				if(start == 0)
					state = false;
				
				if(state) {
					start--;
					end++;
				} 
				else {
					start++;
					end--;
				}
			}
			bufferedWriter.write("#" + t + " " + cnt + "\n");
		}
		bufferedWriter.flush();
		bufferedWriter.close();
	}
}