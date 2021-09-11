package bogdandonduk.navigationdrawertoolboxlib.anatomy.header.simple

import android.graphics.drawable.Drawable
import android.view.View
import androidx.fragment.app.Fragment

class SimpleNavigationDrawerHeaderBuilder() {
    @PublishedApi
    internal var model = SimpleNavigationDrawerHeader(
        backgroundColor = null,
        backgroundDrawable = null,
        backgroundDrawableTintColor = null,
        appNameText = "App name",
        appNameTextColor = null,
        appVersionText = null,
        appVersionTextColor = null,
        appIcon = null,
        appIconByteArray = null,
        appIconTintColor = null,
        appIconContentDescription = "App icon",
        onClickAction = null,
        onLongClickAction = null
    )

    inline fun setBackgroundColor(modification: (oldColor: Int?) -> Int?) = this.apply {
        model.backgroundColor = modification.invoke(model.backgroundColor)
    }

    fun getBackgroundColor() = model.backgroundColor

    inline fun setBackgroundDrawable(modification: (oldDrawable: Drawable?) -> Drawable?) = this.apply {
        model.backgroundDrawable = modification.invoke(model.backgroundDrawable)
    }

    fun getBackgroundDrawable() = model.backgroundDrawable

    inline fun setAppNameText(modification: (oldColor: CharSequence) -> CharSequence) = this.apply {
        model.appNameText = modification.invoke(model.appNameText)
    }

    fun getAppNameText() = model.backgroundColor

    inline fun setAppNameTextColor(modification: (oldColor: Int?) -> Int?) = this.apply {
        model.appNameTextColor = modification.invoke(model.appNameTextColor)
    }

    fun getAppNameTextColor() = model.appNameTextColor

    inline fun setAppVersionText(modification: (oldText: CharSequence?) -> CharSequence?) = this.apply {
        model.appVersionText = modification.invoke(model.appVersionText)
    }

    fun getAppVersionText() = model.appVersionText

    inline fun setAppVersionTextColor(modification: (oldColor: Int?) -> Int?) = this.apply {
        model.appVersionTextColor = modification.invoke(model.appVersionTextColor)
    }

    fun getAppVersionTextColor() = model.appVersionTextColor

    inline fun setAppIcon(modification: (oldDrawable: Drawable?) -> Drawable?) = this.apply {
        model.appIcon = modification.invoke(model.appIcon)
    }

    fun getAppIcon() = model.appIcon

    inline fun setAppIconByteArray(modification: (oldDrawable: ByteArray?) -> ByteArray?) = this.apply {
        model.appIconByteArray = modification.invoke(model.appIconByteArray)
    }

    fun getAppIconByteArray() = model.appIconByteArray

    inline fun setAppIconTintColor(modification: (oldColor: Int?) -> Int?) = this.apply {
        model.appIconTintColor = modification.invoke(model.appIconTintColor)
    }

    fun getAppIconTintColor() = model.appIconTintColor

    fun setOnClickAction(onClickAction: ((headerRootView: View, rootView: View, fragment: Fragment) -> Unit)?) = this.apply {
        model.onClickAction = onClickAction
    }

    fun hasOnClickAction() = model.onClickAction != null

    fun removeOnClickAction() = this.apply {
        model.onClickAction = null
    }

    fun setOnLongClickAction(onClickAction: ((headerRootView: View, rootView: View, fragment: Fragment) -> Unit)?) = this.apply {
        model.onLongClickAction = onClickAction
    }

    fun hasOnLongClickAction() = model.onLongClickAction != null

    fun removeOnLongClickAction() = this.apply {
        model.onLongClickAction = null
    }

    fun build() = model
}