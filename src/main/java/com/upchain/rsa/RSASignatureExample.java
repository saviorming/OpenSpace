package main.java.com.upchain.rsa;

import main.java.com.upchain.sha.PowRun;

import java.security.*;
import java.util.Base64;

public class RSASignatureExample {
    private static final Base64.Encoder encoder = Base64.getEncoder();
    private static final Base64.Decoder decoder = Base64.getDecoder();


    public static void main(String[] args) throws Exception {
        // 1. 生成 RSA 密钥对
        KeyPair keyPair = generateRSAKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        System.out.println("公钥: " + encoder.encodeToString(publicKey.getEncoded()));
        System.out.println("私钥: " + encoder.encodeToString(privateKey.getEncoded()));

        // 2. 原始数据
        String originalData = PowRun.getHash("vurtne","0000");
        System.out.println("原始数据: " + originalData);

        // 3. 使用私钥签名
        String signatureStr = sign(originalData, privateKey);
        System.out.println("签名结果: " + signatureStr);

        // 4. 使用公钥验证签名
        boolean isVerified = verify(originalData, signatureStr, publicKey);
        System.out.println("签名是否有效: " + isVerified);
    }

    /**
     * 生成 RSA 密钥对（默认 2048 位）
     */
    public static KeyPair generateRSAKeyPair() throws Exception {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048); // 可选 1024, 2048, 3072 等
        return kpg.generateKeyPair();
    }

    /**
     * 使用私钥对数据签名
     */
    public static String sign(String plainText, PrivateKey privateKey) throws Exception {
        Signature privateSignature = Signature.getInstance("SHA256withRSA");
        privateSignature.initSign(privateKey);
        privateSignature.update(plainText.getBytes());
        byte[] signature = privateSignature.sign();
        return encoder.encodeToString(signature);
    }

    /**
     * 使用公钥验证签名
     */
    public static boolean verify(String plainText, String signatureStr, PublicKey publicKey) throws Exception {
        Signature publicSignature = Signature.getInstance("SHA256withRSA");
        publicSignature.initVerify(publicKey);
        publicSignature.update(plainText.getBytes());
        byte[] signatureBytes = decoder.decode(signatureStr);
        return publicSignature.verify(signatureBytes);
    }

}
