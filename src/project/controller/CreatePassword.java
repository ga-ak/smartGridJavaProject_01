package project.controller;

import java.util.Random;

public class CreatePassword {

    public String createPW() {
        String password = "";
        Random ran = new Random();
        int passwordSize = ran.nextInt(2)+5;
        int[] pN = {48, 57};
        int[] pB = {65, 90};
        int[] pS = {97, 122};
        int[][] pA = new int[3][];
        pA[0] = pN;
        pA[1] = pB;
        pA[2] = pS;

        for (int i = 0; i < passwordSize; i++) {
            int choosen = ran.nextInt(pA.length);
            int small = pA[choosen][0];
            int big = pA[choosen][1];
            char tempP = (char)(ran.nextInt(big - small + 1) + small);
            //System.out.println((int)tempP+"\t"+tempP);
            password += tempP;
        }

        return password;
    }
}
