package Main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import FileIO.EZFileRead;
import logic.Control;
import timer.stopWatchX;
import Data.*;

public class Main {
	// Fields (Static) below...
	public static stopWatchX timer = new stopWatchX(96);

	//background Art
	public static spriteInfo background = new spriteInfo(new Vector2D(0, 0), "BG");

	//Sprite
	public static spriteInfo SIR;
	public static ArrayList<spriteInfo> sprites = new ArrayList<>();
	public static int sprites_size = 0;
	public static SolidBox SIRBox;

	public static int currentSpriteIndex = 0;

	//BoxSprite to interact with
	public static BoxSprite[] crates = new BoxSprite[3];
	public static SolidBox[] crates_bboxes = new SolidBox[3];

	//file reading
	public static HashMap<String, String> map = new HashMap<>();
	public static EZFileRead ezr = new EZFileRead("Scripts.txt");
	public static String raw, Key, Value;
	public static int numLines = ezr.getNumLines();
	public static StringTokenizer st;
	public static int currScript = 0;

	//KeyInput
	public static String trigger = "";

	//coords
	public static int xcoord, ycoord; //to control sprite movement and stopping
	public static int dir = 0; //to control which way sprite is facing

	//walls
	public static SolidBox[] walls = new SolidBox[5];
	public static SolidBox door = new SolidBox(595, 0, 18, 65);

	//keys
	public static Boolean hasKey = false;
	public static spriteInfo key;

	// End Static fields...

	public static void main(String[] args) {
		Control ctrl = new Control(); // Do NOT remove!
		ctrl.gameLoop(); // Do NOT remove!
	}

	/* This is your access to things BEFORE the game loop starts */
	public static void start() {
		// TODO: Code your starting conditions here...NOT DRAW CALLS HERE! (no addSprite or drawString)

		loadScripts();

		// Populate the first Queue (vecs1)
		while (sprites_size < 8) { //start position
			sprites.add(new spriteInfo(new Vector2D(580, 250), "SIRR"));
			sprites_size++;
		}

		// walls
		walls[0] = new SolidBox(0, 0, 598, 63); //upperleft
		walls[1] = new SolidBox(670, 0, 610, 63); //upperright
		walls[2] = new SolidBox(0, 0, 74, 720); //left
		walls[3] = new SolidBox(1194, 0, 86, 720); //right
		walls[4] = new SolidBox(0, 664, 1280, 60); //bottom

		//populate the boxes
		crates[0] = new BoxSprite(new Vector2D(180, 500), "Box0_Cl", "Box0_Op");
		crates[1] = new BoxSprite(new Vector2D(580, 500), "Box1_Cl", "Box1_Op");
		crates[2] = new BoxSprite(new Vector2D(980, 500), "Box2_Cl", "Box2_Op");
		//bounding boxes
		crates_bboxes[0] = new SolidBox(200, 500, 80, 128);
		crates_bboxes[1] = new SolidBox(610, 500, 80, 128);
		crates_bboxes[2] = new SolidBox(1000, 500, 80, 128);

		//key
		key = new spriteInfo(new Vector2D(10, 10), "key");
		randomKey();

	}

	/*
	 * This is your access to the "game loop" (It is a "callback" method from the
	 * Control class (do NOT modify that class!))
	 */
	public static void update(Control ctrl) {
		// TODO: This is where you can code! (Starting code below is just to show you how it works)

		//bounds check
		if (currentSpriteIndex < 0)
			currentSpriteIndex = 0;

		//add background
		ctrl.addSpriteToFrontBuffer(background.getCoords().getX(), background.getCoords().getY(), background.getTag());

		//add boxes
		ctrl.addSpriteToFrontBuffer(crates[0].getCoords().getX(), crates[0].getCoords().getY(), crates[0].getTag());
		ctrl.addSpriteToFrontBuffer(crates[1].getCoords().getX(), crates[1].getCoords().getY(), crates[1].getTag());
		ctrl.addSpriteToFrontBuffer(crates[2].getCoords().getX(), crates[2].getCoords().getY(), crates[2].getTag());

		//add sprite
		SIR = sprites.get(currentSpriteIndex % sprites_size);//Get current sprite from ArrayList

		SIRBox = new SolidBox(SIR.getCoords().getX() + 24, SIR.getCoords().getY(), 80, 128);

		switch (dir) {
			case 1:
				SIRBox.shiftHands(0, -10);
				break;
			case 2:
				SIRBox.shiftHands(0, 10);
				break;
			case 3:
				SIRBox.shiftHands(-10, 0);
				break;
			case 4:
				SIRBox.shiftHands(10, 0);
				break;
		}

		//check for walls
		wallCheck(SIRBox, walls, 5);
		wallCheck(SIRBox, crates_bboxes, 3);
		wallCheck(SIRBox, door);

		//then render sprite
		ctrl.addSpriteToFrontBuffer(SIR.getCoords().getX(), SIR.getCoords().getY(), SIR.getTag());

		//ctrl.drawString(100, 250, map.get("string" + Integer.toString(currScript % numLines)), Color.WHITE);

		//add text
		ctrl.drawString(100, 250, trigger, Color.WHITE);

		//Timer
		if (timer.isTimeUp()) {
			currentSpriteIndex++;
			if ((currentSpriteIndex % (sprites_size / 5)) == 0)
				currScript++;

			timer.resetWatch();
		}

		if (hasKey) {
			ctrl.addSpriteToFrontBuffer(key.getCoords().getX(), key.getCoords().getY(), "key");
		}

	}

