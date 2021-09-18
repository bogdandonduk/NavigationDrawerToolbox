package bogdandonduk.navigationdrawertoolboxlib.fragment

import androidx.lifecycle.ViewModel
import bogdandonduk.navigationdrawertoolboxlib.NavigationDrawerToolbox
import bogdandonduk.navigationdrawertoolboxlib.extensions.KEY_POPUP_MENU_LANGUAGE_TOGGLE
import bogdandonduk.navigationdrawertoolboxlib.extensions.KEY_POPUP_MENU_THEME_TOGGLE
import bogdandonduk.navigationdrawertoolboxlib.extensions.KEY_TOOLTIP_LANGUAGE_TOGGLE
import bogdandonduk.popupmenutoolboxlib.PopupMenuBuilder
import bogdandonduk.popupmenutoolboxlib.PopupMenuConfig
import bogdandonduk.popupmenutoolboxlib.PopupMenuToolbox
import bogdandonduk.popupmenutoolboxlib.PopupMenusPersistableHandler
import bogdandonduk.tooltiptoolboxlib.TooltipBuilder
import bogdandonduk.tooltiptoolboxlib.TooltipConfig
import bogdandonduk.tooltiptoolboxlib.TooltipToolbox
import bogdandonduk.tooltiptoolboxlib.TooltipsPersistableHandler

class NavigationDrawerFragmentViewModel(var key: String) : ViewModel(), PopupMenusPersistableHandler, TooltipsPersistableHandler {
    override val popupMenuBuilders = mutableMapOf<String, PopupMenuBuilder>().apply {
        this[PopupMenuConfig.KeysExtensionVocabulary.KEY_POPUP_MENU_LANGUAGE_TOGGLE] = PopupMenuToolbox.buildPopupMenu()
        this[PopupMenuConfig.KeysExtensionVocabulary.KEY_POPUP_MENU_THEME_TOGGLE] = PopupMenuToolbox.buildPopupMenu()
    }

    override val tooltipBuilders = mutableMapOf<String, TooltipBuilder>().apply {
        this[TooltipConfig.KeysExtensionVocabulary.KEY_TOOLTIP_SETTINGS] = TooltipToolbox.buildTooltip()
        this[TooltipConfig.KeysExtensionVocabulary.KEY_TOOLTIP_LANGUAGE_TOGGLE] = TooltipToolbox.buildTooltip()
    }

    var menuListStateKey: String? = null

    override fun onCleared() {
        NavigationDrawerToolbox.getSavedNavigationDrawerModel(key)?.run {
            if(!saveModel)
                NavigationDrawerToolbox.deleteNavigationDrawerInfo(key)
        }

        PopupMenuToolbox.dismissPopupMenu(popupMenuBuilders[PopupMenuConfig.KeysExtensionVocabulary.KEY_POPUP_MENU_LANGUAGE_TOGGLE]!!.getKey())
        PopupMenuToolbox.dismissPopupMenu(popupMenuBuilders[PopupMenuConfig.KeysExtensionVocabulary.KEY_POPUP_MENU_THEME_TOGGLE]!!.getKey())

        TooltipToolbox.dismissTooltip(tooltipBuilders[TooltipConfig.KeysExtensionVocabulary.KEY_TOOLTIP_SETTINGS]!!.getKey())
        TooltipToolbox.dismissTooltip(tooltipBuilders[TooltipConfig.KeysExtensionVocabulary.KEY_TOOLTIP_LANGUAGE_TOGGLE]!!.getKey())
    }
}