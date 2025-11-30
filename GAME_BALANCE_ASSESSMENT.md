# Quezon Chronicles: Game Balance Assessment

## Overall Verdict
Your game **"Quezon Chronicles"** has **great bones but significant balance issues**. The game is thematically excellent and mechanically diverse, but will struggle with playability in late-game scenarios.

---

## The Good

### ✅ Excellent Thematic Design
- Filipino mythology and cultural elements create unique character
- Food items as in-game currency/consumables add authentic flavor
- Town progression follows real Philippine geography

### ✅ Diverse Class Roster
- 5 classes with distinct mechanics (stamina vs. mana, speed variations)
- Clear archetypes: Warrior (tank), Mage (glass cannon), Thief (speed/crit), Bruid (support), Tagalog Monk (balanced)
- Each class has 4 unique abilities with meaningful cooldowns

### ✅ Scaling System Foundation
- Enemy.java has a sophisticated scaling mechanism already implemented
- Town multipliers and class biases suggest careful design considerations
- Exp system with town-based multipliers (1.0x to 1.7x)

### ✅ Skill Variety
- Each class has distinct playstyles (resource management, cooldown rotation, dodge mechanics)
- Abilities feel impactful with varied utility (damage, healing, stun, dodge, shields)

---

## Critical Balance Problems

### 🔴 CRITICAL: Speed Scaling is Broken

**The Problem:**
- **Mage starts at +18 speed** (your fastest class by far)
- **Mage gains +0 speed per level** throughout the entire game
- **Warrior, Bruid, Tagalog Monk also gain +0 speed** per level
- **Only Thief gets +1 speed per level** (slower than starting speed)

**Impact Chain:**
1. Turn order is determined by speed
2. Who goes first wins most battles
3. Mage ALWAYS goes first → can eliminate enemies before they act
4. Warrior/Bruid/Monk ALWAYS go last → defensive/reactive only
5. Speed gap never closes despite leveling up

**Example:**
```
Level 1:  Mage (18) → Thief (10) → Bruid (9) → Warrior (7)
Level 10: Mage (18) → Thief (19) → Bruid (9) → Warrior (7)
```
The Mage's initial advantage never decreases, while Thief eventually overtakes her.

**Severity:** ⚠️ **HIGH** - Turn order dominance is a core game mechanic

---

### 🔴 CRITICAL: Enemy Scaling Almost Doesn't Exist

**The Problem:**
- **Only 2 out of ~35 enemies** use level scaling:
  - Alwynn (with scaling)
  - Red (with scaling)
  - All others have hardcoded, static stats
- Most enemies in Towns 1-2 have ~45-85 HP regardless of player level
- A Level 10 player can farm Level 1 enemies with zero challenge

**Impact Chain:**
1. Player can level infinitely on weak enemies
2. Weak enemies remain weak even at Level 15
3. No natural difficulty curve → either trivially easy or suddenly hard
4. Bosses with fixed stats become irrelevant if overleveled

**Example:**
```
FestivalMask (Town 1): 45 HP, 8 ATK, 25 XP (always)
→ Player Level 1: Challenging
→ Player Level 5: Trivial
→ Player Level 10: 1-shot kill
```

**Severity:** ⚠️ **CRITICAL** - Breaks progression difficulty curve

---

### 🔴 HIGH: Resource Starvation in Late Game

**The Problem:**
Skills have high resource costs, but resources grow slowly per level.

#### Thief Analysis:
- **Vanish skill costs 15 stamina** (primary defensive tool)
- **Stamina growth per level: +4** (only 25% skill cost)
- **Level 1:** 70 stamina → ~4-5 uses of Vanish per fight → OK
- **Level 10:** 106 stamina → ~7 uses → starts feeling thin
- **Level 15:** 130 stamina → ~8-9 uses → barely sustainable

#### Mage Analysis:
- **Pinagong Storm costs 15 MP** (heavy damage)
- **LambaShield costs 10 MP** (survival tool)
- **Mana growth per level: +10** (66% of one heavy ability)
- **Level 1:** 110 MP → 7 Pinagongs or mixed usage
- **Level 10:** 210 MP → 14 Pinagongs (sounds OK)
- **Problem:** Heavy skills cost 15 MP but Mana Surge only restores 25 MP → rigid rotation

#### Tagalog Monk Analysis:
- **Most expensive techniques cost 15 stamina**
- **Stamina growth: +4 per level** (same problem as Thief)
- **By Level 10:** Can't afford preferred skills

**Impact:** By mid-to-late game, players revert to basic attacks because resource management becomes punishing.

**Severity:** ⚠️ **HIGH** - Reduces skill expression and fun factor

