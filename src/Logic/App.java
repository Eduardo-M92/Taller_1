/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import Domain.*;

/**
 *
 * @author 
 */
public class App {

    //main part of the program
    public static void main(String[] args) throws FileNotFoundException {
        
        MobaSystem sys = new MobaSystemImpl();
        readCharacters(sys); 
        readAccounts(sys);
        readStatistics(sys);
        menu(sys);
    }
     
    public static void readCharacters(MobaSystem sys) throws FileNotFoundException{
        Scanner scan = new Scanner(new File("Personajes.txt"));
        while(scan.hasNextLine()){
            String [] data= scan.nextLine().split(",");
            String characterName = data[0];
            String rol= data[1];
            int skinAmount= Integer.parseInt(data[2]);
            sys.getCharacter(characterName,rol,skinAmount,0);
            int n= 3;
            for(int i=0;i<skinAmount;i++){
                String skinName= data[n];
                n++;
                String skinRarity= data[n];
                n++;
                sys.getSkins(skinName,skinRarity,characterName);
            }
            
        }
    }
                
    public static void readAccounts(MobaSystem sys) throws FileNotFoundException{
        Scanner scan = new Scanner(new File("Cuentas.txt"));
        String [] characterNames= new String[155];
        int [] skinsPerCharacter= new int[999];
        String [] skinNames= new String[9999];
        
        while(scan.hasNextLine()){
            String [] data= scan.nextLine().split(",");
            String accountName = data[0];
            String password= data[1];
            String nickname= data[2];
            int accountLevel= Integer.parseInt(data[3]);
            int accountRp= Integer.parseInt(data[4]);
            int amountOfCharacters= Integer.parseInt(data[5]);
            int totalAmountOfSkins=0;
            int n= 6;
            int skinPosition= 0;
            if(amountOfCharacters==0){
                skinsPerCharacter[0]=0;
                totalAmountOfSkins=0;
                skinNames[0]="";
                characterNames[0]="";
            }
            else{
                for(int i=0;i<amountOfCharacters;i++){
                    characterNames[i]=data[n++];
                    int amountOfSkins= Integer.parseInt(data[n++]);
                    skinsPerCharacter[i]=amountOfSkins;
                    totalAmountOfSkins+=amountOfSkins;
                    for(int e=0;e<amountOfSkins;e++){
                        String naaaaaame= data[n++];
                        skinNames[skinPosition++]=naaaaaame;
                    }
                }
            }
            
            String region= data[n];
            
            sys.getAccounts(accountName,password,nickname,accountLevel,accountRp,amountOfCharacters,totalAmountOfSkins,region,skinNames,skinsPerCharacter,characterNames);
        }
    }
   
    public static void readStatistics(MobaSystem sys) throws FileNotFoundException{
        Scanner scan = new Scanner(new File("Estadísticas.txt"));
        while(scan.hasNextLine()){
            String [] data= scan.nextLine().split(",");
            String characterName = data[0];
            int earnings= Integer.parseInt(data[1]);
            
            sys.getStatistics(characterName,earnings);
        }
    
    }
    
