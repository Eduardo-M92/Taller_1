/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;
import Domain.Characters;

/**
 *
 * @author 
 */
public class CharacterList {
    private int max;
    private int characterAmount;
    private Characters [] characterList;
    
    public CharacterList(int max) {
        this.max = max;
        this.characterAmount = 0;
        this.characterList = new Characters[max];
    }
    
    
    public boolean addCharacter(String characterName,String rol,int skinVariations,int moneyCollected){
        if(characterAmount<max){
            characterList[characterAmount++]= new Characters(characterName, rol, skinVariations, moneyCollected);
            return true;
        }
        else{
            return false;
        }
    }
    
    public void addStatistics(String characterName,int earnings){
        int amountOfCharacters= characterList.length;
        for(int i=0;i<amountOfCharacters;i++){
            Characters selectedCharacter= characterList[amountOfCharacters];
            if(characterName.equalsIgnoreCase(selectedCharacter.getCharacterName())){
                int moneyAlreadyCollected= selectedCharacter.getMoneyCollected();
                selectedCharacter.setMoneyCollected(moneyAlreadyCollected+earnings);
            }
        }
    }

    
    
    
    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getCharacterAmount() {
        return characterAmount;
    }

    public void setCharacterAmount(int characterAmount) {
        this.characterAmount = characterAmount;
    }

    public Characters[] getCharacterList() {
        return characterList;
    }

    public void setCharacterList(Characters[] characterList) {
        this.characterList = characterList;
    }
    
    
    
}
