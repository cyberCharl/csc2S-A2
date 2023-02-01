# Falling Words - Java Parallelism and Concurrency Assignment 2

A multithreaded typing game implemented in Java, ensuring both thread safety and sufficient concurrency. The game operates as follows:

- When the start button is pressed, a specified number of words start falling from the top of the panel, but fall at different speeds – some faster, some slower.
- Words disappear when they reach the red zone, whereupon the Missed counter is incremented by one and a new word starts falling (with a different speed).
- The user attempts to type all the words before they hit the red zone, pressing enter after each one.
- If a user types a word correctly, that word disappears, the Caught counter is incremented by one, and the Score is incremented by the length of the word. A new word then starts falling (with a different speed).
- If a user types a word incorrectly, it is ignored.
- The game continues until the specified maximum number of words is reached (whereupon a suitable message is displayed) or the user presses the End button. The user can then play again, beginning a new game by pressing the Start button.
- The user presses the Quit button to end the game.

## Input

The program takes the following command-line parameters:

- <total words> - The number of words that will fall
- <words on screen> - The number of words displayed at any point
- <dictionary file> - The name of a file containing a list of words (one per line) to be used as a dictionary for generating words. The first line of the file will be the total number of words in the file.

## Controls

The program has the following buttons:

- Start/Restart button (starts a game from scratch)
- Pause button (halts game but does not quit)
- Quit button

## Code Architecture

The program follows the Model-View-Controller pattern for user interfaces. The model comprises the classes WordDictionary, the array of WordRecords, and the Score. The view is the GUI and the controllers are the threads that alter the model and the view, moving the word positions, performing animation, adding and removing words as necessary, and updating the counters and the score.

## Credits

Wordlist obtained from https://github.com/dwyl/english-words
<!-- ### Report

A concise report is included, detailing and explaining the coding done, including:

- A description of each of the classes added and any modifications made to the existing classes.
- A description of all the Java concurrency features used and why they were necessary (e.g., atomic variables, synchronized classes, synchronized collections, barriers, etc.).
    An explanation of how the code was written to ensure:
        thread safety (for both shared variables and the Swing library)
        Thread synchronization where necessary
        Liveness
        No deadlock.
    An explanation of how the system was validated and checked for errors, especially regarding concurrency and synchronization. -->