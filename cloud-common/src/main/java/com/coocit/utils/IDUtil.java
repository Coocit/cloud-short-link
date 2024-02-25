package com.coocit.utils;

import org.apache.shardingsphere.core.strategy.keygen.SnowflakeShardingKeyGenerator;

/**
 * @author: Coocit
 * @date: 2024/2/25
 * @description:
 */
public class IDUtil {

    private static SnowflakeShardingKeyGenerator shardingKeyGenerator = new SnowflakeShardingKeyGenerator();

    /**
     * 雪花算法生成器
     * @return
     */
    public static   Comparable<?> geneSnowFlakeID(){

        return shardingKeyGenerator.generateKey();
    }

}
