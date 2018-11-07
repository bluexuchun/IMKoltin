package widiazine.bluexuchun.im.contract

import android.os.Handler
import android.os.Looper

/**
 * 线程的切换
 */
interface BasePresenter{
    companion object {
        val handler by lazy{
            Handler(Looper.getMainLooper())
        }
    }
    fun uiThread(f: () -> Unit){
        handler.post { f() }
    }
}