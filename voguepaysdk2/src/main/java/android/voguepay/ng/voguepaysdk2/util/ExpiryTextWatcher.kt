package android.voguepay.ng.voguepaysdk2.util

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class ExpiryWatcher(internal var cardExpiryTv: EditText) : TextWatcher {

    private val calendar: Calendar
    private val simpleDateFormat: SimpleDateFormat
    private var lastInput = ""

    init {
        calendar = Calendar.getInstance()
        simpleDateFormat = SimpleDateFormat("MM/yy")
    }

    override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

    }

    override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

    }

    override fun afterTextChanged(editable: Editable) {
        val input = editable.toString()

        try {
            calendar.time = simpleDateFormat.parse(input)
        } catch (e: ParseException) {
            if (editable.length == 2 && !lastInput.endsWith("/")) {
                val month = Integer.parseInt(input)
                if (month <= 12) {
                    cardExpiryTv.setText(cardExpiryTv.text.toString() + "/")
                    cardExpiryTv.setSelection(cardExpiryTv.text.toString().length)
                } else {
                    cardExpiryTv.setText("12")
                    cardExpiryTv.setSelection(cardExpiryTv.text.toString().length)
                }
            } else if (editable.length == 2 && lastInput.endsWith("/")) {
                val month = Integer.parseInt(input)
                if (month <= 12) {
                    cardExpiryTv.setText(cardExpiryTv.text.toString().substring(0, 1))
                    cardExpiryTv.setSelection(cardExpiryTv.text.toString().length)
                } else {
                    cardExpiryTv.setText("12")
                    cardExpiryTv.setSelection(cardExpiryTv.text.toString().length)
                }
            } else if (editable.length == 1) {
                val month = Integer.parseInt(input)
                if (month > 1) {
                    cardExpiryTv.setText("0" + cardExpiryTv.text.toString() + "/")
                    cardExpiryTv.setSelection(cardExpiryTv.text.toString().length)
                }
            }

            lastInput = cardExpiryTv.text.toString()
        }

    }


}