package com.Team22.preproject.StackOverFlow.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PageInfo {
  private int page;
  private int size;
  private long totalElements;
  private int totalPages;
}
