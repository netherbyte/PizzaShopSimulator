package;

using Toppings;

class OrderChecker {
	public static function verify(order:OrderDataStructure, creation:PizzaDataStructure):Bool {
		var topping1;
		var topping2;
		switch (order.topping) {
			case PEPPERONI:
				topping1 = PEPPERONI;
			case SAUSAGE:
				topping1 = SAUSAGE;
			case EXTRA_CHEESE:
				topping1 = EXTRA_CHEESE;
			case GREEN_BELL_PEPPER:
				topping1 = GREEN_BELL_PEPPER;
			case NONE:
				topping1 = NONE;
		}
		topping2 = creation.meta.topping;

		if (topping1 == topping2) {
			return true;
		} else {
			return false;
		}
	}
}
