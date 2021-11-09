/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import Logic.SkinList;

/**
 *
 * @author 
 */
public class Characters {
    private String characterName;
    private String rol;
    private int skinVariations;
    private int moneyCollected;
    private Skin [] skinList;
    private int skinsAdded;
    
    /*public Character(String characterName, String rol, int skinVariations, int moneyCollected, Skin [] skinList) {
        this.characterName = characterName;
        this.rol = rol;
        this.skinVariations = skinVariations;
        this.moneyCollected = moneyCollected;
        this.skinList = new Skin[9999];
        this.skinsAdded=0;
    }
    */
    
    public Characters(String characterName, String rol, int skinVariations, int moneyCollected) {
        this.characterName = characterName;
        this.rol = rol;
        this.skinVariations = skinVariations;
        this.moneyCollected = moneyCollected;
        this.skinList = new Skin[9999];
        this.skinsAdded= 0;
    }
    
    
    public boolean addSkinToCharacter(Skin skin){
        if(skinsAdded<9999){
            skinList[skinsAdded++]= skin;
            return true;
        }
        else{
            return false;
        }
    }
    
    


    
    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getSkinVariations() {
        return skinVariations;
    }

    public void setSkinVariations(int skinVariations) {
        this.skinVariations = skinVariations;
    }

    public int getMoneyCollected() {
        return moneyCollected;
    }

    public void setMoneyCollected(int moneyCollected) {
        this.moneyCollected = moneyCollected;
    }

    public Skin[] getSkinList() {
        return skinList;
    }

    public void setSkinList(Skin[] skinList) {
        this.skinList = skinList;
    }

    
    
}
