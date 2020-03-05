package android.voguepay.ng.voguepaysdk.di.keys

import android.voguepay.ng.voguepaysdk.base.BaseViewModel
import dagger.MapKey
import kotlin.reflect.KClass


@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class VoguePayViewModelKey(val value: KClass<out BaseViewModel>)