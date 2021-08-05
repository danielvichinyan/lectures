package com.knowit.lectures.exceptions;

import com.knowit.lectures.constants.ExceptionConstants;

public class QuizDoesNotExist extends Throwable {

    public QuizDoesNotExist() {
        super(ExceptionConstants.QUIZ_DOES_NOT_EXIST);
    }
}
