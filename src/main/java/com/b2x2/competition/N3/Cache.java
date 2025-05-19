package com.b2x2.competition.N3;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class AccountService {
    private final ConcurrentHashMap<Long, Account> accounts = new ConcurrentHashMap<>();
    private final Object lock = new Object();

    @Autowired
    Database database;

    public Account getAccountById(Long id) {
        synchronized (lock) {
            if (accounts.containsKey(id)) {
                return accounts.get(id);
            } else {
                accounts.put(id, database.getAccount(id));
                return accounts.get(id);
            }
        }
    }
}