    //displays the menu and the options it offers for users and the admin
    public static void menu(MobaSystem sys) throws NullPointerException{
        Scanner scan= new Scanner(System.in);
        Scanner sc= new Scanner(System.in);
        System.out.println("--- RITO GAMES --");
        System.out.println("Cargando...");
        System.out.println("Inicio de sesión");
        int soldInLAS= 0;
        int soldInLAN= 0;
        int soldInEUW= 0;
        int soldInKR= 0;
        int soldInNA= 0;
        int soldInRU= 0;
        
        int soldPerRolSUP=0;
        int soldPerRolADC=0;
        int soldPerRolTOP=0;
        int soldPerRolMID=0;
        int soldPerRolJG=0;
        
        int amountOfBannedAccounts= 0;
        String [] bannedAccounts= new String[999];
        
        boolean exit= false;
        while(!exit){
            boolean logOut= false;
            while(!logOut){
                System.out.print("Ingrese el nombre de su cuenta: ");
                String accountName= scan.nextLine();
                int bannedCheck= sys.elementInListString(bannedAccounts, amountOfBannedAccounts, accountName);
                int validAccount= sys.checkIfRegistered(accountName);
                
                
                while(validAccount==-1){
                    System.out.print("Jugador no reconocido.\n¿Desearía registrarse?\n-) Sí\n-) No\nIngrese su respuesta: ");
                    String clientAnswer= scan.nextLine();
                    while(!clientAnswer.equalsIgnoreCase("Si") && !clientAnswer.equalsIgnoreCase("Sí") & !clientAnswer.equalsIgnoreCase("NO")){
                        System.out.print("Respuesta inválida.Elija una de las opciones.\n-) Sí\n-) No\nIngrese su respuesta: ");
                        clientAnswer= scan.nextLine();
                    }
                    if(clientAnswer.equalsIgnoreCase("Si") || clientAnswer.equalsIgnoreCase("Sí")){
                        System.out.print("Ingrese el nombre de su cuenta: ");
                        String newAccountName= scan.nextLine();
                        while(sys.checkIfRegistered(newAccountName)!=-1 && sys.checkIfRegistered(newAccountName)!=-2){
                            System.out.print("Nombre ya registrado.\nIngrese el nombre de su cuenta: ");
                            newAccountName= scan.nextLine();
                        }                        
                        System.out.print("Su contraseña debe de tener al menos 4 caracteres.\nIngrese su contraseña: ");
                        String newAccountPassword= scan.nextLine().replace(" ", "");
                        while(newAccountPassword.length()<4){
                            System.out.print("Error, su contraseña debe de tener al menos 4 caracteres.\nIngrese su contraseña: ");
                            newAccountPassword= scan.nextLine().replace(" ", "");
                        }
                        System.out.print("Ingrese su Nick: ");
                        String newAccountNickname= scan.nextLine();
                        System.out.print("Ingrese su región: ");
                        String newAccountRegion= scan.nextLine();
                        while(!newAccountRegion.equalsIgnoreCase("LAS") && !newAccountRegion.equalsIgnoreCase("LAN") && 
                                !newAccountRegion.equalsIgnoreCase("EUW") && !newAccountRegion.equalsIgnoreCase("KR") &&
                                !newAccountRegion.equalsIgnoreCase("NA") && !newAccountRegion.equalsIgnoreCase("RU")){
                            System.out.print("Región inválida.\nIngrese su región: ");
                            newAccountRegion= scan.nextLine();
                        }
                        if(sys.registerAccount(newAccountName, newAccountPassword, newAccountNickname, newAccountRegion)==1){
                            System.out.println("Cuenta registrada exitosamente.");

                        }
                    
                    
                    
                    
                    }
                    if(clientAnswer.equalsIgnoreCase("no")){
                        logOut= true;
                        break;
                        
                    }
                    else{
                        System.out.println("Ingrese el nombre de su cuenta: ");
                        accountName= scan.nextLine();
                        validAccount= sys.checkIfRegistered(accountName);
                    }
                
                }
                
                
                
                
                

                if(validAccount!=-1 && validAccount!= -2 && validAccount !=3){
                    Account activeAccount= (sys.lookInAccountList(accountName));
                    System.out.print("Ingrese su contraseña: ");
                    String enteredPassword= scan.nextLine();
                    String validPassword= activeAccount.getPassword();
                    while(!enteredPassword.equals(validPassword)){
                        System.out.print("La contraseña ingresada es incorrecta.\nPor favor ingrese su contraseña: ");
                        enteredPassword= scan.nextLine();
                    }
                    
                    System.out.println("Acceso exitoso.\nBienvenido al sistema "+activeAccount.getAccountName()+".");
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\nMenú principal");
                    System.out.println("Seleccione una de las siguientes opciones");
                    System.out.println("1) Comprar Skin \n2) Comprar Personaje\n3) Skins disponibles\n4) Mostrar inventario\n5) Recargar RP\n6) Mostrar Datos de cuenta\n7) Cerrar sesión\n8) Salir");
                    System.out.print("Ingrese una de las opciones (1-8): ");   
                    String userChoice= scan.nextLine();
                    while(!userChoice.equals("1") && !userChoice.equals("2") && !userChoice.equals("3") && !userChoice.equals("4") &&
                            !userChoice.equals("5") && !userChoice.equals("6")&& !userChoice.equals("7")&& !userChoice.equals("8")){
                        System.out.println("Selección inválida.\nSeleccione una de las siguientes opciones");
                        System.out.println("1) Comprar Skin \n2) Comprar Personaje\n3) Skins disponibles\n4) Mostrar inventario\n5) Recargar RP\n6) Mostrar Datos de cuenta\n7) Cerrar sesión\n8) Salir");
                        System.out.print("Ingrese una de las opciones (1-8): ");   
                        userChoice= scan.nextLine();
                    }
                    
                    
                    
                    if(userChoice.equals("7")){
                        System.out.println("Hasta luego, see you soon!.");
                        logOut= true;
                        break;
                        
                    }
                    if(userChoice.equals("8")){
                        System.out.println("Hasta pronto, gracias por usar nuestros servicios.");
                        exit= true;
                        logOut= true;
                        break;
                    }
                    while(!userChoice.equals("7") && !userChoice.equals("8")){
                        if(userChoice.equals("1")){
                            String [] characterNamesInAccount= new String[155];
                            String [] skinsInAccount= new String[999];
                            int skinsInAccountCounter=0;
                            int amountOfCharacters= activeAccount.getAmountOfCharacters();
                            if(amountOfCharacters!=0){
                                for(int i=0;i<amountOfCharacters;i++){
                                    characterNamesInAccount[i]= activeAccount.getCharacterList().getCharacterList()[i].getCharacterName();

                                    
                                }
                            }
                            if(amountOfCharacters!=0){
                                for(int i=0;i<amountOfCharacters;i++){
                                    System.out.println(")"+(i+1)+" "+characterNamesInAccount[i]);
                                }
                                System.out.print("Ingrese el personaje a comprar una skin: ");
                                String chosenCharacter= scan.nextLine();
                                while(sys.elementInListString(characterNamesInAccount, amountOfCharacters, chosenCharacter)==0){
                                    System.out.print("Ingrese un personaje válido: ");
                                    chosenCharacter= scan.nextLine();
                                }


                                int amountOfSkinsInAccount= activeAccount.getSkinList().getSkinAmount();

                                for(int i=0;i<amountOfSkinsInAccount;i++){
                                    if(activeAccount.getSkinList().getSkinList()[i].getCharacterName().equalsIgnoreCase(chosenCharacter)){
                                        skinsInAccount[i]=activeAccount.getSkinList().getSkinList()[i].getSkinName();
                                        skinsInAccountCounter++;
                                    }
                                }
    //                            for(int i=0;i<skinsInAccountCounter;i++){
    //                                System.out.println(skinsInAccount[i]);
    //                            }


                                SkinList eSkinList= sys.buyingSkinList();                        
                                String [] skinsInSystem= new String[999];
                                int skinsInSystemCounter=0;

                                int totalSkinsInSystem= eSkinList.getSkinAmount();
                                for(int i=0;i<totalSkinsInSystem;i++){

                                    if(chosenCharacter.equalsIgnoreCase(eSkinList.getSkinList()[i].getCharacterName())){
                                        skinsInSystem[skinsInSystemCounter++]=eSkinList.getSkinList()[i].getSkinName();
                                    }
                                }
                                String skinToBuy="";
                                int check=0;
                                try{
                                    String [] buyableSkins= sys.listSorter(skinsInAccount, skinsInAccountCounter, skinsInSystem, skinsInSystemCounter);
                                    if(!buyableSkins[0].equalsIgnoreCase("safe")){
                                        System.out.println("Skins disponibles: ");
                                        int buyableCounter=0;
                                        for(int i=0;i<buyableSkins.length;i++){
                                            if(buyableSkins[i]!=null){
                                                System.out.println(buyableSkins[i]);
                                                buyableCounter++;
                                            }
                                        }
                                        System.out.print("Ingrese el nombre de la Skin a comprar: ");
                                        skinToBuy= scan.nextLine();
                                        while(sys.elementInListString(buyableSkins, buyableCounter, skinToBuy)==0){
                                            System.out.print("Ingrese una skin válida: ");
                                            skinToBuy= scan.nextLine();
                                        }

                                    }

                                }
                                catch(NullPointerException e){
                                    System.out.println("Usted ya posee todas las skins para este personaje.");
                                    check=-1;
                                }
                                if(check!=-1){
                                    int skinValue=0;
                                    String rarity="";
                                    for(int i=0;i<totalSkinsInSystem;i++){
                                        if(skinToBuy.equalsIgnoreCase(eSkinList.getSkinList()[i].getSkinName())&& chosenCharacter.equalsIgnoreCase(eSkinList.getSkinList()[i].getCharacterName())){
                                            rarity= eSkinList.getSkinList()[i].getSkinRarity();
                                            if(rarity.equalsIgnoreCase("M")){
                                                skinValue=3250;
                                            }
                                            if(rarity.equalsIgnoreCase("D")){
                                                skinValue=2750;
                                            }
                                            if(rarity.equalsIgnoreCase("L")){
                                                skinValue=1820;
                                            }
                                            if(rarity.equalsIgnoreCase("E")){
                                                skinValue=1350;
                                            }
                                            if(rarity.equalsIgnoreCase("N")){
                                                skinValue=975;
                                            }
                                            System.out.println("La skin "+skinToBuy+" cuesta: "+skinValue+" RP.");
                                        }
                                    }
                                    int moneyInWallet= activeAccount.getRp();
                                    System.out.print("¿Desea comprar la skin?\n-) Sí\n-) No\nIngrese su respuesta: ");
                                    String clientAnswer2= scan.nextLine();
                                    while(!clientAnswer2.equalsIgnoreCase("Si") && !clientAnswer2.equalsIgnoreCase("Sí") & !clientAnswer2.equalsIgnoreCase("NO")){
                                        System.out.print("Respuesta inválida.Elija una de las opciones.\n-) Sí\n-) No\nIngrese su respuesta: ");
                                        clientAnswer2= scan.nextLine();
                                    }
                                    int wantingToBuy=1;
                                    if(clientAnswer2.equalsIgnoreCase("no")){
                                        System.out.println("Gracias vuelva pronto.");
                                    }

                                    else{
                                        while(wantingToBuy==1){
                                            if(moneyInWallet>=skinValue){
                                                activeAccount.setRp(activeAccount.getRp()-skinValue);
                                                int skinPosition= activeAccount.getAmountOfSkins();
                                                activeAccount.getSkinList().addSkin(skinToBuy, rarity, chosenCharacter);
                                                System.out.println("Ha comprado la Skin exitosamente.");
                                                System.out.println("new balance after"+activeAccount.getRp());
                                                wantingToBuy=-1;
                                                String accountRegion= activeAccount.getRegion();
                                                if(accountRegion.equalsIgnoreCase("LAS")){
                                                    soldInLAS+=975;
                                                }
                                                if(accountRegion.equalsIgnoreCase("LAN")){
                                                    soldInLAN+=975;
                                                }
                                                if(accountRegion.equalsIgnoreCase("EUW")){
                                                    soldInEUW+=975;
                                                }
                                                if(accountRegion.equalsIgnoreCase("KR")){
                                                    soldInKR+=975;
                                                }
                                                if(accountRegion.equalsIgnoreCase("NA")){
                                                    soldInNA+=975;
                                                }
                                                if(accountRegion.equalsIgnoreCase("RU")){
                                                    soldInRU+=975;
                                                }
                                            }
                                            else{
                                                if(wantingToBuy==1 && moneyInWallet<skinValue){
                                                    System.out.println("No tiene RP suficiente para hacer la transacción.");
                                                    System.out.print("¿Desear recargar con RP?\n-) Sí\n-) No\nIngrese su respuesta: ");
                                                    String clientAnswer3= scan.nextLine();
                                                    while(!clientAnswer3.equalsIgnoreCase("Si") && !clientAnswer3.equalsIgnoreCase("Sí") & !clientAnswer3.equalsIgnoreCase("NO")){
                                                        System.out.print("Respuesta inválida.Elija una de las opciones.\n-) Sí\n-) No\nIngrese su respuesta: ");
                                                        clientAnswer3= scan.nextLine();
                                                    }
                                                    if(clientAnswer3.equalsIgnoreCase("No")){
                                                        wantingToBuy=-1;
                                                        System.out.println("Gracias vuelva pronto.");
                                                    }
                                                    else{
                                                        System.out.print("Ingrese la cantidad de RP a recargar: ");
                                                        while(!scan.hasNextInt()) {
                                                            System.out.print("Ingrese un valor válido: ");
                                                            scan.next();
                                                        }
                                                        int moneyAdded = scan.nextInt();
                                                        activeAccount.setRp(activeAccount.getRp()+moneyAdded);
                                                        moneyInWallet=activeAccount.getRp();
                                                        System.out.println("new balance "+activeAccount.getRp());
                                                    }
                                                }

                                            }
                                        }
        //                                
                                    }

                                }
                            }
                            else{
                                System.out.println("Usted no posee personajes.");
                            }
                            
                            
                            

                    
                    
                        }
                        if(userChoice.equals("2")){
                            String [] characterInAccount= new String[155];
                            int amountOfCharacters= activeAccount.getAmountOfCharacters();
                            if(amountOfCharacters!=0){
                                for(int i=0;i<amountOfCharacters;i++){
                                    characterInAccount[i]= activeAccount.getCharacterList().getCharacterList()[i].getCharacterName();
                                }
                            }
                            
//                            for(int i=0;i<amountOfCharacters;i++){
//                                System.out.println(characterInAccount[i]);
//                            }
                            
                            
                            
                            CharacterList eCharList= sys.buyingCharList();
                            String [] charsInSystem= new String[999];
                            int charsInSystemCounter= 0;
                            int totalCharsInSystem= eCharList.getCharacterAmount();
                            
                            for(int i=0;i<totalCharsInSystem;i++){
                                charsInSystem[i]=eCharList.getCharacterList()[i].getCharacterName();
                                System.out.println(charsInSystem[i]);
                            }
                            
                            String charToBuy="";
                            int check=0;
                            
                            try{
                                String [] buyableChars= sys.listSorter(characterInAccount, amountOfCharacters, charsInSystem, totalCharsInSystem);
                                System.out.println("Personajes disponibles: ");
                                int buyableCounter=0;
                                for(int i=0;i<buyableChars.length;i++){
                                    if(buyableChars[i]!=null){
                                        System.out.println(buyableChars[i]);
                                        buyableCounter++;
                                    }
                                }
                                System.out.print("Ingrese el nombre del personaje comprar: ");
                                charToBuy= scan.nextLine();
                                while(sys.elementInListString(buyableChars, buyableCounter, charToBuy)==0){
                                    System.out.print("Ingrese un personaje válido: ");
                                    charToBuy= scan.nextLine();
                                }

                                

                            }
                            catch(NullPointerException e){
                                System.out.println("Usted ya posee todos los personajes.");
                                check=-1;
                            }
                            
                            if(check!=-1){
                                int moneyInWallet= activeAccount.getRp();
                                System.out.print("¿Desea comprar el personaje?\n-) Sí\n-) No\nIngrese su respuesta: ");
                                    String clientAnswer2= scan.nextLine();
                                    while(!clientAnswer2.equalsIgnoreCase("Si") && !clientAnswer2.equalsIgnoreCase("Sí") & !clientAnswer2.equalsIgnoreCase("NO")){
                                        System.out.print("Respuesta inválida.Elija una de las opciones.\n-) Sí\n-) No\nIngrese su respuesta: ");
                                        clientAnswer2= scan.nextLine();
                                    }
                                int wantingToBuy=1;
                                if(clientAnswer2.equalsIgnoreCase("no")){
                                    System.out.println("Gracias vuelva pronto.");
                                }
                                else{
                                    while(wantingToBuy==1){
                                        if(moneyInWallet>=975){
                                            activeAccount.setRp(activeAccount.getRp()-975);
                                            int charPosition= activeAccount.getAmountOfCharacters();
                                            String rol="";
                                            int skinV= 0;
                                            int moneyC= 0;

                                            for(int i=0;i<totalCharsInSystem;i++){
                                                if(eCharList.getCharacterList()[i].getCharacterName().equalsIgnoreCase(charToBuy)){
                                                    rol= eCharList.getCharacterList()[i].getRol();
                                                    skinV= eCharList.getCharacterList()[i].getSkinVariations();
                                                    moneyC= eCharList.getCharacterList()[i].getMoneyCollected();
                                                    //Adding the amount of RP sold to the total sold by that characted
                                                    eCharList.getCharacterList()[i].setMoneyCollected(975+eCharList.getCharacterList()[i].getMoneyCollected());
                                                }
                                            }
                                            //Adding the amount of RP sold to the correct ROL
                                            if(rol.equalsIgnoreCase("SUP")){
                                                soldPerRolSUP+= 975;
                                            }
                                            if(rol.equalsIgnoreCase("ADC")){
                                                soldPerRolADC+= 975;
                                            }
                                            if(rol.equalsIgnoreCase("TOP")){
                                                soldPerRolTOP+= 975;
                                            }
                                            if(rol.equalsIgnoreCase("MID")){
                                                soldPerRolMID+= 975;
                                            }
                                            if(rol.equalsIgnoreCase("JG")){
                                                soldPerRolJG+= 975;
                                            }
                                            //Adding the amount of RP sold to the correct Region
                                            String accountRegion= activeAccount.getRegion();
                                            if(accountRegion.equalsIgnoreCase("LAS")){
                                                soldInLAS+=975;
                                            }
                                            if(accountRegion.equalsIgnoreCase("LAN")){
                                                soldInLAN+=975;
                                            }
                                            if(accountRegion.equalsIgnoreCase("EUW")){
                                                soldInEUW+=975;
                                            }
                                            if(accountRegion.equalsIgnoreCase("KR")){
                                                soldInKR+=975;
                                            }
                                            if(accountRegion.equalsIgnoreCase("NA")){
                                                soldInNA+=975;
                                            }
                                            if(accountRegion.equalsIgnoreCase("RU")){
                                                soldInRU+=975;
                                            }
                                            //Adding a level to the account for buying a character
                                            activeAccount.setAccountLevel(activeAccount.getAccountLevel()+1);
                                            
                                            activeAccount.getCharacterList().addCharacter(charToBuy, rol, skinV, moneyC);
                                            System.out.println("Ha comprado el personaje exitosamente.");
                                            System.out.println("new balance after"+activeAccount.getRp());
                                            wantingToBuy=-1;
                                        }
                                        else{
                                            if(wantingToBuy==1 && moneyInWallet<975){
                                                System.out.println("No tiene RP suficiente para hacer la transacción.");
                                                System.out.print("¿Desear recargar con RP?\n-) Sí\n-) No\nIngrese su respuesta: ");
                                                String clientAnswer3= scan.nextLine();
                                                while(!clientAnswer3.equalsIgnoreCase("Si") && !clientAnswer3.equalsIgnoreCase("Sí") & !clientAnswer3.equalsIgnoreCase("NO")){
                                                    System.out.print("Respuesta inválida.Elija una de las opciones.\n-) Sí\n-) No\nIngrese su respuesta: ");
                                                    clientAnswer3= scan.nextLine();
                                                }
                                                if(clientAnswer3.equalsIgnoreCase("No")){
                                                    wantingToBuy=-1;
                                                    System.out.println("Gracias vuelva pronto.");
                                                }
                                                else{
                                                    System.out.print("Ingrese la cantidad de RP a recargar: ");
                                                    while(!scan.hasNextInt()) {
                                                        System.out.print("Ingrese un valor válido: ");
                                                        scan.next();
                                                    }
                                                    int moneyAdded = scan.nextInt();
                                                    activeAccount.setRp(activeAccount.getRp()+moneyAdded);
                                                    moneyInWallet=activeAccount.getRp();
                                                    System.out.println("new balance "+activeAccount.getRp());
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            
                        }
                        
                        
                        if(userChoice.equals("3")){
                            String [] skinsInAccount= new String[999]; 
                            int skinsInAccountCounter=0;
                            int amountOfSkinsInAccount= activeAccount.getSkinList().getSkinAmount();
                            for(int i=0;i<amountOfSkinsInAccount;i++){
                                skinsInAccount[skinsInAccountCounter++]=activeAccount.getSkinList().getSkinList()[i].getSkinName();
                            }
                            
                            
                            SkinList eSkinList= sys.buyingSkinList();                        
                            String [] skinsInSystem= new String[999];
                            int skinsInSystemCounter=0;

                            int totalSkinsInSystem= eSkinList.getSkinAmount();
                            for(int i=0;i<totalSkinsInSystem;i++){
                                skinsInSystem[skinsInSystemCounter++]=eSkinList.getSkinList()[i].getSkinName();
                                }
                            
                            String [] showableSkins= sys.listSorter(skinsInAccount, skinsInAccountCounter, skinsInSystem, skinsInSystemCounter);
                            int listSize= sys.actualSize(showableSkins);
                            System.out.println("Skins disponibles.");
                            for(int i=0;i<listSize;i++){
                                System.out.println((i+1)+") "+showableSkins[i]);
                            }
                            
                            
                        
                        }
                        
                        if(userChoice.equals("4")){
                            CharacterList charList= activeAccount.getCharacterList();
                            SkinList skinList= activeAccount.getSkinList();
                            int skinCounter= 0;
                            for(int i=0;i<charList.getCharacterAmount();i++){
                                System.out.println("Nombre personaje: "+charList.getCharacterList()[i].getCharacterName()+"\nSkins:");
                                for(int e=0;e<charList.getCharacterList()[i].getSkinVariations();e++){
                                    System.out.println(skinList.getSkinList()[skinCounter++].getSkinName());
                                }
                                
                            }
                        }
                        
                        if(userChoice.equals("5")){
                            int wantingToBuy=-1;
                            int moneyInWallet=activeAccount.getRp();
                            while(wantingToBuy==-1){
                                System.out.print("¿Desear recargar RP?\n-) Sí\n-) No\nIngrese su respuesta: ");
                                String clientAnswer3= scan.nextLine();
                                while(!clientAnswer3.equalsIgnoreCase("Si") && !clientAnswer3.equalsIgnoreCase("Sí") & !clientAnswer3.equalsIgnoreCase("NO")){
                                    System.out.print("Respuesta inválida.Elija una de las opciones.\n-) Sí\n-) No\nIngrese su respuesta: ");
                                    clientAnswer3= scan.nextLine();
                                }
                                if(clientAnswer3.equalsIgnoreCase("No")){
                                    wantingToBuy=1;
                                    System.out.println("Gracias vuelva pronto.");
                                }
                                else{
                                    System.out.print("Ingrese la cantidad de RP a recargar: ");
                                    while(!scan.hasNextInt()) {
                                        System.out.print("Ingrese un valor válido: ");
                                        scan.next();
                                    }
                                    int moneyAdded = scan.nextInt();
                                    activeAccount.setRp(activeAccount.getRp()+moneyAdded);
                                    moneyInWallet=activeAccount.getRp();
                                    System.out.println("Su nuevo saldo es de: "+activeAccount.getRp());
                                    System.out.println("Gracias por su compra.");
                                    wantingToBuy=1;
                                    
                                } 
                            }
                            
                        }
                        if(userChoice.equals("6")){
                            System.out.println("Nombre de cuenta:"+activeAccount.getAccountName());
                            String password= activeAccount.getPassword();
                            System.out.println("Contraseña: **********" +password.substring(password.length()-3,password.length()));
                            System.out.println("Nick: "+activeAccount.getNickname());
                            System.out.print("¿Desea cambiar su contraseña?\n-)Sí\n-)No\nIngrese su respuesta: ");
                            String clientAnswer= sc.nextLine();
                            while(!clientAnswer.equalsIgnoreCase("Si") && !clientAnswer.equalsIgnoreCase("Sí") & !clientAnswer.equalsIgnoreCase("NO")){
                                System.out.print("Respuesta inválida.Elija una de las opciones.\n-) Sí\n-) No\nIngrese su respuesta: ");
                                clientAnswer= sc.nextLine();
                            }
                            if(clientAnswer.equalsIgnoreCase("NO")){
                                System.out.println("See you");
                            }
                            else{
                                System.out.print("Ingrese su contraseña actual: ");
                                String enteredPs= sc.nextLine();
                                while(!enteredPs.equals(password)){
                                    System.out.print("La contraseña no coincide, intente nuevamente.");
                                    enteredPs= sc.nextLine();
                                }
                                System.out.print("Su contraseña debe de tener al menos 4 caracteres.\nIngrese su contraseña nueva: ");
                                String newPassword= scan.nextLine().replace(" ", "");
                                while(newPassword.length()<4){
                                    System.out.print("Error, su contraseña debe de tener al menos 4 caracteres.\nIngrese su contraseña: ");
                                    newPassword= sc.nextLine().replace(" ", "");
                                }
                                System.out.print("Ingrese su contraseña nueva nuevamente: ");
                                String newPassword2= scan.nextLine().replace(" ", "");
                                while(!newPassword2.equals(newPassword)){
                                    System.out.print("Error, su contraseña no coincide, intente nuevamente ");
                                    newPassword2= sc.nextLine().replace(" ", "");
                                }
                                
                                activeAccount.setPassword(newPassword);
                                System.out.println("Su contraseña ha sido cambiada exitosamente.");
                                
                            }
                        }
                        
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\nMenú principal");
                        System.out.println("Seleccione una de las siguientes opciones");
                        System.out.println("1) Comprar Skin \n2) Comprar Personaje\n3) Skins disponibles\n4) Mostrar inventario\n5) Recargar RP\n6) Mostrar Datos de cuenta\n7) Cerrar sesión\n8) Salir");
                        System.out.print("Ingrese una de las opciones (1-8): ");   
                        userChoice= sc.nextLine();
                        while(!userChoice.equals("1") && !userChoice.equals("2") && !userChoice.equals("3") && !userChoice.equals("4") &&
                                !userChoice.equals("5") && !userChoice.equals("6")&& !userChoice.equals("7")&& !userChoice.equals("8")){
                            System.out.println("Selección inválida.\nSeleccione una de las siguientes opciones");
                            System.out.println("1) Comprar Skin \n2) Comprar Personaje\n3) Skins disponibles\n4) Mostrar inventario\n5) Recargar RP\n6) Mostrar Datos de cuenta\n7) Cerrar sesión\n8) Salir");
                            System.out.print("Ingrese una de las opciones (1-8): ");   
                            userChoice= scan.nextLine();
                        }
                        
                    }
            
                }
                if(validAccount==-2){
                    System.out.print("Ingrese su contraseña: ");
                    String enteredPassword= scan.nextLine();
                    while(!enteredPassword.equalsIgnoreCase("ADMIN")){
                        System.out.print("La contraseña ingresada es incorrecta.\nPor favor ingrese su contraseña: ");
                        enteredPassword= scan.nextLine();
                    }
                    System.out.println("ACCESO ADMINISTRADOR");
                    System.out.println("Acceso exitoso.\nBienvenido al sistema ADMIN.");
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\nMenú principal");
                    System.out.println("Seleccione una de las siguientes opciones");
                    System.out.println("1) Desplegar ventas por rol \n2) Desplegar ventas por región\n3) Desplegar ventas por personaje\n4) Desplegar la cantidad de personajes por cada rol existente\n5) Agregar un personaje al juego\n6) Agregar un skin\n7) Bloquear a un jugador\n8) Desplegar todas las cuentas\n9) Cerrar sesión\n10) Salir");
                    System.out.print("Ingrese una de las opciones (1-10): ");   
                    String userChoice= scan.nextLine();
                    while(!userChoice.equals("1") && !userChoice.equals("2") && !userChoice.equals("3") && !userChoice.equals("4") &&
                            !userChoice.equals("5") && !userChoice.equals("6")&& !userChoice.equals("7")&& !userChoice.equals("8")&& !userChoice.equals("9")&& !userChoice.equals("10")){
                        System.out.println("Selección inválida.\nSeleccione una de las siguientes opciones");
                        System.out.println("1) Desplegar ventas por rol \n2) Desplegar ventas por región\n3) Desplegar ventas por personaje\n4) Desplegar la cantidad de personajes por cada rol existente\n5) Agregar un personaje al juego\n6) Agregar un skin\n7) Bloquear a un jugador\n8) Desplegar todas las cuentas\n9) Cerrar sesión\n10) Salir");
                        System.out.print("Ingrese una de las opciones (1-10): ");   
                        userChoice= scan.nextLine();
                    }
                    if(userChoice.equals("9")){
                        System.out.println("Hasta luego, see you soon!.");
                        logOut= true;
                        break;
                        
                    }
                    if(userChoice.equals("10")){
                        System.out.println("Hasta pronto, gracias por usar nuestros servicios.");
                        exit= true;
                        logOut= true;
                        break;
                    }
                    while(!userChoice.equals("9") && !userChoice.equals("10")){
                        if(userChoice.equals("1")){
                            System.out.println("Mostrando las recaudaciones de ventas por rol");
                            System.out.println("Ventas de Support: $"+soldPerRolSUP*6.15+" (CLP)");
                            System.out.println("Ventas de Attack Damage Carry: $"+soldPerRolADC*6.15+" (CLP)");
                            System.out.println("Ventas de Top Laner: $"+soldPerRolTOP*6.15+" (CLP)");
                            System.out.println("Ventas de Middle Laner: $"+soldPerRolMID*6.15+" (CLP)");
                            System.out.println("Ventas de Jungler: $"+soldPerRolJG*6.15+" (CLP)");
                    
                        }
                        if(userChoice.equals("2")){
                            System.out.println("Mostrando las recaudacion totales por regiones");
                            System.out.println("Recaudación total de la región LAS: $"+soldInLAS*6.15+" (CLP)");
                            System.out.println("Recaudación total de la región LAN: $"+soldInLAN*6.15+" (CLP)");
                            System.out.println("Recaudación total de la región EUW: $"+soldInEUW*6.15+" (CLP)");
                            System.out.println("Recaudación total de la región KR: $"+soldInKR*6.15+" (CLP)");
                            System.out.println("Recaudación total de la región NA: $"+soldInNA*6.15+" (CLP)");
                            System.out.println("Recaudación total de la región RU: $"+soldInRU*6.15+" (CLP)");
                            
                        }
                        if(userChoice.equals("3")){
                            System.out.println("Mostrando las recaudacion totales por personaje");
                            CharacterList eCharList= sys.buyingCharList();
                            int amountOfChars= eCharList.getCharacterAmount();
                            for(int i=0;i<amountOfChars;i++){
                                System.out.println("Nombre de personaje: "+eCharList.getCharacterList()[i].getCharacterName()+" Monto recaudado: $"+eCharList.getCharacterList()[i].getMoneyCollected()*6.15+" CLP");
                            }
                        }
                        
                        if(userChoice.equals("4")){
                            CharacterList eCharList= sys.buyingCharList();
                            int amountOfChars= eCharList.getCharacterAmount();
                            System.out.println("Mostrando la cantidad de personajes por cada rol existente.");
                            int supValue=0;
                            int adcValue=0;
                            int topValue=0;
                            int midValue=0;
                            int jgValue=0;
                            for(int i=0;i<amountOfChars;i++){
                                if(eCharList.getCharacterList()[i].getRol().equalsIgnoreCase("SUP")){
                                    supValue++;
                                }
                                if(eCharList.getCharacterList()[i].getRol().equalsIgnoreCase("ADC")){
                                    adcValue++;
                                }
                                if(eCharList.getCharacterList()[i].getRol().equalsIgnoreCase("TOP")){
                                    topValue++;
                                }
                                if(eCharList.getCharacterList()[i].getRol().equalsIgnoreCase("MID")){
                                    midValue++;
                                }
                                if(eCharList.getCharacterList()[i].getRol().equalsIgnoreCase("JG")){
                                    jgValue++;
                                }
                                
                            }
                            System.out.println("Cantidad de personajes Support: "+supValue+".");
                            System.out.println("Cantidad de personajes Attack Damage Carry: "+adcValue+".");
                            System.out.println("Cantidad de personajes Top Laner: "+topValue+".");
                            System.out.println("Cantidad de personajes Middle Laner: "+midValue+".");
                            System.out.println("Cantidad de personajes Jungler: "+jgValue+".");
                            
                        }
                        if(userChoice.equals("5")){
                            System.out.print("Ingrese el nombre del personaje: ");
                            
                            System.out.print("Ingrese el rol del personaje: ");
                            System.out.print("Ingrese la cantidad de skins del personaje: ");
                            
                        }
                        if(userChoice.equals("6")){
                            CharacterList eCharList= sys.buyingCharList();
                            String [] charsInSystem= new String[999];
                            int charsInSystemCounter= 0;
                            int totalCharsInSystem= eCharList.getCharacterAmount();
                            int totalSkinAmount= 0;
                            
                            for(int i=0;i<totalCharsInSystem;i++){
                                charsInSystem[i]=eCharList.getCharacterList()[i].getCharacterName();
                                totalSkinAmount+=eCharList.getCharacterList()[i].getSkinVariations();
                                charsInSystemCounter++;
                            }
                            
                            System.out.print("Ingrese el personaje a comprar una skin: ");
                            String chosenCharacter= scan.nextLine();
                            int check1=sys.elementInListString(charsInSystem, charsInSystemCounter, chosenCharacter);
                            while(check1==0){
                                System.out.print("Ingrese un personaje válido: ");
                                chosenCharacter= scan.nextLine();
                                check1=sys.elementInListString(charsInSystem, charsInSystemCounter, chosenCharacter);
                            }
                            
                            String [] characterSkins= new String[totalSkinAmount];
                            int characterSkinsCounter= 0;
                            
                            SkinList eSkinList= sys.buyingSkinList();                        
                            String [] skinsInSystem= new String[999];
                            int skinsInSystemCounter=0;

                           
                            int totalSkinsInSystem= eSkinList.getSkinAmount();
                            for(int i=0;i<totalSkinsInSystem;i++){
                                if(eSkinList.getSkinList()[i]!=null){
                                    if(eSkinList.getSkinList()[i].getCharacterName().equalsIgnoreCase(chosenCharacter)){
                                        characterSkins[characterSkinsCounter++]=eSkinList.getSkinList()[i].getSkinName();
                                    }
                                }
                            }
                            
                            for(int i=0;i<characterSkinsCounter;i++){
                                System.out.println(characterSkins[i]);
                            }
                            
                            System.out.print("Ingrese el nombre de la skin: ");
                            String eSkinName= scan.nextLine();
                            
                            int checkSkinName= sys.elementInListString(skinsInSystem, skinsInSystemCounter, eSkinName);
                            while(checkSkinName!=0){
                                System.out.print("El nombre ya existe.\nIngrese el nombre de la skin: ");
                                eSkinName= scan.nextLine();
                                checkSkinName= sys.elementInListString(skinsInSystem, skinsInSystemCounter, eSkinName);
                            }
                            
                            System.out.print("Ingrese la calidad: ");
                            String eSkinRarity= scan.nextLine();
                            while(!eSkinRarity.equalsIgnoreCase("M") && !eSkinRarity.equalsIgnoreCase("D") &&
                                    !eSkinRarity.equalsIgnoreCase("L") && !eSkinRarity.equalsIgnoreCase("E") && !eSkinRarity.equalsIgnoreCase("N")){
                                System.out.print("Ingrese la calidad: ");
                                eSkinRarity= scan.nextLine();
                            }
                            
                            eSkinList.addSkin(eSkinName, eSkinRarity, chosenCharacter);
                            for(int i=0;i<totalCharsInSystem;i++){
                                if(eCharList.getCharacterList()[i].getCharacterName().equalsIgnoreCase(chosenCharacter)){
                                    eCharList.getCharacterList()[i].setSkinVariations(eCharList.getCharacterList()[i].getSkinVariations()+1);
                                }
                            }
                            
                            System.out.println("Se ha agregado la skin de manera exitosa.");
                            
                            
                        }
                        
                        if(userChoice.equals("7")){
                            AccountList eAccount= sys.eAccountList();
                            String [] accountNames= new String[999];
                            int accountsInSystem=0;
                            int accountLength= eAccount.getAccountAmount();
                            int totalAccountNames= 0;
                            
                            for(int i=0;i<accountLength;i++){
                                if(eAccount.getAccountList()[i].getAccountName()!=null){
                                    accountNames[i]=eAccount.getAccountList()[i].getAccountName();
                                    totalAccountNames++;
                                }
                            }
                            
                            
                            System.out.print("Ingrese el nombre de la cuenta a bloquear: ");
                            String chosenAccountName= scan.nextLine();
                            int check1=sys.elementInListString(accountNames, totalAccountNames, chosenAccountName);
                            while(check1==0){
                                System.out.print("Ese nombre de cuenta no está registrado, ingrese otro: ");
                                chosenAccountName= scan.nextLine();
                                check1=sys.elementInListString(accountNames, totalAccountNames, chosenAccountName);
                            }
                            
                            
                            bannedAccounts[amountOfBannedAccounts++]=chosenAccountName;
                            System.out.println("La cuenta del jugador a sido baneada.");
                        
                        }
                        
                        if(userChoice.equals("8")){
                            System.out.println("Desplegando todas las cuentas de mayor a menor nivel.");
                            
                            int var1;
                            String var2;
                            AccountList eAccountList= sys.eAccountList();
                            int amountOfRegisteredAccounts= eAccountList.getAccountAmount();
                            String [] nicknamesRegisted= new String[amountOfRegisteredAccounts];
                            int [] levelsRegistered= new int[amountOfRegisteredAccounts];
                            for (int i = 0; i < amountOfRegisteredAccounts; i++) {
                                nicknamesRegisted[i]=eAccountList.getAccountList()[i].getNickname();
                                levelsRegistered[i]=eAccountList.getAccountList()[i].getAccountLevel();
                            }
                            for (int i = 0; i < amountOfRegisteredAccounts-1; i++) {
                                for (int j = 1; j < amountOfRegisteredAccounts; j++) {
                                    if(levelsRegistered[i]<levelsRegistered[j]){
                                        var1= levelsRegistered[i];
                                        levelsRegistered[i]=levelsRegistered[j];
                                        levelsRegistered[j]=var1;
                                        var2= nicknamesRegisted[i];
                                        nicknamesRegisted[i]=nicknamesRegisted[j];
                                        nicknamesRegisted[j]=var2;
                                    }
                                
                                }
                                
                            }
                            for (int i = 0; i < amountOfRegisteredAccounts; i++) {
                                System.out.println(nicknamesRegisted[i]+" "+levelsRegistered[i]);
                                
                            }
                            
                            
                        }
                        
                        
                        
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\nMenú principal");
                        System.out.println("Seleccione una de las siguientes opciones");
                        System.out.println("1) Desplegar ventas por rol \n2) Desplegar ventas por región\n3) Desplegar ventas por personaje\n4) Desplegar la cantidad de personajes por cada rol existente\n5) Agregar un personaje al juego\n6) Agregar un skin\n7) Bloquear a un jugador\n8) Desplegar todas las cuentas\n9) Cerrar sesión\n10) Salir");
                        System.out.print("Ingrese una de las opciones (1-10): ");   
                        userChoice= scan.nextLine();
                        while(!userChoice.equals("1") && !userChoice.equals("2") && !userChoice.equals("3") && !userChoice.equals("4") &&
                                !userChoice.equals("5") && !userChoice.equals("6")&& !userChoice.equals("7")&& !userChoice.equals("8")&& !userChoice.equals("9")&& !userChoice.equals("10")){
                            System.out.println("Selección inválida.\nSeleccione una de las siguientes opciones");
                            System.out.println("1) Desplegar ventas por rol \n2) Desplegar ventas por región\n3) Desplegar ventas por personaje\n4) Desplegar la cantidad de personajes por cada rol existente\n5) Agregar un personaje al juego\n6) Agregar un skin\n7) Bloquear a un jugador\n8) Desplegar todas las cuentas\n9) Cerrar sesión\n10) Salir");
                            System.out.print("Ingrese una de las opciones (1-10): ");   
                            userChoice= scan.nextLine();
                    }
                    }
                }
                
            }
        }
    }

}
    

