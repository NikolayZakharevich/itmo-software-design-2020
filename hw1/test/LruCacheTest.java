import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LruCacheTest {

    private LruCache<String, Integer> cache;

    private static final String KEY_TEST = "test";
    private static final String KEY_TEST1 = "test1";
    private static final String KEY_TEST2 = "test2";
    private static final String KEY_TEST3 = "test3";

    @BeforeEach
    void setUp() {
        cache = new LruCache<>();
    }

    @Test
    void get_idempotence() {
        assertNull(cache.put(KEY_TEST, 1));
        assertEquals(1, cache.get(KEY_TEST));
        assertEquals(1, cache.get(KEY_TEST));
    }

    @Test
    void put_replace() {
        assertNull(cache.put(KEY_TEST, 0));
        assertEquals(0, cache.put(KEY_TEST, 1));
        assertEquals(1, cache.put(KEY_TEST, 2));
    }

    @Test
    void cache() {
        assertNull(cache.put(KEY_TEST, 0));
        assertEquals(0, cache.get(KEY_TEST));
    }

    @Test
    void cache_displacementFirst() {
        cache = new LruCache<>(1);
        assertNull(cache.put(KEY_TEST1, 1));
        assertNull(cache.put(KEY_TEST2, 2));
        assertNull(cache.get(KEY_TEST1));
        assertEquals(2, cache.get(KEY_TEST2));
    }

    @Test
    void cache_displacementLastUsed() {
        cache = new LruCache<>(2);
        assertNull(cache.put(KEY_TEST1, 1));
        assertNull(cache.put(KEY_TEST2, 2));

        assertEquals(1, cache.get(KEY_TEST1));
        assertNull(cache.put(KEY_TEST3, 3));

        assertNull(cache.get(KEY_TEST2));
        assertEquals(1, cache.get(KEY_TEST1));
        assertEquals(3, cache.get(KEY_TEST3));
    }

    @Test
    void reset() {
        assertNull(cache.put(KEY_TEST, 1));
        cache.reset();
        assertNull(cache.get(KEY_TEST));
    }

    @Test
    void resize() {
        cache = new LruCache<>(1);
        assertNull(cache.put(KEY_TEST1, 1));
        assertNull(cache.put(KEY_TEST2, 2));
        assertEquals(1, cache.size());
        cache.resize(2);
        assertNull(cache.put(KEY_TEST3, 3));
        assertEquals(2, cache.size());
    }
}