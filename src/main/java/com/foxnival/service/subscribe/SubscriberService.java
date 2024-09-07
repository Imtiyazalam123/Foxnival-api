package com.foxnival.service.subscribe;

import com.foxnival.dto.SubscribeDetailDto;
import com.foxnival.entity.SubscribeDetail;


public interface SubscriberService {

   public SubscribeDetail createSubscription(SubscribeDetailDto subscribeDetailDto);
}
