package main.java.com.upchain.sha;


import java.util.Scanner;

public class PowRun {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入你想生成指定开头的哈希值: ");
        String startWith = scanner.nextLine();
        String uName = "Vurtne";
        long nonce;
        String hashStr;
        long startTime = System.currentTimeMillis();
        long endTime;
        while (true){
            nonce = System.nanoTime();
            hashStr = SHA256Example.calculateSHA256(uName + nonce);
            //这里判断生成的hash是否满足指定输入开头的哈希值，如果不满足则继续循环
            String truncatedHash =  hashStr.substring(0,startWith.length());
            if(startWith.equals(truncatedHash)){
                endTime = System.currentTimeMillis();
                break;
            }
        }
        System.out.println("生成指定开头的哈希值完成:"+"00000");
        System.out.println("Cuttent nonce:"+nonce);
        System.out.println("Cuttent hash:"+hashStr);
        System.out.println("耗时:"+(endTime-startTime) + "毫秒");
    }

    public static String getHash(String uName, String startWith) {
        long nonce;
        while (startWith != null && !startWith.equals("")) {
            nonce  = System.nanoTime();
            String hashStr = SHA256Example.calculateSHA256(uName + nonce);
            String truncatedHash = hashStr.substring(0, startWith.length());
            if (startWith.equals(truncatedHash)) {
                return hashStr;
            }
        }
        return null;
    }
}
