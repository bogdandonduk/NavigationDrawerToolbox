package bogdandonduk.navigationdrawertoolboxlib.anatomy.menu.simple

import androidx.annotation.ColorInt
import bogdandonduk.navigationdrawertoolboxlib.anatomy.menu.base.NavigationDrawerMenu

class SimpleNavigationDrawerMenu(
    var menuItems: MutableList<SimpleNavigationDrawerMenuItem>,

    @ColorInt var backgroundColor: Int? = null
) : NavigationDrawerMenu()