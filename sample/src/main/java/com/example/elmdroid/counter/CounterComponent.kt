package com.example.elmdroid.counter

import com.github.dimsuz.diffdispatcher.annotations.DiffElement
import cz.inventi.elmdroid.*

class CounterComponent: SimpleComponent<CounterState, CounterMsg> {
    override fun initState(): CounterState = CounterState(0)

    override fun simpleUpdate(msg: CounterMsg, prevState: CounterState): CounterState = when(msg){
        is Increment -> CounterState(prevState.counter + 1)
        is Decrement -> CounterState(prevState.counter - 1)
    }
}

@DiffElement(diffReceiver = CounterStateRenderer::class)
data class CounterState(val counter: Int) : State
sealed class CounterMsg : Msg
object Increment : CounterMsg()
object Decrement : CounterMsg()
