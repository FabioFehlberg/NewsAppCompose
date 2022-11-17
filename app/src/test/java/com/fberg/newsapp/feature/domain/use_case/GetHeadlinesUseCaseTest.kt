package com.fberg.newsapp.feature.domain.use_case

import com.fberg.newsapp.common.NewsException
import com.fberg.newsapp.common.Resource
import com.fberg.newsapp.feature.data.reporitory.FakeNewsRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetHeadlinesUseCaseTest {

    private lateinit var getHeadlinesUseCase: GetHeadlinesUseCase

    @Before
    fun setUp() {
        getHeadlinesUseCase = GetHeadlinesUseCase(FakeNewsRepository())
    }

    @Test
    fun `Get list success, only category`() = runBlocking {
        val headlines = getHeadlinesUseCase("notBlank", "", "", false).first()

        assert(headlines is Resource.Success)
    }

    @Test
    fun `Get list success, only search`() = runBlocking {
        val headlines = getHeadlinesUseCase("", "notBlank", "", false).first()

        assert(headlines is Resource.Success)
    }

    @Test
    fun `Get list success, should refresh when action is refresh`() = runBlocking {
        val headlines = getHeadlinesUseCase("notBlank", "", "", true).first()

        assert((headlines as Resource.Success).shouldResetList)
    }

    @Test
    fun `Get list success, should refresh when changing category`() = runBlocking {
        getHeadlinesUseCase("first", "", "", false).first()
        val headlines = getHeadlinesUseCase("notBlank", "", "", false).first()

        assert((headlines as Resource.Success).shouldResetList)
    }

    @Test
    fun `Get list success, should refresh when changing search`() = runBlocking {
        getHeadlinesUseCase("", "first", "", false).first()
        val headlines = getHeadlinesUseCase("", "notBlank", "", false).first()

        assert((headlines as Resource.Success).shouldResetList)
    }

    @Test
    fun `Get list success, should refresh when changing country`() = runBlocking {
        getHeadlinesUseCase("notBlank", "", "br", false).first()
        val headlines = getHeadlinesUseCase("notBlank", "", "de", false).first()

        assert((headlines as Resource.Success).shouldResetList)
    }

    @Test
    fun `Get list error, category and search null or blank should return domain exception`() = runBlocking {
        val headlines = getHeadlinesUseCase("", "", "", false).first()

        assert((headlines as Resource.Error).exception is NewsException.DomainException)
    }

    @Test
    fun `Get list error, forced data exception`() = runBlocking {
        getHeadlinesUseCase("notBlank", "", "", false).first()
        val headlines = getHeadlinesUseCase("notBlank", "", "", false).first()

        assert((headlines as Resource.Success).data.articles.size == 2)
    }
}
