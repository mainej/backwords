(ns backwords.html.components.palindrome
  (:require [backwords.html.util :as util]
            [backwords.diginum :as diginum]
            [backwords.palindrome :as palindrome]))

(defn after [n]
  (let [digi-n (diginum/from-int n)]
    [:div
     [:p.font-bold.text-4xl [util/format-digi-n (palindrome/next digi-n)]]
     [:p.my-4 "is the next palindrome after "
      [:span.font-semibold.text-2xl [util/format-digi-n digi-n]]]]))
