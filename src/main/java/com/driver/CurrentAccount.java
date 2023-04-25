package com.driver;

import java.util.*;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name,balance,5000);

        if(this.getBalance()<this.getMinBalance())
            throw new Exception("Insufficient Balance");

        this.tradeLicenseId = tradeLicenseId;
        validateLicenseId();
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception

        Map<Character,Integer> map = new HashMap<>();
        for(int i=0;i<tradeLicenseId.length();i++){

            char ch = tradeLicenseId.charAt(i);
            map.put(ch,map.getOrDefault(ch,0)+1);
        }

        if(Collections.max(map.values())>(tradeLicenseId.length()+1)/2)
            throw new Exception("Valid License can not be generated");

        while(!isValid()) {
            List list  = Arrays.asList(tradeLicenseId.toCharArray());
            Collections.shuffle(list);
            tradeLicenseId = list.toString();
        }
        this.tradeLicenseId = tradeLicenseId;
    }

    private boolean isValid() {
        for (int i = 0; i < tradeLicenseId.length() - 1; i++) {
            if (tradeLicenseId.charAt(i) == tradeLicenseId.charAt(i + 1)) break;
            if (i == tradeLicenseId.length() - 2) return true;
        }
        return false;
    }

}
