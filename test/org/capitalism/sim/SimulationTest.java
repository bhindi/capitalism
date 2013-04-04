package org.capitalism.sim;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;

import org.capitalism.Human;
import org.capitalism.Money;
import org.junit.Assert;
import org.junit.Test;

public class SimulationTest {

	@Test
	public void test() {

		Human man1 = new Human();
		Human man2 = new Human();
		Human man3 = new Human();

		ArrayList<Human> humans = new ArrayList<Human>();
		humans.add(man1);
		humans.add(man2);
		humans.add(man3);

		BigDecimal totalEquity = new BigDecimal(0);
		Iterator<Human> itr = humans.iterator();
		while (itr.hasNext()) {
			totalEquity = totalEquity.add(new BigDecimal(itr.next().getWorth()));
		}
		totalEquity = totalEquity.add(Money.MoneySupply.getTotalMoney());

		for (int i = 0; i < 10; i++) {
			man1.live(humans);
			man2.live(humans);
			man3.live(humans);
		}

		BigDecimal newTotalEquity = new BigDecimal(0);
		Iterator<Human> itr2 = humans.iterator();
		while (itr2.hasNext()) {
			newTotalEquity = newTotalEquity.add(new BigDecimal(itr2.next().getWorth()));
		}
		newTotalEquity = newTotalEquity.add(Money.MoneySupply.getTotalMoney());

		System.out.println("newTotalEquity " + NumberFormat.getCurrencyInstance().format(newTotalEquity)); 
		System.out.println("totalEquity " + NumberFormat.getCurrencyInstance().format(totalEquity)); 
		Assert.assertEquals(newTotalEquity, totalEquity);

	}
	
	@Test
	public void testSim() {
		Simulation sim = new Simulation();

		Assert.assertNotNull(sim);
		
	}

}
