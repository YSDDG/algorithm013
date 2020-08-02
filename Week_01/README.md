学习笔记

# Java Queue 源码解析

## 概述

```java
/**
 * A collection designed for holding elements prior to processing.
 * Besides basic {@link java.util.Collection Collection} operations,
 * queues provide additional insertion, extraction, and inspection
 * operations.  Each of these methods exists in two forms: one throws
 * an exception if the operation fails, the other returns a special
 * value (either {@code null} or {@code false}, depending on the
 * operation).  The latter form of the insert operation is designed
 * specifically for use with capacity-restricted {@code Queue}
 * implementations; in most implementations, insert operations cannot
 * fail.
 *
 * <table BORDER CELLPADDING=3 CELLSPACING=1>
 * <caption>Summary of Queue methods</caption>
 *  <tr>
 *    <td></td>
 *    <td ALIGN=CENTER><em>Throws exception</em></td>
 *    <td ALIGN=CENTER><em>Returns special value</em></td>
 *  </tr>
 *  <tr>
 *    <td><b>Insert</b></td>
 *    <td>{@link Queue#add add(e)}</td>
 *    <td>{@link Queue#offer offer(e)}</td>
 *  </tr>
 *  <tr>
 *    <td><b>Remove</b></td>
 *    <td>{@link Queue#remove remove()}</td>
 *    <td>{@link Queue#poll poll()}</td>
 *  </tr>
 *  <tr>
 *    <td><b>Examine</b></td>
 *    <td>{@link Queue#element element()}</td>
 *    <td>{@link Queue#peek peek()}</td>
 *  </tr>
 * </table>
 *
 /// 典型的队列的操作是先进先出的,但是这个不是必须的。
 ///优先级队列就是根据元素的优先级来出队的
 
 * <p>Queues typically, but do not necessarily, order elements in a
 * FIFO (first-in-first-out) manner.  Among the exceptions are
 * priority queues, which order elements according to a supplied
 * comparator, or the elements' natural ordering, and LIFO queues (or
 * stacks) which order the elements LIFO (last-in-first-out).
 * Whatever the ordering used, the <em>head</em> of the queue is that
 * element which would be removed by a call to {@link #remove() } or
 * {@link #poll()}.  In a FIFO queue, all new elements are inserted at
 * the <em>tail</em> of the queue. Other kinds of queues may use
 * different placement rules.  Every {@code Queue} implementation
 * must specify its ordering properties.
 *
 ///offer操作，不会返回异常，区别于add
 * <p>The {@link #offer offer} method inserts an element if possible,
 * otherwise returning {@code false}.  This differs from the {@link
 * java.util.Collection#add Collection.add} method, which can fail to
 * add an element only by throwing an unchecked exception.  The
 * {@code offer} method is designed for use when failure is a normal,
 * rather than exceptional occurrence, for example, in fixed-capacity
 * (or &quot;bounded&quot;) queues.
 *
 ///poll和remove操作都移除掉队首元素
 ///队列为空时poll操作返回null，remove操作返回异常
 * <p>The {@link #remove()} and {@link #poll()} methods remove and
 * return the head of the queue.
 * Exactly which element is removed from the queue is a
 * function of the queue's ordering policy, which differs from
 * implementation to implementation. The {@code remove()} and
 * {@code poll()} methods differ only in their behavior when the
 * queue is empty: the {@code remove()} method throws an exception,
 * while the {@code poll()} method returns {@code null}.
 
 ///peek和element都返回队首元素，但不会有出队动作
 * <p>The {@link #element()} and {@link #peek()} methods return, but do
 * not remove, the head of the queue.
 *
 ///非线程安全
 * <p>The {@code Queue} interface does not define the <i>blocking queue
 * methods</i>, which are common in concurrent programming.  These methods,
 * which wait for elements to appear or for space to become available, are
 * defined in the {@link java.util.concurrent.BlockingQueue} interface, which
 * extends this interface.
 
 ///入队元素不能为空
 * <p>{@code Queue} implementations generally do not allow insertion
 * of {@code null} elements, although some implementations, such as
 * {@link LinkedList}, do not prohibit insertion of {@code null}.
 * Even in the implementations that permit it, {@code null} should
 * not be inserted into a {@code Queue}, as {@code null} is also
 * used as a special return value by the {@code poll} method to
 * indicate that the queue contains no elements.
 *
 * <p>{@code Queue} implementations generally do not define
 * element-based versions of methods {@code equals} and
 * {@code hashCode} but instead inherit the identity based versions
 * from class {@code Object}, because element-based equality is not
 * always well-defined for queues with the same elements but different
 * ordering properties.
 *
 *
 * <p>This interface is a member of the
 * <a href="{@docRoot}/../technotes/guides/collections/index.html">
 * Java Collections Framework</a>.
 *
 * @see java.util.Collection
 * @see LinkedList
 * @see PriorityQueue
 * @see java.util.concurrent.LinkedBlockingQueue
 * @see java.util.concurrent.BlockingQueue
 * @see java.util.concurrent.ArrayBlockingQueue
 * @see java.util.concurrent.LinkedBlockingQueue
 * @see java.util.concurrent.PriorityBlockingQueue
 * @since 1.5
 * @author Doug Lea
 * @param <E> the type of elements held in this collection
 */
public interface Queue<E> extends Collection<E> {
```

