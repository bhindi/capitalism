package org.capitalism;

import java.util.EnumMap;

import org.capitalism.IConsumer.SpenderType;
import org.capitalism.IProducer.ProfitType;


public class FiscalProfile {
	public final double minProfit;
	public final double minPurchaseOfferPercentage;
	public final double maxPurchaseOfferPercentage;
	public final ProfitType profitType;
	public final SpenderType spenderType;
	
	public static EnumMap<SpenderType, Double> offerBrackets = new EnumMap<SpenderType, Double>(SpenderType.class);
	public static EnumMap<SpenderType, Double> offerRange = new EnumMap<SpenderType, Double>(SpenderType.class);
	public static EnumMap<ProfitType, Double> profitBrackets = new EnumMap<ProfitType, Double>(ProfitType.class);
	
	static {
		offerBrackets.put(SpenderType.HIGH_SPENDER, 1.5);
		offerBrackets.put(SpenderType.NEUTRAL_SPENDER, 1.3);
		offerBrackets.put(SpenderType.LOW_SPENDER, 1.1);
		
		offerRange.put(SpenderType.HIGH_SPENDER, .5);
		offerRange.put(SpenderType.NEUTRAL_SPENDER, .3);
		offerRange.put(SpenderType.LOW_SPENDER, .1);
		
		profitBrackets.put(ProfitType.HIGH_PROFIT, 1.0);
		profitBrackets.put(ProfitType.NEUTRAL_PROFIT, .5);
		profitBrackets.put(ProfitType.LOW_PROFIT, 0.0);
	}
	
	public FiscalProfile(ProfitType profitType, SpenderType spenderType) {		

		this.minProfit = profitBrackets.get(profitType);
		this.minPurchaseOfferPercentage = offerBrackets.get(spenderType);
		this.maxPurchaseOfferPercentage = minPurchaseOfferPercentage + offerRange.get(spenderType);

		this.profitType = profitType;
		this.spenderType = spenderType;
	}
	

}
