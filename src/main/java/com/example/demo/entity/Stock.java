package com.example.demo.entity;
import lombok.Data;

@Data
public class Stock {
  private String entityCode;
  private String operationType;
  private double quantity;
  private String skuCode;
}
