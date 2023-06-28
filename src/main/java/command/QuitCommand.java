package command;

import domain.Game;

/**
 * 退出游戏
 */
public class QuitCommand extends Command {
    /**
     * 执行“退出”命令
     * @param game 游戏对象
     * @return 如果命令执行完毕返回true，否则返回false
     */
    public boolean execute(Game game) {
        if(hasSecondWord()) {
            System.out.println("确定退出吗？");
            return false;
        }
        else {
            return true;
        }
    }
}
