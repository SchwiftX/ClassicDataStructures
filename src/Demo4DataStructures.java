import java.util.*;

public class Demo4DataStructures {
    public static void main(String[] args) {
        // Demo 8 Trie
//        Trie trie = new Trie();
//        trie.insert("aab");
//        trie.insert("aab");
//        trie.insert("abc");
//        System.out.println(trie.search("ab"));
//        System.out.println(trie.search("aab"));
//        System.out.println(trie.startsWith("aa"));
//        System.out.println(trie.startsWith("ab"));
//        System.out.println(trie.startsWith("ac"));

        // Demo 7 Email using Union Find Set
//        List<Element> list = new ArrayList<>();
//        list.add(new Element("A1", "a1@gmail.com", "a2@gmail.com"));
//        list.add(new Element("A2", "b1@gmail.com", "a2@gmail.com"));
//        list.add(new Element("A3", "c1@gmail.com"));
//        list.add(new Element("A4", "c1@gmail.com", "d1@gmail.com"));
//        list.add(new Element("A5", "b1@gmail.com", "e1@gmail.com"));
//        System.out.println(emailMergeProblem(list));

        // Demo 6 SegmentTree
//        int[] nums = {1, 4, 12, 2, 0, 18, 7, 5, 0, 18};
//        SegmentTree st = new SegmentTree(nums);
//        List<Interval> buildings = new ArrayList<>();
//        buildings.add(new Interval(0,1)); buildings.add(new Interval(4,4)); buildings.add(new Interval(6,9));
//        System.out.println(st.query(buildings));
//        System.out.println(st.query(0,nums.length - 1));
//        List<Interval> rains = new ArrayList<>();
//        rains.add(new Interval(-5, 6, 1));
//        rains.add(new Interval(3, 37, 3));
//        st.add(rains);
//        System.out.println(st.query(buildings));
//        System.out.println(st.query(0,nums.length - 1));
//        st.modify(4,5);
//        System.out.println(st.query(buildings));
//        System.out.println(st.query(0,nums.length - 1));

        // Demo 5 MinimumSpanningTree
        /*MinimumSpanningTree mst = new MinimumSpanningTree();
        List<Connection> data = new ArrayList<>();
        data.add(new Connection("北","上",3));data.add(new Connection("北","广",2));
        data.add(new Connection("上","广",4));data.add(new Connection("广","深",1));
        System.out.println(mst.lowestCost(data));*/
        // Demo 4 MyHeap
        /*Integer[] data = new Integer[]{30, 20, 10, 40, 50, 14, 15};
        List<Integer> list = new ArrayList<>(Arrays.asList(data));
        MyHeap myHeap = new MyHeap(list);
        System.out.println(myHeap);
        System.out.println("top:" + myHeap.top());
        System.out.println("pop:" + myHeap.pop());
        System.out.println(myHeap);
        myHeap.push(10);
        System.out.println(myHeap);
        System.out.println(myHeap.delete(40));
        System.out.println(myHeap);

        // Demo 4 Heap Sort
        SortAlgorithms sa = new SortAlgorithms();
        sa.heapSort(data);
        for (int i : data)
            System.out.print(i + " ");

        // Merge Sort
        int[] ints = new int[]{30, 20, 10, 40, 50, 14, 15};
        SortAlgorithms s = new SortAlgorithms();
        System.out.println(Arrays.toString(ints));
        s.mergeSort(ints);
        System.out.println(Arrays.toString(ints));*/
        int[] ints = new int[]{30, 20, 10, 40, 50, 14, 15};
        SortAlgorithms s = new SortAlgorithms();
        System.out.println(s.quickSelect(ints, 0, ints.length - 1, 2));
        System.out.println(s.quickSelectAdvanced(ints, 0, ints.length - 1, 2));

        // Demo 4 LFU Cache
        /*LFUCache lfu = new LFUCache(3);
        lfu.put(1, 1);
        lfu.put(2, 2);
        System.out.println(lfu.get(1));
        lfu.put(3, 3);
        System.out.println(lfu.get(2));
        System.out.println(lfu.get(3));
        lfu.put(4, 4);
        System.out.println(lfu.get(1));
        System.out.println(lfu.get(3));
        System.out.println(lfu.get(4));*/
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