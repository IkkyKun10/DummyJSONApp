package org.riezki.dummyjsonapp.app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform