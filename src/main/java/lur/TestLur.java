package lur;

public class TestLur {

    public static void main(String[] args) {

        System.out.println();
        System.out.println("===========================LRU 链表实现===========================");
        LRUCache1<Integer, String> lru = new LRUCache1(5);
        lru.put(1, "11");
        lru.put(2, "22");
        lru.put(3, "33");
        lru.put(4, "44");
        lru.put(5, "55");
        System.out.println(lru.toString());
        lru.put(6, "66");
        lru.get(2);
        lru.put(7, "77");
        lru.get(4);
        System.out.println(lru.toString());
        System.out.println();


    }

}
