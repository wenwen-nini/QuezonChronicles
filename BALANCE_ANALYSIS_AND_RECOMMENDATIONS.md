# Quezon Chronicles: Game Balance Analysis & Scaling Recommendations

## Executive Summary

The game currently has **5 player classes** and **35+ enemy subclasses** distributed across **10 towns** (5 per path). The balance system has potential but requires comprehensive scaling mechanisms to ensure progression feels natural through level-ups and town transitions.

**Current Status:** Mixed - Some enemies use level-based scaling (Alwynn, Red), but most don't. Player classes have inconsistent stat progression curves.

---

## PLAYER CLASS ANALYSIS

### Class Statistics Comparison

| Class | Max HP | Resource | Attack | Defense | Speed | Resource Cap | Role |
|-------|--------|----------|--------|---------|-------|--------------|------|
| **Warrior** | 140 | Stamina 50 | 16 | 12 | 5 | 50 | Tank/Bruiser |
| **Mage** | 70 | Mana 120 | 14 | 6 | 18 | 120 | Glass Cannon/DPS |
| **Thief** | 85 | Stamina 60 | 18 | 5 | 14 | 60 | Speed/Crit DPS |
| **Bruid** | 100 | Mana 80 | 14 | 8 | 6 | 80 | Support/Hybrid |
| **Tagalog Monk** | 110 | Stamina 50 | 18 | 8 | 8 | 50 | Balanced Melee |

### Level-Up Progression Analysis

| Class | HP Growth | Resource Growth | ATK Growth | DEF Growth | SPD Growth |
|-------|-----------|-----------------|------------|------------|-----------|
| Warrior | +12 | +5 (Stamina) | +2 | +2 | +0 |
| Mage | +6 | +10 (Mana) | +3 | +1 | +0 |
| Thief | +7 | +4 (Stamina) | +3 | +1 | +1 ✓ |
| Bruid | +9 | +7 (Mana) | +2 | +2 | +0 |
| Tagalog Monk | +10 | +4 (Stamina) | +3 | +1 | +0 |

### Current Issues

#### 1. **Inconsistent Speed Scaling**
- **Problem:** Only Thief gets +1 speed per level; all others get +0
- **Impact:** Mage starts with +18 speed (highest) but never improves. Over time, slower classes don't catch up naturally, creating speed meta problems
- **Severity:** HIGH - Speed affects turn order in battle; this is crucial

#### 2. **Resource/Stat Imbalance**
- **Warrior**: HP grows faster than any other class (+12), but resource (Stamina) is modest
  - Pro: Tank role feels good early
  - Con: Doesn't scale into late game; becomes a sponge
  
- **Mage**: Lowest HP growth (+6) makes glass cannon feeling worse over time
  - Pro: High Mana pool (+120 base)
  - Con: Takes increasingly longer to kill, dies in 2-3 hits at high levels
  
- **Thief**: Moderate growth across the board (+3 ATK, +1 SPD)
  - Pro: Only class with speed growth; high base attack
  - Con: Low defense (+1 growth) - stays fragile forever
  
- **Bruid**: Hybrid middle ground
  - Pro: Well-balanced moderate growth
  - Con: Never excels at anything; "jack of all trades" trap
  
- **Tagalog Monk**: Highest base stamina resource but weak stamina growth
  - Pro: High base HP (+110)
  - Con: Stamina grows only +4; skills become bottlenecked

**Severity:** HIGH - Creates class imbalance where some classes scale better than others

#### 3. **Skill Cooldown vs. Resource Management**
- Most classes have one "long cooldown" skill (3-4 turns) that's extremely powerful
  - **Warrior**: Second Wind (3 turn cooldown) - massive heal
  - **Mage**: Mana Surge (2 turn cooldown) - restore 25 MP
  - **Thief**: Looter's Instinct (2 turn cooldown) - self-sustain
  - **Bruid**: Mana Grove (2 turn cooldown) - restore 25 MP
  - **Tagalog Monk**: Dasal ng Katahimikan (2 turn cooldown) - heal + defense boost

- Problem: These are MANDATORY for survival in tough fights, but the cooldown system forces rigid rotation patterns

**Severity:** MEDIUM - Makes battles feel repetitive; reduces skill expression

---

## ENEMY ANALYSIS

### Enemy Type Categorization

#### **Tier 1: Early Game (Towns 1-2)**
- **Expected Level Range:** 1-3
- **Scaling:** Most have NO scaling (static stats)
- **Examples:** FestivalMask (45 HP, 8 ATK), Sirena (45 HP, 8 ATK)
- **Issue:** Players can out-level content permanently with bad balance

