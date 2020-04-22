package com.mastercard.developer;

import com.mastercard.developer.exception.ServiceException;
import com.mastercard.developer.executor.AccountExecutor;
import com.mastercard.developer.executor.UserExecutor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
class ApplicationTest {

    @InjectMocks
    private Application application;

    @Mock
    private UserExecutor userExecutor;

    @Mock
    private AccountExecutor accountExecutor;

    @Test
    void testRun() throws Exception {
        doNothing().when(userExecutor).execute();
        doNothing().when(accountExecutor).execute();

        application.run();

        verify(userExecutor, atMostOnce()).execute();
        verify(accountExecutor, atMostOnce()).execute();
    }

    @Test
    void testRunException() throws Exception {
        doThrow(new ServiceException("Some error occurred")).when(userExecutor).execute();

        application.run();

        verify(userExecutor, atMostOnce()).execute();
        verifyNoInteractions(accountExecutor);
    }
}
