package bogdandonduk.navigationdrawertoolboxlib.anatomy.genericcolors

import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt

class GenericColors(
    var backgroundColor: Int,
    var backgroundDrawable: Drawable? = null,
    var backgroundDrawableTintColor: Int? = null,

    var iconTintColor: Int? = null,

    var textColor: Int,

    @ColorInt var tooltipBackgroundColor: Int,
    @ColorInt var tooltipTextColor: Int,
    @ColorInt var tooltipIconTintColor: Int,

    @ColorInt var popupMenuBackgroundColor: Int,
    @ColorInt var popupMenuTextColor: Int,
    @ColorInt var popupMenuIconTintColor: Int
)