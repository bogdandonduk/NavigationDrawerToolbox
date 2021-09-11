package bogdandonduk.navigationdrawertoolboxlib.anatomy.languagetogglebutton

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import bogdandonduk.uilanguagestoolboxlib.UILanguagesAppManager
import bogdandonduk.uilanguagestoolboxlib.UILanguagesFlagsHolder
import bogdandonduk.uilanguagestoolboxlib.UILanguagesToolbox

class LanguageToggleButtonBuilder(context: Context) {
    @PublishedApi
    internal var model = LanguageToggleButton(
        text = "English",
        tooltipText = "App language",
        uiLanguagesAppManager = UILanguagesAppManager("en"),
        uiLanguagesFlagsHolder = UILanguagesToolbox.getUILanguagesFlagsHolderPopularLanguagesInstance(context),
        currentLanguageProviderAction = {
            "en"
        },
        languageSetterAction = { _: Context, _: String ->

        },
        isAutoLanguageProviderAction = {
            true
        },
        autoLanguageText = "Auto",
        autoLanguageSetterAction = {

        },
        extraOnLongClickAction = null,
        extraOnClickAction = null
    )

    inline fun setText(modification: (oldText: String) -> String) = this.apply {
        model.text = modification.invoke(model.text)
    }

    fun getText() = model.text

    inline fun setTextColor(modification: (oldColor: Int?) -> Int?) = this.apply {
        model.textColor = modification.invoke(model.textColor)
    }

    fun getTextColor() = model.textColor

    inline fun setDropdownIconTintColor(modification: (oldColor: Int?) -> Int?) = this.apply {
        model.dropdownIconTintColor = modification.invoke(model.dropdownIconTintColor)
    }

    fun getDropdownIconTintColor() = model.dropdownIconTintColor

    inline fun setDropdownIconContentDescription(modification: (oldDescription: String?) -> String?) = this.apply {
        model.dropdownIconContentDescription = modification.invoke(model.dropdownIconContentDescription)
    }

    fun getDropdownIconContentDescription() = model.dropdownIconContentDescription

    inline fun setTooltipText(modification: (oldText: String) -> String) = this.apply {
        model.tooltipText = modification.invoke(model.tooltipText)
    }

    fun getTooltipText() = model.tooltipText

    inline fun setTooltipTextColor(modification: (oldColor: Int?) -> Int?) = this.apply {
        model.tooltipTextColor = modification.invoke(model.tooltipTextColor)
    }

    fun getTooltipTextColor() = model.tooltipTextColor

    inline fun setTooltipBackgroundColor(modification: (oldColor: Int?) -> Int?) = this.apply {
        model.tooltipBackgroundColor = modification.invoke(model.tooltipBackgroundColor)
    }

    fun getTooltipBackgroundColor() = model.tooltipBackgroundColor

    inline fun setPopupMenuTextColor(modification: (oldColor: Int?) -> Int?) = this.apply {
        model.popupMenuTextColor = modification.invoke(model.popupMenuTextColor)
    }

    fun getPopupMenuTextColor() = model.popupMenuTextColor

    inline fun setPopupMenuBackgroundColor(modification: (oldColor: Int?) -> Int?) = this.apply {
        model.popupMenuBackgroundColor = modification.invoke(model.popupMenuBackgroundColor)
    }

    fun getPopupMenuBackgroundColor() = model.popupMenuBackgroundColor

    inline fun setPopupMenuIconTintColor(modification: (oldColor: Int?) -> Int?) = this.apply {
        model.popupMenuIconTintColor = modification.invoke(model.popupMenuIconTintColor)
    }

    fun getPopupMenuIconTintColor() = model.popupMenuIconTintColor

    inline fun setUILanguagesAppManager(modification: (oldManager: UILanguagesAppManager) -> UILanguagesAppManager) = this.apply {
        model.uiLanguagesAppManager = modification.invoke(model.uiLanguagesAppManager)
    }

    fun getUILanguagesAppManager() = model.uiLanguagesAppManager

    inline fun setUILanguagesFlagsHolder(modification: (oldManager: UILanguagesFlagsHolder) -> UILanguagesFlagsHolder) = this.apply {
        model.uiLanguagesFlagsHolder = modification.invoke(model.uiLanguagesFlagsHolder)
    }

    fun getUILanguagesFlagsHolder() = model.uiLanguagesAppManager

    fun setCurrentLanguageProviderAction(currentLanguageProviderAction: (context: Context) -> String) = this.apply {
        model.currentLanguageProviderAction = currentLanguageProviderAction
    }

    fun setLanguageSetterAction(languageSetterAction: (context: Context, languageCode: String) -> Unit) = this.apply {
        model.languageSetterAction = languageSetterAction
    }

    fun setIsAutoLanguageProviderAction(isAutoLanguageProviderAction: (context: Context) -> Boolean) = this.apply {
        model.isAutoLanguageProviderAction = isAutoLanguageProviderAction
    }

    fun setAutoLanguageText(modification: (oldText: String) -> String) = this.apply {
        model.autoLanguageText = modification.invoke(model.autoLanguageText)
    }

    fun setAutoLanguageSetterAction(autoLanguageSetterAction: (context: Context) -> Unit) = this.apply {
        model.autoLanguageSetterAction = autoLanguageSetterAction
    }

    fun setExtraOnClickAction(extraOnClickAction: ((buttonView: View, buttonIcon: ImageView, rootView: View, fragment: Fragment) -> Unit)?) = this.apply {
        model.extraOnClickAction = extraOnClickAction
    }

    fun hasExtraOnClickAction() = model.extraOnClickAction != null

    fun removeExtraOnClickAction() = this.apply {
        model.extraOnClickAction = null
    }

    fun setExtraOnLongClickAction(extraOnLongClickAction: ((buttonView: View, buttonIcon: ImageView, rootView: View, fragment: Fragment) -> Unit)?) = this.apply {
        model.extraOnLongClickAction = extraOnLongClickAction
    }

    fun hasExtraOnLongClickAction() = model.extraOnLongClickAction != null

    fun removeExtraOnLongClickAction() = this.apply {
        model.extraOnLongClickAction = null
    }

    fun build() = model
}