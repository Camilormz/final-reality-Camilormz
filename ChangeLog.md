ChangeLog
=========

Version 3.0
-----------
- (RC.3) Added a GUI, game for lat release in the context of its course
- (B.15) Added a state pattern based phase system to manage the game flow
- (RC.2) Added an observer for character knock outs to check winner at K.O.s
- (B.14) Added an observer for turns management at new character enqueuing
- (B.13) Added the checks for character combat availability in the controller and winner checks
- (B.12) Added the ability to attack and equip weapons from the controller
- (B.11) Added an inventory and sets for assigned playable characters o the player and assigned
         enemies to the CPU to play
- (B.10) Added all necessary getters on the controller
- (B.9) Created game controller, there is only model object creation implemented in it
- (RC.1) Refactored all *dead* references to *knock out* in order to follow the spirit that a 0 HP
         character could be available for future combats.
- (B.8) Improved bond playable character - weapon and improved heritage
- (B.7) Ensured 100% coverage
- (B.6) Code fixed in order to pass all tests (99% line and 85% branch coverage)
- (B.5) Preliminary unfixed non-magical combat implementation
- (B.4) Preliminary improved weapon equipment implementation.
- (B.3) Defense points implementation.
- (B.2) Character Damage points implementation.
- (B.1) Health Points (HP) implementation.

Version 2.0
-----------
- (RC.1) Ensured javadoc for all public classes, interfaces, enums, methods and tests.
- (B.12) Ensured 100% total test coverage.
- (B.11) Ensured 100% ``character`` package test coverage.
- (B.10) Reached 100% ``AbstractCharacter`` coverage after implementing tests for ``waitTurn`` and
         ``getCharacterDomain`` methods.
- (B.9) Created tests for the equipment of weapons.
- (B.8) Created tests for constructors, equals and hashcode in playable character subclasses
- (B.7) Fix of ``equals`` and ``hashCode`` methods.
- (B.6) Usage of ``EnumSet<WeaponType>`` to move ``equip`` method directly to
        ``AbstractPlayerCharacter``, this implies that the character classes have an ``EnumSet`` in
        their constructors of the weapons they are allowed to use.
- (B.5) Removed method ``getTurnWeight`` from ``ICharacter`` interface, there is only ``waitTurn``
        needed.
- (B.4) Added ``IMagicalCharacter`` interface to manage magical character's behaviour.
- (B.3) Turning of ``Weapon`` into an abstract class ``AbstractWeapon`` and split it into its
        subtypes to ensure Single-Responsibility principle. (Current test coverage 0%).
- (B.2) Turning of ``PlayerCharacter`` into an abstract class ``AbstractPlayerCharacter`` and split
        it into its subclasses to ensure Single-Responsibility principle. Removal of the previous
        implementation tests.
- (B.2) Defining of ``CharacterDomain`` as set of characteristics and behaviours of the character
        relative to the game mechanics, and restricted ``CharacterClass`` to only
        ``PlayerCharacter``.
- (B.1) Restricted the use of weapons enabling it only to ``PlayerCharacter``.
        Added method to ``ICharacter``, ``turnWeight`` and fixed SOLID issues with
        ``AbstractCharacter`` on method ``waitTurn``.

Version 1.0
-----------
- (RC.1) Implemented missing tests
- (B.5) Updated License
- (B.4) Implementation and testing of Enemy class (ensured 100% branch coverage)
- (B.3) Created .gitignore
- (B.2) Implementation of most base elements of the model
- (B.1) Created project

A note on the version naming
----------------------------
- B.n: Version ``x.y`` _beta x_, alternative to ``x.y-b.n``.
  For example: ``v1.0-b.3``.
- RC.n: Release candidate x, alternative to ``x.y-rc.n``.
  For example: ``v1.0-rc.2``.
