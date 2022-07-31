package space.glimmer.lumine;

interface Monster{
    void attack();
    void runAway();
}

class Creeper implements Monster{
    double health = 20;
    @Override
    public void attack() {
        Game.player.health-=5;
        double maxPower = 0;
        for(Item item : Game.player.inventory){
            if(item instanceof Sword){
                if(item instanceof IronSword) maxPower = Math.max(maxPower, ((IronSword)item).power);
                if(item instanceof DiamondSword) maxPower = Math.max(maxPower, ((DiamondSword)item).power);
                if(item instanceof NetheriteSword) maxPower = Math.max(maxPower, ((NetheriteSword)item).power);
            }
        }
        health -= maxPower;
        System.out.println(Game.player.name+"选择攻击苦力怕！减少了5点生命值！对苦力怕造成"+maxPower+"点伤害！");
        if(health<=0) {
            System.out.println("苦力怕被"+Game.player.name+"击杀了！");
            Game.monsterNow++;
        }
        Game.nextDay();
    }

    @Override
    public void runAway() {
        Game.player.hunger-=5;
        System.out.println(Game.player.name+"选择逃离苦力怕！减少了5点饥饿值！");
        Game.monsterNow++;
        Game.nextDay();
    }
}

class Zombie implements Monster{
    double health = 20;
    @Override
    public void attack() {
        Game.player.health-=2;
        double maxPower = 0;
        for(Item item : Game.player.inventory){
            if(item instanceof Sword){
                if(item instanceof IronSword) maxPower = Math.max(maxPower, ((IronSword)item).power);
                if(item instanceof DiamondSword) maxPower = Math.max(maxPower, ((DiamondSword)item).power);
                if(item instanceof NetheriteSword) maxPower = Math.max(maxPower, ((NetheriteSword)item).power);
            }
        }
        health -= maxPower;
        System.out.println(Game.player.name+"选择攻击僵尸！减少了2点生命值！对僵尸造成"+maxPower+"点伤害！");
        if(health<=0) {
            System.out.println("僵尸被"+Game.player.name+"击杀了！");
            Game.monsterNow++;
        }
        Game.nextDay();
    }

    @Override
    public void runAway() {
        Game.player.hunger-=15;
        System.out.println(Game.player.name+"选择逃离僵尸！减少了15点饥饿值！");
        Game.monsterNow++;
        Game.nextDay();
    }
}

class EnderDragon implements Monster{
    double health = 40;
    @Override
    public void attack() {
        Game.player.health-=3;
        double maxPower = 0;
        for(Item item : Game.player.inventory){
            if(item instanceof Sword){
                if(item instanceof IronSword) maxPower = Math.max(maxPower, ((IronSword)item).power);
                if(item instanceof DiamondSword) maxPower = Math.max(maxPower, ((DiamondSword)item).power);
                if(item instanceof NetheriteSword) maxPower = Math.max(maxPower, ((NetheriteSword)item).power);
            }
        }
        health -= maxPower;
        System.out.println(Game.player.name+"选择攻击末影龙！减少了3点生命值！对末影龙造成"+maxPower+"点伤害！");
        if(health<=0) {
            System.out.println("末影龙被"+Game.player.name+"击杀了！末地迎来了解放！");
            System.out.println("恭喜"+Game.player.name+"获得了游戏胜利！");
            Game.gameOver = true; return;
        }
        Game.nextDay();
    }

    @Override
    public void runAway() {
        Game.player.hunger-=10;
        System.out.println(Game.player.name+"末影龙穷追不舍，无法逃离！勇敢的击杀它吧！减少了10点饥饿值！");
        Game.nextDay();
    }
}