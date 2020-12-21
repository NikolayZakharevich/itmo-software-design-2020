import java.util.HashMap;
import java.util.Map;

public class LruCache<K, V> {

    public final static int CAPACITY_DEFAULT = 100;

    private final Map<K, Node> storage;

    private Node head = getDummyNode();
    private Node tail = getDummyNode();

    private Node dummy = getDummyNode();

    public int capacity;

    private final class Node {
        Node prev;
        Node next;
        K key;
        V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public LruCache() {
        this(CAPACITY_DEFAULT);
    }

    public LruCache(int capacity) {
        this.capacity = capacity;
        storage = new HashMap<>(capacity);
        queueInit();
    }

    public V get(K key) {
        Node node = storage.getOrDefault(key, dummy);
        if (node == dummy) {
            return null;
        }
        queueRemove(node);
        queuePush(node);
        return node.value;
    }

    /**
     * @param key   - cache key
     * @param value - stored value
     * @return if cache already contains key then returns previous value, otherwise returns null
     */
    public V put(K key, V value) {
        assert (storage.size() <= capacity) : "too many keys in cache";

        Node node = storage.getOrDefault(key, dummy);
        if (node == dummy) {
            if (storage.size() == capacity) {
                K displacedKey = queuePop();
                assert (storage.containsKey(displacedKey)) : "storage and queue are not consistent";
                storage.remove(displacedKey);
            }
            Node newNode = new Node(key, value);
            queuePush(newNode);
            storage.put(key, newNode);
            return null;
        }

        V oldValue = node.value;
        node.value = value;
        queueRemove(node);
        queuePush(node);
        return oldValue;
    }

    public int size() {
        return storage.size();
    }

    public void resize(int newCapacity) {
        capacity = newCapacity;
    }

    public void reset() {
        storage.clear();
        queueInit();
    }

    /**
     * Queue based on doubly linked list:
     */

    private void queueInit() {
        head.prev = tail;
        tail.next = head;
    }

    private void queueRemove(Node node) {
        assert (node.prev != null && node.next != null && node.prev != node.next);
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void queuePush(Node node) {
        assert (node != null && head != null && head.prev != null) : "node is not null, head is valid";

        node.next = head;
        node.prev = head.prev;
        head.prev.next = node;
        head.prev = node;
    }

    private K queuePop() {
        K poppedKey = tail.next.key;

        tail.next.next.prev = tail;
        tail.next = tail.next.next;
        return poppedKey;
    }

    private Node getDummyNode() {
        return new Node(null, null);
    }

}
