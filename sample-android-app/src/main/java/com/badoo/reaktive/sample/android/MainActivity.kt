package com.badoo.reaktive.sample.android

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import com.badoo.reaktive.observable.observableFromFunction
import com.badoo.reaktive.observable.observeOn
import com.badoo.reaktive.observable.startWith
import com.badoo.reaktive.observable.subscribe
import com.badoo.reaktive.observable.subscribeOn
import com.badoo.reaktive.scheduler.ioScheduler
import com.badoo.reaktive.scheduler.mainScheduler
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.text)

        findViewById<Button>(R.id.button).setOnClickListener {
            observableFromFunction<String> {
                Thread.sleep(1000L)
                SimpleDateFormat.getDateTimeInstance().format(Date())
            }
                .subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
                .startWith("Loading...")
                .subscribe(onNext = textView::setText)
        }
    }
}
