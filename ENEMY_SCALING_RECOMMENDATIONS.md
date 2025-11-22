# Enemy Scaling Recommendations & Base Stats Analysis

## Overview

This document provides **recommended base stats and scaling formulas** for all 31 enemies (excluding Eduard, Alwynn, Red, and Nell). The scaling is designed to match player progression through levels 1-15 across 5 towns.

---

## Player Class Baseline for Reference

### Average Player Stats by Level
```
Level 1:  HP: 100,   ATK: 16,  DEF: 8,   SPD: 10
Level 5:  HP: 150,   ATK: 22,  DEF: 11,  SPD: 12
Level 10: HP: 200,   ATK: 28,  DEF: 14,  SPD: 14
Level 15: HP: 250,   ATK: 34,  DEF: 17,  SPD: 16
```

**Combat Math:**
- Player damage to enemy: `playerAttackPower - enemyDefense`
- Expected duration: `enemyHP / (playerDamage * 0.8)` turns (accounting for variable rolls)
- **Target**: 4-8 turns per regular encounter, 8-12 turns per miniboss

---

## TOWN TIER STRUCTURE

```
TOWN 1 (Gumaca/Lucban)        → Level 1-3  → Tier 1 (1.0x)
TOWN 2 (Lopez/Lucena)         → Level 4-5  → Tier 2 (1.3x)
TOWN 3 (Calauag/Sariaya)      → Level 6-8  → Tier 3 (1.6x)
TOWN 4 (Infanta/Candelaria)   → Level 9-11 → Tier 4 (1.9x)
TOWN 5 (Real/Tiaong)          → Level 12-15→ Tier 5 (2.2x)
```

---

## CURRENT ENEMY ANALYSIS & RECOMMENDATIONS

### TIER 1 ENEMIES (Town 1 - Level 1-3)

#### Enemy: Festival Mask
**Current Stats:** HP: 45, ATK: 8, DEF: 2, SPD: 5
**Analysis:** OK baseline
**Recommendation:**
```
Base Stats (Level 1):
  HP: 45 (Keep - easy enemy)
  ATK: 8 (Keep)
  DEF: 2 (Keep - low armor fits squishy archetype)
  SPD: 5 (Keep)
  Exp: 25 (Keep)

Scaling Formula:
  HP = 45 + (playerLevel * 3)
  ATK = 8 + (playerLevel * 0.5)
  DEF = 2 (Fixed - no scaling)
  SPD = 5 (Fixed)

Expected at Level 5: HP: 60, ATK: 10.5
```
---

#### Enemy: Sirena (Gumacan Sirena)
**Current Stats:** HP: 45, ATK: 8, DEF: 2, SPD: 5
**Analysis:** Identical to Festival Mask (design issue)
**Recommendation:**
```
Base Stats (Level 1):
  HP: 45 (Keep)
  ATK: 8 (Keep)
  DEF: 2 (Keep)
  SPD: 5 (Keep)
  Exp: 35 (Keep)

Scaling Formula:
  Same as Festival Mask (standardize tier 1 regular enemies)
  
Note: Consider making Sirena slightly different later
```
---

#### Enemy: HabhabBandit
**Current Stats:** HP: 45, ATK: 8, DEF: 2, SPD: 5
**Analysis:** Tier 1 basic enemy
**Recommendation:**
```
Base Stats: HP: 45, ATK: 8, DEF: 2, SPD: 5, Exp: 30

Scaling Formula:
  HP = 45 + (playerLevel * 3)
  ATK = 8 + (playerLevel * 0.5)
  DEF = 2
  SPD = 5
```
---

#### Enemy: KipingGolem
**Current Stats:** HP: 45, ATK: 8, DEF: 2, SPD: 5
**Analysis:** Tier 1 basic enemy
**Recommendation:**
```
Base Stats: HP: 45, ATK: 8, DEF: 2, SPD: 5, Exp: 30

Scaling Formula: Same as above
```
---

