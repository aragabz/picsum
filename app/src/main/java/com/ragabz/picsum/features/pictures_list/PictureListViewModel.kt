package com.ragabz.picsum.features.pictures_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ragabz.picsum.base.Store
import com.ragabz.picsum.base.onError
import com.ragabz.picsum.base.onSuccess
import com.ragabz.picsum.domian.repositories.PicturesRepository
import com.ragabz.picsum.domian.usecases.GetCachedPictureListUseCase
import com.ragabz.picsum.domian.usecases.GetPictureListUseCase
import com.ragabz.picsum.models.PictureList
import com.ragabz.picsum.models.PictureModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PictureListViewModel
@Inject constructor(
    private val getPictureListUseCase: GetPictureListUseCase,
    private val getCachedPictureListUseCase: GetCachedPictureListUseCase
) : ViewModel() {

    private val store = Store(
        initialState = PictureListStateView(),
        reducer = PictureListReducer()
    )

    val state: StateFlow<PictureListStateView> = store.state


    fun getPictures() {
        val action = PictureListAction.GetPictureList
        store.dispatch(action)
        viewModelScope.launch {
            getPictureListUseCase.invoke(state.value.page).collect {
                it.onSuccess {
                    handleSuccess(it)
                }.onError {
                    handleError(it ?: "error happened")
                }
            }
        }
    }

    fun getCachedPictures() {
        val action = PictureListAction.GetPictureList
        store.dispatch(action)
        viewModelScope.launch {
            getCachedPictureListUseCase.invoke(Unit).collect {
                it.onSuccess {
                    val action = PictureListAction.ShowPictureList(it, 1)
                    store.dispatch(action)
                }.onError {
                    handleError(it ?: "error happened")
                }
            }
        }
    }

    private fun handleSuccess(list: PictureList) {
        val newList = mutableListOf<PictureModel>().apply {
            if (state.value.page > 1) {
                addAll(state.value.pictureList)
            }
            addAll(list)
        }
        val action = PictureListAction.ShowPictureList(newList, state.value.page + 1)
        store.dispatch(action)
    }

    private fun handleError(message: String) {
        val action = PictureListAction.ShowError(message)
        store.dispatch(action)
    }
}