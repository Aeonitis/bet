package com.punter.moneybags.model.request;

import com.punter.moneybags.model.Bet;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@ToString
public class BetCollectionRequest {
    private List<Bet> bets;
}
