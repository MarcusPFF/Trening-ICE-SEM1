package app;

import JFrame.JFrameGUI;

public class Main {
    public static void main(String[] args) {

        WorkoutPage.connectToDatabase();

        JFrameGUI jFrameGUI = new JFrameGUI();
        jFrameGUI.launchGUI();
        System.out.println("skibidi");
    }
}
