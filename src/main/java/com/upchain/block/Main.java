package main.java.com.upchain.block;

public class Main {
    public static void main(String[] args) {
        // 创建区块链，设置挖矿难度为 4（即哈希值必须以 4 个 0 开头）
        Blockchain blockchain = new Blockchain(4);

        // 添加第一个区块
        Block block1 = new Block("First block", blockchain.getLatestBlock().getHash());
        blockchain.addBlock(block1);

        // 添加第二个区块
        Block block2 = new Block("Second block", blockchain.getLatestBlock().getHash());
        blockchain.addBlock(block2);

        // 打印区块链信息
        blockchain.printChain();

        // 验证区块链是否有效
        System.out.println("Is blockchain valid? " + blockchain.isChainValid());
    }
}
