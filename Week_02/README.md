## HashMap.java源码解析

### 概述

```java

///HashMap非线程安全,允许值为null
///hashTable线程安全，不允许值为null
/**
 * Hash table based implementation of the <tt>Map</tt> interface.  This
 * implementation provides all of the optional map operations, and permits
 * <tt>null</tt> values and the <tt>null</tt> key.  (The <tt>HashMap</tt>
 * class is roughly equivalent to <tt>Hashtable</tt>, except that it is
 * unsynchronized and permits nulls.)  This class makes no guarantees as to
 * the order of the map; in particular, it does not guarantee that the order
 * will remain constant over time.
 *
 //大多数情况下，get操作和put操作的时间复杂度都是O(1)
 //遍历HashMap的时间和哈希表的capacity与size都有关系
 //如果遍历哈希表比较重要的话，初始容量和负载因子的设置非常重要
 
 * <p>This implementation provides constant-time performance for the basic
 * operations (<tt>get</tt> and <tt>put</tt>), assuming the hash function
 * disperses the elements properly among the buckets.  Iteration over
 * collection views requires time proportional to the "capacity" of the
 * <tt>HashMap</tt> instance (the number of buckets) plus its size (the number
 * of key-value mappings).  Thus, it's very important not to set the initial
 * capacity too high (or the load factor too low) if iteration performance is
 * important.
 *
 ///影响HashMap性能的两大参数分别为capacity和load factor 容量和负载因子
 ///当元素数量超过其容量与负载因子的乘积时，HashMap将会进行rehash操作，实现动态扩容
 
 * <p>An instance of <tt>HashMap</tt> has two parameters that affect its
 * performance: <i>initial capacity</i> and <i>load factor</i>.  The
 * <i>capacity</i> is the number of buckets in the hash table, and the initial
 * capacity is simply the capacity at the time the hash table is created.  The
 * <i>load factor</i> is a measure of how full the hash table is allowed to
 * get before its capacity is automatically increased.  When the number of
 * entries in the hash table exceeds the product of the load factor and the
 * current capacity, the hash table is <i>rehashed</i> (that is, internal data
 * structures are rebuilt) so that the hash table has approximately twice the
 * number of buckets.
 *
 
 ///一般来说，综合时间和空间性能的考量，负载因子设为0.75拥有比较好的性能表现
 ///为了减少rehash操作，初始容量设置的比放入元素的最大数量大的话，可以避免rehash的发生
 
 * <p>As a general rule, the default load factor (.75) offers a good
 * tradeoff between time and space costs.  Higher values decrease the
 * space overhead but increase the lookup cost (reflected in most of
 * the operations of the <tt>HashMap</tt> class, including
 * <tt>get</tt> and <tt>put</tt>).  The expected number of entries in
 * the map and its load factor should be taken into account when
 * setting its initial capacity, so as to minimize the number of
 * rehash operations.  If the initial capacity is greater than the
 * maximum number of entries divided by the load factor, no rehash
 * operations will ever occur.
 *
 ///若key实现了Comparacoble接口，当哈希碰撞时，链表会根据比较器进行排序
 
 * <p>If many mappings are to be stored in a <tt>HashMap</tt>
 * instance, creating it with a sufficiently large capacity will allow
 * the mappings to be stored more efficiently than letting it perform
 * automatic rehashing as needed to grow the table.  Note that using
 * many keys with the same {@code hashCode()} is a sure way to slow
 * down performance of any hash table. To ameliorate impact, when keys
 * are {@link Comparacoble}, this class may use comparison order among
 * keys to help break ties.
 *
 
 ///非线程安全
 * <p><strong>Note that this implementation is not synchronized.</strong>
 * If multiple threads access a hash map concurrently, and at least one of
 * the threads modifies the map structurally, it <i>must</i> be
 * synchronized externally.  (A structural modification is any operation
 * that adds or deletes one or more mappings; merely changing the value
 * associated with a key that an instance already contains is not a
 * structural modification.)  This is typically accomplished by
 * synchronizing on some object that naturally encapsulates the map.
 *
 * If no such object exists, the map should be "wrapped" using the
 * {@link Collections#synchronizedMap Collections.synchronizedMap}
 * method.  This is best done at creation time, to prevent accidental
 * unsynchronized access to the map:<pre>
 *   Map m = Collections.synchronizedMap(new HashMap(...));</pre>
 *
 * <p>The iterators returned by all of this class's "collection view methods"
 * are <i>fail-fast</i>: if the map is structurally modified at any time after
 * the iterator is created, in any way except through the iterator's own
 * <tt>remove</tt> method, the iterator will throw a
 * {@link ConcurrentModificationException}.  Thus, in the face of concurrent
 * modification, the iterator fails quickly and cleanly, rather than risking
 * arbitrary, non-deterministic behavior at an undetermined time in the
 * future.
 *
 * <p>Note that the fail-fast behavior of an iterator cannot be guaranteed
 * as it is, generally speaking, impossible to make any hard guarantees in the
 * presence of unsynchronized concurrent modification.  Fail-fast iterators
 * throw <tt>ConcurrentModificationException</tt> on a best-effort basis.
 * Therefore, it would be wrong to write a program that depended on this
 * exception for its correctness: <i>the fail-fast behavior of iterators
 * should be used only to detect bugs.</i>
 *
 * <p>This class is a member of the
 * <a href="{@docRoot}/../technotes/g
```

