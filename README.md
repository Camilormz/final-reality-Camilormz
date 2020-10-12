Final Reality
=============

![http://creativecommons.org/licenses/by/4.0/](https://i.creativecommons.org/l/by/4.0/88x31.png)

This work is licensed under a 
[Creative Commons Attribution 4.0 International License](http://creativecommons.org/licenses/by/4.0/)

**Author:** Camilo Ramírez Canales (@CamiloRMZ)

Context
-------

This project's goal is to create a (simplified) clone of _Final Fantasy_'s combat, a game developed
by [_Square Enix_](https://www.square-enix.com)
Broadly speaking for the combat the player has a group of characters to control and a group of 
enemies controlled by the computer.

The project is part of the course CC3002 Design and Programming Methodologies at Universidad de Chile

---

**_Brief_ project description**
-------------------------------
As it's said, this project it's a simplified clone of _Final Fantasy_. A turn based combat game where
the player is controlling a fixed size character group in which any character has an associated class
who can equip any weapon of the player's inventory compatible with the class. On the other side, the
adversary (CPU) is controlling a group of variable size enemy group which are single-class entities
unable to equip weapons.

The turn system is determined by weights; the bigger the weight, the bigger the wait before the next
turn, the character behaviour at its turn depends if it's an enemy (CPU controlled), in where it will
attack instant and randomly any player character, or if it's a playable character, in where it will
be avialable for the player to attack an enemy.

Whithin the playable character classes, there are magical classes, they can throw spells if they have
an staff as the equipped weapon.

---

**Current implementation status**
---------------------------------
The current implementation status of the project is that only the backbone of the models of characters
and weapons are implemented, this following the model-view-controller architectural pattern. More
detailed information is available at the [changelog](ChangeLog.md).

---

**Installation and execution**
------------------------------
In the current state, there are no executables. To install the source code and its tests, this project
can be cloned from this repository and the tests can be run on JUnit 5.
