package com.b2x2.competition.N3;


public class BankAccount {
    private final Object lock = new Object();
    private long balance = 0;

    public long getBalance() {
        synchronized (lock) {
            return balance;
        }
    }

    public void add(long amount) {
        synchronized (lock) {
            balance += amount;
        }
    }

    public void withdraw(long amount) {
        synchronized (lock) {
            balance -= amount;
        }
    }
}

