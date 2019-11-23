package com.droidconsf.architectureagnosticuidevelopment.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.ui.core.setContent
import com.droidconsf.architectureagnosticuidevelopment.ArchitectureAgnosticUiApplication
import com.droidconsf.architectureagnosticuidevelopment.ui.comicbooks.compose.ComicsScreen
import com.droidconsf.architectureagnosticuidevelopment.ui.common.di.ActivityModule
import com.droidconsf.architectureagnosticuidevelopment.ui.common.statemachine.Event
import javax.inject.Inject

class ComposeActivity : AppCompatActivity() {
    @Inject
    internal lateinit var viewModel: ComicbooksViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDagger()
        viewModel.triggerEvent(Event.View.LoadComics)
        setContent {
            ComicsScreen(viewModel.state)
        }
    }

    private fun initDagger() {
        (application as ArchitectureAgnosticUiApplication).appComponent
            ?.activitySubComponent(ActivityModule(this))
            ?.inject(this)
    }
}