	// Additional Static methods below...(if needed)
	/**aaaaa
		A method for loading a file into a hashmap
	*/
	public static void loadScripts() {
		//Text input
		for (int i = 0; i < numLines; i++) {
			raw = ezr.getNextLine(); // Load raw text into string line by line
			st = new StringTokenizer(raw, "*"); // breakup lines into tokens
			Key = st.nextToken(); // Acquires the Key from the raw String
			Value = st.nextToken(); // Acquires the Value from the raw String
			//place tokens into map
			map.put(Key, Value); // Key, Value are the two tokens we acquired from the StringTokenizer
		}
	}

	/**
			A method to populate the sprite arraylist starting at the current coords and make the sprite walk up.
		*/
	public static void moveBack() {
		dir = 1;
		xcoord = SIR.getCoords().getX();
		ycoord = SIR.getCoords().getY();
		sprites_size = 0;
		sprites.clear();
		while (ycoord >= 0) {
			sprites.add(
					new spriteInfo(new Vector2D(xcoord, ycoord), "walk_back" + Integer.toString((sprites_size++) % 8)));
			ycoord -= 8;
		}
		currentSpriteIndex = 0;
	}

	/**
			A method to populate the sprite arraylist starting at the current coords and make the sprite walk down.
		*/
	public static void moveForward() {
		dir = 2;
		xcoord = SIR.getCoords().getX();
		ycoord = SIR.getCoords().getY();
		sprites_size = 0;
		sprites.clear();
		while (ycoord <= 720) {
			sprites.add(new spriteInfo(new Vector2D(xcoord, ycoord),
					"walk_forward" + Integer.toString((sprites_size++) % 8)));
			ycoord += 8;
		}
		currentSpriteIndex = 0;
	}

	/**
		A method to populate the sprite arraylist starting at the current coords and make the sprite walk left.
	*/
	public static void moveLeft() {
		dir = 3;
		xcoord = SIR.getCoords().getX();
		ycoord = SIR.getCoords().getY();
		sprites_size = 0;
		sprites.clear();
		while (xcoord >= 0) {
			sprites.add(new spriteInfo(new Vector2D(xcoord, ycoord),
					"walk_left" + Integer.toString((sprites_size++) % 8)));
			xcoord -= 8;
		}
		currentSpriteIndex = 0;
	}

	/**
		A method to populate the sprite arraylist starting at the current coords and make the sprite walk right.
	*/
	public static void moveRight() {
		dir = 4;
		xcoord = SIR.getCoords().getX();
		ycoord = SIR.getCoords().getY();
		sprites_size = 0;
		sprites.clear();
		while (xcoord <= 1280) {
			sprites.add(new spriteInfo(new Vector2D(xcoord, ycoord),
					"walk_right" + Integer.toString((sprites_size++) % 8)));
			xcoord += 8;
		}
		currentSpriteIndex = 0;
	}

	/**
			A method to win the game.
		*/
	public static void OpenDoor() {
		if (hasKey) {
			trigger = map.get("win");
		} else
			trigger = map.get("needKey");
	}

	/**
		a method to randomly load the key into one of three crates.
	*/
	public static void randomKey() {
		int rnd = (int) (Math.random() * 3);
		crates[rnd].setKey(true);
	}

	/**
		A method to populate the sprite arraylist starting at the current coords and make the sprite stand still.
	*/
	public static void standStill() {
		xcoord = SIR.getCoords().getX();
		ycoord = SIR.getCoords().getY();
		sprites_size = 0;
		sprites.clear();
		String _tag = "SIRR";

		switch (dir) {
			case 1:
				_tag = "SIRB";
				break;
			case 2:
				_tag = "SIRF";
				break;
			case 3:
				_tag = "SIRL";
				break;
			case 4:
				_tag = "SIRR";
				break;
		}

		while (sprites_size < 8) {
			sprites.add(new spriteInfo(new Vector2D(xcoord, ycoord), _tag));
			sprites_size++;
		}
		currentSpriteIndex = 0;
	}

	/**
			A method to bounds check two bounds, and allow the sprite to interact with another obj.
		*/
	public static void touchable() {
		if (SolidBox.touchable(SIRBox, door)) {
			OpenDoor();
		} else {
			for (int i = 0; i < 3; i++) {
				if (SolidBox.touchable(SIRBox, crates_bboxes[i])) {
					if (!crates[i].isOpen()) {
						crates[i].OpenBox();
						if (crates[i].getKey()) {
							hasKey = true;
							trigger = map.get("keyBox");
						} else {
							trigger = map.get("emptyBox");
						}
					} else {
						crates[i].CloseBox();
					}
				}
			}
		}

	}

	/**
		A wrapper method to bounds check an array of bounds.
	*/
	public static void wallCheck(SolidBox sprite, SolidBox[] arr, int size) {
		//TODO: make a function that stops walking when sprite hits a solid object.
		for (int i = 0; i < size; i++) {
			wallCheck(sprite, arr[i]);
		}
	}

	/**
		A helper method to bounds check two bounds, and stop the sprite's movement.
	*/
	public static void wallCheck(SolidBox sprite, SolidBox obj) {
		//TODO: make a function that stops walking when sprite hits a solid object.
		if (SolidBox.stoppable(sprite, obj)) {
			currentSpriteIndex--;
			if (currentSpriteIndex < 0)//bounds check
				currentSpriteIndex = 0;
			SIR = sprites.get(currentSpriteIndex % sprites_size);
			standStill();
		}

	}

}