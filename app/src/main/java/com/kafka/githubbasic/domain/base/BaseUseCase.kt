package com.kafka.githubbasic.domain.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

abstract class BaseUseCase<out Result, in Params> where Result : Any {

    internal abstract suspend fun run(params: Params): Flow<Result>

    operator fun invoke(
        params: Params,
        scope: CoroutineScope,
        onResult: (Result) -> Unit = {},
        onError: (Throwable) -> Unit = {}
    ) {
        scope.launch {
            run(params).flowOn(Dispatchers.IO).catch { onError(it) }.collect {
                onResult(it)
            }
        }
    }
}