#### **Tier 2: Mid Game (Towns 3-4)**
- **Expected Level Range:** 4-6
- **Scaling:** Mixed - most static, some level-scaled
- **Examples:** BakeryGremlin (85 HP, 16 ATK), HeritageWraith (85 HP, 16 ATK)
- **Issue:** Difficulty spike between towns is not managed properly

#### **Tier 3: Boss Enemies (Every 5th town + final)**
- **Expected Level Range:** Varies
- **Scaling:** Boss-specific
- **Examples:** 
  - DonMariano (240 HP, 28 ATK, 2000 XP) - FIXED stats
  - QueenAmihan (240 HP, 28 ATK, 3000 XP) - FIXED stats
  - HarborSentinel - UNKNOWN (not examined)

#### **Special Cases: Level-Scaled Enemies**
- **Alwynn**: `HP = 85 * (playerLevel / 4)`, `ATK = 10 * (playerLevel / 4)`
- **Red**: `HP = 85 * (playerLevel / 4)`, `ATK = 16 * (playerLevel / 4)`

**Major Issue:** Only 2 out of 35+ enemies use level scaling! This is a CRITICAL problem.

### Enemy Difficulty Analysis

| Enemy | HP | ATK | DEF | Speed | Boss? | Exp | Scaling |
|-------|----|----|-----|-------|-------|-----|---------|
| FestivalMask | 45 | 8 | 2 | 5 | No | 25 | ❌ None |
| Sirena | 45 | 8 | 2 | 5 | No | 35 | ❌ None |
| BakeryGremlin | 85 | 16 | 6 | 8 | No | 60 | ❌ None |
| HeritageWraith | 85 | 16 | 6 | 8 | No | 70 | ❌ None |
| Alwynn | 85×(L/4) | 10×(L/4) | 6 | 8 | No | 350 | ✓ Level |
| Red | 85×(L/4) | 16×(L/4) | 6 | 8 | No | 500 | ✓ Level |
| DonMariano | 240 | 28 | 12 | 6 | Yes | 2000 | ❌ None |
| QueenAmihan | 240 | 28 | 12 | 20 | Yes | 3000 | ❌ None |

---

## SCALING TYPE RECOMMENDATIONS

### Recommended Scaling Model: **Progressive Difficulty Curve**

The game has **10 towns** and should feature **natural progression** where:
- Players naturally level from 1→15+ over the campaign
- Each town poses the same **relative difficulty** to the player at that point
- Enemies should have **perceived power growth** even if using the same AI

### 📊 Proposed Difficulty Progression

```
TOWN 1 (Gumaca/Lucban)     → Level 1-3  → Early Game
TOWN 2 (Lopez/Lucena)      → Level 4-5  → Early-Mid Game
TOWN 3 (Calauag/Sariaya)   → Level 6-8  → Mid Game
TOWN 4 (Infanta/Candelaria)→ Level 9-11 → Late Mid Game
TOWN 5 (Real/Tiaong)       → Level 12-15→ Late Game + Boss
```

---

## DETAILED RECOMMENDATIONS

### 🎯 RECOMMENDATION #1: Implement Comprehensive Enemy Scaling

**Problem:** 97% of enemies don't scale with player level

**Solution:** Create an Enemy Scaling System

```java
// In Enemy.java - Add scaling method
public void scaleToPlayerLevel(Player player) {
    double levelScaler = player.getLevel() / 4.0;
    
    // Apply town tier multiplier
    double townMultiplier = getTownTier();
    
    // Scale stats proportionally
    double finalScaler = levelScaler * townMultiplier;
    
    setMaxHp((int)(baseMaxHp * finalScaler));
    setHp(getMaxHp());
    setAttackPower((int)(baseAttackPower * finalScaler));
    setDefense((int)(baseDefense * finalScaler));
}

// Town tier modifiers (example)
// Tier 1 (Gumaca/Lucban): 1.0x
// Tier 2 (Lopez/Lucena): 1.3x
// Tier 3 (Calauag/Sariaya): 1.6x
// Tier 4 (Infanta/Candelaria): 1.9x
// Tier 5 (Real/Tiaong): 2.2x
```

**Benefits:**
- Content remains challenging throughout
- Prevents over-leveling from breaking difficulty
- Creates consistent "time to kill" across all enemies
- Bosses still need special handling but feel more climactic

**Implementation:** Add `baseMaxHp`, `baseAttackPower`, `baseDefense` fields to Enemy; scale in constructor based on player level and town

---

### 🎯 RECOMMENDATION #2: Fix Player Class Speed Scaling

**Problem:** Only Thief gets speed growth; Mage has massive early advantage that never decreases

**Solution:** Give every class incremental speed growth

