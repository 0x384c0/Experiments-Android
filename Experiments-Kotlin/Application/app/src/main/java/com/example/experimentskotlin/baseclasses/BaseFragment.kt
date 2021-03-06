package com.example.experimentskotlin.baseclasses

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import java.util.*

/**
 * базовый класс для фрагмента
 */
abstract class BaseFragment : Fragment() {
    abstract val layoutID: Int

    //region UI
    private var baseActivity: BaseActivity
        get() = activity as BaseActivity
        set(_) {}
    //endregion

    //region LifeCycle
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutID, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fixTextInputEditText(view)
    }

    override fun onResume() {
//        Crashlytics.log("${javaClass.name}.onResume")
        super.onResume()
    }

    override fun onPause() {
//        Crashlytics.log("${javaClass.name}.onPause")
        super.onPause()
        hideKeyboard()
    }
    //endregion

    //Others
    internal open fun showAlert(e: Throwable) {
        try {
            baseActivity.showAlert(e)
        } catch (e: Exception) {
        }
    }

    internal open fun showAlert(text: String) {
        try {
            baseActivity.showAlert(null, text)
        } catch (e: Exception) {
        }
    }

    internal open fun showLoading() {
        try {
            baseActivity.showLoading()
        } catch (e: Exception) {
        }
    }

    internal open fun hideLoading() {
        try {
            baseActivity.hideLoading()
        } catch (e: Exception) {
        }
    }


    internal open fun hideKeyboard() {
        try {
            baseActivity.hideKeyboard()
        } catch (e: Exception) {
        }
    }

    //region Workarounds
    /**
     *  предотвразает краш приложения на телефонах meizu
     *  https://stackoverflow.com/questions/51891415/nullpointerexception-on-meizu-devices-in-editor-updatecursorpositionmz
     */
    private fun fixTextInputEditText(view: View) {
        val manufacturer = Build.MANUFACTURER.toUpperCase(Locale.US)
        if ("MEIZU" in manufacturer) {
            val views = getAllTextInputs(view)
            views.forEach(::hackFixHintsForMeizu)
        }
    }

    private fun hackFixHintsForMeizu(editText: TextInputEditText) {
        if (editText.hint != null) {
            editText.setHintTextColor(Color.TRANSPARENT)
            editText.hint = editText.hint
        }
    }

    private fun getAllTextInputs(v: View): List<TextInputEditText> {
        if (v !is ViewGroup) {
            val editTexts = mutableListOf<TextInputEditText>()
            (v as? TextInputEditText)?.let {
                editTexts += it
            }
            return editTexts
        }

        val result = mutableListOf<TextInputEditText>()
        for (i in 0 until v.childCount) {
            val child = v.getChildAt(i)
            result += getAllTextInputs(child)
        }
        return result
    }
    //endregion
}