---

### 🟠 HIGH: Stat Growth Inconsistency

**The Problem:**
Different classes scale at wildly different rates, creating disparities.

#### HP Growth Comparison:
| Class | Base HP | Growth/Level | Level 10 HP | Growth Rate |
|-------|---------|--------------|------------|-------------|
| Warrior | 135 | +14 | 275 | 2.04x |
| Bruid | 110 | +12 | 230 | 2.09x |
| Tagalog Monk | ~110 | +10 | 210 | 1.91x |
| Mage | 100 | +12 | 220 | 2.2x |
| Thief | 100 | +10 | 200 | 2.0x |

**Warrior's Edge:** Gets +14 HP/level while others get +10-12
- By Level 10: Warrior has 40-65 extra HP
- By Level 15: Warrior has 60-100 extra HP
- **Result:** Warrior becomes unkillable tank; others are fragile

**Thief's Defense Problem:** Defense growth only +0.5-2 per level
- Base: 6 defense
- Level 10: ~16 defense
- Warrior Level 10: ~28+ defense
- **Result:** Thief stays fragile despite leveling

**Severity:** ⚠️ **HIGH** - Creates unbalanced power progression

---

### 🟠 MEDIUM: Skill Cooldown vs. Resource Management Tension

**The Problem:**
Powerful survival/recovery skills have long cooldowns (2-3 turns), making them mandatory but predictable.

**Examples:**
- **Warrior's Second Wind:** 3-turn cooldown, massive heal (mandatory every 3 turns)
- **Mage's Mana Surge:** 2-turn cooldown, restore 25 MP (mandatory for spam)
- **Thief's Looter's Instinct:** 2-turn cooldown, self-sustain
- **Bruid's Mana Grove:** 2-turn cooldown, restore 25 MP

**Impact:**
- Battle becomes a rigid rotation: use skill → wait 2 turns → use skill
- No flexibility in tight situations
- Monsters learn the pattern easily
- Reduces tactical depth

**Severity:** 🟠 **MEDIUM** - Makes battles repetitive but not game-breaking

---

## Class Tier Analysis: Progression Through Game

### **Early Game (Levels 1-3)**

| Class | Viability | Reason |
|-------|-----------|--------|
| 🟢 **Warrior** | Strong | Tank role works; enemies aren't strong enough to bypass defense |
| 🟢 **Thief** | Strong | High speed + critical damage; dominates weak enemies |
| 🟡 **Mage** | Adequate | Glass cannon feels risky but high speed compensates |
| 🟡 **Bruid** | Adequate | Balanced but not exceptional; middle of the road |
| 🟡 **Tagalog Monk** | Adequate | Similar to Bruid; no clear advantage |

### **Mid Game (Levels 4-8)**

| Class | Viability | Reason |
|-------|-----------|--------|
| 🟢 **Warrior** | Excellent | Defense scaling makes enemies tickle; still survives |
| 🟡 **Thief** | Adequate | Stamina costs bite; Vanish becomes hard to use every turn |
| 🟡 **Mage** | Adequate | Resource pressure starts; big spells become luxury items |
| 🔴 **Bruid** | Weak | Hybrid approach means no true specialty; gets outclassed |
| 🔴 **Tagalog Monk** | Weak | Same problem as Bruid + slower than competitors |

### **Late Game (Levels 9-15)**

| Class | Viability | Reason |
|-------|-----------|--------|
| 🟢 **Warrior** | Excellent | Tank becomes immortal; simply outlasts enemies |
| 🔴 **Thief** | Poor | Can't afford Vanish + expensive skills; forced to basic attack |
| 🔴 **Mage** | Poor | MP starved; Pinagong Storm too expensive to use frequently |
| 🔴 **Bruid** | Poor | Hybrid scaling means scales with no one; weak everywhere |
| 🔴 **Tagalog Monk** | Poor | Stamina starvation identical to Thief |

---

## Would It Be Playable?

### ✅ Towns 1-2 (Early Game)
- **Yes, quite fun!** Classes feel distinct, all are viable
- Players experience unique playstyles
- Difficulty is appropriate

### 🟡 Towns 3-4 (Mid Game)
- **Starts having friction**
- Resource management becomes punishing
- Warrior starts outclassing others
- Enemies with fixed stats become trivial if overleveled

### ❌ Towns 5+ (Late Game)
- **Likely frustrating**
- Either trivial (if overleveled from easy farming)
- Or resource-starved (if keeping level appropriate)
- Warrior dominates; other classes struggle
- Cooldown rotation becomes mandatory, killing tactical depth

---

## Detailed Recommendations (Priority Order)

### 🎯 PRIORITY 1: Implement Comprehensive Enemy Scaling

**Status:** Already designed in your BALANCE_ANALYSIS_AND_RECOMMENDATIONS.md

**What needs to happen:**
- Apply `scaleToPlayer()` method to ALL enemies in constructor
- Ensure TownIndex is passed when enemies are spawned
- Test that enemies scale smoothly without jumps
- Verify exp rewards scale proportionally

**Expected outcome:**
- Consistent difficulty throughout game
- Cannot farm low-level enemies forever
- Late-game bosses stay challenging

---

### 🎯 PRIORITY 2: Add Speed Growth to All Classes

**Current:** Only Thief gets +1 SPD/level

**Proposed Changes:**
```
Warrior:      +1 speed per 2 levels (floor every other level-up)
Mage:         +0 speed per level (keep high base of 18, it's part of design)
Thief:        +1 speed per level (keep as-is, fits archetype)
Bruid:        +0.5 speed per level (slight improvement for support)
Tagalog Monk: +0.5 speed per level (moderate improvement)
```

**Result by Level 10:**
```
Warrior:      7 → 12 speed (+5)
Mage:         18 → 18 speed (unchanged, but high)
Thief:        10 → 19 speed (+9)
Bruid:        9 → 14 speed (+5)
Tagalog Monk: 8 → 13 speed (+5)
```

---

### 🎯 PRIORITY 3: Increase Resource Growth for Resource-Starved Classes

**Mage** (currently +10 mana/level):
```java
// Change from: setMp(getMp() + 10)
setMp(getMp() + 13);           // +30% increase
setMaxMp(getMaxMp() + 13);
```
**Reason:** Skills cost 10-15 mana; need better regeneration support

**Thief** (currently +4 stamina/level):
```java
// Change from: setStamina(getStamina() + 4)
setStamina(getStamina() + 6);          // +50% increase
setMaxStamina(getMaxStamina() + 6);
```
**Reason:** Vanish costs 15 stamina; can't afford it late game

**Tagalog Monk** (currently +4 stamina/level):
```java
// Same as Thief
setStamina(getStamina() + 6);
setMaxStamina(getMaxStamina() + 6);
```

---

### 🎯 PRIORITY 4: Rebalance Warrior HP Growth

**Current:** Warrior gets +14 HP/level (highest in game)

**Problem:** Makes Warrior immortal while others remain fragile

**Proposed Solution:**
```java
// Change from: setMaxHp(getMaxHp() + 14)
setMaxHp(getMaxHp() + 12);     // Match Mage/Bruid level growth
setHp(getHp() + 12);
```

**Result:** Warrior still tanks effectively but not unkillable

---

### 🎯 PRIORITY 5: Improve Thief Defense Scaling

**Current:** Thief gets +2 defense/level (tied for worst with Mage's +3, but Mage has lower base)

**Problem:** Thief uses dodge mechanic but low defense means damage still stacks

**Proposed Solution:**
```java
// Change from: setDefense(getDefense() + 2)
setDefense(getDefense() + 3);     // +50% improvement
```

**Result:** Thief's evasion is more meaningful when hits connect

---

## Summary Table: Recommended Changes

| Component | Current | Proposed | Reason |
|-----------|---------|----------|--------|
| **Enemy Scaling** | Most static | Apply to all | Progression difficulty |
| **Warrior HP Growth** | +14/level | +12/level | Reduce dominance |
| **Warrior Speed Growth** | +0/level | +1 per 2 levels | Less fragile to crit |
| **Mage Speed Growth** | +0/level | +0/level | Keep design intent |
| **Mage Mana Growth** | +10/level | +13/level | Spell affordability |
| **Thief Speed Growth** | +1/level | +1/level | Keep as-is |
| **Thief Stamina Growth** | +4/level | +6/level | Skill affordability |
| **Thief Defense Growth** | +2/level | +3/level | Reduce fragility |
| **Bruid Speed Growth** | +0/level | +0.5/level | Some scaling |
| **Tagalog Monk Speed Growth** | +0/level | +0.5/level | Some scaling |
| **Tagalog Monk Stamina Growth** | +4/level | +6/level | Skill affordability |

---

## Conclusion

Quezon Chronicles has **excellent thematic and mechanical foundations**, but balance issues will make **late-game frustrating**. The good news: **all issues are fixable** with targeted adjustments.

**Minimal changes** (implementing the 5 priorities above) would transform the game from "fun early, broken late" to "consistently engaging throughout."

**Estimated Implementation Time:** 2-3 hours (mostly testing and bug fixes)
