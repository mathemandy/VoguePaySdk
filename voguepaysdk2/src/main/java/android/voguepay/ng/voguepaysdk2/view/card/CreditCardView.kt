package android.voguepay.ng.voguepaysdk2.view.card

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.text.InputType
import android.util.AttributeSet
import android.util.Log
import android.util.SparseArray
import android.voguepay.ng.voguepaysdk2.R
import com.google.android.material.textfield.TextInputEditText
import java.util.regex.Pattern

class CreditCardView : TextInputEditText {


    private var mCCPatterns: SparseArray<Pattern>? = null
    //default credit card image
    private val mDefaultDrawableResId = R.drawable.creditcard
    private var mCurrentDrawableResId = 0
    private var mCurrentDrawable: Drawable? = null
    private var lastFormattedText: String? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        lastFormattedText = ""
        if (mCCPatterns == null) {
            mCCPatterns = SparseArray()
            // With spaces for credit card masking
            mCCPatterns?.put(

                R.drawable.visa_logo_new, Pattern.compile(
                    "^4[0-9]{2,12}(?:[0-9]{3})?$"
                )
            )
            mCCPatterns?.put(
                R.drawable.master_card_logo_svg, Pattern.compile(
                    "^5[1-5][0-9]{1,14}$"
                )
            )
            mCCPatterns?.put(
                R.drawable.amex, Pattern.compile(
                    "^3[47][0-9]{1,13}$"
                )
            )
            ///^([506]{3})([0-9]{1,16})$/
            mCCPatterns?.put(
                R.drawable.verve, Pattern.compile(
                    "^([506]{3})([0-9]{1,16})$"
                )
            )
        }

        inputType = InputType.TYPE_CLASS_PHONE

        addTextChangedListener(CreditCardTextWatcher())
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (mCurrentDrawable == null) {
            return
        }

        var rightOffset = 0
        if (error != null && error.isNotEmpty()) {
            rightOffset = resources.displayMetrics.density as Int * 32
        }

        val right = width - paddingRight - rightOffset

        val top = paddingTop
        val bottom = height - paddingBottom
        val ratio = mCurrentDrawable!!.intrinsicWidth.toFloat() / mCurrentDrawable?.intrinsicHeight?.toFloat() !!
        //int left = right - mCurrentDrawable.getIntrinsicWidth(); //If images are correct size.
        val left = (right - (bottom - top) * ratio).toInt() //scale image depeding on height available.
        mCurrentDrawable!!.setBounds(left, top, right, bottom)

        mCurrentDrawable!!.draw(canvas)
    }

    protected override fun onTextChanged(text: CharSequence, start: Int, lengthBefore: Int, lengthAfter: Int) {

        if (mCCPatterns == null) {
            init()
        }
        //
        val actualText = text.toString().replace("\\s".toRegex(), "")

        Log.d("actual", actualText)

        if (lengthBefore == actualText.length) {
            return
        }

        var mDrawableResId = 0
        for (i in 0 until mCCPatterns!!.size()) {
            val key = mCCPatterns!!.keyAt(i)
            // get the object by the key.
            val p = mCCPatterns!!.get(key)
            val m = p.matcher(actualText)
            if (m.find()) {
                mDrawableResId = key
                break
            }
        }
        if (mDrawableResId > 0 && mDrawableResId != mCurrentDrawableResId) {
            mCurrentDrawableResId = mDrawableResId
        } else if (mDrawableResId == 0) {
            mCurrentDrawableResId = mDefaultDrawableResId
        }
        mCurrentDrawable = resources
            .getDrawable(mCurrentDrawableResId)


    }

}