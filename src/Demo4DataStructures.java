import java.util.*;

public class Demo4DataStructures {
    public static void main(String[] args) {
        fenwickTreeTest();
//        MyLinkedList myLinkedList = new MyLinkedList();
//        myLinkedList.addAtHead(5);
//        myLinkedList.addAtHead(2);
//        myLinkedList.deleteAtIndex(1);
//        myLinkedList.addAtIndex(1,9);
//        myLinkedList.addAtHead(4);
//        myLinkedList.addAtHead(9);
//        myLinkedList.addAtHead(8);
//        System.out.println(myLinkedList.get(3));
//        myLinkedList.addAtTail(1);
//        myLinkedList.addAtIndex(3,6);
//        myLinkedList.addAtHead(3);
//        System.out.println(myLinkedList);
    }

    private static void fenwickTreeTest(){
        int[] data = new int[]{1,40,3,2,5,9,6}; // 正数就行
        FenwickTree ft = new FenwickTree(data.length);
        ft.update(1, data[0]);
        for (int i = 1; i < data.length; i++)
            ft.update(i + 1, data[i] - data[i - 1]);
        System.out.println(ft.getSum(6) - ft.getSum(2));
        int x = 2, y = 5, k = 20;
        ft.update(x, k); // data[x] - data[x - 1] 增加 k
        ft.update(y + 1, - k); // data[y + 1] - data[y] 减少 k 表示[x, y]这个区间都增加了k
        System.out.println(ft.getSum(6) - ft.getSum(2));
    }

    private static void segmentTreeTest(){
        int[] data = new int[6];
        SegmentTree st = new SegmentTree(data);
        List<Interval> list = new ArrayList<>();
        list.add(new Interval(0,2,3));
        list.add(new Interval(1,1,7));
//        list.add(new Interval(2,3,5));
//        list.add(new Interval(4,5,2));
        st.add(list);
        System.out.println(st.query(0,2));
    }

    private static List<List<String>> emailMergeProblem(List<Element> list){
        //邮箱合并问题 给出几家合并公司的账号以及账号绑定的邮箱，
        //合并那些具有相同绑定邮箱的账号。
        /*Example：
        { "A1": ["a1@gmail.com", "a2@gmail.com"],
            "A2": ["b1@gmail.com", "a2@gmail.com"],
            "A3": ["c1@gmail.com"],
            "A4": ["c1@gmail.com", "d1@gmail.com"],
            "A5": ["b1@gmail.com", "e1@gmail.com"]}
        Output：["A1", "A2", "A5"], ["A3", "A4"]*/

        Map<UnionFindSetEnhanced, List<String>> setMap = new HashMap<>();
        Map<String, UnionFindSetEnhanced> ufseMap = new HashMap<>();
        for(Element e : list){
            UnionFindSetEnhanced lastUfse = null;
            for(int i = 0; i < e.emails.size(); i++){
                UnionFindSetEnhanced ufse = ufseMap.getOrDefault(e.emails.get(i), new UnionFindSetEnhanced());
                ufseMap.put(e.emails.get(i), ufse);
                if(lastUfse != null)
                    ufse.union(lastUfse);
                lastUfse = ufse;
            }
            UnionFindSetEnhanced topUfse = lastUfse.find();
            List<String> strs = setMap.getOrDefault(topUfse, new ArrayList<>());
            setMap.put(topUfse, strs);
            strs.add(e.account);
        }
        Collection<List<String>> values = setMap.values();
        return new ArrayList<>(values);
    }
}
class Element{
    String account;
    List<String> emails;
    Element(String account, String ... strings){
        this.account = account;
        emails = Arrays.asList(strings);
    }

}