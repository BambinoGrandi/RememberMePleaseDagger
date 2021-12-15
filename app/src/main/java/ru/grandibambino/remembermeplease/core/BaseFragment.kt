package ru.grandibambino.remembermeplease.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<T : ViewBinding> : Fragment() {

    protected var binding: T? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = provideBinding(inflater)
        
        return binding?.root
    }

    override fun onStart() {
        super.onStart()
        setupUI()
        clickEvent()
        setObservable()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    protected abstract fun provideBinding(inflater: LayoutInflater): T
    protected abstract fun setupUI()
    protected abstract fun setObservable()
    protected abstract fun clickEvent()
}