package command;

import domain.Game;
import domain.Item;
import domain.Player;

import java.util.List;

/**
 * 食用物品
 */
public class EatCommand extends Command{
    @Override
    public boolean execute(Game game) {
        Player player = Player.getPlayer();
        String secondWord = getSecondWord();

        if (secondWord == null) {
            System.out.println("请问需要食用什么？");
        } else {
            List<Item> playerItems = player.getPlayerItems();
            Item item = null;
            for (Item playerItem : playerItems) {
                if (playerItem.getName().equalsIgnoreCase(secondWord)) {
                    item = playerItem;
                    break;
                }
            }

            if (item != null) {
                player.eatItem(item);
            } else {
                System.out.println("抱歉，您没有 " + secondWord + ".");
            }
        }

        return false;
    }
}
