(ns backwords.html.util
  (:require [reagent.core :as reagent]
            [reitit.frontend.easy :as rfe]
            #?(:cljs ["react-transition-group" :refer [CSSTransition]])))

(def href rfe/href)

(defn visit [route]
  (apply rfe/push-state route))

(defn parse-int [s]
  #?(:clj (Integer/parseInt s)
     :cljs (js/parseInt s)))

(defn format-digi-n [digi-n]
  (let [segments (->> digi-n
                      (partition-all 3)
                      reverse
                      (map reverse))]
    (->> segments
         (map (fn [s]
                (into [:span] s)))
         (into [:span.stack-row-1]))))

(defn palindrome-span [digi-n]
  [:span.font-bold.text-4xl [format-digi-n digi-n]])

(defn digi-span [digi-n]
  [:span.font-bold.text-2xl [format-digi-n digi-n]])

(def css-transition
  #?(:clj :div
     :cljs (reagent/adapt-react-class CSSTransition)))
