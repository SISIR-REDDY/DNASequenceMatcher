# 🧬 DNA Sequence Matcher using Burrows-Wheeler Transform (BWT)

This project efficiently matches DNA sequences and detects mutation patterns using advanced string matching techniques like the **Burrows-Wheeler Transform (BWT)** algorithm. It is aimed at bioinformatics applications such as genome sequencing, alignment, and mutation analysis.

---

## 📌 Table of Contents

1. Introduction
2. Problem Statement
3. Background
4. Solution Approach
5. Algorithms Used
6. Project Structure
7. Author - SISIR-REDDY (github-user acc)

---

## 📖 Introduction

DNA sequencing is the process of determining the exact order of nucleotides (A, C, G, T) in a DNA molecule. Efficient string matching techniques are essential for comparing DNA sequences, identifying mutations, and aligning genomes.

This project uses the "Burrows-Wheeler Transform (BWT)", a powerful algorithm used in bioinformatics to compress and quickly search DNA sequences.

---

## ❓ Problem Statement

1. Given a long DNA sequence, efficiently **match a target pattern** (substring) within the sequence.
2. Detect the **occurrence positions** of a specific mutation pattern.
3. Display total number of times the mutation occurs.

---

## 🌐 Background

- **DNA Sequence:** A string composed of characters A, C, G, T representing nucleotide bases.
- **Mutation:** A change or pattern (like "CGT") occurring at various positions in the DNA sequence.
- **String Matching Challenge:** Traditional string matching algorithms like Naive Search or KMP are good, but **BWT + Backward Search** offers better performance, especially for large sequences.

---

## 💡 Solution Approach

1. **Burrows-Wheeler Transform (BWT):**
   - Converts the input DNA sequence to a transformed version that is easier to search.
2. **Backward Search:**
   - Searches the transformed BWT string backward to locate the pattern.
3. **Mutation Detection:**
   - Detects specific patterns like mutations and records the positions.

---

## 🧮 Algorithms Used

### 1️⃣ Burrows-Wheeler Transform (BWT)

- Steps:
  1. Append special end character `$` to DNA sequence.
  2. Generate all rotations of the sequence.
  3. Sort the rotations lexicographically.
  4. Take the last column as the transformed string.

**Advantage:**  
- Easy to compress.
- Searching is faster with backward search.

---

### 2️⃣ Backward Search

- Using **Last-to-First mapping**, we can efficiently search for patterns in BWT string.
- Finds occurrences and retrieves original positions in the DNA sequence.

---

## 🗂️ Project Structure
DNASequenceMatcher/ ├── src/ │ ├── Main.java # Main program │ ├── BWT.java # BWT logic │ ├── MutationDetector.java # Mutation pattern detection logic │ └── DNAUtils.java # Helper methods └── README.md # Project documentation
