package android.voguepay.ng.voguepaysdk2

import android.content.ContentValues.TAG
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
