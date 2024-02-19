package edu.ssafy.im.BOJ.Silver.S3.No2606;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		new Main().io();
	}

	private int n;
	private int m;
	private ArrayList<Edge> edgeList;
	private boolean[] visited;
	private int ans = 0;

	class Edge {
		int from, to;

		public Edge(int from, int to) {
			super();
			this.from = from;
			this.to = to;
		}
	}

	private void io() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		edgeList = new ArrayList<>();
		
		StringTokenizer st;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			edgeList.add(new Edge(from, to));
			edgeList.add(new Edge(to, from));
		}
		
		visited = new boolean[n];
		dfs(0);
		
		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	private void dfs(int i) {
		visited[i] = true;
		for(Edge e : edgeList) {
			if(e.from == i) {
				if(!visited[e.to]) {
					ans++;
					dfs(e.to);
				}
			}
		}
	}
}
