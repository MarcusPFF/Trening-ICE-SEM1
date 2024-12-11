package util;

import app.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountMapper {
    DBConnector dbconnector;

    public AccountMapper {
        dbconnector = new DBConnector();
    }
    private List<Account> account;

    public AccountMapper() {
        account = new ArrayList<>();
    }

    public List<Account> loadAccountData() {
        return null;
    }


    public List<Account> saveAccountData() {
        return null;
    }
}