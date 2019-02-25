package android.voguepay.ng.voguepaysdk.api;

import android.voguepay.ng.voguepaysdk.WhiteLabel;
import android.voguepay.ng.voguepaysdk2.LabelResponse;
import io.reactivex.Observable;
import retrofit2.http.*;

public interface VoguePayApiService {




    @GET(".")
    Observable<String> getPaymentUrl(@Query("p") String typeOfLink,
                                     @Query("v_merchant_id")  String merchant_id,
                                     @Query("total") String total,
                                     @Query("memo") String memo,
                                     @Query("cur") String currency);

    @FormUrlEncoded
    @POST("/api")
    Observable<LabelResponse> getPaymentHeader (@Field("json") String whiteLabel);

}
