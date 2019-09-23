import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.*;
class Node{
    private int stage_num;
    private int prev_stage_num;
    Random rand = new Random();
    private int monster_decider=0;
    private monster stage_monster;
    Node(int n){
        this.stage_num=n;
        monster_decider=1+rand.nextInt(3);
        if(n==12){
            monster_decider=4;
        }
        if(n==1){
            this.prev_stage_num=1;
        }
        if(monster_decider==1){
            stage_monster=new goblins();
        }
        else if(monster_decider==2){
            stage_monster=new zombies();
        }
        else if(monster_decider==3){
            stage_monster=new fiends();
        }
        else{
            stage_monster=new lionfang();
        }
    }

    public int getStage_num() {
        return this.stage_num;
    }

    public void setStage_num(int stage_num) {
        this.stage_num = stage_num;
    }

    public int getPrev_stage_num() {
        return prev_stage_num;
    }

    public void setPrev_stage_num(int prev_stage_num) {
        this.prev_stage_num = prev_stage_num;
    }

    public monster getStage_monster() {
        return this.stage_monster;
    }

    public void setStage_monster(monster stage_monster) {
        this.stage_monster = stage_monster;
    }
}
class map{
    private int number_of_vertices=12;
    public LinkedList<Integer> adjacencyLinkedList[];
    map(){
        adjacencyLinkedList = new LinkedList[number_of_vertices];
        for(int i = 0; i < number_of_vertices ; i++){ 
            adjacencyLinkedList[i] = new LinkedList<Integer>(); 
        }
    }
    public void addEdge(int src, int dest) 
    {
        this.adjacencyLinkedList[src].add(dest);
    }
    public void create_map(){
        this.addEdge(1, 2);
        this.addEdge(1, 5);
        this.addEdge(1, 8);
        this.addEdge(2, 3);
        this.addEdge(2, 6);
        this.addEdge(2, 9);
        this.addEdge(5, 3);
        this.addEdge(5, 6);
        this.addEdge(5, 9);
        this.addEdge(8, 3);
        this.addEdge(8, 6);
        this.addEdge(8, 9);
        this.addEdge(3, 4);
        this.addEdge(3, 7);
        this.addEdge(3, 10);
        this.addEdge(6, 4);
        this.addEdge(6, 7);
        this.addEdge(6, 10);
        this.addEdge(9, 4);
        this.addEdge(9, 7);
        this.addEdge(9, 10);
        this.addEdge(10, 11);
        this.addEdge(7, 11);
        this.addEdge(4, 11);
    }
}
abstract class monster{
    protected final String monster_name;
    protected final int monster_level;
    protected int monster_health=0;
    protected int monster_max_hp=0;
    protected Random r = new Random();
    monster(int a,String name){
        this.monster_level=a;
        this.monster_health=0;
        this.monster_name=name;
        this.monster_max_hp=0;
    }
    // monster(){
    //     this.monster_health=0;
    // }
    public int getMonster_level() {
        return monster_level;
    }
    public int getMonster_health() {
        return monster_health;
    }
    public void setMonster_health(int monster_health) {
        this.monster_health = monster_health;
    }
    public String getMonster_name() {
        return monster_name;
    }

    public int getMonster_max_hp() {
        return monster_max_hp;
    }

    public void setMonster_max_hp(int monster_max_hp) {
        this.monster_max_hp = monster_max_hp;
    }
    public int monster_random_attack(int h){
        double a=0;
        a=(h/8)+r.nextGaussian();
        if(a>h/4){
            a=h/4;
        }
        if(a<0){
            a=0;
        }
        // while(a>h/4 || a<0){
        //     a=(h/8) +r.nextGaussian();
        // }
        // int b = ((int)a)*h;
        return (int)a;
    }


}
class goblins extends monster{
    goblins(){
        super(1,"goblins");
        this.setMonster_health(100);
        this.setMonster_max_hp(100);
    }
}
class zombies extends monster{
    zombies(){
        super(2,"zombies");
        this.setMonster_health(100);
        this.setMonster_max_hp(100);
    }
}
class fiends extends monster{
    fiends(){
        super(3,"fiends");
        this.setMonster_health(200);
        this.setMonster_max_hp(200);
    }
}
class lionfang extends monster{
    lionfang(){
        super(4,"lionfang");
        this.setMonster_health(250);
        this.setMonster_max_hp(250);
    }
}
abstract class hero{
    protected final String hero_name;
    protected int hero_attack;
    protected int hero_defense;
    protected int hero_xp;
    protected int hero_hp;
    protected int hero_level;
    protected int hero_max_hp;
    protected int counter ;

