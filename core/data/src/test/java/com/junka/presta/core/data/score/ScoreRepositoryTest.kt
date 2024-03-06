package com.junka.presta.core.data.score

import com.junka.presta.core.common.Resource
import com.junka.presta.core.testing.data.scoreTestData
import com.junka.presta.core.testing.datasource.FakeScoreDataSource
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.Test

class ScoreRepositoryTest {

    @Test
    fun `when score is called, then return from remote source`() {
        val dataSource = FakeScoreDataSource(scoreTestData)
        val repository = ScoreRepositoryImp(dataSource)

        val result = runBlocking { repository.getScore(99778667) }

        result shouldBeInstanceOf Resource.Success::class
        (result as Resource.Success).data shouldBeEqualTo scoreTestData
    }
}