package;

import lime.system.System;
import engine.Resources;
import engine.SessionStorage;
import flixel.FlxG;
import flixel.FlxSprite;
import flixel.FlxState;
import flixel.text.FlxText;
import flixel.util.FlxColor;
import openfl.Lib;
import openfl.display.FPS;

using Toppings;
using StringTools;

class MainActivity extends FlxState {
	static var background:FlxSprite;

	static var titleText:FlxText;

	// order hud
	static var orderNotifyTitle:FlxText;
	static var orderDescLine1:FlxText;
	static var orderDescLine2:FlxText;

	// ingridients hud
	static var doughIcon:FlxSprite;
	static var sauceIcon:FlxSprite;
	static var cheeseIcon:FlxSprite;
	static var pepperoniIcon:FlxSprite;
	static var sausageIcon:FlxSprite;
	static var excheeseIcon:FlxSprite;
	static var greenPepperIcon:FlxSprite;
	static var tutorialArrow:FlxText;

	static var draggable:FlxSprite; // currently with a single cursor you can only drag one at a time so there is no point in having a draggable sprite for each ingredient

	// work area
	static var dragHereHint:FlxText;
	static var currentPizza:PizzaDataStructure = {
		base: new FlxSprite(),
		topping: null,
		meta: {
			topping: null,
			composition: []
		}
	};
	static var freezeBackupPizza:PizzaDataStructure;
	static var oven:FlxSprite;
	static var ovenBack:FlxSprite;
	static var ovenRack:FlxSprite;

	static var cookTime:Float = 0;
	static var maxCookTime:Float = 15;
	static var pizzaInOven:Bool = false;

	static var finishTutorialButton:FlxText;

	static var cookIndicator:FlxText;

	static var order:OrderDataStructure;

	static var orderFeedback:FlxText;
	static var acceptOrderFeedbackButton:FlxText;

	static var lastIngredientAdded:PizzaIngredients = null;

	static var freezeWorkspace:Bool = false;

	// stat hud
	static var salesIndicator:FlxText;
	static var revenueIndicator:FlxText;

