package com.example.ui

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.core.graphics.ColorUtils
import androidx.core.view.children
import androidx.core.view.marginBottom
import com.example.ui.databinding.FragmentDebugAnimationsBinding
import com.example.ui.view.progress.MultiProgressView

/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class DebugAnimationsFragment : Fragment() {
    private lateinit var binding: FragmentDebugAnimationsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDebugAnimationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupCurvesAnimation()
        setupCardsAnimation()
        setupAngleProgressView()
    }

    //region Curves animation
    private fun setupCurvesAnimation() = with(binding) {
        loanProgressView.progressColor = Color.parseColor("#3A85F0")
        loanProgressView.progressBackgroundColor =
            ColorUtils.setAlphaComponent(loanProgressView.progressColor, (255 * 0.3).toInt())
        seekBarOffset.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                loanProgressView.endOffset = p1.toFloat() / 100f
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })
        seekBarProgress.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                loanProgressView.progress = p1.toFloat() / 100f
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })
        seekBarWidth.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                loanProgressView.progressWidth = 20f * p1.toFloat() / 100f
                loanProgressView.progressBackgroundWidth = loanProgressView.progressWidth / 2f
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })
        seekBarRadius.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                loanProgressView.radius = 40f * p1.toFloat() / 100f
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })

        multiProgressView.progresses = listOf(
            MultiProgressView.Progress(Color.parseColor("#BEBEBE"), 100),
            MultiProgressView.Progress(Color.parseColor("#FFC3A0"), 80),
            MultiProgressView.Progress(Color.parseColor("#9034C9"), 60),
            MultiProgressView.Progress(Color.parseColor("#3A85F0"), 20)
        )
        val c = Color.parseColor("#5243C2")
        squareMultiProgressView.ovelaid = false
        squareMultiProgressView.reverse = true
        squareMultiProgressView.progresses = listOf(
            MultiProgressView.Progress(c, 60),
            MultiProgressView.Progress(ColorUtils.setAlphaComponent(c, (255 * 0.8).toInt()), 80),
            MultiProgressView.Progress(ColorUtils.setAlphaComponent(c, (255 * 0.5).toInt()), 100)
        )

        seekBarMultiWidth.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                multiProgressView.setHeight(16f * p1.toFloat() / 100f)
                squareMultiProgressView.setHeight(16f * p1.toFloat() / 100f)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })
    }
    //endregion

    //region CardsAnimation
    private var cardCornerRadius = -1f
    private var margin = -1
    private var marginMerged = -1

    private fun setupCardsAnimation() = with(binding) {
        val cards = listOf(
            cardView1,
            cardView2,
            cardView3
        )
        cardCornerRadius = cards.first().radius
        margin = cards.first().marginBottom
        marginMerged = margin * 2
        seekBarCards.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                cards.forEachIndexed { index, cardView ->
                    val isNotLast = index != cards.count() - 1
                    val isNotFirst = index != 0
                    val progress = p1.toFloat() / 100f
                    val progressInverted = 1f - progress
                    cardView.radius = cardCornerRadius * progress

                    //padding
                    if (isNotLast) {
                        val lp =
                            cardView.children.first().layoutParams as ViewGroup.MarginLayoutParams
                        lp.setMargins(
                            lp.leftMargin,
                            lp.topMargin,
                            lp.rightMargin,
                            ((marginMerged / 2) * progressInverted).toInt()
                        )
                        cardView.children.first().layoutParams = lp
                    }
                    if (isNotFirst) {
                        val lp =
                            cardView.children.first().layoutParams as ViewGroup.MarginLayoutParams
                        lp.setMargins(
                            lp.leftMargin,
                            ((marginMerged / 2) * progressInverted).toInt(),
                            lp.rightMargin,
                            lp.bottomMargin
                        )
                        cardView.children.first().layoutParams = lp
                    }

                    //margin
                    if (isNotLast) {
                        val lp = cardView.layoutParams as ViewGroup.MarginLayoutParams
                        lp.setMargins(
                            lp.leftMargin,
                            lp.topMargin,
                            lp.rightMargin,
                            (margin * progress).toInt()
                        )
                        cardView.layoutParams = lp
                    }

                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })
    }
    //endregion


    //region Angle progress view
    private fun setupAngleProgressView() = with(binding) {
        viewAngleProgress.progress = seekBarAngleProgress.progress.toFloat() / 100f
        seekBarAngleProgress.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                viewAngleProgress.progress = p1.toFloat() / 100f
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })
    }
    //endregion
}