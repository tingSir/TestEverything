package com.resoft.hashMap;

/**
 * 手写hashmap,
 *   1）、什么是hash？
             它是将一个任意长度的二进制值通过一个映射关系转换成一个固定长度的二进制值。
             固定长度的二进制值就相当于一个关键字，是对任意长度的二进制值的摘要----key
            真正有效的数据是任意长度的二进制----value

   2）、hash的作用：一个key和一个value的唯一映射关系。

   3）、实现用的是除留余数法，公式如下： int index=key%m;
         用的链表形式解决存储冲突问题
 */
public class MyhashMap<K,V> {
	private static Integer defaultLength = 16;//定义数组长度（定义成2的倍数）
    private static double defaultLoad=0.75;//定义负载因子(超过这个因子就会扩容)
    private Entry<K,V>[] table =null;//定一个数组，盛放Entry对象
    private int size=0;//定义一个常量，用来记录数组元素个数
    //定义构造函数,
    MyhashMap(int defaultLength,double defaultLoad){
        this.defaultLength=defaultLength;
        this.defaultLoad=defaultLoad;

        table=new Entry[defaultLength];//定义一个默认数组，长度就是传过来的长度
    }
    MyhashMap(){
        this(defaultLength,defaultLoad);
    }
    public V put(K key, V value){
    	int index = this.getIndex(key);
    	if(table[index]==null){
    		table[index]=new Entry<K, V>(key, value, null);
    	}else{
    		table[index]=new Entry<K, V>(key, value, table[index]);
    	}
    	return value;
    }

    public V get(K key){
    	int index = this.getIndex(key);
    	if(table[index]==null){
    		return null;
    	}else if(table[index].getNext()==null){
    		return table[index].value;
    	}else{
    		return getFromLinked(table[index], key);
    	}
    }
    public V getFromLinked(Entry<K,V> ent,K key){
    	if(ent.getKey().equals(key)){
    		return ent.value;
    	}else if(ent.next !=null){
    		return getFromLinked(ent.next, key);
    	}else{
    		return null;
    	}
    }


    private int getIndex(K key){
        //除留取余数法
        //m的取值是比数组长度小的质数的最大值
        int m=this.defaultLength-1;
        return key.hashCode() & m;

    }
    class Entry<K,V> implements java.util.Map.Entry<K, V>{
        K key;
        V value;
        Entry<K,V> next;
        Entry(K k,V v,Entry<K,V> n){
            key=k;
            value=v;
             next=n;
        }

        public K getKey(){
            return key;
        }

        public V getValue(){
            return value;
        }

		public Entry<K, V> getNext() {
			return next;
		}

		public void setNext(Entry<K, V> next) {
			this.next = next;
		}

		public V setValue(V value) {
			this.value=value;
			return value;
		}
    }

}




