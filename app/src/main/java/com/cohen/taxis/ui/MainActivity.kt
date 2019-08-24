package com.cohen.taxis.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.cohen.taxis.R
import com.cohen.taxis.dummy.DummyContent
import com.cohen.taxis.helpers.room.Persistent
import com.cohen.taxis.viewmodel.TaxiViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    private val persistent: Persistent by inject()
    private val taxiViewModel: TaxiViewModel by viewModel()
    private var timer: Timer? = null
    private var timerTask: TimerTask? = null

    private fun startTimer() {
        timer = Timer()
        timerTask = object : TimerTask() {
            override fun run() {
                lifecycleScope.launch {
                    val taxis = persistent.taxis()
                    persistent.addOrUpdateTaxis(taxis.map {
                        it.eta = Random.nextInt(0, MAX_ETA_IN_MINUTES)
                        it
                    })
                }
            }
        }
        timer!!.schedule(
            timerTask,
            TimeUnit.SECONDS.toMillis(TIMER_DURATION_IN_SECONDS),
            TimeUnit.SECONDS.toMillis(TIMER_DURATION_IN_SECONDS)
        )
    }

    private fun stopTimer() {
        timer?.cancel()
        timer?.purge()

    }

    override fun onResume() {
        super.onResume()
        startTimer()
    }

    override fun onPause() {
        super.onPause()
        stopTimer()
    }


    private val mainFragment by lazy {
        if (supportFragmentManager.findFragmentByTag("MainFragment") != null) {
            supportFragmentManager.findFragmentByTag("MainFragment") as MainFragment
        } else {
            MainFragment.newInstance()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setMainMode()

        taxiViewModel.taxis.observe(this, Observer {
            setMainMode()
        })

        initDataIfNeeded()
    }

    private fun initDataIfNeeded() {
        GlobalScope.launch {
            val taxis = persistent.taxis()
            if (taxis.isNullOrEmpty()) {
                persistent.addOrUpdateTaxis(DummyContent.ITEMS.map {
                    it.eta = Random.nextInt(0, MAX_ETA_IN_MINUTES)
                    it
                })
            }

        }
    }

    private fun setMainMode() {
        val transaction = supportFragmentManager.beginTransaction()

        transaction.replace(R.id.fragment_container, mainFragment, "mainFragment")
        transaction.addToBackStack("mainFragment")
        transaction.commit()
    }

    override fun onBackPressed() {
        when {
            mainFragment.isVisible -> finish()
        }
        super.onBackPressed()
    }

    companion object {
        const val TIMER_DURATION_IN_SECONDS = 5L
        const val MAX_ETA_IN_MINUTES = 120
    }
}