## API

### add：入队，失败时返回异常

```java
/**
 * Inserts the specified element into this queue if it is possible to do so
 * immediately without violating capacity restrictions, returning
 * {@code true} upon success and throwing an {@code IllegalStateException}
 * if no space is currently available.
 *
 * @param e the element to add
 * @return {@code true} (as specified by {@link Collection#add})
 * @throws IllegalStateException if the element cannot be added at this
 *         time due to capacity restrictions
 * @throws ClassCastException if the class of the specified element
 *         prevents it from being added to this queue
 * @throws NullPointerException if the specified element is null and
 *         this queue does not permit null elements
 * @throws IllegalArgumentException if some property of this element
 *         prevents it from being added to this queue
 */
boolean add(E e);
```

### offer:入队，失败时返回false

```java
/**
 * Inserts the specified element into this queue if it is possible to do
 * so immediately without violating capacity restrictions.
 * When using a capacity-restricted queue, this method is generally
 * preferable to {@link #add}, which can fail to insert an element only
 * by throwing an exception.
 *
 * @param e the element to add
 * @return {@code true} if the element was added to this queue, else
 *         {@code false}
 * @throws ClassCastException if the class of the specified element
 *         prevents it from being added to this queue
 * @throws NullPointerException if the specified element is null and
 *         this queue does not permit null elements
 * @throws IllegalArgumentException if some property of this element
 *         prevents it from being added to this queue
 */
boolean offer(E e);
```

### remove：出队，失败时返回异常

```java
/**
 * Retrieves and removes the head of this queue.  This method differs
 * from {@link #poll poll} only in that it throws an exception if this
 * queue is empty.
 *
 * @return the head of this queue
 * @throws NoSuchElementException if this queue is empty
 */
E remove();
```

### poll：出队，失败时返回null

```java
/**
 * Retrieves and removes the head of this queue,
 * or returns {@code null} if this queue is empty.
 *
 * @return the head of this queue, or {@code null} if this queue is empty
 */
E poll();
```

### element：获取队首元素，队列为空时返回异常

```java
/**
 * Retrieves, but does not remove, the head of this queue.  This method
 * differs from {@link #peek peek} only in that it throws an exception
 * if this queue is empty.
 *
 * @return the head of this queue
 * @throws NoSuchElementException if this queue is empty
 */
E element();
```

### peek：获取队手元素，队列为空时返回null

