队列接口分为阻塞和不阻塞接口

Queue 普通队列

    add(E): boolean 插入成功返回true，否则抛出异常
    element(): E
    offer(E): boolean 插入成功返回true，否则false
    peek(): E
    poll(): E
    remove(): E

BlockingDeque 阻塞队列

    add(E): boolean
    addFirst(E): void
    addLast(E): void
    contains(Object): boolean
    element(): E
    iterator(): Iterator<E>
    offer(E): boolean
    offer(E, long, TimeUnit): boolean
    offerFirst(E): boolean
    offerFirst(E, long, TimeUnit): boolean
    offerLast(E): boolean
    offerLast(E, long, TimeUnit): boolean
    peek(): E
    poll(): E
    poll(long, TimeUnit): E
    pollFirst(long, TimeUnit): E
    pollLast(long, TimeUnit): E
    push(E): void
    put(E): void 
    putFirst(E): void
    putLast(E): void
    remove(): E
    remove(Object): boolean
    removeFirstOccurrence(Object): boolean
    removeLastOccurrence(Object): boolean
    size(): int
    take(): E
    takeFirst(): E
    takeLast(): E


Deque 双端队列

    add(E): boolean
    addFirst(E): void
    addLast(E): void
    contains(Object): boolean
    descendingIterator(): Iterator<E>
    element(): E
    getFirst(): E
    getLast(): E
    iterator(): Iterator<E>
    offer(E): boolean
    offerFirst(E): boolean
    offerLast(E): boolean
    peek(): E
    peekFirst(): E
    peekLast(): E
    poll(): E
    pollFirst(): E
    pollLast(): E
    pop(): E
    push(E): void
    remove(): E
    remove(Object): boolean
    removeFirst(): E
    removeFirstOccurrence(Object): boolean
    removeLast(): E
    removeLastOccurrence(Object): boolean
    size(): int


主要实现队列

ConcurrentLinkedQueue
    
