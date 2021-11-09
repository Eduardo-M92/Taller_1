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
public class SkinList {
    private int max;
    private int skinAmount;
    private Skin [] skinList;
    
    public SkinList(int max) {
        this.max = max;
        this.skinAmount = 0;
        this.skinList = new Skin[max];
    }
    
     public boolean addSkin(String skinName, String skinRarity,String characterName){
        if(skinAmount < max){
            skinList[skinAmount++]= new Skin(skinName, skinRarity, characterName);
            return true;
        }
        else{
            return false;
        }
    }
    

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getSkinAmount() {
        return skinAmount;
    }

    public void setSkinAmount(int skinAmount) {
        this.skinAmount = skinAmount;
    }

    public Skin[] getSkinList() {
        return skinList;
    }

    public void setSkinList(Skin[] skinList) {
        this.skinList = skinList;
    }
    
}
