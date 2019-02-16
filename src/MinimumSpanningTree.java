import java.util.*;

public class MinimumSpanningTree { // Minimum Spanning Tree
    /*给出一些Connections，即Connections类，找到一些能够将所有城市都连接起来并且花费最小的边。
    如果说可以将所有城市都连接起来，则返回这个连接方法；不然的话返回一个空列表。*/
    public List<Connection> lowestCost(List<Connection> connections) {
        List<Connection> res = new ArrayList<>();
        Collections.sort(connections, new Comparator<Connection>() {
            @Override
            public int compare(Connection conn1, Connection conn2) {
                if (conn1.cost != conn2.cost)
                    return conn1.cost - conn2.cost;
                return (conn1.city1 + conn1.city2).compareTo(conn2.city1 + conn2.city2);
            }
        });

        // UnionFindSetEnhanced 解法
        Map<String, UnionFindSetEnhanced> cityMap = new HashMap<>();
        for (Connection c : connections) {
            UnionFindSetEnhanced ufs1 = cityMap.getOrDefault(c.city1, new UnionFindSetEnhanced());
            UnionFindSetEnhanced ufs2 = cityMap.getOrDefault(c.city2, new UnionFindSetEnhanced());
            cityMap.put(c.city1, ufs1);
            cityMap.put(c.city2, ufs2);
            if (ufs1.find() != ufs2.find()) {
                ufs1.union(ufs2);
                res.add(c);
            }
        }

        /*
        //UnionFindSet的解法
        Map<String, Integer> cityMap = new HashMap<>();
        UnionFindSet ufs = new UnionFindSet(connections.size() * 2); // 最多可能有connections长度2倍的点，但是不一定全都会用到。
        for (Connection c : connections) {
            int c1 = getCityId(cityMap, c.city1);
            int c2 = getCityId(cityMap, c.city2);
            if (ufs.find(c1) != ufs.find(c2)) { // 表示两个城市尚未联通
                ufs.union(c1, c2); // 联通
                res.add(c); // 记入结果
            }
        }*/

        if (res.size() + 1 == cityMap.size()) // 恰好联通所有城市且没有环路时，道路数目+1 = 城市数量
            return res;
        return new ArrayList<>();
    }

    private int getCityId(Map<String, Integer> cityMap, String cityName) {
        int id = cityMap.getOrDefault(cityName, -1);
        if (id == -1) {
            id = cityMap.size();
            cityMap.put(cityName, id);
        }
        return id;
    }
}


class Connection {
    public String city1, city2;
    public int cost;

    public Connection(String city1, String city2, int cost) {
        this.city1 = city1;
        this.city2 = city2;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return city1 + " --> " + city2 + " cost:" + cost;
    }
}

class UnionFindSet { //根节点为代表元素，其值是负数，负几表示该集合中有几个元素。其余元素的值是父节点的角标
    int[] set;

    public UnionFindSet(int setSize) {
        set = new int[setSize];
        Arrays.fill(set, -1);
    }

    public void union(int a, int b) {
        int headA = find(a);
        int headB = find(b);
        if (headA == headB)
            return;
        if (set[headA] < set[headB]) { // 说明A集合大
            set[headA] += set[headB];
            set[headB] = headA;
        } else {
            set[headB] += set[headA];
            set[headA] = headB;
        }
    }

    public boolean isConnected(int a, int b) {
        return find(a) == find(b);
    }

    public int find(int target) {
        if (set[target] < 0)
            return target;
        set[target] = find(set[target]);
        return set[target];
    }
}

class UnionFindSetEnhanced { // UnionFindSet的增强 不限长度，城市数量不用预先定死
    int count;
    UnionFindSetEnhanced father;

    public UnionFindSetEnhanced() {
        count = 1;
        father = this;
    }

    public void union(UnionFindSetEnhanced ufsb) {
        UnionFindSetEnhanced ufsa = find();
        ufsb = ufsb.find();
        if (ufsa.count > ufsb.count) { //说明集合A更大
            ufsa.count += ufsb.count;
            ufsb.count = 1;
            ufsb.father = ufsa;
        } else {
            ufsb.count += ufsa.count;
            ufsa.count = 1;
            ufsa.father = ufsb;
        }
    }

    public UnionFindSetEnhanced find() {
        if (this == father)
            return this;
        father = father.find();
        return father;
    }
}