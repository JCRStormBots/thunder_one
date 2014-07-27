/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj.templates;
//IP Addresses 10.xx.yy.1
//Team 5076: 10.50.76.1
//ping to detect
//cRIO: 10.xx.yy.2
//Programming Computer: 10.xx.yy.9
//Driver station IP: 10.xx.yy.6
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Timer;
public class RobotTemplate extends SimpleRobot {
    public RobotTemplate()
    {
        comp.start(); //Start the compresspr
    }
    Joystick joy = new Joystick(1); //Wireless Xbox controller
    Compressor comp = new Compressor(1,1); //Compressor plugged into Digital IO -1 and Relay-1
    Solenoid sol1 = new Solenoid(1); //Solenoid 1 for the bottom piston
    Solenoid sol2 = new Solenoid(2); //Solenoid 2 for the bottom piston
    Solenoid sol3 = new Solenoid(3); //Solenoid 1 for the top piston
    Solenoid sol4 = new Solenoid(4); //Solenoi 2 for the top piston

    Talon talRight = new Talon(1); //Right side speed controller
    Talon talLeft = new Talon(2); //Left side speed controller
    Talon pickup = new Talon(3); //Pickup speed controller
    public void autonomous() {
        //First 15 seconds of the match
        //Preprogramming to score points
	talRight.set(1);
	talLeft.set(1);
	Timer.delay(3.52);
	talRight.set(0);
	talLeft.set(0);
	pickup.set(-0.5);
	Timer.delay(2.0);
	pickup.set(0); 
    }
    public void operatorControl() {
        while(true){     
            if(joy.getRawButton(3))
                prepPickup(); //Prepare to pickup the ball
            else if(joy.getRawButton(1))
                engage(); //Drop the arms on the ball
            else if(joy.getRawButton(2))
                pickup(); //Pull the ball into the robot
            else if(joy.getRawButton(4))
                pickup.set(-0.5); //Reverse the pickup motor
            else if(joy.getRawButton(7))
                pickup.set(0); //Turn off the pickup motor
            else if(joy.getRawButton(6)){
                sol3.set(false);
                sol4.set(true);
            }
            else if(joy.getRawButton(5)){
                sol3.set(true);
                sol4.set(false);
            }           
            talRight.set((joy.getZ() + joy.getX()*0.4)); //Set the Right speed controller based on the Z and X axes of the Xbox controller
            talLeft.set(-joy.getZ() + joy.getX()*0.4); //Set the Left speed controller based on the Z and X axes of the Xbox controller
         }
    }
    public void prepPickup(){
        sol1.set(true);
        sol2.set(false);
        sol3.set(false);
        sol4.set(true);
        pickup.set(0.5);
    }
    public void engage(){
        sol3.set(true);
        sol4.set(false);
    }
    public void pickup(){
	sol1.set(false);
        sol2.set(true);
        sol3.set(true);
        sol4.set(false);
        Timer.delay(1);
        pickup.set(0);
    }
    public void test() {
    }
}
