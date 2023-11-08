package com.example.demo.entity;
import java.util.List;
import lombok.Data;

@Data
public class StockInput {
  private String documentNumber;
  private String origin;
  private List<Stock> stocks;
}