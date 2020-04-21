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
import org.openapitools.client.model.PagedUserSearchResponse;
import org.openapitools.client.model.UserEnrollResponse;
import org.openapitools.client.model.UserSearchResponse;
import org.openapitools.client.model.UserUpdateResponse;
import org.springframework.http.HttpStatus;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import static com.mastercard.developer.response.MockAccountResponses.ACCOUNT_ID;
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
        when(apiClient.buildCall(anyString(), anyString(), anyList(), anyList(), any(), anyMap(), anyMap(), anyMap(), any(), any())).thenReturn(mock(Call.class));
    }

    @Test
    void testEnrollUserOnly() throws Exception {
        when(apiClient.execute(any(Call.class), any(Type.class))).thenReturn(new ApiResponse<>(HttpStatus.CREATED.value(), new HashMap<>(), getEnrollResponse(false)));

        UserEnrollResponse enrollResponse = userService.enrollUserOnly(UserExample.getUserEnrollRequest());

        verify(apiClient, atMostOnce()).buildCall(anyString(), anyString(), anyList(), anyList(), any(), anyMap(), anyMap(), anyMap(), any(), any());
        verify(apiClient, atMostOnce()).execute(any(Call.class), any(Type.class));

        assertAll(
                () -> assertNotNull(enrollResponse),
                () -> assertEquals(USER_ID, enrollResponse.getReferenceId())
        );
    }

    @Test
    void testEnrollUserAndAccount() throws Exception {
        when(apiClient.execute(any(Call.class), any(Type.class))).thenReturn(new ApiResponse<>(HttpStatus.CREATED.value(), new HashMap<>(), getEnrollResponse(true)));

        UserEnrollResponse enrollResponse = userService.enrollUserAndAccount(UserExample.getUserAndAccountEnrollRequest());

        verify(apiClient, atMostOnce()).buildCall(anyString(), anyString(), anyList(), anyList(), any(), anyMap(), anyMap(), anyMap(), any(), any());
        verify(apiClient, atMostOnce()).execute(any(Call.class), any(Type.class));

        assertAll(
                () -> assertNotNull(enrollResponse),
                () -> assertEquals(USER_ID, enrollResponse.getReferenceId()),
                () -> assertNotNull(enrollResponse.getAccount()),
                () -> assertEquals(ACCOUNT_ID, enrollResponse.getAccount().getReferenceId())
        );
    }

    @Test
    void testFindById() throws Exception {
        UserSearchResponse expected = getSearchResponse();
        when(apiClient.escapeString(anyString())).thenReturn(USER_ID);
        when(apiClient.execute(any(Call.class), any(Type.class))).thenReturn(new ApiResponse<>(HttpStatus.OK.value(), new HashMap<>(), expected));

        UserSearchResponse actual = userService.findById(USER_ID);

        verify(apiClient, atMostOnce()).buildCall(anyString(), anyString(), anyList(), anyList(), any(), anyMap(), anyMap(), anyMap(), any(), any());
        verify(apiClient, atMostOnce()).execute(any(Call.class), any(Type.class));

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected.getUserId(), actual.getUserId()),
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
                () -> assertEquals(expected.getGenericIdentification(), actual.getGenericIdentification()),
                () -> assertEquals(expected.getGenericIdentificationDescription(), actual.getGenericIdentificationDescription()),
                () -> assertEquals(expected.getAddress(), actual.getAddress()),
                () -> assertEquals(expected.getReferenceId(), actual.getReferenceId())
        );
    }

    @Test
    void testSearch() throws Exception {
        when(apiClient.execute(any(Call.class), any(Type.class))).thenReturn(new ApiResponse<>(HttpStatus.OK.value(), new HashMap<>(), getPagedSearchResponse()));

        PagedUserSearchResponse pagedSearchResponse = userService.search(UserExample.getUserSearchRequest(), 10, 0);

        verify(apiClient, atMostOnce()).buildCall(anyString(), anyString(), anyList(), anyList(), any(), anyMap(), anyMap(), anyMap(), any(), any());
        verify(apiClient, atMostOnce()).execute(any(Call.class), any(Type.class));

        assertAll(
                () -> assertNotNull(pagedSearchResponse),
                () -> assertNotNull(pagedSearchResponse.getItems()),
                () -> assertEquals(1, pagedSearchResponse.getItems().size())
        );
        pagedSearchResponse.getItems().forEach(searchResponse -> assertAll(() -> assertNotNull(searchResponse),
                () -> assertNotNull(searchResponse.getUserId()),
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
                () -> assertNotNull(searchResponse.getGenericIdentification()),
                () -> assertNotNull(searchResponse.getGenericIdentificationDescription()),
                () -> assertNotNull(searchResponse.getAddress()),
                () -> assertEquals(USER_ID, searchResponse.getReferenceId())
        ));
    }

    @Test
    void testUpdate() throws Exception {
        when(apiClient.escapeString(anyString())).thenReturn(USER_ID);
        when(apiClient.execute(any(Call.class), any(Type.class))).thenReturn(new ApiResponse<>(HttpStatus.OK.value(), new HashMap<>(), getUpdateResponse()));

        UserUpdateResponse updateResponse = userService.update(USER_ID, UserExample.getUserUpdateRequest());

        verify(apiClient, atMostOnce()).buildCall(anyString(), anyString(), anyList(), anyList(), any(), anyMap(), anyMap(), anyMap(), any(), any());
        verify(apiClient, atMostOnce()).execute(any(Call.class), any(Type.class));

        assertAll(
                () -> assertNotNull(updateResponse),
                () -> assertEquals(USER_ID, updateResponse.getReferenceId())
        );
    }

    @Test
    void testErrorResponse() throws Exception {
        when(apiClient.execute(any(Call.class), any(Type.class))).thenThrow(new ApiException(400, new HashMap<>(), getErrorResponseBody()));

        ServiceException serviceException = Assertions.assertThrows(ServiceException.class, () -> userService.enrollUserAndAccount(UserExample.getUserAndAccountEnrollRequest()));

        verify(apiClient, atMostOnce()).buildCall(anyString(), anyString(), anyList(), anyList(), any(), anyMap(), anyMap(), anyMap(), any(), any());
        verify(apiClient, atMostOnce()).execute(any(Call.class), any(Type.class));

        Assertions.assertNotNull(serviceException.getServiceErrors());
        List<Error> errors = serviceException.getServiceErrors().getErrors().getError();
        Assertions.assertFalse(errors.isEmpty());
        errors.forEach(error -> {
            Assertions.assertEquals("Loyalty-Enrollment", error.getSource());
            Assertions.assertEquals("DUPLICATE_REQUEST", error.getReasonCode());
            Assertions.assertEquals("The User has already exists for the given Company ID.", error.getDescription());
            Assertions.assertFalse(error::getRecoverable);
        });
    }
}
