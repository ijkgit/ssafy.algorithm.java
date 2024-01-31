package edu.ssafy.im.SWEA.D5.No1247;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	boolean[] visited;
	int[] sel;
	int[][] graph;
	int ans;
	int sx,sy,ex,ey;
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		new Solution().sol();
	}

	private void sol() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine());
		for (int t = 1; t <= testCase; t++) {
			int n = Integer.parseInt(br.readLine());
			graph = new int[n][2];
			String input = br.readLine();
			StringTokenizer st = new StringTokenizer(input);
			
			sx = Integer.parseInt(st.nextToken());
			sy = Integer.parseInt(st.nextToken());
			ex = Integer.parseInt(st.nextToken());
			ey = Integer.parseInt(st.nextToken());
			
			for (int i = 0; i < n; i++) {
				graph[i][0] = Integer.parseInt(st.nextToken());
				graph[i][1] = Integer.parseInt(st.nextToken());
			}
			
			ans = Integer.MAX_VALUE;
			visited = new boolean[n];
			sel = new int[n];
			recursion(0);
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}

	private void recursion(int k) {
		// basis part
		if (k == visited.length) {
			int sum = 0;
			for (int i = 0; i < sel.length-1; i++) {
				sum += getDistance(graph[sel[i]][0], graph[sel[i]][1], graph[sel[i+1]][0], graph[sel[i+1]][1]);
			}
			sum += getDistance(sx, sy, graph[sel[0]][0], graph[sel[0]][1]);
			sum += getDistance(graph[sel[sel.length-1]][0], graph[sel[sel.length-1]][1], ex, ey);
			ans = Math.min(sum, ans);
			
			return;
		}
		
		// inductive part
		for (int i = 0; i < visited.length; i++) {
			if(!visited[i]) {
				visited[i] = true;
				sel[k] = i;
				recursion(k+1);
				visited[i] = false;
			}
		}
	}
	
	private int getDistance(int x, int y, int nx, int ny) {
		return Math.abs(x - nx) + Math.abs(y - ny);
	}
	
	
}
