package com.mastercard.developer.service.impl;

import com.mastercard.developer.example.UserExample;
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
import org.openapitools.client.model.Error;
import org.openapitools.client.model.PagedResponseOfUserSearchResponse;
import org.openapitools.client.model.UserEnrollResponse;
import org.openapitools.client.model.UserSearchResponse;
import org.openapitools.client.model.UserUpdateResponse;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static com.mastercard.developer.response.MockAccountResponses.ACCOUNT_ID;
import static com.mastercard.developer.response.MockUserResponses.DUP_REQ_DESCRIPTION;
import static com.mastercard.developer.response.MockUserResponses.DUP_REQ_REASON_CODE;
import static com.mastercard.developer.response.MockUserResponses.SER_ERR_DESCRIPTION;
import static com.mastercard.developer.response.MockUserResponses.SER_ERR_REASON_CODE;
import static com.mastercard.developer.response.MockUserResponses.SOURCE;
import static com.mastercard.developer.response.MockUserResponses.USER_ID;
import static com.mastercard.developer.response.MockUserResponses.getEnrollResponse;
import static com.mastercard.developer.response.MockUserResponses.getErrorResponseBody;
import static com.mastercard.developer.response.MockUserResponses.getPagedSearchResponse;
import static com.mastercard.developer.response.MockUserResponses.getSearchResponse;
import static com.mastercard.developer.response.MockUserResponses.getUpdateResponse;
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
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private ApiClient apiClient;

    @BeforeEach
    void setUp() throws Exception {
        when(apiClient.buildCall(nullable(String.class), anyString(), anyString(), anyList(), anyList(), any(), anyMap(), anyMap(), anyMap(), any(), any())).thenReturn(mock(Call.class));
    }

    @Test
    void testEnrollUserOnly() throws Exception {
        when(apiClient.execute(any(Call.class), any(Type.class))).thenReturn(new ApiResponse<>(201, new HashMap<>(), getEnrollResponse(false)));

        UserEnrollResponse enrollResponse = userService.enrollUserOnly(UserExample.getUserEnrollRequest());

        verify(apiClient, atMostOnce()).buildCall(anyString(), anyString(), anyString(), anyList(), anyList(), any(), anyMap(), anyMap(), anyMap(), any(), any());
        verify(apiClient, atMostOnce()).execute(any(Call.class), any(Type.class));

        assertAll(
                () -> assertNotNull(enrollResponse),
                () -> assertEquals(USER_ID, enrollResponse.getId())
        );
    }

    @Test
    void testEnrollUserAndAccount() throws Exception {
        when(apiClient.execute(any(Call.class), any(Type.class))).thenReturn(new ApiResponse<>(201, new HashMap<>(), getEnrollResponse(true)));

        UserEnrollResponse enrollResponse = userService.enrollUserAndAccount(UserExample.getUserAndAccountEnrollRequest());

        verify(apiClient, atMostOnce()).buildCall(anyString(), anyString(), anyString(), anyList(), anyList(), any(), anyMap(), anyMap(), anyMap(), any(), any());
        verify(apiClient, atMostOnce()).execute(any(Call.class), any(Type.class));

        assertAll(
                () -> assertNotNull(enrollResponse),
                () -> assertEquals(USER_ID, enrollResponse.getId()),
                () -> assertNotNull(enrollResponse.getAccount()),
                () -> assertEquals(ACCOUNT_ID, enrollResponse.getAccount().getId())
        );
    }

    @Test
    void testFindById() throws Exception {
        UserSearchResponse expected = getSearchResponse();
        when(apiClient.escapeString(anyString())).thenReturn(USER_ID);
        when(apiClient.execute(any(Call.class), any(Type.class))).thenReturn(new ApiResponse<>(200, new HashMap<>(), expected));

        UserSearchResponse actual = userService.findById(USER_ID);

        verify(apiClient, atMostOnce()).buildCall(anyString(), anyString(), anyString(), anyList(), anyList(), any(), anyMap(), anyMap(), anyMap(), any(), any());
        verify(apiClient, atMostOnce()).execute(any(Call.class), any(Type.class));

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected.getUserNumber(), actual.getUserNumber()),
                () -> assertEquals(expected.getFirstName(), actual.getFirstName()),
                () -> assertEquals(expected.getLastName(), actual.getLastName()),
                () -> assertEquals(expected.getMotherMaidenName(), actual.getMotherMaidenName()),
                () -> assertEquals(expected.getGender(), actual.getGender()),
                () -> assertEquals(expected.getBirthDate(), actual.getBirthDate()),
                () -> assertEquals(expected.getNationalIdentifier(), actual.getNationalIdentifier()),
                () -> assertEquals(expected.getEmailAddress(), actual.getEmailAddress()),
                () -> assertEquals(expected.getBusinessPhoneNumber(), actual.getBusinessPhoneNumber()),
                () -> assertEquals(expected.getHomePhoneNumber(), actual.getHomePhoneNumber()),
                () -> assertEquals(expected.getMobilePhoneNumber(), actual.getMobilePhoneNumber()),
                () -> assertEquals(expected.getVip(), actual.getVip()),
                () -> assertEquals(expected.getEmployee(), actual.getEmployee()),
                () -> assertEquals(expected.getAddress(), actual.getAddress()),
                () -> assertEquals(expected.getId(), actual.getId())
        );
    }

    @Test
    void testSearch() throws Exception {
        when(apiClient.execute(any(Call.class), any(Type.class))).thenReturn(new ApiResponse<>(200, new HashMap<>(), getPagedSearchResponse()));

        PagedResponseOfUserSearchResponse pagedSearchResponse = userService.search(0, 10, UserExample.getUserSearchRequest());

        verify(apiClient, atMostOnce()).buildCall(anyString(), anyString(), anyString(), anyList(), anyList(), any(), anyMap(), anyMap(), anyMap(), any(), any());
        verify(apiClient, atMostOnce()).execute(any(Call.class), any(Type.class));

        assertAll(
                () -> assertNotNull(pagedSearchResponse),
                () -> assertNotNull(pagedSearchResponse.getItems()),
                () -> assertEquals(1, pagedSearchResponse.getItems().size())
        );
        pagedSearchResponse.getItems().forEach(searchResponse -> assertAll(() -> assertNotNull(searchResponse),
                () -> assertNotNull(searchResponse.getUserNumber()),
                () -> assertNotNull(searchResponse.getFirstName()),
                () -> assertNotNull(searchResponse.getLastName()),
                () -> assertNotNull(searchResponse.getMotherMaidenName()),
                () -> assertNotNull(searchResponse.getGender()),
                () -> assertNotNull(searchResponse.getBirthDate()),
                () -> assertNotNull(searchResponse.getNationalIdentifier()),
                () -> assertNotNull(searchResponse.getEmailAddress()),
                () -> assertNotNull(searchResponse.getBusinessPhoneNumber()),
                () -> assertNotNull(searchResponse.getHomePhoneNumber()),
                () -> assertNotNull(searchResponse.getMobilePhoneNumber()),
                () -> assertNotNull(searchResponse.getVip()),
                () -> assertNotNull(searchResponse.getEmployee()),
                () -> assertNotNull(searchResponse.getAddress()),
                () -> assertEquals(USER_ID, searchResponse.getId())
        ));
    }

    @Test
    void testUpdate() throws Exception {
        when(apiClient.escapeString(anyString())).thenReturn(USER_ID);
        when(apiClient.execute(any(Call.class), any(Type.class))).thenReturn(new ApiResponse<>(200, new HashMap<>(), getUpdateResponse()));

        UserUpdateResponse updateResponse = userService.update(USER_ID, UserExample.getUserUpdateRequest());

        verify(apiClient, atMostOnce()).buildCall(anyString(), anyString(), anyString(), anyList(), anyList(), any(), anyMap(), anyMap(), anyMap(), any(), any());
        verify(apiClient, atMostOnce()).execute(any(Call.class), any(Type.class));

        assertAll(
                () -> assertNotNull(updateResponse),
                () -> assertEquals(USER_ID, updateResponse.getId())
        );
    }

    @Test
    void testEnrollUserErrorResponse() throws Exception {
        when(apiClient.execute(any(Call.class), any(Type.class))).thenThrow(new ApiException(400, new HashMap<>(), getErrorResponseBody(DUP_REQ_REASON_CODE, DUP_REQ_DESCRIPTION, false)));

        ServiceException serviceException = Assertions.assertThrows(ServiceException.class, () -> userService.enrollUserOnly(UserExample.getUserEnrollRequest()));

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
    void testEnrollUserAndAccountErrorResponse() throws Exception {
        when(apiClient.execute(any(Call.class), any(Type.class))).thenThrow(new ApiException(400, new HashMap<>(), getErrorResponseBody(DUP_REQ_REASON_CODE, DUP_REQ_DESCRIPTION, false)));

        ServiceException serviceException = Assertions.assertThrows(ServiceException.class, () -> userService.enrollUserAndAccount(UserExample.getUserAndAccountEnrollRequest()));

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
        when(apiClient.escapeString(anyString())).thenReturn(USER_ID);
        when(apiClient.execute(any(Call.class), any(Type.class))).thenThrow(new ApiException(400, new HashMap<>(), getErrorResponseBody(SER_ERR_REASON_CODE, SER_ERR_DESCRIPTION, false)));

        ServiceException serviceException = Assertions.assertThrows(ServiceException.class, () -> userService.findById(UUID.randomUUID().toString()));

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
