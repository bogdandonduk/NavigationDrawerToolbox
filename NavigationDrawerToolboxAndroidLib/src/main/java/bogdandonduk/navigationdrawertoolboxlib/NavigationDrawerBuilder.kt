package bogdandonduk.navigationdrawertoolboxlib

import android.content.Context
import android.graphics.Color
import androidx.annotation.IdRes
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import bogdandonduk.navigationdrawertoolboxlib.anatomy.callbacks.NavigationDrawerFragmentCallbacks
import bogdandonduk.navigationdrawertoolboxlib.anatomy.genericcolors.GenericColors
import bogdandonduk.navigationdrawertoolboxlib.anatomy.genericcolors.GenericColorsBuilder
import bogdandonduk.navigationdrawertoolboxlib.anatomy.header.base.NavigationDrawerHeader
import bogdandonduk.navigationdrawertoolboxlib.anatomy.header.simple.SimpleNavigationDrawerHeaderBuilder
import bogdandonduk.navigationdrawertoolboxlib.anatomy.languagetogglebutton.LanguageToggleButton
import bogdandonduk.navigationdrawertoolboxlib.anatomy.languagetogglebutton.LanguageToggleButtonBuilder
import bogdandonduk.navigationdrawertoolboxlib.anatomy.menu.base.NavigationDrawerMenu
import bogdandonduk.navigationdrawertoolboxlib.anatomy.menu.simple.SimpleNavigationDrawerMenuBuilder
import bogdandonduk.navigationdrawertoolboxlib.anatomy.settingsbutton.SettingsButton
import bogdandonduk.navigationdrawertoolboxlib.anatomy.settingsbutton.SettingsButtonBuilder
import bogdandonduk.navigationdrawertoolboxlib.anatomy.themetogglebutton.ThemeToggleButton
import bogdandonduk.navigationdrawertoolboxlib.anatomy.themetogglebutton.ThemeToggleButtonBuilder
import bogdandonduk.navigationdrawertoolboxlib.fragment.NavigationDrawerFragment

class NavigationDrawerBuilder internal constructor(context: Context, key: String) {
    @PublishedApi
    internal var model = NavigationDrawerToolbox.getSavedNavigationDrawerModel(key).run {
        if(this != null)
            NavigationDrawerModel(
                key = key,

                genericColors = genericColors,

                header = header,
                menu = menu,

                dividerLinesColor = dividerLinesColor,

                settingsButton = settingsButton,

                languageToggleButton = languageToggleButton,

                themeToggleButton = themeToggleButton,

                fragmentCallbacks = fragmentCallbacks
            ).also {
                it.navigationDrawerFragment = navigationDrawerFragment

                it.hostActivityOnTouchAction = hostActivityOnTouchAction

                it.saveModel = saveModel
            }
        else {
            NavigationDrawerConfig.RandomKeyGenerationUtils.transientKeyRegistry.add(key)

            NavigationDrawerModel(
                key = key,

                genericColors = GenericColorsBuilder().build(),

                header = SimpleNavigationDrawerHeaderBuilder().build(),

                menu = SimpleNavigationDrawerMenuBuilder().build(),

                dividerLinesColor = Color.DKGRAY,

                settingsButton = SettingsButtonBuilder().build(),

                languageToggleButton = LanguageToggleButtonBuilder(context).build(),

                themeToggleButton = ThemeToggleButtonBuilder().build(),

                fragmentCallbacks = null
            )
        }
    }

    inline fun setGenericColors(modification: (oldGenericColors: GenericColors) -> GenericColors) = this.apply {
        model.genericColors = modification.invoke(model.genericColors)
    }

    fun getGenericColors() = model.genericColors

    inline fun setHeader(modification: (oldHeader: NavigationDrawerHeader) -> NavigationDrawerHeader) = this.apply {
        model.header = modification.invoke(model.header)
    }

    fun getHeader() = model.header

    inline fun setMenu(modification: (oldMenu: NavigationDrawerMenu) -> NavigationDrawerMenu) = this.apply {
        model.menu = modification.invoke(model.menu)
    }

    fun getMenu() = model.menu

    inline fun setDividerLinesColor(modification: (oldColor: Int?) -> Int?) = this.apply {
        model.dividerLinesColor = modification.invoke(model.dividerLinesColor)
    }

    fun getDividerLinesColor() = model.dividerLinesColor

    inline fun setSettingsButton(modification: (oldSettingsButton: SettingsButton?) -> SettingsButton?) = this.apply {
        model.settingsButton = modification.invoke(model.settingsButton)
    }

    fun getSettingsButton() = model.settingsButton

    inline fun setLanguageToggleButton(modification: (oldLanguageToggleButton: LanguageToggleButton?) -> LanguageToggleButton?) = this.apply {
        model.languageToggleButton = modification.invoke(model.languageToggleButton)
    }

    fun getLanguageToggleButton() = model.languageToggleButton

    inline fun setThemeToggleButton(modification: (oldThemeToggleButton: ThemeToggleButton?) -> ThemeToggleButton?) = this.apply {
        model.themeToggleButton = modification.invoke(model.themeToggleButton)
    }

    fun getThemeToggleButton() = model.themeToggleButton

    fun setHostActivityOnTouchAction(hostActivityOnTouchAction: (() -> Unit)?) = this.apply {
        model.hostActivityOnTouchAction = hostActivityOnTouchAction
    }

    fun hasHostActivityOnTouchAction() = model.hostActivityOnTouchAction != null

    fun removeHostActivityOnTouchAction() = this.apply {
        model.hostActivityOnTouchAction = null
    }