#### Enemy: LanggonisaLord
**Current Stats:** HP: 45, ATK: 8, DEF: 2, SPD: 5
**Analysis:** Tier 1 basic enemy with burn debuff ability
**Recommendation:**
```
Base Stats: HP: 45, ATK: 8, DEF: 2, SPD: 5, Exp: 35

Scaling Formula: Same as above
```
---

#### Enemy: TanimGuardian
**Current Stats:** HP: 45, ATK: 8, DEF: 2, SPD: 5
**Analysis:** Tier 1 basic enemy
**Recommendation:**
```
Base Stats: HP: 45, ATK: 8, DEF: 2, SPD: 5, Exp: 25

Scaling Formula: Same as above
```
---

### TIER 2 ENEMIES (Town 2 - Level 4-5)

#### Enemy: LopezWolf
**Current Stats:** HP: 70, ATK: 14, DEF: 5, SPD: 8
**Analysis:** Good mid-tier stat progression
**Recommendation:**
```
Base Stats:
  HP: 70
  ATK: 14
  DEF: 5
  SPD: 8
  Exp: 50

Scaling Formula:
  HP = 70 + (playerLevel * 6)
  ATK = 14 + (playerLevel * 0.8)
  DEF = 5 + (playerLevel * 0.2)
  SPD = 8 (Fixed)

Expected at Level 5: HP: 100, ATK: 18, DEF: 6
```
---

#### Enemy: LucenaPirate
**Current Stats:** HP: 70, ATK: 14, DEF: 5, SPD: 8
**Analysis:** Identical to LopezWolf
**Recommendation:**
```
Base Stats: HP: 70, ATK: 14, DEF: 5, SPD: 8, Exp: 50

Scaling Formula: Same as LopezWolf
```
---

#### Enemy: PortRat
**Current Stats:** HP: 70, ATK: 14, DEF: 5, SPD: 8
**Analysis:** Tier 2 standard enemy
**Recommendation:**
```
Base Stats: HP: 70, ATK: 14, DEF: 5, SPD: 8, Exp: 40

Scaling Formula: Same as LopezWolf
```
---

#### Enemy: SumanMimic
**Current Stats:** HP: 70, ATK: 14, DEF: 5, SPD: 8
**Analysis:** Tier 2 with self-heal (2 HP/turn)
**Recommendation:**
```
Base Stats: HP: 70, ATK: 14, DEF: 5, SPD: 8, Exp: 40

Scaling Formula: Same as LopezWolf
(Note: Self-heal scales to 3-4 HP at higher levels - see ability note)
```
---

#### Enemy: HarborSentinel (MINIBOSS)
**Current Stats:** HP: 140, ATK: 20, DEF: 8, SPD: 6
**Analysis:** Tier 2 miniboss, significantly stronger than regulars
**Recommendation:**
```
Base Stats:
  HP: 140
  ATK: 20
  DEF: 8
  SPD: 6
  Exp: 150

Scaling Formula (Miniboss - different curve):
  HP = 140 + (playerLevel * 12)
  ATK = 20 + (playerLevel * 1.2)
  DEF = 8 + (playerLevel * 0.3)
  SPD = 6 (Fixed)

Expected at Level 5: HP: 200, ATK: 26, DEF: 10

Justification: Minibosses should be 2-2.5x harder than regular enemies
```

---

#### Enemy: OldTrainSpirit (MINIBOSS)
**Current Stats:** HP: 160, ATK: 28, DEF: 11, SPD: 5
**Analysis:** Strong miniboss with stun + charge damage (Town 2 boss)
**Recommendation:**
```
Base Stats:
  HP: 160
  ATK: 28
  DEF: 11
  SPD: 5
  Exp: 400

Scaling Formula (Miniboss - different curve):
  HP = 160 + (playerLevel * 16)
  ATK = 28 + (playerLevel * 1.5)
  DEF = 11 + (playerLevel * 0.4)
  SPD = 5 (Fixed)

Expected at Level 5: HP: 240, ATK: 36, DEF: 13

Justification: 2.5-3x harder than regular Tier 2 enemies
This should be challenging but possible at level 5-6
```
---

