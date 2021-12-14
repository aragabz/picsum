package com.ragabz.picsum.domian.usecases

import com.ragabz.picsum.base.Result
import com.ragabz.picsum.base.onSuccess
import com.ragabz.picsum.domian.repositories.PicturesRepository
import com.ragabz.picsum.models.PictureList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class GetPictureListUseCase @Inject constructor(
    private val picturesRepository: PicturesRepository
) : UseCase<Int, Flow<Result<PictureList>>> {

    override suspend fun invoke(params: Int): Flow<Result<PictureList>> {
        with(picturesRepository) {
            val remoteFlow = getPictures(params)
            return remoteFlow.onEach { result ->
                result.onSuccess { savePicturesList(list = *it.toTypedArray()) }
            }
        }
    }
}