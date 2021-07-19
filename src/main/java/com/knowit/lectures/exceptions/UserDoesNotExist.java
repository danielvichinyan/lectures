package com.knowit.lectures.exceptions;

import com.knowit.lectures.constants.ExceptionConstants;

public class UserDoesNotExist extends Throwable{

    public UserDoesNotExist() { super(ExceptionConstants.THIS_USER_DOES_NOT_EXIST); }
}
