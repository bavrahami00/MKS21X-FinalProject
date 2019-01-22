# SpaceInvaders

TO RUN THE PROGRAM: After cloning, simply type "bash run.sh" into the Terminal

If the program doesn't run, you can run it manually by typing "resize -s 40 100" followed by "javac -cp lanterna.jar:. SpaceInvaders.java" and "java -cp lanterna.jar:. SpaceInvaders"

About the Program:

This is a version of Space Invaders on the terminal. It is much like the original game, with enemies moving across the screen and the player at the bottom with barriers protecting it.
Each enemy kill is worth 10 points, displayed on the top left corner and the player has 3 lives, one of which is lost every time an enemy laser hits the player.
When all the enemies are killed, the next level starts and the enemies respawn and the difficulty increases. There are 10 levels in total.

Cheats :') Page Up ==> Add 1 life Page Down ==> Subtract 1 life End ==> Toggle Invincibility


Update Log:

-Derek (1/4) ==> Created SpaceInvaders class that creates an empty terminal when run [more to be added in the future!]
-Benjamin (1/4) ==> Created outlines of Being/Laser/Enemy/User/Powerup/HasLocation classes/interfaces that have all planned methods within (but may not work)

-Derek (1/7) ==> Started working on character movement in SpaceInvaders class
-Benjamin (1/7) ==> Moved onto terminal, tried to instantiate a few
classes in file

-Derek (1/8) ==> Added moving character
-Benjamin (1/8) ==> Changed move methods in User and Laser to work on
terminal

-Derek (1/9) ==> Drew Barriers
-Benjamin (1/9) ==> Made laser shoot and die at edge of screen

-Derek (1/12) ==> Scrapped earlier barriers and redid them (with hitboxes)

-Benjamin (1/13) ==> Fixed barrier issues, made enemies

-Derek (1/14) ==> Added to player model

-Benjamin(1/14) ==> Created limits to movement

-Derek(1/15) ==> Adjusted enemy spacing
-Benjamin(1/15) ==> Create faster play and end of game scenarios

-Derek (1/16) ==> Cleaned up look and added features like levels
-Benjamin (1/16) ==> Changed the way the enemy attacked and made only front shoot

-Derek (1/17) ==>Added more to README(see top) and did more comments
-Benjamin (1/17) ==> Started implementing Powerup class

-Derek (1/18) ==>Cleaned up/fixed code
-Benjamin (1/18) ==> Continued putting in Powerup, made it move

-Derek (1/19) ==> Added run.sh and wrote more in readme

-Benjamin (1/20) ==> Cleaned up code
