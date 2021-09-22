package bogdandonduk.navigationdrawertoolboxlib.fragment

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import bogdandonduk.commontoolboxlib.CommonToolbox
import bogdandonduk.listtoolboxlib.ListToolbox
import bogdandonduk.navigationdrawertoolboxlib.NavigationDrawerConfig
import bogdandonduk.navigationdrawertoolboxlib.NavigationDrawerToolbox
import bogdandonduk.navigationdrawertoolboxlib.R
import bogdandonduk.navigationdrawertoolboxlib.anatomy.header.simple.SimpleNavigationDrawerHeader
import bogdandonduk.navigationdrawertoolboxlib.anatomy.menu.simple.SimpleNavigationDrawerMenu
import bogdandonduk.navigationdrawertoolboxlib.anatomy.menu.simple.SimpleNavigationDrawerMenuAdapter
import bogdandonduk.navigationdrawertoolboxlib.databinding.FragmentNavigationDrawerBinding
import bogdandonduk.navigationdrawertoolboxlib.databinding.LayoutSimpleNavigationDrawerHeaderBinding
import bogdandonduk.navigationdrawertoolboxlib.extensions.KEY_POPUP_MENU_LANGUAGE_TOGGLE
import bogdandonduk.navigationdrawertoolboxlib.extensions.KEY_POPUP_MENU_THEME_TOGGLE
import bogdandonduk.navigationdrawertoolboxlib.extensions.KEY_TOOLTIP_LANGUAGE_TOGGLE
import bogdandonduk.popupmenutoolboxlib.PopupMenuConfig
import bogdandonduk.popupmenutoolboxlib.PopupMenuItem
import bogdandonduk.popupmenutoolboxlib.PopupMenuToolbox
import bogdandonduk.popupmenutoolboxlib.PopupMenusHandler
import bogdandonduk.tooltiptoolboxlib.TooltipConfig
import bogdandonduk.tooltiptoolboxlib.TooltipToolbox
import bogdandonduk.tooltiptoolboxlib.TooltipsHandler
import bogdandonduk.uilanguagestoolboxlib.UILanguagesAppManager
import bogdandonduk.viewdatabindingwrapperslib.BaseViewBindingHandlerFragment
import bogdandonduk.viewmodelwrapperslib.automatic.SingleAutomaticInitializationWithInitializationViewModelHandler
import bogdandonduk.viewmodelwrapperslib.automatic.SingleAutomaticInitializationWithInstanceViewModelHandler
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import top.defaults.drawabletoolbox.DrawableBuilder

