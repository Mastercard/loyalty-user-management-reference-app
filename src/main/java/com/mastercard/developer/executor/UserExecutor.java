package com.mastercard.developer.executor;

import com.mastercard.developer.example.UserExample;
import com.mastercard.developer.exception.ServiceException;
import com.mastercard.developer.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.client.model.PagedResponseOfUserSearchResponse;
import org.openapitools.client.model.UserEnrollResponse;
import org.openapitools.client.model.UserSearchResponse;
import org.openapitools.client.model.UserUpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This is demo executor class to represent how the User operations can be perform.
 * You can have your own implementation logic to call User API.
 */

@Slf4j
@Component
public class UserExecutor {

    private final UserService userService;

    @Autowired
    public UserExecutor(UserService userService) {
        this.userService = userService;
    }

    public void execute() throws ServiceException {
        log.info("<<<---- USER API EXECUTION STARTED ---->>>");

        UserEnrollResponse userEnrollResponse = enrollUser();

        searchUserById(userEnrollResponse.getId());

        searchUser();

        updateUser(userEnrollResponse.getId());

        UserEnrollResponse userAndAccountEnrollResponse = enrollUserAndAccount();

        searchUserById(userAndAccountEnrollResponse.getId());

        log.info("<<<---- USER API EXECUTION COMPLETED ---->>>");
    }

    /**
     * USE CASE 1: USER ENROLLMENT
     * It will enroll User's demographic details into Mastercard Rewards Platform
     *
     * @return An instance of UserEnrollResponse
     */
    private UserEnrollResponse enrollUser() throws ServiceException {
        return userService.enrollUserOnly(UserExample.getUserEnrollRequest());
    }

    /**
     * USE CASE 2: USER WITH ACCOUNT ENROLLMENT
     * It will enroll User's demographic and Account details into Mastercard Rewards Platform
     *
     * @return An instance of UserEnrollResponse
     */
    private UserEnrollResponse enrollUserAndAccount() throws ServiceException {
        return userService.enrollUserAndAccount(UserExample.getUserAndAccountEnrollRequest());
    }

    /**
     * USE CASE 3: USER SEARCH
     * It will retrieves User’s demographic details based on provided search criteria
     *
     * @return An instance of PagedResponseOfUserSearchResponse
     */
    private PagedResponseOfUserSearchResponse searchUser() throws ServiceException {
        return userService.search(0, 25, UserExample.getUserSearchRequest());
    }

    /**
     * USE CASE 4: USER SEARCH BY MASTERCARD GENERATED UNIQUE ID
     * It will retrieves User’s demographic details by Mastercard generated unique Id
     *
     * @return An instance of UserEnrollResponse
     */
    private UserSearchResponse searchUserById(String id) throws ServiceException {
        return userService.findById(id);
    }

    /**
     * USE CASE 5: USER UPDATE
     * It will updates User's demographic details into Mastercard Rewards Platform
     *
     * @return An instance of UserUpdateResponse
     */
    private UserUpdateResponse updateUser(String id) throws ServiceException {
        return userService.update(id, UserExample.getUserUpdateRequest());
    }
}
