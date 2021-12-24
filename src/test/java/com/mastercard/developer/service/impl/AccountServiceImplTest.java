package com.mastercard.developer.service.impl;

import com.mastercard.developer.example.AccountExample;
import com.mastercard.developer.exception.ServiceException;
import okhttp3.Call;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.ApiResponse;
import org.openapitools.client.model.AccountResponse;
import org.openapitools.client.model.AccountSearchResponse;
import org.openapitools.client.model.Error;
import org.openapitools.client.model.PagedResponseOfAccountSearchResponse;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static com.mastercard.developer.response.MockAccountResponses.ACCOUNT_ID;
import static com.mastercard.developer.response.MockAccountResponses.DUP_REQ_DESCRIPTION;
import static com.mastercard.developer.response.MockAccountResponses.SER_ERR_DESCRIPTION;
import static com.mastercard.developer.response.MockAccountResponses.getEnrollOrUpdateResponse;
import static com.mastercard.developer.response.MockAccountResponses.getPagedSearchResponse;
import static com.mastercard.developer.response.MockAccountResponses.getSearchResponse;
import static com.mastercard.developer.response.MockUserResponses.DUP_REQ_REASON_CODE;
import static com.mastercard.developer.response.MockUserResponses.SER_ERR_REASON_CODE;
import static com.mastercard.developer.response.MockUserResponses.SOURCE;
import static com.mastercard.developer.response.MockUserResponses.getErrorResponseBody;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @InjectMocks
    private AccountServiceImpl accountService;

    @Mock
    private ApiClient apiClient;

    @BeforeEach
    void setUp() throws Exception {
        when(apiClient.buildCall(nullable(String.class), anyString(), anyString(), anyList(), anyList(), any(), anyMap(), anyMap(), anyMap(), any(), any())).thenReturn(mock(Call.class));
    }

    @Test
    void testEnroll() throws Exception {
        when(apiClient.execute(any(Call.class), any(Type.class))).thenReturn(new ApiResponse<>(201, new HashMap<>(), getEnrollOrUpdateResponse()));

        AccountResponse accountResponse = accountService.enroll(AccountExample.getAccountEnrollRequest());

        verify(apiClient, atMostOnce()).buildCall(anyString(), anyString(), anyString(), anyList(), anyList(), any(), anyMap(), anyMap(), anyMap(), any(), any());
        verify(apiClient, atMostOnce()).execute(any(Call.class), any(Type.class));

        assertAll(
                () -> assertNotNull(accountResponse),
                () -> assertEquals(ACCOUNT_ID, accountResponse.getId())
        );
    }

    @Test
    void testFindById() throws Exception {
        AccountSearchResponse expected = getSearchResponse();
        when(apiClient.escapeString(anyString())).thenReturn(ACCOUNT_ID);
        when(apiClient.execute(any(Call.class), any(Type.class))).thenReturn(new ApiResponse<>(200, new HashMap<>(), expected));

        AccountSearchResponse actual = accountService.findById(ACCOUNT_ID);

        verify(apiClient, atMostOnce()).buildCall(anyString(), anyString(), anyString(), anyList(), anyList(), any(), anyMap(), anyMap(), anyMap(), any(), any());
        verify(apiClient, atMostOnce()).execute(any(Call.class), any(Type.class));

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected.getCompanyId(), actual.getCompanyId()),
                () -> assertEquals(expected.getUserNumber(), actual.getUserNumber()),
                () -> assertEquals(expected.getAccountNumber(), actual.getAccountNumber()),
                () -> assertEquals(expected.getStatus(), actual.getStatus()),
                () -> assertEquals(expected.getProductCode(), actual.getProductCode()),
                () -> assertEquals(expected.getProgramEnrollmentCode(), actual.getProgramEnrollmentCode()),
                () -> assertEquals(expected.getOpenDate(), actual.getOpenDate()),
                () -> assertEquals(expected.getEnrollmentDate(), actual.getEnrollmentDate()),
                () -> assertEquals(expected.getId(), actual.getId())
        );
    }

    @Test
    void testSearch() throws Exception {
        when(apiClient.execute(any(Call.class), any(Type.class))).thenReturn(new ApiResponse<>(200, new HashMap<>(), getPagedSearchResponse()));

        PagedResponseOfAccountSearchResponse pagedSearchResponse = accountService.search(0, 10, AccountExample.getAccountSearchRequest());

        verify(apiClient, atMostOnce()).buildCall(anyString(), anyString(), anyString(), anyList(), anyList(), any(), anyMap(), anyMap(), anyMap(), any(), any());
        verify(apiClient, atMostOnce()).execute(any(Call.class), any(Type.class));

        assertAll(
                () -> assertNotNull(pagedSearchResponse),
                () -> assertNotNull(pagedSearchResponse.getItems()),
                () -> assertEquals(1, pagedSearchResponse.getItems().size())
        );
        pagedSearchResponse.getItems().forEach(searchResponse -> assertAll(() -> assertNotNull(searchResponse),
                () -> assertNotNull(searchResponse.getCompanyId()),
                () -> assertNotNull(searchResponse.getUserNumber()),
                () -> assertNotNull(searchResponse.getAccountNumber()),
                () -> assertNotNull(searchResponse.getStatus()),
                () -> assertNotNull(searchResponse.getProductCode()),
                () -> assertNotNull(searchResponse.getProgramEnrollmentCode()),
                () -> assertNotNull(searchResponse.getOpenDate()),
                () -> assertNotNull(searchResponse.getEnrollmentDate()),
                () -> assertEquals(ACCOUNT_ID, searchResponse.getId())
        ));
    }

    @Test
    void testUpdate() throws Exception {
        when(apiClient.escapeString(anyString())).thenReturn(ACCOUNT_ID);
        when(apiClient.execute(any(Call.class), any(Type.class))).thenReturn(new ApiResponse<>(200, new HashMap<>(), getEnrollOrUpdateResponse()));

        AccountResponse accountResponse = accountService.update(ACCOUNT_ID, AccountExample.getAccountUpdateRequest());

        verify(apiClient, atMostOnce()).buildCall(anyString(), anyString(), anyString(), anyList(), anyList(), any(), anyMap(), anyMap(), anyMap(), any(), any());
        verify(apiClient, atMostOnce()).execute(any(Call.class), any(Type.class));

        assertAll(
                () -> assertNotNull(accountResponse),
                () -> assertEquals(ACCOUNT_ID, accountResponse.getId())
        );
    }

    @Test
    void testEnrollErrorResponse() throws Exception {
        when(apiClient.execute(any(Call.class), any(Type.class))).thenThrow(new ApiException(400, new HashMap<>(), getErrorResponseBody(DUP_REQ_REASON_CODE, DUP_REQ_DESCRIPTION, false)));

        ServiceException serviceException = Assertions.assertThrows(ServiceException.class, () -> accountService.enroll(AccountExample.getAccountEnrollRequest()));

        verify(apiClient, atMostOnce()).buildCall(anyString(), anyString(), anyString(), anyList(), anyList(), any(), anyMap(), anyMap(), anyMap(), any(), any());
        verify(apiClient, atMostOnce()).execute(any(Call.class), any(Type.class));

        Assertions.assertNotNull(serviceException.getServiceErrors());
        List<Error> errors = serviceException.getServiceErrors().getErrors().getError();
        Assertions.assertFalse(errors.isEmpty());
        errors.forEach(error -> {
            Assertions.assertEquals(SOURCE, error.getSource());
            Assertions.assertEquals(DUP_REQ_REASON_CODE, error.getReasonCode());
            Assertions.assertEquals(DUP_REQ_DESCRIPTION, error.getDescription());
            Assertions.assertFalse(error::getRecoverable);
        });
    }

    @Test
    void testFindByIdErrorResponse() throws Exception {
        when(apiClient.escapeString(anyString())).thenReturn(ACCOUNT_ID);
        when(apiClient.execute(any(Call.class), any(Type.class))).thenThrow(new ApiException(400, new HashMap<>(), getErrorResponseBody(SER_ERR_REASON_CODE, SER_ERR_DESCRIPTION, false)));

        ServiceException serviceException = Assertions.assertThrows(ServiceException.class, () -> accountService.findById(UUID.randomUUID().toString()));

        verify(apiClient, atMostOnce()).buildCall(anyString(), anyString(), anyString(), anyList(), anyList(), any(), anyMap(), anyMap(), anyMap(), any(), any());
        verify(apiClient, atMostOnce()).execute(any(Call.class), any(Type.class));

        Assertions.assertNotNull(serviceException.getServiceErrors());
        List<Error> errors = serviceException.getServiceErrors().getErrors().getError();
        Assertions.assertFalse(errors.isEmpty());
        errors.forEach(error -> {
            Assertions.assertEquals(SOURCE, error.getSource());
            Assertions.assertEquals(SER_ERR_REASON_CODE, error.getReasonCode());
            Assertions.assertEquals(SER_ERR_DESCRIPTION, error.getDescription());
            Assertions.assertFalse(error::getRecoverable);
        });
    }
}
