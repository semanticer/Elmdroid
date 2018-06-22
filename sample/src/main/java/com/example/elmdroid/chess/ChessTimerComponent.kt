package com.example.elmdroid.chess

import com.github.dimsuz.diffdispatcher.annotations.DiffElement
import cz.inventi.elmdroid.*
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class ChessTimerComponent(private val startingIsOn: Boolean) : SimpleComponent<ChessTimerState, ChessTimerMsg> {

    override fun initState() = ChessTimerState(1000, startingIsOn)

    override fun simpleUpdate(msg: ChessTimerMsg, prevState: ChessTimerState) = when(msg) {
        Click -> prevState.copy(isOn = !prevState.isOn)
        Tick -> prevState.copy(time = prevState.time - 1)
    }

    override fun subscriptions(): List<Sub<ChessTimerState, ChessTimerMsg>> = listOf(TimerSubscription())
}


class TimerSubscription : StatefulSub<ChessTimerState, ChessTimerMsg>() {
    override fun invoke(state: ChessTimerState): Observable<ChessTimerMsg> = when {
        state.isOn -> Observable.interval(1, TimeUnit.SECONDS).map { Tick }
        else -> Observable.empty()
    }
    override fun isDistinct(s1: ChessTimerState, s2: ChessTimerState) = s1.isOn != s2.isOn
}

@DiffElement(diffReceiver = ChessTimerStateRenderer::class)
data class ChessTimerState(val time: Int, val isOn: Boolean): State

sealed class ChessTimerMsg : Msg
object Click: ChessTimerMsg()
object Tick: ChessTimerMsg()