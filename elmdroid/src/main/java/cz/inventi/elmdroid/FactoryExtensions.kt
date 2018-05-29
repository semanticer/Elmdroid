package cz.inventi.elmdroid

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity

fun <V : LifecycleOwner, STATE : State, MSG : Msg, CMD : Cmd> V.createRuntimeFor(component: Component<STATE, MSG, CMD>, logLevel: LogLevel = Elmdroid.defaultLogLevel): ComponentRuntime<STATE, MSG> {
    return ComponentRuntime.create(component, this, logLevel)
}

inline fun <F : FragmentActivity, STATE : State, MSG : Msg, CMD : Cmd, reified T : ElmViewModel<STATE, MSG, CMD>> F.getViewModelFor(component: Component<STATE, MSG, CMD>, key: String? = null, logLevel: LogLevel = Elmdroid.defaultLogLevel): ElmViewModel<STATE, MSG, CMD> {
    val viewModelProvider = ViewModelProviders.of(this, ElmViewModelFactory(component, logLevel))
    return if (key != null) {
        viewModelProvider.get(key, T::class.java)
    } else {
        viewModelProvider.get(T::class.java)
    }
}

inline fun <F : Fragment, STATE : State, MSG : Msg, CMD : Cmd, reified T : ElmViewModel<STATE, MSG, CMD>> F.getViewModelFor(component: Component<STATE, MSG, CMD>, key: String? = null, logLevel: LogLevel = Elmdroid.defaultLogLevel): ElmViewModel<STATE, MSG, CMD> {
    val viewModelProvider = ViewModelProviders.of(this, ElmViewModelFactory(component, logLevel))
    return if (key != null) {
        viewModelProvider.get(key, T::class.java)
    } else {
        viewModelProvider.get(T::class.java)
    }
}

class ElmViewModelFactory<STATE : State, in MSG : Msg, CMD : Cmd>(private val component: Component<STATE, MSG, CMD>, private val logLevel: LogLevel = Elmdroid.defaultLogLevel) : ViewModelProvider.Factory {
    override fun <U : ViewModel> create(modelClass: Class<U>): U {
        if (modelClass.isAssignableFrom(ElmViewModel::class.java)) {
            return ElmViewModel(component, logLevel) as U
        }
        throw IllegalArgumentException("Unknown ViewModel class - ViewModel must be ElmViewModel or subclass")
    }
}