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
		long K=sc.nextLong();
		long[] A=sc.nextLongArray(N);
		long[] sum=new long[N+1];
		for (int i = 1; i < sum.length; i++) {
			sum[i]=sum[i-1]+A[i-1];
		}
		TreeMap<Long, Integer> treeMap=new TreeMap<>();
		for (int i = 0; i < sum.length; i++) {
			sum[i]=sum[i]-K*(long)i;
			treeMap.put(sum[i], 0);
		}
		SegTree<Long> segTree=new SegTree<Long>(200001, (x,y)->x+y, 0L);
		int c=0;
		for (long l: treeMap.keySet()) {
			treeMap.put(l, c);
			c++;
		}
		long ans=0;
		for (int i = 0; i <= N; i++) {
			int num=treeMap.get(sum[i]);
			ans+=segTree.prod(0, num+1);
			segTree.set(num, segTree.get(num)+1);
		}
		System.out.println(ans);
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
//SegTree<Integer> segTree=new SegTree<Integer>(N, (x,y)->x+y, 0);使用例
class SegTree<S> {
	    final int MAX;

	    final int N;
	    final java.util.function.BinaryOperator<S> op;
	    final S E;

	    final S[] data;

	    @SuppressWarnings("unchecked")
	    public SegTree(int n, java.util.function.BinaryOperator<S> op, S e) {
	        this.MAX = n;
	        int k = 1;
	        while (k < n) k <<= 1;
	        this.N = k;
	        this.E = e;
	        this.op = op;
	        this.data = (S[]) new Object[N << 1];
	        java.util.Arrays.fill(data, E);
	    }

	    public SegTree(S[] dat, java.util.function.BinaryOperator<S> op, S e) {
	        this(dat.length, op, e);
	        build(dat);
	    }

	    private void build(S[] dat) {
	        int l = dat.length;
	        System.arraycopy(dat, 0, data, N, l);
	        for (int i = N - 1; i > 0; i--) {
	            data[i] = op.apply(data[i << 1 | 0], data[i << 1 | 1]);
	        }
	    }

	    public void set(int p, S x) {
	        exclusiveRangeCheck(p);
	        data[p += N] = x;
	        p >>= 1;
	        while (p > 0) {
	            data[p] = op.apply(data[p << 1 | 0], data[p << 1 | 1]);
	            p >>= 1;
	        }
	    }

	    public S get(int p) {
	        exclusiveRangeCheck(p);
	        return data[p + N];
	    }

	    public S prod(int l, int r) {
	        if (l > r) {
	            throw new IllegalArgumentException(
	                String.format("Invalid range: [%d, %d)", l, r)
	            );
	        }
	        inclusiveRangeCheck(l);
	        inclusiveRangeCheck(r);
	        S sumLeft = E;
	        S sumRight = E;
	        l += N; r += N;
	        while (l < r) {
	            if ((l & 1) == 1) sumLeft = op.apply(sumLeft, data[l++]);
	            if ((r & 1) == 1) sumRight = op.apply(data[--r], sumRight);
	            l >>= 1; r >>= 1;
	        }
	        return op.apply(sumLeft, sumRight);
	    }

	    public S allProd() {
	        return data[1];
	    }

	    public int maxRight(int l, java.util.function.Predicate<S> f) {
	        inclusiveRangeCheck(l);
	        if (!f.test(E)) {
	            throw new IllegalArgumentException("Identity element must satisfy the condition.");
	        }
	        if (l == MAX) return MAX;
	        l += N;
	        S sum = E;
	        do {
	            l >>= Integer.numberOfTrailingZeros(l);
	            if (!f.test(op.apply(sum, data[l]))) {
	                while (l < N) {
	                    l = l << 1;
	                    if (f.test(op.apply(sum, data[l]))) {
	                        sum = op.apply(sum, data[l]);
	                        l++;
	                    }
	                }
	                return l - N;
	            }
	            sum = op.apply(sum, data[l]);
	            l++;
	        } while ((l & -l) != l);
	        return MAX;
	    }

	    public int minLeft(int r, java.util.function.Predicate<S> f) {
	        inclusiveRangeCheck(r);
	        if (!f.test(E)) {
	            throw new IllegalArgumentException("Identity element must satisfy the condition.");
	        }
	        if (r == 0) return 0;
	        r += N;
	        S sum = E;
	        do {
	            r--;
	            while (r > 1 && (r & 1) == 1) r >>= 1;
	            if (!f.test(op.apply(data[r], sum))) {
	                while (r < N) {
	                    r = r << 1 | 1;
	                    if (f.test(op.apply(data[r], sum))) {
	                        sum = op.apply(data[r], sum);
	                        r--;
	                    }
	                }
	                return r + 1 - N;
	            }
	            sum = op.apply(data[r], sum);
	        } while ((r & -r) != r);
	        return 0;
	    }

	    private void exclusiveRangeCheck(int p) {
	        if (p < 0 || p >= MAX) {
	            throw new IndexOutOfBoundsException(
	                String.format("Index %d out of bounds for the range [%d, %d).", p, 0, MAX)
	            );
	        }
	    }

	    private void inclusiveRangeCheck(int p) {
	        if (p < 0 || p > MAX) {
	            throw new IndexOutOfBoundsException(
	                String.format("Index %d out of bounds for the range [%d, %d].", p, 0, MAX)
	            );
	        }
	    }
	}



