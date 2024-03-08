package edu.ssafy.im.BOJ.Gold.G2.No1938;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;

public class Main {
	private static int N;
	private static int[][] map;
	private static ArrayDeque<Point> q;
	private static Point destination;
	private static int[][] direction = {{0,1},{0,-1},{1,0},{-1,0}};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		boolean f1 = false, f2 = false;
		for (int i = 0; i < N; i++) {
			String row = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = row.charAt(j);
				if(!f1 && map[i][j] == 'B') {
					if (j+1 < N && map[i][j+1] == 'B') {
						q.offer(new Point(i, j+1, 0, 0));
					} else {
						q.offer(new Point(i+1, j, 0, 1));
					}
					f1 = true;
				}
				if(!f2 && map[i][j] == 'E') {
					if (j+1 < N && map[i][j+1] == 'E') {
						destination = new Point(i, j+1, 0);
					} else {
						destination = new Point(i+1, j, 1);
					}
					f2 = true;
				}
			}
		}
		
		bfs();
	}
	// d 0 : 가로, d 1 : 세로
	private static void bfs() {
		q = new ArrayDeque<>();
		boolean[][] v = new boolean[N][N];
		v[q.peek().x][q.peek().y] = true;
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			
			for(int[] d: direction) {
				int nx = p.x + d[0];
				int ny = p.y + d[1];
				
				if (nx < 1 || ny < 1 || nx >= N-1 || ny >= N-1) continue;
				if (v[nx])
				v[nx][ny] = true;
				q.offer(new Point(nx, ny, p.c+1, p.d));
			}
		}
	}
	
	static class Point {
		int x, y, c, d;

		public Point(int x, int y, int c, int d) {
			super();
			this.x = x;
			this.y = y;
			this.c = c;
			this.d = d;
		}
		
		public Point(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}
}
