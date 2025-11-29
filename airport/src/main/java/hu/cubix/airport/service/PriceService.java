package hu.cubix.airport.service;

import org.springframework.stereotype.Service;

@Service
public class PriceService {
	
	private DiscountService discountService;	
		
	public PriceService(DiscountService discountService) {	
		this.discountService = discountService;
	}

	public int getFinalPrice(int price) {
		//return 95;
		return (int) (price / 100.0 * (100 - discountService.getDiscountPercent(price)));
	}
	
}
