package com.ch.ni.an.handlerthread.lessonView2

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.os.Build
import android.util.AttributeSet
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View
import androidx.annotation.RequiresApi
import com.ch.ni.an.handlerthread.R
import java.lang.Integer.max
import kotlin.math.min
import kotlin.properties.Delegates



typealias OnCellActionListener = (row:Int, column:Int, field: TikTacToeField) -> Unit

class TikTakToeView(
    context :Context,
    attributeSet :AttributeSet?,
    defStyleAtr: Int,
    defStyleRes: Int
): View(context,attributeSet, defStyleAtr,defStyleRes) {

    var ticTacToeField: TikTacToeField? = null
        set(value) {
            field?.listeners?.remove(listener)
            field = value
            value?.listeners?.add(listener)
            updateViewSizes()
            requestLayout()
            invalidate()

        }

    var actionListener: OnCellActionListener? = null
    private var playerOneColor by Delegates.notNull<Int>()
    private var playerTwoColor by Delegates.notNull<Int>()
    private var greedColor by Delegates.notNull<Int>()


    private val fieldRect = RectF(0f,0f,0f,0f)
    private var cellSize = 0f
    private var cellPadding = 0f

    private lateinit var playerOnePaint :Paint
    private lateinit var playerTwoPaint :Paint
    private lateinit var greedPaint :Paint

    private var cellRect = RectF()

    constructor(context :Context,attributeSet :AttributeSet?, defStyleAtr :Int): this(context, attributeSet,defStyleAtr,R.style.DefaultTicTacFieldStyle)
    constructor(context :Context, attributeSet :AttributeSet?): this(context, attributeSet, R.attr.TicTacToeFieldStyle)
    constructor(context :Context): this(context, null)


    init {
        if(attributeSet != null){
            initAttributes(attributeSet, defStyleAtr, defStyleRes)
        } else initDefaultColor()
        initPaints()
        if(isInEditMode){
            ticTacToeField = TikTacToeField(6,4).apply{
                setCell(2,3,Cell.PLAYER_1)
                setCell(1,2,Cell.PLAYER_2)
            }

        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        ticTacToeField?.listeners?.add(listener)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        ticTacToeField?.listeners?.remove(listener)
    }

    override fun onSizeChanged(w :Int, h :Int, oldw :Int, oldh :Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        updateViewSizes()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onMeasure(widthMeasureSpec :Int, heightMeasureSpec :Int) {
        val minWidth = suggestedMinimumWidth + paddingStart + paddingEnd
        val minHeight = suggestedMinimumHeight + paddingTop + paddingBottom

        val desiredCellSizeInPixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
            DESIRED_CELL_SIZE,
            resources.displayMetrics).toInt()

        val rows = ticTacToeField?.rows ?: 0
        val columns = ticTacToeField?.columns ?: 0

        val desiredWith = max(minWidth,columns*desiredCellSizeInPixels + paddingStart + paddingRight)
        val desiredHeight = max(minHeight,rows*desiredCellSizeInPixels + paddingTop + paddingBottom)

        setMeasuredDimension(
            resolveSize(desiredWith, widthMeasureSpec),
            resolveSize(desiredHeight, heightMeasureSpec)
        )
    }

    override fun onDraw(canvas :Canvas) {
        super.onDraw(canvas)

        if(ticTacToeField == null) return
        if(cellSize == 0f) return
        if(fieldRect.width() <=0) return
        if(fieldRect.height() <=0) return
        onDrawCells(canvas)
        onDrawGrid(canvas)
    }



    override fun onTouchEvent(event :MotionEvent) :Boolean {

        val field = this.ticTacToeField ?: return false
        when(event.action){
            MotionEvent.ACTION_DOWN -> {
                return true
            }
            MotionEvent.ACTION_UP ->{
                val row = getRow(event)
                val column = getColumn(event)
                if(row >=0 || column >= 0 && row < field.rows && column < field.columns){
                    actionListener?.invoke(row, column, field)
                    return true
                }
                return false
            }
        }
        return super.onTouchEvent(event)
    }


    private fun getRow(event :MotionEvent): Int {
        return ((event.y - fieldRect.top) / cellSize).toInt()
    }

    private fun getColumn(event :MotionEvent): Int{
        return ((event.x - fieldRect.left) / cellSize).toInt()
    }

    private fun onDrawCells(canvas :Canvas){
        val field = this.ticTacToeField ?: return
        for(row in 0 until field.rows  ){
            for( column in 0 until field.columns){
                val cell = field.getCell(row, column)
                if(cell == Cell.PLAYER_1){
                    onDrawPlayerOne(canvas, row, column)
                } else if(cell ==Cell.PLAYER_2){
                    onDrawPlayerTwo(canvas, row, column)
                }
            }
        }
    }

    private fun onDrawPlayerOne(canvas :Canvas, row :Int, column :Int) {
        val cellRect = getCellRect(row, column)
        canvas.drawLine(cellRect.left, cellRect.top, cellRect.right, cellRect.bottom, playerOnePaint)
        canvas.drawLine(cellRect.right, cellRect.top, cellRect.left, cellRect.bottom, playerOnePaint)

    }

    private fun onDrawPlayerTwo(canvas :Canvas, row:Int, column: Int){
        val cellRect = getCellRect(row, column)
        canvas.drawCircle(cellRect.centerX(), cellRect.centerY(), cellRect.width()/2, playerTwoPaint)
    }

    private fun getCellRect(row :Int, column :Int): RectF{
        cellRect.left = fieldRect.left + column * cellSize + cellPadding
        cellRect.top = fieldRect.top + row * cellSize + cellPadding
        cellRect.right = cellRect.left + cellSize - cellPadding * 2
        cellRect.bottom = cellRect.top + cellSize - cellPadding * 2
        return cellRect
    }

    private fun onDrawGrid(canvas :Canvas){
        val field = this.ticTacToeField ?: return
        val xStart = fieldRect.left
        val xEnd = fieldRect.right
        for(i in 0..field.rows){
            val y = fieldRect.top + cellSize * i
            canvas.drawLine(xStart, y, xEnd, y, greedPaint)
        }
        val yStart = fieldRect.top
        val yEnd = fieldRect.bottom
        for(i in 0..field.columns){
            val x = fieldRect.left + cellSize * i
            canvas.drawLine(x, yStart, x, yEnd, greedPaint)
        }
    }

    private fun initPaints(){

        playerOnePaint = Paint(Paint.ANTI_ALIAS_FLAG)
        playerOnePaint.color = playerOneColor
        playerOnePaint.style = Paint.Style.STROKE
        playerOnePaint.strokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3f, resources.displayMetrics)

        playerTwoPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        playerTwoPaint.color = playerTwoColor
        playerTwoPaint.style = Paint.Style.STROKE
        playerTwoPaint.strokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3f, resources.displayMetrics)

        greedPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        greedPaint.color = greedColor
        greedPaint.style = Paint.Style.STROKE
        greedPaint.strokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1f, resources.displayMetrics)



    }

    private fun updateViewSizes(){
        val field = this.ticTacToeField ?: return

        val safeWidth = width - paddingStart - paddingEnd
        val safeHeight = height -paddingTop - paddingBottom

        val cellWidth = safeWidth / field.columns.toFloat()
        val cellHeight = safeHeight / field.rows.toFloat()

        cellSize = min(cellWidth, cellHeight)
        cellPadding = cellSize * 0.2f

        val fieldWidth = cellSize * field.columns
        val fieldHeight = cellSize * field.rows

        fieldRect.left = paddingLeft + (safeWidth - fieldWidth) / 2
        fieldRect.top = paddingTop + (safeHeight - fieldHeight) / 2
        fieldRect.right = fieldRect.left + fieldWidth
        fieldRect.bottom = fieldRect.top + fieldHeight
    }

    private fun initAttributes(attributeSet :AttributeSet?, defStyleAtr :Int, defStyleRes :Int){
        val typedArray = context.obtainStyledAttributes(attributeSet,R.styleable.TikTakToeView , defStyleAtr, defStyleRes)
        playerOneColor = typedArray.getColor(R.styleable.TikTakToeView_playerOneColor, PLAYER_ONE_DEF_COLOR)
        playerTwoColor = typedArray.getColor(R.styleable.TikTakToeView_playerTwoColor, PLAYER_TWO_DEF_COLOR)
        greedColor = typedArray.getColor(R.styleable.TikTakToeView_greedColor, GREED_DEF_COLOR)


        typedArray.recycle()
    }

    private fun initDefaultColor(){
        playerOneColor = PLAYER_ONE_DEF_COLOR
        playerTwoColor = PLAYER_TWO_DEF_COLOR
        greedColor = GREED_DEF_COLOR
    }

    private val listener: OnFieldChangedListener = {
        invalidate()
    }

    companion object{
        const val PLAYER_ONE_DEF_COLOR = Color.GREEN
        const val PLAYER_TWO_DEF_COLOR = Color.RED
        const val GREED_DEF_COLOR = Color.GRAY

        const val DESIRED_CELL_SIZE = 50f
    }


}