package com.punter.moneybags.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class Bet {

  @NotBlank(message = "betId is mandatory")
  @JsonProperty("betId")
  private String betId;

  // TODO: Should get a better validator for unix timestamp
  @NotBlank(message = "Timestamp cannot be empty")
  @Size(min = 13, max = 13)
  @Digits(fraction = 0, integer = 13)
  @JsonProperty("betTimestamp")
  private long betTimestamp;

  @NotBlank(message = "selectionId is mandatory")
  @JsonProperty("selectionId")
  private int selectionId;

  @NotBlank(message = "Name is mandatory")
  @JsonProperty("selectionName")
  private String selectionName;

  @NotBlank(message = "Stake is mandatory")
  @JsonProperty("stake")
  private double stake;

  @NotBlank(message = "Price is mandatory")
  @JsonProperty("price")
  private double price;

  @NotBlank(message = "Currency is mandatory")
  @JsonProperty("currency")
  private String currency;

  @Override
  public String toString() {
    return "Bet{" +
        "betId='" + betId + '\'' +
        ", betTimestamp=" + betTimestamp +
        ", selectionId=" + selectionId +
        ", selectionName='" + selectionName + '\'' +
        ", stake=" + stake +
        ", price=" + price +
        ", currency='" + currency + '\'' +
        '}';
  }
}