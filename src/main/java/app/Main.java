package app;

import JFrame.JFrameGUI;
import util.ExercisesMapper;

public class Main {
   public static void main(String[] args) {
       JFrameGUI jFrameGUI = new JFrameGUI();
       ExercisesMapper em = new ExercisesMapper();
       jFrameGUI.launchGUI();
       System.out.println("skibidi");
       var url = "jdbc:sqlite:Database.db";
       em.exConnectToDB(url);


    }
}