```java
///默认容量为16 必须为2的正整数幂

/**
 * The default initial capacity - MUST be a power of two.
 */
static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16

///最大容量为2^30
/**
 * The maximum capacity, used if a higher value is implicitly specified
 * by either of the constructors with arguments.
 * MUST be a power of two <= 1<<30.
 */
static final int MAXIMUM_CAPACITY = 1 << 30;

///负载因为为0.75
/**
 * The load factor used when none specified in constructor.
 */
static final float DEFAULT_LOAD_FACTOR = 0.75f;

/**
 * The bin count threshold for using a tree rather than list for a
 * bin.  Bins are converted to trees when adding an element to a
 * bin with at least this many nodes. The value must be greater
 * than 2 and should be at least 8 to mesh with assumptions in
 * tree removal about conversion back to plain bins upon
 * shrinkage.
 */
static final int TREEIFY_THRESHOLD = 8;



/**
 * The bin count threshold for untreeifying a (split) bin during a
 * resize operation. Should be less than TREEIFY_THRESHOLD, and at
 * most 6 to mesh with shrinkage detection under removal.
 */
static final int UNTREEIFY_THRESHOLD = 6;

/**
 * The smallest table capacity for which bins may be treeified.
 * (Otherwise the table is resized if too many nodes in a bin.)
 * Should be at least 4 * TREEIFY_THRESHOLD to avoid conflicts
 * between resizing and treeification thresholds.
 */
static final int MIN_TREEIFY_CAPACITY = 64;
```

### 重要方法

#### hash方法

计算key的hash值，使用key的hashCode()方法的到HashCode，然后再右移16位；此处可以看出HashMap是支持null为key的。

```java
/**
 * Computes key.hashCode() and spreads (XORs) higher bits of hash
 * to lower.  Because the table uses power-of-two masking, sets of
 * hashes that vary only in bits above the current mask will
 * always collide. (Among known examples are sets of Float keys
 * holding consecutive whole numbers in small tables.)  So we
 * apply a transform that spreads the impact of higher bits
 * downward. There is a tradeoff between speed, utility, and
 * quality of bit-spreading. Because many common sets of hashes
 * are already reasonably distributed (so don't benefit from
 * spreading), and because we use trees to handle large sets of
 * collisions in bins, we just XOR some shifted bits in the
 * cheapest possible way to reduce systematic lossage, as well as
 * to incorporate impact of the highest bits that would otherwise
 * never be used in index calculations because of table bounds.
 */
static final int hash(Object key) {
    int h;
    return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
}
```

#### put方法