internal class NavigationDrawerFragment : BaseViewBindingHandlerFragment<FragmentNavigationDrawerBinding>({ layoutInflater: LayoutInflater, viewGroup: ViewGroup? ->
    FragmentNavigationDrawerBinding.inflate(layoutInflater, viewGroup, false)
}),
    SingleAutomaticInitializationWithInitializationViewModelHandler<NavigationDrawerFragmentViewModel>,
    NavigationDrawerConfig.Callbacks,
    TooltipsHandler,
    PopupMenusHandler {

    lateinit var key: String

    override var viewModelInitialization = {
        NavigationDrawerFragmentViewModel(key)
    }

    var rippleColor: Int? = null

    var languageToggleTooltipAppendix = ""

    var headerLayoutRoot: View? = null

    var rootView: View? = null

    var menuList: RecyclerView? = null

    var settingsButtonView: View? = null
    var themeToggleButtonView: View? = null
    var languageToggleButtonView: View? = null

    override fun onDestroy() {
        super.onDestroy()

        getInitializedViewModel(viewModelStore).menuListStateKey = ListToolbox.saveListState(this, recyclerView = viewBinding.fragmentNavigationDrawerMenuRecyclerView)

        NavigationDrawerToolbox.getSavedNavigationDrawerModel(key)?.navigationDrawerFragment = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        key = tag!!

        NavigationDrawerToolbox.getSavedNavigationDrawerModel(key)?.navigationDrawerFragment = this

        getInitializedViewModel(viewModelStore)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION

        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getInitializedViewModel(viewModelStore)

        draw()
    }

    fun draw() {
        NavigationDrawerToolbox.getSavedNavigationDrawerModel(key)?.let {
            viewBinding.run {
                rootView = root

                rippleColor = CommonToolbox.getRippleColorByLuminance(requireActivity(), if(it.genericColors.backgroundDrawable != null) {
                    root.background = it.genericColors.backgroundDrawable

                    if(it.genericColors.backgroundDrawableTintColor != null) CommonToolbox.applyColorFilter(root.background, it.genericColors.backgroundDrawableTintColor!!)

                    (root.background as ColorDrawable).color
                } else {
                    root.setBackgroundColor(it.genericColors.backgroundColor)

                    it.genericColors.backgroundColor
                })

                fragmentNavigationDrawerHeaderRootAppBarLayout.outlineProvider = null

                /** HEADER */
                it.header.let { header ->
                    if(header is SimpleNavigationDrawerHeader) {
                        fragmentNavigationDrawerHeaderCollapsingToolbarLayout.addView(
                            LayoutSimpleNavigationDrawerHeaderBinding.inflate(layoutInflater, fragmentNavigationDrawerHeaderCollapsingToolbarLayout, false).apply {
                                if(header.backgroundDrawable != null) {
                                    root.background = DrawableBuilder()
                                        .baseDrawable(header.backgroundDrawable!!)
                                        .ripple()
                                        .rippleColor(CommonToolbox.getRippleColorByLuminance(requireActivity(), header.backgroundDrawableTintColor ?: (header.backgroundDrawable as ColorDrawable).color))
                                        .build()

                                    if(header.backgroundDrawableTintColor != null)
                                        CommonToolbox.applyColorFilter(root.background, header.backgroundDrawableTintColor!!)
                                    else if(it.genericColors.backgroundDrawableTintColor != null)
                                        CommonToolbox.applyColorFilter(root.background, it.genericColors.backgroundDrawableTintColor!!)
                                } else
                                    root.background = DrawableBuilder()
                                        .solidColor(header.backgroundColor ?: it.genericColors.backgroundColor)
                                        .ripple()
                                        .rippleColor(CommonToolbox.getRippleColorByLuminance(requireActivity(), (header.backgroundColor ?: it.genericColors.backgroundColor)))
                                        .build()

                                if(header.appIcon != null || header.appIconByteArray != null) {
                                    layoutSimpleNavigationDrawerHeaderIconImageView.run {
                                        visibility = View.VISIBLE

                                        Glide.with(requireActivity())
                                            .load(header.appIcon ?: header.appIconByteArray)
                                            .priority(Priority.HIGH)
                                            .into(this)
                                    }

                                    if(header.appIconTintColor != null)
                                        CommonToolbox.applyColorFilter(layoutSimpleNavigationDrawerHeaderIconImageView.drawable, header.appIconTintColor!!)

                                    layoutSimpleNavigationDrawerHeaderIconImageView.contentDescription = header.appIconContentDescription
                                }

                                layoutSimpleNavigationDrawerHeaderAppNameTextTextView.run {
                                    text = header.appNameText
                                    setTextColor(header.appNameTextColor ?: it.genericColors.textColor)
                                }

                                if(header.appVersionText != null) {
                                    layoutSimpleNavigationDrawerHeaderVersionNameTextContainerLinearLayout.visibility = View.VISIBLE

                                    layoutSimpleNavigationDrawerHeaderVersionNameTextTextView.run {
                                        text = header.appVersionText
                                        setTextColor(header.appVersionTextColor ?: it.genericColors.textColor)
                                    }
                                }

                                layoutSimpleNavigationDrawerHeaderDividerLineLinearLayout.setBackgroundColor(it.dividerLinesColor ?: rippleColor!!)

                                if(header.onClickAction != null) root.setOnClickListener {
                                    header.onClickAction!!.invoke(it, this.root, this@NavigationDrawerFragment)
                                }

                                if(header.onLongClickAction != null) root.setOnLongClickListener {
                                    header.onLongClickAction!!.invoke(it, this.root, this@NavigationDrawerFragment)

                                    true
                                }
                            }.root.apply {
                                headerLayoutRoot = this
                            }
                        )
                    }
                }

                /** MENU */
                it.menu.let { menu ->
                    if(menu is SimpleNavigationDrawerMenu) {
                        fragmentNavigationDrawerMenuRecyclerView.run {
                            if(menu.backgroundColor != null)
                                setBackgroundColor(menu.backgroundColor!!)

                            ListToolbox.initializeList(requireActivity(), fragmentNavigationDrawerMenuRecyclerView,
                                SimpleNavigationDrawerMenuAdapter(
                                    menu.menuItems,
                                    it.genericColors,
                                    this@NavigationDrawerFragment,
                                    it.hostActivityOnTouchAction,
                                    requireActivity()
                                )
                            )

                            getInitializedViewModel(viewModelStore).menuListStateKey?.run {
                                ListToolbox.restoreSavedListState(this, viewBinding.fragmentNavigationDrawerMenuRecyclerView)
                            }

                            menuList = this
                        }
                    }
                }

                fragmentNavigationDrawerSettingsQuickAccessPanelConstraintLayout.setBackgroundColor(it.genericColors.backgroundColor)

                /** SETTINGS BUTTON */
                if(it.settingsButton != null)
                    fragmentNavigationDrawerSettingsQuickAccessPanelSettingsButtonConstraintLayout.run {
                        visibility = View.VISIBLE

                        fragmentNavigationDrawerSettingsQuickAccessPanelSettingsButtonIconImageView.run {
                            if(it.settingsButton!!.customIcon != null) setImageDrawable(it.settingsButton!!.customIcon)

                            if(it.settingsButton!!.iconTintColor != null)
                                CommonToolbox.applyColorFilter(drawable, it.settingsButton!!.iconTintColor!!)
                            else if(it.genericColors.iconTintColor != null)
                                CommonToolbox.applyColorFilter(drawable, it.genericColors.iconTintColor!!)

                            contentDescription = it.settingsButton!!.iconContentDescription
                        }

                        background = DrawableBuilder()
                            .cornerRadius(1000000)
                            .ripple()
                            .rippleColor(rippleColor!!)
                            .build()

                        setOnClickListener { view ->
                            if(it.settingsButton!!.settingsActivityClass != null)
                                CommonToolbox.openActivity(requireActivity(), it.settingsButton!!.settingsActivityClass!!, it.settingsButton!!.settingsActivityStartOptions)

                            it.settingsButton!!.extraOnClickAction?.invoke(view, fragmentNavigationDrawerSettingsQuickAccessPanelSettingsButtonIconImageView, viewBinding.root, this@NavigationDrawerFragment)
                        }

                        setOnLongClickListener { view ->
                            CommonToolbox.vibrateOneShot(requireActivity())

                            buildSettingsTooltip().show(requireActivity(), view)

                            true
                        }

                        settingsButtonView = this
                    }

                /** LANGUAGE TOGGLE BUTTON */
                if(it.languageToggleButton != null)
                    fragmentNavigationDrawerSettingsQuickAccessPanelLanguageToggleButtonConstraintLayout.run {
                        visibility = View.VISIBLE

                        Glide.with(requireActivity())
                            .load(it.languageToggleButton!!.uiLanguagesFlagsHolder.getFlag(it.languageToggleButton!!.currentLanguageProviderAction.invoke(requireActivity())))
                            .priority(Priority.HIGH)
                            .into(fragmentNavigationDrawerSettingsQuickAccessPanelLanguageToggleButtonIconImageView)

                        fragmentNavigationDrawerSettingsQuickAccessPanelLanguageToggleButtonTextTextView.run {
                            text = if(it.languageToggleButton!!.isAutoLanguageProviderAction.invoke(requireActivity()))
                                it.languageToggleButton!!.autoLanguageText
                            else {
                                val languageCode = it.languageToggleButton!!.currentLanguageProviderAction.invoke(requireActivity())

                                UILanguagesAppManager.getDisplayName(languageCode, languageCode, true)
                            }

                            setTextColor(it.languageToggleButton!!.textColor ?: it.genericColors.textColor)

                            post {
                                if(layout != null)
                                    languageToggleTooltipAppendix =
                                        if(layout!!.getEllipsisCount(1) > 0)
                                            ": $text"
                                        else ""
                            }
                        }

                        fragmentNavigationDrawerSettingsQuickAccessPanelLanguageToggleButtonDropdownIconImageView.run {
                            if(it.languageToggleButton!!.dropdownIconTintColor != null)
                                CommonToolbox.applyColorFilter(drawable, it.languageToggleButton!!.dropdownIconTintColor!!)
                            else if(it.genericColors.iconTintColor != null)
                                CommonToolbox.applyColorFilter(drawable, it.genericColors.iconTintColor!!)

                            contentDescription = it.languageToggleButton!!.dropdownIconContentDescription
                        }

                        background = DrawableBuilder()
                            .cornerRadius(1000000)
                            .ripple()
                            .rippleColor(rippleColor!!)
                            .build()

                        setOnClickListener { view ->
                            dismissTooltips()

                            dismissPopupMenus(getInitializedViewModel(viewModelStore).popupMenuBuilders[PopupMenuConfig.KeysExtensionVocabulary.KEY_POPUP_MENU_LANGUAGE_TOGGLE]!!.getKey())

                            buildLanguageTogglePopupMenu().show(requireActivity(), view)
                        }

                        setOnLongClickListener { view ->
                            CommonToolbox.vibrateOneShot(requireActivity())

                            dismissTooltips()

                            dismissPopupMenus(getInitializedViewModel(viewModelStore).popupMenuBuilders[PopupMenuConfig.KeysExtensionVocabulary.KEY_POPUP_MENU_LANGUAGE_TOGGLE]!!.getKey())

                            buildLanguageToggleTooltip().show(requireActivity(), view)

                            true
                        }

                        languageToggleButtonView = this
                    }

                /** THEME TOGGLE BUTTON */
                if(it.themeToggleButton != null)
                    fragmentNavigationDrawerSettingsQuickAccessPanelThemeToggleButtonConstraintLayout.run {
                        visibility = View.VISIBLE

                        fragmentNavigationDrawerSettingsQuickAccessPanelThemeToggleButtonIconImageView.run {
                            setImageResource(if(it.themeToggleButton!!.isDarkThemeProviderAction.invoke(requireActivity())) R.drawable.ic_baseline_wb_sunny_24 else R.drawable.ic_baseline_nights_stay_24)

                            if(it.themeToggleButton!!.iconTintColor != null)
                                CommonToolbox.applyColorFilter(drawable, it.themeToggleButton!!.iconTintColor!!)
                            else if(it.genericColors.iconTintColor != null)
                                CommonToolbox.applyColorFilter(drawable, it.genericColors.iconTintColor!!)

                            contentDescription = it.themeToggleButton!!.iconContentDescription
                        }

                        background = DrawableBuilder()
                            .cornerRadius(1000000)
                            .ripple()
                            .rippleColor(rippleColor!!)
                            .build()

                        setOnClickListener { view ->
                            it.themeToggleButton!!.isDarkThemeProviderAction.invoke(requireActivity()).let { darkTheme ->
                                fragmentNavigationDrawerSettingsQuickAccessPanelThemeToggleButtonIconImageView.run {
                                    setImageResource(if(!darkTheme) R.drawable.ic_baseline_nights_stay_24 else R.drawable.ic_baseline_wb_sunny_24)

                                    if(it.themeToggleButton!!.iconTintColor != null)
                                        CommonToolbox.applyColorFilter(drawable, it.themeToggleButton!!.iconTintColor!!)
                                    else if(it.genericColors.iconTintColor != null)
                                        CommonToolbox.applyColorFilter(drawable, it.genericColors.iconTintColor!!)
                                }

                                it.themeToggleButton!!.themeSetterAction.invoke(requireActivity(), !darkTheme)

                                restoreState()

                                it.themeToggleButton!!.extraOnClickAction?.invoke(view, fragmentNavigationDrawerSettingsQuickAccessPanelThemeToggleButtonIconImageView, root, this@NavigationDrawerFragment)
                            }
                        }

                        setOnLongClickListener { view ->
                            CommonToolbox.vibrateOneShot(requireActivity())

                            dismissModals()

                            buildThemeTogglePopupMenu().show(requireActivity(), view)

                            true
                        }

                        themeToggleButtonView = this
                    }

                /** DIVIDER LINES */
                fragmentNavigationDrawerSettingsQuickAccessPanelDividerLineLinearLayout.setBackgroundColor(it.dividerLinesColor ?: rippleColor!!)

                /** MODALS MANUAL OUTSIDE TOUCH DISMISSERS */
                CommonToolbox.setOnTouchListeners(
                    { _, event ->
                        if(event.action == MotionEvent.ACTION_DOWN) {
                            dismissModals()

                            it.hostActivityOnTouchAction?.invoke()
                        }
                    },
                    headerLayoutRoot,
                    fragmentNavigationDrawerMenuRecyclerView,
                    fragmentNavigationDrawerSettingsQuickAccessPanelConstraintLayout,
                    fragmentNavigationDrawerSettingsQuickAccessPanelSettingsButtonConstraintLayout
                )
            }
        }
    }

    fun restoreState() {
        continueTooltips()

        continuePopupMenus()
    }

    private fun buildSettingsTooltip() = getInitializedViewModel(viewModelStore).tooltipBuilders[TooltipConfig.KeysExtensionVocabulary.KEY_TOOLTIP_SETTINGS]!!
        .apply {
            if(this@NavigationDrawerFragment::key.isInitialized) {
                NavigationDrawerToolbox.getSavedNavigationDrawerModel(key)!!.run {
                    setText {
                        settingsButton!!.tooltipText
                    }

                    setTextColor {
                        settingsButton!!.tooltipTextColor ?: genericColors.tooltipTextColor
                    }

                    setBackgroundColor { _ ->
                        settingsButton!!.tooltipBackgroundColor ?: genericColors.tooltipBackgroundColor
                    }

                    if(rippleColor != null)
                        setStrokeColor { _ ->
                            rippleColor!!
                        }

                    setOnClickAction { _, _ ->
                        TooltipToolbox.dismissTooltip(getKey())

                        viewBinding.fragmentNavigationDrawerSettingsQuickAccessPanelSettingsButtonConstraintLayout.performClick()

                        settingsButton!!.extraOnClickAction?.invoke(
                            viewBinding.fragmentNavigationDrawerSettingsQuickAccessPanelSettingsButtonConstraintLayout,
                            viewBinding.fragmentNavigationDrawerSettingsQuickAccessPanelSettingsButtonIconImageView,
                            viewBinding.root,
                            this@NavigationDrawerFragment
                        )
                    }
                }
            }
        }

    private fun buildLanguageTogglePopupMenu() = getInitializedViewModel(viewModelStore).popupMenuBuilders[PopupMenuConfig.KeysExtensionVocabulary.KEY_POPUP_MENU_LANGUAGE_TOGGLE]!!
        .apply {
            if(this@NavigationDrawerFragment::key.isInitialized)
                NavigationDrawerToolbox.getSavedNavigationDrawerModel(key)!!.run {
                    setBackgroundColor {
                        languageToggleButton!!.popupMenuBackgroundColor ?: genericColors.popupMenuBackgroundColor
                    }

                    setAllMenuItems {
                        mutableListOf<PopupMenuItem>().apply {
                            val currentLanguageCode = languageToggleButton!!.currentLanguageProviderAction.invoke(requireActivity())

                            val isAutoLanguage = languageToggleButton!!.isAutoLanguageProviderAction.invoke(requireActivity())

                            add(
                                PopupMenuItem(
                                    languageToggleButton!!.autoLanguageText,
                                    languageToggleButton!!.popupMenuTextColor ?: genericColors.popupMenuTextColor,
                                    icon = languageToggleButton!!.uiLanguagesFlagsHolder.getFlag(currentLanguageCode),
                                    isChecked = isAutoLanguage
                                ) { _, _, _ ->
                                    PopupMenuToolbox.dismissPopupMenu(getKey())

                                    languageToggleButton!!.autoLanguageSetterAction.invoke(requireActivity())
                                }
                            )

                            languageToggleButton!!.uiLanguagesAppManager.languageItems.forEach { languageItem ->
                                add(
                                    PopupMenuItem(
                                        languageItem.getDisplayName(currentLanguageCode, true),
                                        languageToggleButton!!.popupMenuTextColor ?: genericColors.popupMenuTextColor,
                                        icon = languageToggleButton!!.uiLanguagesFlagsHolder.getFlag(languageItem.languageCode),
                                        isChecked = !isAutoLanguage && languageItem.languageCode == currentLanguageCode,
                                    ) { _, _, _ ->
                                        PopupMenuToolbox.dismissPopupMenu(getKey())

                                        languageToggleButton!!.languageSetterAction.invoke(requireActivity(), languageItem.languageCode)
                                    }
                                )
                            }
                        }
                    }

                    if(rippleColor != null)
                        setStrokeColor {
                            rippleColor!!
                        }

                    setOnShowAction { _, _ ->
                        val rippleColor = rippleColor ?: CommonToolbox.getRippleColorByLuminance(requireActivity(), genericColors.backgroundColor)

                        viewBinding.fragmentNavigationDrawerSettingsQuickAccessPanelLanguageToggleButtonConstraintLayout.background = DrawableBuilder()
                            .solidColor(rippleColor)
                            .cornerRadius(1000000)
                            .ripple()
                            .rippleColor(CommonToolbox.getRippleColorByLuminance(requireActivity(), rippleColor))
                            .build()

                        viewBinding.fragmentNavigationDrawerSettingsQuickAccessPanelLanguageToggleButtonDropdownIconImageView.run {
                            setImageDrawable(ResourcesCompat.getDrawable(resources,
                                R.drawable.ic_baseline_keyboard_arrow_up_24, null))

                            if(languageToggleButton!!.dropdownIconTintColor != null)
                                CommonToolbox.applyColorFilter(drawable, languageToggleButton!!.dropdownIconTintColor!!)
                            else if(genericColors.iconTintColor != null)
                                CommonToolbox.applyColorFilter(drawable, genericColors.iconTintColor!!)
                        }
                    }

                    setOnDismissAction {
                        viewBinding.fragmentNavigationDrawerSettingsQuickAccessPanelLanguageToggleButtonConstraintLayout.background = DrawableBuilder()
                            .cornerRadius(1000000)
                            .ripple()
                            .rippleColor(rippleColor ?: CommonToolbox.getRippleColorByLuminance(requireActivity(), CommonToolbox.getRippleColorByLuminance(requireActivity(), genericColors.backgroundColor)))
                            .build()

                        viewBinding.fragmentNavigationDrawerSettingsQuickAccessPanelLanguageToggleButtonDropdownIconImageView.run {
                            setImageDrawable(
                                ResourcesCompat.getDrawable(
                                    resources,
                                    R.drawable.ic_baseline_keyboard_arrow_down_24,
                                    null
                                )
                            )

                            if (languageToggleButton!!.dropdownIconTintColor != null)
                                CommonToolbox.applyColorFilter(
                                    drawable,
                                    languageToggleButton!!.dropdownIconTintColor!!
                                )
                            else if (genericColors.iconTintColor != null)
                                CommonToolbox.applyColorFilter(
                                    drawable,
                                    genericColors.iconTintColor!!
                                )
                        }
                    }

                }
        }

    private fun buildLanguageToggleTooltip() = getInitializedViewModel(viewModelStore).tooltipBuilders[TooltipConfig.KeysExtensionVocabulary.KEY_TOOLTIP_LANGUAGE_TOGGLE]!!
        .apply {
            if(this@NavigationDrawerFragment::key.isInitialized) {
                NavigationDrawerToolbox.getSavedNavigationDrawerModel(key)!!.run {
                    setText {
                        languageToggleButton!!.tooltipText + languageToggleTooltipAppendix
                    }

                    setBackgroundColor {
                        languageToggleButton!!.tooltipBackgroundColor ?: genericColors.tooltipBackgroundColor
                    }

                    setTextColor {
                        languageToggleButton!!.tooltipTextColor ?: genericColors.tooltipTextColor
                    }

                    if(rippleColor != null)
                        setStrokeColor {
                            rippleColor!!
                        }

                    setOnClickAction { _, _ ->
                        TooltipToolbox.dismissTooltip(getKey())

                        viewBinding.fragmentNavigationDrawerSettingsQuickAccessPanelLanguageToggleButtonConstraintLayout.performClick()

                        languageToggleButton!!.extraOnClickAction?.invoke(
                            viewBinding.fragmentNavigationDrawerSettingsQuickAccessPanelLanguageToggleButtonConstraintLayout,
                            viewBinding.fragmentNavigationDrawerSettingsQuickAccessPanelLanguageToggleButtonDropdownIconImageView,
                            viewBinding.root,
                            this@NavigationDrawerFragment
                        )
                    }

                    setOnShowAction { _, _ ->
                        val key = getInitializedViewModel(viewModelStore).popupMenuBuilders[PopupMenuConfig.KeysExtensionVocabulary.KEY_POPUP_MENU_LANGUAGE_TOGGLE]!!.getKey()

                        if(PopupMenuToolbox.isPopupMenuShowing(key)) {
                            PopupMenuToolbox.dismissPopupMenu(key)

                            setOnDismissAction {
                                buildLanguageTogglePopupMenu().show(requireActivity(), viewBinding.fragmentNavigationDrawerSettingsQuickAccessPanelLanguageToggleButtonConstraintLayout)
                            }
                        } else
                            removeOnDismissAction()
                    }
                }
            }
        }

    private fun buildThemeTogglePopupMenu() = getInitializedViewModel(viewModelStore).popupMenuBuilders[PopupMenuConfig.KeysExtensionVocabulary.KEY_POPUP_MENU_THEME_TOGGLE]!!
        .apply {
            if(this@NavigationDrawerFragment::key.isInitialized)
                NavigationDrawerToolbox.getSavedNavigationDrawerModel(key)!!.run {
                    setBackgroundColor {
                        themeToggleButton!!.popupMenuBackgroundColor ?: genericColors.popupMenuBackgroundColor
                    }

                    setAllMenuItems {
                        mutableListOf<PopupMenuItem>().apply {
                            val isDarkTheme = themeToggleButton!!.isDarkThemeProviderAction.invoke(requireActivity())

                            val isAutoTheme = themeToggleButton!!.isAutoThemeProviderAction.invoke(requireActivity())

                            add(
                                PopupMenuItem(
                                    themeToggleButton!!.autoThemeText,
                                    themeToggleButton!!.popupMenuTextColor ?: genericColors.popupMenuTextColor,
                                    isChecked = isAutoTheme
                                ) { _, _, _ ->
                                    PopupMenuToolbox.dismissPopupMenu(getKey())

                                    themeToggleButton!!.autoThemeSetterAction.invoke(requireActivity())
                                }
                            )

                            add(
                                PopupMenuItem(
                                    themeToggleButton!!.popupMenuLightThemeText,
                                    themeToggleButton!!.popupMenuTextColor ?: genericColors.popupMenuTextColor,
                                    isChecked = !isAutoTheme && !isDarkTheme,
                                ) { _, _, _ ->
                                    PopupMenuToolbox.dismissPopupMenu(getKey())

                                    themeToggleButton!!.themeSetterAction.invoke(requireActivity(), false)
                                }
                            )

                            add(
                                PopupMenuItem(
                                    themeToggleButton!!.popupMenuDarkThemeText,
                                    themeToggleButton!!.popupMenuTextColor ?: genericColors.popupMenuTextColor,
                                    isChecked = !isAutoTheme && isDarkTheme,
                                ) { _, _, _ ->
                                    PopupMenuToolbox.dismissPopupMenu(getKey())

                                    themeToggleButton!!.themeSetterAction.invoke(requireActivity(), true)
                                }
                            )


                        }
                    }

                    if(rippleColor != null)
                        setStrokeColor {
                            rippleColor!!
                        }

                    setOnShowAction { _, _ ->
                        val rippleColor = rippleColor ?: CommonToolbox.getRippleColorByLuminance(requireActivity(), genericColors.backgroundColor)

                        viewBinding.fragmentNavigationDrawerSettingsQuickAccessPanelThemeToggleButtonConstraintLayout.background = DrawableBuilder()
                            .solidColor(rippleColor)
                            .cornerRadius(1000000)
                            .ripple()
                            .rippleColor(CommonToolbox.getRippleColorByLuminance(requireActivity(), rippleColor))
                            .build()
                    }

                    setOnDismissAction {
                        viewBinding.fragmentNavigationDrawerSettingsQuickAccessPanelThemeToggleButtonConstraintLayout.background = DrawableBuilder()
                            .cornerRadius(1000000)
                            .ripple()
                            .rippleColor(CommonToolbox.getRippleColorByLuminance(requireActivity(), rippleColor ?: CommonToolbox.getRippleColorByLuminance(requireActivity(), genericColors.backgroundColor)))
                            .build()
                    }
                }
        }

    fun dismissModals() {
        dismissTooltips()

        dismissPopupMenus()
    }

    override fun onDrawerOpened() {  }

    override fun onDrawerClosed() {
        dismissModals()
    }

    override fun continueTooltips(vararg excludedKeys: String) {
        NavigationDrawerToolbox.getSavedNavigationDrawerModel(key)!!.run {
            getInitializedViewModel(viewModelStore).tooltipBuilders[TooltipConfig.KeysExtensionVocabulary.KEY_TOOLTIP_SETTINGS]!!.getKey().run {
                if(settingsButton != null && !excludedKeys.contains(this))
                    buildSettingsTooltip().`continue`(requireActivity())
            }

            getInitializedViewModel(viewModelStore).tooltipBuilders[TooltipConfig.KeysExtensionVocabulary.KEY_TOOLTIP_LANGUAGE_TOGGLE]!!.getKey().run {
                if(languageToggleButton != null && !excludedKeys.contains(this))
                    buildLanguageToggleTooltip().`continue`(requireActivity())
            }
        }
    }

    override fun dismissTooltips(vararg excludedKeys: String) {
        getInitializedViewModel(viewModelStore).run {
            tooltipBuilders[TooltipConfig.KeysExtensionVocabulary.KEY_TOOLTIP_SETTINGS]!!.getKey().run {
                if(!excludedKeys.contains(this))
                    TooltipToolbox.dismissTooltip(this)
            }

            tooltipBuilders[TooltipConfig.KeysExtensionVocabulary.KEY_TOOLTIP_LANGUAGE_TOGGLE]!!.getKey().run {
                if(!excludedKeys.contains(this))
                    TooltipToolbox.dismissTooltip(this)
            }
        }
    }

    @JvmOverloads
    fun dismissTooltipByKey(key: String, deleteInfo: Boolean = true) {
        TooltipToolbox.dismissTooltip(key, deleteInfo)
    }

    override fun continuePopupMenus(vararg excludedKeys: String) {
        NavigationDrawerToolbox.getSavedNavigationDrawerModel(key)!!.run {
            getInitializedViewModel(viewModelStore).popupMenuBuilders[PopupMenuConfig.KeysExtensionVocabulary.KEY_POPUP_MENU_LANGUAGE_TOGGLE]!!.getKey().run {
                if(languageToggleButton != null && !excludedKeys.contains(this))
                    buildLanguageTogglePopupMenu().`continue`(requireActivity())
            }

            getInitializedViewModel(viewModelStore).popupMenuBuilders[PopupMenuConfig.KeysExtensionVocabulary.KEY_POPUP_MENU_THEME_TOGGLE]!!.getKey().run {
                if(themeToggleButton != null && !excludedKeys.contains(this))
                    buildThemeTogglePopupMenu().`continue`(requireActivity())
            }
        }
    }

    override fun dismissPopupMenus(vararg excludedKeys: String) {
        getInitializedViewModel(viewModelStore).run {
            popupMenuBuilders[PopupMenuConfig.KeysExtensionVocabulary.KEY_POPUP_MENU_LANGUAGE_TOGGLE]!!.getKey().run {
                if(!excludedKeys.contains(this))
                    PopupMenuToolbox.dismissPopupMenu(this, false)
            }

            popupMenuBuilders[PopupMenuConfig.KeysExtensionVocabulary.KEY_POPUP_MENU_THEME_TOGGLE]!!.getKey().run {
                if(!excludedKeys.contains(this))
                    PopupMenuToolbox.dismissPopupMenu(this, false)
            }
        }
    }

    @JvmOverloads
    fun dismissPopupMenuByKey(key: String, deleteInfo: Boolean = true) {
        PopupMenuToolbox.dismissPopupMenu(key, deleteInfo)
    }
}