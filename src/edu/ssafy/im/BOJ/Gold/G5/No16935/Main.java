package edu.ssafy.im.BOJ.Gold.G5.No16935;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		new Main().io();
	}

	private int[][] graph;
	private int[][] tmp;
	private int n;
	private int m;
	private int r;
	ArrayDeque<Integer> dq;

	private void io() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());

		graph = new int[n][m];
		tmp = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dq = new ArrayDeque<Integer>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < r; i++) {
			dq.offer(Integer.parseInt(st.nextToken()));
		}

		sol();

		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph[0].length; j++) {
				sb.append(graph[i][j]).append(" ");
			}
			sb.append("\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	private void sol() {
		while (!dq.isEmpty()) {
			int c = dq.poll();

			if (!dq.isEmpty()) {
				// 연산 1 또는 2가 연속 두번 나올 경우 복구됨
				if (dq.peek() == c) {
					if (c == 1 || c == 2) {
						dq.poll();
						continue;
					}
				}

				// 연산 3 또는 4 가 이어 나올 경우 복구됨
				if (c == 3 && dq.peek() == 4) {
					dq.poll();
					continue;
				}
				if (c == 4 && dq.peek() == 3) {
					dq.poll();
					continue;
				}

				// 연산 5 또는 6 이 이어 나올 경우 복구됨
				if (c == 5 && dq.peek() == 6) {
					dq.pop();
					continue;
				}
				if (c == 6 && dq.peek() == 5) {
					dq.pop();
					continue;
				}
			}

			checkCalculation(c);
		}
	}

	private void checkCalculation(int c) {
		switch (c) {
		case 1:
			calculation01();
			break;
		case 2:
			calculation02();
			break;
		case 3:
			calculation03();
			break;
		case 4:
			calculation04();
			break;
		case 5:
			calculation05();
			break;
		case 6:
			calculation06();
			break;
		}
	}

	private void copy() {
		graph = Arrays.copyOf(tmp, tmp.length);
		for (int i = 0; i < tmp.length; i++) {
			graph[i] = tmp[i].clone();
		}
	}

	private void calculation01() {
		for (int i = graph.length - 1; i >= 0; i--) {
			tmp[i] = graph[graph.length - 1 - i].clone();
		}
		copy();
	}

	private void calculation02() {
		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph[i].length; j++) {
				tmp[i][graph[i].length - 1 - j] = graph[i][j];
			}
		}
		copy();
	}

	private void calculation03() {
		tmp = new int[graph[0].length][graph.length];

		for (int i = 0; i < graph.length; i++) {
			int[] row = graph[i].clone();

			for (int j = 0; j < graph[0].length; j++) {
				tmp[j][graph.length - 1 - i] = row[j];
			}
		}
		copy();
	}

	private void calculation04() {
		tmp = new int[graph[0].length][graph.length];

		for (int i = 0; i < graph.length; i++) {
			int[] row = graph[i].clone();

			for (int j = 0; j < graph[0].length; j++) {
				tmp[graph[0].length - 1 - j][i] = row[j];
			}
		}
		copy();
	}

	private void calculation05() {
		// 1번 그룹 오른쪽 이동
		for (int i = 0; i < graph.length / 2; i++) {
			for (int j = 0; j < graph[0].length / 2; j++) {
				tmp[i][j + graph[0].length / 2] = graph[i][j];
			}
		}

		// 2번 그룹 아래쪽 이동
		for (int i = 0; i < graph.length / 2; i++) {
			for (int j = graph[0].length / 2 - 1; j < graph[0].length; j++) {
				tmp[i + graph.length / 2][j] = graph[i][j];
			}
		}

		// 3번 그룹 왼쪽 이동
		for (int i = graph.length / 2 - 1; i < graph.length; i++) {
			int c = graph[0].length / 2;
			for (int j = graph[0].length / 2 - 1; j >= 0; j--) {
				tmp[i][graph[0].length / 2 - 1 - j] = graph[i][c];
				c++;
			}
		}

		// 4번 그룹 위쪽 이동
		int r = graph.length - 1;
		for (int i = graph.length / 2 - 1; i >= 0; i--) {
			for (int j = 0; j < graph[0].length / 2; j++) {
				tmp[i][j] = graph[r][j];
			}
			r--;
		}

		copy();
	}

	private void calculation06() {
		// 1번 그룹 오른쪽 이동
		for (int i = 0; i < graph.length / 2; i++) {
			for (int j = 0; j < graph[0].length / 2; j++) {
				tmp[graph.length / 2 + i][j] = graph[i][j];
			}
		}

		// 2번 그룹 아래쪽 이동
		for (int i = graph.length / 2; i < graph.length; i++) {
			for (int j = 0; j < graph[0].length / 2; j++) {
				tmp[i][j + graph[0].length / 2] = graph[i][j];
			}
		}

		// 3번 그룹 왼쪽 이동
		for (int i = graph.length / 2; i < graph.length; i++) {
			for (int j = graph[0].length / 2; j < graph[0].length; j++) {
				tmp[i - graph.length / 2][j] = graph[i][j];
			}
		}

		// 4번 그룹 위쪽 이동
		for (int i = 0; i < graph.length / 2; i++) {
			for (int j = graph[0].length / 2; j < graph[0].length; j++) {
				tmp[i][j - graph[0].length / 2] = graph[i][j];
			}
		}

		copy();
	}
}