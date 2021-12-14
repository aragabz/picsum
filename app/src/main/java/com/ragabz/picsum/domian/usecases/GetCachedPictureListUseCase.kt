package com.ragabz.picsum.domian.usecases

import com.ragabz.picsum.base.Result
import com.ragabz.picsum.base.onSuccess
import com.ragabz.picsum.domian.repositories.PicturesRepository
import com.ragabz.picsum.models.PictureList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class GetCachedPictureListUseCase @Inject constructor(
    private val picturesRepository: PicturesRepository
) : UseCase<Unit, Flow<Result<PictureList>>> {

    override suspend fun invoke(params: Unit): Flow<Result<PictureList>> {
        return with(picturesRepository) {
            getCachedPictures()
        }
    }
}