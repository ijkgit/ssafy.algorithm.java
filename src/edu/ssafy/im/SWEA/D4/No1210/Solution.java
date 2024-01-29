package edu.ssafy.im.SWEA.D4.No1210;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int testCase = 10;

		for (int t = 1; t <= testCase; t++) {
			br.readLine();
			int[][] graph = new int[100][100];
			for (int r = 0; r < graph.length; r++) {
				String row = br.readLine();
				StringTokenizer st = new StringTokenizer(row);
				for (int c = 0; c < graph.length; c++) {
					graph[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			int[] direction = {-1, 1};
			for (int c = 0; c < graph.length; c++) {
				int r = 0;
				int nowY = c;
				if (graph[r][c] == 1) {
					while(r < 100) {
						r++;
						if(0 <= c - 1 && graph[r][c-1] == 1)
							nowY = c-1;
						if(c+1 < 100 && graph[r][c+1] == 1)
							nowY = c+1;
					}
				}
			}

		}

	}
}
