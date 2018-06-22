package com.example.elmdroid.counter

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.elmdroid.R
import cz.inventi.elmdroid.createRuntimeFor
import kotlinx.android.synthetic.main.activity_counter.*
import net.semanticer.renderit.renderit

class CounterActivity : AppCompatActivity(), CounterStateRenderer {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_counter)
        supportActionBar?.title = getString(R.string.basic_counter)

        val runtime = createRuntimeFor(CounterComponent())

        renderit(runtime.state(), CounterStateDiffDispatcher.Builder().target(this@CounterActivity).build() )

        increment.setOnClickListener { runtime.dispatch(Increment) }
        decrement.setOnClickListener { runtime.dispatch(Decrement) }
    }

    override fun renderCounter(counter: Int) {
        counterLabel.text = "$counter"
    }
}
