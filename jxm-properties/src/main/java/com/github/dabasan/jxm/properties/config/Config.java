package com.github.dabasan.jxm.properties.config;

import java.util.Objects;

/**
 * Config
 *
 * @author maeda6uiui
 */
public class Config {
    //Key code
    public KeyCode turnUp;
    public KeyCode turnDown;
    public KeyCode turnLeft;
    public KeyCode turnRight;
    public KeyCode moveForward;
    public KeyCode moveBackward;
    public KeyCode moveLeft;
    public KeyCode moveRight;
    public KeyCode walk;
    public KeyCode jump;
    public KeyCode reload;
    public KeyCode dropWeapon;
    public KeyCode zoom;
    public KeyCode fireMode;
    public KeyCode switchWeapon;
    public KeyCode weapon1;
    public KeyCode weapon2;
    public KeyCode fire;
    //Other config
    public int mouseSensitivity;
    public int brightness;
    public WindowMode windowMode;
    public boolean enableSound;
    public boolean enableBlood;
    public boolean invertMouse;
    public boolean frameSkip;
    public boolean anotherGunsight;
    public String name;

    /**
     * Creates a config instance.
     */
    public Config() {
        this.reset();
    }

    /**
     * Copies a config instance.
     *
     * @param config Config
     */
    public Config(Config config) {
        this.turnUp = config.turnUp;
        this.turnDown = config.turnDown;
        this.turnLeft = config.turnLeft;
        this.turnRight = config.turnRight;
        this.moveForward = config.moveForward;
        this.moveBackward = config.moveBackward;
        this.moveLeft = config.moveLeft;
        this.moveRight = config.moveRight;
        this.walk = config.walk;
        this.jump = config.jump;
        this.reload = config.reload;
        this.dropWeapon = config.dropWeapon;
        this.zoom = config.zoom;
        this.fireMode = config.fireMode;
        this.switchWeapon = config.switchWeapon;
        this.weapon1 = config.weapon1;
        this.weapon2 = config.weapon2;
        this.fire = config.fire;
        this.mouseSensitivity = config.mouseSensitivity;
        this.brightness = config.brightness;
        this.windowMode = config.windowMode;
        this.enableSound = config.enableSound;
        this.enableBlood = config.enableBlood;
        this.invertMouse = config.invertMouse;
        this.frameSkip = config.frameSkip;
        this.anotherGunsight = config.anotherGunsight;
        this.name = config.name;
    }

    /**
     * Resets every field to its default value.
     */
    public void reset() {
        //Key code
        turnUp = KeyCode.KEY_UP;
        turnDown = KeyCode.KEY_DOWN;
        turnLeft = KeyCode.KEY_LEFT;
        turnRight = KeyCode.KEY_RIGHT;
        moveForward = KeyCode.KEY_W;
        moveBackward = KeyCode.KEY_S;
        moveLeft = KeyCode.KEY_A;
        moveRight = KeyCode.KEY_D;
        walk = KeyCode.KEY_TAB;
        jump = KeyCode.KEY_SPACE;
        reload = KeyCode.KEY_R;
        dropWeapon = KeyCode.KEY_G;
        zoom = KeyCode.KEY_SHIFT;
        fireMode = KeyCode.KEY_X;
        switchWeapon = KeyCode.KEY_Z;
        weapon1 = KeyCode.KEY_1;
        weapon2 = KeyCode.KEY_2;
        fire = KeyCode.KEY_MOUSE_L;
        //Other config
        mouseSensitivity = 25;
        brightness = 4;
        windowMode = WindowMode.FULL_SCREEN;
        enableSound = true;
        enableBlood = true;
        invertMouse = false;
        frameSkip = false;
        anotherGunsight = false;
        name = "xopsplayer";
    }

