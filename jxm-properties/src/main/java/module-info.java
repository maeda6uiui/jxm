module com.github.maeda6uiui.jxm.properties {
    exports com.github.maeda6uiui.jxm.properties.character;
    exports com.github.maeda6uiui.jxm.properties.character.openxops;
    exports com.github.maeda6uiui.jxm.properties.character.xcs;
    exports com.github.maeda6uiui.jxm.properties.character.xops;
    exports com.github.maeda6uiui.jxm.properties.config;
    exports com.github.maeda6uiui.jxm.properties.weapon;
    exports com.github.maeda6uiui.jxm.properties.weapon.ids;
    exports com.github.maeda6uiui.jxm.properties.weapon.openxops;
    exports com.github.maeda6uiui.jxm.properties.weapon.xgs;
    exports com.github.maeda6uiui.jxm.properties.weapon.xops;
    exports com.github.maeda6uiui.jxm.properties.xops;

    requires transitive org.slf4j;
    requires com.github.maeda6uiui.jxm.bintools;
}
