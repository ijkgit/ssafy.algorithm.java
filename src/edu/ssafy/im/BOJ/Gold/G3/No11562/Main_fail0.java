package edu.ssafy.im.BOJ.Gold.G3.No11562;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_fail0 {
	public static void main(String[] args) throws IOException {
		new Main_fail0().sol();
	}

	private int N;
	private int M;
	private int[][] graph;
	private int K;
	private StringBuilder sb = new StringBuilder();

	private void sol() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new int[N][N];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()) - 1; // zero base
			int v = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken());
			graph[u][v] = 1;
			if (b == 1) graph[v][u] = 1;
		}
		
		K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()) - 1;
			int v = Integer.parseInt(st.nextToken()) - 1;
			dfs(u, v, 0, 1 << u);
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	private void dfs(int u, int v, int c, int vi) {
		if (u == v) {
			sb.append(c).append("\n");
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if ((vi & (1 << i)) == 0) {
				if (graph[u][i] == 1) dfs(i, v, c, vi |= 1 << i);
				else if (graph[i][u] == 1) dfs(i, v, c+1, vi |= 1 << i);	
			}
		}
	}
}