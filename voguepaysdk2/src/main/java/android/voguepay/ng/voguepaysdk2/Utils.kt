package android.voguepay.ng.voguepaysdk2

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.security.MessageDigest
import kotlin.experimental.and


class   Utils {
    val TAG = LogHelper.makeLogTag(Utils::class.java)
    companion object {
        fun isEmailValid(email: String): Boolean
                = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()

        fun hashStrings(commandApiToken : String, task : String?, merchant_email_on_vogue_pay : String?, time : String?) : String {
            var generatedHash : String = ""
            val stringToHash = commandApiToken + task + merchant_email_on_vogue_pay +  time
            LogHelper.e(TAG, stringToHash)
            try {
                var md = MessageDigest.getInstance("SHA-512")
                var digest = md.digest(stringToHash.toByteArray())
                var sb =  StringBuilder()
                for (b in digest){
                    sb.append(String.format("%02x", b and 0xff.toByte()))
                }
                LogHelper.e(TAG, sb.toString())
                generatedHash =  sb.toString()

            }catch (e : Exception){

            }
            return generatedHash
        }

    }




}
fun hide_keyboard(activity: Activity) {
    val inputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    //Find the currently focused view, so we can grab the correct window token from it.
    var view = activity.currentFocus
    //If no view currently has focus, create a new one, just so we can grab a window token from it
    if (view == null) {
        view = View(activity)
    }
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

 fun hashStrings(commandApiToken : String, task : String?, merchant_email_on_vogue_pay : String?, time : String?)  : String{
    var generatedHash : String = ""
    val stringToHash = commandApiToken + task + merchant_email_on_vogue_pay +  time
    Log.e("main", stringToHash)

    try {
        var md = MessageDigest.getInstance("SHA-512")
        var digest = md.digest(stringToHash.toByteArray())
        var sb =  StringBuilder()
        for (b in digest){
            sb.append(String.format("%02x", b and 0xff.toByte()))
        }
        Log.e("mainFragment", sb.toString())
         generatedHash =  sb.toString()

    }catch (e : Exception){


    }

    return generatedHash
}
