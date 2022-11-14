package com.mkashimu.fragmentsample.ui.main

import android.annotation.SuppressLint
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.mkashimu.fragmentsample.R

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    private var listener: OnGlobalLayoutListener? = null

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 参考: https://android-note.open-memo.net/sub/other_view__get_view_size.html
        this.listener = OnGlobalLayoutListener {
            view.findViewById<TextView>(R.id.view_tree_observer_text).text =
                "View: height:${view.measuredHeight}width:${view.measuredWidth}"

            val rect = Rect()
            activity?.window?.decorView?.getWindowVisibleDisplayFrame(rect)
            view.findViewById<TextView>(R.id.window_rect_text).text = "Window Rect: $rect"
            view.viewTreeObserver.removeOnGlobalLayoutListener(listener)
        }
        view.viewTreeObserver.addOnGlobalLayoutListener(listener)
    }
}