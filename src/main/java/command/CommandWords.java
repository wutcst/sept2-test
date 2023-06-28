package command;

import java.util.HashMap;
import java.util.Iterator;

/**
 * 指令词汇
 */
public class CommandWords {
    private HashMap<String, Command> commands;
    /**
     * 创建CommandWords对象并初始化命令词汇表
     */
    public CommandWords()
    {
        commands = new HashMap<String, Command>();
        commands.put("go", new GoCommand());
        commands.put("help", new HelpCommand(this));
        commands.put("quit", new QuitCommand());
        commands.put("look", new LookCommand());
        commands.put("back", new BackCommand());
        commands.put("take", new TakeCommand());
        commands.put("drop", new DropCommand());
        commands.put("items", new ItemCommand());
        commands.put("eat", new EatCommand());

    }
    /**
     * 获取指定命令词汇对应的命令对象
     * @param word 命令词汇
     * @return 对应的命令对象，如果没有找到返回null
     */
    public Command get(String word)
    {
        return (Command)commands.get(word);
    }

    /**
     * 显示所有命令词汇
     */
    public void showAll()
    {
        for(Iterator i = commands.keySet().iterator(); i.hasNext(); ) {
            System.out.print(i.next() + "  ");
        }
        System.out.println();
    }
}
