package main.java.com.upchain.block;

import java.util.ArrayList;
import java.util.List;

public class Blockchain {
    private List<Block> chain;
    private int difficulty; // 挖矿难度

    // 构造函数
    public Blockchain(int difficulty) {
        this.chain = new ArrayList<>();
        this.difficulty = difficulty;
        addGenesisBlock();
    }

    // 添加创世区块
    private void addGenesisBlock() {
        Block genesis = new Block("Genesis Block", "0");
        genesis.mineBlock(difficulty);
        chain.add(genesis);
    }

    // 获取最新区块
    public Block getLatestBlock() {
        return chain.get(chain.size() - 1);
    }

    // 添加新区块到链中
    public void addBlock(Block newBlock) {
        newBlock.setPreviousHash(getLatestBlock().getHash());
        newBlock.mineBlock(difficulty);
        chain.add(newBlock);
    }

    // 验证区块链是否有效
    public boolean isChainValid() {
        for (int i = 1; i < chain.size(); i++) {
            Block currentBlock = chain.get(i);
            Block previousBlock = chain.get(i - 1);

            // 验证当前区块的哈希值是否正确
            if (!currentBlock.getHash().equals(currentBlock.calculateHash())) {
                System.out.println("Current block hash not equal");
                return false;
            }

            // 验证当前区块的前一个哈希值是否等于上一个区块的哈希值
            if (!currentBlock.getPreviousHash().equals(previousBlock.getHash())) {
                System.out.println("Previous hash not equal");
                return false;
            }

            // 验证当前区块是否满足挖矿难度要求
            if (!currentBlock.getHash().substring(0, difficulty).equals(new String(new char[difficulty]).replace('\0', '0'))) {
                System.out.println("Block not mined");
                return false;
            }
        }
        return true;
    }

    // 打印区块链信息
    public void printChain() {
        for (Block block : chain) {
            System.out.println("Block Hash: " + block.getHash());
            System.out.println("Previous Hash: " + block.getPreviousHash());
            System.out.println("Data: " + block.getData());
            System.out.println("Nonce: " + block.getNonce());
            System.out.println("Timestamp: " + block.getTimestamp());
            System.out.println("----------------------------");
        }
    }
}
