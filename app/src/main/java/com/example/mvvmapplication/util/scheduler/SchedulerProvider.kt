package com.example.mvvmapplication.util.scheduler

import io.reactivex.Scheduler

interface SchedulerProvider {
    fun computation(): Scheduler

    fun io(): Scheduler

    fun ui(): Scheduler
}