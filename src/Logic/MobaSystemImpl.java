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
public class MobaSystemImpl implements MobaSystem{
    private AccountList accountList;
    private CharacterList characterList;
    private SkinList skinList;

    public MobaSystemImpl() {
        this.accountList = new AccountList(9999);
        this.characterList = new CharacterList(9999);
        this.skinList = new SkinList(9999);
    }
    //obtains the information about the skins from the "Personajes.txt" file
    public boolean getSkins(String skinName, String skinRarity, String characterName){
        if(skinList.addSkin(skinName, skinRarity, characterName)){
            return true;
        }
        else{
            return false;
        }
    }
    
    //obtains the information about the characters from the "Personajes.txt" file
    public boolean getCharacter(String characterName, String rol, int skinAmount, int moneyCollected){
        if(characterList.addCharacter(characterName, rol, skinAmount, moneyCollected)){
            return true;
        }
        else{
            return false;
        }
    
    }
   
    //obtains the information about the statistics from the "Estadísticas.txt" file
    public void getStatistics(String characterName,int earnings){
        int amountOfcharacter= characterList.getCharacterAmount();
        for(int i=0;i<amountOfcharacter;i++){
            Characters eChar= characterList.getCharacterList()[i];
            if(characterName.equalsIgnoreCase(eChar.getCharacterName())){
                eChar.setMoneyCollected(earnings);
            }
        }
    }
    
    //obtains the information about the accounts from the "Cuentas.txt" file
    public boolean getAccounts(String accountName, String password, String nickname, int accountLevel, int accountRp,
            int amountOfCharacters, int totalAmountOfSkins, String region, String[] skinNames,int [] skinsPerCharacter, String[] characterNames){
        int characterAmount= characterList.getCharacterAmount();
        int skinAmount= skinList.getSkinAmount();
        SkinList toAddSkinList=new SkinList(999);
        CharacterList toAddCharacterList= new CharacterList(155);
        for(int i=0;i<amountOfCharacters;i++){
            for(int e=0;e<characterAmount;e++){
                Characters eCharacter= characterList.getCharacterList()[e];
                if(characterNames[i].equalsIgnoreCase(eCharacter.getCharacterName())){
                    if(toAddCharacterList.getCharacterAmount()==0){
                        toAddCharacterList.addCharacter(characterNames[i], eCharacter.getRol(), skinsPerCharacter[i], eCharacter.getMoneyCollected());
                    }
                    else{
                        if(checkIfExistsChar(characterNames[i], toAddCharacterList)){
                            toAddCharacterList.addCharacter(characterNames[i], eCharacter.getRol(), skinsPerCharacter[i], eCharacter.getMoneyCollected());
                        }
                    }
                }
            }
            
        }

        for(int x=0;x<totalAmountOfSkins;x++){
            for(int y=0;y<skinAmount;y++){
                Skin eSkin= skinList.getSkinList()[y];
                if(skinNames[x].equalsIgnoreCase(eSkin.getSkinName())){
                    if(toAddSkinList.getSkinAmount()==0){
                        toAddSkinList.addSkin(skinNames[x], eSkin.getSkinRarity(), eSkin.getCharacterName());
                    }
                    else{
                        if(checkIfExistsSkin(skinNames[x], toAddSkinList)){
                            toAddSkinList.addSkin(skinNames[x], eSkin.getSkinRarity(), eSkin.getCharacterName());
                        }
                    }
                }
            }
        }

        
        
        

        
        if(accountList.addAccount(accountName, password, nickname, accountLevel, accountRp, amountOfCharacters, totalAmountOfSkins, region, toAddSkinList, toAddCharacterList)){
            
            return true;
            
        }
        else{
            return false;
        }
    }
    
    //Checks if a character is registered in the system or not
    public boolean checkIfExistsChar(String charName, CharacterList toAddCharacterList){
        boolean answer= true;
        for(int i=0;i<toAddCharacterList.getCharacterAmount();i++){
            if(charName.equalsIgnoreCase(toAddCharacterList.getCharacterList()[i].getCharacterName())){
                answer= false;
            }
            else{
                answer= true;
            }
        }
        return answer;
        
        
    }
    