	public override function create() {
		super.create();

		background = new FlxSprite();
		background.makeGraphic(FlxG.width, FlxG.height, FlxColor.WHITE);
		background.screenCenter();
		add(background);

		lastIngredientAdded = null;
		freezeWorkspace = false;

		titleText = new FlxText(0, 0, 0, Text.translatable("shop.name.loading")).setFormat(Reference.FONT, 16, FlxColor.BLACK, CENTER);
		try {
			titleText.text = SessionStorage.shopName + Text.translatable("kitchen.suffix");
		} catch (error) {
			titleText.text = Text.translatable("shop.name.error");
		}
		add(titleText);

		// order hud will be invisible until there is an order
		// for testing, we will create an order at the beginning of the game
		orderNotifyTitle = new FlxText(0, 0, 0, Text.translatable("order.new")).setFormat(Reference.FONT, 32, FlxColor.BLACK, CENTER);
		orderNotifyTitle.x = FlxG.width - orderNotifyTitle.width;
		orderNotifyTitle.visible = false;
		add(orderNotifyTitle);
		orderDescLine1 = new FlxText(0, 0, 0, Text.translatable("order.summary")).setFormat(Reference.FONT, 24, FlxColor.BLACK, CENTER);
		orderDescLine1.x = FlxG.width - orderDescLine1.width;
		orderDescLine1.y = orderNotifyTitle.height;
		orderDescLine1.visible = false;
		add(orderDescLine1);
		orderDescLine2 = new FlxText(0, 0, 0, Text.translatable("order.summary")).setFormat(Reference.FONT, 24, FlxColor.BLACK, CENTER);
		orderDescLine2.x = FlxG.width - orderDescLine2.width;
		orderDescLine2.y = orderDescLine1.y + orderDescLine1.height;
		orderDescLine2.visible = false;
		add(orderDescLine2);

		if (SessionStorage.tutorialCompleted) {
			order = OrderGenerator.generateOrder();
		} else {
			order = {
				customerName: Names.getNames()[Random.int(0, Names.getNames().length - 1)],
				topping: Toppings.PEPPERONI,
				tip: 0
			}
		}

		orderDescLine1.text = Text.translatable("order.from") + " " + order.customerName;
		switch (order.topping) {
			case NONE:
				orderDescLine2.text = Text.translatable("pizza.cheese");
			case PEPPERONI:
				orderDescLine2.text = Text.translatable("pizza.pepperoni");
			case SAUSAGE:
				orderDescLine2.text = Text.translatable("pizza.sausage");
			case EXTRA_CHEESE:
				orderDescLine2.text = Text.translatable("pizza.excheese");
			case GREEN_BELL_PEPPER:
				orderDescLine2.text = Text.translatable("pizza.greenpepper");
		}
		orderNotifyTitle.x = FlxG.width - orderNotifyTitle.width;
		orderDescLine1.x = FlxG.width - orderDescLine1.width;
		orderDescLine2.x = FlxG.width - orderDescLine2.width;
		orderNotifyTitle.visible = true;
		orderDescLine1.visible = true;
		orderDescLine2.visible = true;

		// ingridients hud
		var items = 7;
		var itemHeight = 64;
		var hHeight = items * itemHeight;
		var yOffset = (FlxG.height - hHeight) / 2;
		doughIcon = new FlxSprite(0, 0, "res/images/UncookedDough.png");
		doughIcon.y = yOffset + (itemHeight * 0);
		add(doughIcon);
		sauceIcon = new FlxSprite(0, 0, "res/images/Sauce.png");
		sauceIcon.y = yOffset + (itemHeight * 1);
		add(sauceIcon);
		cheeseIcon = new FlxSprite(0, 0, "res/images/Cheese.png");
		cheeseIcon.y = yOffset + (itemHeight * 2);
		add(cheeseIcon);
		pepperoniIcon = new FlxSprite(0, 0, "res/images/UncookedPepperoni.png");
		pepperoniIcon.y = yOffset + (itemHeight * 3);
		add(pepperoniIcon);
		sausageIcon = new FlxSprite(0, 0, "res/images/sausage.png");
		sausageIcon.y = yOffset + (itemHeight * 4);
		add(sausageIcon);
		excheeseIcon = new FlxSprite(0, 0, "res/images/excheese.png");
		excheeseIcon.y = yOffset + (itemHeight * 5);
		add(excheeseIcon);
		greenPepperIcon = new FlxSprite(0, 0, "res/images/green_pepper.png");
		greenPepperIcon.y = yOffset + (itemHeight * 6);
		add(greenPepperIcon);
		tutorialArrow = new FlxText(0, 0, 0, "<-").setFormat(Reference.FONT, 24, FlxColor.BLACK, CENTER);
		tutorialArrow.x = sauceIcon.width;
		tutorialArrow.y = doughIcon.y;

		if (SessionStorage.tutorialCompleted) {
			tutorialArrow.visible = false;
		}
		add(tutorialArrow);

		// work area
		dragHereHint = new FlxText(0, 0, 0, Text.translatable("tutorial.clickdough")).setFormat(Reference.FONT, 32, FlxColor.BLACK, CENTER);
		dragHereHint.screenCenter(XY);
		dragHereHint.y -= dragHereHint.height * 4;
		if (SessionStorage.tutorialCompleted) {
			dragHereHint.visible = false;
		}
		add(dragHereHint);

		currentPizza.base = new FlxSprite();
		currentPizza.base.screenCenter(XY);
		currentPizza.base.visible = false;
		add(currentPizza.base);
		oven = new FlxSprite(0, 0, Resources.ovenresized_0__png);
		oven.screenCenter(XY);
		oven.x += oven.width * 2;
		add(oven);
		ovenBack = new FlxSprite(0, 0, Resources.ovenresized_1__png);
		ovenBack.x = oven.x;
		ovenBack.y = oven.y;
		add(ovenBack);
		ovenRack = new FlxSprite(0, 0, Resources.panewithoutoverlayresized__png);
		ovenRack.x = oven.x;
		ovenRack.y = oven.y;
		add(ovenRack);

		finishTutorialButton = new FlxText(0, 0, 0, Text.translatable("tutorial.finished")).setFormat(Reference.FONT, 32, FlxColor.BLACK, CENTER);
		finishTutorialButton.screenCenter(XY);
		finishTutorialButton.y += FlxG.height / 4;
		finishTutorialButton.visible = false;
		add(finishTutorialButton);

		cookIndicator = new FlxText(0, 0, 0, Text.translatable("generic.error")).setFormat(Reference.FONT, 32, FlxColor.BLACK, CENTER);
		cookIndicator.x = oven.x + (oven.width / 2 - cookIndicator.width / 2);
		cookIndicator.y = oven.y - cookIndicator.height * 2;
		add(cookIndicator);

		currentPizza.topping = new FlxSprite(0, 0);
		currentPizza.topping.visible = false;
		add(currentPizza.topping);

		orderFeedback = new FlxText(0, 0, 0,
			Text.translatable("order.incorrect").replace("%c", order.customerName)).setFormat(Reference.FONT, 32, FlxColor.BLACK, CENTER);
		orderFeedback.screenCenter(XY);
		orderFeedback.y = FlxG.height - (orderFeedback.height * 3);
		orderFeedback.visible = false;
		add(orderFeedback);
		acceptOrderFeedbackButton = new FlxText(0, 0, 0, Text.translatable("order.next")).setFormat(Reference.FONT, 48, FlxColor.BLACK, CENTER);
		acceptOrderFeedbackButton.screenCenter(XY);
		acceptOrderFeedbackButton.y = FlxG.height - acceptOrderFeedbackButton.height;
		acceptOrderFeedbackButton.visible = false;
		add(acceptOrderFeedbackButton);

		// stat hud
		salesIndicator = new FlxText(0, 0, 0, "0").setFormat(Reference.FONT, 32, FlxColor.BLACK, CENTER);
		salesIndicator.screenCenter(X);
		salesIndicator.x -= salesIndicator.width;
		add(salesIndicator);
		revenueIndicator = new FlxText(0, 0, 0, "0").setFormat(Reference.FONT, 32, FlxColor.BLACK, CENTER);
		revenueIndicator.screenCenter(X);
		revenueIndicator.x += revenueIndicator.width;
		add(revenueIndicator);

		// Main.menuMusic.pause();
		// Main.kitchenMusic.play();
		Main.playMainMusic();
	}

