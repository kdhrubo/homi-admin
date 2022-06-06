package com.tryhomi.admin.core.handler;

import com.tryhomi.admin.core.event.UserRegisteredEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserRegisteredEventHandler {

    //private final AwsSesService awsSesService;

    @EventListener
    @Async
    public void handle(UserRegisteredEvent userRegisteredEvent) {
        log.info("Handling user registered event - {}", userRegisteredEvent);

        //Mail mail = new Mail();
        //mail.setFrom("arjun@getarjun.com");
        //mail.setTo(userRegisteredEvent.getUser().getEmail());
        //mail.setTemplateName("email-confirmation");
        //mail.setSubject("GetArjun : Please verify your signup.");
        //mail.setModel(userRegisteredEvent.toMap());

        //awsSesService.sendEmail(mail);
    }
}
