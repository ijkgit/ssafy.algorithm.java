import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class 문제2번 {
	private static int T;
	private static int N;
	private static int SIZE = 15;
	private static int[][] map;
	private static ArrayList<House> houseList;
	private static int ans;
	
	static class House implements Comparable<House> {
		int x, y, d;

		public House(int x, int y, int d) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
		}

		@Override
		public int compareTo(House o) {
			return Integer.compare(this.d, o.d);
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			houseList = new ArrayList<>();
			ans = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				houseList.add(new House(x, y, d));
			}
			
			Collections.sort(houseList);
			
			// ������ �Ѱ��� �����.
			for (int x = -SIZE; x <= SIZE; x++) {
				for (int y = -SIZE; y <= SIZE; y++) {
					int sum = 0, cnt = 0;
					L:for (House h : houseList) {
						if (h.x == x && h.y == y) break L; // ������ ��������
						int diff = Math.abs(x - h.x) + Math.abs(y - h.y);
						if (h.d < diff) break L; // �� Ż��
						else {
//							System.out.println(x + " " + y);
							sum += diff;
							cnt++;
						}
						if (cnt == houseList.size()) {
							ans = Math.min(ans, sum);
						}
					}
				}
			}
			
			// ������ �ΰ� �����.
			// �ϳ� �����ϸ� �ϳ��� ����ϱ�.. skip
			if (ans == Integer.MAX_VALUE) {
				// ù��° �������Դϴ�.
				for (int x = -SIZE; x <= SIZE; x++) {
					for (int y = -SIZE; y <= SIZE; y++) {
						
						// �ι�° ������
						for (int x2 = -SIZE; x2 <= SIZE; x2++) {
							for (int y2 = -SIZE; y2 <= SIZE; y2++) {
								
								if (x == x2 && y == y2) continue; // �ߺ� ó��
								
								int sum = 0, cnt = 0;
								L:for (House h : houseList) {
									int diff = Math.abs(x - h.x) + Math.abs(y - h.y);
									int diff2 = Math.abs(x2 - h.x) + Math.abs(y2 - h.y);
									
									if (h.d < diff && h.d < diff2) break L; // �� Ż��
									
									if (h.d >= diff && h.d >= diff2) { // ���� �����Ҹ� �ΰ��� �����׿�
										if (h.x == x && h.y == y) { // �˰��� ������ �����ҿ����
											sum += diff2;
											cnt++;
										} else if (h.x == x2 && h.y == y2) { // �˰��� ������ �����ҿ����
											sum += diff;
											cnt++;
										} else { // �� �߿� ����� �����Ҹ� ���ؿ�
											sum += Math.min(diff, diff2);
											cnt++;
										}
									}
									else if (h.d >= diff) {
										if (!(h.x == x && h.y == y)) { // ������ ��������
											sum += diff;
											cnt++;
										}
									} else if (h.d >= diff2) {
										if (!(h.x == x2 && h.y == y2)) { // ������ ��������
											sum += diff2;
											cnt++;	
										}
									}
									
									if (cnt == houseList.size()) {
										ans = Math.min(ans, sum);
									}
								}
							}
						}
					}
				}
				
			}
			
			sb.append("#").append(t).append(" ").append(ans = ans == Integer.MAX_VALUE ? -1 : ans).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}
