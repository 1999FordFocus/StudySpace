
/**
 *       ��������
 * @author Administrator
 *
 */
public class UnionFindHelper {
	

    // ���Ե�һ�汾�Ĳ��鼯, ����Ԫ�ظ���Ϊn, �����߼���֮ǰ����ȫһ����
    // ˼��һ��: ��������������������?
    // ��������γ̲������ģʽ�γ�, ������Ͳ�����������ص����⽲���ˡ���������ҵ�˼����:)
    public static void testUF(int n ,IUnionFind iUnionFind){

        long startTime = System.currentTimeMillis();

        // ����n�β���, ÿ�����ѡ������Ԫ�ؽ��кϲ�����
        for( int i = 0 ; i < n ; i ++ ){
            int a = (int)(Math.random()*n);
            int b = (int)(Math.random()*n);
            iUnionFind.union(a,b);
        }
        // �ٽ���n�β���, ÿ�����ѡ������Ԫ��, ��ѯ�����Ƿ�ͬ��һ������
        for(int i = 0 ; i < n ; i ++ ){
            int a = (int)(Math.random()*n);
            int b = (int)(Math.random()*n);
            iUnionFind.isConnected(a,b);
        }
        long endTime = System.currentTimeMillis();

        // ��ӡ�������2n�������ĺ�ʱ
        System.out.println(iUnionFind.method()+", " + 2*n + " ops, " + (endTime-startTime) + "ms");
    }

}
