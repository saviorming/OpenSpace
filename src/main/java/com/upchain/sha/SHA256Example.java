package main.java.com.upchain.sha;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256Example {

    // 定义一个方法用于计算 SHA-256 哈希值
    public static String calculateSHA256(String input) {
        try {
            // 创建 MessageDigest 实例，指定 SHA-256 算法
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // 将输入字符串转换为字节数组并计算哈希值
            byte[] hashBytes = md.digest(input.getBytes());

            // 将字节数组转换为十六进制字符串
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0'); // 补零
                }
                hexString.append(hex);
            }

            return hexString.toString(); // 返回 SHA-256 哈希值
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found", e);
        }

    }
}