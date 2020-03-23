package com.mastercard.developer.executor;

import com.mastercard.developer.example.UserExample;
import com.mastercard.developer.exception.ServiceException;
import com.mastercard.developer.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.client.model.UserEnrollResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserExecutor {

    private UserService userService;

    @Autowired
    public UserExecutor(UserService userService) {
        this.userService = userService;
    }

    public void execute() throws ServiceException {
        log.info("<<<---- USER API EXECUTION STARTED ---->>>");
        UserEnrollResponse userEnrollResponse = userService.enrollUserOnly(UserExample.getUserEnrollRequest());

        userService.findById(userEnrollResponse.getReferenceId());

        userService.search(UserExample.getUserSearchRequest(), 25, 0);

        userService.update(userEnrollResponse.getReferenceId(), UserExample.getUserUpdateRequest());

        UserEnrollResponse userAndAccountEnrollResponse = userService.enrollUserAndAccount(UserExample.getUserAndAccountEnrollRequest());

        userService.findById(userAndAccountEnrollResponse.getReferenceId());
        log.info("<<<---- USER API EXECUTION COMPLETED ---->>>");
    }
}
