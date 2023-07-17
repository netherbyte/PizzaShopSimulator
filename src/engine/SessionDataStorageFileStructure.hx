package engine;

typedef SessionDataStorageFileStructure = {
	version:Int,
	tutorialFinished:Bool,
	volume:Float,
	language:String,
	shop:{
		name:String, basePrice:Float, toppingPrice:Float
	},
	economy:{
		self:{
			nw:Float
		}, shop:{
			revenue:Float, sales:Int
		}
	}
}
