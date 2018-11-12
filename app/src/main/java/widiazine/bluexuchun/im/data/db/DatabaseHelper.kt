package widiazine.bluexuchun.im.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*
import widiazine.bluexuchun.im.app.IMApplication

class DatabaseHelper(ctx: Context = IMApplication.instance) :
    ManagedSQLiteOpenHelper(ctx, NAME, null,VERSION){
    /**
     * 数据库的创建
     */
    override fun onCreate(db: SQLiteDatabase?) {
        /**
         * 创建数据表
         */
        db?.createTable(ContactTable.NAME,true,
            ContactTable.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            ContactTable.CONTACT to TEXT)
    }

    /**
     * 数据库的更新
     */
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        /**
         * 干掉数据表
         */
        db?.dropTable(ContactTable.NAME,true)
        onCreate(db)
    }

    companion object {
        val NAME = "im.db"
        val VERSION = 1
    }
}