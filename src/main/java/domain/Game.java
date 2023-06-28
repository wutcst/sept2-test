/**
 * 该类是“World-of-Zuul”应用程序的主类。
 * 《World of Zuul》是一款简单的文本冒险游戏。用户可以在一些房间组成的迷宫中探险。
 * 你们可以通过扩展该游戏的功能使它更有趣!.
 *
 * 如果想开始执行这个游戏，用户需要创建Game类的一个实例并调用“play”方法。
 *
 * Game类的实例将创建并初始化所有其他类:它创建所有房间，并将它们连接成迷宫；它创建解析器
 * 接收用户输入，并将用户输入转换成命令后开始运行游戏。
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 1.0
 */
package domain;

import command.Command;

import java.util.ArrayList;

/**
 * 游戏类
 */
public class Game {
    private Parser parser;//命令解析器
    private Player link; //游戏玩家
    private boolean isWin;//游戏胜利
    private boolean isFail;//游戏失败
    private ArrayList<Room> rooms; // 房间列表,主要用于随机传送功能
    /**
     * 创建游戏对象并初始化房间和解析器
     */
    public Game()
    {
        rooms = new ArrayList<>();
        createGame();
        parser = new Parser();
        isWin=false;
        isFail=false;
    }

    /**
     * 创建游戏中的房间
     */
    private void createGame() {
        Room outside, theater, pub, lab, office,exit,teleport;

        // 创建怪物
        Monster monsterCommonA=new Monster("普通怪物",6,3,0);
        Monster monsterCommonB=new Monster("普通怪物",6,3,0);
        Monster monsterBOOS=new Monster("精英怪物",20,5,0);

        // 创建物品
        Item Sword=new Item("武器","一般的文字冒险游戏可用不上这个...",3,false);
        Item armor=new Item("护盾","你无坚不摧啦！",4,false);
        Item apple=new Item("苹果","这苹果看上去味道不错。",1,true);
        Item magicCookie=new Item("魔法饼干","这块饼干似乎能改善你的体质。",1,true);

        // 创建房间对象
        outside = new Room("大学正门外");
        theater = new Room("教学楼");
        pub = new Room("商场");
        lab = new Room("餐厅");
        office = new Room("游乐园");
        exit=new Room("一个有传送门的房间！");
        teleport=new Room("一个随机传送房间");

        teleport.setTeleportRooms(true);

        rooms.add(outside);
        rooms.add(theater);
        rooms.add(pub);
        rooms.add(lab);
        rooms.add(office);

        // 初始化房间的出口
        outside.setExit("east", theater);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theater.setExit("west", outside);

        pub.setExit("east", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);
        lab.setExit("west", teleport);

        office.setExit("west", lab);
        office.setExit("east",exit);


        teleport.setExit("east",lab);

        //初始化房间的物品
        //outside.addItem(Sword);
        //outside.addItem(key);
        outside.addItem(apple);
        outside.addItem(magicCookie);
        //outside.addItem(armor);

        theater.addItem(Sword);
        theater.addItem(magicCookie);
        //theater.addItem(key);

        pub.addItem(armor);
        pub.addItem(magicCookie);

        lab.addItem(apple);

        office.addItem(apple);

        //初始化怪物位置
        outside.addMonster("west",monsterCommonA);
        lab.addMonster("east",monsterCommonB);
        office.addMonster("east",monsterBOOS);

        // 创建玩家
        link=Player.getPlayer();

        //设置初始房间
        link.setCurrentRoom(outside);
    }
    /**
     * 游戏的主循环，不断读取用户输入并执行命令
     */
    public void play() {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
        // 游戏循环
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            if(command == null) {
                System.out.println("当前指令无效");
            } else {
                finished = command.execute(this);
            }
            if(isWin){
                System.out.println("游戏胜利！");
            }
            if(isFail){
                System.out.println("游戏失败！");
            }
        }

        System.out.println("感谢游玩，期待下次见面！");
    }
    /**
     * 打印欢迎信息
     */
    private void printWelcome() {
        System.out.println();
        System.out.println("欢迎来到 World of Zuul!");
        System.out.println("如果需要帮助，请键入“help”。");
        System.out.println();
        System.out.println(Player.getPlayer().getCurrentRoom().getDescription());
    }

    /**
     * 获取当前游戏胜利标识
     * @return isWin 游戏胜利标识
     */
    public boolean getIsWin(){
        return this.isWin;
    }

    /**
     * 获取当前游戏失败标识
     * @return isFail 游戏失败标识
     */
    public boolean getIsFail(){
        return this.isFail;
    }

    /**
     * 设置游戏胜利
     */
    public void setWin(){
        this.isWin = true;
    }

    /**
     * 设置游戏失败
     */

    public void setFail(){
        this.isFail = true;
    }

    /**
     * 获取房间列表
     * @return rooms
     */
    public ArrayList<Room> getRooms(){
        return new ArrayList<>(rooms);
    }

    public boolean getWin() {
        return isWin;
    }

    public boolean getFail() {
        return isFail;
    }

}