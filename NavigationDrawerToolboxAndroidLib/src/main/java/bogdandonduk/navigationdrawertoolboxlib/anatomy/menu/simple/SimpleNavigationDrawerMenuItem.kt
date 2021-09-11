package bogdandonduk.navigationdrawertoolboxlib.anatomy.menu.simple

import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.ColorInt
import bogdandonduk.navigationdrawertoolboxlib.anatomy.menu.base.NavigationDrawerMenuItem

class SimpleNavigationDrawerMenuItem(
    var text: CharSequence,
    @ColorInt var textColor: Int? = null,

    @ColorInt var backgroundColor: Int? = null,

    var icon: Drawable? = null,
    @ColorInt var iconTintColor: Int? = null,

    var currentlyIn: Boolean = false,
    var onClickAction: (text: String, view: View) -> Unit
) : NavigationDrawerMenuItem()