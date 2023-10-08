package com.example.carcrimesplus

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.carcrimesplus.databinding.FragmentContentBinding
import com.example.carcrimesplus.rv.content.items.Content
import com.example.carcrimesplus.navigation.coroutineScope
import com.example.carcrimesplus.navigation.navigationObserver
import com.example.carcrimesplus.rv.base.RecyclerDelegateAdapter
import com.example.carcrimesplus.rv.content.delegate.ImageDelegate
import com.example.carcrimesplus.rv.content.delegate.TextDelegate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ContentFragment : Fragment(R.layout.fragment_content) {

    private val binding: FragmentContentBinding by viewBinding()

    private var currentContent = MutableStateFlow<List<Content>>(listOf())

    private val adapter = RecyclerDelegateAdapter(
        mutableListOf(
            TextDelegate(),
            ImageDelegate()
        )
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNavigationCollector()
        setupContentCollector()
    }

    private fun setupNavigationCollector() {
        coroutineScope.launch {
            navigationObserver.navFlow.collect {
                currentContent.emit(it.content)
            }
        }
    }

    private fun setupContentCollector() {
        coroutineScope.launch(Dispatchers.Main) {
            currentContent.collect {
                adapter.items = it.toMutableList()
                binding.contentRcv.adapter = adapter
                binding.contentRcv.adapter?.notifyDataSetChanged()
            }
        }
    }
}