    inline fun setFragmentCallbacks(modification: (oldCallbacks: NavigationDrawerFragmentCallbacks?) -> NavigationDrawerFragmentCallbacks?) = this.apply {
        model.fragmentCallbacks = modification.invoke(model.fragmentCallbacks)
    }

    fun getFragmentCallbacks() = model.fragmentCallbacks

    fun getKey() = model.key

    fun save(replaceIfPresent: Boolean = true) = this.apply {
        NavigationDrawerToolbox.saveNavigationDrawerModel(model.key, model, replaceIfPresent)
    }

    fun isLoaded() = model.navigationDrawerFragment != null

    fun isLoaded(fragmentManager: FragmentManager, containerViewId: Int) = model.navigationDrawerFragment != null && fragmentManager.findFragmentById(containerViewId) != null

    fun load(
        fragmentManager: FragmentManager,
        @IdRes containerViewId: Int,
        queueMode: NavigationDrawerConfig.QueueMode = NavigationDrawerConfig.QueueMode.CONTINUE_OLD_IF_SHOWING,
        saveInfo: Boolean = true
    ) = if(queueMode.name == NavigationDrawerConfig.QueueMode.DISPLAY_NEW.name
        || NavigationDrawerToolbox.getSavedNavigationDrawerModel(model.key) == null
        || NavigationDrawerToolbox.getSavedNavigationDrawerModel(model.key)!!.navigationDrawerFragment == null
    ) {
        model.saveModel = saveInfo

        save()

        val navigationFragment = NavigationDrawerFragment()

        try {
            fragmentManager.beginTransaction().apply {
                if(fragmentManager.findFragmentById(containerViewId) == null)
                    add(containerViewId, navigationFragment, model.key)
                else
                    replace(containerViewId, navigationFragment, model.key)
            }.commit()
        } catch(thr: Throwable) {

        }
    } else null

    fun update() = this.apply {
        save()

        NavigationDrawerToolbox.getSavedNavigationDrawerModel(model.key)?.navigationDrawerFragment?.draw()
    }

    fun dismissNavigationDrawerAllModals() = this.apply {
        NavigationDrawerToolbox.getSavedNavigationDrawerModel(model.key)?.navigationDrawerFragment?.dismissModals()
    }

    fun dismissNavigationDrawerTooltipByKey(tooltipKeyProviderAction: (allKeys: MutableList<String>) -> String) {
        NavigationDrawerToolbox.getSavedNavigationDrawerModel(model.key)?.navigationDrawerFragment?.let {
            it.dismissTooltipByKey(tooltipKeyProviderAction.invoke(
                mutableListOf<String>().apply {
                    it.getInitializedViewModel(it.viewModelStore).tooltipBuilders.forEach { (_, builder)->
                        add(builder.getKey())
                    }
                }
            ))
        }
    }

    fun dismissNavigationDrawerPopupMenuByKey(popupMenuKeyProviderAction: (allKeys: MutableList<String>) -> String) {
        NavigationDrawerToolbox.getSavedNavigationDrawerModel(model.key)?.navigationDrawerFragment?.let {
            it.dismissPopupMenuByKey(popupMenuKeyProviderAction.invoke(
                mutableListOf<String>().apply {
                    it.getInitializedViewModel(it.viewModelStore).popupMenuBuilders.forEach { (_, builder)->
                        add(builder.getKey())
                    }
                }
            ))
        }
    }

    fun restoreState() = this.apply {
        save()

        NavigationDrawerToolbox.getSavedNavigationDrawerModel(model.key)?.navigationDrawerFragment?.restoreState()
    }

    fun getBackgroundView() = model.navigationDrawerFragment?.viewBinding?.root

    fun geHeaderRootView() = model.navigationDrawerFragment?.headerLayoutRoot

    fun getMenuListView() = model.navigationDrawerFragment?.menuList

    fun getSettingsButtonView() = model.navigationDrawerFragment?.settingsButtonView

    fun getLanguageToggleButtonView() = model.navigationDrawerFragment?.languageToggleButtonView

    fun getThemeToggleButtonView() = model.navigationDrawerFragment?.themeToggleButtonView

    fun getThemeToggleButtonViewGroup() = model.navigationDrawerFragment?.viewBinding?.fragmentNavigationDrawerSettingsQuickAccessPanelThemeToggleButtonConstraintLayout

    fun getThemeToggleButtonIconImageView() = model.navigationDrawerFragment?.viewBinding?.fragmentNavigationDrawerSettingsQuickAccessPanelThemeToggleButtonIconImageView

    fun getSettingsButtonViewGroup() = model.navigationDrawerFragment?.viewBinding?.fragmentNavigationDrawerSettingsQuickAccessPanelSettingsButtonConstraintLayout

    fun getSettingsButtonIconImageView() = model.navigationDrawerFragment?.viewBinding?.fragmentNavigationDrawerSettingsQuickAccessPanelSettingsButtonIconImageView

    fun getLanguageToggleButtonViewGroup() = model.navigationDrawerFragment?.viewBinding?.fragmentNavigationDrawerSettingsQuickAccessPanelLanguageToggleButtonConstraintLayout

    fun getLanguageToggleButtonTextView() = model.navigationDrawerFragment?.viewBinding?.fragmentNavigationDrawerSettingsQuickAccessPanelLanguageToggleButtonTextTextView

    fun getLanguageToggleButtonIconImageView() = model.navigationDrawerFragment?.viewBinding?.fragmentNavigationDrawerSettingsQuickAccessPanelLanguageToggleButtonTextTextView
}