```java
/**
 * Retrieves, but does not remove, the head of this queue,
 * or returns {@code null} if this queue is empty.
 *
 * @return the head of this queue, or {@code null} if this queue is empty
 */
E peek();
```

# Java PriorityQueue 源码解析

## 概述

```java
/**
/// 基于堆实现的
/// 因为元素实现了Comparable接口，所以que中的元素不能为null且必须实现Comparable接口

 * An unbounded priority {@linkplain Queue queue} based on a priority heap.
 * The elements of the priority queue are ordered according to their
 * {@linkplain Comparable natural ordering}, or by a {@link Comparator}
 * provided at queue construction time, depending on which constructor is
 * used.  A priority queue does not permit {@code null} elements.
 * A priority queue relying on natural ordering also does not permit
 * insertion of non-comparable objects (doing so may result in
 * {@code ClassCastException}).

 
 ///	小顶堆（根据其排序依据）
 ///poll remove peek element 访问的都是队列的head
 	
 * <p>The <em>head</em> of this queue is the <em>least</em> element
 * with respect to the specified ordering.  If multiple elements are
 * tied for least value, the head is one of those elements -- ties are
 * broken arbitrarily.  The queue retrieval operations {@code poll},
 * {@code remove}, {@code peek}, and {@code element} access the
 * element at the head of the queue.
 *
 /// priority queue 是无界的，但是内部维护了一个capacity来管理数组的大小，可以实现动态扩容
 
 * <p>A priority queue is unbounded, but has an internal
 * <i>capacity</i> governing the size of an array used to store the
 * elements on the queue.  It is always at least as large as the queue
 * size.  As elements are added to a priority queue, its capacity
 * grows automatically.  The details of the growth policy are not
 * specified.
 *
 
 /// 迭代顺序是内部数组的特定顺序
 /// 若想有序代，需要对数组进行排序
 
 * <p>This class and its iterator implement all of the
 * <em>optional</em> methods of the {@link Collection} and {@link
 * Iterator} interfaces.  The Iterator provided in method {@link
 * #iterator()} is <em>not</em> guaranteed to traverse the elements of
 * the priority queue in any particular order. If you need ordered
 * traversal, consider using {@code Arrays.sort(pq.toArray())}.
 *
 /// 是一个非线程安全的类
 /// PriorityBlockingQueue是其线程安全的实现类
 
 * <p><strong>Note that this implementation is not synchronized.</strong>
 * Multiple threads should not access a {@code PriorityQueue}
 * instance concurrently if any of the threads modifies the queue.
 * Instead, use the thread-safe {@link
 * java.util.concurrent.PriorityBlockingQueue} class.
 *
 /// 入队和出队的时间复杂度是O(n)，因其入队时需要维护自身的优先特性
 /// peek element size方法的时间复杂度为O（1）
 
 * <p>Implementation note: this implementation provides
 * O(log(n)) time for the enqueuing and dequeuing methods
 * ({@code offer}, {@code poll}, {@code remove()} and {@code add});
 * linear time for the {@code remove(Object)} and {@code contains(Object)}
 * methods; and constant time for the retrieval methods
 * ({@code peek}, {@code element}, and {@code size}).
 *
 
 ///这个类是Java集合框架的一部分
 * <p>This class is a member of the
 * <a href="{@docRoot}/../technotes/guides/collections/index.html">
 * Java Collections Framework</a>.
 *
 * @since 1.5
 * @author Josh Bloch, Doug Lea
 * @param <E> the type of elements held in this collection
 */
public class PriorityQueue<E> extends AbstractQueue<E>
```

## 重要属性

### queue: 基于堆实现,使用数组存储 ,索引为n的元素左右子节点索引分别为2n+1和2*(n+1)

### size:队列中元素的数量

### comparator:元素比较器

### modCount：当前队列结构被修改的次数总和

