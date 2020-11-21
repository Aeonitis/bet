package com.punter.moneybags.model.request;

import com.punter.moneybags.model.Bet;
import lombok.Builder;

import java.util.List;

@Builder
public class BetCollectionRequest {
    private List<Bet> bets;
}
