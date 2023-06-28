package command;

import domain.Game;
import domain.Player;

/**
 * 返回指令
 */
public class BackCommand extends Command{
    @Override
    public boolean execute(Game game) {
        if(Player.getPlayer()!=null) {
            Player.getPlayer().goBack();
        }
        return false;
    }
}
