package bogdandonduk.navigationdrawertoolboxlib.anatomy.menu.simple

class SimpleNavigationDrawerMenuBuilder() {
    @PublishedApi
    internal var model = SimpleNavigationDrawerMenu(
        mutableListOf<SimpleNavigationDrawerMenuItem>().apply {
            add(
                SimpleNavigationDrawerMenuItem(
                    "Item 1"
                ) { _, _ ->

                }
            )

            add(
                SimpleNavigationDrawerMenuItem(
                    "Item 2"
                ) { _, _ ->

                }
            )

            add(
                SimpleNavigationDrawerMenuItem(
                    "Item 3"
                ) { _, _ ->

                }
            )

        },
        null
    )

    inline fun setAllMenuItems(modification: (oldItems: MutableList<SimpleNavigationDrawerMenuItem>) -> MutableList<SimpleNavigationDrawerMenuItem>) = this.apply {
        model.menuItems = modification.invoke(model.menuItems)
    }

    fun getAllMenuItems() = model.menuItems

    inline fun setBackgroundColor(modification: (oldColor: Int?) -> Int?) = this.apply {
        model.backgroundColor = modification.invoke(model.backgroundColor)
    }

    fun getBackgroundColor() = model.backgroundColor

    fun build() = model
}