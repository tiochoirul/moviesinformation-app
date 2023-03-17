package com.tcm.moviesinformationapp.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.tcm.core.data.Resource
import com.tcm.core.ui.PopularAdapter
import com.tcm.moviesinformationapp.R
import com.tcm.moviesinformationapp.databinding.FragmentHomeBinding
import com.tcm.moviesinformationapp.detail.DetailPopularActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val popularAdapter = PopularAdapter()
            popularAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailPopularActivity::class.java)
                intent.putExtra(DetailPopularActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            homeViewModel.popular.observe(viewLifecycleOwner) { popular ->
                binding.apply {
                    if (popular != null) when (popular) {
                        is Resource.Loading -> progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            progressBar.visibility = View.GONE
                            popularAdapter.setData(popular.data)
                        }
                        is Resource.Error -> {
                            progressBar.visibility = View.GONE
                            viewError.root.visibility = View.VISIBLE
                            viewError.tvError.text =
                                popular.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            }

            with(binding.rvPopular) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = popularAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}