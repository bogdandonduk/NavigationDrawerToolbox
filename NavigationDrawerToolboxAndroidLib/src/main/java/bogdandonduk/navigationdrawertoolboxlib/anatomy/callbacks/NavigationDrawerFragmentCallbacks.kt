package bogdandonduk.navigationdrawertoolboxlib.anatomy.callbacks

import androidx.fragment.app.Fragment

class NavigationDrawerFragmentCallbacks(var onViewCreatedAction: ((fragment: Fragment) -> Unit)? = null, var onDestroyViewAction: ((fragment: Fragment) -> Unit)? = null)