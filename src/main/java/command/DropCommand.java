package command;

import domain.Game;
import domain.Item;
import domain.Player;
import domain.Room;

import java.util.ArrayList;
import java.util.List;

/**
 * 丢弃物品
 */
public class DropCommand extends Command{
    @Override
    public boolean execute(Game game) {
        if (Player.getPlayer() != null) {
            Player player = Player.getPlayer();
            Room currentRoom = player.getCurrentRoom();

            if (!hasSecondWord()) {
                System.out.println("需要丢弃什么物品？");
                return false;
            }

            String itemToDrop = getSecondWord();

            List<Item> playerItems = player.getItems();

            if(itemToDrop.equals("all")){
                if (playerItems.isEmpty()) {
                    System.out.println("您身上没有任何物品。");
                    return false;
                }
                ArrayList<Item> itemsToDrop = new ArrayList<>(playerItems);
                for (Item playerItem : itemsToDrop) {
                    currentRoom.addItem(playerItem);  // 将物品添加到当前房间的物品列表中
                    player.removeItem(playerItem);    // 从玩家的物品列表中移除物品
                    System.out.println("您丢弃了物品：" + playerItem.getName());
                }
                System.out.println("您当前负重：" + player.getCurrentLoad());
            } else {
                Item item = null;
                for (Item playerItem : playerItems) {
                    if (playerItem.getName().equalsIgnoreCase(itemToDrop)) {
                        item = playerItem;
                        break;
                    }
                }

                if (item == null) {
                    System.out.println("您没有该物品。");
                    return false;
                }

                // 从玩家的物品列表中移除物品，加入当前房间的物品列表
                player.removeItem(item);
                currentRoom.addItem(item);
                System.out.println("您丢弃了物品：" + item.getName());
                System.out.println("您当前负重：" + player.getCurrentLoad());
            }

        }
        return false;
    }
}
