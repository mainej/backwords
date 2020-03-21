(ns backwords.palindrome
  "Tools for finding the next palindromic [[backwords.diginum]]."
  (:refer-clojure :exclude [next])
  (:require [backwords.diginum :as diginum]))

(defn palindromic?
  "Is the `digi-n` the same backwards and forwards?"
  [digi-n]
  (= digi-n (reverse digi-n)))

(defn- from-even-high
  "Creates a palindrome from the most significant digits `high` of a diginum
  with an even number of digits.

  (from-even-high [1 2]) ;; -> [2 1 1 2]"
  [high]
  (let [low (vec (reverse high))]
    (into low high)))

(defn- from-odd-high
  "Creates a palindrome from the most significant digits `high` of a diginum
  with an odd number of digits.
  ```
  (from-odd-high [3 1 2]) ;; -> [2 1 3 1 2]
  ```"
  [high]
  (let [low (vec (reverse (rest high)))]
    (into low high)))

(defn next
  "Returns the smallest palindromic diginum that is greater than or equal to
  `digi-n`."
  [digi-n]
  (if (palindromic? digi-n)
    digi-n
    (let [length (count digi-n)
          high   (drop (quot length 2) digi-n)]
      (if (even? length)
        (let [candidate (from-even-high high)]
          (if (diginum/gt candidate digi-n)
            candidate
            (from-even-high (diginum/roll high))))
        (let [candidate (from-odd-high high)]
          (if (diginum/gt candidate digi-n)
            candidate
            (from-odd-high (diginum/roll high))))))))
