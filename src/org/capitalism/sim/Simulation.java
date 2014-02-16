package org.capitalism.sim;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.capitalism.FiscalProfile;
import org.capitalism.Human;
import org.capitalism.IConsumer.SpenderType;
import org.capitalism.IProducer.ProfitType;
import org.capitalism.ITransactor;
import org.capitalism.Money;

public class Simulation {

	final static int NUM_HUMANS = 100;

	public static void main(String[] args) {

		Simulation sim = new Simulation();
		sim.run();

	}

	ArrayList<ITransactor> humans;

	public Simulation() {
		humans = new ArrayList<ITransactor>();

		SpenderType[] spendTypes = SpenderType.values();
		ProfitType[] profitTypes = ProfitType.values();

		int spendTypeIndex = 0;
		int profitTypeIndex = 0;
		final int totalTypes = spendTypes.length * profitTypes.length;

		for (int i = 0; i < NUM_HUMANS; i++) {

			int range = i/(NUM_HUMANS/totalTypes);
			range %= (totalTypes-1);
			profitTypeIndex = range % profitTypes.length;
			spendTypeIndex = range / profitTypes.length;
			humans.add(new Human(new FiscalProfile(
					profitTypes[profitTypeIndex], spendTypes[spendTypeIndex])));
		}
	}

	private void run() {

		BigDecimal totalMoney = Money.MoneySupply.getTotalMoney();
		System.out.println(totalMoney.toString());
		
		while (true) {

			for (int i = 0; i < NUM_HUMANS; i++) {
				((Human)humans.get(i)).live(humans);
			}
		}

	}

}
