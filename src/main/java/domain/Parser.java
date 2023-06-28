package domain;

import command.Command;
import command.CommandWords;

import java.util.Scanner;

/**
 * 命令解析类
 */
public class Parser {
    private CommandWords commands;  // 命令词汇表
    private Scanner reader;         // source of command input

    /**
     * 创建命令解析器对象
     */
    public Parser() {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    /**
     * 从用户输入中获取命令
     * @return 用户输入的命令对象
     */
    public Command getCommand() {
        String inputLine;   // 存储用户输入的命令
        String word1 = null;
        String word2 = null;

        System.out.print("> ");     // 提示符

        inputLine = reader.nextLine();

        // 将输入拆分为单词
        Scanner tokenizer = new Scanner(inputLine);
        if(tokenizer.hasNext()) {
            word1 = tokenizer.next();      // 获取第一个单词
            if(tokenizer.hasNext()) {
                word2 = tokenizer.next();      // 获取第二个单词
            }
        }

        // 根据命令词汇表创建对应的命令对象
        Command command = commands.get(word1);
        if(command != null) {
            command.setSecondWord(word2);
        }
        return command;
    }

    public void showCommands() {
        commands.showAll();
    }
}

