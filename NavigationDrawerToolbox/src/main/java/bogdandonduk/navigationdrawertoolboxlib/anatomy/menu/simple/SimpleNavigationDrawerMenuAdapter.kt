package bogdandonduk.navigationdrawertoolboxlib.anatomy.menu.simple

import android.app.Activity
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import bogdandonduk.commontoolboxlib.CommonToolbox
import bogdandonduk.navigationdrawertoolboxlib.anatomy.genericcolors.GenericColors
import bogdandonduk.navigationdrawertoolboxlib.databinding.LayoutNavigationDrawerMenuItemBinding
import bogdandonduk.navigationdrawertoolboxlib.fragment.NavigationDrawerFragment
import bogdandonduk.viewdatabindingwrapperslib.ViewBindingHandler
import top.defaults.drawabletoolbox.DrawableBuilder

internal class SimpleNavigationDrawerMenuAdapter(
    var menuItems: MutableList<SimpleNavigationDrawerMenuItem>,
    var drawerGenericColors: GenericColors,
    var navigationDrawerFragment: NavigationDrawerFragment,
    var hostActivityOnTouchAction: (() -> Unit)?,
    var hostActivity: Activity
) : RecyclerView.Adapter<SimpleNavigationDrawerMenuAdapter.ViewHolder>() {
    lateinit var recyclerView: RecyclerView

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        this.recyclerView = recyclerView
    }

    inner class ViewHolder(override var viewBinding: LayoutNavigationDrawerMenuItemBinding) : RecyclerView.ViewHolder(viewBinding.root), ViewBindingHandler<LayoutNavigationDrawerMenuItemBinding> {
        lateinit var item: SimpleNavigationDrawerMenuItem

        init {
            viewBinding.root.run {
                setOnTouchListener { _, _ ->
                    navigationDrawerFragment.dismissModals()

                    hostActivityOnTouchAction?.invoke()

                    false
                }

                setOnClickListener {
                    if(this@ViewHolder::item.isInitialized) item.onClickAction.invoke(item.text.toString(), it)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutNavigationDrawerMenuItemBinding.inflate(hostActivity.layoutInflater))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.run {
            item = menuItems[position]

            viewBinding.run {
                val rippleColor = CommonToolbox.getRippleColorByLuminance(hostActivity, item.backgroundColor ?: drawerGenericColors.backgroundDrawableTintColor ?: (drawerGenericColors.backgroundDrawable as? ColorDrawable)?.color ?: drawerGenericColors.backgroundColor)

                root.background = DrawableBuilder()
                    .ripple()
                    .rippleColor(rippleColor)
                    .apply {
                        if(item.currentlyIn)
                            solidColor(rippleColor)
                    }
                    .build()

                root.layoutParams = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT).apply {
                    width = recyclerView.width
                }

                layoutNavigationMenuItemTextTextView.run {
                    text = item.text

                    setTextColor(item.textColor ?: drawerGenericColors.textColor)
                }

                if(item.icon != null)
                    layoutNavigationMenuItemIconImageView.run {
                        visibility = View.VISIBLE
                        setImageDrawable(item.icon)

                        if(item.iconTintColor != null) {
                            CommonToolbox.applyColorFilter(drawable, item.iconTintColor!!)
                        } else if(drawerGenericColors.iconTintColor != null) {
                            CommonToolbox.applyColorFilter(drawable, drawerGenericColors.iconTintColor!!)
                        }
                    }
                else
                    layoutNavigationMenuItemIconImageView.run {
                        visibility = View.GONE
                        setImageDrawable(null)
                    }
            }
        }
    }

    override fun getItemCount() = menuItems.size
}