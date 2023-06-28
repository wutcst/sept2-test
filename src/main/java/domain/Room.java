package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashMap;

/**
 * 房间类
 */
public class Room {
    private String description;//房间信息描述
    private HashMap<String, Room> exits;//出口信息获取
    private List<Item> items;//房间内物品列表
    private HashMap<String, Monster> monsters;//怪物信息
    private boolean isTeleportRooms;//当前房间是否为随机传送房间
    /**
     * 复制房间信息
     */
    public Room(Room other) {
        this.description = other.description;
        this.exits = new HashMap<>(other.exits);
        this.items = new ArrayList<>(other.items);
        this.monsters = new HashMap<>(other.monsters);
        this.isTeleportRooms = other.isTeleportRooms;
    }

    /**
     * 创建一个新房间
     * @param description 房间信息描述
     */
    public Room(String description) {
        this.description = description;
        exits = new HashMap<>();
        items = new ArrayList<>();
        monsters = new HashMap<>();
        isTeleportRooms = false;
    }

    /**
     * 设置所在房间的出口
     * @param direction 出口的方向
     * @param neighbor  相邻房间
     */
    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    /**
     * 获取房间的信息
     * @return 房间信息的描述
     */
    public String getDescription() {
        return "当前所在位置为" + description + ".\n" + getExitString();
    }

    /**
     * 获取房间的出口
     * @return 房间的出口
     */
    private String getExitString() {
        String returnString = "出口:";
        Set<String> keys = exits.keySet();
        StringBuilder sb = new StringBuilder();
        for (String exit : keys) {
            sb.append(" ").append(exit);
        }
        returnString = sb.toString();
        return returnString;
    }

    /**
     * 获取指定方向上的出口
     * @param direction 出口的方向
     * @return 相邻房间的引用，如果没有出口则返回null
     */
    public Room getExit(String direction) {
        return exits.get(direction);
    }

    /**
     * 向房间添加物件
     * @param item 要添加的物件
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * 从房间移除物件
     * @param item 要移除的物件
     */
    public void removeItem(Item item) {
        items.remove(item);
    }


    /**
     * 获取房间的物品列表
     * @return items 返回物品列表
     */
    public List<Item> getRoomItems() {
        List<Item> copy = new ArrayList<>(items);
        return copy;
    }

    /**
     * 房间内添加怪物，并设置怪物位置
     * @param direction 怪物所在位置
     * @param monster   怪物信息
     */
    public void addMonster(String direction, Monster monster) {
        monsters.put(direction, monster);
    }

    /**
     * 房间内移除怪物
     * @param direction 怪物所在位置
     */
    public void removeMonster(String direction) {
        monsters.remove(direction);
    }

    /**
     * 获取房间的怪物列表
     * @return monsters 返回怪物列表
     */
    public HashMap<String, Monster> getMonsters() {
        return new HashMap<>(monsters);
    }

    /**
     * 设置房间是否为随机传送房间
     * @param isTeleportRooms 随机传送标识
     */
    public void setTeleportRooms(boolean isTeleportRooms) {
        this.isTeleportRooms = isTeleportRooms;
        System.out.println("设置成功！");
    }

    /**
     * 获取房间的随机传送标识符
     * @return isTeleportRooms 随机传送标识
     */
    public boolean getTeleportRooms() {
        return isTeleportRooms;
    }
}


