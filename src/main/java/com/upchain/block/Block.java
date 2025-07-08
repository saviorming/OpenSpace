package main.java.com.upchain.block;

import java.util.Date;

public class Block {
    private String hash;
    private String previousHash;
    private String data;
    private long timestamp;
    private int nonce;

    // 构造函数
    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timestamp = new Date().getTime();
        this.nonce = 0;
        this.hash = calculateHash();
    }

    // 计算哈希值
    public String calculateHash() {
        return StringUtil.applySha256(
                previousHash +
                Long.toString(timestamp) +
                Integer.toString(nonce) +
                data
        );
    }

    // 工作量证明（Proof of Work）
    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0'); // 创建目标字符串，如 "0000"
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block mined: " + hash);
    }

    // 获取哈希值
    public String getHash() {
        return hash;
    }

    // 获取前一个区块的哈希值
    public String getPreviousHash() {
        return previousHash;
    }

    // 获取数据
    public String getData() {
        return data;
    }

    // 获取nonce

    public int getNonce() {
        return nonce;
    }
    //获取时间戳

    public long getTimestamp() {
        return timestamp;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }
}
