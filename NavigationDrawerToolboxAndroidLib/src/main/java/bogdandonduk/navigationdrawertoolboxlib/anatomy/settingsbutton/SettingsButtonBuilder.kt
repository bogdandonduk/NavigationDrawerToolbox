package bogdandonduk.navigationdrawertoolboxlib.anatomy.settingsbutton

import android.app.Activity
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment

class SettingsButtonBuilder() {
    @PublishedApi
    internal var model = SettingsButton(
        tooltipText = "Settings"
    )

    inline fun setCustomIcon(modification: (oldIcon: Drawable?) -> Drawable?) = this.apply {
        model.customIcon = modification.invoke(model.customIcon)
    }

    fun getCustomIcon() = model.customIcon

    inline fun setIconTintColor(modification: (oldColor: Int?) -> Int?) = this.apply {
        model.iconTintColor = modification.invoke(model.iconTintColor)
    }

    fun getIconTintColor() = model.iconTintColor

    inline fun setIconContentDescription(modification: (oldDescription: String?) -> String?) = this.apply {
        model.iconContentDescription = modification.invoke(model.iconContentDescription)
    }

    fun getIconContentDescription() = model.iconContentDescription

    inline fun setTooltipText(modification: (oldText: String) -> String) = this.apply {
        model.tooltipText = modification.invoke(model.tooltipText)
    }

    fun getTooltipText() = model.tooltipText

    inline fun setTooltipTextColor(modification: (oldColor: Int?) -> Int?) = this.apply {
        model.tooltipTextColor = modification.invoke(model.tooltipTextColor)
    }

    fun getTooltipTextColor() = model.tooltipTextColor

    inline fun setTooltipBackgroundColor(modification: (oldColor: Int?) -> Int?) = this.apply {
        model.tooltipBackgroundColor = modification.invoke(model.tooltipBackgroundColor)
    }

    fun getTooltipBackgroundColor() = model.tooltipBackgroundColor

    fun setSettingsActivityClass(modification: (oldClass: Class<out Activity>?) -> Class<out Activity>?) = this.apply {
        model.settingsActivityClass = modification.invoke(model.settingsActivityClass)
    }

    fun getSettingsActivityClass() = model.settingsActivityClass

    fun setSettingsActivityStartOptions(modification: (oldOptions: Bundle?) -> Bundle?) = this.apply {
        model.settingsActivityStartOptions = modification.invoke(model.settingsActivityStartOptions)
    }

    fun getSettingsActivityStartOptions() = model.settingsActivityStartOptions

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