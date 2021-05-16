package com.chooloo.www.koler.ui.widgets

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import androidx.annotation.ColorInt
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.chooloo.www.koler.R
import com.chooloo.www.koler.util.AnimationManager
import com.chooloo.www.koler.util.getAttrColor
import java.util.*

class Tab : AppCompatTextView {
    private val disabledColor by lazy { ContextCompat.getColor(context, R.color.color_hint) }
    private val enabledColor by lazy { context.getAttrColor(R.attr.colorOnSurface) }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleRes: Int = 0
    ) : super(context, attrs, defStyleRes) {
        textSize = 28f
        text = text.toString().capitalize(Locale.ROOT)
        typeface = ResourcesCompat.getFont(context, R.font.google_sans_bold)

        setTextColor(enabledColor)
    }

    override fun setActivated(activated: Boolean) {
        super.setActivated(activated)
        setColor(if (activated) enabledColor else disabledColor)
        if (activated) {
            animateAttension()
        }
    }

    private fun animateAttension() {
        AnimationManager(context).bounceInUp(this)
    }

    private fun setColor(@ColorInt color: Int) {
        ValueAnimator.ofObject(ArgbEvaluator(), currentTextColor, color).apply {
            addUpdateListener { setTextColor(it.animatedValue as Int) }
            start()
        }
    }
}