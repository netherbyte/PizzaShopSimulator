package;

import engine.SessionStorage;

class OrderGenerator {
	public static function generateOrder():OrderDataStructure {
		var topping:Int = Random.int(0, 6);
		var toppings:Toppings = Toppings.NONE;
		switch (topping) {
			case 1:
				toppings = Toppings.PEPPERONI;
			case 2:
				toppings = Toppings.SAUSAGE;
			case 3, 4:
				toppings = Toppings.EXTRA_CHEESE;
			case 5, 6:
				toppings = Toppings.GREEN_BELL_PEPPER;
		}
		var name:String = Names.getNames()[Random.int(0, Names.getNames().length - 1)];
		var tip = Random.int(0, Math.ceil(0.1 * (SessionStorage.cheesePizzaPrice + SessionStorage.pricePerTopping)));
		return {
			customerName: name,
			topping: toppings,
			tip: tip
		};
	}
}
