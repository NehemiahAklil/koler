package com.chooloo.www.koler.ui.call

import android.net.Uri
import android.os.Bundle
import com.chooloo.www.koler.R
import com.chooloo.www.koler.databinding.ActivityCallBinding
import com.chooloo.www.koler.ui.base.BaseActivity
import com.chooloo.www.koler.ui.callactions.CallActionsFragment
import com.chooloo.www.koler.util.*

class CallActivity : BaseActivity(), CallContract.View {
    private val _presenter by lazy { CallPresenter<CallContract.View>() }
    private val _proximitySensor by lazy { ProximitySensor(this) }
    private val _binding by lazy { ActivityCallBinding.inflate(layoutInflater) }

    override var stateText: String?
        get() = _binding.callStateText.text.toString()
        set(value) {
            _binding.callStateText.text = value
        }

    override var stateTextColor: Int
        get() = _binding.callStateText.currentTextColor
        set(value) {
            _binding.callStateText.setTextColor(value)
        }

    override var callerNameText: String?
        get() = _binding.callNameText.text.toString()
        set(value) {
            _binding.callNameText.text = value
        }

    override var callerImageURI: Uri?
        get() = null
        set(value) {
            _binding.callImage.setImageURI(value)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(_binding.root)
    }

    override fun onSetup() {
        _presenter.attach(this)
        _proximitySensor.acquire()

        _binding.apply {
            callAnswerButton.setOnClickListener { _presenter.onAnswerClick() }
            callRejectButton.setOnClickListener { _presenter.onRejectClick() }
        }

        disableKeyboard()
        setShowWhenLocked()

        _presenter.onInitialUI()
    }

    override fun onDestroy() {
        super.onDestroy()
        _presenter.detach()
        _proximitySensor.release()
    }

    override fun getContact(number: String?) = lookupContact(number)
    
    override fun transitionToActiveUI() {
        if (_binding.root.currentState == R.id.incoming_call) {
            supportFragmentManager
                .beginTransaction()
                .add(_binding.callActionsContainer.id, CallActionsFragment.newInstance())
                .commitNow()
            showView(_binding.callActionsContainer, true)
            _binding.root.transitionToEnd()
        }
    }

    override fun blinkStateText() {
        blinkView(_binding.callStateText, 400, 2500)
    }

    override fun startStopwatch() {
        _binding.callChronometer.start()
    }

    override fun stopStopwatch() {
        _binding.callChronometer.stop()
    }
}
