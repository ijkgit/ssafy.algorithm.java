package edu.ssafy.im.SWEA.D0.No1767;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	private int[][] graph;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		new Solution().io();
	}

	private void io() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= testCase; t++) {
			int n = Integer.parseInt(br.readLine());
			graph = new int[n][n];
			for (int i = 0; i < graph.length; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < graph.length; j++) {
					graph[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			sb.append(sol());
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	private int sol() {
		return 0;
	}
	
	class Point {
		int x;
		int y;
		int cnt;
		public Point(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
}
