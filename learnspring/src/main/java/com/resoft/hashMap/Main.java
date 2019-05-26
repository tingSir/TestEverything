package com.resoft.hashMap;

public class Main {

	public static void main(String[] args) {

		String key="xixi";
		String value="longting";
		MyhashMap<String, String> hashMap = new MyhashMap<String, String>();
		for (int i = 0; i < 1000; i++) {
			hashMap.put(key+i, value+i);
		}
		for (int i = 0; i < 1000; i++) {
			System.out.println(key+i+":"+hashMap.get(key+i));
		}
	}

	/**
	 * Initializes or doubles table size.  If null, allocates in
	 * accord with initial capacity target held in field threshold.
	 * Otherwise, because we are using power-of-two expansion, the
	 * elements from each bin must either stay at same index, or move
	 * with a power of two offset in the new table.
	 *  @return the table
	 *
	 *
	 *
	 * 初始化或者翻倍表大小。
	 * 如果表为null，则根据存放在threshold变量中的初始化capacity的值来分配table内存
	 * （这个注释说的很清楚，在实例化HashMap时，capacity其实是存放在了成员变量threshold中，
	 * 注意，HashMap中没有capacity这个成员变量）
	 * 。如果表不为null，由于我们使用2的幂来扩容，
	 * 则每个bin元素要么还是在原来的bucket中，要么在2的幂中
	 *
	 * 此方法功能：初始化或扩容
	 */
/*	final Node<K,V>[] resize() {
		Node<K,V>[] oldTab = table;
		int oldCap = (oldTab == null) ? 0 : oldTab.length;
		int oldThr = threshold;
		//新的容量值，新的扩容阀界值
		int newCap, newThr = 0;
		//oldTab!=null,则oldCap>0
		if (oldCap > 0) {
			//如果此时oldCap>=MAXIMUM_CAPACITY(1 << 30)，表示已经到了最大容量，这时还要往map中放数据，则阈值设置为整数的最大值 Integer.MAX_VALUE，直接返回这个oldTab的内存地址。
			if (oldCap >= MAXIMUM_CAPACITY) {
				threshold = Integer.MAX_VALUE;
				return oldTab;
			}
			//如果(当前容量*2<最大容量&&当前容量>=默认初始化容量（16）)
			//并将将原容量值<<1(相当于*2)赋值给 newCap
			else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
					oldCap >= DEFAULT_INITIAL_CAPACITY)
				//如果能进来证明此map是扩容而不是初始化
				//操作：将原扩容阀界值<<1(相当于*2)赋值给 newThr
				newThr = oldThr << 1; // double threshold
		}
		else if (oldThr > 0) // initial capacity was placed in threshold
			//进入此if证明创建map时用的带参构造：public HashMap(int initialCapacity)或 public HashMap(int initialCapacity, float loadFactor)
			//注：带参的构造中initialCapacity（初始容量值）不管是输入几都会通过 “this.threshold = tableSizeFor(initialCapacity);”此方法计算出接近initialCapacity参数的2^n来作为初始化容量（初始化容量==oldThr）
			newCap = oldThr;
		else {               // zero initial threshold signifies using defaults
			//进入此if证明创建map时用的无参构造：
			//然后将参数newCap（新的容量）、newThr(新的扩容阀界值)进行初始化
			newCap = DEFAULT_INITIAL_CAPACITY;
			newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
		}
		if (newThr == 0) {

			//进入此if有两种可能
			// 第一种：进入此“if (oldCap > 0)”中且不满足该if中的两个if
			// 第二种：进入这个“else if (oldThr > 0)”

			//分析：进入此if证明该map在创建时用的带参构造，如果是第一种情况就说明是进行扩容且oldCap（旧容量）小于16，如果是第二种说明是第一次put
			float ft = (float)newCap * loadFactor;
			//计算扩容阀界值
			newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
					(int)ft : Integer.MAX_VALUE);
		}
		threshold = newThr;
		@SuppressWarnings({"rawtypes","unchecked"})
		Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
		table = newTab;
		//如果“oldTab != null”说明是扩容，否则直接返回newTab
		if (oldTab != null) {
			for (int j = 0; j < oldCap; ++j) {
				Node<K,V> e;
				if ((e = oldTab[j]) != null) {
					oldTab[j] = null;

					if (e.next == null)
						newTab[e.hash & (newCap - 1)] = e;
					else if (e instanceof TreeNode)
						//如果该元素是TreeNode的实例
						((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
					else { // preserve order
						Node<K,V> loHead = null, loTail = null;//此对象接收会放在原来位置
						Node<K,V> hiHead = null, hiTail = null;//此对象接收会放在“j + oldCap”（当前位置索引+原容量的值）
						Node<K,V> next;
						do {
							next = e.next;
							//以下是扩容操作的核心，详情见我的博客：https://www.cnblogs.com/shianliang/p/9204942.html
							if ((e.hash & oldCap) == 0) {

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
						if (loTail != null) {
							loTail.next = null;
							newTab[j] = loHead;
						}
						if (hiTail != null) {
							hiTail.next = null;
							newTab[j + oldCap] = hiHead;
						}
					}
				}
			}
		}
		return newTab;
	}*/

}
