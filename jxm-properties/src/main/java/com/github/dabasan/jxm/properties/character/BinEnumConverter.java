package com.github.dabasan.jxm.properties.character;

/**
 * Bin to enum converter
 * 
 * @author Daba
 *
 */
public class BinEnumConverter {
	public static ModelType getModelTypeFromBinSpecifier(int spc) {
		ModelType modelType;

		switch (spc) {
			case 0x00 :
				modelType = ModelType.MALE;
				break;
			case 0x1F :
				modelType = ModelType.SUN_GLASSES;
				break;
			case 0x21 :
				modelType = ModelType.POLICEMAN;
				break;
			case 0x23 :
				modelType = ModelType.FEMALE;
				break;
			case 0x24 :
				modelType = ModelType.BACK_PACK;
				break;
			case 0x26 :
				modelType = ModelType.HELMET;
				break;
			default :
				modelType = ModelType.MALE;
				break;
		}

		return modelType;
	}
	public static int getBinSpecifierFromModelType(ModelType modelType) {
		int spc = 0x00;

		switch (modelType) {
			case MALE :
				spc = 0x00;
				break;
			case SUN_GLASSES :
				spc = 0x1F;
				break;
			case POLICEMAN :
				spc = 0x21;
				break;
			case FEMALE :
				spc = 0x23;
				break;
			case BACK_PACK :
				spc = 0x24;
				break;
			case HELMET :
				spc = 0x26;
				break;
		}

		return spc;
	}
}
