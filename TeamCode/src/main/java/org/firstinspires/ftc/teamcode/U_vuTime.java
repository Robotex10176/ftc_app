package org.firstinspires.ftc.teamcode;

/**
 * Created by Eric D'Urso on 9/20/2017.
 * this is a 10 second countdown timer
 */

public class U_vuTime {
    public static boolean ranit = false;
    static Thread thread = new Thread ();
    public static void main (String args []) throws InterruptedException{
        for (int c = 600; c >= 0; c--){
            thread.sleep(1000);
            System.out.println(c);
        }
        ranit = true;
    }
}
