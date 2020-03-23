(ns backwords.html.components.palindrome
  (:require [backwords.html.util :as util]
            [backwords.diginum :as diginum]
            [backwords.palindrome :as palindrome]))

(defn after [n]
  (let [digi-n (diginum/from-int n)]
    [:div
     [:p [util/palindrome-span (palindrome/next digi-n)]]
     [:p.my-4 "is the next palindrome after "
      [util/digi-span digi-n]]]))
