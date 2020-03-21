(ns backwords.diginum
  "A diginum is like an odometer. It's a vector of counters, between 0 and 9,
  standing for the digits of a positive integer. Unlike an odometer, in a
  diginum the least significant digit is first.

  As an example, [3 2 6] is the diginum corresponding to the natural integer
  623.")

(defn from-int
  "Convert a natural integer into a diginum.

  ```
  (= [3 2 6] (from-int 623))
  ```"
  [n]
  {:pre [(nat-int? n)]}
  (loop [n      n
         result []]
    (if (< n 10)
      (conj result n)
      (let [q (quot n 10)
            m (mod n 10)]
        (recur q
               (conj result m))))))

(def ^:private places
  "An infinite sequence of the digit 'places': 1, 10, 100, etc."
  (iterate #(* % 10) 1))

(defn to-int
  "Convert a diginum to a natural integer.

  ```
  (= 623 (to-int [3 2 6]))
  ```"
  [digi-n]
  (->> digi-n
       (map * places)
       (reduce +)))

(defn roll
  "Return the next diginum, like rolling an odometer."
  [[smallest & more]]
  (if (= smallest 9)
    (into [0] (if more (roll more) [1]))
    (into [(inc smallest)] more)))

(defn gt
  "Is the diginum `a` greater than the diginum `b`?"
  [a b]
  (or (> (count a) (count b))
      (pos? (compare (vec (reverse a)) (vec (reverse b))))))
