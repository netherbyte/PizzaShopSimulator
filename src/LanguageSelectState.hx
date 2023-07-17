package;

import engine.SessionStorage;
import flixel.FlxG;
import flixel.util.FlxColor;
import flixel.text.FlxText;
import flixel.FlxState;

class LanguageSelectState extends FlxState {
	private static var title:FlxText;

	private static var en_us:FlxText;
	private static var en_in:FlxText;

	public override function create() {
		super.create();

		title = new FlxText(0, 0, 0,
			Text.translatable("menu.languageselection")).setFormat(Reference.FONT, 72, FlxColor.fromInt(0xFFFFFFFF), FlxTextAlign.CENTER);
		title.screenCenter(X);
		add(title);

		en_us = new FlxText(0, 0, 0, Text.literal("American English")).setFormat(Reference.FONT, 36, FlxColor.fromInt(0xFFFFFFFF), FlxTextAlign.CENTER);
		en_us.y = title.y + (title.height * 1.5);
		en_us.screenCenter(X);
		add(en_us);

		en_in = new FlxText(0, 0, 0, Text.literal("Indian English")).setFormat(Reference.FONT, 36, FlxColor.fromInt(0xFFFFFFFF), FlxTextAlign.CENTER);
		en_in.y = en_us.y + en_us.height;
		en_in.screenCenter(X);
		add(en_in);
	}

	public override function update(dt:Float) {
		super.update(dt);

		if (FlxG.mouse.overlaps(en_us) && FlxG.mouse.justPressed) {
			SessionStorage.language = "en_us";
			Text.loadTranslations();
			SessionStorage.saveDataToJSON();
			FlxG.switchState(new MainActivity());
		}

		if (FlxG.mouse.overlaps(en_in) && FlxG.mouse.justPressed) {
			SessionStorage.language = "en_in";
			Text.loadTranslations();
			SessionStorage.saveDataToJSON();
			FlxG.switchState(new MainActivity());
		}
	}
}
