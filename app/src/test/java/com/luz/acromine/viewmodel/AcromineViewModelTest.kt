package com.luz.acromine.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.luz.acromine.api.model.Lf
import com.luz.acromine.repository.AcromineRepository
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito.*
import org.mockito.kotlin.verify

class AcromineViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private var repository = mock(AcromineRepository::class.java)
    private val     private val = mock(List::class.java) as List<Lf>


            @Test
    fun callAcronymsFromRepository() = runTest{
        val s = "TI"
        val vm = AcromineViewModel(repository)
        vm.getAcronymList(s)
        verify(repository, times(1)).acronymList(s)
    }

    @Test
    fun emitDataWhenGetDataFromRepository() = runTest{
        val s = "TI"
        `when`(repository.getData()).thenReturn(expectedList)
        val vm = AcromineViewModel(repository)
        vm.livedataAcronym.captureValues{
            vm.getAcronymList(s)
            advanceUntilIdle()
            assertEquals(expectedList, values.first())
        }
    }
}