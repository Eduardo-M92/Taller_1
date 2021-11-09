/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;
import Logic.*;

/**
 *
 * @author 
 */
public class Account{
    private String accountName;
    private String password;
    private String nickname;
    private int accountLevel;
    private int rp;
    private int amountOfCharacters;
    private int amountOfSkins;
    private String region;
    private SkinList skinList;
    private CharacterList characterList;

    public Account(String accountName, String password, String nickname,
            int accountLevel, int rp, int amountOfCharacters, int amountOfSkins, String region, SkinList skinList, CharacterList characterList) {
        this.accountName = accountName;
        this.password = password;
        this.nickname = nickname;
        this.accountLevel = accountLevel;
        this.rp = rp;
        this.amountOfCharacters = amountOfCharacters;
        this.amountOfSkins = amountOfSkins;
        this.region = region;
        this.skinList = skinList;
        this.characterList = characterList;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getAccountLevel() {
        return accountLevel;
    }

    public void setAccountLevel(int accountLevel) {
        this.accountLevel = accountLevel;
    }

    public int getRp() {
        return rp;
    }

    public void setRp(int rp) {
        this.rp = rp;
    }

    public int getAmountOfCharacters() {
        return amountOfCharacters;
    }

    public void setAmountOfCharacters(int amountOfCharacters) {
        this.amountOfCharacters = amountOfCharacters;
    }

    public int getAmountOfSkins() {
        return amountOfSkins;
    }

    public void setAmountOfSkins(int amountOfSkins) {
        this.amountOfSkins = amountOfSkins;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public SkinList getSkinList() {
        return skinList;
    }

    public void setSkinList(SkinList skinList) {
        this.skinList = skinList;
    }

    public CharacterList getCharacterList() {
        return characterList;
    }

    public void setCharacterList(CharacterList characterList) {
        this.characterList = characterList;
        
    }
    
    
    

    

    
    
}
