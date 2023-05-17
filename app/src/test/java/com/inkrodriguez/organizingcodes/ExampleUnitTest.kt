package com.inkrodriguez.organizingcodes

import android.content.Context
import com.inkrodriguez.organizingcodes.test.ContextWrapper
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

private const val FAKE_STRING = "Login"

@RunWith(MockitoJUnitRunner::class)
class MockedContextTest {

    @Mock
    private lateinit var mockContext: Context

    @Test
    fun readStringFromContext_LocalizedString() {

        // Given a mocked Context injected into the object under test...
        `when`(mockContext.getString(R.string.login)).thenReturn(FAKE_STRING)

        val myObjectUnderTest = ContextWrapper(mockContext)

        // ...when the string is returned from the object under test...
        val result: String = myObjectUnderTest.getLogin()

        // ...then the result should be the expected one.
        assertEquals(result, FAKE_STRING)
    }
}