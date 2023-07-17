package;

import engine.SessionStorage;

class OrderGenerator {
	public static function generateOrder():OrderDataStructure {
		var topping:Int = Random.int(0, 2);
		var toppings:Toppings = Toppings.NONE;
		switch (topping) {
			case 1:
				toppings = Toppings.PEPPERONI;
			case 2:
				toppings = Toppings.SAUSAGE;
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
