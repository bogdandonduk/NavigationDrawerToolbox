package bogdandonduk.navigationdrawertoolboxlib.anatomy.header.simple

import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.ColorInt
import androidx.fragment.app.Fragment
import bogdandonduk.navigationdrawertoolboxlib.anatomy.header.base.NavigationDrawerHeader

class SimpleNavigationDrawerHeader(
    @ColorInt var backgroundColor: Int?,
    var backgroundDrawable: Drawable?,
    @ColorInt var backgroundDrawableTintColor: Int?,

    var appNameText: CharSequence,
    @ColorInt var appNameTextColor: Int?,
    var appVersionText: CharSequence?,
    var appVersionTextColor: Int?,

    var appIcon: Drawable?,
    var appIconByteArray: ByteArray?,
    @ColorInt var appIconTintColor: Int? = null,

    var appIconContentDescription: String? = null,

    var onClickAction: ((headerRootView: View, rootView: View, fragment: Fragment) -> Unit)?,
    var onLongClickAction: ((headerRootView: View, rootView: View, fragment: Fragment) -> Unit)?
) : NavigationDrawerHeader()