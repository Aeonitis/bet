package com.punter.moneybags.model.request;

import com.punter.moneybags.model.Bet;
import lombok.Builder;
import lombok.ToString;

import java.util.List;

@Builder
@ToString
public class BetCollectionRequest {
    private List<Bet> bets;
}
