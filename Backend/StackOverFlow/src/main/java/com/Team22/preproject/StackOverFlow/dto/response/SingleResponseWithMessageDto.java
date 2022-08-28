package com.Team22.preproject.StackOverFlow.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SingleResponseWithMessageDto<T> {
  private T data;
  private String message;
}
