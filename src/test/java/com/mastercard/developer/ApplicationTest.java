package com.mastercard.developer;

import com.mastercard.developer.executor.AccountExecutor;
import com.mastercard.developer.executor.UserExecutor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

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
}
