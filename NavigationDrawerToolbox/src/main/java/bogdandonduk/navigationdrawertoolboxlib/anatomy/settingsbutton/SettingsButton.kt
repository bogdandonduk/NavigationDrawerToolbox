package bogdandonduk.navigationdrawertoolboxlib.anatomy.settingsbutton

import android.app.Activity
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.fragment.app.Fragment

class SettingsButton(
    var customIcon: Drawable? = null,
    var iconTintColor: Int? = null,
    var iconContentDescription: String? = null,
    var tooltipText: String,
    @ColorInt var tooltipTextColor: Int? = null,
    @ColorInt var tooltipBackgroundColor: Int? = null,
    var settingsActivityClass: Class<out Activity>? = null,
    var settingsActivityStartOptions: Bundle? = null,
    var extraOnLongClickAction: ((buttonView: View, buttonIcon: ImageView, rootView: View, fragment: Fragment) -> Unit)? = null,
    var extraOnClickAction: ((buttonView: View, buttonIcon: ImageView, rootView: View, fragment: Fragment) -> Unit)? = null
)