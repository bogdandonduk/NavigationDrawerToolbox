package bogdandonduk.navigationdrawertoolboxlib

import android.content.Context
import androidx.annotation.IdRes
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import bogdandonduk.navigationdrawertoolboxlib.anatomy.genericcolors.GenericColorsBuilder
import bogdandonduk.navigationdrawertoolboxlib.anatomy.header.simple.SimpleNavigationDrawerHeaderBuilder
import bogdandonduk.navigationdrawertoolboxlib.anatomy.languagetogglebutton.LanguageToggleButtonBuilder
import bogdandonduk.navigationdrawertoolboxlib.anatomy.menu.simple.SimpleNavigationDrawerMenuBuilder
import bogdandonduk.navigationdrawertoolboxlib.anatomy.settingsbutton.SettingsButtonBuilder
import bogdandonduk.navigationdrawertoolboxlib.anatomy.themetogglebutton.ThemeToggleButtonBuilder

object NavigationDrawerToolbox {
    private val savedNavigationDrawerModels = mutableMapOf<String, NavigationDrawerModel>()

    internal fun getSavedNavigationDrawerModel(key: String) = savedNavigationDrawerModels[key]

    @Synchronized
    internal fun saveNavigationDrawerModel(key: String, model: NavigationDrawerModel, replaceIfPresent: Boolean = true) {
        if(replaceIfPresent || !savedNavigationDrawerModels.containsKey(key))
            savedNavigationDrawerModels[key] = model
    }

    fun dismissNavigationDrawer(activity: FragmentActivity, key: String, @IdRes containerViewId: Int, deleteInfo: Boolean = true) {
        savedNavigationDrawerModels[key]?.run {
            activity.supportFragmentManager.popBackStack(containerViewId, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }

        if(deleteInfo) savedNavigationDrawerModels.remove(key)
    }

    @Synchronized
    fun deleteNavigationDrawerInfo(key: String) {
        savedNavigationDrawerModels.remove(key)
    }

    fun buildNavigationDrawer(context: Context, key: String = NavigationDrawerConfig.RandomKeyGenerationUtils.generate()) = NavigationDrawerBuilder(context, key)

    fun buildSimpleNavigationDrawerHeader() = SimpleNavigationDrawerHeaderBuilder()

    fun buildSimpleNavigationDrawerMenu() = SimpleNavigationDrawerMenuBuilder()

    fun buildGenericColors() = GenericColorsBuilder()

    fun buildSettingsButton() = SettingsButtonBuilder()

    fun buildLanguageToggleButton(context: Context) = LanguageToggleButtonBuilder(context)

    fun buildThemeToggleButton() = ThemeToggleButtonBuilder()

    fun getCallbacks(key: String) = savedNavigationDrawerModels[key]?.navigationDrawerFragment as NavigationDrawerConfig.Callbacks
}