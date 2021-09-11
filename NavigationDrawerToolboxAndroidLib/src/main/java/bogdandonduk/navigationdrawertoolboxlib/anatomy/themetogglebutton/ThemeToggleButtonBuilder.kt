package bogdandonduk.navigationdrawertoolboxlib.anatomy.themetogglebutton

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment

class ThemeToggleButtonBuilder() {
    @PublishedApi
    internal var model = ThemeToggleButton(
        popupMenuLightThemeText = "Light theme",
        popupMenuDarkThemeText = "Dark theme",

        isDarkThemeProviderAction = {
            false
        },
        themeSetterAction = { _: Context, _: Boolean ->

        },

        autoThemeText = "Auto",
        isAutoThemeProviderAction = {
            true
        },
        autoThemeSetterAction = {

        }
    )

    inline fun setCustomIcon(modification: (oldIcon: Drawable?) -> Drawable?) = this.apply {
        model.customIcon = modification.invoke(model.customIcon)
    }

    fun getCustomIcon() = model.customIcon

    inline fun setIconContentDescription(modification: (oldDescription: String?) -> String?) = this.apply {
        model.iconContentDescription = modification.invoke(model.iconContentDescription)
    }

    fun getIconContentDescription() = model.iconContentDescription

    inline fun setPopupMenuTextColor(modification: (oldColor: Int?) -> Int?) = this.apply {
        model.popupMenuTextColor = modification.invoke(model.popupMenuTextColor)
    }

    fun getPopupMenuTextColor() = model.popupMenuTextColor

    inline fun setPopupMenuBackgroundColor(modification: (oldColor: Int?) -> Int?) = this.apply {
        model.popupMenuBackgroundColor = modification.invoke(model.popupMenuBackgroundColor)
    }

    fun getPopupMenuBackgroundColor() = model.popupMenuBackgroundColor

    inline fun setPopupMenuIconTintColor(modification: (oldColor: Int?) -> Int?) = this.apply {
        model.popupMenuIconTintColor = modification.invoke(model.popupMenuIconTintColor)
    }

    fun getPopupMenuIconTintColor() = model.popupMenuIconTintColor

    inline fun setPopupMenuLightThemeText(modification: (oldText: String) -> String) = this.apply {
        model.popupMenuLightThemeText = modification.invoke(model.popupMenuLightThemeText)
    }

    fun getPopupMenuLightThemeText() = model.popupMenuLightThemeText

    inline fun setPopupMenuDarkThemeText(modification: (oldText: String) -> String) = this.apply {
        model.popupMenuDarkThemeText = modification.invoke(model.popupMenuDarkThemeText)
    }

    fun getPopupMenuDarkThemeText() = model.popupMenuDarkThemeText

    fun setIsDarkThemeProviderAction(isDarkThemeProviderAction: (context: Context) -> Boolean) = this.apply {
        model.isDarkThemeProviderAction = isDarkThemeProviderAction
    }

    fun setThemeSetterAction(themeSetterAction: (context: Context, darkTheme: Boolean) -> Unit) = this.apply {
        model.themeSetterAction = themeSetterAction
    }

    inline fun setAutoThemeText(modification: (oldText: String) -> String) = this.apply {
        model.autoThemeText = modification.invoke(model.autoThemeText)
    }

    fun setIsAutoThemeProviderAction(isAutoThemeProviderAction: (context: Context) -> Boolean) = this.apply {
        model.isAutoThemeProviderAction = isAutoThemeProviderAction
    }

    fun setAutoThemeSetterAction(autoThemeSetterAction: (context: Context) -> Unit) = this.apply {
        model.autoThemeSetterAction = autoThemeSetterAction
    }

    fun setExtraOnClickAction(extraOnClickAction: ((buttonView: View, buttonIcon: ImageView, rootView: View, fragment: Fragment) -> Unit)?) = this.apply {
        model.extraOnClickAction = extraOnClickAction
    }

    fun hasExtraOnClickAction() = model.extraOnClickAction != null

    fun removeExtraOnClickAction() = this.apply {
        model.extraOnClickAction = null
    }

    fun setExtraOnLongClickAction(extraOnLongClickAction: ((buttonView: View, buttonIcon: ImageView, rootView: View, fragment: Fragment) -> Unit)?) = this.apply {
        model.extraOnLongClickAction = extraOnLongClickAction
    }

    fun hasExtraOnLongClickAction() = model.extraOnLongClickAction != null

    fun removeExtraOnLongClickAction() = this.apply {
        model.extraOnLongClickAction = null
    }

    fun build() = model
}