
# NavigationDrawerToolbox
  
  Android library based on the enhanced builder pattern that simplifies creation of powerful and beautiful navigation drawer layouts.
  
  ![Example](https://github.com/bogdandonduk/AppManualToolbox/blob/master/device-2021-09-12-205834.png)
  
## Include in your project  
**Gradle dependency**  
  
Add this in your **app**-level **build.gradle** file:  
```groovy  
dependencies {  
	...  
  
	def latest_version_tag = 1.4
	implementation "com.github.bogdandonduk:NavigationDrawerToolbox:$latest_version_tag"  
  
	...  
}  
```  
You can always find the **latest_version_tag** [here](https://github.com/bogdandonduk/NavigationDrawerToolbox/releases).  
  
Also make sure you have this repository in your **project**-level **build.gradle** file:  
```groovy  
allprojects {  
	repositories {  
		...  
  
		maven { url 'https://jitpack.io' }  
	}  
}  
```  

# Examples of usage
```kotlin 
// get a builder for simple NavigationDrawer layout containing header, menu and bottom panel for quick settings, set it, and load the built NavigationDrawer layout into your NavigationView:

val builder = NavigationDrawerToolbox.buildNavigationDrawer(context, key = "my_navigation_drawer")

builder
	.setHeader { oldHeader: NavigationDrawerHeader -> // this is a feature of the enhanced builder pattern, 
	// it provides previous values as lambda arguments for you to operate on.
	// just return a new value from this lambda to set it
	// p.s. Do not worry about performance, this lambda is inlined.
		NavigationDrawerToolbox.buildSimpleNavigationDrawerHeader()
			.setAppIcon { oldIcon: Drawable? ->
				ResourcesCompat.getDrawable(context.resources, R.drawable.ic_book_epub, null)
			}
			.setAppNameText { oldText: CharSequence -> // you may even use a spannable string here
				context.getString(R.string.app_name)
			}
			.build()
	}
	.setMenu { oldMenu: NavigationDrawerMenu ->
		buildSimpleNavigationDrawerMenu()
			.setAllMenuItems {
				mutableListOf<SimpleNavigationDrawerMenuItem>().apply {
					add(
						SimpleNavigationDrawerMenuItem(
							"App guide",
							icon = ResourcesCompat.getDrawable(context.resources, R.drawable.ic_baseline_book_24, null)
						) {
							// ... onClickAction
						}
					)
					add(
						SimpleNavigationDrawerMenuItem(
							"Rate on Google Play",
							icon = ResourcesCompat.getDrawable(context.resources, R.drawable.ic_baseline_rate_review_24, null)
						) {
							// ... onClickAction
						}
					)
					add(
						SimpleNavigationDrawerMenuItem(
							"Contact developers",
							icon = ResourcesCompat.getDrawable(context.resources, R.drawable.ic_baseline_email_24, null)
						) {
							// ... onClickAction
						}
					)
				}
			}
	}
	.setSettingsButton { oldButton: SettingsButton? ->
		NavigationDrawerToolbox.buildSimpleNavigationDrawerHeader()
			.setAppIcon { oldIcon: Drawable? ->
				ResourcesCompat.getDrawable(context.resources, R.drawable.ic_book_epub, null)
			}
			.setAppNameText { oldText: CharSequence -> // you may even use a spannable string here
				context.getString(R.string.app_name)
			}
			.build()
	}
	.setSettingsActivityClass { oldSettingsActivityClass: Class<out Activity>? ->
		SettingsActivity::class.java
	}
  .build()  
}

class MainActivity : Activity {
	override fun onCreate(savedInstanceState: Bundle?) {
			...

			builder.load(supportFragmentManager, containerViewId = R.id.my_navigation_view)
			
			...
	}
}
```

