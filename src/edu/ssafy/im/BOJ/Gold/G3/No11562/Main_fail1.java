package edu.ssafy.im.BOJ.Gold.G3.No11562;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_fail1 {
	public static void main(String[] args) throws IOException {
		new Main_fail1().sol();
	}

	private int N;
	private int M;
	private ArrayList<ArrayList<Integer>> graph;
	private int K;
	private StringBuilder sb = new StringBuilder();
	private boolean branch;

	private void sol() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList<>();
		for (int i = 0; i < N+1; i++) {
			graph.add(new ArrayList<>());
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph.get(u).add(v);
			if (b == 1) graph.get(v).add(u);
			if (b == 0) graph.get(v).add(-u);
		}
		
		K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			branch = false;
			dfs(u, v, 0, 1 << u);
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	private void dfs(int u, int v, int c, int vi) {
		if (branch) return;
		
		if (u == v) {
			branch = true;
			sb.append(c).append("\n");
			return;
		}
		
		for (int i = 0; i < graph.get(u).size(); i++) {
			int cur = graph.get(u).get(i);
			if ((vi & (1 << Math.abs(cur))) == 0) {
				if (cur < 0) dfs(Math.abs(cur), v, c+1, vi |= 1 << Math.abs(cur));
				else dfs(cur, v, c, vi |= 1 << cur);
			}
		}
	}
}