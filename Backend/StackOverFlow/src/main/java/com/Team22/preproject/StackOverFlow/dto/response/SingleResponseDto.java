package com.Team22.preproject.StackOverFlow.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SingleResponseDto<T> {
  private T data;
}
