package com.kyk.soundstory.results.user;

import com.kyk.soundstory.results.Result;

public enum RegisterResult implements Result {
    FAILURE_DUPLICATE_EMAIL,
    FAILURE_DUPLICATE_NICKNAME
}
