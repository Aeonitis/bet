package com.punter.moneybags.model.request;

import com.punter.moneybags.model.Bet;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class BetCollectionRequest {

  private List<Bet> bets;
}
