
/**
 * size �Ż�
 * @author Administrator
 *
 */
public class UnionFind2 implements IUnionFind{

	private int count;
	private int[] parent;
	private int[] sz;
	
	//constructor
	public UnionFind2(int n) {
		this.count = n;
		this.parent = new int[n];
		this.sz = new int[n];
		//initiate
		for (int i = 0; i < n; i++) {
			parent[i] = i;//ÿ��Ԫ��ָ���Լ�
			sz[i] = 1;//ÿ�����ݼ���СΪ1 
		}
	}
	
	/**
	 * ���Ҹ��ڵ�
	 * @return
	 */
	private int find(int p) {
		assert(p >= 0 && p < count);
		while(p != parent[p]) {
			p = parent[p];
		}
		return p;
	}
	
	public boolean isConnected(int p , int q) {
		return find(p) == find(q);
	}
	
	public void union(int p , int q) {
		int pRoot = find(p);
		int qRoot = find(q);
		if(pRoot == qRoot) {
			return;
		}
		 // ��������Ԫ����������Ԫ�ظ�����ͬ�жϺϲ�����
        // ��Ԫ�ظ����ٵļ��Ϻϲ���Ԫ�ظ�����ļ�����
		if(sz[p] < sz[q]) {
			parent[p] = qRoot;
			sz[qRoot] += sz[pRoot];
		}else {
			parent[q] = pRoot;
			sz[pRoot] += sz[qRoot]; 
		}
	}

	@Override
	public String method() {
		// TODO Auto-generated method stub
		return "UnionFind2";
	}
}






















