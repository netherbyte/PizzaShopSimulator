package;

import lime.system.System;
import haxe.io.Path;
import sys.io.File;
import engine.SessionStorage;
import flixel.FlxG;
import flixel.util.FlxColor;
import flixel.text.FlxText;
import flixel.FlxState;

class LanguageSelectState extends FlxState {
	private static var title:FlxText;

	private static var languages:Array<FlxText> = [];

	private static var en_us:FlxText;
	private static var en_in:FlxText;

	private static var ll:Array<String>;

	public override function create() {
		super.create();

		title = new FlxText(0, 0, 0,
			Text.translatable("menu.languageselection")).setFormat(Reference.FONT, 72, FlxColor.fromInt(0xFFFFFFFF), FlxTextAlign.CENTER);
		title.screenCenter(X);
		add(title);

		ll = File.getContent(Path.join([System.applicationDirectory, "res/translations/_TRANSLATIONS.txt"])).split("\n");

		for (lang in ll) {
			var code = lang.split("=")[0];
			var name = lang.split("=")[1];

			var tl = new FlxText(0, 0, 0, Text.literal(name)).setFormat(Reference.FONT, 36, FlxColor.fromInt(0xFFFFFFFF), FlxTextAlign.CENTER);
			if (languages.length == 0) {
				tl.y = title.y + (title.height * 1.5);
			} else {
				tl.y = languages[languages.length - 1].y + languages[languages.length - 1].height;
			}
			tl.screenCenter(X);
			languages.push(tl);
			add(languages[languages.length - 1]);
		}

		// en_us = new FlxText(0, 0, 0, Text.literal("American English")).setFormat(Reference.FONT, 36, FlxColor.fromInt(0xFFFFFFFF), FlxTextAlign.CENTER);
		// en_us.y = title.y + (title.height * 1.5);
		// en_us.screenCenter(X);
		// add(en_us);

		// en_in = new FlxText(0, 0, 0, Text.literal("Indian English")).setFormat(Reference.FONT, 36, FlxColor.fromInt(0xFFFFFFFF), FlxTextAlign.CENTER);
		// en_in.y = en_us.y + en_us.height;
		// en_in.screenCenter(X);
		// add(en_in);
	}

	public override function update(dt:Float) {
		super.update(dt);

		// if (FlxG.mouse.overlaps(en_us) && FlxG.mouse.justPressed) {
		// 	SessionStorage.language = "en_us";
		// 	Text.loadTranslations();
		// 	SessionStorage.saveDataToJSON();
		// 	FlxG.switchState(new MainActivity());
		// }

		// if (FlxG.mouse.overlaps(en_in) && FlxG.mouse.justPressed) {
		// 	SessionStorage.language = "en_in";
		// 	Text.loadTranslations();
		// 	SessionStorage.saveDataToJSON();
		// 	FlxG.switchState(new MainActivity());
		// }

		for (lang in languages) {
			if (FlxG.mouse.overlaps(lang) && FlxG.mouse.justPressed) {
				var lc = "en_us";
				for (llc in ll) {
					lc = llc.split("=")[0];
				}
				SessionStorage.language = lc;
				Text.loadTranslations();
				SessionStorage.saveDataToJSON();
				FlxG.switchState(new MainActivity());
			}
		}
	}
}
