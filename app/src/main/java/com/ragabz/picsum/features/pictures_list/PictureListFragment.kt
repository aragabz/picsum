package com.ragabz.picsum.features.pictures_list

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.ragabz.picsum.R
import com.ragabz.picsum.base.ViewBindingFragment
import com.ragabz.picsum.databinding.FragmentPictureListBinding
import com.ragabz.picsum.extensions.createSnackBar
import com.ragabz.picsum.extensions.visible
import com.ragabz.picsum.models.PictureList
import com.ragabz.picsum.models.PictureModel
import com.ragabz.picsum.utils.ConnectionLiveData
import com.ragabz.picsum.utils.EndlessRecyclerViewScrollListener
import com.ragabz.picsum.utils.ItemClickSupport
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PictureListFragment : ViewBindingFragment<FragmentPictureListBinding>(
    FragmentPictureListBinding::inflate
) {

    private val viewModel: PictureListViewModel by viewModels()

    private lateinit var connectionLiveData: ConnectionLiveData
    var isInternetGone = false

    private val adapter: PictureAdapter by lazy {
        PictureAdapter()
    }


    override fun onInitBinding() {
        connectionLiveData = ConnectionLiveData(requireContext())
        initRecyclerView()
        observeNetworkConnection()
        subscribeToViewModel()
        viewModel.getPictures(true)
    }

    private fun initRecyclerView() {
        binding.recyclerViewPictures.adapter = adapter
        val layoutManager =
            binding.recyclerViewPictures.layoutManager as LinearLayoutManager

        binding.recyclerViewPictures.addOnScrollListener(object :
            EndlessRecyclerViewScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                if (isInternetGone) return
                viewModel.getPictures(true)
            }
        })

        ItemClickSupport.addTo(binding.recyclerViewPictures).setOnItemClickListener(
            object : ItemClickSupport.OnItemClickListener {
                override fun onItemClicked(recyclerView: RecyclerView, position: Int, v: View) {
                    val direction =
                        PictureListFragmentDirections.actionPictureListFragmentToPicturePreviewFragment(
                            adapter.getImageUrlForItem(position)
                        )
                    findNavController().navigate(direction)
                }
            }
        )
    }

    private fun subscribeToViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.state.collectLatest {
                    renderState(it)
                }
            }
        }
    }

    private fun observeNetworkConnection() {
        //check for internet
        connectionLiveData.observe(this) { isNetworkAvailable ->
            if (isNetworkAvailable && isInternetGone == true) {
                binding.root.createSnackBar(
                    getString(R.string.internet_connection_state_connected),
                    Snackbar.LENGTH_LONG
                ).show()
                isInternetGone = false
            } else if (isNetworkAvailable == false) {
                binding.root.createSnackBar(
                    getString(R.string.internet_connection_state_not_connected),
                    Snackbar.LENGTH_LONG
                ).show()
                isInternetGone = true
            }
        }
    }

    private fun renderState(state: PictureListStateView) {

        binding.apply {
            progressBar.visible = state.isLoading
            if (state.error.trim().isNotEmpty()) {
                showErrorSnackBar(state.error)
            }
            renderPictureList(state.pictureList)

        }

    }

    private fun showErrorSnackBar(message: String) {
        binding.root.createSnackBar(message).show()
    }

    private fun renderPictureList(list: PictureList) {
        val pictureAndAdsList = mutableListOf<PictureItemViewModel>()
        list.forEachIndexed { index, pictureModel ->
            when (index % 5 == 0) {
                true -> {
                    val adsList = listOf(
                        PictureItemViewModel(ItemType.NORMAL.value, pictureModel),
                        PictureItemViewModel(ItemType.ADS.value, PictureModel())
                    )
                    pictureAndAdsList.addAll(adsList)
                }

                false -> pictureAndAdsList.add(
                    PictureItemViewModel(
                        ItemType.NORMAL.value,
                        pictureModel
                    )
                )
            }
        }

        adapter.submitList(pictureAndAdsList)
    }

}