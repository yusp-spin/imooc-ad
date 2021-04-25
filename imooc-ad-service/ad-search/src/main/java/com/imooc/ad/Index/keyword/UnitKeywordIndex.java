package com.imooc.ad.Index.keyword;

import com.imooc.ad.Index.IndexAware;
import com.imooc.ad.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * @author spin
 * @date 2021/4/2214:59
 * @description: TODO
 */
@Slf4j
@Component
public class UnitKeywordIndex implements IndexAware<String, Set<Long>> {

    //关键词到推广单元的映射
    private static Map<String, Set<Long>> keywordUnitMap;

    //推广单元 id 到关键词的映射，一个推广单元也可以设置很多个限制
    private static Map<Long,Set<String>> unitKeywordMap;

    static {
        keywordUnitMap = new ConcurrentHashMap<>();
        unitKeywordMap = new ConcurrentHashMap<>();
    }

    @Override
    public Set<Long> get(String key) {
        if(StringUtils.isEmpty(key)) {
            return Collections.emptySet();
        }
        Set<Long> result = keywordUnitMap.get(key);
        if(result == null) {
            return Collections.emptySet();
        }
        return result;
    }

    @Override
    public void add(String key, Set<Long> value) {
        log.info("UnitKeywordIndex,before add: {}",unitKeywordMap);
        Set<Long> unitIdSet = CommonUtils.getOrCreate(
                key,keywordUnitMap, ConcurrentSkipListSet::new
        );
        unitIdSet.addAll(value);
        for(Long unitId : value) {
            Set<String> keywordSet = CommonUtils.getOrCreate(
                    unitId, unitKeywordMap, ConcurrentSkipListSet::new
            );
            keywordSet.add(key);
        }
        log.info("UnitKeywordIndex,after add: {}",unitKeywordMap);
    }

    @Override
    public void update(String key, Set<Long> value) {
        log.info("keyword index can not support update");
    }

    @Override
    public void delete(String key, Set<Long> value) {
        log.info("UnitKeywordIndex,before delete: {}",unitKeywordMap);
        Set<Long> unitIds = CommonUtils.getOrCreate(
                key, keywordUnitMap, ConcurrentSkipListSet::new
        );
        unitIds.removeAll(unitIds);
        for(Long unitId : value) {
            Set<String> keywordSet = CommonUtils.getOrCreate(
                    unitId, unitKeywordMap, ConcurrentSkipListSet::new);
            keywordSet.remove(key);
        }
        log.info("UnitKeywordIndex,after delete: {}",unitKeywordMap);
    }

    /**
    * @description: TODO 匹配推广单元是否包含这些关键词
    * @author yusp
    * @date 2021/4/22 16:01
    */
    public boolean match (Long unitId, List<String> keywords) {
        if(unitKeywordMap.containsKey(unitId) && CollectionUtils.isNotEmpty(unitKeywordMap.get(unitId))) {
            Set<String> unitKeywords = unitKeywordMap.get(unitId);
            return CollectionUtils.isSubCollection(keywords,unitKeywords);
        }
        return false;
    }
}
