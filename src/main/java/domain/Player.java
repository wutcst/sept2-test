package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 游玩者类
 */
public class Player {
    //private static Player player; // 单例实例
    private String name; // 玩家姓名
    private int health;  // 生命值
    private int attack;  // 攻击力
    private int defense; // 防御力
    private Room currentRoom; // 玩家当前所在的房间
    private Stack<Room> roomStack;  // 玩家经过的房间
    private int carryingCapacity; // 玩家负载上限
    private int currentLoad; // 玩家当前负载
    private List<Item> items; // 玩家携带的物品列表

    /**
     * 静态内部类
     */
    private static class PlayerHolder {
        private static final Player INSTANCE = createPlayerInstance();
    }
    /**
     * 获取Player类的单例实例
     * @return Player类的单例实例
     */
    public static Player getPlayer() {
        return PlayerHolder.INSTANCE;
    }

    /**
     * 初始化player的属性
     * @return Player类的单例实例
     */
    private static Player createPlayerInstance() {
        Player newInstance = new Player();
        newInstance.setName("link");
        newInstance.health = 10;
        newInstance.attack = 2;
        newInstance.defense = 0;
        newInstance.setCarryingCapacity(10);
        newInstance.setCurrentLoad(0);
        newInstance.roomStack = new Stack<>();
        newInstance.items = new ArrayList<>();
        return newInstance;
    }

    /**
     * 私有构造函数，确保只能通过getInstance()方法获取实例
     */
    private Player() {
    }

    /**
     * 获取玩家姓名
     * @return 玩家姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置玩家姓名
     * @param name 玩家姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取玩家当前所在的房间
     * @return 玩家当前所在的房间
     */
    public Room getCurrentRoom() {
        Room copy = new Room(currentRoom);
        return copy;
    }

    /**
     * 设置玩家当前所在的房间
     * @param currentRoom 玩家当前所在的房间
     */
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = new Room(currentRoom);
    }

    /**
     * 进入新的房间
     * @param room 玩家将要进入的房间
     */
    public void enterRoom(Room room) {
        roomStack.push(currentRoom);
        currentRoom = new Room(room);
    }

    /**
     * 回到上一个房间
     */
    // 回到上一个房间
    public void goBack() {
        if(roomStack.empty()){
            System.out.println("你已经回到了起点！");
        }
        else{
            currentRoom = roomStack.pop();
            System.out.println(currentRoom.getDescription());
        }
    }

    /**
     * 获取玩家的负重能力
     * @return 玩家的负重能力
     */
    public int getCarryingCapacity() {
        return carryingCapacity;
    }

    /**
     * 设置玩家的负重能力
     * @param carryingCapacity 玩家的负重能力
     */
    public void setCarryingCapacity(int carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
    }

    /**
     * 获取玩家当前负重
     * @return 玩家当前负重
     */
    public int getCurrentLoad() {
        return currentLoad;
    }

    /**
     * 设置玩家当前负重
     * @param currentLoad 玩家当前负重
     */
    public void setCurrentLoad(int currentLoad) {
        this.currentLoad = currentLoad;
    }

    /**
     * 获取玩家携带的物品列表
     * @return 玩家携带的物品列表
     */
    public List<Item> getPlayerItems() {
        return new ArrayList<>(items);
    }

    /**
     * 添加物品到玩家的携带物品列表
     * @param item 要添加的物品
     */
    public void addItem(Item item) {
        items.add(item);
        currentLoad += item.getWeight();
        if(item.getName().equals("武器")){
            this.attack+=3;
        }
        else if(item.getName().equals("护盾")){
            this.defense+=3;
        }
    }

    /**
     * 从玩家的携带物品列表中移除物品
     * @param item 要移除的物品
     */
    public void removeItem(Item item) {
        items.remove(item);
        currentLoad -= item.getWeight();
        if(item.getName().equals("武器")){
            this.attack-=3;
        }
        else if(item.getName().equals("护盾")){
            this.defense-=3;
        }
    }

    /**
     * 玩家与怪物交战逻辑判断
     * @return 玩家血量
     */
    public boolean fight(Monster monster){
        System.out.println("你与怪物发生了交战！！！");
        display();
        monster.display();
        while(getPlayer().health>0 && monster.getHealth()>0){
            int playerDamage = Math.max(0, monster.getAttack() - getPlayer().getDefense());
            int monsterDamage = Math.max(0, getPlayer().getAttack() - monster.getDefense());
            getPlayer().setHealth(getPlayer().getHealth()-monsterDamage);
            monster.setHealth(monster.getHealth()-playerDamage);
            getPlayer().displayHealth();
            monster.displayHealth();
            System.out.println();
        }
        return getPlayer().health>0;
    }

    /**
     * 设置玩家血量
     * @param  health 玩家血量
     */
    private void setHealth(int health) {
        this.health=health;
    }

    /**
     * 获取玩家血量
     * @return health 玩家血量
     */
    public int getHealth() {
        return health;
    }
    /**
     * 获取玩家攻击力
     * @return 玩家攻击力
     */
    public int getAttack() {
        return attack;
    }

    /**
     * 获取玩家防御力
     * @return 玩家防御力
     */
    public int getDefense() {
        return defense;
    }

    public void display(){
        System.out.println("当前生命值："+this.health+" 攻击力："+this.attack+" 防御力："+this.defense);
    }

    public void displayHealth(){
        System.out.print("当前生命值："+health+"         ");
    }

    public void displayLoad(){
        System.out.println("当前负重上限："+carryingCapacity);
    }

    /**
     * 获取玩家的物品列表
     * @return items 返回物品列表
     */
    public List<Item> getItems() {return new ArrayList<>(items);}

    /**
     * 玩家食用物品
     * @param  item 要食用的物品
     */
    public void eatItem(Item item) {
        if (item.getcanEat()) {
            items.remove(item);
            currentLoad -= item.getWeight();
            System.out.println("你吃掉了 " + item.getName() + ".");
            if(item.getName().equals("魔法饼干")){
                this.carryingCapacity+=1;
                System.out.println("你的负重上限+1 ！");
            }
            if(item.getName().equals("生命药剂")){
                this.health=Math.min(health+5,10);     //生命上限为10
                System.out.println("你的生命值+5 ！");
            }
        } else {
            System.out.println("你无法食用 " + item.getName() + ".");
        }
    }

}
