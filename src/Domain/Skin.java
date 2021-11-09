/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

/**
 *
 * @author 
 */
public class Skin {
    private String skinName;
    private String skinRarity;
    private String characterName;

    public Skin(String skinName, String skinRarity, String characterName) {
        this.skinName = skinName;
        this.skinRarity = skinRarity;
        this.characterName = characterName;
    }

    
    public String getSkinName() {
        return skinName;
    }

    public void setSkinName(String skinName) {
        this.skinName = skinName;
    }

    public String getSkinRarity() {
        return skinRarity;
    }

    public void setSkinRarity(String skinRarity) {
        this.skinRarity = skinRarity;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }
    
}
