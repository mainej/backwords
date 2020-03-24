(ns backwords.html.components.palindrome
  (:require [backwords.html.util :as util]
            [backwords.diginum :as diginum]
            [backwords.palindrome :as palindrome]))

(defn after [n]
  (let [digi-n (diginum/from-int n)
        digi-p (palindrome/next digi-n)]
    [:div.leading-tight
     (if (= digi-n digi-p)
       [:div
        [:p [util/palindrome-span digi-p]]
        [:p.my-4 "is a palindrome!"]]
       [:div
        [:p [util/palindrome-span digi-p]]
        [:p.my-4 "follows" [:br]
         [util/digi-span digi-n]]])]))