    @Override
    public String toString() {
        return "Config [turnUp=" + turnUp + ", turnDown=" + turnDown + ", turnLeft=" + turnLeft
                + ", turnRight=" + turnRight + ", moveForward=" + moveForward + ", moveBackward="
                + moveBackward + ", moveLeft=" + moveLeft + ", moveRight=" + moveRight + ", walk="
                + walk + ", jump=" + jump + ", reload=" + reload + ", dropWeapon=" + dropWeapon
                + ", zoom=" + zoom + ", fireMode=" + fireMode + ", switchWeapon=" + switchWeapon
                + ", weapon1=" + weapon1 + ", weapon2=" + weapon2 + ", fire=" + fire
                + ", mouseSensitivity=" + mouseSensitivity + ", brightness=" + brightness
                + ", windowMode=" + windowMode + ", enableSound=" + enableSound + ", enableBlood="
                + enableBlood + ", invertMouse=" + invertMouse + ", frameSkip=" + frameSkip
                + ", anotherGunsight=" + anotherGunsight + ", name=" + name + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Config config = (Config) o;
        return mouseSensitivity == config.mouseSensitivity
                && brightness == config.brightness
                && enableSound == config.enableSound
                && enableBlood == config.enableBlood
                && invertMouse == config.invertMouse
                && frameSkip == config.frameSkip
                && anotherGunsight == config.anotherGunsight
                && turnUp == config.turnUp
                && turnDown == config.turnDown
                && turnLeft == config.turnLeft
                && turnRight == config.turnRight
                && moveForward == config.moveForward
                && moveBackward == config.moveBackward
                && moveLeft == config.moveLeft
                && moveRight == config.moveRight
                && walk == config.walk
                && jump == config.jump
                && reload == config.reload
                && dropWeapon == config.dropWeapon
                && zoom == config.zoom
                && fireMode == config.fireMode
                && switchWeapon == config.switchWeapon
                && weapon1 == config.weapon1
                && weapon2 == config.weapon2
                && fire == config.fire
                && windowMode == config.windowMode
                && Objects.equals(name, config.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                turnUp,
                turnDown,
                turnLeft,
                turnRight,
                moveForward,
                moveBackward,
                moveLeft,
                moveRight,
                walk,
                jump,
                reload,
                dropWeapon,
                zoom,
                fireMode,
                switchWeapon,
                weapon1,
                weapon2,
                fire,
                mouseSensitivity,
                brightness,
                windowMode,
                enableSound,
                enableBlood,
                invertMouse,
                frameSkip,
                anotherGunsight,
                name
        );
    }

    public Config setTurnUp(KeyCode turnUp) {
        this.turnUp = turnUp;
        return this;
    }

    public Config setTurnDown(KeyCode turnDown) {
        this.turnDown = turnDown;
        return this;
    }

    public Config setTurnLeft(KeyCode turnLeft) {
        this.turnLeft = turnLeft;
        return this;
    }

    public Config setTurnRight(KeyCode turnRight) {
        this.turnRight = turnRight;
        return this;
    }

    public Config setMoveForward(KeyCode moveForward) {
        this.moveForward = moveForward;
        return this;
    }

    public Config setMoveBackward(KeyCode moveBackward) {
        this.moveBackward = moveBackward;
        return this;
    }

    public Config setMoveLeft(KeyCode moveLeft) {
        this.moveLeft = moveLeft;
        return this;
    }

    public Config setMoveRight(KeyCode moveRight) {
        this.moveRight = moveRight;
        return this;
    }

    public Config setWalk(KeyCode walk) {
        this.walk = walk;
        return this;
    }

    public Config setJump(KeyCode jump) {
        this.jump = jump;
        return this;
    }

    public Config setReload(KeyCode reload) {
        this.reload = reload;
        return this;
    }

    public Config setDropWeapon(KeyCode dropWeapon) {
        this.dropWeapon = dropWeapon;
        return this;
    }

    public Config setZoom(KeyCode zoom) {
        this.zoom = zoom;
        return this;
    }

    public Config setFireMode(KeyCode fireMode) {
        this.fireMode = fireMode;
        return this;
    }

    public Config setSwitchWeapon(KeyCode switchWeapon) {
        this.switchWeapon = switchWeapon;
        return this;
    }

    public Config setWeapon1(KeyCode weapon1) {
        this.weapon1 = weapon1;
        return this;
    }

    public Config setWeapon2(KeyCode weapon2) {
        this.weapon2 = weapon2;
        return this;
    }

    public Config setFire(KeyCode fire) {
        this.fire = fire;
        return this;
    }

    public Config setMouseSensitivity(int mouseSensitivity) {
        this.mouseSensitivity = mouseSensitivity;
        return this;
    }

    public Config setBrightness(int brightness) {
        this.brightness = brightness;
        return this;
    }

    public Config setWindowMode(WindowMode windowMode) {
        this.windowMode = windowMode;
        return this;
    }

    public Config setEnableSound(boolean enableSound) {
        this.enableSound = enableSound;
        return this;
    }

    public Config setEnableBlood(boolean enableBlood) {
        this.enableBlood = enableBlood;
        return this;
    }

    public Config setInvertMouse(boolean invertMouse) {
        this.invertMouse = invertMouse;
        return this;
    }

    public Config setFrameSkip(boolean frameSkip) {
        this.frameSkip = frameSkip;
        return this;
    }

    public Config setAnotherGunsight(boolean anotherGunsight) {
        this.anotherGunsight = anotherGunsight;
        return this;
    }

    public Config setName(String name) {
        this.name = name;
        return this;
    }
}
