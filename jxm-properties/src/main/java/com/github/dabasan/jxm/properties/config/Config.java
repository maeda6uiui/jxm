package com.github.dabasan.jxm.properties.config;

/**
 * Config
 * 
 * @author Daba
 *
 */
public class Config {
	// Key code
	private KeyCode turnUp;
	private KeyCode turnDown;
	private KeyCode turnLeft;
	private KeyCode turnRight;
	private KeyCode moveForward;
	private KeyCode moveBackward;
	private KeyCode moveLeft;
	private KeyCode moveRight;
	private KeyCode walk;
	private KeyCode jump;
	private KeyCode reload;
	private KeyCode dropWeapon;
	private KeyCode zoom;
	private KeyCode fireMode;
	private KeyCode switchWeapon;
	private KeyCode weapon1;
	private KeyCode weapon2;
	private KeyCode fire;
	// Other config
	private int mouseSensitivity;
	private int brightness;
	private WindowMode windowMode;
	private boolean enableSound;
	private boolean enableBlood;
	private boolean invertMouse;
	private boolean frameSkip;
	private boolean anotherGunsight;
	private String name;

	public Config() {
		this.reset();
	}
	public void reset() {
		// Key code
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
		// Other config
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

	public KeyCode getTurnUp() {
		return turnUp;
	}
	public KeyCode getTurnDown() {
		return turnDown;
	}
	public KeyCode getTurnLeft() {
		return turnLeft;
	}
	public KeyCode getTurnRight() {
		return turnRight;
	}
	public KeyCode getMoveForward() {
		return moveForward;
	}
	public KeyCode getMoveBackward() {
		return moveBackward;
	}
	public KeyCode getMoveLeft() {
		return moveLeft;
	}
	public KeyCode getMoveRight() {
		return moveRight;
	}
	public KeyCode getWalk() {
		return walk;
	}
	public KeyCode getJump() {
		return jump;
	}
	public KeyCode getReload() {
		return reload;
	}
	public KeyCode getDropWeapon() {
		return dropWeapon;
	}
	public KeyCode getZoom() {
		return zoom;
	}
	public KeyCode getFireMode() {
		return fireMode;
	}
	public KeyCode getSwitchWeapon() {
		return switchWeapon;
	}
	public KeyCode getWeapon1() {
		return weapon1;
	}
	public KeyCode getWeapon2() {
		return weapon2;
	}
	public KeyCode getFire() {
		return fire;
	}
	public int getMouseSensitivity() {
		return mouseSensitivity;
	}
	public int getBrightness() {
		return brightness;
	}
	public WindowMode getWindowMode() {
		return windowMode;
	}
	public boolean isEnableSound() {
		return enableSound;
	}
	public boolean isEnableBlood() {
		return enableBlood;
	}
	public boolean isInvertMouse() {
		return invertMouse;
	}
	public boolean isFrameSkip() {
		return frameSkip;
	}
	public boolean isAnotherGunsight() {
		return anotherGunsight;
	}
	public String getName() {
		return name;
	}

	public void setTurnUp(KeyCode turnUp) {
		this.turnUp = turnUp;
	}
	public void setTurnDown(KeyCode turnDown) {
		this.turnDown = turnDown;
	}
	public void setTurnLeft(KeyCode turnLeft) {
		this.turnLeft = turnLeft;
	}
	public void setTurnRight(KeyCode turnRight) {
		this.turnRight = turnRight;
	}
	public void setMoveForward(KeyCode moveForward) {
		this.moveForward = moveForward;
	}
	public void setMoveBackward(KeyCode moveBackward) {
		this.moveBackward = moveBackward;
	}
	public void setMoveLeft(KeyCode moveLeft) {
		this.moveLeft = moveLeft;
	}
	public void setMoveRight(KeyCode moveRight) {
		this.moveRight = moveRight;
	}
	public void setWalk(KeyCode walk) {
		this.walk = walk;
	}
	public void setJump(KeyCode jump) {
		this.jump = jump;
	}
	public void setReload(KeyCode reload) {
		this.reload = reload;
	}
	public void setDropWeapon(KeyCode dropWeapon) {
		this.dropWeapon = dropWeapon;
	}
	public void setZoom(KeyCode zoom) {
		this.zoom = zoom;
	}
	public void setFireMode(KeyCode fireMode) {
		this.fireMode = fireMode;
	}
	public void setSwitchWeapon(KeyCode switchWeapon) {
		this.switchWeapon = switchWeapon;
	}
	public void setWeapon1(KeyCode weapon1) {
		this.weapon1 = weapon1;
	}
	public void setWeapon2(KeyCode weapon2) {
		this.weapon2 = weapon2;
	}
	public void setFire(KeyCode fire) {
		this.fire = fire;
	}
	public void setMouseSensitivity(int mouseSensitivity) {
		this.mouseSensitivity = mouseSensitivity;
	}
	public void setBrightness(int brightness) {
		this.brightness = brightness;
	}
	public void setWindowMode(WindowMode windowMode) {
		this.windowMode = windowMode;
	}
	public void setEnableSound(boolean enableSound) {
		this.enableSound = enableSound;
	}
	public void setEnableBlood(boolean enableBlood) {
		this.enableBlood = enableBlood;
	}
	public void setInvertMouse(boolean invertMouse) {
		this.invertMouse = invertMouse;
	}
	public void setFrameSkip(boolean frameSkip) {
		this.frameSkip = frameSkip;
	}
	public void setAnotherGunsight(boolean anotherGunsight) {
		this.anotherGunsight = anotherGunsight;
	}
	public void setName(String name) {
		this.name = name;
	}
}
