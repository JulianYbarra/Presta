package com.junka.presta.core.domain

import com.junka.domain.Resource
import com.junka.presta.core.testing.data.scoreTestData
import com.junka.presta.core.testing.repository.ScoreRepositoryFake
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.Test

class ScoreUseCaseTest{

    @Test
    fun `when invoke is called, then call repository score`(){
        val expected = scoreTestData
        val repository = ScoreRepositoryFake(expected)
        val useCase = ScoreUseCase(repository)

        val result = runBlocking{ useCase(99778667) }

        result shouldBeInstanceOf Resource.Success::class
        result shouldBeEqualTo expected
    }
}