    hero(String name){
        this.hero_name=name;
        this.hero_attack=0;
        this.hero_defense=0;
        this.hero_xp=0;
        this.hero_hp=100;
        this.hero_level=1;
        this.hero_max_hp=100;
        this.counter=0;
    }
    public abstract boolean isthief();
    public void check_if_level_upgrade(){
        if(this.getHero_level()==1){
            if(this.getHero_xp()>=20){
                System.out.println("Hero upgraded to level 2");
                this.setHero_level(2);
                this.setHero_max_hp(150);
                this.setHero_xp(this.getHero_xp()-20);
                this.setHero_attack(this.getHero_attack()+1);
                this.setHero_defense(this.getHero_defense()+1);
                this.check_if_level_upgrade();
            }
        }
        else if(this.getHero_level()==2){
            if(this.getHero_xp()>=40){
                System.out.println("Hero upgraded to level 3");
                this.setHero_max_hp(200);
                this.setHero_level(3);
                this.setHero_attack(this.getHero_attack()+1);
                this.setHero_defense(this.getHero_defense()+1);
                this.setHero_xp(this.getHero_xp()-40);
                this.check_if_level_upgrade();
            }
        }
        else if(this.getHero_level()==3){
            if(this.getHero_xp()>=60){
                System.out.println("Hero upgraded to level 4");
                this.setHero_max_hp(250);
                this.setHero_attack(this.getHero_attack()+1);
                this.setHero_defense(this.getHero_defense()+1);
                this.setHero_level(4);
                this.setHero_xp(this.getHero_xp()-60);
            }
        }
        
    }
    public abstract void special_power(monster m,user u);

