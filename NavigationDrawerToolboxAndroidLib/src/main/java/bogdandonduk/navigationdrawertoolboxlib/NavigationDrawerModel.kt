package bogdandonduk.navigationdrawertoolboxlib

import androidx.annotation.ColorInt
import bogdandonduk.navigationdrawertoolboxlib.anatomy.genericcolors.GenericColors
import bogdandonduk.navigationdrawertoolboxlib.anatomy.header.base.NavigationDrawerHeader
import bogdandonduk.navigationdrawertoolboxlib.anatomy.languagetogglebutton.LanguageToggleButton
import bogdandonduk.navigationdrawertoolboxlib.anatomy.menu.base.NavigationDrawerMenu
import bogdandonduk.navigationdrawertoolboxlib.anatomy.settingsbutton.SettingsButton
import bogdandonduk.navigationdrawertoolboxlib.anatomy.themetogglebutton.ThemeToggleButton
import bogdandonduk.navigationdrawertoolboxlib.fragment.NavigationDrawerFragment

@PublishedApi
internal class NavigationDrawerModel(
    var key: String,

    var genericColors: GenericColors,

    var header: NavigationDrawerHeader,
    var menu: NavigationDrawerMenu,

    @ColorInt var dividerLinesColor: Int?,

    var settingsButton: SettingsButton?,

    var languageToggleButton: LanguageToggleButton?,

    var themeToggleButton: ThemeToggleButton?
) {
    var navigationDrawerFragment: NavigationDrawerFragment? = null

    var hostActivityOnTouchAction: (() -> Unit)? = null

    var saveModel = true
}