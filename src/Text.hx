package;

import haxe.Json;
import sys.io.File;
import lime.system.System;
import haxe.io.Path;

using StringTools;

class Text {
	private static var translationsK = [];
	private static var translationsV = [];

	public static function literal(text:String):String {
		return text;
	}

	public static function translatable(key:String):String {
		for (i in 0...translationsK.length) {
			if (translationsK[i] == key)
				return translationsV[i].substr(0, translationsV[i].length - 1);
		}
		return key;
	}

	public static function loadTranslations() {
		translationsK = [];
		translationsV = [];
		var path = Path.join([
			System.applicationDirectory,
			"res/translations/" + Reference.getLanguage() + ".txt"
		]);
		var content = File.getContent(path);
		var lines = content.split("\n");
		for (line in lines) {
			translationsK.push(line.split("=")[0]);
			translationsV.push(line.split("=")[1]);
		}
	}
}
