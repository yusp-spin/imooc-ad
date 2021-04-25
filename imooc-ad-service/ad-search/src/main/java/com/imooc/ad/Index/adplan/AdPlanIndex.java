package com.imooc.ad.Index.adplan;

import com.imooc.ad.Index.IndexAware;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author spin
 * @date 2021/4/2213:17
 * @description: TODO 推广计划索引
 *  首先要定义一个 map 的全局变量，因为 map 就是典型的 key-value 结构，
 *  符合我们的需求。由于我们的服务会实现索引的更新，而对于索引的更新，
 *  我们需要保证的就是线程安全的，于是我们需要先静态的构造一个安全的 map 全局变量。
 */
@Slf4j
@Component
public class AdPlanIndex implements IndexAware<Long,AdPlanObject> {

    private static Map<Long,AdPlanObject> objectMap;
    static {
        objectMap = new ConcurrentHashMap<>();
    }
    @Override
    public AdPlanObject get(Long key) {
        return objectMap.get(key);
    }

    @Override
    public void add(Long key, AdPlanObject value) {
        log.info("before add:{}",objectMap);
        objectMap.put(key,value);
        log.info("after add: {}", objectMap);
    }

    @Override
    public void update(Long key, AdPlanObject value) {
        log.info("before update: {}",objectMap);
        AdPlanObject oldObject = objectMap.get(key);
        if(null == oldObject) {
            objectMap.put(key, value);
        }else {
            oldObject.update(value);
        }
        log.info("after update: {}",objectMap);
    }

    @Override
    public void delete(Long key, AdPlanObject value) {
        log.info("before delete: {}",objectMap);
        objectMap.remove(key);
        log.info("after delete: {}",objectMap);
    }
}
