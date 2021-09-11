package bogdandonduk.navigationdrawertoolboxlib.anatomy.genericcolors

import android.graphics.Color
import android.graphics.drawable.Drawable

class GenericColorsBuilder() {
    @PublishedApi
    internal var model = GenericColors(
        backgroundColor = Color.WHITE,
        textColor = Color.BLACK,
        tooltipBackgroundColor = Color.BLACK,
        tooltipTextColor = Color.WHITE,
        tooltipIconTintColor = Color.WHITE,
        popupMenuBackgroundColor = Color.BLACK,
        popupMenuTextColor = Color.WHITE,
        popupMenuIconTintColor = Color.WHITE
    )

    inline fun setBackgroundColor(modification: (oldColor: Int) -> Int) = this.apply {
        model.backgroundColor = modification.invoke(model.backgroundColor)
    }

    fun getBackgroundColor() = model.backgroundColor

    inline fun setBackgroundDrawable(modification: (oldIcon: Drawable?) -> Drawable?) = this.apply {
        model.backgroundDrawable = modification.invoke(model.backgroundDrawable)
    }

    fun getBackgroundDrawable() = model.backgroundDrawable

    inline fun setBackgroundDrawableTintColor(modification: (oldColor: Int?) -> Int?) = this.apply {
        model.backgroundDrawableTintColor = modification.invoke(model.backgroundDrawableTintColor)
    }

    fun getBackgroundDrawableTintColor() = model.backgroundDrawableTintColor

    inline fun setIconTintColor(modification: (oldColor: Int?) -> Int?) = this.apply {
        model.iconTintColor = modification.invoke(model.iconTintColor)
    }

    fun getIconTintColor() = model.iconTintColor

    inline fun setTextColor(modification: (oldColor: Int) -> Int) = this.apply {
        model.textColor = modification.invoke(model.textColor)
    }

    fun getTextColor() = model.textColor

    inline fun setTooltipBackgroundColor(modification: (oldColor: Int) -> Int) = this.apply {
        model.tooltipBackgroundColor = modification.invoke(model.tooltipBackgroundColor)
    }

    fun getTooltipBackgroundColor() = model.tooltipBackgroundColor

    inline fun setTooltipTextColor(modification: (oldColor: Int) -> Int) = this.apply {
        model.tooltipTextColor = modification.invoke(model.tooltipTextColor)
    }

    fun getTooltipTextColor() = model.tooltipTextColor

    inline fun setTooltipIconTintColor(modification: (oldColor: Int) -> Int) = this.apply {
        model.tooltipIconTintColor = modification.invoke(model.tooltipIconTintColor)
    }

    fun getTooltipIconTintColor() = model.tooltipIconTintColor

    inline fun setPopupMenuBackgroundColor(modification: (oldColor: Int) -> Int) = this.apply {
        model.popupMenuBackgroundColor = modification.invoke(model.popupMenuBackgroundColor)
    }

    fun getPopupMenuBackgroundColor() = model.popupMenuBackgroundColor

    inline fun setPopupMenuTextColor(modification: (oldColor: Int) -> Int) = this.apply {
        model.popupMenuTextColor = modification.invoke(model.popupMenuTextColor)
    }

    fun getPopupMenuTextColor() = model.popupMenuTextColor

    inline fun setPopupMenuIconTintColor(modification: (oldColor: Int) -> Int) = this.apply {
        model.popupMenuIconTintColor = modification.invoke(model.popupMenuIconTintColor)
    }

    fun getPopupMenuIconTintColor() = model.popupMenuIconTintColor

    fun build() = model
}