    //Checks if a skin is registered in the system or not
    public boolean checkIfExistsSkin(String skinName, SkinList toAddSkinList){
        boolean answer= true;
        for(int i=0;i<toAddSkinList.getSkinAmount();i++){
            if(skinName.equalsIgnoreCase(toAddSkinList.getSkinList()[i].getCharacterName())){
                answer= false;
            }
            else{
                answer= true;
            }
        }
        return answer;
    }
    
    //Checks if the user is registered in the system or not
    public  int checkIfRegistered(String accountName){
        int answer= -1;
        int accountAmount= accountList.getAccountAmount();
        for(int i= 0;i<accountAmount;i++){
            if(accountName.equalsIgnoreCase(accountList.getAccountList()[i].getAccountName())){
                answer= i;
            }
            if(accountName.equalsIgnoreCase("Admin")){
                answer= -2;
            }
        }
        return answer;
    }
    
    //Registers an account and add it to the list of accounts
    public int registerAccount(String newAccountName,String newAccountPassword, String newAccountNickname, String region){
        int value;
        SkinList skinList= new SkinList(999);
        CharacterList characterList= new CharacterList(155);
        Account eAccount= new Account( newAccountName, newAccountPassword, newAccountNickname, 1, 0, 0, 0, region, skinList, characterList);
        
        if(accountList.addNewAccount(eAccount)){
            value=1;
        }
        else{
            value=-1;
        }
        return value;
    }
    
    //Checks for an element inside the Account data.
    public Account lookInAccountList(String accountName){
        int accountAmount= accountList.getAccountAmount();
        System.out.println("--------->>>>>>>> "+accountAmount);
        Account eAccount= null;
        for(int i= 0;i<accountAmount;i++){
            Account activeAccount= accountList.getAccountList()[i];
            System.out.println("----amountOfCharacters     "+activeAccount.getAmountOfCharacters());
            System.out.println("-------nickname    "+activeAccount.getNickname());
            System.out.println("-------region    "+activeAccount.getRegion());
            System.out.println("-------pass    "+activeAccount.getPassword());
            System.out.println("-------ALvl    "+activeAccount.getAccountLevel());
            System.out.println("-------RP    "+activeAccount.getRp());
            System.out.println("-------nickname    "+activeAccount.getCharacterList().getCharacterList()[0].getCharacterName());
            if(accountName.equalsIgnoreCase(activeAccount.getAccountName())){
                
                eAccount= activeAccount;
                break;
            }
            else{
                eAccount= null;
            }
        }
        return eAccount;
    }
    
    //allows the user to check if there's an element inside a list
    public int elementInListString(String[]list, int listLength,String element){
        int value=0;
        for(int i=0;i<listLength;i++){
            if(list[i].equalsIgnoreCase(element)){
                value++;
            }
        }
        return value;
    }
    
    //allows the user to use the list of skins in the app
    public SkinList buyingSkinList(){
        SkinList eSkinList= skinList;
        return eSkinList;
    }
    
    //sorts an array to just have unique items in it
    public String [] listSorter(String [] list1,int length1, String [] list2, int length2){
        for(int i=0;i<length1;i++){
            String [] newList= new String[99];
            int newListCounter= 0;
            for(int e=0;e<length2;e++){
                if(!list1[i].equalsIgnoreCase(list2[e])){
                    newList[newListCounter++]=list2[e];
                }
            }
            list2=newList;
        }
        
        return list2;
    }
    
    //allows the user to use the list of characters in the app
    public CharacterList buyingCharList(){
        CharacterList eCharList= characterList;
        return eCharList;
    }
    
    public int actualSize(String [] list){
        int value= 0;
        for(int i=0;i<list.length;i++){
            if(list[i]!=null){
                value++;
            }
        }
        
        return value;
    }
    //allows the user to use the list of accounts in the app
    public AccountList eAccountList(){
        AccountList eAccountList= accountList;
        return eAccountList;
    }
    
    
    
    
    
}