### TIER 3 ENEMIES (Town 3 - Level 6-8)

#### Enemy: CalauagBandit
**Current Stats:** HP: 85, ATK: 16, DEF: 6, SPD: 8
**Analysis:** Natural progression from Tier 2
**Recommendation:**
```
Base Stats:
  HP: 85
  ATK: 16
  DEF: 6
  SPD: 8
  Exp: 75

Scaling Formula:
  HP = 85 + (playerLevel * 8)
  ATK = 16 + (playerLevel * 1.0)
  DEF = 6 + (playerLevel * 0.25)
  SPD = 8 (Fixed)

Expected at Level 8: HP: 149, ATK: 24, DEF: 8
```
---

#### Enemy: CoconutBrigade
**Current Stats:** HP: 85, ATK: 16, DEF: 6, SPD: 8
**Analysis:** Tier 3 standard enemy
**Recommendation:**
```
Base Stats: HP: 85, ATK: 16, DEF: 6, SPD: 8, Exp: 75

Scaling Formula: Same as CalauagBandit
```
---

#### Enemy: FisherRogue
**Current Stats:** HP: 85, ATK: 16, DEF: 6, SPD: 8
**Analysis:** Tier 3 standard enemy
**Recommendation:**
```
Base Stats: HP: 85, ATK: 16, DEF: 6, SPD: 8, Exp: 60

Scaling Formula: Same as CalauagBandit
```
---

#### Enemy: HeritageWraith
**Current Stats:** HP: 85, ATK: 16, DEF: 6, SPD: 8
**Analysis:** Tier 3 with defense debuff ability
**Recommendation:**
```
Base Stats: HP: 85, ATK: 16, DEF: 6, SPD: 8, Exp: 70

Scaling Formula: Same as CalauagBandit

Ability Note: Defense debuff becomes -2 DEF at higher levels
```
---

#### Enemy: SeaWidow
**Current Stats:** HP: 85, ATK: 16, DEF: 6, SPD: 8
**Analysis:** Tier 3 with attack debuff (weakening)
**Recommendation:**
```
Base Stats: HP: 85, ATK: 16, DEF: 6, SPD: 8, Exp: 70

Scaling Formula: Same as CalauagBandit

Ability Note: Attack debuff becomes -3 ATK at higher levels
```
---

#### Enemy: BakeryGremlin
**Current Stats:** HP: 85, ATK: 16, DEF: 6, SPD: 8
**Analysis:** Tier 3 standard enemy
**Recommendation:**
```
Base Stats: HP: 85, ATK: 16, DEF: 6, SPD: 8, Exp: 60

Scaling Formula: Same as CalauagBandit
```
---

### TIER 4 ENEMIES (Town 4 - Level 9-11)

#### Enemy: BibingkaElemental
**Current Stats:** HP: 100, ATK: 21, DEF: 8, SPD: 9
**Analysis:** Good Tier 4 baseline
**Recommendation:**
```
Base Stats:
  HP: 100
  ATK: 21
  DEF: 8
  SPD: 9
  Exp: 85

Scaling Formula:
  HP = 100 + (playerLevel * 10)
  ATK = 21 + (playerLevel * 1.2)
  DEF = 8 + (playerLevel * 0.3)
  SPD = 9 (Fixed)

Expected at Level 11: HP: 210, ATK: 34, DEF: 12
```
---

#### Enemy: FlamingCandelarian
**Current Stats:** HP: 100, ATK: 21, DEF: 8, SPD: 9
**Analysis:** Tier 4 standard
**Recommendation:**
```
Base Stats: HP: 100, ATK: 21, DEF: 8, SPD: 9, Exp: 95

Scaling Formula: Same as BibingkaElemental
```
---

#### Enemy: ForestNymph
**Current Stats:** HP: 100, ATK: 21, DEF: 8, SPD: 9
**Analysis:** Tier 4 with self-heal ability (18 HP/turn)
**Recommendation:**
```
Base Stats: HP: 100, ATK: 21, DEF: 8, SPD: 9, Exp: 85

Scaling Formula: Same as BibingkaElemental

Ability Note: Self-heal becomes 25 HP at level 11, scales with enemy max HP
  Heal Formula: 0.18 * maxHP per turn (roughly)
```
---

