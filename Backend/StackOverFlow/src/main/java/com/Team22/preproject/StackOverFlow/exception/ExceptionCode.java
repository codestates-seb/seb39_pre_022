package com.Team22.preproject.StackOverFlow.exception;

import lombok.Getter;

public enum ExceptionCode {
  MEMBER_NOT_FOUND(404, "Member not found"),
  MEMBER_ALREADY_EXISTS(409, "Member already exists"),
  PASSWORD_INCORRECT(404, "Password Incorrect"),
  FIELD_ERROR(400, "Field Error"),
  CONSTRAINT_VIOLATION_ERROR(400, "Constraint Violation Error"),
  NICKNAME_NOT_FOUND(404, "NickName Not Found"),
  QUESTION_NOT_FOUND(404, "Question Not Found" ),
  ANSWER_NOT_FOUND(404, "Answer Not Found" );


  @Getter
  private int status;

  @Getter
  private String message;

  ExceptionCode(int code, String message) {
    this.status = code;
    this.message = message;
  }
}
