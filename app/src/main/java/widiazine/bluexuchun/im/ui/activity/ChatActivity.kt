package widiazine.bluexuchun.im.ui.activity

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.header.*
import widiazine.bluexuchun.im.R

class ChatActivity:BaseActivity(){
    override fun getLayoutResId(): Int {
        return R.layout.activity_chat
    }

    override fun init() {
        super.init()
        initHeader()
        initEditText()
    }

    private fun initEditText() {
        /**
         * 文字输入改变监听
         */
        edit.addTextChangedListener(object : TextWatcher {
            /**
             * 用户输入的文本长度大于0 发送按钮enable
             */
            override fun afterTextChanged(s: Editable?) {
                send.isEnabled = !s.isNullOrEmpty()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }

    private fun initHeader() {
        back.visibility = View.VISIBLE
        back.setOnClickListener {
            finish()
        }

        /**
         * 获取聊天的用户名
         * getStringExtra获取参数
         */
        val username = intent.getStringExtra("username")
        val title = String.format(getString(R.string.chat_title),username)
        headerTitle.text = title
    }

}