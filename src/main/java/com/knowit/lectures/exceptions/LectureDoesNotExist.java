package com.knowit.lectures.exceptions;

import com.knowit.lectures.constants.ExceptionConstants;

public class LectureDoesNotExist extends Throwable {

    public LectureDoesNotExist() { super(ExceptionConstants.THIS_LECTURE_DOES_NOT_EXIST); }
}
