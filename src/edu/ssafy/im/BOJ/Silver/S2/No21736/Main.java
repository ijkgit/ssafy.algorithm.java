package edu.ssafy.im.BOJ.Silver.S2.No21736;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static int M;
	private static char[][] map;
	private static Point start;
	private static int ans;
	private static int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};	
	static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			String row = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = row.charAt(j);
				if(map[i][j] == 'I') start = new Point(i, j);
			}
		}
		
		bfs();
		
		if(ans == 0) sb.append("TT");
		else sb.append(ans);
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	private static void bfs() {
		Queue<Point> q = new ArrayDeque<>();
		q.offer(start);
		map[start.x][start.y] = 'X';
		ans = 0;
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			for(int[] d: direction) {
				int nx = p.x + d[0];
				int ny = p.y + d[1];
				
				if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
				if (map[nx][ny] == 'X') continue;
				if (map[nx][ny] == 'P') ans++;
				
				map[nx][ny] = 'X';
				q.offer(new Point(nx, ny));
			}
		}
	}
}
