package app.wayneedle.ui.format

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import app.wayneedle.BuildConfig
import java.util.Locale

/** Uses the device's number formats and the Chinese edition's date locale in the debug build. */
@Composable
fun rememberUiFormatter(): UiFormatter {
    val locale = LocalConfiguration.current.locales[0]
    val dateLocale = if (BuildConfig.DEBUG) Locale.SIMPLIFIED_CHINESE else locale
    return remember(locale, dateLocale) { UiFormatter(locale, dateLocale) }
}
