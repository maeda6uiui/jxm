module com.github.dabasan.jxm.properties {
    exports com.github.dabasan.jxm.properties.character;
    exports com.github.dabasan.jxm.properties.character.openxops;
    exports com.github.dabasan.jxm.properties.character.xcs;
    exports com.github.dabasan.jxm.properties.character.xops;
    exports com.github.dabasan.jxm.properties.config;
    exports com.github.dabasan.jxm.properties.weapon;
    exports com.github.dabasan.jxm.properties.weapon.ids;
    exports com.github.dabasan.jxm.properties.weapon.openxops;
    exports com.github.dabasan.jxm.properties.weapon.xgs;
    exports com.github.dabasan.jxm.properties.weapon.xops;
    exports com.github.dabasan.jxm.properties.xops;

    requires transitive org.slf4j;
    requires com.github.dabasan.jxm.bintools;
}
