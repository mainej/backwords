(ns backwords.palindrome-test
  (:require [backwords.palindrome :as sut]
            [backwords.diginum :as diginum]
            #?(:clj [clojure.test :as t]
               :cljs [cljs.test :as t :include-macros true])
            [clojure.test.check.clojure-test :as tc #?@(:cljs [:include-macros true])]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop #?@(:cljs [:include-macros true])]))

(defn naive-next [digi-n]
  (loop [digi-n digi-n]
    (if (sut/palindromic? digi-n)
      digi-n
      (recur (diginum/roll digi-n)))))

#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
(tc/defspec is-next
  1000
  (prop/for-all
   [n gen/nat]
   (let [digi-n (diginum/from-int n)]
     (= (naive-next digi-n)
        (sut/next digi-n)))))

(t/deftest fixed-counterexamples-naive
  (t/is (= [1 0 1] (naive-next (diginum/from-int 100)))))

(t/deftest fixed-counterexamples-next
  (let [n-next #(diginum/to-int (sut/next (diginum/from-int %)))]
    (t/is (= 99 (n-next 98)))
    (t/is (= 99 (n-next 99)))
    (t/is (= 101 (n-next 100)))
    ))
