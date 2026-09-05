package org.odin.cinekmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform