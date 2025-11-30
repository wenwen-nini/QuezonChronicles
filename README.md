<div align="center">

# Quezon Chronicles

**A Quezon Province-Inspired Console RPG Adventure**  

<b>IT-2108:</b><br>
Aguho, Alwynn L.<br>
Jamilano, John Red S.<br>
Cortuna, Nhel Eduard B.<br>
Mapalad, Nell John Cedrick J.<br>
</div>

---
<p align="center">
  <a href="#-overview">
    <img src="https://img.shields.io/badge/📖_Overview-6b4f29?style=for-the-badge">
  </a>
  <a href="#-key-features">
    <img src="https://img.shields.io/badge/⭐_Features-8c6931?style=for-the-badge">
  </a>
  <a href="#-gameplay-guide">
    <img src="https://img.shields.io/badge/🎮_Gameplay_Guide-b8863b?style=for-the-badge">
  </a>
  <a href="#-program-structure">
    <img src="https://img.shields.io/badge/🏗️_Program_Structure-d4a15f?style=for-the-badge">
  </a>
  <a href="#-how-to-run">
    <img src="https://img.shields.io/badge/🚀_How_to_Run-e3b679?style=for-the-badge">
  </a>
  <a href="#-sample-output">
    <img src="https://img.shields.io/badge/📜_Sample_Output-efcb9f?style=for-the-badge">
  </a>
  <a href="#-author--acknowledgement">
    <img src="https://img.shields.io/badge/👤_Author_Acknowledgement-f7e4c5?style=for-the-badge">
  </a>
</p>


---
## 📖 Overview

**Quezon Chronicles** is a comprehensive Java-based RPG featuring:
- **5 playable character classes** with unique combat mechanics (Warrior, Bruid, Mage, Tagalog Monk, Thief)
- **Turn-based combat system** with resource management (Stamina/MP) and status effects
- **Two branching story paths** through Quezon Province (West: Lucban→Lucena→Tiaong | East: Gumaca→Lopez→Real)
- **35 unique enemy types** inspired by Quezon's folklore and local culture
- **23 Quezon-themed consumable items** for healing, buffs, and debuff removal
- **Progressive difficulty** with minibosses and final boss encounters per path
- **Character leveling system** with stat growth and experience rewards

---

## 🎯 Key Features

### Character Classes
| Class | HP | Resource | Attack | Defense | Speed | Playstyle |
|-------|----|----|--------|---------|-------|----------|
| **Warrior** | 140 | 50 Stamina | 18 | 12 | 5 | Tank, High sustain |
| **Bruid** | 100 | 80 MP | 12 | 8 | 6 | Hybrid support, Nature magic |
| **Mage** | 70 | 120 MP | 12 | 6 | 18 | Glass cannon, High speed |
| **Tagalog Monk** | 110 | 50 Stamina | 16 | 8 | 8 | Balanced melee hybrid |
| **Thief** | 85 | 60 Stamina | 22 | 5 | 14 | High damage, High speed |

### Combat Mechanics
- **Initiative System**: Turn order based on Speed stat
- **Resource Management**: Stamina (physical classes) or MP (magical classes)
- **Status Effects**: Poison, burn, stun, confusion with multi-turn duration
- **Skill Cooldowns**: Ultimate abilities require cooldown management
- **Critical Strikes**: 10-25% chance for double damage
- **Loot Drops**: 40% probability of item drops from defeated enemies

### World Exploration
**West Path Towns**: Lucban, Lucena, Sariaya, Candelaria, Tiaong
- Minibosses: Harbor Sentinel, Don Mariano (final boss)

**East Path Towns**: Gumaca, Lopez, Calauag, Infanta, Real
- Minibosses: Multiple regional minibosses
- Final Boss: Queen Amihan (240 HP, 28 Attack)

### Inventory System
- **Capacity**: 10 items maximum
- **23 Items Available**: Bibingka, Lambanog, Turon, PugonCoffee, SinigangHipon, Habhab, KipingDelight, CocoJam, BananaChips, Chami, DriedFishSnack, LongganisangLucban, LopezCocoaDrink, MountainHoney, Panutsa, Pinagong, SumanIbos, Tinuto, TropicalBreezeJuice, LambanogLecheFlan, AdobongPusit, Budin
- **Effects**: HP restoration, MP restoration, debuff removal, status effect cures

---

## 🏗️ OOP Concepts Applied

### 1. **Inheritance**
- `Character` (abstract) → `Player` (abstract) → 5 concrete classes (Warrior, Bruid, Mage, TagalogMonk, Thief)
- `Character` (abstract) → `Enemy` (abstract) → 31 concrete enemy subclasses
- `Item` (abstract) → 23 concrete item subclasses

### 2. **Polymorphism**
- Abstract methods implemented uniquely per class: `useMoves()`, `enemyMove()`, `useItem()`
- Method overriding for class-specific combat abilities and stat progression
- Battle system calls methods on abstract types, runtime resolution selects concrete implementation

### 3. **Encapsulation**
- Private stats (HP, MP, Stamina, Attack, Defense, Speed) with public getter/setter methods
- Protected access for inherited classes to extend functionality
- Static inventory management in Player class
- Internal debuff tracking with private arrays and methods

### 4. **Abstraction**
- Abstract classes hide implementation while defining interface contracts
- Battle system operates through Character interface, not concrete types
- Styling utilities abstracted into separate packages (animationHub, textColor, printAlignmentHub, clearScreen)
- Complex combat logic hidden behind simple method calls

### 5. **Composition**
- Character contains arrays of active debuffs and debuff durations
- Player contains inventory (Item array), move descriptions, path tracking data
- Battle system combines Player, Enemy, and Item interactions
- Town contains array of enemies for encounters

---

## 📁 Program Structure

```
Main/
├── Main.java                          # Application entry point
├── game/
│   ├── Game.java                      # Main menu, title animation, game flow
│   ├── GameMenu.java                  # Character creation, path selection
│   └── BattleSystem.java              # Turn-based combat loop engine
├── worldBuilder/
│   ├── WorldMap.java                  # Builds West & East paths with towns
│   └── Town.java                      # Town logic, enemy encounters, progression
├── character/
│   ├── Character.java                 # Abstract base class (stats, debuffs)
│   ├── player/
│   │   ├── Player.java                # Abstract player class (inventory, leveling)
│   │   └── classes/                   # 5 concrete player classes
│   │       ├── Warrior.java           # Tank class with stamina
│   │       ├── Bruid.java             # Balanced fighter
│   │       ├── Mage.java              # Glass cannon with MP
│   │       ├── TagalogMonk.java       # Support/hybrid class
│   │       └── Thief.java             # High-damage, high-speed assassin
│   └── enemy/
│       ├── Enemy.java                 # Abstract enemy class (loot, XP)
│       └── subclasses/                # 31 concrete enemy types
│           ├── TanimGuardian.java
│           ├── HabhabBandit.java
│           ├── LucenaPirate.java
│           ├── PortRat.java
│           ├── HarborSentinel.java    # Miniboss
│           ├── BakeryGremlin.java
│           ├── HeritageWraith.java
│           ├── DonMariano.java        # West path final boss
│           ├── QueenAmihan.java       # East path final boss
│           └── [21 more...]
├── item/
│   ├── Item.java                      # Abstract item base class
│   └── [23 concrete items]
│       ├── Bibingka.java              # +25 HP, remove debuffs
│       ├── Lambanog.java              # Spirit drink
│       ├── PugonCoffee.java           # Coffee item
│       └── [20 more...]
└── styles/
    ├── animationHub/
    │   ├── TypeWriter.java            # Typewriter text animation
    │   └── LoadingDots.java           # Loading animation
    ├── textColor/
    │   └── TextColorHub.java          # ANSI color codes (RED, GREEN, YELLOW, etc.)
    ├── printAlignmentHub/
    │   └── CenterHub.java             # Text centering & right alignment
    └── clearScreen/
        └── ClearScreen.java           # Cross-platform terminal clearing
```

---

## 🚀 How to Run

### Prerequisites
- **Java Development Kit (JDK) 11 or later**
- **Bash shell** for executing build commands
- ~50MB disk space for compiled bytecode

### Compilation

```bash
# Navigate to project root
cd /workspaces/QuezonChronicles

# Generate sources list
find Main -name "*.java" > sources.txt

# Compile all files to 'out' directory
javac -d out @sources.txt
```

### Execution

```bash
# Run the game
java -cp out Main.Main
```

### Quick Start (One Command)
```bash
cd /workspaces/QuezonChronicles && find Main -name "*.java" > sources.txt && javac -d out @sources.txt && java -cp out Main.Main
```

### Rebuild After Modifications
```bash
find Main -name "*.java" > sources.txt && javac -d out @sources.txt
```

---

## 🎮 Gameplay Guide

### Starting the Game
1. Run `java -cp out Main.Main`
2. View title animation with ASCII art
3. Choose: (1) Start, (2) About, (3) Exit

### Character Creation
1. Enter your character name
2. Select class (1-5)
3. Read class description and abilities
4. Choose path (West or East)

### Combat System
**During Battle**:
```
( 1 ) Move 1 - Basic attack (usually free)
( 2 ) Move 2 - Special ability (costs resource)
( 3 ) Move 3 - Utility move (may cost resource)
( 4 ) Move 4 - Ultimate ability (cooldown after use)
( 5 ) Use Item - Consume from inventory
```

**Combat Flow**:
- Initiative determined by Speed stat
- Player and enemy alternate turns
- Status effects trigger each turn
- Battle ends when one side reaches 0 HP
- Victorious player gains XP and receives loot

### Town Progression
- Enter town and encounter enemies
- Defeat required number of enemies (varies per town)
- Progress to next town upon completion
- Some towns have minibosses blocking final progression
- Defeat final boss to complete that path

### Leveling System
- Each enemy defeat grants experience
- Experience accumulates toward next level
- Level up triggers stat increase based on class
  - Warrior: +10 HP, +5 Stamina, +1 Defense, +2 Attack
  - Mage: Enhanced MP and spell power
  - Thief: +Attack, +Speed emphasis

---

## 📊 Example Combat Scenario

```
Warrior enters Lucena Town
    └─ Encounters: Port Rat, Lucena Pirate (miniboss)

Round 1: Warrior vs Port Rat
    Warrior Speed (5) < Port Rat Speed (8)
    → Port Rat attacks first!
    
    Port Rat: "Port Rat scurries at you!"
    Warrior takes 8 damage (reduced by defense)
    
    Warrior: "Warrior used Slash!"
    Port Rat takes 18 damage
    
    Port Rat defeated! +50 XP, +1 Chami (item drop)
    
Round 2: Warrior vs Lucena Pirate (miniboss)
    [Extended battle with special moves, MP/Stamina management...]
    Lucena Pirate defeated! +100 XP, +1 Coconut Brigade loot
    
Lucena progression: 2/2 enemies defeated
→ Advance to Sariaya
```

---

## 🎬 Sample Output

### Main Menu
```
Game Starting in...

[ASCII ART TITLE]

=====================================================================================
            ( 1 ) Start
            ( 2 ) About the Game
            ( 3 ) Exit
=====================================================================================
Enter your choice: 
```

### Battle UI
```
==== Warrior vs Lucena Pirate ====
Health: 140/140        vs        Health: 85/85
Stamina: 50/50
=====================
Player goes first!

Warrior used Slash!
Lucena Pirate took 18 damage.

[ Menu options appear... ]
```

### Character Stats
```
====== Warrior Stats ======
Health: 140/140
Stamina: 50/50
Defense: 12
Attack Power: 18
Speed: 5
Experience: 150/100
Level: 2
===========================
```

---

## 👤 Author & Acknowledgement

### Development
Developed as a comprehensive Java educational project demonstrating OOP principles, design patterns, and game architecture.
### Acknowledgement
Place holder for now

### Information Table

| | Name | Role |
|----------|----------|----------|
| <img src="images/wyn.jpg" width="120">| Aguho, Alwynn L. | Lead Developer 1    |
|<img src="images/red.jpg" width="120">| Jamilano, John Red S. | Lead Developer 2    |
|<img src="images/nhel.jpg" width="120">| Cortuna, Nhel Edward B. |  UI Designer / Tester    |
|<img src="images/nell.jpg" width="120">| Mapalad, Nell John Cedrick J. | UI Designer / Tester |

---

## 📞 Support & Contribution

For bug reports, feature requests, or contributions, refer to the project repository on GitHub. 

---

## 🙏 Acknowledgements

### Special Thanks to Our Instructors
- **Ma'am Fatima** - For providing comprehensive lessons, templates, and resources shared through her Discord.
- **Sir Emmanuel** - For his dedicated lessons and continuous effort in teaching us whenever possible. His support and expertise greatly contributed to our understanding and development

### Learning Resources & Inspiration
- **YouTube Channels**: 
  - Bro Code - For comprehensive Java tutorials and best practices
  - Coding with Mosh - For clear programming concepts and design patterns
  - Other Indian Coding Tutorials - For diverse perspectives and innovative solutions
- **Stack Overflow** - For community support and solving countless technical challenges
- **Open-Source Community** - For shared knowledge and programming resources

### Personal Support
- **Our Parents** - For their unwavering support, encouragement, and belief in this project. This wouldn't have been possible without their sacrifices and dedication
- **Our Peers & Friends** - For playtesting, constructive feedback, and motivation throughout development

---

**Enjoy your adventure through Quezon Chronicles!** 🏛️⚔️✨
