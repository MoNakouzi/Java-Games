# How To Play

To try out the game without downloading the source code, the JAR and EXE files provided in a separate folder in this repo work. However, if the source code is downloaded, you can just compile and run the code (in an IDE or otherwise), and a JAR pop-up window will show up as this game is all within a JFrame.
Please share any errors found or suggestions to make this game better.
<br/>

# About This Game

As a personal project, I made this Brick Breaker game made fully in Java using object-oriented design. The game is built with different game modes, where players can 
choose between a goal of breaking 50 bricks or 100 bricks, with an extra mode with infinite bricks. The game supports both 1-player and 2-players gameplay, each with 
their own controls.
<br/>

# Building Blocks And Design

## Classes
As stated previously, this game was made using object-oriented programming (OOP) in Java. Several classes were created and built upon one another; I will go through them here:

  - BrickBreaker: This is the main class which is run to create the JFrame containing the game.
  - GameFrame: This is the custom JFrame which is called in BrickBreaker. This class creates a new JPanel and sets the JFrame's size, visibility, resizability, background colour, and so on.
  - GamePanel: This is the custom JPanel that is created in GameFrame. This class contains the game loop and all the game's functionality. It makes use of functions defined in other classes to control the graphics/building blocks (such as paddles, balls, bricks, and so on) on the screen, but the functionality itself is all written within this class. Moreover, an enum containing all possible states is contained within this class. Those states control the game modes and the graphics shown in each, including the title screen, pause screen, gameover screen, 1-player screen, 2-players screen, and so on. Each state has different graphics drawn to account for what the state should be showing. This class also senses any mouse or key presses, and does the function of each accordingly.
  - State Screens: The following are classes created for each state to define what graphics each screen should show,
    - TitleScreen: This contains the graphics shown on the title screen, including the colourful BRICK BREAKER and the background image (included as a separate file and added by code).
    - OptionsScreen: This contains the game mode selection graphics, with functionality to make buttons 'glow up' when the mouse hovers over them or clicks them. The choices made by the user here are stored for the rest of the turn and depicts what the graphics will be in other states (whether 1/2-players graphics and the number of bricks drawn).
    - TransitionScreen: This screen is just a transition before the main menu to ensure the player chose their options correctly (there is an option to go back and re-select options).
    - MainMenu: This is the start screen, where the controls are shown (if 1-player mode is selected, only 1-player controls are shown; if 2-players mode is selected, 2-player controls are shown), and the player presses start to begin the game.
    - PauseScreen: This pause screen appears if 'P' or 'ESC' are pressed. It shows the players that the game is paused with a translucent cover over the frozen gameplay. The player can then continue the game or quit it by following instructions drawn on the screen.
    - GameOver: This screen appears if the player reaches the goal of bricks broken or loses all the balls they had. It shows some player statistics, and is designed differently for 1/2-player modes. The player can restart or quit the game from here.
    - NOTE: The running state which contains the actual gameplay does not have it's own class as the GamePanel depicts functionality and it continues only a few components to draw.
    - NOTE 2: Each of those state screens have their own 'draw' method with a Graphics parameter that is used to design each one according to use.
  - SoundEffect: This class contains functions that generate sounds when they are called if the game is not muted (done by pressing 'M' at any point during the game, as explained in controls). Those functions are called throughout the GamePanel class when needed to generate a more immersive gameplay. The sound effects are stores as .wav files within the source folder.
  - RemainingCount: This is a class to draw the number of balls remaining shown at the bottom of the screen, and is updated throughout the gameplay. The graphics of this class are called during the run state only (and in the background of some states).
  - Ball: This is the class that draws the ball. It gives the ball an Id to make it identifiable in 2-player modes, and depicts the speed and size of the ball. This class contains functionality to make the ball speed up to a certain limit with each collision with the paddle (the actual increase in speed is done within the GamePanel class).
  - Brick: This class draws the graphics for the bricks, along with the gradient in colour and layering to make them look better. The bricks created are stored in a hashmap as keys that have Boolean values. Those Boolean values depict whether each brick has been collided with or not (true if collision happened), and this functionality is used in the GamePanel class to make bricks disappear.
  - Paddle: This class contains the graphics for the paddles and the speed the paddles move with the key presses. The paddles also have an Id to differentiate them in 2-players mode.
  - NOTE: Most of these classes contain key/action listener classes to keep on the lookout for any mouse clicks/hovers and key presses to carry out the actions needed if any of them match.

## Functionality
When the game is run, the game loop starts executing infinitely. This loop was made to generate 60 frames/second (60 fps). Whitin this loop, a function is called to check the state of the game.
When the state is checked, the functionality is executed according to each state. If the current state is one of the run states, then the function that allows the paddles and balls to move is called, and the repaint function is called to show any changes on the screen with each repetition of the game loop. Moreover, the checkCollision() function is called.
If the state was not one of the run states and not the pause state, then the game just repaints the screen. In some classes, this allows certain features, such as a button 'glowing up' when the mouse hovers over it.
If the state was the pause state, the functions freezing the gameplay are called to make sure that the ball stays stationary as the game is paused. The repaint function is also called.

The functionality during the running states are due to 2 main functions in the GamePanel class:
- move(): This function calls the move() function within the Paddle and Ball class to move them a set number of pixels with each frame. It checks for 2-players mode and moves the second ball and paddle accordingly.
- checkCollision(): This checks the collision of the paddles with the edge of screen, the ball with paddles and the edges of the screen, and the ball with the bricks. The direction/speed of each is adjusted according to the nature of the collision. THe collision of the ball with the paddles/bricks is done by .instersects, as they all extend the Java Rectangle class. Upon the ball's collision with the bricks, the value of the brick in the map is updated to True. When the value is true and the brick class is repainted by the game loop, the brick is no longer drawn/detected (due to functionality in the Brick class). This allows the program to keep track of which bricks are destroyed and stop drawing them.
