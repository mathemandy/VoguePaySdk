package android.voguepay.ng.voguepaysdk2.view.card

import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.SparseArray
import android.voguepay.ng.voguepaysdk2.R
import java.util.regex.Pattern

 class CreditCardTextWatcher : TextWatcher {
    private val mDefaultDrawableResId = R.drawable.creditcard
    private val mCurrentDrawableResId = 0
    private val mCurrentDrawable: Drawable? = null
    var lastFormattedText: String? = null
    private var mCCPatterns: SparseArray<Pattern>? = null

    init {
        init()
    }

    private fun init() {
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
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

    }

    override fun afterTextChanged(s: Editable) {

        //        if (s.length() > 0) {
        //            String spacified = spacifyCardNumber(s.toString());
        //            s.replace(0, s.length(), spacified);
        //        }

        if (s.isNotEmpty() && s.length % 5 == 0) {
            val c = s[s.length - 1]
            if (space == c) {
                s.delete(s.length - 1, s.length)
            }
        }
        // Insert char where needed.
        if (s.isNotEmpty() && s.length % 5 == 0) {
            val c = s[s.length - 1]
            // Only if its a digit where there should be a space we insert a space
            if (Character.isDigit(c) && TextUtils.split(s.toString(), space.toString()).size <= 3) {
                s.insert(s.length - 1, space.toString())
            }
        }
    }

    companion object {

        private val space = ' '
    }


}
