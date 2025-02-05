package com.foxnival.service.subscribe;

import com.foxnival.dto.SubscribeDetailDto;
import com.foxnival.entity.PaymentDetails;
import com.foxnival.entity.SubscribeDetail;
import com.foxnival.entity.Subscriber;
import com.foxnival.entity.User;
import com.foxnival.exception.DataInsertionFailedException;
import com.foxnival.exception.InvalidRequestException;
import com.foxnival.mapper.PaymentDetailsMapper;
import com.foxnival.repository.PaymentDetailsRepository;
import com.foxnival.repository.SubscribeRepository;
import com.foxnival.repository.UserRepository;
import com.foxnival.service.message.EmailService;
import com.foxnival.util.CalculateValidity;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Slf4j
@Transactional
@Service
public class SubscriberServiceImpl implements SubscriberService {

    @Autowired
    private PaymentDetailsMapper mapper;

    @Autowired
    private CalculateValidity calculateValidity;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubscribeRepository subscribeRepository;

    @Autowired
    private PaymentDetailsRepository paymentDetailsRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public SubscribeDetail createSubscription(SubscribeDetailDto subscribeDetailDto) {

        Subscriber subscriber = new Subscriber();
        subscriber.setActive(true);
        subscriber.setOrganizationName(subscribeDetailDto.getOrganizationName());
        subscriber.setSubscribedDate(Instant.now());
        Instant validityDate = calculateValidity.getValidityDate(subscribeDetailDto.getPlanForYear());
        subscriber.setValidityDate(validityDate);
        subscriber.setPlanForYear(subscribeDetailDto.getPlanForYear());

        Subscriber savedSubscriber = subscribeRepository.save(subscriber);
        if (savedSubscriber == null) {
            throw new DataInsertionFailedException("Failed to create subscription");
        }

        User user = new User();
        user.setSubscriber(savedSubscriber);
        user.setName(subscribeDetailDto.getName());
        user.setUsername(subscribeDetailDto.getUsername());
        user.setMobile(subscribeDetailDto.getMobile());
        user.setPassword(subscribeDetailDto.getPassword());
        user.setRole(subscribeDetailDto.getRole().getRoleName());
        user.setActive(true);

        User savedUser = userRepository.save(user);
        if (savedUser == null) {
            throw new DataInsertionFailedException("Failed to create default user");
        }

        PaymentDetails paymentDetails = mapper.subscribeDetailDtoToPaymentDetails(subscribeDetailDto);
        paymentDetails.setUser(savedUser);
        PaymentDetails savedPaymentDetails = paymentDetailsRepository.save(paymentDetails);
        if (savedPaymentDetails == null) {
            throw new DataInsertionFailedException("Failed to save paymentDetails");
        }


        SubscribeDetail subscribeDetail = new SubscribeDetail();
        subscribeDetail.setSubscriber(savedSubscriber);
        subscribeDetail.setUser(savedUser);
        subscribeDetail.setPaymentDetails(savedPaymentDetails);

        boolean isMailSend = emailService.sendEmail(savedUser.getUsername(), savedUser.getName(), savedUser.getPassword());

        subscribeDetail.setMailSend(isMailSend);

        return subscribeDetail;
    }

    @Override
    public void checkEmail(String email) {

        Optional<User> byUsernameAndRole = userRepository.findByUsername(email);
        if (byUsernameAndRole.isPresent()) {
            Optional<Subscriber> optionalSubscriber = subscribeRepository.findById(byUsernameAndRole.get().getSubscriber().getId());
            if (optionalSubscriber.isPresent()) {
                log.error("Email is already associated with another subscriber : Email : " + email + "already associated with subscriber id : " + byUsernameAndRole.get().getSubscriber().getId());
                throw new InvalidRequestException("Email is already associated with another subscriber.");
            }
        }
    }
}