	public override function update(dt:Float) {
		super.update(dt);

		titleText.text = SessionStorage.shopName + Text.translatable("kitchen.suffix");
		salesIndicator.text = SessionStorage.totalSales + " " + Text.translatable("stats.sales");
		revenueIndicator.text = SessionStorage.totalRevenue + " " + Text.translatable("stats.coins");
		salesIndicator.screenCenter(X);
		salesIndicator.x -= salesIndicator.width;
		revenueIndicator.screenCenter(X);
		revenueIndicator.x += revenueIndicator.width;

		if (FlxG.mouse.overlaps(titleText) && FlxG.mouse.justPressed) {
			FlxG.switchState(new NameYourShopState(false));
		}

		if (FlxG.mouse.overlaps(doughIcon) && FlxG.mouse.justPressed) {
			if (!pizzaInOven && !freezeWorkspace && lastIngredientAdded == null) {
				currentPizza.base.loadGraphic(Resources.UncookedDough__png);
				currentPizza.base.visible = true;
				dragHereHint.text = Text.translatable("tutorial.clicksauce");
				dragHereHint.screenCenter(X);
				tutorialArrow.y = sauceIcon.y;
				currentPizza.meta.composition.push(DOUGH);
				lastIngredientAdded = DOUGH;
				FlxG.sound.play(Resources.Plop__wav, 0.5 * SessionStorage.volume);
			}
		}

		if (FlxG.mouse.overlaps(sauceIcon) && FlxG.mouse.justPressed) {
			if (!pizzaInOven && !freezeWorkspace && lastIngredientAdded == DOUGH) {
				currentPizza.base.loadGraphic(Resources.UncookedDoughWithSauce__png);
				dragHereHint.text = Text.translatable("tutorial.clickcheese");
				dragHereHint.screenCenter(X);
				tutorialArrow.y = cheeseIcon.y;
				currentPizza.meta.composition.push(SAUCE);
				lastIngredientAdded = SAUCE;
				FlxG.sound.play(Resources.Plop__wav, 0.5 * SessionStorage.volume);
			}
		}

		if (FlxG.mouse.overlaps(cheeseIcon) && FlxG.mouse.justPressed) {
			if (!pizzaInOven && !freezeWorkspace && lastIngredientAdded == SAUCE) {
				currentPizza.base.loadGraphic(Resources.UncookedDoughWithSauceAndCheese__png);
				dragHereHint.text = Text.translatable("tutorial.clickpepperoni");
				dragHereHint.screenCenter(X);
				tutorialArrow.y = pepperoniIcon.y;
				currentPizza.meta.topping = NONE;
				currentPizza.meta.composition.push(CHEESE);
				lastIngredientAdded = CHEESE;
				FlxG.sound.play(Resources.Plop__wav, 0.5 * SessionStorage.volume);
			}
		}

		if (FlxG.mouse.overlaps(pepperoniIcon) && FlxG.mouse.justPressed) {
			if (!pizzaInOven && !freezeWorkspace && lastIngredientAdded == CHEESE) {
				currentPizza.topping.loadGraphic("res/images/UncookedPepperoni.png");
				currentPizza.topping.screenCenter(XY);
				currentPizza.topping.x += 48;
				currentPizza.topping.y += 32;
				currentPizza.topping.visible = true;
				tutorialArrow.visible = false;
				dragHereHint.text = Text.translatable("tutorial.movepizza");
				dragHereHint.screenCenter(X);
				currentPizza.meta.topping = PEPPERONI;
				FlxG.sound.play(Resources.Plop__wav, 0.5 * SessionStorage.volume);
			}
		}

		if (FlxG.mouse.overlaps(sausageIcon) && FlxG.mouse.justPressed) {
			if (!pizzaInOven && !freezeWorkspace && lastIngredientAdded == CHEESE) {
				currentPizza.topping.loadGraphic("res/images/sausage.png");
				currentPizza.topping.screenCenter(XY);
				currentPizza.topping.x += 48;
				currentPizza.topping.y += 32;
				currentPizza.topping.visible = true;
				tutorialArrow.visible = false;
				dragHereHint.text = Text.translatable("tutorial.movepizza");
				dragHereHint.screenCenter(X);
				currentPizza.meta.topping = SAUSAGE;
				FlxG.sound.play(Resources.Plop__wav, 0.5 * SessionStorage.volume);
			}
		}

		if (FlxG.mouse.overlaps(excheeseIcon) && FlxG.mouse.justPressed) {
			if (!pizzaInOven && !freezeWorkspace && lastIngredientAdded == CHEESE) {
				currentPizza.topping.loadGraphic("res/images/excheese.png");
				currentPizza.topping.screenCenter(XY);
				currentPizza.topping.x += 48;
				currentPizza.topping.y += 32;
				currentPizza.topping.visible = true;
				tutorialArrow.visible = false;
				dragHereHint.text = Text.translatable("tutorial.movepizza");
				dragHereHint.screenCenter(X);
				currentPizza.meta.topping = EXTRA_CHEESE;
				FlxG.sound.play(Resources.Plop__wav, 0.5 * SessionStorage.volume);
			}
		}

		if (FlxG.mouse.overlaps(greenPepperIcon) && FlxG.mouse.justPressed) {
			if (!pizzaInOven && !freezeWorkspace && lastIngredientAdded == CHEESE) {
				currentPizza.topping.loadGraphic("res/images/green_pepper.png");
				currentPizza.topping.screenCenter(XY);
				currentPizza.topping.x += 48;
				currentPizza.topping.y += 32;
				currentPizza.topping.visible = true;
				tutorialArrow.visible = false;
				dragHereHint.text = Text.translatable("tutorial.movepizza");
				dragHereHint.screenCenter(X);
				currentPizza.meta.topping = GREEN_BELL_PEPPER;
				FlxG.sound.play(Resources.Plop__wav, 0.5 * SessionStorage.volume);
			}
		}

		if (FlxG.mouse.overlaps(currentPizza.base) && FlxG.mouse.justPressed) {
			if (currentPizza.meta.composition.contains(DOUGH)
				&& currentPizza.meta.composition.contains(SAUCE)
				&& currentPizza.meta.composition.contains(CHEESE)
				&& !pizzaInOven
				&& !freezeWorkspace) {
				// currentPizza.base.visible = false;
				// currentPizza.topping.visible = false;
				dragHereHint.text = Text.translatable("tutorial.ovenwait");
				dragHereHint.screenCenter(X);
				ovenRack.loadGraphic(Resources.ovenresized_5__png);
				currentPizza.base.x = ovenRack.x + ovenRack.width / 4;
				currentPizza.base.y = ovenRack.y + ovenRack.height / 1.5;
				currentPizza.topping.x = ovenRack.x + ovenRack.width / 4;
				currentPizza.topping.y = ovenRack.y + ovenRack.height / 1.5;
				currentPizza.topping.visible = false;
				pizzaInOven = true;
				currentPizza.base.visible = false;
				currentPizza.topping.visible = false;
				freezeWorkspace = true;
				FlxG.sound.play(Resources.Plop__wav, 0.5 * SessionStorage.volume);
			}
		}

		if (pizzaInOven) {
			cookTime += dt;
			dragHereHint.text = Text.translatable("tutorial.cooking").replace("%t", Math.round(maxCookTime - cookTime) + "");
			dragHereHint.screenCenter(X);
			cookIndicator.visible = true;
			cookIndicator.text = "" + Math.round(maxCookTime - cookTime);
		} else {
			cookIndicator.visible = false;
		}

		if (cookTime >= maxCookTime) {
			pizzaInOven = false;
			ovenRack.loadGraphic(Resources.ovenresized_1__png);
			dragHereHint.text = Text.translatable("tutorial.done");
			dragHereHint.screenCenter(X);
			finishTutorialButton.visible = true;
			cookTime = 0;
			if (SessionStorage.tutorialCompleted) {
				finishTutorialButton.text = Text.translatable("order.deliver");
			}
			// currentPizza.base.visible = true;
			// currentPizza.topping.visible = true;
		}

		if (FlxG.mouse.overlaps(finishTutorialButton) && FlxG.mouse.justPressed) {
			FlxG.sound.play(Resources.Plop__wav, 0.5 * SessionStorage.volume);
			finishTutorialButton.visible = false;
			if (!SessionStorage.tutorialCompleted) {
				SessionStorage.tutorialCompleted = true;
				SessionStorage.saveDataToJSON();
			}
			if (OrderChecker.verify(order, currentPizza)) {
				SessionStorage.totalSales++;
				SessionStorage.totalRevenue += SessionStorage.cheesePizzaPrice;
				SessionStorage.totalRevenue += order.tip;
				if (order.topping == PEPPERONI || order.topping == SAUSAGE) {
					SessionStorage.totalRevenue += SessionStorage.pricePerTopping;
				}
				SessionStorage.saveDataToJSON();
				FlxG.switchState(new MainActivity());
			} else {
				orderFeedback.visible = true;
				acceptOrderFeedbackButton.visible = true;
			}
		}

		if (FlxG.mouse.overlaps(acceptOrderFeedbackButton) && FlxG.mouse.justPressed) {
			FlxG.sound.play(Resources.Plop__wav, 0.5 * SessionStorage.volume);
			FlxG.switchState(new MainActivity());
		}
	}
}
