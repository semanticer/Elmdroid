package com.example.elmdroid.chess

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.elmdroid.R
import com.jakewharton.rxbinding2.view.RxView
import cz.inventi.elmdroid.ElmViewModel
import cz.inventi.elmdroid.getViewModelFor
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_chess.*
import net.semanticer.renderit.renderit

class ChessActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chess)

        val firstTimer = getViewModelFor(ChessTimerComponent(true), "first")
        val secondTimer = getViewModelFor(ChessTimerComponent(false), "second")

        val clickMsgStream: Observable<ChessTimerMsg> = RxView.clicks(firstTimerBtn)
                                .mergeWith(RxView.clicks(secondTimerBtn))
                                .subscribeOn(AndroidSchedulers.mainThread())
                                .publish()
                                .autoConnect(2)
                                .map { Click }

        firstTimer.dispatch(clickMsgStream)
        secondTimer.dispatch(clickMsgStream)

        renderit(firstTimer.state(), ChessTimerStateDiffDispatcher.Builder().target(FirstTimerRenderer(firstTimerBtn)).build())
        renderit(secondTimer.state(), ChessTimerStateDiffDispatcher.Builder().target(FirstTimerRenderer(secondTimerBtn)).build())
    }
}

class FirstTimerRenderer(private val timerView: TextView) : ChessTimerStateRenderer {
    override fun renderTime(time: Int) {
        timerView.text = "$time"
    }
}