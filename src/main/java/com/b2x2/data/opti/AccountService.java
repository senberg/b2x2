package com.b2x2.data.opti;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    @Scheduled(initialDelay = 1000, fixedDelay = 60000)
    public void play() {
        /*accountRepository.save(AccountEntity.builder()
                .name("snake")
                .age(42)
                .build());
        System.out.println("Success");

        accountRepository.findAll().forEach(System.out::println);*
        /
         */
    }
}
