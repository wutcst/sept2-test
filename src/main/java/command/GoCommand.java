package command;

import domain.Game;
import domain.Monster;
import domain.Player;
import domain.Room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * 前进指令
 */
public class GoCommand extends Command {
    /**
     * 执行“前进”命令。
     * @param game 游戏对象
     * @return 如果命令执行完毕返回true，否则返回false
     */
    @Override
    public boolean execute(Game game)
    {
        if (!hasSecondWord()) {
            System.out.println("请问前往哪个方向?");
        }

        String direction = getSecondWord();
        Room currentRoom = Player.getPlayer().getCurrentRoom();

        Room nextRoom = Player.getPlayer().getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("抱歉，这个方向没有房间!");
        }
        else {
            if (Player.getPlayer() != null)
            {
                HashMap<String, Monster> monsters = currentRoom.getMonsters();
                if (monsters.get(direction) != null){
                    boolean flag = Player.getPlayer().fight(monsters.get(direction));
                    if (!flag){
                        game.setFail();
                        System.out.println("您被怪物击败，濒临死亡！");
                        return true;
                    }
                    currentRoom.removeMonster(direction);
                    System.out.println("成功击败怪物，获得了胜利！");
                    System.out.println();
                }
                if (nextRoom.getDescription().equals("在一个有传送门的房间！")){
                    game.setWin();
                    System.out.println("您找到了传送门，离开了当前区域");
                    return true;
                }
                if (nextRoom.getTeleportRooms()) {
                    System.out.println("您进入了一个随机传送房间!");

                    // 随机选择目标房间
                    ArrayList<Room> teleportRooms = game.getRooms();
                    Random random = new Random();
                    int randomIndex = getRandomIndex(random, teleportRooms.size());
                    nextRoom = teleportRooms.get(randomIndex);
                }
                Player.getPlayer().enterRoom(nextRoom);
                System.out.println(nextRoom.getDescription());
            }
            else
            {
                System.out.println("玩家未初始化！");
            }
        }

        return false;
    }


    private int getRandomIndex(Random random, int size) {
        return random.nextInt(size);
    }

}
