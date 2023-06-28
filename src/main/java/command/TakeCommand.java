package command;

import domain.Game;
import domain.Item;
import domain.Player;
import domain.Room;

import java.util.ArrayList;
import java.util.List;

/**
 * 拾取物品
 */
public class TakeCommand extends Command{
    @Override
    public boolean execute(Game game) {
        if (Player.getPlayer() != null) {
            Player player = Player.getPlayer();
            Room currentRoom = player.getCurrentRoom();

            if (!hasSecondWord()) {
                System.out.println("拾取什么物品？");
                return false;
            }

            String itemToTake = getSecondWord();

            List<Item> roomItems = currentRoom.getRoomItems();

            if (itemToTake.equals("all")) {
                if (roomItems.isEmpty()) {
                    System.out.println("房间内没有该物品。");
                    return false;
                }
                ArrayList<Item> itemsToTake = new ArrayList<>(roomItems);
                for (Item roomItem : itemsToTake) {
                    if (player.getCurrentLoad() + roomItem.getWeight() > player.getCarryingCapacity()) {
                        System.out.println(roomItem.getName() + "过重，已超出负载上限。");
                    } else {
                        currentRoom.removeItem(roomItem);  // 从房间中移除物品
                        player.addItem(roomItem);           // 将物品添加到玩家的物品列表中
                        System.out.println("您拾取了物品：" + roomItem.getName());
                    }
                }
                System.out.println("您当前负载：" + player.getCurrentLoad());
            } else {
                Item item = null;
                for (Item roomItem : roomItems) {
                    if (roomItem.getName().equalsIgnoreCase(itemToTake)) {
                        item = roomItem;
                        break;
                    }
                }

                if (item == null) {
                    System.out.println("房间内没有该物品。");
                    return false;
                }

                if (player.getCurrentLoad() + item.getWeight() > player.getCarryingCapacity()) {
                    System.out.println("负载超过上限，无法拾取。");
                    return false;
                }

                // 从房间中移除物品，加入玩家的物品列表
                currentRoom.removeItem(item);
                player.addItem(item);
                System.out.println("您拾取了物品：" + item.getName());
                System.out.println("您当前负重：" + player.getCurrentLoad());
            }
        }

        return false;
    }
}
