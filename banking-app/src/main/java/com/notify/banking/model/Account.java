package com.notify.banking.model;

import com.notify.agent.annotations.Model;
import com.notify.agent.annotations.Vocabulary;

/**
 * In-memory account representation.
 */
@Model(description = "Bank account profile used to resolve notification recipients and account context")
public class Account {

    @Vocabulary(name = "accountId", description = "Unique bank account identifier")
    private String id;

    @Vocabulary(name = "holderName", description = "Full name of the account holder")
    private String holderName;

    @Vocabulary(name = "email", description = "Account holder email address for banking notifications")
    private String email;

    @Vocabulary(name = "phone", description = "Account holder phone number for SMS banking notifications")
    private String phone;

    @Vocabulary(name = "balance", description = "Current available account balance")
    private double balance;

    public Account() {}

    public Account(String id, String holderName, String email, String phone, double balance) {
        this.id = id;
        this.holderName = holderName;
        this.email = email;
        this.phone = phone;
        this.balance = balance;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getHolderName() { return holderName; }
    public void setHolderName(String holderName) { this.holderName = holderName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
}
