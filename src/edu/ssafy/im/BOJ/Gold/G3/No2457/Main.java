package edu.ssafy.im.BOJ.Gold.G3.No2457;
// https://www.acmicpc.net/board/view/86573
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		new Main().io();
	}

	private int N;
	private int[] date;
	private int[] monthToDay;
	private int ans = 0;
	private PriorityQueue<Period> periodList;

	private void io() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		date = new int[275];
		monthToDay = new int[] { 30, 31, 30, 31, 31, 30, 31, 30 };
		periodList = new PriorityQueue<>((o1, o2) -> o1.si - o2.si);
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int sm = Integer.parseInt(st.nextToken());
			int sd = Integer.parseInt(st.nextToken());
			int em = Integer.parseInt(st.nextToken());
			int ed = Integer.parseInt(st.nextToken());
			if (sm < 3 && em < 3)
				continue;
			if (sm < 3 && em >= 3) {
				sm = 3;
				sd = 1;
			}
			if (sm == 12 && em == 12)
				continue;
			if (em == 12 && sm < 12) {
				em = 11;
				ed = 31;
			}

			count(sm, sd, em, ed);
		}
		check();
		sb.append(sol());
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	private void count(int sm, int sd, int em, int ed) {
		int si = -1, ei = -1;

		for (int i = 0; i < sm - 3; i++) {
			si += monthToDay[i];
		}
		si += sd;

		for (int i = 0; i < em - 3; i++) {
			ei += monthToDay[i];
		}
		ei += ed;

		
		for (int i = si; i <= ei; i++) {
			date[i]++;
		}
		
		periodList.offer(new Period(si, ei, ei - si));
		ans++;
	}

	private void check() {
		while (!periodList.isEmpty()) {
			Period p = periodList.poll();
//			System.out.println(p);
//			System.out.println(Arrays.toString(date));
			boolean flag = false;
			for (int i = p.si; i <= p.ei; i++) {
//				System.out.print(date[i]);
				if (date[i] == 1) {
					flag = true;
					break;
				}
			}
//			System.out.println();

			if (!flag) {
				ans--;
				for (int i = p.si; i <= p.ei; i++) {
					date[i]--;
				}
			}
		}
	}

	private int sol() {
		for (int i = 0; i < date.length; i++) {
			if (date[i] == 0) {
				ans = 0;
				break;
			}
		}

		return ans;
	}

	class Period {
		int si, ei, p;

		public Period(int si, int ei, int p) {
			super();
			this.si = si;
			this.ei = ei;
			this.p = p;
		}

		@Override
		public String toString() {
			return "Period [si=" + si + ", ei=" + ei + ", p=" + p + "]";
		}
	}
}
