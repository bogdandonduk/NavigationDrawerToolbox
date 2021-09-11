package bogdandonduk.navigationdrawertoolboxlib.anatomy.menu.simple

import android.graphics.drawable.Drawable
import android.view.View

class SimpleNavigationDrawerMenuItemBuilder internal constructor() {
    @PublishedApi
    internal var menuItem: SimpleNavigationDrawerMenuItem = SimpleNavigationDrawerMenuItem(
        "Item",
        null,
        null,
        null,
        null,
        false,
    ) { _, _ ->

    }

    inline fun setText(modification: (oldText: CharSequence) -> CharSequence) = this.apply {
        menuItem.text = modification.invoke(menuItem.text)
    }

    fun getText() = menuItem.text

    inline fun setTextColor(modification: (oldColor: Int?) -> Int?) = this.apply {
        menuItem.textColor = modification.invoke(menuItem.textColor)
    }

    fun getTextColor() = menuItem.textColor

    inline fun setBackgroundColor(modification: (oldColor: Int?) -> Int?) = this.apply {
        menuItem.backgroundColor = modification.invoke(menuItem.backgroundColor)
    }

    fun getBackgroundColor() = menuItem.backgroundColor

    inline fun setIcon(modification: (oldIcon: Drawable?) -> Drawable?) = this.apply {
        menuItem.icon = modification.invoke(menuItem.icon)
    }

    fun getIcon() = menuItem.icon

    inline fun setIconTintColor(modification: (oldColor: Int?) -> Int?) = this.apply {
        menuItem.iconTintColor = modification.invoke(menuItem.iconTintColor)
    }

    fun getIconTintColor() = menuItem.iconTintColor

    inline fun setCurrentlyIn(modification: (oldValue: Boolean) -> Boolean) = this.apply {
        menuItem.currentlyIn = modification.invoke(menuItem.currentlyIn)
    }

    fun isCurrentlyIn() = menuItem.currentlyIn

    fun setOnClickAction(onClickAction: (text: String, rootView: View) -> Unit) = this.apply {
        menuItem.onClickAction = onClickAction
    }

    fun build() = menuItem
}