#### Enemy: HoneyGuardian
**Current Stats:** HP: 100, ATK: 21, DEF: 8, SPD: 9
**Analysis:** Tier 4 with poison debuff and crit
**Recommendation:**
```
Base Stats: HP: 100, ATK: 21, DEF: 8, SPD: 9, Exp: 90

Scaling Formula: Same as BibingkaElemental

Ability Note: Poison debuff scaling remains at 2 damage/turn (fixed)
```
---

#### Enemy: InfantaShade
**Current Stats:** HP: 100, ATK: 21, DEF: 8, SPD: 9
**Analysis:** Tier 4 simple attacker
**Recommendation:**
```
Base Stats: HP: 100, ATK: 21, DEF: 8, SPD: 9, Exp: 95

Scaling Formula: Same as BibingkaElemental
```
---

#### Enemy: SweetVendor
**Current Stats:** HP: 100, ATK: 21, DEF: 8, SPD: 9
**Analysis:** Tier 4 with crit mechanic
**Recommendation:**
```
Base Stats: HP: 100, ATK: 21, DEF: 8, SPD: 9, Exp: 90

Scaling Formula: Same as BibingkaElemental
```
---

### TIER 5 ENEMIES (Town 5 - Level 12-15)

#### Enemy: PugonPhantom
**Current Stats:** HP: 120, ATK: 25, DEF: 10, SPD: 10
**Analysis:** Tier 5 regular with burn + stamina drain
**Recommendation:**
```
Base Stats:
  HP: 120
  ATK: 25
  DEF: 10
  SPD: 10
  Exp: 110

Scaling Formula:
  HP = 120 + (playerLevel * 12)
  ATK = 25 + (playerLevel * 1.4)
  DEF = 10 + (playerLevel * 0.4)
  SPD = 10 (Fixed)

Expected at Level 15: HP: 300, ATK: 46, DEF: 16

Ability Note: Stamina drain becomes 8 points at level 15
```
---

#### Enemy: TiaongKnight
**Current Stats:** HP: 120, ATK: 25, DEF: 10, SPD: 10
**Analysis:** Tier 5 with high crit (50%)
**Recommendation:**
```
Base Stats: HP: 120, ATK: 25, DEF: 10, SPD: 10, Exp: 120

Scaling Formula: Same as PugonPhantom
```
---

#### Enemy: WaveFiend
**Current Stats:** HP: 120, ATK: 25, DEF: 10, SPD: 10
**Analysis:** Tier 5 with attack debuff
**Recommendation:**
```
Base Stats: HP: 120, ATK: 25, DEF: 10, SPD: 10, Exp: 110

Scaling Formula: Same as PugonPhantom

Ability Note: Attack debuff becomes -4 ATK at level 15
```
---

#### Enemy: RealSpecter
**Current Stats:** HP: 120, ATK: 25, DEF: 10, SPD: 10
**Analysis:** Tier 5 with very high crit (50%)
**Recommendation:**
```
Base Stats: HP: 120, ATK: 25, DEF: 10, SPD: 10, Exp: 120

Scaling Formula: Same as PugonPhantom
```
---

### MINIBOSSES (Town 3, 4, 5 Bosses)

#### Enemy: OldTrainSpirit (Town 2 Boss)
**Current Stats:** HP: 160, ATK: 28, DEF: 11, SPD: 5
**Analysis:** Strong miniboss with stun + charge damage
**Recommendation:**
```
Base Stats:
  HP: 160
  ATK: 28
  DEF: 11
  SPD: 5
  Exp: 400

Scaling Formula (Miniboss curve):
  HP = 160 + (playerLevel * 16)
  ATK = 28 + (playerLevel * 1.5)
  DEF = 11 + (playerLevel * 0.4)
  SPD = 5 (Fixed - matches slow tank archetype)

Expected at Level 5: HP: 240, ATK: 36, DEF: 13

Justification: 2.5-3x harder than regular Tier 2 enemies
This should be challenging but possible at level 5-6
```
---

