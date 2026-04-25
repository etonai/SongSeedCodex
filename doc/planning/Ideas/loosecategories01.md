# 🎤 SongSeed – Loose Rhymes (Design Overview)

## 1. Top-Level Category

**Loose Rhymes**

> Explore rhymes that feel connected without being obvious or perfect.

---

## 2. Subcategory Selection Screen

When the user taps **Loose Rhymes**, show:

- **Default**
- **All**
- **Hard Ending Shift** *(first new subcategory)*

### Behavior

- **Default**
    - Uses the existing **Slant Rhyme** behavior already in the app

- **All**
    - Pulls from all Loose Rhymes subcategories (current + future)

- **Hard Ending Shift**
    - Opens a description screen

---

## 3. Subcategory: Hard Ending Shift

### Description Screen

**Title:**  
Hard Ending Shift

**Description:**
> These rhymes keep a similar vowel sound, but change the ending so the rhyme feels related without sounding exact.
>
> Avoid words that are too close (like *cod → cot*), and avoid perfect rhymes (like *find → kind*).
>
> Good loose rhymes feel like they belong together, but still surprise you.

**Example pairs:**
- cod → pot
- bad → cat
- bed → step

**CTA Button:**  
👉 **Start Drill**

---

## 4. Drill Screen (Core Interaction)

### Layout

**Main Word (center):**  
`cod`

---

### Buttons (only two)

- **Show Example**
- **Next Word**

---

### Behavior

#### Show Example
- Reveals **one example only**
- Each word has a single predefined example pair

Example flow:
- cod  
  → *(tap)* pot

---

#### Next Word
- Advances to a new prompt word
- Resets example state

---

## 5. Design Principles

- No correctness checking
- No required number of responses
- User participates mentally or vocally
- Examples define the quality bar
- One example per word keeps interaction fast and focused

---

## 6. Starter Dataset

Each prompt word maps to exactly **one example**.

---

bad → cat  
mad → map

bed → step  
led → set

bid → tip  
rid → kit

bod → pot  
rod → lot

bud → cup  
mud → cut

pad → tap  
fed → pet

lid → lip  
sod → top

had → cap  
red → bet

hid → hip  
pod → pop

dud → pup  
cud → cut

made → take  
said → set

code → coat  
road → note

side → time  
gold → goad

wide → white  
late → lake

---

## 🚀 Next Step

Once this feels good in use:
- Remove weaker pairs
- Add additional Loose Rhymes subcategories
- Expand dataset variety
- Refine based on real usage feel