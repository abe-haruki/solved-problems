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
		int Q=sc.nextInt();
		int[] A=sc.nextIntArray(N);
		int[] X=sc.nextIntArray(Q);
		long[] sum=new long[N];
		sum[0]=A[0];
		sum[1]=A[1];
		for (int i = 2; i < sum.length; i++) {
			sum[i]=sum[i-2]+A[i];
		}
		long[] sum2=new long[N];
		sum2[N-1]=A[N-1];
		for (int i = N-2; i >= 0; i--) {
			sum2[i]=sum2[i+1]+A[i];
		}
		for (int i = 0; i < X.length; i++) {
			int from=Math.min(N-1, lower_bound(A, 0, N-1, X[i]));
			int to=N-1;
			while ((to-from)>=1) {
				int mid=(to-from)/2+from;
				if (f(A, mid, X[i])) {
					to=mid;
				}
				else {
					from=mid+1;
				}
			}
			if (2*to-N-1<0) {
				out.println(sum2[to]);
				continue;
			}
			out.println(sum2[to]+sum[2*to-N-1]);
		}
		out.flush();
 	} 
	static boolean f(int[] A,int mid,int X) {
		int diff=A[mid]-X;
		int k=lower_bound(A, 0, mid, X-diff);
		int S=A.length-mid;
		int T=mid-k+1;
		if (S-T<=0) {
			return true;
		}else {
			return false;
		}
	}
	public static int lower_bound(int[] a, int fromIndex, int toIndex, int val) {
		if (val > a[toIndex])
			return toIndex + 1;
		if (val <= a[fromIndex])
			return fromIndex;
		int lb = fromIndex - 1, ub = toIndex;
		while (ub - lb > 1) {
			int mid = (ub - lb)/2+lb;
			if (a[mid] >= val) {
				ub = mid;
			} else {
				lb = mid;
			}
		}
		return ub;
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