```java
/**
 * Priority queue represented as a balanced binary heap: the two
 * children of queue[n] are queue[2*n+1] and queue[2*(n+1)].  The
 * priority queue is ordered by comparator, or by the elements'
 * natural ordering, if comparator is null: For each node n in the
 * heap and each descendant d of n, n <= d.  The element with the
 * lowest value is in queue[0], assuming the queue is nonempty.
 */
transient Object[] queue; // non-private to simplify nested class access

/**
 * The number of elements in the priority queue.
 */
private int size = 0;

/**
 * The comparator, or null if priority queue uses elements'
 * natural ordering.
 */
private final Comparator<? super E> comparator;

/**
 * The number of times this priority queue has been
 * <i>structurally modified</i>.  See AbstractList for gory details.
 */
transient int modCount = 0; // non-private to simplify nested class access
```

## API

### heapify:将一个树堆化

```java
/**
 * Establishes the heap invariant (described above) in the entire tree,
 * assuming nothing about the order of the elements prior to the call.
 */
@SuppressWarnings("unchecked")
private void heapify() {
    //从数组的中间元素遍历到数组开始  
    for (int i = (size >>> 1) - 1; i >= 0; i--)
        siftDown(i, (E) queue[i]);
}
```

### 向上调整，让调整的节点与其父节点比较

#### siftUpComparable：使用元素的自带的比较器比较

#### siftUpUsingComparator：使用队列指定的比较器比较

```java
/**
 * Inserts item x at position k, maintaining heap invariant by
 * promoting x up the tree until it is greater than or equal to
 * its parent, or is the root.
 *
 * To simplify and speed up coercions and comparisons. the
 * Comparable and Comparator versions are separated into different
 * methods that are otherwise identical. (Similarly for siftDown.)
 *
 * @param k the position to fill
 * @param x the item to insert
 */
private void siftUp(int k, E x) {
    if (comparator != null)
        siftUpUsingComparator(k, x);
    else
        siftUpComparable(k, x);
}
@SuppressWarnings("unchecked")
private void siftUpComparable(int k, E x) {//k,
    Comparable<? super E> key = (Comparable<? super E>) x;
    //向上调整到下标为0的位置就不调整了
    while (k > 0) {
        //父节点为（k-1）/2
        int parent = (k - 1) >>> 1;
        Object e = queue[parent];
        //与父亲结点相比较
        //如果父亲节点的值小，就交换位置，否则就不调整
        if (key.compareTo((E) e) >= 0)
            break;
        queue[k] = e;
        k = parent;
    }
    //将x放入找到的索引位置，完成遍历
    queue[k] = key;
}
@SuppressWarnings("unchecked")
private void siftUpUsingComparator(int k, E x) {
    while (k > 0) {
        int parent = (k - 1) >>> 1;
        Object e = queue[parent];
        if (comparator.compare(x, (E) e) >= 0)
            break;
        queue[k] = e;
        k = parent;
    }
    queue[k] = x;
}
```

### 向下调整：让调整的节点与其孩子节点进行比较

#### siftDownComparable: 使用元素的自带的比较器比较

#### siftDownUsingComparator:使用队列指定的比较器比较

