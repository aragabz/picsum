package com.ragabz.picsum.features.pictures_list

import com.ragabz.picsum.base.Reducer

class PictureListReducer : Reducer<PictureListStateView, PictureListAction> {
    override fun reduce(
        currentState: PictureListStateView,
        action: PictureListAction
    ): PictureListStateView {
        return when (action) {
            is PictureListAction.GetPictureList -> stateToGetPictureList(currentState)
            is PictureListAction.ShowError -> stateToShowError(currentState, action)
            is PictureListAction.ShowPictureList -> stateToShowPictureList(currentState, action)
        }
    }


    private fun stateToGetPictureList(
        currentState: PictureListStateView,
    ) = currentState.copy(
        isLoading = true,
        error = ""
    )

    private fun stateToShowPictureList(
        currentState: PictureListStateView,
        action: PictureListAction.ShowPictureList
    ) = currentState.copy(
        isLoading = false,
        error = "",
        pictureList = action.list,
        page = action.page
    )

    private fun stateToShowError(
        currentState: PictureListStateView,
        action: PictureListAction.ShowError
    ) = currentState.copy(
        isLoading = false,
        error = action.error
    )
}