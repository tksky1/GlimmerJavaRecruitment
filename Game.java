package space.glimmer.lumine;

import java.util.ArrayList;
import java.util.Random;

class Player{
    double hunger;
    double health;
    String name;
    ArrayList<Item> inventory;
    Player(String name){
        this.name = name;
        health = 20; hunger = 20;
        inventory = new ArrayList<>();
    }

    public void play(){

        while(Game.day<=30){
            if(Game.gameOver) return;
            //在这里开始玩游戏吧!






        }
    }
}


public class Game {
    static int day = 0;
    static boolean gameOver = false;
    static Player player;
    static int monsterNow = 0;
    static ArrayList<Monster> monsters = new ArrayList<>();

    static void mine(){ // 玩家选择挖矿
        int num1 = new Random().nextInt(6)+1;
        int num2 = new Random().nextInt(3)+1;
        int num3 = new Random().nextInt(3);
        Iron iron = new Iron(); iron.count = num1;
        Diamond diamond = new Diamond(); diamond.count = num2;
        Netherite netherite = new Netherite(); netherite.count = num3;
        boolean tag1 = false;
        for(Item item : player.inventory){
            if(item instanceof Iron){
                tag1 = true; ((Iron) item).count+= num1;
            }
        }
        if(!tag1) player.inventory.add(iron);

        boolean tag2 = false;
        for(Item item : player.inventory){
            if(item instanceof Diamond){
                tag2 = true; ((Diamond) item).count+= num1;
            }
        }
        if(!tag2) player.inventory.add(diamond);

        boolean tag3 = false;
        for(Item item : player.inventory){
            if(item instanceof Netherite){
                tag3 = true; ((Netherite) item).count+= num3;
            }
        }
        if(!tag3) player.inventory.add(netherite);
        System.out.println(player.name+"通过挖矿获得了"+num1+"个铁，"+num2+"个钻石，"+num3+"个下届合金！");
        nextDay();
    }

    static void adventure(){ // 玩家选择冒险
        int num1 = new Random().nextInt(4)+1;
        int num2 = new Random().nextInt(3)+1;
        switch(num1){
            case 1:
                boolean tag = false;
                for(Item item : player.inventory){
                    if(item instanceof Apple){
                        tag = true; ((Apple) item).count+= num1;
                    }
                }
                if(!tag){
                    player.inventory.add(new Apple(num2));
                }
                System.out.println(player.name+"通过探索获得了"+num2+"个苹果！");
            case 2:
                boolean tag2 = false;
                for(Item item : player.inventory){
                    if(item instanceof Cake){
                        tag2 = true; ((Cake) item).count+= num1;
                    }
                }
                if(!tag2){
                    player.inventory.add(new Cake(num2));
                }
                System.out.println(player.name+"通过探索获得了"+num2+"个蛋糕！");
            case 3:
                boolean tag3 = false;
                for(Item item : player.inventory){
                    if(item instanceof GoldenApple){
                        tag3 = true; ((GoldenApple) item).count+= num1;
                    }
                }
                if(!tag3){
                    player.inventory.add(new GoldenApple(num2));
                }
                System.out.println(player.name+"通过探索获得了"+num2+"个金苹果！");
        }
        nextDay();
    }

    static void make(int id){ // 玩家选择合成武器
        // 1:铁剑 2：钻石剑 3：下界合金剑
        boolean materialAvailable = false;
        switch (id){
            case 1:
                for(Item item : player.inventory){
                    if(item instanceof Iron && ((Iron)item).count>=10){
                        ((Iron)item).count-=10;
                        player.inventory.add(new IronSword());
                        System.out.println(player.name+"合成了铁剑！");
                        materialAvailable = true; break;
                    }
                }
            case 2:
                for(Item item : player.inventory){
                    if(item instanceof Diamond && ((Diamond)item).count>=10){
                        ((Diamond)item).count-=10;
                        player.inventory.add(new DiamondSword());
                        System.out.println(player.name+"合成了钻石剑！");
                        materialAvailable = true; break;
                    }
                }
            case 3:
                for(Item item : player.inventory){
                    if(item instanceof Netherite && ((Netherite)item).count>=10){
                        ((Netherite)item).count-=10;
                        player.inventory.add(new Netherite());
                        System.out.println(player.name+"合成了下届合金剑！");
                        materialAvailable = true; break;
                    }
                }
        }
        if(!materialAvailable){
            System.out.println("材料不够！合成失败！");
        }
        nextDay();
    }

    static Monster getNextMonster(){
        return monsters.get(monsterNow);
    }

    static void nextDay(){
        if(Game.gameOver) return;
        if(player.hunger<=0){ player.health -= 5; System.out.println("由于太饿了，"+player.name+"损失了5点生命值！");}
        if(player.health<=0){
            System.out.println(player.name+"去世了！游戏失败！gg！");
            gameOver = true; return;
        }
        if(player.hunger>=16){ player.health = Math.min(20,player.health+2); System.out.println("吃饱喝足，"+player.name+
                "回复了2点生命值！");}
        day++; player.hunger = Math.max(0,player.hunger-2);
        System.out.println("第"+day+"天结束！今天健康值为"+player.health+"，饥饿值为"+player.hunger);
        if(day>=30){
            System.out.println("30天生存时限到！你挑战了"+monsterNow+"只怪物！未挑战全部怪物，游戏失败！");
            gameOver = true;
        }
    }

    public static void main(String[] args){
        monsters.add(new Zombie());
        monsters.add(new Creeper());
        monsters.add(new Zombie());
        monsters.add(new Zombie());
        monsters.add(new Creeper());
        monsters.add(new EnderDragon());
        player = new Player("Lumine");
        player.play();
    }
}
