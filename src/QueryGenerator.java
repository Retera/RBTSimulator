import java.util.LinkedList;
import java.util.Random;


public class QueryGenerator {

	public static enum QueryType{Insert,Search,Delete,RankOfElement,KSmallest,KLargest,RangeCount,ElementAtRank};

	/**
	 * @param N - number of queries to generate
	 * @param p - Prob(I, S, or D query)
	 * @return 
	 */
	public static String[] generateQueries(int N, double p) {
		String[] q = new String[N];
		Random random = new Random(System.currentTimeMillis());
		LinkedList<Integer> data = new LinkedList<Integer>();
		int k;
		double r;
		int r2;
		for (int i = 0; i < N; i++) {
			if (random.nextDouble() < p) {
				// simple query
				r = random.nextDouble();
				if (r < 0.5) {
					// insert
					r2 = random.nextInt();
					data.add(r2);
					q[i] = String.format("Insert %d %d",r2,r2);
				} else if (r < 0.75) {
					// search
					if (data.isEmpty()) {
						// search for something that's probably not there
						r2 = random.nextInt();
						q[i] = String.format("Search %d", r2);
					} else {
						// search for something that should be there
						r2 = random.nextInt(data.size());
						int d = data.get(r2);
						q[i] = String.format("Search %d", d);
					}

				} else {
					// delete
					if (data.isEmpty()) {
						// delete something that's probably not there
						r2 = random.nextInt();
						q[i] = String.format("Delete %d", r2);
					} else {
						// delete something that should be there
						r2 = random.nextInt(data.size());
						int d = data.get(r2);
						data.remove(r2);
						q[i] = String.format("Delete %d", d);
					}
				}
			} else {
				// range query
				switch (random.nextInt(5)) {
				case 0 :
					// rank of element
					if (data.isEmpty()) {
						// rank of something probably not there
						r2 = random.nextInt();
						q[i] = String.format("Rank %d", r2);
					} else {
						// rank of something that should be there
						r2 = random.nextInt(data.size());
						int d = data.get(r2);
						data.remove(r2);
						q[i] = String.format("Rank %d", d);
					}
					break;
				case 1 :
					// k-smallest
					k = random.nextInt(Math.max(1,data.size()));
					q[i] = String.format("kSmallest %d",k);
					break;
				case 2 :
					// k-largest
					k = random.nextInt(Math.max(1,data.size()));
					q[i] = String.format("kLargest %d",k);
					break;
				case 3 :
					// range count
					int x_min,x_max;
					if (data.isEmpty()) {
						// random range
						x_min = random.nextInt();
						x_max = x_min + random.nextInt(1000);
					} else {
						//not so random range
						x_min = data.get(random.nextInt(data.size()));
						x_max = x_min + random.nextInt(1000);
					}
					q[i] = String.format("RangeCount %d %d", x_min,x_max);
					break;
				case 4 :
					// element at rank
					r2 = random.nextInt(Integer.MAX_VALUE);
					q[i] = String.format("GetValByRank %d", r2);
					break;
				default:
					// noop
					q[i] = "noop";
					break;
				}
			}
		}
		return q;
	}

	public static void main(String[] args) {
		String[] q = generateQueries(100,0.8);
		for (String s : q) {
			System.out.println(s);
		}
	}

}
