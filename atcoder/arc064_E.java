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
		double sx=sc.nextDouble();
		double sy=sc.nextDouble();
		double tx=sc.nextDouble();
		double ty=sc.nextDouble();
		int N=sc.nextInt();
		graph G=new graph(N+2);
		double[][] xyr=new double[N+2][3];
		xyr[0][0]=sx;
		xyr[0][1]=sy;
		xyr[N+1][0]=tx;
		xyr[N+1][1]=ty;
		for (int i = 1; i < xyr.length-1; i++) {
			for (int j = 0; j < 3; j++) {
				xyr[i][j]=sc.nextDouble();
			}
		}
		for (int i = 0; i < xyr.length-1; i++) {
			for (int j = i+1; j < xyr.length; j++) {
				double x0=xyr[i][0];
				double y0=xyr[i][1];
				double x1=xyr[j][0];
				double y1=xyr[j][1];
				double r0=xyr[i][2];
				double r1=xyr[j][2];
				G.addEdge(i, j, Math.max(0, d1(x0, y0, x1, y1)-r1-r0));
				G.addEdge(j, i, Math.max(0, d1(x0, y0, x1, y1)-r1-r0));
			}
		}
		double[] ds=G.dijkstra(0);
		System.out.println(ds[N+1]);
 	} 
	static double d1(double x0,double y0,double x1,double y1){
		return Math.sqrt((x0-x1)*(x0-x1) + (y0-y1)*(y0-y1));
	}
	static class Edge implements Comparable<Edge>{
		int to;
		double v;
		public Edge(int to,double v) {
			this.to=to;
			this.v=v;
		}
		@Override
    	public boolean equals(Object obj) {
    		if(obj instanceof Edge) {
    			Edge other = (Edge) obj;
    			return other.to==this.to && other.v==this.v;
    		}
    		return false;
    	}//同値の定義
    	@Override
    	public int hashCode() {
    		return Objects.hash(this.to,this.v);
    	}
    	@Override
		  public int compareTo( Edge p2 ){
			 if (this.v>p2.v) {
				return 1;
			}
			 else if (this.v<p2.v) {
				return -1;
			}
			 else {
				return 0;
			}
		  }
	} 
	static class graph{
		ArrayList<Edge>[] list;
		int size;
		long INF=Long.MAX_VALUE/2;
		int[] color;
		@SuppressWarnings("unchecked")
		public graph(int n) {
			size = n;
			list = new ArrayList[n];
			color =new int[n];
			for(int i=0;i<n;i++){
				list[i] = new ArrayList<Edge>();
			}
		}
		void addEdge(int from,int to,double w) {
			list[from].add(new Edge(to,w));
		}
		double[] dijkstra(int s){
	        double[] L = new double[size];
	        for(int i=0;i<size;i++){
	            L[i] = -1;
	        }
	        int[] visited = new int[size];
	        L[s] = 0;
	        PriorityQueue<Edge> Q = new PriorityQueue<Edge>();
	        Q.add(new Edge(s,0L));//Edgeに最短距離候補を持たせる
	        while(!Q.isEmpty()){
	            Edge C = Q.poll();
	            if(visited[C.to]==0){
	                L[C.to] = C.v;
	                visited[C.to] = 1;
	                for(Edge D:list[C.to]){
	                	if (visited[D.to]==1) {
							continue;
						}
	                    Q.add(new Edge(D.to,L[C.to]+D.v));
	                }
	            }
	        }
	        return L;
	    }
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

