package com.atguigu.flink

import org.apache.flink.api.common.state.StateTtlConfig.TtlTimeCharacteristic
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.scala._

object HotItems {

  def main(args: Array[String]): Unit = {
    //创建一个StreamExecutionEnvironment
    val environment: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    //1.12版本开始默认时间语义就是eventtime
    environment.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
    environment.setParallelism(1)

    environment.readTextFile("")
      .map(
        line =>{
          val lineArray = line.split(",")
          UserBehavior(lineArray(0).toLong,lineArray(1).toLong,lineArray(2).toInt,lineArray(3),lineArray(4).toInt)
        })
      .assignAscendingTimestamps(_.timeStamp * 1000)



  }

}

case class UserBehavior( userId : Long, itemId : Long, CategoryId : Int, behavior : String, timeStamp : Long)

case class ItemViewCount(itemId: Long, windowEnd: Long, count: Long)