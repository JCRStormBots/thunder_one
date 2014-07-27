

package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.Solenoid;

public class Piston{
	public Solenoid s1;
	public Solenoid s2;
	
	public Piston(int slot1, int slot2){
		s1 = new Solenoid(slot1);
		s2 = new Solenoid(slot2);
	}
	public void init(){
		s1.set(true);
		s2.set(false);	
	}

}
