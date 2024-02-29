package edu.ssafy.im.BOJ.Gold.G5.No2174;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int A, B, N, M;

	static class Robot {
		int r, c, d;

		public Robot(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}

	static int dr[] = { -1, 0, 1, 0 }; // NESW
	static int dc[] = { 0, 1, 0, -1 };
	static Robot[] robots;
	static int maps[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		robots = new Robot[N + 1];
		maps = new int[B + 1][A + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			String o = st.nextToken();
			maps[B - r + 1][c] = i;
			switch (o) {
			case "N":
				robots[i] = new Robot(B - r + 1, c, 0);
				break;
			case "E":
				robots[i] = new Robot(B - r + 1, c, 1);
				break;
			case "S":
				robots[i] = new Robot(B - r + 1, c, 2);
				break;
			case "W":
				robots[i] = new Robot(B - r + 1, c, 3);
				break;
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int robotNum = Integer.parseInt(st.nextToken());
			String order = st.nextToken();
			int loopCnt = Integer.parseInt(st.nextToken());
			int result = move(robotNum, order, loopCnt);
			if (result == -1) {
				sb.append("Robot ").append(robotNum).append(" crashes into the wall");
				bw.write(sb.toString());
				bw.flush();
				bw.close();
				return;
				
			}
			if (result > 0) {
				sb.append("Robot ").append(robotNum).append(" crashes into robot ").append(result);
				bw.write(sb.toString());
				bw.flush();
				bw.close();
				return;
			}
		}

		sb.append("OK");
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	private static int move(int robotNum, String order, int loopCnt) {
		for (int i = 0; i < loopCnt; i++) {
			switch (order) {
			case "F":
				maps[robots[robotNum].r][robots[robotNum].c] = 0;
				int r = robots[robotNum].r += dr[robots[robotNum].d];
				int c = robots[robotNum].c += dc[robots[robotNum].d];
				if (r <= 0 || r > B || c <= 0 || c > A) return -1;
				if (maps[r][c] != 0) return maps[r][c];
				maps[robots[robotNum].r][robots[robotNum].c] = robotNum;
				break;
			case "L":
				robots[robotNum].d--;
				robots[robotNum].d = robots[robotNum].d == -1 ? 3 : robots[robotNum].d;
				break;
			case "R":
				robots[robotNum].d++;
				robots[robotNum].d = robots[robotNum].d == 4 ? 0 : robots[robotNum].d;
				break;
			}
		}
		return 0;
	}
}