/**
 * @author zyc
 * @date 2020/12/20
 * @time 21:09
 * @description : 这里做几个实力化的需求
 * 1 string
 *      字符串 （二进制文件 对象 session）
 *      int  秒杀，库存，限流
 *      二进制 bitmap 统计设计到二进制的操作 与或非
 *
 *
 * 2 list
 *      双端 队列 比如无状态的替换我们jvm里面用的队列
 *
 * 3 hash
 *      这里就是去聚合一些场景， 比如商品详情， 购物车详情
 * 4 set
 *    集合的无序去重还有所具有的随机，交集并集差集
 *
 * 5 zset
 *      这个我们相当于半自动的有序，通过我们设计对分值的操作，来排列成一定的顺序
 *
 */
package com.luping.redis;