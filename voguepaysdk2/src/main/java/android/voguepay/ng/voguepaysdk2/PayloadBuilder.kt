package android.voguepay.ng.voguepaysdk2

import com.google.gson.annotations.SerializedName
import java.net.URLEncoder

class PayloadBuilder {

    var task: String? = "card"
    @SerializedName("merchant")
    var merchant_id: String? = "8302-0053889"
    var ref: String? = ""
    var hash: String = ""
    //compulsory fields
    var total: Int = 10
    @SerializedName("email")
    var buyer_email: String = "andyeshiet@gmail.com"// Email Address of Card Owner
    var merchant_ref: String = "ref123" // This will be returned to you on transaction requery
    var currency: String = "NGN"  // Supported Currencies in Nigeria
    var memo: String = "For Shoe Payment" // description of transaction (Must be encoded)
    var referral_url: String = "" // Shpuld be the Url the user was before he arrived here
    var response_url: String = URLEncoder.encode("http://f3c45a65.ngrok.io/customers", "UTF-8")  // Transaction ID is posted to this url (Must be encoded)
    var card : Card? = null
    //optional Fields
    var demo: Boolean = false
    var phone : String = "08120088124"//Phone number of card owner
    //Descriptor Information (Optional)
    var company : String = "test"
    var  city : String = "ikeja"
    var street : String = "56 thgb"
    var country : String = "NGA"



    fun createPayload() {


    }





}