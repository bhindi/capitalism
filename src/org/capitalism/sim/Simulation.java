package org.capitalism.sim;

import java.util.ArrayList;

import org.capitalism.Human;

public class Simulation {

	final static int NUM_HUMANS = 100;
	
	public static void main(String[] args) {

		Simulation sim = new Simulation();
		sim.run();
		

		
	}
	
	ArrayList<Human> humans;
	
	public Simulation() {
		humans = new ArrayList<Human>();
		
		for(int i=0; i<NUM_HUMANS; i++) {
			humans.add(new Human());
		}		
	}

	private void run() {

		while(true) {
			
			for(int i=0; i<NUM_HUMANS; i++) {
				humans.get(i).live(humans);
			}			
		}		
		
	}

}