### MAJOR BOSSES (Final Town Bosses)

#### Enemy: DonMariano (Town 5 West Boss)
**Current Stats:** HP: 240, ATK: 28, DEF: 12, SPD: 6
**Analysis:** CRITICAL ISSUE - Fixed stats, should scale!
**Recommendation:**
```
Base Stats (for Level 10 player):
  HP: 240
  ATK: 28
  DEF: 12
  SPD: 6
  Exp: 2000

Scaling Formula (Boss - aggressive scaling):
  HP = 240 + (playerLevel * 20)
  ATK = 28 + (playerLevel * 2.0)
  DEF = 12 + (playerLevel * 0.5)
  SPD = 6 (Fixed)

Expected at Level 10: HP: 440, ATK: 48, DEF: 17
Expected at Level 15: HP: 540, ATK: 58, DEF: 19

Justification: Boss at ~Level 10 should have ~60-80% of player HP
but significantly higher attack power
Turn duration: 8-12 turns expected

Abilities:
  - Greed's Flame: Base damage + 50% burn chance
  - Life Drain: Absorbs 50% of player damage as healing
```
---

#### Enemy: QueenAmihan (Town 5 East Boss)
**Current Stats:** HP: 240, ATK: 28, DEF: 12, SPD: 20
**Analysis:** Final boss, should be most challenging
**Recommendation:**
```
Base Stats (for Level 15 player):
  HP: 240
  ATK: 28
  DEF: 12
  SPD: 20
  Exp: 3000

Scaling Formula (Final Boss - very aggressive):
  HP = 240 + (playerLevel * 24)
  ATK = 28 + (playerLevel * 2.2)
  DEF = 12 + (playerLevel * 0.6)
  SPD = 20 (FIXED - Queen is inherently fast)

Expected at Level 10: HP: 480, ATK: 50, DEF: 18
Expected at Level 15: HP: 600, ATK: 61, DEF: 21

Justification: Final boss should be approximately as tough as 
the toughest enemy encountered, with higher skill requirements

Abilities:
  - Wave Crash: Base damage + self-heal (50% of damage dealt)
  - Fast attack speed makes her more dangerous than Don Mariano
```
---

## SCALING IMPLEMENTATION GUIDE

### Generic Scaling Function (for regular enemies)

```java
public void scaleToPlayerLevel(Player player) {
    int playerLevel = player.getLevel();
    double townTier = getTownTier(); // 1.0, 1.3, 1.6, 1.9, 2.2
    
    // Recalculate stats based on base values and level
    int scaledHP = (int)(baseMaxHp + (playerLevel * hpGrowthPerLevel * townTier));
    int scaledATK = (int)(baseAttackPower + (playerLevel * atkGrowthPerLevel * townTier));
    int scaledDEF = (int)(baseDefense + (playerLevel * defGrowthPerLevel * townTier));
    
    setMaxHp(scaledHP);
    setHp(scaledHP);
    setAttackPower(scaledATK);
    setDefense(scaledDEF);
    // SPD typically doesn't scale
}
```

### Town Tier Modifiers

```java
private double getTownTier() {
    // Must be set based on which town this enemy appears in
    switch(currentTown) {
        case "Gumaca":
        case "Lucban":
            return 1.0;  // Tier 1
        case "Lopez":
        case "Lucena":
            return 1.3;  // Tier 2
        case "Calauag":
        case "Sariaya":
            return 1.6;  // Tier 3
        case "Infanta":
        case "Candelaria":
            return 1.9;  // Tier 4
        case "Real":
        case "Tiaong":
            return 2.2;  // Tier 5
        default:
            return 1.0;
    }
}
```

---

## ABILITY SCALING RECOMMENDATIONS

### Debuff Effects (Scale with level)

