package edu.ssafy.im.BOJ.Gold.G1.No1194;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Main_비트마스킹전 {
	char[][] graph;
	int n, m, ans = -1;
	int[][] direction = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

	public static void main(String[] args) throws IOException {
		new Main_비트마스킹전().io();
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

		sol(startX, startY, 0, new boolean[n][m], new ArrayList<>());

		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	private void sol(int x, int y, int cnt, boolean[][] visited, ArrayList<Character> keys) {
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(x, y, cnt, visited, keys));
		visited[x][y] = true;
		while (!queue.isEmpty()) {
//			System.out.println(queue);
//			for (boolean[] s : visited) {
//				System.out.println(Arrays.toString(s));
//			}
			Point p = queue.poll();
			x = p.x;
			y = p.y;
			cnt = p.cnt;
			visited = p.visited;
			keys = p.keys;
			for (int d = 0; d < direction.length; d++) {
				int newX = x + direction[d][0];
				int newY = y + direction[d][1];
				if (checkStatus(newX, newY, visited)) { // 배열 범위를 벗어나지 않았는지, 벽이 아닌지 확인
					if (checkGo(newX, newY)) {
						visited[newX][newY] = true;
						queue.offer(new Point(newX, newY, cnt + 1, visited, keys));
					} else if (checkAns(newX, newY)) { // 탈출 시
						ans = cnt + 1;
						return;
					} else if (checkDoor(newX, newY, keys)) {
						if (checkKey(newX, newY)) {
							boolean[][] newVisited = new boolean[n][m]; // 키를 얻을 시 갈 수 있는 경로가 새롭게 갱신
							newVisited[newX][newY] = true;
							queue.offer(new Point(newX, newY, cnt + 1, newVisited, addKey(newX, newY, keys)));
						} else {
							queue.offer(new Point(newX, newY, cnt + 1, visited, addKey(newX, newY, keys)));
						}
					}
				}
			}
//			System.out.println(queue);
//			for (boolean[] s : visited) {
//				System.out.println(Arrays.toString(s));
//			}
		}

		ans = -1;
	}

	private boolean checkStatus(int x, int y, boolean[][] visited) {
		return 0 <= x && x < n && 0 <= y && y < m && graph[x][y] != '#' && !visited[x][y];
	}

	private boolean checkGo(int x, int y) {
		return graph[x][y] == '.' || graph[x][y] == '0';
	}

	private boolean checkAns(int x, int y) {
		return graph[x][y] == '1';
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

	private ArrayList<Character> addKey(int x, int y, ArrayList<Character> keys) {
		ArrayList<Character> newKeys = (ArrayList<Character>) keys.clone();
		switch (graph[x][y]) {
		case 'a':
			newKeys.add('a');
			return newKeys;
		case 'b':
			newKeys.add('b');
			return newKeys;
		case 'c':
			newKeys.add('c');
			return newKeys;
		case 'd':
			newKeys.add('d');
			return newKeys;
		case 'e':
			newKeys.add('e');
			return newKeys;
		case 'f':
			newKeys.add('f');
			return newKeys;
		default:
			return keys;
		}
	}

	private boolean checkDoor(int x, int y, ArrayList<Character> keys) {
		switch (graph[x][y]) {
		case 'A':
			return keys.contains('a');
		case 'B':
			return keys.contains('b');
		case 'C':
			return keys.contains('c');
		case 'D':
			return keys.contains('d');
		case 'E':
			return keys.contains('e');
		case 'F':
			return keys.contains('f');
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

	class Point {
		int x;
		int y;
		int cnt;
		boolean[][] visited;
		ArrayList<Character> keys;

		public Point(int x, int y, int cnt, boolean[][] visited, ArrayList<Character> keys) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.visited = visited;
			this.keys = keys;
		}

		@Override
		public String toString() {
			return "Point{" + "x=" + x + ", y=" + y + ", cnt=" + cnt + ", visited=" + Arrays.toString(visited)
					+ ", keys=" + keys + '}';
		}
	}
}