```java
/**
 * Inserts item x at position k, maintaining heap invariant by
 * demoting x down the tree repeatedly until it is less than or
 * equal to its children or is a leaf.
 *
 * @param k the position to fill
 * @param x the item to insert
 */
private void siftDown(int k, E x) {
    if (comparator != null)
        siftDownUsingComparator(k, x);
    else
        siftDownComparable(k, x);
}

@SuppressWarnings("unchecked")
private void siftDownComparable(int k, E x) {
    Comparable<? super E> key = (Comparable<? super E>)x;
    int half = size >>> 1;        // loop while a non-leaf
    while (k < half) {
        //左子节点的索引为2k+1
        int child = (k << 1) + 1; // assume left child is least
        Object c = queue[child];
		//右子节点索引为2*(k+1)
        int right = child + 1;
        //选取左右子节点较小的一个
        if (right < size &&
            ((Comparable<? super E>) c).compareTo((E) queue[right]) > 0)
            c = queue[child = right];
        //比较该根结点与最小的孩子结点的大小，如果孩子结点小，则交换位置后继续堆化
        if (key.compareTo((E) c) <= 0)
            break;
        queue[k] = c;
        k = child;
    }
    queue[k] = key;
}

@SuppressWarnings("unchecked")
private void siftDownUsingComparator(int k, E x) {
    int half = size >>> 1;
    while (k < half) {
        int child = (k << 1) + 1;
        Object c = queue[child];
        int right = child + 1;
        if (right < size &&
            comparator.compare((E) c, (E) queue[right]) > 0)
            c = queue[child = right];
        if (comparator.compare(x, (E) c) <= 0)
            break;
        queue[k] = c;
        k = child;
    }
    queue[k] = x;
}
```

### add,offer：入队

```java
public boolean add(E e) {
    return offer(e);
}
    /**
     * Inserts the specified element into this priority queue.
     *
     * @return {@code true} (as specified by {@link Queue#offer})
     * @throws ClassCastException if the specified element cannot be
     *         compared with elements currently in this priority queue
     *         according to the priority queue's ordering
     * @throws NullPointerException if the specified element is null
     */
    public boolean offer(E e) {
        if (e == null)
            throw new NullPointerException();
        modCount++;
        int i = size;
        if (i >= queue.length)
            grow(i + 1);
        size = i + 1;
        if (i == 0)
            queue[0] = e;
        else
            siftUp(i, e);
        return true;
    }
```

### clear：清空队列

```java
/**
 * Removes all of the elements from this priority queue.
 * The queue will be empty after this call returns.
 */
public void clear() {
    modCount++;
    for (int i = 0; i < size; i++)
        queue[i] = null;
    size = 0;
}
```

### peek：返回（窥探）头元素，不对队列造成影响

```java
public E peek() {
    return (size == 0) ? null : (E) queue[0];
}
```

### poll：头元素出队并返回

```java
@SuppressWarnings("unchecked")
public E poll() {
    if (size == 0)
        return null;
    int s = --size;
    modCount++;
    E result = (E) queue[0];
    E x = (E) queue[s];
    queue[s] = null;
    if (s != 0)
        siftDown(0, x);
    return result;
}
```

### remove

#### removeAt：删除指定索引的元素，并且不破坏队列的优先性

#### removeEq: 删除与指定对象相等的元素，并且不破坏队列的优先性

```java
/**
 * Removes the ith element from queue.
 *
 * Normally this method leaves the elements at up to i-1,
 * inclusive, untouched.  Under these circumstances, it returns
 * null.  Occasionally, in order to maintain the heap invariant,
 * it must swap a later element of the list with one earlier than
 * i.  Under these circumstances, this method returns the element
 * that was previously at the end of the list and is now at some
 * position before i. This fact is used by iterator.remove so as to
 * avoid missing traversing elements.
 */
@SuppressWarnings("unchecked")
private E removeAt(int i) {
    // assert i >= 0 && i < size;
    modCount++;
    int s = --size;
    if (s == i) // removed last element
        queue[i] = null;
    else {
        E moved = (E) queue[s];
        queue[s] = null;
        siftDown(i, moved);
        if (queue[i] == moved) {
            siftUp(i, moved);
            if (queue[i] != moved)
                return moved;
        }
    }
    return null;
}
    /**
     * Version of remove using reference equality, not equals.
     * Needed by iterator.remove.
     *
     * @param o element to be removed from this queue, if present
     * @return {@code true} if removed
     */
    boolean removeEq(Object o) {
        for (int i = 0; i < size; i++) {
            if (o == queue[i]) {
                removeAt(i);
                return true;
            }
        }
        return false;
    }
```