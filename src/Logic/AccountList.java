/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Domain.Account;

/**
 *
 * @author 
 */
public class AccountList {
    private int max;
    private int accountAmount;
    private Account [] accountList;
    
    public AccountList(int max) {
        this.max = max;
        this.accountAmount = 0;
        this.accountList = new Account[max];
    }
    
    
    public boolean addAccount(String accountName, String password, String nickname, int accountLevel, int accountRp,
            int amountOfCharacters, int totalAmountOfSkins, String region, SkinList skinList, CharacterList characterList){
        if(accountAmount < max){
           accountList[accountAmount++]= new Account(accountName, password, nickname, accountLevel, accountRp, amountOfCharacters, totalAmountOfSkins, region, skinList, characterList);
            return true;
        }
        else{
            return false;
        }
    }
    
        
    public boolean addNewAccount(Account newAccount){
        if(accountAmount < max){
            accountList[accountAmount++]= newAccount;
            return true;
        }
        else{
            return false;
        }
    }
    
    /*
    public boolean addAccount(String accountName, String password, String nickname, int accountLevel, int rp, int amountOfCharacters, int amountOfSkins, String region, SkinList skinList, CharacterList characterList){
        if(accountAmount < max){
            accountList[accountAmount++]= new Account(accountName, password, nickname, 1, 0, 0, 0, region, skinList, characterList);
            return true;
        }
        else{
            return false;
        }
    }
    */
    
    /*
    public boolean addAccount(String accountName, String password, String nickname, int accountLevel, int accountRp,
            int amountOfCharacters, int totalAmountOfSkins, String region, String[] skinNames,int [] skinsPerCharacter, String[] characterNames){
        if(accountAmount < max){
            
            accountList[accountAmount++]= new Account(accountName, password, nickname, 1, 0, 0, 0, region, skinList, characterList);
            return true;
        }
        else{
            return false;
        }
    }
    */   
    
    /*
    public Account lookInAccountList(String accountName){
        for(int i= 0;i<accountAmount;i++){
            if(accountList[i].getAccountName().equalsIgnoreCase(accountName)){
                return accountList[i];
            }
        }
        return null;
    }
    */
    
//    public Account checkIfRegistered(String accountName){
//        boolean answer= true;
//        Account eAccount= null;
//        for(int i=0;i<accountAmount;i++){
//            eAccount= accountList[i];
//            if(accountName.equalsIgnoreCase(eAccount.getAccountName())){
//                return eAccount;
//            }
//        }
//        return eAccount;
//    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getAccountAmount() {
        return accountAmount;
    }

    public void setAccountAmount(int accountAmount) {
        this.accountAmount = accountAmount;
    }

    public Account[] getAccountList() {
        return accountList;
    }

    public void setAccountList(Account[] accountList) {
        this.accountList = accountList;
    }

    
    
    
}