    public String getHero_name() {
        return hero_name;
    }
    public int getHero_xp() {
        return hero_xp;
    }
    public void setHero_xp(int hero_xp) {
        this.hero_xp = hero_xp;
    }
    public int getHero_hp() {
        return hero_hp;
    }
    public void setHero_hp(int hero_hp) {
        this.hero_hp = hero_hp;
    }
    public int getHero_level() {
        return hero_level;
    }
    public void setHero_level(int hero_level) {
        this.hero_level = hero_level;
    }
    public int getHero_max_hp() {
        return hero_max_hp;
    }
    public void setHero_max_hp(int hero_max_hp) {
        this.hero_max_hp = hero_max_hp;
    }
    public int getHero_attack() {
        return hero_attack;
    }
    public void setHero_attack(int hero_attack) {
        this.hero_attack = hero_attack;
    }
    public int getHero_defense() {
        return hero_defense;
    }
    public void setHero_defense(int hero_defense) {
        this.hero_defense = hero_defense;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
class warrior extends hero{
    private int count=0;
    warrior(){
        super("warrior");
        this.setHero_attack(10);
        this.setHero_defense(3);
    }
    @Override
    public void special_power(monster m,user u) {
        if(this.count==0){
            this.setHero_attack(this.getHero_attack()+5);
            this.setHero_defense(this.getHero_defense()+5);
            this.count++;
        }
        System.out.println("Special power activated");
        System.out.println("Performing special attack");
        System.out.println("Your HP: "+this.getHero_hp()+"/"+this.getHero_max_hp()+" Monster HP: "+m.getMonster_health()+"/"+m.getMonster_max_hp());
        System.out.println("Monster Attack.");
        int temp=m.monster_random_attack(m.getMonster_health());
        System.out.println("The monster has attacked and inflicted "+temp+" damage to you");
        this.setHero_hp(this.getHero_hp()-temp);
        if(this.getHero_hp()<=0){
            u.gameend();
            u.setGamerun(false);
        }
        System.out.println("Your HP: "+this.getHero_hp()+"/"+this.getHero_max_hp()+" Monster HP: "+m.getMonster_health()+"/"+m.getMonster_max_hp());
        this.setCounter(this.getCounter()+1);
        System.out.println("Special power deactivated");
        this.setCounter(0);
        
    }
    @Override
    public boolean isthief(){
        return false;
    }
}
class mage extends hero{
    mage(){
        super("mage");
        this.setHero_attack(5);
        this.setHero_defense(5);
    }
    @Override
    public void special_power(monster m,user u) {
        System.out.println("Special power activated");
        System.out.println("Performing special attack");
        System.out.println("Your HP: "+this.getHero_hp()+"/"+this.getHero_max_hp()+" Monster HP: "+m.getMonster_health()+"/"+m.getMonster_max_hp());
        System.out.println("Monster Attack.");
        int temp=m.monster_random_attack(m.getMonster_health());
        System.out.println("The monster has attacked and inflicted "+temp+" damage to you");
        this.setHero_hp(this.getHero_hp()-temp);
        if(this.getHero_hp()<=0){
            u.gameend();
            u.setGamerun(false);
        }
        System.out.println("Your HP: "+this.getHero_hp()+"/"+this.getHero_max_hp()+" Monster HP: "+m.getMonster_health()+"/"+m.getMonster_max_hp());
        this.setCounter(this.getCounter()+1);
        System.out.println("Special power deactivated");
        this.setCounter(0);
        
    }
    @Override
    public boolean isthief(){
        return false;
    }
}
class thief extends hero{
    thief(){
        super("thief");
        this.setHero_attack(6);
        this.setHero_defense(4);
    }
    @Override
    public void special_power(monster m,user u) {
        System.out.println("Special power activated");
        System.out.println("Performing special attack");
        System.out.println("You have stolen " +((m.getMonster_health()*3)/10)+" Hp from the monster!");
        this.setHero_hp(getHero_hp()+(m.getMonster_health()*3)/10);
        m.setMonster_health((m.getMonster_health()*7)/10);
        if(this.getHero_hp()>this.getHero_max_hp()){
            this.setHero_max_hp(this.getHero_hp());
        }
        System.out.println("Your HP: "+this.getHero_hp()+"/"+this.getHero_max_hp()+" Monster HP: "+m.getMonster_health()+"/"+m.getMonster_max_hp());
        System.out.println("Monster Attack.");
        int temp=m.monster_random_attack(m.getMonster_health());
        System.out.println("The monster has attacked and inflicted "+temp+" damage to you");
        this.setHero_hp(this.getHero_hp()-temp);
        if(this.getHero_hp()<=0){
            u.gameend();
            u.setGamerun(false);
        }
        System.out.println("Your HP: "+this.getHero_hp()+"/"+this.getHero_max_hp()+" Monster HP: "+m.getMonster_health()+"/"+m.getMonster_max_hp());
        this.setCounter(this.getCounter()+1);
        System.out.println("Special power deactivated");
        this.setCounter(0);
        
    }
    @Override
    public boolean isthief(){
        return true;
    }
}
class healer extends hero{
    healer(){
        super("healer");
        this.setHero_attack(4);
        this.setHero_defense(8);
    }
    @Override
    public void special_power(monster m,user u) {
        System.out.println("Special power activated");
        System.out.println("Performing special attack");
        System.out.println("Your HP: "+this.getHero_hp()+"/"+this.getHero_max_hp()+" Monster HP: "+m.getMonster_health()+"/"+m.getMonster_max_hp());
        System.out.println("Monster Attack.");
        int temp=m.monster_random_attack(m.getMonster_health());
        System.out.println("The monster has attacked and inflicted "+temp+" damage to you");
        this.setHero_hp(this.getHero_hp()-temp);
        if(this.getHero_hp()<=0){
            u.gameend();
            u.setGamerun(false);
        }
        System.out.println("Your HP: "+this.getHero_hp()+"/"+this.getHero_max_hp()+" Monster HP: "+m.getMonster_health()+"/"+m.getMonster_max_hp());
        this.setCounter(this.getCounter()+1);
        System.out.println("Special power deactivated");
        this.setCounter(0);
        
    }
    @Override
    public boolean isthief(){
        return false;
    }
}

class user{
    private final String user_name;
    private final hero user_hero;
    private map user_map;
    private ArrayList<Node> game_graph;
    private int current_stage_number;
    boolean check=false;
    private boolean gamerun=true;
    private boolean showhint=true;
    Scanner scan = new Scanner(System.in);
    user(String user_name,int hero_number){
        this.user_map=new map();
        this.user_map.create_map();
        this.current_stage_number=1;
        this.game_graph=new ArrayList<Node>();
        for(int i=1;i<11;i++){
            game_graph.add(new Node(i));
        }
        game_graph.add(new Node(12));
        this.user_name=user_name;
        if(hero_number==1){ this.user_hero=new warrior();}
        else if(hero_number==2){ this.user_hero=new thief();}
        else if(hero_number==3){ this.user_hero=new mage();}
        else{ this.user_hero=new healer();}
    }
    public int show_locations(int n,gamestart g){
        if(this.showhint){
            System.out.println("Show hint press 1 for yes and 0 for no");
            int asd = scan.nextInt();
            if(asd==1){
                this.hint();
            }
            this.showhint=false;
        }
        int count=1;
        Iterator<Integer> i = user_map.adjacencyLinkedList[n].iterator();
        while(i.hasNext()){
            System.out.println(count+") Go to location "+ i.next());
            count++;
        }
        System.out.println(count+") Go to previous location "+game_graph.get(n-1).getPrev_stage_num());
        System.out.println("Enter -1 to exit");
        int a = scan.nextInt();
        int abc=0;
        if(a==-1){
            g.startgame();
        }
        else{
            if(a==count){
                abc=game_graph.get(n-1).getPrev_stage_num();
            }
            else{
                abc=user_map.adjacencyLinkedList[n].get(a-1);
                this.game_graph.get(abc-1).setPrev_stage_num(n);
            }
        }
        return abc;
    }
    public void hint(){
        int[] arr = new int[12];
        int[] ans = new int[10];
        for(int i=1;i<12;i++){
            arr[i]=this.game_graph.get(i-1).getStage_monster().getMonster_level();
        }
        int a = Math.min(arr[2],arr[5]);
        a = Math.min(a,arr[8]);
        for(int i=2;i<9;i=i+3){
            if(a==arr[i]){
                ans[1]=i;
                ans[6]=arr[i];
            }
        }
        a = Math.min(arr[3],arr[6]);
        a = Math.min(a,arr[9]);
        for(int i=3;i<10;i=i+3){
            if(a==arr[i]){
                ans[2]=i;
                ans[7]=arr[i];
            }
        }
        a = Math.min(arr[4],arr[7]);
        a = Math.min(a,arr[10]);
        for(int i=4;i<11;i=i+3){
            if(a==arr[i]){
                ans[3]=i;
                ans[8]=arr[i];
            }
        }
        ans[0]=1;
        ans[5]=arr[1];
        ans[4]=11;
        ans[9]=arr[11];
        System.out.println("Go to ");
        for(int i=0;i<4;i++){
            System.out.print(" location-"+ans[i]+" monster level-"+ans[i+5]+" ----------> ");
        }
        System.out.println(" location-"+ans[4]+" monster level-"+ans[9]+" aka The Boss Lionfang");
    }
    public void gameend(){
        System.out.println("--------Game ended--------");
        System.exit(1);
    }
    public void gamefinish(){
        System.out.println("------------Game Completed------------");
        System.exit(1);
    }
    public void newfight(hero h,monster m,int location,gamestart g){
        System.out.println("----Monster killed----");
        h.setHero_xp(h.getHero_xp()+m.getMonster_level()*20);
        h.check_if_level_upgrade();
        h.setHero_hp(h.getHero_max_hp());
        this.gamerun=true;
        // this.check=false;
        h.setCounter(0);
        m.setMonster_health(m.getMonster_max_hp());
        this.setCurrent_stage_number(location);
        this.maingame(location,g);
    }
    public void fightmonster(int location,monster m,hero h,gamestart g){
        if(this.gamerun){
            int move;
            int temp;
            
            System.out.println("Choose move:");
            System.out.println("1) Attack");
            System.out.println("2) Defense");
            if(h.getCounter()>=3){
                System.out.println("3) Special Power");
            }
            if(check){
                if(h.getCounter()==3 && h.getHero_name().equals("warrior")){
                    h.setHero_attack(h.getHero_attack()-5);
                    h.setHero_defense(h.getHero_defense()-5);
                    check=false;
                }
                else if(h.getCounter()<=3 && h.getHero_name().equals("mage")){
                    m.setMonster_health((m.getMonster_health()*95)/100);
                }
                else{
                    h.setHero_hp((h.getHero_hp()*105)/100);
                }
                if(h.getCounter()==3){ check=false;}
            }
            move=scan.nextInt();
            if(move==1){
                System.out.println("You chose to attack");
                System.out.println("You attacked and inflicted "+h.getHero_attack()+" damage to the monster");
                m.setMonster_health(m.getMonster_health()-h.getHero_attack());
                if(m.getMonster_health()<=0 && location==11){
                    this.gamerun=false;
                    this.gamefinish();
                }
                if(m.getMonster_health()<=0){
                    this.gamerun=false;
                    this.newfight(h,m,location,g);
                }
                System.out.println("Your HP: "+h.getHero_hp()+"/"+h.getHero_max_hp()+" Monster HP: "+m.getMonster_health()+"/"+m.getMonster_max_hp());
                System.out.println("Monster Attack.");
                if(location==11){
                    Random rand = new Random();
                    int ghj =rand.nextInt(9);
                    if(ghj==0){
                        temp=m.monster_random_attack(m.getMonster_health())+(h.getHero_hp()/2);
                    }
                    else{
                        temp=m.monster_random_attack(m.getMonster_health());
                    }
                }
                else{
                    temp=m.monster_random_attack(m.getMonster_health());
                }
                System.out.println("The monster has attacked and inflicted "+temp+" damage to you");
            
                h.setHero_hp(h.getHero_hp()-temp);
                if(h.getHero_hp()<=0){
                    this.gameend();
                    this.gamerun=false;
                    System.out.println("Your HP: "+h.getHero_hp()+"/"+h.getHero_max_hp()+" Monster HP: "+m.getMonster_health()+"/"+m.getMonster_max_hp());
                    this.fightmonster(location,m,h,g);
                }
                System.out.println("Your HP: "+h.getHero_hp()+"/"+h.getHero_max_hp()+" Monster HP: "+m.getMonster_health()+"/"+m.getMonster_max_hp());
                h.setCounter(h.getCounter()+1);
                this.fightmonster(location, m, h,g);
            }
            else if(move==2){
                System.out.println("You chose to defend");
                System.out.println("Monster attack reduced by "+h.getHero_defense()+"!");
                System.out.println("Your HP: "+h.getHero_hp()+"/"+h.getHero_max_hp()+" Monster HP: "+m.getMonster_health()+"/"+m.getMonster_max_hp());
                System.out.println("Monster Attack.");
                temp=m.monster_random_attack(m.getMonster_health())-h.getHero_defense();
                System.out.println("The monster has attacked and inflicted "+temp+" damage to you");
                h.setHero_hp(h.getHero_hp()-temp);
                if(h.getHero_hp()<=0){
                    this.gameend();
                    this.gamerun=false;
                }
                System.out.println("Your HP: "+h.getHero_hp()+"/"+h.getHero_max_hp()+" Monster HP: "+m.getMonster_health()+"/"+m.getMonster_max_hp());
                h.setCounter(h.getCounter()+1);
                this.fightmonster(location, m, h,g);
            }
            else if(move==3 && h.getCounter()>=3 && h.isthief()){
                h.special_power(m,this);
                this.fightmonster(location, m, h,g);
                
            }
            else if(move==3 && h.getCounter()>=3){
                check=true;
                h.special_power(m,this);
                this.fightmonster(location, m, h,g);        
            }
            else{
                System.out.println("Invalid option try again");
                this.fightmonster(location, m, h,g);
            }
        }
    }
    public String getUser_name() {
        return user_name;
    }
    public hero getUser_hero() {
        return user_hero;
    }
    public void maingame(int n,gamestart g){
        int a=this.show_locations(n,g);
        System.out.println("Moving to location "+a);
        System.out.println("Fight started .You're fighting a level "+game_graph.get(a-1).getStage_monster().getMonster_level()+" Monster.");
        this.fightmonster(a,game_graph.get(a-1).getStage_monster(),user_hero,g);
    }
    public boolean isGamerun() {
        return gamerun;
    }
    public void setGamerun(boolean gamerun) {
        this.gamerun = gamerun;
    }
    public int getCurrent_stage_number() {
        return current_stage_number;
    }
    public void setCurrent_stage_number(int current_stage_number) {
        this.current_stage_number = current_stage_number;
    }
    public map getUser_map() {
        return user_map;
    }
    public void setUser_map(map user_map) {
        this.user_map = user_map;
    }
    public ArrayList<Node> getGame_graph() {
        return game_graph;
    }
    public void setGame_graph(ArrayList<Node> game_graph) {
        this.game_graph = game_graph;
    }
    public boolean isCheck() {
        return check;
    }
    public void setCheck(boolean check) {
        this.check = check;
    }
    public boolean isShowhint() {
        return showhint;
    }
    public void setShowhint(boolean showhint) {
        this.showhint = showhint;
    }
    public Scanner getScan() {
        return scan;
    }
    public void setScan(Scanner scan) {
        this.scan = scan;
    }
}

class gamestart{
    private ArrayList<user> user_arr;
    Scanner scan = new Scanner(System.in);
    String user_name;
    int user_hero_number;
    boolean check_exist_user=false;
    gamestart(){
        user_arr=new ArrayList<user>();
    }
    public void startgame(){
        System.out.println("Welcome to ArchLegends");
        System.out.println("Choose you option");
        System.out.println("1) New User");
        System.out.println("2) Existing User");
        System.out.println("3) Exit");
        switch (scan.nextInt()){
            case 1:
                System.out.println("Enter Username");
                user_name=scan.next();
                System.out.println("Choose a Hero");
                System.out.println("1) Warrior");
                System.out.println("2) Thief");
                System.out.println("3) Mage");
                System.out.println("4) Healer");
                user_hero_number = scan.nextInt();
                user new_user = new user(user_name,user_hero_number);
                user_arr.add(new_user);
                System.out.println("User creation done.");
                System.out.println("Username : "+new_user.getUser_name());
                System.out.println("Hero type : "+new_user.getUser_hero().getHero_name());
                System.out.println("Login to play the game.");
                this.startgame();
                break;
            case 2:
                System.out.println("Enter username");
                user_name=scan.next();
                user temp;
                for(int i=0;i<user_arr.size();i++){
                    if(user_arr.get(i).getUser_name().equals(user_name)){
                        check_exist_user=true;
                        temp=user_arr.get(i);
                        System.out.println("User Found.");
                        System.out.println("Logging in...");
                        System.out.println("Welcome "+user_arr.get(i).getUser_name());
                        if(temp.getCurrent_stage_number()==1){
                            System.out.println("You are at the starting location(1). Choose path");
                        }
                        else{
                            System.out.println("You are at location "+temp.getCurrent_stage_number()+" .Choose path");
                        }
                        temp.maingame(temp.getCurrent_stage_number(),this);
                    }
                }
                if(!check_exist_user){
                    System.out.println("Invalid user");
                    this.startgame();
                }
                break;
            case 3:
                System.out.println("--------Thank You for Playing--------");
                break;
            default:
                System.out.println("Invalid option try again");
                break;
        }
    }
}

public class game {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        gamestart g = new gamestart();
        g.startgame();
        
        
    }
}