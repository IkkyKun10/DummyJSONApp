package org.riezki.dummyjsonapp

import androidx.compose.ui.window.ComposeUIViewController
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.riezki.dummyjsonapp.app.App

fun MainViewController() = ComposeUIViewController(
    configure = {
        Napier.base(DebugAntilog())
    }
) { App() }