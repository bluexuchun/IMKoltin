package widiazine.bluexuchun.im.data.db

/**
 * 定义数据库的表 即本地存储
 * 1、定义了ContactTable的类
 * 2、创建了ContactTable的一个对象实例，通过类名，直接访问实例，实现单例的一种方式
 *
 */
object ContactTable{
    val NAME = "contact"

    //定义字段
    val ID = "_id"
    var CONTACT = "name"
}