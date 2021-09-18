package bogdandonduk.navigationdrawertoolboxlib.anatomy.languagetogglebutton

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.fragment.app.Fragment
import bogdandonduk.navigationdrawertoolboxlib.base.Button
import bogdandonduk.uilanguagestoolboxlib.UILanguagesAppManager
import bogdandonduk.uilanguagestoolboxlib.UILanguagesFlagsHolder

class LanguageToggleButton(
    var text: String,
    @ColorInt var textColor: Int? = null,
    @ColorInt var dropdownIconTintColor: Int? = null,
    var dropdownIconContentDescription: String? = null,
    var tooltipText: String,

    @ColorInt var tooltipTextColor: Int? = null,
    @ColorInt var tooltipBackgroundColor: Int? = null,

    var popupMenuTextColor: Int? = null,
    var popupMenuBackgroundColor: Int? = null,
    @ColorInt var popupMenuIconTintColor: Int? = null,

    var uiLanguagesAppManager: UILanguagesAppManager,
    var uiLanguagesFlagsHolder: UILanguagesFlagsHolder,

    var currentLanguageProviderAction: (context: Context) -> String,
    var languageSetterAction: (context: Context, languageCode: String) -> Unit,

    var isAutoLanguageProviderAction: (context: Context) -> Boolean,
    var autoLanguageText: String,
    var autoLanguageSetterAction: (context: Context) -> Unit,

    var extraOnLongClickAction: ((buttonView: View, buttonDropdownIcon: ImageView, rootView: View, fragment: Fragment) -> Unit)? = null,
    var extraOnClickAction: ((buttonView: View, buttonDropdownIcon: ImageView, rootView: View, fragment: Fragment) -> Unit)? = null
)