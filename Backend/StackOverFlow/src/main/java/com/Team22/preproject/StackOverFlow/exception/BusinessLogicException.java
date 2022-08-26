package com.Team22.preproject.StackOverFlow.exception;

import lombok.Getter;

public class BusinessLogicException extends RuntimeException{
  @Getter
  private ExceptionCode exceptionCode;

  public BusinessLogicException(ExceptionCode exceptionCode) {
    super(exceptionCode.getMessage());
    this.exceptionCode = exceptionCode;
  }
}
