/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;
import Domain.*;
/**
 *
 * @author 
 */
public interface MobaSystem {

    public boolean getSkins(String skinName, String skinRarity, String characterName);

    public boolean getCharacter(String characterName, String rol, int skinAmount, int moneyCollected);
    
    public void getStatistics(String characterName,int earnings);

    public boolean getAccounts(String accountName, String password, String nickname, int accountLevel, int accountRp, int amountOfCharacters,
            int totalAmountOfSkins, String region, String[] skinNames,int [] skinsPerCharacter, String[] characterNames);
    
    public int checkIfRegistered(String accountName);
     
    public int registerAccount(String newAccountName,String newAccountPassword, String newAccountNickname, String region);
    
    public Account lookInAccountList(String accountName);
    
    public int elementInListString(String[]list, int listLength,String element);
    
    public SkinList buyingSkinList();
    
    public String [] listSorter(String [] list1,int length1, String [] list2, int length2);

    public CharacterList buyingCharList();
    
    public int actualSize(String [] list);
    
    public AccountList eAccountList();
    
    
}
