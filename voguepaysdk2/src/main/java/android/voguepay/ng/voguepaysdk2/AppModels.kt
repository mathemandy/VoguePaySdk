package android.voguepay.ng.voguepaysdk2

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class VoguePayInitializer (
    var clientEmail: String?,
    var merchantEmail: String?,
    var transactionAmount: Int?,
    var txRef: String?,
    var narration: String?,
    var currencyCode: String?,
    var countryCode: String?,
    var firstName: String?,
    var lastName: String?,
    var withCard: Boolean?,
    var withAccount: Boolean?,
    var staging: Boolean?,
    var allowSaveCard: Boolean?,
    var merchant_id: String?,
    var ref: String?,
    var hash: String?,
    var merchant_ref: String?,
    var memo: String?,
    var referral_url: String?,
    var theme: Int?,
    var response_url: String?,
    var card: Card?,
    //optional Fields
    var demo: Boolean?,
    var phone: String?,
    //Descriptor Information (Optional)
    var company: String?,
    var city: String?,
    var street: String?,
    var task: String?,
    var total: Int?
) : Parcelable

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