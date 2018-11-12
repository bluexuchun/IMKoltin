package widiazine.bluexuchun.im.data.db

import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import widiazine.bluexuchun.im.extentions.toVarargArray

class IMDatabase{
    companion object {
        val databaseHelper = DatabaseHelper()
        val instance = IMDatabase()
    }

    fun saveContact(contact:Contact){
        databaseHelper.use{
            // SQLiteDatabase的扩展方法
            /**
             *   *意思为把pari数组展开 变成可变的数组
             */
            insert(ContactTable.NAME,*contact.map.toVarargArray())
        }
    }

    /**
     * 查询所有用户
     */
    fun getAllContacts():List<Contact>{
        return databaseHelper.use{
            select(ContactTable.NAME).parseList(object :MapRowParser<Contact>{
                override fun parseRow(columns: Map<String, Any?>): Contact {
                     return Contact(columns.toMutableMap())
                }

            })
        }
    }

    /**
     * 删除所有用户
     */
    fun deleteAllContacts(){
        databaseHelper.use{
            delete(ContactTable.NAME,null,null)
        }
    }
}