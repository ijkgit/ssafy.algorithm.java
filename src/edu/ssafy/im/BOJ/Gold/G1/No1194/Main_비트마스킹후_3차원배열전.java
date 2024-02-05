package edu.ssafy.im.BOJ.Gold.G1.No1194;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Main_비트마스킹후_3차원배열전 {
	char[][] graph;
	int n, m, ans = -1;
	int[][] direction = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
	static final int binarySize = 25;

	public static void main(String[] args) throws IOException {
		new Main_비트마스킹후_3차원배열전().io();
	}

	private void io() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		graph = new char[n][m];

		int startX = 0, startY = 0;
		for (int x = 0; x < n; x++) {
			String s = br.readLine();
			for (int y = 0; y < m; y++) {
				graph[x][y] = s.charAt(y);
				if (graph[x][y] == '0') {
					startX = x;
					startY = y;
				}
			}
		}

		sol(startX, startY, 0, new int[n][2], 0);

		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	private void sol(int x, int y, int cnt, int[][] visited, int keys) {

		Queue<Point> queue = new ArrayDeque<>();
		visited[x][y / binarySize] = 1 << (y % binarySize);
		queue.offer(new Point(x, y, cnt, visited, keys));

		while (!queue.isEmpty()) {
			Point p = queue.poll();
			x = p.x;
			y = p.y;
			cnt = p.cnt;
			visited = p.visited;
			keys = p.keys;

			for (int d = 0; d < direction.length; d++) {
				int newX = x + direction[d][0];
				int newY = y + direction[d][1];
				int newYtoBinaryIndex = newY / binarySize;
				int newYtoBinary = 1 << (newY % binarySize);
				if (checkStatus(newX, newY, newYtoBinaryIndex, newYtoBinary, visited)) { // 배열 범위를 벗어나지 않았는지, 벽이 아닌지 확인
					if (checkGo(newX, newY)) { // 일반 길
						visited[newX][newYtoBinaryIndex] += newYtoBinary;
						queue.offer(new Point(newX, newY, cnt + 1, visited, keys));
					} else if (checkAns(newX, newY)) { // 탈출 시
						ans = cnt + 1;
						return;
					} else if (checkDoor(newX, newY, keys)) { // 대문자이면서 지나갈 수 있을 때
						visited[newX][newYtoBinaryIndex] += newYtoBinary;
						queue.offer(new Point(newX, newY, cnt + 1, visited, keys));
					} else if (checkKey(newX, newY)) { // 소문자일 때
						int[][] newVisited = new int[n][2]; // 키를 얻을 시 갈 수 있는 경로가 새롭게 갱신
						newVisited[newX][newYtoBinaryIndex] = newYtoBinary;
						int newKey = keys;
						if (!checkVisitedKey(newX, newY, keys))
							newKey = keys + getBinaryKey(newX, newY);
						queue.offer(new Point(newX, newY, cnt + 1, newVisited, newKey));
					}
				}
//				System.out.println(Integer.toBinaryString(keys) + " " + cnt);
//				System.out.println(queue.toString());
			}
		}

		ans = -1; // 루프를 탈출했다면 미로를 빠져나가지 못한 것

	}

	private boolean checkStatus(int x, int y, int newYtoBinaryIndex, int newYtoBinary, int[][] visited) {
		return 0 <= x && x < n && 0 <= y && y < m && graph[x][y] != '#'
				&& ((visited[x][newYtoBinaryIndex] & newYtoBinary) == 0);
	}

	private boolean checkGo(int x, int y) {
		return graph[x][y] == '.' || graph[x][y] == '0';
	}

	private boolean checkAns(int x, int y) {
		return graph[x][y] == '1';
	}

	private int getBinaryKey(int x, int y) {
		switch (graph[x][y]) {
		case 'a':
			return (1 << 0);
		case 'b':
			return (1 << 1);
		case 'c':
			return (1 << 2);
		case 'd':
			return (1 << 3);
		case 'e':
			return (1 << 4);
		case 'f':
			return (1 << 5);
		default:
			return 0;
		}
	}

	private boolean checkKey(int x, int y) {
		switch (graph[x][y]) {
		case 'a':
			return true;
		case 'b':
			return true;
		case 'c':
			return true;
		case 'd':
			return true;
		case 'e':
			return true;
		case 'f':
			return true;
		default:
			return false;
		}
	}

	private boolean checkVisitedKey(int x, int y, int keys) {
		switch (graph[x][y]) {
		case 'a':
			return ((keys & (1 << 0)) != 0);
		case 'b':
			return ((keys & (1 << 1)) != 0);
		case 'c':
			return ((keys & (1 << 2)) != 0);
		case 'd':
			return ((keys & (1 << 3)) != 0);
		case 'e':
			return ((keys & (1 << 4)) != 0);
		case 'f':
			return ((keys & (1 << 5)) != 0);
		default:
			return false;
		}
	}

	private boolean checkDoor(int x, int y, int keys) {
		switch (graph[x][y]) {
		case 'A':
			return ((keys & (1 << 0)) != 0);
		case 'B':
			return ((keys & (1 << 1)) != 0);
		case 'C':
			return ((keys & (1 << 2)) != 0);
		case 'D':
			return ((keys & (1 << 3)) != 0);
		case 'E':
			return ((keys & (1 << 4)) != 0);
		case 'F':
			return ((keys & (1 << 5)) != 0);
		default:
			return false;
		}
	}

	class Point {
		int x;
		int y;
		int cnt;
		int[][] visited;
		int keys;

		public Point(int x, int y, int cnt, int[][] visited, int keys) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.visited = visited;
			this.keys = keys;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + ", cnt=" + cnt + ", keys=" + keys + "]";
		}

	}
}