package com.imooc.ad.Index;

/**
 * @author spin
 * @date 2021/4/2211:20
 * @description: TODO 索引的增删改查方法
 */
public interface IndexAware<K, V> {
    V get(K key);

    void add(K key, V value);

    void update(K key, V value);

    void delete(K key, V value);
}
