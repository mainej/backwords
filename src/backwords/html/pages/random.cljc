(ns backwords.html.pages.random
  (:require [reagent.core :as r]
            [backwords.diginum :as diginum]
            [backwords.palindrome :as palindrome]))

(defn format-digi-n [digi-n]
  (let [segments (->> digi-n
                      (partition-all 3)
                      reverse
                      (map reverse))]
    (->> segments
         (map (fn [s]
                (into [:span.ml-1] s)))
         (into [:span]))))

(defn palindrome-next [n]
  (let [digi-n (diginum/from-int n)]
    [:div
     [:p.mb-4 "The next palindrome after "]
     [:p.font-bold.text-2xl (format-digi-n digi-n)]
     [:p.my-4 " is "]
     [:p.font-bold.text-4xl (format-digi-n (palindrome/next digi-n))]]))

(defn expt [n e]
  #?(:clj (Math/pow n e)
     :cljs (js/Math.pow n e)))

(defn small-rand []
  (+ 10 (rand-int (expt 10 (inc (rand-int 7))))))

(defn page [_params]
  (let [!n (r/atom (small-rand))]
    (fn []
      [:div.font-mono.p-8.max-w-xs.m-auto.text-right
       [palindrome-next @!n]
       [:button.mt-8.px-4.py-2.border.border-gray-900 {:on-click #(reset! !n (small-rand))}
        "Give me another!"]])))