```java
/**
 * Associates the specified value with the specified key in this map.
 * If the map previously contained a mapping for the key, the old
 * value is replaced.
 *
 * @param key key with which the specified value is to be associated
 * @param value value to be associated with the specified key
 * @return the previous value associated with <tt>key</tt>, or
 *         <tt>null</tt> if there was no mapping for <tt>key</tt>.
 *         (A <tt>null</tt> return can also indicate that the map
 *         previously associated <tt>null</tt> with <tt>key</tt>.)
 */
public V put(K key, V value) {
    return putVal(hash(key), key, value, false, true);
}

@Override
public V putIfAbsent(K key, V value) {
    return putVal(hash(key), key, value, true, true);
}

/**
 * Implements Map.put and related methods
 *
 * @param hash hash for key
 * @param key the key
 * @param value the value to put
 * @param onlyIfAbsent if true, don't change existing value
 * @param evict if false, the table is in creation mode.
 * @return previous value, or null if none
 */
final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
               boolean evict) {
    Node<K,V>[] tab; Node<K,V> p; int n, i;
    //若数组为空或者数组长度为0，需要进行初始化
    if ((tab = table) == null || (n = tab.length) == 0)
        n = (tab = resize()).length;
    ///索引位置没有元素，直接添加
    if ((p = tab[i = (n - 1) & hash]) == null)
        tab[i] = newNode(hash, key, value, null);
    else {
        Node<K,V> e; K k;
        //索引位置包含元素，覆盖
        if (p.hash == hash &&
            ((k = p.key) == key || (key != null && key.equals(k))))
            e = p;
        ///如果是红黑树，则加入红黑树
        else if (p instanceof TreeNode)
            e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
		///索引处是一个链表
        else {        
            for (int binCount = 0; ; ++binCount) {
                ///链表中不存在，加入链表（尾插法）
                if ((e = p.next) == null) {
                    p.next = newNode(hash, key, value, null);
                    //若加入后链表长度达到8，则将链表变为红黑树
                    if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                        treeifyBin(tab, hash);
                    break;
                }
                ///链表中存在该元素，直接覆盖
                if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                    break;
                p = e;
            }
        }
        if (e != null) { // existing mapping for key
            V oldValue = e.value;
            ///与putIfAbsent()方法有关系
            if (!onlyIfAbsent || oldValue == null)
                e.value = value;
            afterNodeAccess(e);
            ///返回原值
            return oldValue;
        }
    }
    ///HashMap发生结构化修改时，计数器加一
    ++modCount;
    ///如果key的数目超过了阈值，则进行扩容
    if (++size > threshold)
        resize();
    ///子类方法
    afterNodeInsertion(evict);
    return null;
}
```

#### resize() 扩容方法

