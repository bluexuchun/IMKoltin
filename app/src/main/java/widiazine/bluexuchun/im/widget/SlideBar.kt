package widiazine.bluexuchun.im.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import org.jetbrains.anko.sp
import widiazine.bluexuchun.im.R

/**
 * 自定义组件 slideBar 继承view
 * 在xml中 能够引用
 */
class SlideBar(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    var sectionHeight = 0

    var textBaseLine = 0f

    /**
     * 设置下标改变的监听
     */
    var onSectionChangeListener: OnSectionChangeListener? = null

    /**
     * 画笔
     */
    var paint = Paint()

    companion object {
        private val SECTIONS = arrayOf("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z")
    }

    init {
        /**
         * 文字的配置
         */
        paint.apply {
            color = resources.getColor(R.color.qq_gray)
            textSize = sp(12).toFloat()
            //文字对齐剧中
            textAlign = Paint.Align.CENTER
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        // 计算每个字符分配的高度
        sectionHeight = h / SECTIONS.size

        /**
         * 文本居中的计算
         * 了解fontMetrics这个概念
         */
        val fontMetrics = paint.fontMetrics
        // 计算绘制文本的高度
        val textHeight = fontMetrics.descent - fontMetrics.ascent
        // 计算基准线
        textBaseLine = sectionHeight / 2 +(textHeight/2 - fontMetrics.descent)

    }
    /**
     * 开始绘制
     * 绘制所有的字母
     */
    override fun onDraw(canvas: Canvas) {
        /**
         * 绘制字符的起始位置
         **/
        val x = width/2.toFloat()
        var y = textBaseLine
        SECTIONS.forEach {
            canvas.drawText(it,x,y,paint)
            // 更新y 绘制下一个字母
            y += sectionHeight
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                setBackgroundResource(R.drawable.btn_warn)
                /**
                 * 找到字母
                 */
                var index = getTouchIndex(event)
                var firstLetter = SECTIONS[index]
                onSectionChangeListener?.onSectionChange(firstLetter)

            }
            MotionEvent.ACTION_MOVE -> {
                setBackgroundResource(R.drawable.btn_warn)
                /**
                 * 找到字母
                 */
                var index = getTouchIndex(event)
                var firstLetter = SECTIONS[index]
                onSectionChangeListener?.onSectionChange(firstLetter)
            }
            MotionEvent.ACTION_UP -> {
                setBackgroundResource(R.drawable.btn_primary)
                onSectionChangeListener?.onSlideFinish()
            }

        }
        //消费事件
        return true
    }

    /**
     * 获取点击位置的字母的下标
     */
    private fun getTouchIndex(event: MotionEvent): Int {
        var index = (event.getY() / sectionHeight).toInt()
        //越界的检查
        if (index < 0){
            index = 0
        }else if(index >= SECTIONS.size){
            index = SECTIONS.size - 1
        }
        return index
    }

    /**
     * 定义接口 给fragment去调用
     */
    interface OnSectionChangeListener{
        /**
         * 字母下标改变
         */
        fun onSectionChange(firstLetter:String)

        /**
         * 活动结束的回调
         */
        fun onSlideFinish()
    }
}