```java
// Proposed Speed Growth Per Level-Up:
Warrior:      +0.5 → +1 per 2 levels (tank learns positioning)
Mage:         +0.2 per 2 levels (slight offset for high base speed)
Thief:        +1.0 per level (keep as-is, fits the archetype)
Bruid:        +0.3 per level (support needs some speed too)
Tagalog Monk: +0.5 per level (balanced growth)
```

**Why this works:**
- Slower classes gradually speed up but never catch Thief
- Mage's advantage decays but remains (high speed is part of identity)
- Warrior gains speed as they become more experienced (thematic)
- Everyone has some progression (better than +0)

---

### 🎯 RECOMMENDATION #3: Rebalance Resource Growth to Match Scaling

**Problem:** Some classes' resources don't grow with their skill costs; late-game resource starvation

**Current Issue Analysis:**
- **Warrior**: Needs 5 stamina for Cleave; grows +5 per level → OK
- **Mage**: Skills cost 10-18 mana; only grows +10 mana → PROBLEM (expensive skills starved late game)
- **Thief**: Skills cost 10-15 stamina; grows +4 stamina → PROBLEM (can't afford Vanish after 3 levels)
- **Bruid**: Skills cost 10-15 mana; grows +7 mana → OK
- **Monk**: Skills cost 10-15 stamina; grows +4 stamina → PROBLEM

**Solution: Adjust Resource Growth**

```java
// Updated levelStats() for each class:

// Mage: Increase mana growth for spell spam viability
setMp(getMp() + 14);           // was +10
setMaxMp(getMaxMp() + 14);     // was +10

// Thief: Increase stamina for skill affordability
setStamina(getStamina() + 6);  // was +4
setMaxStamina(getMaxStamina() + 6); // was +4

// Tagalog Monk: Increase stamina for technique use
setStamina(getStamina() + 6);  // was +4
setMaxStamina(getMaxStamina() + 6); // was +4
```

**Result:** By level 10, all classes can use their signature moves without starvation

---

### 🎯 RECOMMENDATION #4: Normalize Base Stats & Create Class Identity

**Problem:** Stat distribution seems ad-hoc; some classes lack clear strengths

**Solution: Clear Identity Matrix**

```
WARRIOR (Tank)
  Base: HP:140↑ STA:50 ATK:16 DEF:12↑ SPD:5
  Growth: HP+12, STA+5, ATK+2, DEF+2, SPD+0.5/2levels
  Identity: "Highest HP & DEF, sustains through stamina management"

MAGE (Glass Cannon)
  Base: HP:70↓ MAN:120↑ ATK:14 DEF:6↓ SPD:18↑
  Growth: HP+6, MAN+14↑, ATK+3↑, DEF+1, SPD+0.2/2levels
  Identity: "Highest damage output, lowest durability, highest speed"

THIEF (Mobility DPS)
  Base: HP:85 STA:60↑ ATK:18↑ DEF:5↓ SPD:14
  Growth: HP+7, STA+6↑, ATK+3, DEF+1, SPD+1
  Identity: "Highest base ATK, best speed growth, fragile"

BRUID (Support)
  Base: HP:100 MAN:80 ATK:14 DEF:8 SPD:6
  Growth: HP+9, MAN+7↑, ATK+2, DEF+2, SPD+0.3
  Identity: "Balanced everything, defensive stance, mana-based support"

TAGALOG MONK (Balanced Striker)
  Base: HP:110 STA:50 ATK:18 DEF:8 SPD:8
  Growth: HP+10, STA+6↑, ATK+3, DEF+1, SPD+0.5
  Identity: "High base HP and ATK, balanced defense, disciplined fighter"
```

---

### 🎯 RECOMMENDATION #5: Implement Boss Scaling

**Problem:** Bosses (DonMariano, QueenAmihan) have FIXED stats (240 HP, 28 ATK) regardless of player level

**Solution: Boss Tier System**

```java
// In each Boss class:
public Boss(Player player) {
    double levelScaler = player.getLevel() / 12.0; // Bosses are 12x harder than minions
    double tierMultiplier = getBossTier(); // Town-specific modifier
    
    double finalMultiplier = Math.min(levelScaler, 3.0); // Cap at 3x for balance
    
    setMaxHp((int)(baseMaxHp * finalMultiplier));
    setAttackPower((int)(baseAttackPower * finalMultiplier));
    setDefense((int)(baseDefense * finalMultiplier));
}

// Boss Tiers:
// Town 2 Boss (HarborSentinel): 1.2x multiplier
// Town 5 Boss (DonMariano/QueenAmihan): 1.5x multiplier

// CURRENT PROBLEM: If player reaches level 15, Boss at level ~5 was set to:
// HP: 240, ATK: 28 (instant loss)
```

**Alternative Approach (if you want fixed bosses):**
Instead of scaling stats, scale their ABILITIES:
- Boss uses more powerful skills at higher levels
- Boss uses skills more frequently
- Boss calls minions to assist
- Boss gains new mechanics (phase 2, etc.)

---

### 🎯 RECOMMENDATION #6: Improve Cooldown/Resource Mechanics

**Problem:** Cooldown skills feel mandatory and create predictable rotations

**Current:** "Wait 2-3 turns for big heal, then use immediately" → repetitive

**Solution A: Dynamic Cooldown Reduction**
```java
// Cooldown reduces as player levels up
baseSkillCooldown = 3;
effectiveSkillCooldown = baseSkillCooldown - (playerLevel / 5); // Level 5 = 2 turns, Level 10 = 1 turn
```

**Solution B: Cooldown Refresh Through Other Actions**
```java
// Hitting enemies with basic attacks reduces cooldown by 1 turn
// Tanks get cooldown reduction from defensive actions
// Mages get cooldown reduction from spell casts
```

**Solution C: Add "Desperate" versions of skills**
```java
// Warrior's "Desperate Slash": Use Slash if Second Wind on cooldown → gains 30% attack that turn
// Makes the "waiting period" interactive instead of dead time
```

---

### 🎯 RECOMMENDATION #7: Town-Specific Difficulty Scaling

**Current:** Enemies in Town 3 have same stats regardless of player level

**Solution: Contextual Enemy Variation**

Create 3 versions of each regular enemy:
```
Tier 1 (Base):   "Bakery Gremlin" - standard stats
Tier 2 (Mid):    "Veteran Bakery Gremlin" - +30% stats
Tier 3 (Hard):   "Ancient Bakery Gremlin" - +60% stats

Randomly select tier based on:
- Current player level vs. expected town level
- How many enemies have been defeated
- Cumulative difficulty
```

---

## IMPLEMENTATION PRIORITY

### 🔴 CRITICAL (Do First)
1. **Implement Enemy Level Scaling** - Without this, content is unbalanced
2. **Fix Boss Stats** - Prevent instant-deaths or trivial wins
3. **Normalize Class Speed Growth** - Turn order is fundamental to balance

### 🟠 HIGH PRIORITY (Do Next)
4. Fix Resource Growth (Mage, Thief, Monk stamina)
5. Implement Boss Tier System
6. Create scaling formulas document

### 🟡 MEDIUM PRIORITY (Polish)
7. Add Town-Specific Difficulty Variations
8. Improve Cooldown System (see Recommendation #6)
9. Balance Skill Costs vs. Resources

### 🟢 OPTIONAL (Nice-to-Have)
10. Advanced mechanics (dynamic cooldowns, desperation moves)
11. Per-enemy special scaling rules
12. Dynamic difficulty adjustment based on win/loss ratio

---

## TESTING CHECKLIST

After implementing these changes:

- [ ] Player reaches Level 10 with all classes → Can still afford all signature moves?
- [ ] Play through each town with different classes → Feeling consistent difficulty?
- [ ] Defeat boss at recommended level (5, 10, 15) → Does it feel balanced?
- [ ] Play underleveled (at level 3 vs. level 5 town) → Is it hard but fair?
- [ ] Play overleveled (at level 10 vs. level 5 town) → Can still win in <5 turns?
- [ ] Track average turn counts per town → Should be consistent (e.g., 6-8 turns average)

---

## SUMMARY TABLE: Proposed Changes

| Element | Current | Proposed | Impact |
|---------|---------|----------|--------|
| Enemy Scaling | 2/35 enemies scale | All enemies scale to player level | **HIGH** - Fixes progression |
| Boss Stats | Fixed (240 HP) | Scale 1.0x-3.0x based on level | **HIGH** - Prevents trivial/impossible |
| Class Speeds | Warrior +0, Mage +0, etc | +0.5-1.0 per level all classes | **HIGH** - Fix turn order |
| Mana Growth | Mage +10 | Mage +14 | **MEDIUM** - Spell viability |
| Stamina Growth | Thief +4, Monk +4 | Thief +6, Monk +6 | **MEDIUM** - Skill affordability |
| Resource Scaling | Per-level flat | Scale with town tier multipliers | **MEDIUM** - Future-proofing |

---

## CONCLUSION

The Quezon Chronicles has **excellent thematic design** and **well-defined classes**. The missing piece is **scalable difficulty**. By implementing:

1. Universal enemy level scaling
2. Dynamic boss stats  
3. Consistent speed growth
4. Resource balancing per class

...the game will feel like a **proper RPG progression** where players grow stronger at the same rate as challenges, creating satisfying, engaging combat throughout all 10 towns.

The changes are **modular** — implement them one at a time and test each component independently.