| Ability | Current | Recommended at Level 15 |
|---------|---------|------------------------|
| Burn damage | 2 HP/turn | 3-4 HP/turn |
| Poison damage | 2 HP/turn | 3-4 HP/turn |
| Defense down | -1 DEF | -2 to -3 DEF |
| Attack down | -2 ATK | -3 to -4 ATK |
| Stun | 1 turn | 1 turn (fixed) |
| Confusion | N/A | 1 turn (fixed) |

**Implementation:**
```java
public void applyDebuffEffect(String debuff) {
    int debuffScaleFactor = (int)(1 + player.getLevel() / 10.0);
    
    switch(debuff.toLowerCase()) {
        case "burn":
            int burnDamage = 2 * debuffScaleFactor;
            takeDamage(burnDamage);
            break;
        // ... etc
    }
}
```

### Self-Heal Abilities

| Enemy | Current | Level 15 Target |
|-------|---------|-----------------|
| ForestNymph | 18 HP/turn | 25 HP/turn (0.25 * max HP) |
| SumanMimic | 2 HP/turn | 4 HP/turn |
| HoneyGuardian | N/A | 3 HP/turn (if added) |
| DonMariano (drain) | 50% of player ATK | Remains 50% |
| QueenAmihan (heal) | 50% of damage dealt | Remains 50% |

---

## SUMMARY TABLE: Complete Scaling Recommendations

| Enemy | Tier | Base HP | Base ATK | HP/Lvl | ATK/Lvl | DEF/Lvl | Role |
|-------|------|---------|----------|--------|---------|---------|------|
| **Festival Mask** | 1 | 45 | 8 | +3 | +0.5 | 0 | Debuffer |
| **Sirena** | 1 | 45 | 8 | +3 | +0.5 | 0 | Debuffer |
| **HabhabBandit** | 1 | 45 | 8 | +3 | +0.5 | 0 | Regular |
| **KipingGolem** | 1 | 45 | 8 | +3 | +0.5 | 0 | Regular |
| **LanggonisaLord** | 1 | 45 | 8 | +3 | +0.5 | 0 | Debuffer |
| **TanimGuardian** | 1 | 45 | 8 | +3 | +0.5 | 0 | Regular |
| **LopezWolf** | 2 | 70 | 14 | +6 | +0.8 | +0.2 | Regular |
| **LucenaPirate** | 2 | 70 | 14 | +6 | +0.8 | +0.2 | Regular |
| **PortRat** | 2 | 70 | 14 | +6 | +0.8 | +0.2 | Regular |
| **SumanMimic** | 2 | 70 | 14 | +6 | +0.8 | +0.2 | Self-Healer |
| **HarborSentinel** | 2 | 140 | 20 | +12 | +1.2 | +0.3 | Miniboss |
| **CalauagBandit** | 3 | 85 | 16 | +8 | +1.0 | +0.25 | Regular |
| **CoconutBrigade** | 3 | 85 | 16 | +8 | +1.0 | +0.25 | Regular |
| **FisherRogue** | 3 | 85 | 16 | +8 | +1.0 | +0.25 | Regular |
| **HeritageWraith** | 3 | 85 | 16 | +8 | +1.0 | +0.25 | Debuffer |
| **SeaWidow** | 3 | 85 | 16 | +8 | +1.0 | +0.25 | Debuffer |
| **BakeryGremlin** | 3 | 85 | 16 | +8 | +1.0 | +0.25 | Regular |
| **BibingkaElemental** | 4 | 100 | 21 | +10 | +1.2 | +0.3 | Regular |
| **FlamingCandelarian** | 4 | 100 | 21 | +10 | +1.2 | +0.3 | Regular |
| **ForestNymph** | 4 | 100 | 21 | +10 | +1.2 | +0.3 | Self-Healer |
| **HoneyGuardian** | 4 | 100 | 21 | +10 | +1.2 | +0.3 | Debuffer |
| **InfantaShade** | 4 | 100 | 21 | +10 | +1.2 | +0.3 | Regular |
| **SweetVendor** | 4 | 100 | 21 | +10 | +1.2 | +0.3 | Regular |
| **OldTrainSpirit** | 2-Boss | 160 | 28 | +16 | +1.5 | +0.4 | Miniboss |
| **PugonPhantom** | 5 | 120 | 25 | +12 | +1.4 | +0.4 | Debuffer |
| **TiaongKnight** | 5 | 120 | 25 | +12 | +1.4 | +0.4 | Crit |
| **WaveFiend** | 5 | 120 | 25 | +12 | +1.4 | +0.4 | Debuffer |
| **RealSpecter** | 5 | 120 | 25 | +12 | +1.4 | +0.4 | Crit |
| **DonMariano** | 5-Boss | 240 | 28 | +20 | +2.0 | +0.5 | Boss |
| **QueenAmihan** | 5-Boss | 240 | 28 | +24 | +2.2 | +0.6 | Final Boss |

