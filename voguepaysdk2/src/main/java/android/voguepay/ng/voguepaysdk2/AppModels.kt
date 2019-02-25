package android.voguepay.ng.voguepaysdk2

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.net.URLEncoder

data class VoguePayInitializer (
    var task: String? = "card",
    @SerializedName("merchant")
    var merchant_id: String? = "8302-0053889",
    var ref: String? = "",
    var hash: String? = "",
    //compulsory fields
    var total: Int? = 10,
    @SerializedName("email")
    var buyer_email: String? = "andyeshiet@gmail.com",// Email Address of Card Owner
    var merchant_ref: String? = "ref123", // This will be returned to you on transaction requery
    var currency: String? = "NGN",  // Supported Currencies in Nigeria
    var memo: String? = "For Shoe Payment", // description of transaction (Must be encoded)
    var referral_url: String = "", // Shpuld be the Url the user was before he arrived here
    var response_url: String = URLEncoder.encode("http://f3c45a65.ngrok.io/customers", "UTF-8"),  // Transaction ID is posted to this url (Must be encoded)
    var card : Card? = null,
    //optional Fields
    var demo: Boolean = false,
    var phone : String? = "08120088124",//Phone number of card owner
    //Descriptor Information (Optional)
    var company : String = "test",
    var  city : String = "ikeja",
    var street : String = "56 thgb",
    var country : String = "NGA",
    var withCard : Boolean = true,
    var withAccount : Boolean = true
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(Card::class.java.classLoader),
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(task)
        parcel.writeString(merchant_id)
        parcel.writeString(ref)
        parcel.writeString(hash)
        parcel.writeValue(total)
        parcel.writeString(buyer_email)
        parcel.writeString(merchant_ref)
        parcel.writeString(currency)
        parcel.writeString(memo)
        parcel.writeString(referral_url)
        parcel.writeString(response_url)
        parcel.writeParcelable(card, flags)
        parcel.writeByte(if (demo) 1 else 0)
        parcel.writeString(phone)
        parcel.writeString(company)
        parcel.writeString(city)
        parcel.writeString(street)
        parcel.writeString(country)
        parcel.writeByte(if (withCard) 1 else 0)
        parcel.writeByte(if (withAccount) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VoguePayInitializer> {
        override fun createFromParcel(parcel: Parcel): VoguePayInitializer {
            return VoguePayInitializer(parcel)
        }

        override fun newArray(size: Int): Array<VoguePayInitializer?> {
            return arrayOfNulls(size)
        }
    }

}

data class  LabelResponse(
    var id : Any = "", //object id
    var merchant_ref : String = "",
    var message : String = "",
    var status : String = "",
    var response : String = "",
    var salt : String =  "",
    var hash: String = "",
    var username : String = ""
)
data class  Card (
    var name:  String = "Andy Eshiet", // Name of card holder
    var pan: String = "5123450000000008", // Pan of card to be debited
    var month: String = "05", //expiry month of card to be debited
    var year: String = "21", //Expiry year of card to be debited
    var  cvv : String  = "100" // cvv of card to be debited
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(pan)
        parcel.writeString(month)
        parcel.writeString(year)
        parcel.writeString(cvv)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Card> {
        override fun createFromParcel(parcel: Parcel): Card {
            return Card(parcel)
        }

        override fun newArray(size: Int): Array<Card?> {
            return arrayOfNulls(size)
        }
    }
}

data class Payment(
    var p: String = "linkToken",
    var v_merchant_id: String = "demo",
    var memo: String = "",
    var total: String = "",
    var merchant_ref: String = "ref123",
    var notify_url: String = "",
    var success_url: String = "",
    var fail_url: String = "",
    var developer_code: String = "",
    var cur: String = "NGN"
)