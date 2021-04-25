package com.imooc.ad.handler;

import com.alibaba.fastjson.JSON;
import com.imooc.ad.Index.DataTable;
import com.imooc.ad.Index.IndexAware;
import com.imooc.ad.Index.adcreative.AdCreativeIndex;
import com.imooc.ad.Index.adcreative.AdCreativeObject;
import com.imooc.ad.Index.adplan.AdPlanIndex;
import com.imooc.ad.Index.adplan.AdPlanObject;
import com.imooc.ad.Index.adunit.AdUnitIndex;
import com.imooc.ad.Index.adunit.AdUnitObject;
import com.imooc.ad.Index.creativeunit.CreativeUnitIndex;
import com.imooc.ad.Index.creativeunit.CreativeUnitObject;
import com.imooc.ad.common.table.AdCreativeTable;
import com.imooc.ad.common.table.AdPlanTable;
import com.imooc.ad.common.table.AdUnitTable;
import com.imooc.ad.common.table.CreativeUnitTable;
import com.imooc.ad.mysql.constant.OpType;
import com.imooc.ad.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author spin
 * @date 2021/4/2322:26
 * @description: TODO 第二层级的索引操作 即广告计划 广告创意 不依赖于其他部分
 *  1. 索引之间存在着层级的划分，也就是依赖关系的划分
 *  2. 加载全量索引其实是增量索引 “增加” 的一种特殊实现
 *
 */
@Slf4j
public class AdLevelDataHandler {

    private static <K,V> void handleBinlogEvent(IndexAware<K,V> index, K key, V value, OpType type) {
        switch (type) {
            case ADD:
                index.add(key,value);
                break;
            case UPDATE:
                index.update(key,value);
                break;
            case DELETE:
                index.delete(key,value);
                break;
            case OTHER:
                break;
        }
    }

    /**
    * @description: TODO 通过索引表数据构建索引对象，调用之前定义的索引操作
     * 广告计划
    * @author yusp
    * @date 2021/4/24 15:29
    */
    public static void handleLevel2(AdPlanTable planTable, OpType type) {
        AdPlanObject planObject = new AdPlanObject(
                planTable.getPlanId(),
                planTable.getUserId(),
                planTable.getPlanStatus(),
                planTable.getStartDate(),
                planTable.getEndDate()
        );
        handleBinlogEvent(
                DataTable.of(AdPlanIndex.class),
                planObject.getPlanId(),
                planObject,
                type
                );
    }

    public static void handleLevel2(AdCreativeTable adCreativeTable, OpType type) {
        AdCreativeObject adCreativeObject = new AdCreativeObject(
                adCreativeTable.getAdId(),
                adCreativeTable.getName(),
                adCreativeTable.getType(),
                adCreativeTable.getMaterialType(),
                adCreativeTable.getHeight(),
                adCreativeTable.getWidth(),
                adCreativeTable.getAuditStatus(),
                adCreativeTable.getAdUrl()
        );

        handleBinlogEvent(
                DataTable.of(AdCreativeIndex.class),
                adCreativeObject.getAdId(),
                adCreativeObject,
                type
        );
    }

    /**
    * @description: TODO
     * 由于广告单元关联于广告计划，广告单元创意中间表关联于广告创意，
     * 所以将其归类为第三层级。相比于第二层级的对象，
     * 不仅需要判断本身对应的索引对象是否为空，还得判断关联的索引对象是否为空
    * @author yusp
    * @date 2021/4/24 15:59
    */
    public static void handleLevel3(AdUnitTable adUnitTable, OpType type) {
        //关联计划的判断
        AdPlanObject adPlanObject = DataTable.of(AdPlanIndex.class).get(adUnitTable.getPlanId());
        if(null == adPlanObject) {
            log.error("handleLevel3 found AdPlanObject error:{}",adUnitTable.getPlanId());
            return;
        }
        AdUnitObject adUnitObject = new AdUnitObject(
                adUnitTable.getUnitId(),
                adUnitTable.getUnitStatus(),
                adUnitTable.getPositionType(),
                adUnitTable.getPlanId(),
                adPlanObject
        );
        handleBinlogEvent(
                DataTable.of(AdUnitIndex.class),
                adUnitObject.getUnitId(),
                adUnitObject,
                type
        );
    }

    public static void handleLevel3(CreativeUnitTable creativeUnitTable, OpType type) {
        if(type == OpType.UPDATE) {
            log.error("CreativeUnitIndex not support update");
            return;
        }
        AdUnitObject adUnitObject = DataTable.of(AdUnitIndex.class).get(creativeUnitTable.getUnitId());
        AdCreativeObject creativeObject = DataTable.of(AdCreativeIndex.class).get(creativeUnitTable.getAdId());
        if(adUnitObject == null || creativeObject == null) {
            log.error("AdCreativeUnitTable index error:{}", JSON.toJSONString(creativeUnitTable));
            return;
        }
        CreativeUnitObject creativeUnitObject = new CreativeUnitObject(
                creativeUnitTable.getAdId(),
                creativeUnitTable.getUnitId()
        );
        handleBinlogEvent(
                DataTable.of(CreativeUnitIndex.class),
                CommonUtils.stringConcat(
                        creativeUnitObject.getAdId().toString(),
                        creativeUnitObject.getUnitId().toString()
                ),
                creativeUnitObject,
                type
        );
    }

}
