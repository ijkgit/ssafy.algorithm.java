import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class Algo2_대전_05반_임재경 {
	private static int N;
	private static int[][] map;
	private static Point start;
	private static int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		int c = 0;
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == 'B') c++;
				if (c == 2) {
					if (j > 0 && map[i][j - 1] == 'B') start = new Point(i, j, 0, 0);
					else start = new Point(i, j, 1, 0);
					c++;
				}
			}
		}
		
		sb.append(sol());
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	private static int sol() {
		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.offer(start);
		
		boolean[][][] visited = new boolean[N][N][2];
		visited[start.x][start.y][start.d] = true;
		
		while (!pq.isEmpty()) {
			Point p = pq.poll();
			
			if (finish(p.x, p.y, p.d)) return p.c;
			if (rotate(p.x, p.y) && !visited[p.x][p.y][(p.d + 1) % 2]) {
				visited[p.x][p.y][(p.d + 1) % 2] = true;
				pq.offer(new Point(p.x, p.y, (p.d + 1) % 2, p.c + 1));
			}
			
			for (int[] d : direction) {
				int nx = p.x + d[0];
				int ny = p.y + d[1];
				
				if (!move(nx, ny, p.d) || visited[nx][ny][p.d]) continue;
				visited[nx][ny][p.d] = true;
				pq.offer(new Point(nx, ny, p.d, p.c + 1));
			}
		}
		
		return 0;
	}
	
	private static boolean move(int x, int y, int d) {
		if (d == 0) return (check(x, y - 1) && check(x, y) && check(x, y + 1));
		else return (check(x - 1, y) && check(x, y) && check(x + 1, y));
	}
	
	private static boolean finish(int x, int y, int d) {
		if (d == 0) return (ans(x, y - 1) && ans(x, y) && ans(x, y + 1));
		else return (ans(x - 1, y) && ans(x, y) && ans(x + 1, y));
	}
	
	private static boolean rotate(int x, int y) {
		for (int nx = x - 1; nx <= x + 1; nx++) {
			for (int ny = y - 1; ny <= y + 1; ny++) {
				if (!check(nx, ny)) return false; 
			}
		}
		return true;
	}
	
	private static boolean check(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < N && map[x][y] != '1';
	}
	
	private static boolean ans(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < N && map[x][y] == 'E';
	}

	static class Point implements Comparable<Point> {
		int x, y, d, c;
		
		public Point(int x, int y, int d, int c) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
			this.c = c;
		}

		@Override
		public int compareTo(Point o) {
			return this.c - o.c;
		}
	}
}
