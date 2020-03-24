(ns backwords.html.components.palindrome
  (:require [backwords.html.util :as util]
            [backwords.diginum :as diginum]
            [backwords.palindrome :as palindrome]))

(defn after-state [n]
  (let [digi-n         (diginum/from-int n)
        digi-p         (palindrome/next digi-n)
        is-palindrome? (= digi-n digi-p)]
    {:digi-n         digi-n
     :digi-p         digi-p
     :is-palindrome? is-palindrome?}))

(defn after [{:keys [digi-n digi-p is-palindrome?]}]
  [:div.leading-tight
   (if is-palindrome?
     [:div
      [:p [util/palindrome-span digi-p]]
      [:p.my-4 "is a palindrome!"]]
     [:div
      [:p [util/palindrome-span digi-p]]
      [:p.my-4 "follows" [:br]
       [util/digi-span digi-n]]])])
