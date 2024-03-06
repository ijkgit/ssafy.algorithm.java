package edu.ssafy.im.BOJ.Gold.G5.No16928;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static int M;
	private static int ans = Integer.MAX_VALUE;
	private static int[] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[106];
		for (int i = 0; i < N + M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			map[u] = v;
		}
		
		bfs();
		sb.append(ans);
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	private static void bfs() {
		Queue<Info> q = new PriorityQueue<>();
		q.offer(new Info(1, 0));
		boolean[] v = new boolean[106];
		v[1] = true;
		
		while(!q.isEmpty()) {
			Info info = q.poll();
			
			if (info.count >= ans) return;
			
			if (info.now + 6 >= 100) ans = Math.min(ans, info.count + 1);
			
			boolean flag = false;
			for (int i = 6; i >= 1; i--) {
				if (v[info.now + i]) continue;
				if (map[info.now + i] != 0) {
					v[info.now + i] = true;
					v[map[info.now + i]] = true;
					q.offer(new Info(map[info.now + i], info.count+1));
				} else if (!flag) {
					flag = true;
					v[info.now + i] = true;
					q.offer(new Info(info.now + i, info.count+1));
				}
			}
		}
	}
	
	static class Info implements Comparable<Info> {
		int now, count;

		public Info(int now, int count) {
			super();
			this.now = now;
			this.count = count;
		}

		@Override
		public int compareTo(Info o) {
			if (this.count == o.count) return o.now - this.now;
			return this.count - o.count;
		}
	}
}