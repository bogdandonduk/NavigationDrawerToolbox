package bogdandonduk.navigationdrawertoolboxlib

import bogdandonduk.randomkeygenerator.RandomKeyGenerator

object NavigationDrawerConfig {
    internal const val KEY_SAVE_INFO = "key_save_info"
    internal const val KEY_MODEL_KEY = "key_model_key"

    enum class QueueMode {
        DISPLAY_NEW,
        CONTINUE_OLD_IF_SHOWING
    }

    object RandomKeyGenerationUtils {
        internal val transientKeyRegistry = mutableListOf<String>()

        fun generate() = RandomKeyGenerator.generateWithPrefix("navigation_drawer") {
            NavigationDrawerToolbox.getSavedNavigationDrawerModel(it) != null || transientKeyRegistry.contains(it)
        }

        fun getRandomKeyWithClassSuffix(host: Any) = "${generate()}_${host::class.java}"
    }

    object KeysExtensionVocabulary

    interface Callbacks {
        fun onDrawerOpened()

        fun onDrawerClosed()
    }
}