package com.motiontracker

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform