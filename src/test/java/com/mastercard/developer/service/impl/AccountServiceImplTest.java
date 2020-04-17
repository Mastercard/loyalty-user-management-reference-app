package com.mastercard.developer.service.impl;

import com.mastercard.developer.example.AccountExample;
import okhttp3.Call;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiResponse;
import org.openapitools.client.model.AccountResponse;
import org.openapitools.client.model.AccountSearchResponse;
import org.openapitools.client.model.PagedAccountSearchResponse;
import org.springframework.http.HttpStatus;

import java.lang.reflect.Type;
import java.util.HashMap;

import static com.mastercard.developer.response.MockAccountResponses.ACCOUNT_ID;
import static com.mastercard.developer.response.MockAccountResponses.getEnrollOrUpdateResponse;
import static com.mastercard.developer.response.MockAccountResponses.getPagedSearchResponse;
import static com.mastercard.developer.response.MockAccountResponses.getSearchResponse;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
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
        when(apiClient.buildCall(anyString(), anyString(), anyList(), anyList(), any(), anyMap(), anyMap(), anyMap(), any(), any())).thenReturn(mock(Call.class));
    }

    @Test
    void testEnroll() throws Exception {
        when(apiClient.execute(any(Call.class), any(Type.class))).thenReturn(new ApiResponse<>(HttpStatus.CREATED.value(), new HashMap<>(), getEnrollOrUpdateResponse()));

        AccountResponse accountResponse = accountService.enroll(AccountExample.getAccountEnrollRequest());

        verify(apiClient, atMostOnce()).buildCall(anyString(), anyString(), anyList(), anyList(), any(), anyMap(), anyMap(), anyMap(), any(), any());
        verify(apiClient, atMostOnce()).execute(any(Call.class), any(Type.class));

        assertAll(
                () -> assertNotNull(accountResponse),
                () -> assertEquals(ACCOUNT_ID, accountResponse.getReferenceId())
        );
    }

    @Test
    void testFindById() throws Exception {
        AccountSearchResponse expected = getSearchResponse();
        when(apiClient.escapeString(anyString())).thenReturn(ACCOUNT_ID);
        when(apiClient.execute(any(Call.class), any(Type.class))).thenReturn(new ApiResponse<>(HttpStatus.OK.value(), new HashMap<>(), expected));

        AccountSearchResponse actual = accountService.findById(ACCOUNT_ID);

        verify(apiClient, atMostOnce()).buildCall(anyString(), anyString(), anyList(), anyList(), any(), anyMap(), anyMap(), anyMap(), any(), any());
        verify(apiClient, atMostOnce()).execute(any(Call.class), any(Type.class));

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected.getCompanyId(), actual.getCompanyId()),
                () -> assertEquals(expected.getUserId(), actual.getUserId()),
                () -> assertEquals(expected.getAccountId(), actual.getAccountId()),
                () -> assertEquals(expected.getAccountIdType(), actual.getAccountIdType()),
                () -> assertEquals(expected.getStatus(), actual.getStatus()),
                () -> assertEquals(expected.getProductCode(), actual.getProductCode()),
                () -> assertEquals(expected.getProgramIdentifier(), actual.getProgramIdentifier()),
                () -> assertEquals(expected.getOpenDate(), actual.getOpenDate()),
                () -> assertEquals(expected.getEnrollmentDate(), actual.getEnrollmentDate()),
                () -> assertEquals(expected.getReferenceId(), actual.getReferenceId())
        );
    }

    @Test
    void testSearch() throws Exception {
        when(apiClient.execute(any(Call.class), any(Type.class))).thenReturn(new ApiResponse<>(HttpStatus.OK.value(), new HashMap<>(), getPagedSearchResponse()));

        PagedAccountSearchResponse pagedSearchResponse = accountService.search(AccountExample.getAccountSearchRequest(), 10, 0);

        verify(apiClient, atMostOnce()).buildCall(anyString(), anyString(), anyList(), anyList(), any(), anyMap(), anyMap(), anyMap(), any(), any());
        verify(apiClient, atMostOnce()).execute(any(Call.class), any(Type.class));

        assertAll(
                () -> assertNotNull(pagedSearchResponse),
                () -> assertNotNull(pagedSearchResponse.getItems()),
                () -> assertEquals(1, pagedSearchResponse.getItems().size())
        );
        pagedSearchResponse.getItems().forEach(searchResponse -> assertAll(() -> assertNotNull(searchResponse),
                () -> assertNotNull(searchResponse.getCompanyId()),
                () -> assertNotNull(searchResponse.getUserId()),
                () -> assertNotNull(searchResponse.getAccountId()),
                () -> assertNotNull(searchResponse.getAccountIdType()),
                () -> assertNotNull(searchResponse.getStatus()),
                () -> assertNotNull(searchResponse.getProductCode()),
                () -> assertNotNull(searchResponse.getProgramIdentifier()),
                () -> assertNotNull(searchResponse.getOpenDate()),
                () -> assertNotNull(searchResponse.getEnrollmentDate()),
                () -> assertEquals(ACCOUNT_ID, searchResponse.getReferenceId())
        ));
    }

    @Test
    void testUpdate() throws Exception {
        when(apiClient.escapeString(anyString())).thenReturn(ACCOUNT_ID);
        when(apiClient.execute(any(Call.class), any(Type.class))).thenReturn(new ApiResponse<>(HttpStatus.OK.value(), new HashMap<>(), getEnrollOrUpdateResponse()));

        AccountResponse accountResponse = accountService.update(ACCOUNT_ID, AccountExample.getAccountUpdateRequest());

        verify(apiClient, atMostOnce()).buildCall(anyString(), anyString(), anyList(), anyList(), any(), anyMap(), anyMap(), anyMap(), any(), any());
        verify(apiClient, atMostOnce()).execute(any(Call.class), any(Type.class));

        assertAll(
                () -> assertNotNull(accountResponse),
                () -> assertEquals(ACCOUNT_ID, accountResponse.getReferenceId())
        );
    }
}
