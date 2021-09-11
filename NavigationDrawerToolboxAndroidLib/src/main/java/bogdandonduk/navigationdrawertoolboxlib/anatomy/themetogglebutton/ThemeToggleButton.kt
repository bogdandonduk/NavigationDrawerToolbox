package bogdandonduk.navigationdrawertoolboxlib.anatomy.themetogglebutton

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.fragment.app.Fragment

class ThemeToggleButton(
    var customIcon: Drawable? = null,
    @ColorInt var iconTintColor: Int? = null,
    var iconContentDescription: String? = null,

    var popupMenuTextColor: Int? = null,
    var popupMenuBackgroundColor: Int? = null,
    @ColorInt var popupMenuIconTintColor: Int? = null,

    var popupMenuLightThemeText: String,
    var popupMenuDarkThemeText: String,

    var isDarkThemeProviderAction: (context: Context) -> Boolean,
    var themeSetterAction: (context: Context, darkTheme: Boolean) -> Unit,

    var autoThemeText: String,
    var isAutoThemeProviderAction: (context: Context) -> Boolean,
    var autoThemeSetterAction: (context: Context) -> Unit,

    var extraOnLongClickAction: ((buttonView: View, buttonIcon: ImageView, rootView: View, fragment: Fragment) -> Unit)? = null,
    var extraOnClickAction: ((buttonView: View, buttonIcon: ImageView, rootView: View, fragment: Fragment) -> Unit)? = null,
)