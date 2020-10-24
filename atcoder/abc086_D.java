import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.*;


public class Main {
	static final long MOD=1000000007;
	static final long MOD2=998244353;
	public static void main(String[] args) {
		PrintWriter out = new PrintWriter(System.out);
		InputReader sc=new InputReader(System.in);
		int N=sc.nextInt();
		int K=sc.nextInt();
		int[][] xy=new int[N][2];
		String[] s=new String[N];
		for (int i = 0; i < s.length; i++) {
			for (int j = 0; j < 2; j++) {
				xy[i][j]=sc.nextInt();
			}
			s[i]=sc.next();
			if (s[i].equals("W")) {
				xy[i][0]+=K;
			}
		}
		long[][] sum=new long[4*K+1][4*K+1];
		long max=0;
		for (int i = 0; i < N; i++) {
			sum[(xy[i][0])%(2*K)+1][(xy[i][1])%(2*K)+1]++;
		}
		int[] x= {0,2*K};
		int[] y= {0,2*K};
		for (int l = 0; l < y.length; l++) {
			for (int k = 0; k < y.length; k++) {
				if (x[l]==0&&y[k]==0) {
					continue;
				}
				for (int i = 1; i <= 2*K; i++) {
					for (int j = 1; j <= 2*K; j++) {
						sum[i+x[l]][j+y[k]]=sum[i][j];
					}
				}
			}
		}
		for (int i = 1; i <= 4*K; i++) {
			for (int j = 1; j <= 4*K; j++) {
				sum[i][j]+=sum[i-1][j]+sum[i][j-1]-sum[i-1][j-1];
			}
		}
		for (int i = 1; i <= 2*K; i++) {
			for (int j = 1; j <= 2*K; j++) {
				long w=getsum(i+K-1, j+K-1, i, j, sum)+getsum(i+2*K-1, j+2*K-1, i+K, j+K, sum);
				max=Math.max(max, w);
			}
		}
		System.out.println(max);
 	} 
	static long getsum(int x1,int y1,int x0,int y0,long[][] sum) {
		return sum[x1][y1]-sum[x1][y0-1]-sum[x0-1][y1]+sum[x0-1][y0-1];
	}
		static class InputReader { 
		private InputStream in;
		private byte[] buffer = new byte[1024];
		private int curbuf;
		private int lenbuf;
		public InputReader(InputStream in) {
			this.in = in;
			this.curbuf = this.lenbuf = 0;
		}
 
		public boolean hasNextByte() {
			if (curbuf >= lenbuf) {
				curbuf = 0;
				try {
					lenbuf = in.read(buffer);
				} catch (IOException e) {
					throw new InputMismatchException();
				}
				if (lenbuf <= 0)
					return false;
			}
			return true;
		}
 
		private int readByte() {
			if (hasNextByte())
				return buffer[curbuf++];
			else
				return -1;
		}
 
		private boolean isSpaceChar(int c) {
			return !(c >= 33 && c <= 126);
		}
 
		private void skip() {
			while (hasNextByte() && isSpaceChar(buffer[curbuf]))
				curbuf++;
		}
 
		public boolean hasNext() {
			skip();
			return hasNextByte();
		}
 
		public String next() {
			if (!hasNext())
				throw new NoSuchElementException();
			StringBuilder sb = new StringBuilder();
			int b = readByte();
			while (!isSpaceChar(b)) {
				sb.appendCodePoint(b);
				b = readByte();
			}
			return sb.toString();
		}
 
		public int nextInt() {
			if (!hasNext())
				throw new NoSuchElementException();
			int c = readByte();
			while (isSpaceChar(c))
				c = readByte();
			boolean minus = false;
			if (c == '-') {
				minus = true;
				c = readByte();
			}
			int res = 0;
			do {
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res = res * 10 + c - '0';
				c = readByte();
			} while (!isSpaceChar(c));
			return (minus) ? -res : res;
		}
 
		public long nextLong() {
			if (!hasNext())
				throw new NoSuchElementException();
			int c = readByte();
			while (isSpaceChar(c))
				c = readByte();
			boolean minus = false;
			if (c == '-') {
				minus = true;
				c = readByte();
			}
			long res = 0;
			do {
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res = res * 10 + c - '0';
				c = readByte();
			} while (!isSpaceChar(c));
			return (minus) ? -res : res;
		}
 
		public double nextDouble() {
			return Double.parseDouble(next());
		}
 
		public int[] nextIntArray(int n) {
			int[] a = new int[n];
			for (int i = 0; i < n; i++)
				a[i] = nextInt();
			return a;
		}
 
		public long[] nextLongArray(int n) {
			long[] a = new long[n];
			for (int i = 0; i < n; i++)
				a[i] = nextLong();
			return a;
		}
 
		public char[][] nextCharMap(int n, int m) {
			char[][] map = new char[n][m];
			for (int i = 0; i < n; i++)
				map[i] = next().toCharArray();
			return map;
		}
	}
}

