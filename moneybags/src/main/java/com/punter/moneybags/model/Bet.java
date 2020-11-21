package com.punter.moneybags.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Bet {

  @JsonProperty("betId")
  private String betId;

  @JsonProperty("betTimestamp")
  private long betTimestamp;

  @JsonProperty("selectionId")
  private int selectionId;

  @JsonProperty("selectionName")
  private String selectionName;

  @JsonProperty("stake")
  private double stake;

  @JsonProperty("price")
  private double price;

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