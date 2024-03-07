package edu.ssafy.im.BOJ.Silver.S2.No11725;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static ArrayList<ArrayList<Integer>> graph;
	private static int[] ans;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		graph = new ArrayList<>();
		ans = new int[N];
		for (int i = 0; i < N; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()) - 1;
			int v = Integer.parseInt(st.nextToken()) - 1;
			graph.get(u).add(v);
			graph.get(v).add(u);
		}
		
		bfs();
		
		for (int i = 1; i < N; i++) {
			sb.append(ans[i]).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	private static void bfs() {
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] visited = new boolean[N];
		q.offer(0);
		visited[0] = true;
		
		while(!q.isEmpty()) {
			int v = q.poll();
			for (int i = 0; i < graph.get(v).size(); i++) {
				int cur = graph.get(v).get(i);
				if (visited[cur]) continue;
				
				visited[cur] = true;
				q.offer(cur);
				ans[cur] = v + 1;
			}
		}
	}
}
