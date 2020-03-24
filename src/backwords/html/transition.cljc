(ns backwords.html.transition
  (:require [backwords.html.util :as util]))

(def palindrome-classes
  (let [palindrome     "text-white bg-blue-500"
        not-palindrome "text-black bg-transparent"]
    {:appear      palindrome
     :appear-done palindrome

     :enter        "text-gray-300"
     :enter-active (str "transition " palindrome)
     :enter-done   palindrome

     :exit        "text-gray-700"
     :exit-active (str "transition " not-palindrome)
     :exit-done   not-palindrome}))

(defn palindrome [props child]
  [util/css-transition
   (assoc props
          :timeout     500
          :appear      true
          :class-names palindrome-classes)
   [:div.duration-500 child]])
