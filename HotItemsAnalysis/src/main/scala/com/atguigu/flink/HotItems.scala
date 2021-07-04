package com.atguigu.flink

object HotItems {





}

case class UserBehavior( userId : Long, itemId : Long, CategoryId : Int, behavior : String, timeStamp : Long)

case class ItemViewCount(itemId: Long, windowEnd: Long, count: Long)