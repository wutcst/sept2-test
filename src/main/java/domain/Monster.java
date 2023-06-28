package domain;

/**
 * 怪物类
 */
public class Monster {
    private String name; // 怪物名称
    private int health;  // 生命值
    private int attack;  // 攻击力
    private int defense; // 防御力

    /**
     * 构造函数，创建怪物实例
     *
     * @param health  怪物生命值
     * @param attack  怪物攻击力
     * @param defense 怪物防御力
     */
    public Monster(String name, int health, int attack, int defense) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
    }

    /**
     * 设置怪物名称
     * @param name 怪物名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取怪物名称
     * @return name 怪物名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置怪物生命值
     * @param health 怪物生命值
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * 获取怪物生命值
     * @return health 怪物生命值
     */
    public int getHealth() {
        return health;
    }

    /**
     * 获取怪物攻击力
     * @return attack 怪物攻击力
     */
    public int getAttack() {
        return attack;
    }

    /**
     * 获取怪物防御力
     * @return defense 怪物防御力
     */
    public int getDefense() {
        return defense;
    }

    public void display() {
        System.out.println(this.name + "当前生命值：" + this.health + " 攻击力：" + this.attack + " 防御力：" + this.defense);
    }

    public void displayHealth() {
        if (this.health < 0) {
            this.health = 0;
        }
        System.out.print(this.name + "当前生命值：" + health);
    }

}
