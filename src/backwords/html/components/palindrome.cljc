(ns backwords.html.components.palindrome
  (:require [backwords.diginum :as diginum]
            [backwords.palindrome :as palindrome]))

(defn format-digi-n [digi-n]
  (let [segments (->> digi-n
                      (partition-all 3)
                      reverse
                      (map reverse))]
    (->> segments
         (map (fn [s]
                (into [:span] s)))
         (into [:span.stack-row-1]))))

(defn after [n]
  (let [digi-n (diginum/from-int n)]
    [:div
     [:p.font-bold.text-4xl (format-digi-n (palindrome/next digi-n))]
     [:p.my-4 "is the next palindrome after "
      [:span.font-semibold.text-2xl (format-digi-n digi-n)]]]))