```java
/**
 * Initializes or doubles table size.  If null, allocates in
 * accord with initial capacity target held in field threshold.
 * Otherwise, because we are using power-of-two expansion, the
 * elements from each bin must either stay at same index, or move
 * with a power of two offset in the new table.
 *
 * @return the table
 */
final Node<K,V>[] resize() {
    ///扩容前哈希桶数组
    Node<K,V>[] oldTab = table;
    ///扩容前容量
    int oldCap = (oldTab == null) ? 0 : oldTab.length;
	///扩容前阈值
    int oldThr = threshold;
	///扩容后容量，扩容后阈值
    int newCap, newThr = 0;
    if (oldCap > 0) {
        //原有的容量超过最大容量则阈值设为最大，不再进行扩容（扩无可扩）
        if (oldCap >= MAXIMUM_CAPACITY) {
            threshold = Integer.MAX_VALUE;
            ///返回原哈希桶数组
            return oldTab;
        }
        ///原有容量扩容一倍
        ///前提条件：原有容量达到16且翻倍后的数量小于最大容量
        else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
                 oldCap >= DEFAULT_INITIAL_CAPACITY)
            ///扩容后阈值
            newThr = oldThr << 1; // double threshold
    }
    ///条件：1-oldCap==0 2-oldThr>0
    ///扩容为指定的阈值
    else if (oldThr > 0) // initial capacity was placed in threshold
        newCap = oldThr;
    ///条件：1-oldCap==0 2-oldThr==0
    ///默认值初始化
    else {               // zero initial threshold signifies using defaults
        newCap = DEFAULT_INITIAL_CAPACITY;///8
        newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);///8*0.75
    }
    ///若newThr未计算，则计算新的阈值
    if (newThr == 0) {
        float ft = (float)newCap * loadFactor;///新的容量与负载因子的乘积
        newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?///若newcap>2^30或者计算出的阈值大于2^30;取int的最大值
                  (int)ft : Integer.MAX_VALUE);
    }
    ///新的阈值计算完毕
    threshold = newThr;
    @SuppressWarnings({"rawtypes","unchecked"})
        Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];///创建新的哈希桶数组
    table = newTab;///替换旧的哈希桶数组
    if (oldTab != null) {
        ///遍历元素，放入新的哈希桶数组
        for (int j = 0; j < oldCap; ++j) {
            Node<K,V> e;
            if ((e = oldTab[j]) != null) {
                oldTab[j] = null;
                ///只有一个元素，直接放入新的索引位置
                if (e.next == null)
                    ///新的索引位置
                    newTab[e.hash & (newCap - 1)] = e;
                ///红黑树处理
                else if (e instanceof TreeNode)
                    ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
                ///链表处理
                else { // preserve order 尾插法---保证了之前的顺序
                    Node<K,V> loHead = null, loTail = null;
                    Node<K,V> hiHead = null, hiTail = null;
                    Node<K,V> next;
                    do {
                        /*********************************************/
					  /**
					   * 注: e本身就是一个链表的节点，它有 自身的值和next(链表的值)，但是因为next值对节点扩容没有帮助，
					   * 所有在下面讨论中，我近似认为 e是一个只有自身值，而没有next值的元素。
					   */
					  /*********************************************/
                        next = e.next;
                        // 注意：不是(e.hash & (oldCap-1));而是(e.hash & oldCap)
                            // (e.hash & oldCap) 得到的是 元素的在数组中的位置是否需要移动,示例如下
                            // 示例1：
                            // e.hash=10 0000 1010
                            // oldCap=16 0001 0000
                            //   &   =0  0000 0000       比较高位的第一位 0
                            //结论：元素位置在扩容后数组中的位置没有发生改变
                            // 示例2：
                            // e.hash=17 0001 0001
                            // oldCap=16 0001 0000
                            //   &   =1  0001 0000      比较高位的第一位   1
                            //结论：元素位置在扩容后数组中的位置发生了改变，新的下标位置是原下标位置+原数组长度
                            // (e.hash & (oldCap-1)) 得到的是下标位置,示例如下
                            //   e.hash=10 0000 1010
                            // oldCap-1=15 0000 1111
                            //      &  =10 0000 1010
                            //   e.hash=17 0001 0001
                            // oldCap-1=15 0000 1111
                            //      &  =1  0000 0001
                            //新下标位置
                            //   e.hash=17 0001 0001
                            // newCap-1=31 0001 1111    newCap=32
                            //      &  =17 0001 0001    1+oldCap = 1+16
                            //元素在重新计算hash之后，因为n变为2倍，那么n-1的mask范围在高位多1bit(红色)，因此新的index就会发生这样的变化：
                            // 0000 0001->0001 0001
                        
                        ///将链表元素拆分 
                        ///变量名意义： 低位low？ 高位high？
                        ///位置没变的为lo链表 
                        ///位置变化的为hi链表 
                        if ((e.hash & oldCap) == 0) {
                            // 如果原元素位置没有发生变化的成为一个链表
                            if (loTail == null)
                                loHead = e;
                            else
                                loTail.next = e;
                            loTail = e;
                        }
                        else {
                            if (hiTail == null)
                                hiHead = e;
                            else
                                hiTail.next = e;
                            hiTail = e;
                        }
                    } while ((e = next) != null);                  
                    ///元素索引未改变的低位链表，赋值到当前索引
                    if (loTail != null) {
                        loTail.next = null;
                        newTab[j] = loHead;
                    }
                    ///元素索引改变的高位链表，新的索引为j+oldCap
                    if (hiTail != null) {
                        hiTail.next = null;
                        newTab[j + oldCap] = hiHead;
                    }
                  //总结：1.8中 旧链表迁移新链表  尾插法   链表元素相对位置没有变化; 实际是对对象的内存地址进行操作 
				//在1.7中  旧链表迁移新链表     头插法   如果在新表的数组索引位置相同，则链表元素会倒置
                }
            }
        }
    }
    return newTab;
}
```