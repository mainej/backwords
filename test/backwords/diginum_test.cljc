(ns backwords.diginum-test
  (:require [backwords.diginum :as sut]
            [clojure.string :as string]
            #?(:clj [clojure.test :as t]
               :cljs [cljs.test :as t :include-macros true])
            [clojure.test.check.clojure-test :as tc #?@(:cljs [:include-macros true])]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop #?@(:cljs [:include-macros true])]))

#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
(tc/defspec conversion-is-invertible
  100
  (prop/for-all
   [n gen/nat]
   (= n (sut/to-int (sut/from-int n)))))

#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
(tc/defspec generation-preserves
  100
  (prop/for-all
   [n gen/nat]
   (= (str n)
      (string/join "" (reverse (sut/from-int n))))))

#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
(tc/defspec rolling
  1000
  (prop/for-all
   [n gen/nat]
   (= (sut/roll (sut/from-int n))
      (sut/from-int (inc n)))))

(t/deftest fixed-counterexamples-from-int
  (t/is (= [0] (sut/from-int 0)))
  (t/is (= [1] (sut/from-int 1)))
  (t/is (= [0 1] (sut/from-int 10)))
  (t/is (= [0 0 1] (sut/from-int 100)))
  )

(t/deftest fixed-counterexamples-roll
  (t/is (= [9 9] (sut/roll (sut/from-int 98))))
  (t/is (= [0 0 1] (sut/roll (sut/from-int 99))))
  (t/is (= [1 0 1] (sut/roll (sut/from-int 100))))
  )
