package com.escodro.alkaa

import android.graphics.Color
import android.os.Bundle
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.BundleCompat
import androidx.core.view.WindowCompat
import com.escodro.navigation.NavigationAction
import com.escodro.shared.MainView

/**
 * Main Alkaa Activity.
 */
internal class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            MainView(
                navigationAction = getNavigationAction(),
                onThemeUpdate = ::updateTheme,
            )
        }
    }

    private fun updateTheme(isDarkTheme: Boolean) {
        val systemBarStyle = if (isDarkTheme) {
            SystemBarStyle.dark(Color.TRANSPARENT)
        } else {
            SystemBarStyle.light(lightScrim, darkScrim)
        }

        enableEdgeToEdge(statusBarStyle = systemBarStyle, navigationBarStyle = systemBarStyle)
    }

    private fun getNavigationAction(): NavigationAction = intent.extras?.let { bundle ->
        BundleCompat.getParcelable(
            bundle,
            NavigationAction.EXTRA_DESTINATION,
            NavigationAction::class.java,
        )
    } ?: NavigationAction.Home
}

/**
 * Android light scrim color.
 */
private val lightScrim = Color.argb(0xe6, 0xFF, 0xFF, 0xFF)

/**
 * Android dark scrim color.
 */
private val darkScrim = Color.argb(0x80, 0x1b, 0x1b, 0x1b)
