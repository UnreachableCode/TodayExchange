package com.freeagent.testapp.exchange.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.freeagent.testapp.databinding.FragmentRatesListBinding
import com.freeagent.testapp.exchange.viewmodels.RatesListViewModel

class RatesListFragment : Fragment() {

    private val binding by lazy { FragmentRatesListBinding.inflate(layoutInflater) }
    private val ratesListAdapter = RatesListAdapter()
    private val viewModel: RatesListViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ratesListRecycler.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = ratesListAdapter
        }
    }
}