
/**
 * ���鼯����ʵ��
 * @apiNote ���˼�룺
 * 		����һ��ָ�򸸽ڵ��������ʾ��ʹ������s[] �洢
 * 		ÿ��Ԫ��i��Ӧ��ֵs[i]��ʾԪ����ָ��ĸ��ڵ�
 * 		���ڵ��������parent == p
 */
public class UnionFind1 implements IUnionFind{

	private int[] parent;
	private int count;
	
	//constructor
	public UnionFind1(int count) {
		parent = new int[count];
		this.count = count;
		
		//initiate
		for(int i=0;i<count;i++) {
			parent[i]=i; //ÿ��Ԫ��ָ���Լ����Գɼ���
		}
	}
	
	/**
	 * ����Ԫ��p
	 * @param p
	 * O(h)���Ӷ�, hΪ���ĸ߶�
	 */
	private int find(int p) {
		assert(p>=0 && p<count);
		//����׷���Լ��ĸ��ڵ㣬ֱ�����ڵ㣬���ڵ��������parent == p
		while(p != parent[p]) {
			p = parent[p];//��������׷��
		}
		return p;
	}
	
	/**
	 * �鿴Ԫ��p��Ԫ��q�Ƿ�����һ�����ϣ��Ƿ����ӣ�
	 * O(h)���Ӷ�, hΪ���ĸ߶�
	 */
	public boolean isConnected(int p, int q) {
		return find(p) == find(q);
	}
	/**
	 * �ϲ�Ԫ��p��Ԫ��q�����ļ���
	 * @param p
	 * @param q
	 * O(h)���Ӷ�, hΪ���ĸ߶�
	 */
	public void union(int p ,int q) {
		int pRoot = find(p);
		int qRoot = find(q);
		if(pRoot == qRoot) {
			return;
		}
		parent[pRoot] = qRoot;
	}

	@Override
	public String method() {
		// TODO Auto-generated method stub
		return "UnionFind1";
	}
}






























