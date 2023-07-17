package;

import engine.SessionStorage;

class Reference {
	public static final FONT = "res/fonts/good126.ttf";

	public static function getLanguage():String {
		return SessionStorage.language;
	}
}
