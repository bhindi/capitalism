package org.capitalism.sim;

import java.util.ArrayList;
import java.util.Iterator;

import org.capitalism.FiscalProfile;
import org.capitalism.Human;
import org.capitalism.IConsumer.SpenderType;
import org.capitalism.IProducer.ProfitType;
import org.capitalism.Money;
import org.junit.Assert;
import org.junit.Test;

public class SimulationTest {

	@Test
	public void test() {

		Human man1 = new Human(new FiscalProfile(ProfitType.HIGH_PROFIT,
				SpenderType.HIGH_SPENDER));
		Human man2 = new Human(new FiscalProfile(ProfitType.NEUTRAL_PROFIT,
				SpenderType.NEUTRAL_SPENDER));
		Human man3 = new Human(new FiscalProfile(ProfitType.LOW_PROFIT,
				SpenderType.LOW_SPENDER));

		ArrayList<Human> humans = new ArrayList<Human>();
		humans.add(man1);
		humans.add(man2);
		humans.add(man3);

		double totalEquity = 0;
		Iterator<Human> itr = humans.iterator();
		while (itr.hasNext()) {
			totalEquity += itr.next().getWorth();
		}
		totalEquity += Money.MoneySupply.getTotalMoney();

		for (int i = 0; i < 10; i++) {
			man1.live(humans);
			man2.live(humans);
			man3.live(humans);
		}

		double newTotalEquity = 0;
		Iterator<Human> itr2 = humans.iterator();
		while (itr2.hasNext()) {
			newTotalEquity += itr2.next().getWorth();
		}
		newTotalEquity += Money.MoneySupply.getTotalMoney();

		System.out.println("newTotalEquity " + Double.toString(newTotalEquity));
		System.out.println("totalEquity " + Double.toString(totalEquity));
		Assert.assertTrue(Double.compare(newTotalEquity, totalEquity) == 0);

	}

	@Test
	public void testSim() {
		Simulation sim = new Simulation();

		Assert.assertNotNull(sim);

	}

}
