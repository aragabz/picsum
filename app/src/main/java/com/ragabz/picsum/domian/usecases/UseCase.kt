package com.ragabz.picsum.domian.usecases

interface UseCase<in P, R> {

    suspend fun invoke(params: P): R
}