---

## COMPARISON: BEFORE vs AFTER

### Before (Current)
```
Level 1 vs Town 1 Enemy (Lv1):  5-6 turns ✓ OK
Level 3 vs Town 2 Enemy (Fixed):3-4 turns ✓ Too easy
Level 5 vs Town 2 Enemy (Fixed):1-2 turns ✗ TRIVIAL
Level 8 vs Town 3 Enemy (Fixed):4-5 turns ✓ OK
Level 12 vs Boss (Fixed):       Instant death or 1-2 turns ✗ BROKEN
```

### After (Proposed)
```
Level 1 vs Town 1 Enemy (Lv1):  5-6 turns ✓ OK
Level 3 vs Town 2 Enemy (Lv3):  5-6 turns ✓ CONSISTENT
Level 5 vs Town 2 Enemy (Lv5):  6-7 turns ✓ BALANCED
Level 8 vs Town 3 Enemy (Lv8):  6-8 turns ✓ CHALLENGING
Level 12 vs Boss (Lv12):        10-12 turns ✓ ENGAGING
```

---

## IMPLEMENTATION CHECKLIST

- [ ] Create `baseHP`, `baseATK`, `baseDEF`, `baseSpeed` fields in Enemy base class
- [ ] Create `hpGrowthPerLevel`, `atkGrowthPerLevel`, `defGrowthPerLevel` fields
- [ ] Update each enemy constructor to set base values (from this table)
- [ ] Add `getTownTier()` method to determine tier multiplier
- [ ] Call `scaleToPlayerLevel(player)` in enemy constructor after base stats set
- [ ] Test at Level 1 vs Town 1: Should be 5-7 turns
- [ ] Test at Level 5 vs Town 2: Should be 6-8 turns
- [ ] Test at Level 10 vs Town 3: Should be 7-9 turns
- [ ] Test at Level 15 vs Boss: Should be 10-14 turns
- [ ] Adjust growth rates if turns are off by >2 turns from target
- [ ] Verify debuff abilities scale appropriately
- [ ] Verify self-heal abilities are challenging but not unkillable

---

## NOTES FOR DEVELOPERS

1. **Speed never scales** - Turn order should be deterministic based on base speed
2. **Minibosses scale 25-30% more than regular enemies** - They should feel noticeably harder
3. **Final bosses scale most aggressively** - They're the climactic fight
4. **Ability damage is NOT affected by these stats** - Debuff damage (burn, poison) remains flat unless explicitly adjusted
5. **Defense reduces incoming damage** - Formula: `damageTaken = max(0, incomingDamage - defense)`
6. **Critical hits still apply** - 15-50% crit chance per enemy, still 2x damage multiplier
7. **Excluded enemies (Eduard, Alwynn, Red, Nell) keep current behavior** - Don't modify them

---

## FUTURE BALANCE PASSES

After 1-2 weeks of testing:
- [ ] Analyze average turn counts per town from player data
- [ ] If too easy (<5 turns avg), increase all growth rates by 10%
- [ ] If too hard (>12 turns avg), decrease all growth rates by 10%
- [ ] Monitor boss win rates - should be ~60-70% at appropriate level
- [ ] Consider difficulty modifiers for New Game+ or difficulty settings

