package com.ch.ni.an.handlerthread.trash.presenter

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import com.ch.ni.an.handlerthread.R
import com.ch.ni.an.handlerthread.databinding.PartButtonsBinding


enum class BottomButtonAction {
    POSITIVE, NEGATIVE
}

typealias OnBottomButtonsActionListener = (BottomButtonAction) -> Unit

class BottomButtonsView(
    context :Context,
    attrs: AttributeSet?,
    defStyleAtr: Int,
    defStyleRes: Int
): ConstraintLayout(context, attrs,defStyleAtr,defStyleRes) {

    private val binding: PartButtonsBinding

    private var listener: OnBottomButtonsActionListener? = null

    var isProgressMode :Boolean = false
        set(value) {
            field = value
            with(binding) {
                if (value) {
                    positiveButton.visibility = INVISIBLE
                    negativeButton.visibility = INVISIBLE
                    progressBar.visibility = VISIBLE
                } else {
                    positiveButton.visibility = VISIBLE
                    negativeButton.visibility = VISIBLE
                    progressBar.visibility = GONE
                }
            }
        }

    constructor(context :Context,attrs :AttributeSet?,defStyleAtr :Int): this(context, attrs, defStyleAtr, R.style.MyBottomButtonsStyle)
    constructor(context :Context, attrs :AttributeSet?): this(context,attrs, R.attr.bottomButtonsStyle)
    constructor(context :Context): this(context, null)


    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.part_buttons, this, true)
        binding = PartButtonsBinding.bind(this)
        initializeAttrs(attrs,defStyleAtr,defStyleRes)
        initListeners()
    }

    private fun initializeAttrs(attrs :AttributeSet?, defStyleAtr :Int, defStyleRes :Int){
        if(attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.BottomButtonsView, defStyleAtr, defStyleRes)
        with(binding){
            val positiveButtonText = typedArray.getString(R.styleable.BottomButtonsView_bottomPositiveButtonText)
            setPositiveText(positiveButtonText)

            val negativeButtonText = typedArray.getString(R.styleable.BottomButtonsView_bottomNegativeButtonText)
            setNegativeText(negativeButtonText)

            val positiveBackgroundColor = typedArray.getColor(R.styleable.BottomButtonsView_bottomPositiveBackgroundColor, Color.BLACK)
            positiveButton.backgroundTintList = ColorStateList.valueOf(positiveBackgroundColor)

            val negativeBackgroundColor = typedArray.getColor(R.styleable.BottomButtonsView_bottomNegativeBackgroundColor, Color.WHITE)
            negativeButton.backgroundTintList = ColorStateList.valueOf(negativeBackgroundColor)

            isProgressMode = typedArray.getBoolean(R.styleable.BottomButtonsView_bottomProgressMode, false)

        }

        typedArray.recycle()
    }

    private fun initListeners(){
        binding.positiveButton.setOnClickListener {
            this.listener?.invoke(BottomButtonAction.POSITIVE)
        }
        binding.negativeButton.setOnClickListener {
            this.listener?.invoke(BottomButtonAction.NEGATIVE)
        }
    }

    fun setListener(listener: OnBottomButtonsActionListener){
        this.listener = listener
    }

    fun setPositiveText(text:String?){
        binding.positiveButton.text = text ?: "Start"
    }

    fun setNegativeText(text :String?){
        binding.negativeButton.text = text ?: "Stop"
    }

    override fun onSaveInstanceState() :Parcelable? {
        val superState = super.onSaveInstanceState()!!
        val savedState = SavedState(superState)
        savedState.isProgressMode = isProgressMode
        return savedState

    }

    override fun onRestoreInstanceState(state :Parcelable?) {
        val savedState = state as SavedState
        super.onRestoreInstanceState(savedState.superState)
        isProgressMode = savedState.isProgressMode
    }

    class SavedState: BaseSavedState{

        var isProgressMode: Boolean = false

        constructor(superState: Parcelable): super(superState)
        @RequiresApi(Build.VERSION_CODES.Q)
        constructor(parcel:Parcel): super(parcel){
            isProgressMode = parcel.readBoolean()
        }

        @RequiresApi(Build.VERSION_CODES.Q)
        override fun writeToParcel(out :Parcel, flags :Int) {
            super.writeToParcel(out, flags)
            out.writeBoolean(isProgressMode)
        }

        companion object {
            @JvmField
            val CREATOR: Parcelable.Creator<SavedState> = object : Parcelable.Creator<SavedState>{
                @RequiresApi(Build.VERSION_CODES.Q)
                override fun createFromParcel(source :Parcel) :SavedState {
                    return SavedState(source)
                }

                override fun newArray(size :Int) :Array<SavedState?> {
                    return Array(size) {null}
                }

            }
        }
    }
}